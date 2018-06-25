package com.example.soundloneteamcomp.searcharticle.SearchArticle;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response implements Parcelable {

    @SerializedName("docs")
    @Expose
    private List<Doc> docs = null;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.docs);
        dest.writeParcelable(this.meta, flags);
    }

    public Response() {
    }

    protected Response(Parcel in) {
        this.docs = in.createTypedArrayList(Doc.CREATOR);
        this.meta = in.readParcelable(Meta.class.getClassLoader());
    }

    public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel source) {
            return new Response(source);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };
}