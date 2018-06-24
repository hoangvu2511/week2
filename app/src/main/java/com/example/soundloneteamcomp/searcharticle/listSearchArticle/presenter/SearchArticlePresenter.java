package com.example.soundloneteamcomp.searcharticle.listSearchArticle.presenter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.soundloneteamcomp.searcharticle.R;
import com.example.soundloneteamcomp.searcharticle.SearchArticle.Doc;
import com.example.soundloneteamcomp.searcharticle.listSearchArticle.model.DataListener;
import com.example.soundloneteamcomp.searcharticle.listSearchArticle.model.SearchArticleModel;
import com.example.soundloneteamcomp.searcharticle.listSearchArticle.view.ArticleViewInreface;
import com.example.soundloneteamcomp.searcharticle.utils.NetWorkUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchArticlePresenter implements DataListener,SearchArticleInterface{
    private SearchArticleModel searchArticleModel;
    private ArticleViewInreface articleViewInreface;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public SearchArticlePresenter(SearchArticleModel searchArticleModel,ArticleViewInreface articleViewInreface){
        this.searchArticleModel = searchArticleModel;
        this.articleViewInreface = articleViewInreface;
    }


    @Override
    public void onResponse(List<Doc> docs) {
        articleViewInreface.hideLoading();
        articleViewInreface.showListArticle(docs);
    }

    @Override
    public void onError() {
        List<Doc> docs = new ArrayList<>();
        articleViewInreface.hideLoading();
        articleViewInreface.showListArticle(docs);
    }

    @Override
    public void getArticle() {
        articleViewInreface.showLoading();
        if (NetWorkUtil.isOnline() && NetWorkUtil.isNetworkAvailable()){
            searchArticleModel.getArticle(this);
        }
    }

    @Override
    public void searchByBody(String search) {
        articleViewInreface.showLoading();
        if (NetWorkUtil.isOnline() && NetWorkUtil.isNetworkAvailable()){
            searchArticleModel.searchByBody(this,search);
        }
    }

    @Override
    public void searchFull(String begindate, String sort,boolean art,boolean fashion,boolean sport, String query,int page) {
        articleViewInreface.showLoading();
        if (NetWorkUtil.isOnline() && NetWorkUtil.isNetworkAvailable()){
            searchArticleModel.searchFull(this,checkDate(begindate),checkQuerry(sort.toLowerCase()),setNewVal(art,fashion,sport),checkQuerry(query),page);
            articleViewInreface.hideLoading();
        }
    }

    @Override
    public void searchByFilter(@Nullable String begin, @Nullable String sort, @Nullable String newVal,int page) {
        articleViewInreface.showLoading();
        if (NetWorkUtil.isOnline() && NetWorkUtil.isNetworkAvailable()){
            searchArticleModel.searchByFilter(this,begin,sort,newVal,page);
        }
    }


    /**
     * @param art check box from filter
     * @param fashion check box from filter
     * @param sport check box from filter
     * @return news desk
     */
    private String setNewVal(boolean art,boolean fashion,boolean sport){
        if (!art&&!fashion&&!sport)
            return null;
        String a ="news_desk:(";
        String b ,c,d;
        if(art){
            b="\"Arts\"";
            a+=b;
        }
        if (fashion){
            c="\"Fashion & Style\"";
            if (art)
                a += " "+c;
            else
                a += c;
        }
        if (sport){
            d="\"Sports\"";
            if(art||fashion)
                a += " " + d;
            else
                a += d;
        }
        a += ")";
        Log.e("Error",a);
        return a;
    }

    /**
     * @param querry input
     * @return querry
     */
    private String checkQuerry(String querry){
        if (querry.equals(""))
            return null;
        return querry;
    }

    /**
     *
     * @param date date that pick in filter activity
     * @return string YYYYMMMDD
     */
    private String checkDate(String date){
        if (date.equals(""))
            return null;
        String[] a = date.split("/");
        String b = a[2]+a[1]+a[0];
        return b;
    }

}
