package com.example.driceenibt4_v1;


public class UserDataClass {

    public String prenom,nom,numTel,age,email,passeworld;

    public UserDataClass(){

    }

    public UserDataClass(String prenom, String numTel, String age, String email,String passeworld) {
        this.prenom = prenom;
        this.numTel = numTel;
        this.age = age;
        this.email=email;
        this.passeworld=passeworld;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasseworld() {
        return passeworld;
    }

    public void setPasseworld(String passeworld) {
        this.passeworld = passeworld;
    }






}
