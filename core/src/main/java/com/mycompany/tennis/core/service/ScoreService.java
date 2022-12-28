package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.*;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.joueurRepositoryImpl;
import com.mycompany.tennis.core.repository.scoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {

    private scoreRepositoryImpl scoreRepository;

    public ScoreService(){

        this.scoreRepository=new scoreRepositoryImpl();
    }

    public ScoreFullDto getScoreById(Long id){
        Score score=null;
        Session session =null;
        Transaction tx = null;
        ScoreFullDto scoreFullDto=null;
        try {
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            score=scoreRepository.getIdScoreId(id);

            scoreFullDto=new ScoreFullDto();
            scoreFullDto.setId(score.getId());
            scoreFullDto.setSet1(score.getSet1());
            scoreFullDto.setSet2(score.getSet2());
            scoreFullDto.setSet3(score.getSet3());
            scoreFullDto.setSet4(score.getSet4());
            scoreFullDto.setSet5(score.getSet5());


             MatchDto matchDto = new MatchDto();
             matchDto.setId(score.getMatch().getId());

            JoueurDto joueurDtofinaliste = new JoueurDto();
            joueurDtofinaliste.setId(score.getMatch().getFinaliste().getId());
            joueurDtofinaliste.setNom(score.getMatch().getFinaliste().getNom());
            joueurDtofinaliste.setPrenom(score.getMatch().getFinaliste().getPrenom());
            joueurDtofinaliste.setSexe(score.getMatch().getFinaliste().getSexe());
            matchDto.setFinaliste(joueurDtofinaliste);

            JoueurDto joueurDtoVainqueur = new JoueurDto();
            joueurDtoVainqueur.setId(score.getMatch().getVainqueur().getId());
            joueurDtoVainqueur.setNom(score.getMatch().getVainqueur().getNom());
            joueurDtoVainqueur.setPrenom(score.getMatch().getVainqueur().getPrenom());
            joueurDtoVainqueur.setSexe(score.getMatch().getVainqueur().getSexe());
            matchDto.setVainqueur(joueurDtoVainqueur);

            EpreuveFullDto epreuveMatch =new EpreuveFullDto();
            epreuveMatch.setId(score.getMatch().getEpreuve().getId());
            epreuveMatch.setAnnee(score.getMatch().getEpreuve().getAnnee());
            epreuveMatch.setTypeEpreuve(score.getMatch().getEpreuve().getTypeEpreuve());


            TournoiDto tournoiDto =new TournoiDto();
            tournoiDto.setId(score.getMatch().getEpreuve().getTournoi().getId());
            tournoiDto.setCode(score.getMatch().getEpreuve().getTournoi().getCode());
            tournoiDto.setNom(score.getMatch().getEpreuve().getTournoi().getNom());
            epreuveMatch.setTournoi(tournoiDto);

            matchDto.setEpreuve(epreuveMatch);

            scoreFullDto.setMatch(matchDto);

            tx.commit();

            System.out.println("Score Lu en base de données " );
        } catch (Exception e) {
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session!=null) {
                session.close();
            }
        }
        return scoreFullDto;
    }

    public void createScore(Joueur joueur){

    }

    public  void modifierNouveauScore(Long id,String nouveauNom){

    }

}
