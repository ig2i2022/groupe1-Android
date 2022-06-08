package com.example.sel.model;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

    @SerializedName("ID_TRANSACTION")
    private int id;
    @SerializedName("ETAT")
    private String etat;
    @SerializedName("ID_PROPOSITION")
    private int idProposition;
    @SerializedName("MONTANT_TRANSACTION")
    private int price;
    @SerializedName("ID_MEMBRE_BENEFICIAIRE")
    private int idBeneficaire;
    @SerializedName("ID_MEMBRE")
    private int idDonneur;
    @SerializedName("DESCRIPTION")
    private String description;
    @SerializedName("DATE_CREATION")
    private String creationDate;


    private Profile beneficiare;
    private Profile donneur;
    private Proposition proposition;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getIdProposition() {
        return idProposition;
    }

    public void setIdProposition(int idProposition) {
        this.idProposition = idProposition;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIdBeneficaire() {
        return idBeneficaire;
    }

    public void setIdBeneficaire(int idBeneficaire) {
        this.idBeneficaire = idBeneficaire;
    }

    public int getIdDonneur() {
        return idDonneur;
    }

    public void setIdDonneur(int idDonneur) {
        this.idDonneur = idDonneur;
    }

    public Profile getBeneficiare() {
        return beneficiare;
    }

    public void setBeneficiare(Profile beneficiare) {
        this.beneficiare = beneficiare;
    }

    public Profile getDonneur() {
        return donneur;
    }

    public void setDonneur(Profile donneur) {
        this.donneur = donneur;
    }

    public Proposition getProposition() {
        return proposition;
    }

    public void setProposition(Proposition proposition) {
        this.proposition = proposition;
    }

    public Date getCreationDate() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(creationDate);
        } catch (ParseException e) {
            return new Date();

        }

    }

    public void setCreationDate(String beginningDate) {
        this.creationDate = beginningDate;
    }
}
