package com.example.soundloneteamcomp.searcharticle.SearchArticle;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Headline implements Parcelable {

    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("kicker")
    @Expose
    private String kicker;
//    @SerializedName("content_kicker")
//    @Expose
//    private Object contentKicker;
    @SerializedName("print_headline")
    @Expose
    private String printHeadline;
    @SerializedName("name")
    @Expose
    private String name;
//    @SerializedName("seo")
//    @Expose
//    private Object seo;
    @SerializedName("sub")
    @Expose
    private String sub;

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

//    public Object getContentKicker() {
//        return contentKicker;
//    }
//
//    public void setContentKicker(Object contentKicker) {
//        this.contentKicker = contentKicker;
//    }
//
    public String getPrintHeadline() {
        return printHeadline;
    }

    public void setPrintHeadline(String printHeadline) {
        this.printHeadline = printHeadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    public Object getSeo() {
//        return seo;
//    }
//
//    public void setSeo(Object seo) {
//        this.seo = seo;
//    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.main);
        dest.writeString(this.kicker);
        dest.writeString(this.printHeadline);
        dest.writeString(this.name);
        dest.writeString(this.sub);
    }

    public Headline() {
    }

    protected Headline(Parcel in) {
        this.main = in.readString();
        this.kicker = in.readString();
        this.printHeadline = in.readString();
        this.name = in.readString();
        this.sub = in.readString();
    }

    public static final Parcelable.Creator<Headline> CREATOR = new Parcelable.Creator<Headline>() {
        @Override
        public Headline createFromParcel(Parcel source) {
            return new Headline(source);
        }

        @Override
        public Headline[] newArray(int size) {
            return new Headline[size];
        }
    };
}