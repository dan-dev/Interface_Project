package com.ismai.aninterface.interface_project;


import android.graphics.LightingColorFilter;
import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Parcelable {
    private String name;
    private String description;
    private double score;
    private double latitude;
    private double longitude;
    private double distance;
    private String contact;
    private String imageLink;

    public Place(String name, String description, double score, double latitude, double longitude, double distance, String contact, String imageLink) {
        this.name = name;
        this.description = description;
        this.score = score;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.contact = contact;
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeDouble(score);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeDouble(distance);
        parcel.writeString(contact);
        parcel.writeString(imageLink);
    }

    public static final Parcelable.Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel parcel) {
            Place myPlace = new Place(parcel.readString(), parcel.readString(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readString(), parcel.readString());
            /*myPlace.name = parcel.readString();
            myPlace.description = parcel.readString();
            myPlace.contact = parcel.readString();
            myPlace.distance = parcel.readDouble();
            myPlace.latitude = parcel.readDouble();
            myPlace.longitude = parcel.readDouble();
            myPlace.imageLink = parcel.readString();
            myPlace.score = parcel.readDouble();*/

            return myPlace;
        }

        @Override
        public Place[] newArray(int i) {
            return new Place[i];
        }
    };
}
