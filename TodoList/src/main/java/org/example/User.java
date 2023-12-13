package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class User {
    private static int dernierIdAttribue = 1;
    private int id;
    private String nom;
    private String prenom;
    private String  mail;
    private String  mdp;
    private LocalDate date;
    

    public User(String nom, String prenom, String mail, String mdp, LocalDate date) {
        if (isvalid(mail,nom,prenom,mdp,date)){
            this.id = dernierIdAttribue++;
            this.nom = nom;
            this.prenom = prenom;
            this.mail = mail;
            this.mdp = mdp;
            this.date = date;}


    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getAge(LocalDate dateNaissance){

        LocalDate dateActuelle = LocalDate.now();

        Period difference = Period.between(dateNaissance, dateActuelle);


        return difference.getYears();
    }
    public boolean  isvalid(String email,String nom ,String prenom,String mdp, LocalDate date){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

        return  (Pattern.matches(regex, email)&&( !nom.equals("")) && ((mdp.length()<40 )&& (mdp.length()>8)) &&  (!prenom.equals("")) && getAge(date) < 13);
    }






}