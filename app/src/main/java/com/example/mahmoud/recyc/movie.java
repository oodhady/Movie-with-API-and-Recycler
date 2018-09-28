package com.example.mahmoud.recyc;

import android.os.Parcel;
import android.os.Parcelable;

public class movie implements Parcelable {

     String adult,backdrop_path,id,lng,title,overview,poster_path,relase_date,vote_avg,vote_count,popularity;

    public movie(String adult, String backdrop_path, String id, String lng, String title, String overview, String poster_path, String relase_date, String vote_avg, String vote_count, String popularity) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.id = id;
        this.lng = lng;
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.relase_date = relase_date;
        this.vote_avg = vote_avg;
        this.vote_count = vote_count;
        this.popularity = popularity;
    }

    protected movie(Parcel in) {
        adult = in.readString();
        backdrop_path = in.readString();
        id = in.readString();
        lng = in.readString();
        title = in.readString();
        overview = in.readString();
        poster_path = in.readString();
        relase_date = in.readString();
        vote_avg = in.readString();
        vote_count = in.readString();
        popularity = in.readString();
    }

    public static final Creator <movie> CREATOR = new Creator <movie>() {
        @Override
        public movie createFromParcel(Parcel in) {
            return new movie(in);
        }

        @Override
        public movie[] newArray(int size) {
            return new movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(adult);
        parcel.writeString(backdrop_path);
        parcel.writeString(id);
        parcel.writeString(lng);
        parcel.writeString(title);
        parcel.writeString(overview);
        parcel.writeString(poster_path);
        parcel.writeString(relase_date);
        parcel.writeString(vote_avg);
        parcel.writeString(vote_count);
        parcel.writeString(popularity);
    }
}
