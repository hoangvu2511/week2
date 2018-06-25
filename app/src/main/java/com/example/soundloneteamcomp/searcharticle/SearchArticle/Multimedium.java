package com.example.soundloneteamcomp.searcharticle.SearchArticle;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Multimedium implements Parcelable {

    @SerializedName("rank")
    @Expose
    private int rank;
    @SerializedName("subtype")
    @Expose
    private String subtype;
//    @SerializedName("caption")
//    @Expose
//    private Object caption;
//    @SerializedName("credit")
//    @Expose
//    private Object credit;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("width")
    @Expose
    private int width;
    @SerializedName("legacy")
    @Expose
    private Legacy legacy;
    @SerializedName("subType")
    @Expose
    private String subType;
//    @SerializedName("crop_name")
//    @Expose
//    private Object cropName;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

//    public Object getCaption() {
//        return caption;
//    }
//
//    public void setCaption(Object caption) {
//        this.caption = caption;
//    }
//
//    public Object getCredit() {
//        return credit;
//    }
//
//    public void setCredit(Object credit) {
//        this.credit = credit;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Legacy getLegacy() {
        return legacy;
    }

    public void setLegacy(Legacy legacy) {
        this.legacy = legacy;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

//    public Object getCropName() {
//        return cropName;
//    }
//
//    public void setCropName(Object cropName) {
//        this.cropName = cropName;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.rank);
        dest.writeString(this.subtype);
        dest.writeString(this.type);
        dest.writeString(this.url);
        dest.writeInt(this.height);
        dest.writeInt(this.width);
        dest.writeParcelable(this.legacy, flags);
        dest.writeString(this.subType);
    }

    public Multimedium() {
    }

    protected Multimedium(Parcel in) {
        this.rank = in.readInt();
        this.subtype = in.readString();
        this.type = in.readString();
        this.url = in.readString();
        this.height = in.readInt();
        this.width = in.readInt();
        this.legacy = in.readParcelable(Legacy.class.getClassLoader());
        this.subType = in.readString();
    }

    public static final Parcelable.Creator<Multimedium> CREATOR = new Parcelable.Creator<Multimedium>() {
        @Override
        public Multimedium createFromParcel(Parcel source) {
            return new Multimedium(source);
        }

        @Override
        public Multimedium[] newArray(int size) {
            return new Multimedium[size];
        }
    };
}
