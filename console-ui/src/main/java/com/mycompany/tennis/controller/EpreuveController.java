package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLiteDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.service.EpreuveService;
import com.mycompany.tennis.core.service.ScoreService;

import java.util.Scanner;

public class EpreuveController {

    private EpreuveService epreuveService;

    public EpreuveController(){
        this.epreuveService =new EpreuveService();
    }

    public  void afficheDetailleListeJoeur(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir l'identifiant de l'epreuve à afficher :");
        long idEpreuve = scanner.nextLong();
        EpreuveFullDto epreuveFullDto = epreuveService.afficherDetailleEpreuveJoueur(idEpreuve);
        System.out.println("Le nom du tournoi selectionner est: " + epreuveFullDto.getTournoi().getNom() );

        for (JoueurDto joueurDto : epreuveFullDto.getParticipants()) {
            System.out.println("Liste des Joueurs " + joueurDto.getNom()+ "  " + joueurDto.getPrenom());
        }
    }

    public  void afficheDetaiDerniereEpreuveRollanGarros(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir l'identifiant de l'epreuve à afficher :");
        long idEpreuve = scanner.nextLong();
        EpreuveLiteDto epreuve = epreuveService.getEpreuveByIdSansTournoi(idEpreuve);

    }

    public void afficheListesEpreuvesBycodeTournoi(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir le code toirnoi a afficher ?");
        String codeTournoi = scanner.nextLine();
        for (EpreuveFullDto epreuveFullDto :epreuveService.afficherListeEpreuves(codeTournoi)) {
            System.out.println(epreuveFullDto.getId()+"  "+epreuveFullDto.getTypeEpreuve() + "  " + epreuveFullDto.getAnnee() + "  " + epreuveFullDto.getTournoi().getNom() );
        }
    }
}