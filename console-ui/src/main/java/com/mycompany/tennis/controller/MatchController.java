package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.*;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.service.EpreuveService;
import com.mycompany.tennis.core.service.MatchService;

import javax.persistence.ManyToOne;
import java.util.Scanner;

public class MatchController {

    private MatchService matchService;

    public MatchController(){
        this.matchService =new MatchService();
    }

    public  void afficheDetailMatch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir l'identifiant du match à afficher :");
        long idEpreuve = scanner.nextLong();
        MatchDto matchDto = matchService.getMatchParId(idEpreuve);

        System.out.println("Il s'agit d'un tournoi qui s'est deroulé en : " + matchDto.getEpreuve().getAnnee()+ "  et le  nom du tournoi est : "
                + matchDto.getEpreuve().getTournoi().getNom());

        System.out.println("Le nom et prenom du Vainqueur  est : " + matchDto.getVainqueur().getNom()+ " " + matchDto.getVainqueur().getPrenom());

        System.out.println("Le nom et prenom du finaliste est: " + matchDto.getFinaliste().getNom()+ " " + matchDto.getFinaliste().getPrenom());

        System.out.println(matchDto.getScore().getSet1());
        System.out.println(matchDto.getScore().getSet2());
        if (matchDto.getScore().getSet3()!=null){
            System.out.println(matchDto.getScore().getSet3());
        }
        if (matchDto.getScore().getSet4()!=null){
            System.out.println(matchDto.getScore().getSet4());
        }
        if (matchDto.getScore().getSet5()!=null){
            System.out.println(matchDto.getScore().getSet5());
        }
    }
    public void afficherTapisVert(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir l'identifianT du vainqueur à annuler:");
        long idEpreuve = scanner.nextLong();
        matchService.tapisVert(idEpreuve);
    }
    public void creeerMatch(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir l'identifiant de l'epreuve :");
        long idEpreuve = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Veuillez saisir l'identifiant du vainqueur : ");
        long idVainqueur = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Veuillez saisir l'identifiant du finaliste :");
        long idFinaliste = scanner.nextLong();

        MatchDto matchDto = new MatchDto();

        matchDto.setEpreuve(new EpreuveFullDto());
        matchDto.getEpreuve().setId(idEpreuve);

        matchDto.setVainqueur(new JoueurDto());
        matchDto.getVainqueur().setId(idVainqueur);

        matchDto.setFinaliste(new JoueurDto());
        matchDto.getFinaliste().setId(idFinaliste);

        System.out.println("Quelle est la valeur du premier Set :");
        byte set1 = scanner.nextByte();
        scanner.nextLine();

        System.out.println("Quelle est la valeur du deuxième Set :");
        byte set2 = scanner.nextByte();
        scanner.nextLine();

        System.out.println("Quelle est la valeur du troisième Set :");
        byte set3 = scanner.nextByte();
        scanner.nextLine();

        System.out.println("Quelle est la valeur du quatrième Set :");
        byte set4 = scanner.nextByte();
        scanner.nextLine();

        System.out.println("Quelle est la valeur du cinquième Set :");
        byte set5 = scanner.nextByte();
        scanner.nextLine();

        ScoreFullDto scoredto = new ScoreFullDto();
        scoredto.setSet1(set1);
        scoredto.setSet2(set2);
        scoredto.setSet3(set3);
        scoredto.setSet4(set4);
        scoredto.setSet5(set5);

        matchDto.setScore(scoredto);
        scoredto.setMatch(matchDto);

        matchService.creationMatch(matchDto);
    }
}