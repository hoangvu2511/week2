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

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder>{

    private List<Doc> docList;
    private Context ctx;
    private ItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_art,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Doc doc = docList.get(position);
        String title = doc.getHeadline().getMain();
        holder.textView.setText(title);
        int height = 150, width = 150;
        String a = "https://www.nytimes.com/";
        if (doc.getMultimedia().size() != 0) {
            Multimedium multimedium = doc.getMultimedia().get(0);
            a  += multimedium.getUrl();

        }

        Glide.with(ctx)
                .applyDefaultRequestOptions(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .placeholder(R.drawable.new_york_times_logo)
                        .override(width,height))
                .load(a)
                .into(holder.imageView);
    }

    public AdapterRecyclerView(Context context){
        this.ctx = context;
    }

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

    public void setListener(ItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        if (docList == null)
            return 0;
        return docList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.img)
        ImageView imageView;
        @BindView(R.id.description)
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(docList.get(getAdapterPosition()));
        }
    }

    public interface ItemClickListener {
            void onItemClick(Doc doc);
    }
}
