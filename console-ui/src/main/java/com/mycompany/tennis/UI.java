package com.mycompany.tennis;


import com.mycompany.tennis.controller.*;

public class UI {

 public  static  void main(String... args){


     //MatchController matchController = new MatchController();
     // matchController.afficherTapisVert();
     //matchController.afficheDetailMatch();
     //matchController.creeerMatch();

   // EpreuveController epreuveController = new EpreuveController();
     //epreuveController.afficheDetailleListeJoeur();
    // epreuveController.afficheListesEpreuvesBycodeTournoi();

    // ScoreController scoreController=new ScoreController();
     //scoreController.afficheDetailscore();

     JoueurController joueurController = new JoueurController();
     //joueurController.afficherDetailJoueur();
    // joueurController.creationDeJoueur();
     // joueurController.modificationNomJoueur();
     joueurController.afficheJoueursListe();

    // TournoiController tournoiController=new TournoiController();
    // tournoiController.creationDeTournoi();
     //tournoiController.afficherDetailTournoi();
     // tournoiController.seppressionTournoi();
     //joueurController.modificationSexeNouveauJoueur();
     // joueurController.suppressionIdJoueurEnBase();

  }

}
