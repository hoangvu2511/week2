package com.example.soundloneteamcomp.searcharticle.listSearchArticle.view;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.soundloneteamcomp.searcharticle.R;
import com.example.soundloneteamcomp.searcharticle.SearchArticle.Doc;
import com.example.soundloneteamcomp.searcharticle.activity.SettingActivity;
import com.example.soundloneteamcomp.searcharticle.listSearchArticle.model.SearchArticleModelImp;
import com.example.soundloneteamcomp.searcharticle.listSearchArticle.presenter.SearchArticleInterface;
import com.example.soundloneteamcomp.searcharticle.listSearchArticle.presenter.SearchArticlePresenter;
import com.example.soundloneteamcomp.searcharticle.recyclerView.ComplexAdapterRecyclerView;
import com.example.soundloneteamcomp.searcharticle.recyclerView.EndlessRecyclerViewScrollListener;
import com.example.soundloneteamcomp.searcharticle.utils.NetWorkUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ArticleViewInreface{

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @BindView(R.id.tool_bar) Toolbar toolbar;

    @BindView(R.id.progressBar) ProgressBar progressBar;

    @BindString(R.string.error_network) String Error;

    @BindString(R.string.error_dont_have_list) String Error_List;

    @BindString(R.string.GG) String anounce;

    private String dates = "",sort ="",searchQuery ="";
    boolean art=false,fashion=false,sport=false;

    private ComplexAdapterRecyclerView adapterRecyclerView;

    private SearchArticleInterface searchArticleInterface;

    private EndlessRecyclerViewScrollListener scrollListener;

    public int page = 1;

    ShareActionProvider miShare;

    MenuItem shareLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        NetWorkUtil.setContext(this);
        setUpView();
        SearchArticleModelImp modelImp = new SearchArticleModelImp();
        searchArticleInterface = new SearchArticlePresenter(modelImp,this);
        loadPage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                searchQuery = query;
                resetState();
                Snackbar.make(recyclerView,R.string.GG,Snackbar.LENGTH_LONG).show();
                loadPage();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    /**
     *
     * listen for click filter button action
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id  = item.getItemId();
        switch (id){
            case R.id.action_filter:
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("dates",dates);
                intent.putExtra("sort",sort);
                intent.putExtra("art",art);
                intent.putExtra("fashion",fashion);
                intent.putExtra("sport",sport);
                intent.putExtra("querry",searchQuery);
                startActivityForResult(intent,113);
                return true;
        }
        return false;
    }

    /**
     * Recieve data from filter activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 113)
            if (resultCode == 112){
                dates = data.getStringExtra("dates");
                sort = data.getStringExtra("sort");
                searchQuery = data.getStringExtra("querry");
                art = data.getBooleanExtra("art",false);
                fashion = data.getBooleanExtra("fashion",false);
                sport = data.getBooleanExtra("sport",false);
                resetState();
                loadPage();
            }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * set up recycler view
     */
    public void setUpView(){
        List<Doc> docs = new ArrayList<>();

        adapterRecyclerView = new ComplexAdapterRecyclerView(this);
        adapterRecyclerView.setData(docs);

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapterRecyclerView);

        adapterRecyclerView.setListener(new ComplexAdapterRecyclerView.ItemClickListener() {
            @Override
            public void onItemClick(Doc doc) {
                toWebTab(doc.getWebUrl());
            }
        });

        scrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadNextDataFromApi(page);

            }
        };
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);
    }

    /**
     * show list get from calling api
     * @param docs
     */
    @Override
    public void showListArticle(List<Doc> docs) {
        if (docs.size() == 0){
            Snackbar.make(recyclerView,R.string.error_dont_have_list,Snackbar.LENGTH_LONG)
                    .setAction(R.string.action_ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    }).show() ;
        }
        else{
            adapterRecyclerView.setData(docs);
        }
    }

    /**
     * show progress bar
     */
    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * hide progress bar
     */
    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    /**
     * load more data from api
     */
    public void loadNextDataFromApi(int offset){
        Log.e("Halo","has scroll");
        page++;
        loadPage();
    }


    /**
     * see detail an item
     * @param url link web
     */
    private void toWebTab(String url) {


        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_share);


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.android.chrome");
        intent.putExtra(Intent.EXTRA_TEXT, url);

        int requestCode = 100;

//        PendingIntent pendingIntent = PendingIntent.getActivity(this,
//                requestCode,
//                intent,PendingIntent.FLAG_ONE_SHOT);

        PendingIntent pendingIntent = createPendingShareIntent();

//        builder.addDefaultShareMenuItem();
        builder.setActionButton(bitmap, "Share Link", pendingIntent, true);



        builder.setToolbarColor(ContextCompat.getColor(this,R.color.colorAqua));

        CustomTabsIntent customTabsIntent1 = builder.build();

        customTabsIntent1.launchUrl(MainActivity.this, Uri.parse(url));


    }

    /**
     * reset page list of adapter and scrollListener
     */
    private void resetState(){
        page = 1;
        adapterRecyclerView.clearData();
        scrollListener.resetState();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void loadPage(){
        searchArticleInterface.searchFull(dates,sort,art,fashion,sport,searchQuery,page);
    }

    private PendingIntent createPendingShareIntent() {
        Intent actionIntent = new Intent(Intent.ACTION_SEND);
        actionIntent.setType("text/plain");
        actionIntent.putExtra(Intent.EXTRA_TEXT, "Share text");
        return PendingIntent.getActivity(
                getApplicationContext(), 0, actionIntent, 0);
    }
}
