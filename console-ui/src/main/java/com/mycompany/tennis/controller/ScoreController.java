package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.ScoreFullDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.service.JoueurService;
import com.mycompany.tennis.core.service.ScoreService;

import java.util.Scanner;

public class ScoreController {

    private ScoreService scoreService;

    public ScoreController(){
        this.scoreService =new ScoreService();
    }
    public  void afficheDetailscore(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir l'identifiant du score à afficher :");
        long idScore = scanner.nextLong();
        ScoreFullDto scoreDto= scoreService.getScoreById(idScore);
        System.out.println("Il s'agit d'un tournoi qui s'est deroulé en : " + scoreDto.getMatch().getEpreuve().getTournoi().getNom()+ " en "
                + scoreDto.getMatch().getEpreuve().getAnnee() + " de type :"+ scoreDto.getMatch().getEpreuve().getTypeEpreuve());

        System.out.println("Le nom et prenom du Vainqueur  est : " + scoreDto.getMatch().getVainqueur().getNom()+ " " + scoreDto.getMatch().getVainqueur().getPrenom());

        System.out.println("Le nom et prenom du finaliste est: " + scoreDto.getMatch().getFinaliste().getNom()+ " " + scoreDto.getMatch().getFinaliste().getPrenom());

        System.out.println(scoreDto.getSet1());
        System.out.println(scoreDto.getSet2());
        if (scoreDto.getSet3()!=null){
            System.out.println(scoreDto.getSet3());
        }
        if (scoreDto.getSet4()!=null){
            System.out.println(scoreDto.getSet4());
        }
        if (scoreDto.getSet5()!=null){
            System.out.println(scoreDto.getSet5());
        }
    }
}