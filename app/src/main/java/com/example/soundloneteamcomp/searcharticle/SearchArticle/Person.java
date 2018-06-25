package com.example.soundloneteamcomp.searcharticle.SearchArticle;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person implements Parcelable {

    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("middlename")
    @Expose
    private String middlename;
    @SerializedName("lastname")
    @Expose
    private String lastname;
//    @SerializedName("qualifier")
//    @Expose
//    private Object qualifier;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("organization")
    @Expose
    private String organization;
    @SerializedName("rank")
    @Expose
    private Integer rank;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

//    public Object getQualifier() {
//        return qualifier;
//    }
//
//    public void setQualifier(Object qualifier) {
//        this.qualifier = qualifier;
//    }

    public Object getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstname);
        dest.writeString(this.middlename);
        dest.writeString(this.lastname);
        dest.writeString(this.title);
        dest.writeString(this.role);
        dest.writeString(this.organization);
        dest.writeValue(this.rank);
    }

    public Person() {
    }

    protected Person(Parcel in) {
        this.firstname = in.readString();
        this.middlename = in.readString();
        this.lastname = in.readString();
        this.title = in.readString();
        this.role = in.readString();
        this.organization = in.readString();
        this.rank = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
