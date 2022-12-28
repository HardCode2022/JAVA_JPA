package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.*;
import com.mycompany.tennis.core.service.JoueurService;
import com.mycompany.tennis.core.service.MatchService;

import java.util.List;

public class TestTD {

    public static void main(String... args){
         //JoueurService joueurService =new JoueurService();
        // List<Tournoi> listes = joueurService.getListTournoi();
        // for (Tournoi liste : listes) {
        // System.out.println( liste.getId() +" "+ liste.getNom()+ "  " + liste.getCode());
        //}
        //Joueur joueur = new Joueur();
        //joueur.setNom("CAMARA");
        //joueur.setPrenom("Marcel");
        //joueur.setSexe('H');
        // joueurService.createJoueur(joueur);
        //System.out.println(joueur.getNom()+ "  " + joueur.getPrenom() + "  " + joueur.getId());

        MatchService matchService = new MatchService();

        Match match = new Match();
        Score score = new Score();

        Joueur maurice = new Joueur();
        maurice.setId(48L);

        Joueur fatou = new Joueur();
        fatou.setId(49L);

        match.setVainqueur(maurice);
        match.setFinaliste(fatou);

        Epreuve epreuve = new Epreuve();
        epreuve.setId(10L);
        match.setEpreuve(epreuve);

        score.setSet1((byte)3);
        score.setSet2((byte)4);
        score.setSet3((byte)6);

        match.setScore(score);
        score.setMatch(match);

       // matchService.enregisterNouveauMatch(match);
       }

}
