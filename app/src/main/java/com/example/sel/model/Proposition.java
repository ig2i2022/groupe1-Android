package com.example.sel.model;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Proposition {
    @SerializedName("ID_PROPOSITION")
    private int id;
    @SerializedName("DESCRIPTION")
    private String description;
    @SerializedName("DATE_DEBUT")
    private String beginningDate;
    @SerializedName("DATE_FIN")
    private String endingDate;
    private float latitude;
    private float longitude;
    @SerializedName("ID_COMPETENCE")
    private int idCompetence;
    @SerializedName("ID_MEMBRE")
    private int idMembre;
    private Profile profile;
    @SerializedName("prix")
    private int prix;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBeginningDate() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(beginningDate);
        } catch (ParseException e) {
            return new Date();

        }
    }

    public void setBeginningDate(String beginningDate) {
        this.beginningDate = beginningDate;
    }

    public Date getEndingDate() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(endingDate);
        } catch (ParseException e) {
            return new Date();

        }

    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getIdCompetence() {
        return idCompetence;
    }

    public void setIdCompetence(int idCompetence) {
        this.idCompetence = idCompetence;
    }

    public int getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
