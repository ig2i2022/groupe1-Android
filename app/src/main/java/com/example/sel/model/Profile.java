package com.example.sel.model;

import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("ID_MEMBRE")
    private int id;
    @SerializedName("NOM_MEMBRE")
    private String name;
    @SerializedName("PRENOM_MEMBRE")
    private String firstName;
    @SerializedName("MAIL_MEMBRE")
    private String mail;
    @SerializedName("PSEUDO")
    private String pseudo;
    @SerializedName("IS_PRO")
    private boolean isPro;
    @SerializedName("SOLDE_HEURE")
    private int balance;
    @SerializedName("HABITANT")
    private boolean living;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public boolean isPro() {
        return isPro;
    }

    public void setPro(boolean pro) {
        isPro = pro;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", mail='" + mail + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", isPro=" + isPro +
                ", balance=" + balance +
                ", living=" + living +
                '}';
    }
}
