package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.service.JoueurService;
import com.mycompany.tennis.core.service.TournoiService;

import java.util.Scanner;

public class TournoiController {

    private TournoiService tournoiService;

    public TournoiController(){
        this.tournoiService = new TournoiService();
    }

    public void afficherDetailTournoi(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir l'identifiant du Tournoi à afficher :");
        long idTournoi = scanner.nextLong();
        TournoiDto tournoiDto = tournoiService.getTournoi(idTournoi);
        System.out.println(" Le tournoi dont l'id est : " + idTournoi + " correspond à : " + tournoiDto.getNom() +"  "+ tournoiDto.getCode());
    }

    public void creationDeTournoi(){
        Scanner scanner = new Scanner(System.in);

        TournoiDto tournoiDto = new TournoiDto();

        if (tournoiDto.getNom() == null) {
            System.out.println("Veuillez saisir le Nom du Tournoi :");
            tournoiDto.setNom(scanner.nextLine());
        }
        if (tournoiDto.getCode() == null) {
            System.out.println("Veuillez saisir le Code du Tournoi :");
            tournoiDto.setCode(scanner.nextLine());
        }

        tournoiService.creationTournoi(tournoiDto);
    }

    public void seppressionTournoi(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir l'identifiant du Tournoi à supprimer : ");
        long idTournoi = scanner.nextLong();
        tournoiService.supprimerTournoiEnBase(idTournoi);
    }
}
