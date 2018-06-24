package com.example.soundloneteamcomp.searcharticle.recyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.soundloneteamcomp.searcharticle.R;
import com.example.soundloneteamcomp.searcharticle.SearchArticle.Doc;
import com.example.soundloneteamcomp.searcharticle.SearchArticle.Multimedium;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComplexAdapterRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Doc> docList;
    private Context ctx;
    private ItemClickListener listener;

    private final int Arts = 1 , Fashion_Style = 2, Sport = 3;

    public void setData(List<Doc> docs){
        if (docList == null)
            this.docList = docs;
        if (docList!=null)
            this.docList.addAll(docs);

        notifyDataSetChanged();
    }

    public void clearData(){
        if (docList!=null)
            this.docList.clear();
        notifyDataSetChanged();
    }

    public ComplexAdapterRecyclerView(Context context){
        this.ctx = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case Arts:
                View art = inflater.inflate(R.layout.recyclerview_item_art,parent,false);
                holder = new ViewHolderArt(art);
                break;
            case Fashion_Style:
                View fash = inflater.inflate(R.layout.recyclerview_item_fs,parent,false);
                holder = new ViewHolderF_S(fash);
                break;
            case Sport:
                View sport = inflater.inflate(R.layout.recyclerview_item_sport,parent,false);
                holder = new ViewHolderSport(sport);
                break;
            case 0:
                View zer0 = inflater.inflate(R.layout.recyclerview_item_no_title,parent,false);
                holder = new ViewHolderNoTitle(zer0);
                break;
            default:
                View defaul = inflater.inflate(R.layout.recyclerview_item_no_title,parent,false);
                holder = new ViewHolderArt(defaul);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case Arts:
                ViewHolderArt holderArt = (ViewHolderArt) holder;
                configureViewHolderArt(holderArt,position);
                break;
            case Fashion_Style:
                ViewHolderF_S holderFS = (ViewHolderF_S) holder;
                configureViewHolderFS(holderFS,position);
                break;
            case Sport:
                ViewHolderSport holderSport = (ViewHolderSport) holder;
                configureViewHolderSport(holderSport,position);
                break;
            case 0:
                ViewHolderNoTitle holderNoTitle = (ViewHolderNoTitle) holder;
                configureViewHolderNoTitle(holderNoTitle,position);
                break;
        }
    }

    private void configureViewHolderNoTitle(ViewHolderNoTitle holderNoTitle, int position) {
        Doc doc = docList.get(position);
        String descrip = doc.getHeadline().getMain();
        holderNoTitle.textView.setText(descrip);
        String a = "https://www.nytimes.com/";
        if (doc.getMultimedia().size() != 0) {
            Multimedium multimedium = doc.getMultimedia().get(0);
            a  += multimedium.getUrl();
        }
        loadImg(ctx,holderNoTitle.imageView,a);
    }

    private void configureViewHolderSport(ViewHolderSport holderSport, int position) {
        Doc doc = docList.get(position);
        String title = doc.getHeadline().getMain();
        holderSport.textView.setText(title);
        holderSport.sportTitle.setText(R.string.sport);
        String a = "https://www.nytimes.com/";
        if (doc.getMultimedia().size() != 0) {
            Multimedium multimedium = doc.getMultimedia().get(0);
            a  += multimedium.getUrl();

        }
        loadImg(ctx,holderSport.imageView,a);
    }

    private void configureViewHolderFS(ViewHolderF_S holderFS, int position) {
        Doc doc = docList.get(position);
        String title = doc.getHeadline().getMain();
        holderFS.textView.setText(title);
        holderFS.fsTitle.setText(R.string.fashion);
        String a = "https://www.nytimes.com/";
        if (doc.getMultimedia().size() != 0) {
            Multimedium multimedium = doc.getMultimedia().get(0);
            a  += multimedium.getUrl();

        }
        loadImg(ctx,holderFS.imageView,a);
    }

    private void configureViewHolderArt(ViewHolderArt holderArt, int position) {
        Doc doc = docList.get(position);
        String descrip = doc.getHeadline().getMain();
        holderArt.textView.setText(descrip);
        holderArt.artTitle.setText("Arts");
        String a = "https://www.nytimes.com/";
        if (doc.getMultimedia().size() != 0) {
            Multimedium multimedium = doc.getMultimedia().get(0);
            a  += multimedium.getUrl();

        }
        loadImg(ctx,holderArt.imageView,a);
    }

    @Override
    public int getItemCount() {
        if (docList == null)
            return 0;
        return docList.size();
    }

    @Override
    public int getItemViewType(int position) {
        String newDesk = docList.get(position).getNewsDesk();
        if (newDesk == null)
            return 0;
        switch (newDesk){
            case "Arts":
                return Arts;
            case "Fashion & Style":
                return Fashion_Style;
            case "Sports":
                return Sport;
            default:
                return 0;
        }
    }

    private void loadImg(Context ctx,ImageView view, String url){
        int height = 150, width = 150;
        Glide.with(ctx)
                .applyDefaultRequestOptions(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .placeholder(R.drawable.new_york_times_logo)
                        .override(width,height))
                .load(url)
                .into(view);
    }

    public void setListener(ItemClickListener listener){
        this.listener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(Doc doc);
    }

    public class ViewHolderArt extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.img)
        ImageView imageView;

        @BindView(R.id.description)
        TextView textView;

        @BindView(R.id.tilteArt)
        TextView artTitle;

        public ViewHolderArt(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(docList.get(getAdapterPosition()));
        }
    }

    public class ViewHolderF_S extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.img)
        ImageView imageView;
        @BindView(R.id.description)
        TextView textView;
        @BindView(R.id.fsTilte)
        TextView fsTitle;

        public ViewHolderF_S(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(docList.get(getAdapterPosition()));
        }
    }

    public class ViewHolderSport extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.img)
        ImageView imageView;
        @BindView(R.id.description)
        TextView textView;
        @BindView(R.id.sportTitle)
        TextView sportTitle;

        public ViewHolderSport(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(docList.get(getAdapterPosition()));
        }
    }

    public class ViewHolderNoTitle extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.img)
        ImageView imageView;
        @BindView(R.id.description)
        TextView textView;
        public ViewHolderNoTitle(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(docList.get(getAdapterPosition()));
        }
    }

}
