package com.mycompany.tennis.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Tournoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String code;

    public boolean equals(Object o){
        if (this==o) return  true;
        if (!(o instanceof Tournoi)) return  false;
        Tournoi tournoi = (Tournoi) o;
        return Objects.equals(id,tournoi.id) &&
                Objects.equals(nom,tournoi.nom)&&
                Objects.equals(code,tournoi.code);
    }
    @Override
    public int hashCode(){
        return Objects.hash(id,nom,code);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
