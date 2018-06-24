package com.example.soundloneteamcomp.searcharticle.SearchArticle;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Doc implements Parcelable {

    @SerializedName("web_url")
    @Expose
    private String webUrl;
    @SerializedName("snippet")
    @Expose
    private String snippet;
    @SerializedName("print_page")
    @Expose
    private String printPage;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("multimedia")
    @Expose
    private List<Multimedium> multimedia = null;
    @SerializedName("headline")
    @Expose
    private Headline headline;
    @SerializedName("keywords")
    @Expose
    private List<Keyword> keywords = null;
    @SerializedName("pub_date")
    @Expose
    private String pubDate;
    @SerializedName("document_type")
    @Expose
    private String documentType;
    @SerializedName("news_desk")
    @Expose
    private String newsDesk;
    @SerializedName("byline")
    @Expose
    private Byline byline;
    @SerializedName("type_of_material")
    @Expose
    private String typeOfMaterial;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("word_count")
    @Expose
    private int wordCount;
    @SerializedName("score")
    @Expose
    private float score;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("section_name")
    @Expose
    private String sectionName;

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getPrintPage() {
        return printPage;
    }

    public void setPrintPage(String printPage) {
        this.printPage = printPage;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedium> multimedia) {
        this.multimedia = multimedia;
    }

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getNewsDesk() {
        return newsDesk;
    }

    public void setNewsDesk(String newsDesk) {
        this.newsDesk = newsDesk;
    }

    public Byline getByline() {
        return byline;
    }

    public void setByline(Byline byline) {
        this.byline = byline;
    }

    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.webUrl);
        dest.writeString(this.snippet);
        dest.writeString(this.printPage);
        dest.writeString(this.source);
        dest.writeList(this.multimedia);
        dest.writeParcelable(this.headline, flags);
        dest.writeList(this.keywords);
        dest.writeString(this.pubDate);
        dest.writeString(this.documentType);
        dest.writeString(this.newsDesk);
        dest.writeParcelable(this.byline, flags);
        dest.writeString(this.typeOfMaterial);
        dest.writeString(this.id);
        dest.writeInt(this.wordCount);
        dest.writeFloat(this.score);
        dest.writeString(this._abstract);
        dest.writeString(this.sectionName);
    }

    public Doc() {
    }

    protected Doc(Parcel in) {
        this.webUrl = in.readString();
        this.snippet = in.readString();
        this.printPage = in.readString();
        this.source = in.readString();
        this.multimedia = new ArrayList<Multimedium>();
        in.readList(this.multimedia, Multimedium.class.getClassLoader());
        this.headline = in.readParcelable(Headline.class.getClassLoader());
        this.keywords = new ArrayList<Keyword>();
        in.readList(this.keywords, Keyword.class.getClassLoader());
        this.pubDate = in.readString();
        this.documentType = in.readString();
        this.newsDesk = in.readString();
        this.byline = in.readParcelable(Byline.class.getClassLoader());
        this.typeOfMaterial = in.readString();
        this.id = in.readString();
        this.wordCount = in.readInt();
        this.score = in.readFloat();
        this._abstract = in.readString();
        this.sectionName = in.readString();
    }

    public static final Parcelable.Creator<Doc> CREATOR = new Parcelable.Creator<Doc>() {
        @Override
        public Doc createFromParcel(Parcel source) {
            return new Doc(source);
        }

        @Override
        public Doc[] newArray(int size) {
            return new Doc[size];
        }
    };
}