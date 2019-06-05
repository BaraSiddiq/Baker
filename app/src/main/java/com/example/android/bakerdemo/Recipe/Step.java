package com.example.android.bakerdemo.Recipe;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable {

    int id;
    String summary;
    String description;
    String vid;
    String thumbnail;


    public Step(int id, String summary, String description, String vid, String thumbnail) {
        this.id = id;
        this.summary = summary;
        this.description = description;
        this.vid = vid;
        this.thumbnail = thumbnail;
    }

    protected Step(Parcel in) {
        id = in.readInt();
        summary = in.readString();
        description = in.readString();
        vid = in.readString();
        thumbnail = in.readString();
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(summary);
        dest.writeString(description);
        dest.writeString(vid);
        dest.writeString(thumbnail);
    }
}
