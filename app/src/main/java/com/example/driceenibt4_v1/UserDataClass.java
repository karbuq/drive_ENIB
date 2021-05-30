package com.example.driceenibt4_v1;


public class UserDataClass {

    public String prenom,nom,numTel,age,email;

    public UserDataClass(){

    }

    public UserDataClass(String prenom, String nom, String numTel, String age,String email) {
        this.prenom = prenom;
        this.nom = nom;
        this.numTel = numTel;
        this.age = age;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
