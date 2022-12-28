package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.service.JoueurService;

import java.util.Scanner;

public class JoueurController {

    private JoueurService joueurService;

    public JoueurController() {

        this.joueurService = new JoueurService();
    }

    public void afficherDetailJoueur() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir l'identifiant du joueur à afficher :");
        long idJouer = scanner.nextLong();
        Joueur joueur = joueurService.lireJoueur(idJouer);
        System.out.println(" Le joueur dont l'id est " + idJouer + " correspond à : " + joueur.getPrenom() + "  " + joueur.getNom());
    }

    public void creationDeJoueur() {
        Scanner scanner = new Scanner(System.in);
        Joueur joueur = new Joueur();
        if (joueur.getNom() == null) {
            System.out.println("Veuillez saisir le Nom du joueur :");
            joueur.setNom(scanner.nextLine());
        }
        if (joueur.getPrenom() == null) {
            System.out.println("Veuillez saisir le Prenom du joueur :");
            joueur.setPrenom(scanner.nextLine());
        }
        if (joueur.getSexe() == null) {
            System.out.println("Veuillez saisir le sexe du joueur :");
            joueur.setSexe(scanner.nextLine().charAt(0));
        }
        joueurService.createJoueur(joueur);
        System.out.println("Le joueur a été creer avec l'identifinat : " + joueur.getId());
    }

    public void modificationNomJoueur() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir l'identifiant du joueur à modifier :");
        long id = scanner.nextLong();
        //next line à la suite pour permettre la bonne prise des valeurs
        scanner.nextLine();
        System.out.println("Veuillez saisir le nouveau nom du joueur à modifier :");
        String nouveauNom=scanner.nextLine();
        joueurService.modifierNomJoueurNouveau(id,nouveauNom);

    }
    public void modificationSexeNouveauJoueur() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir l'identifiant du joueur à modifier :");
        long id = scanner.nextLong();
        //next line à la suite pour permettre la bonne prise des valeurs
        scanner.nextLine();
        System.out.println("Veuillez saisir le nouveau sexe du joueur à modifier :");
        char sexe=scanner.nextLine().charAt(0);
        joueurService.modifierSexeJoueur(id,sexe);
    }

    public void suppressionIdJoueurEnBase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir l'identifiant du joueur à supprimer :");
        long id = scanner.nextLong();
        scanner.nextLine();
        joueurService.supprimerUnJoueurEnBaseDonnees(id);

    }
    public  void afficheJoueursListe(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voullez vous afficher la liste des Hommes (H) ou des femmaes (F) ?");
        char sexe = scanner.nextLine().charAt(0);
        for (JoueurDto joueurs:joueurService.afficherListeJoueurs(sexe)) {
            System.out.println(joueurs.getNom() + "  " + joueurs.getPrenom() + "  " + joueurs.getSexe() );
        }

    }
}