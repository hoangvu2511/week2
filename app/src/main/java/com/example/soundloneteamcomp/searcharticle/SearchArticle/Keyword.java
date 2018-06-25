package com.example.soundloneteamcomp.searcharticle.SearchArticle;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Keyword implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("rank")
    @Expose
    private Integer rank;
//    @SerializedName("major")
//    @Expose
//    private Object major;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

//    public Object getMajor() {
//        return major;
//    }
//
//    public void setMajor(Object major) {
//        this.major = major;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.value);
        dest.writeValue(this.rank);
    }

    public Keyword() {
    }

    protected Keyword(Parcel in) {
        this.name = in.readString();
        this.value = in.readString();
        this.rank = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Keyword> CREATOR = new Parcelable.Creator<Keyword>() {
        @Override
        public Keyword createFromParcel(Parcel source) {
            return new Keyword(source);
        }

        @Override
        public Keyword[] newArray(int size) {
            return new Keyword[size];
        }
    };
}