package model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Proposition {

    @SerializedName("ID_PROPOSITION")
    private int id;
    @SerializedName("DESCRIPTION")
    private String description;
    /*@SerializedName("DATE_DEBUT")
    private LocalDateTime beginningDate;
    @SerializedName("DATE_FIN")
    private LocalDateTime endingDate;*/
    private float latitude;
    private float longitude;
    @SerializedName("CATEGORIE")
    private String categorie;
    @SerializedName("COMPETENCE")
    private String competence;



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

    /*public LocalDateTime getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(LocalDateTime beginningDate) {
        this.beginningDate = beginningDate;
    }

    public LocalDateTime getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDateTime endingDate) {
        this.endingDate = endingDate;
    }*/

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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    @Override
    public String toString() {
        return description;
    }
}
