package com.example.soundloneteamcomp.searcharticle.SearchArticle;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListSearch implements Parcelable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("response")
    @Expose
    private Response response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.copyright);
        dest.writeParcelable(this.response, flags);
    }

    public ListSearch() {
    }

    protected ListSearch(Parcel in) {
        this.status = in.readString();
        this.copyright = in.readString();
        this.response = in.readParcelable(Response.class.getClassLoader());
    }

    public static final Parcelable.Creator<ListSearch> CREATOR = new Parcelable.Creator<ListSearch>() {
        @Override
        public ListSearch createFromParcel(Parcel source) {
            return new ListSearch(source);
        }

        @Override
        public ListSearch[] newArray(int size) {
            return new ListSearch[size];
        }
    };
}