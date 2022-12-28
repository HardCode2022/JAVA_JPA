package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.*;
import com.mycompany.tennis.core.entity.*;
import com.mycompany.tennis.core.repository.epreuveRepositoryImpl;
import com.mycompany.tennis.core.repository.joueurRepositoryImpl;
import com.mycompany.tennis.core.repository.matchRepositoryImpl;
import com.mycompany.tennis.core.repository.scoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class MatchService {

    private matchRepositoryImpl matchRepository;
    private joueurRepositoryImpl joueurRepository;
    private epreuveRepositoryImpl epreuveRepository;

    public MatchService(){
        this.matchRepository= new matchRepositoryImpl();
        this.epreuveRepository = new epreuveRepositoryImpl();
        this.joueurRepository=new joueurRepositoryImpl();
    }

    public MatchDto getMatchParId(Long Id){
        Session session = null;
        Transaction tx = null;
        Match match=null;
        MatchDto matchDto=null;
        try {
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            match=matchRepository.getMatchById(Id);

            matchDto = new MatchDto();
            matchDto.setId(match.getId());

            JoueurDto joueurDtofinaliste = new JoueurDto();
            joueurDtofinaliste.setId(match.getFinaliste().getId());
            joueurDtofinaliste.setNom(match.getFinaliste().getNom());
            joueurDtofinaliste.setPrenom(match.getFinaliste().getPrenom());
            joueurDtofinaliste.setSexe(match.getFinaliste().getSexe());
            matchDto.setFinaliste(joueurDtofinaliste);

            JoueurDto joueurDtoVainqueur = new JoueurDto();
            joueurDtoVainqueur.setId(match.getVainqueur().getId());
            joueurDtoVainqueur.setNom(match.getVainqueur().getNom());
            joueurDtoVainqueur.setPrenom(match.getVainqueur().getPrenom());
            joueurDtoVainqueur.setSexe(match.getVainqueur().getSexe());
            matchDto.setVainqueur(joueurDtoVainqueur);

            EpreuveFullDto epreuveMatch =new EpreuveFullDto();
            epreuveMatch.setId(match.getEpreuve().getId());
            epreuveMatch.setAnnee(match.getEpreuve().getAnnee());
            epreuveMatch.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());

            TournoiDto tournoiDto =new TournoiDto();
            tournoiDto.setId(match.getEpreuve().getTournoi().getId());
            tournoiDto.setCode(match.getEpreuve().getTournoi().getCode());
            tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
            epreuveMatch.setTournoi(tournoiDto);

            matchDto.setEpreuve(epreuveMatch);

            ScoreFullDto scoreFullDto =new ScoreFullDto();
            scoreFullDto.setId(match.getScore().getId());
            scoreFullDto.setSet1(match.getScore().getSet1());
            scoreFullDto.setSet2(match.getScore().getSet2());
            scoreFullDto.setSet3(match.getScore().getSet3());
            scoreFullDto.setSet4(match.getScore().getSet4());
            scoreFullDto.setSet5(match.getScore().getSet5());

            matchDto.setScore(scoreFullDto);
            scoreFullDto.setMatch(matchDto);


            tx.commit();
            System.out.println("Match Lu en base de données ");
        }

        catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return matchDto;
    }
    public void tapisVert(Long Id){
        Session session = null;
        Transaction tx = null;
        Match match=null;
        try {
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            match=matchRepository.getMatchById(Id);

            Joueur ancienVainqueur = match.getVainqueur();
            match.setVainqueur(match.getFinaliste());
            match.setFinaliste(ancienVainqueur);

            match.getScore().setSet1((byte)0);
            match.getScore().setSet2((byte)0);
            match.getScore().setSet3((byte)0);
            match.getScore().setSet4((byte)0);
            match.getScore().setSet5((byte)0);

            tx.commit();
            System.out.println("Score du match modifier en base ");
       }
        catch (Exception e) {
        if (tx != null) {
            tx.rollback();
        }
        e.printStackTrace();
    } finally {
        if (session != null) {
            session.close();
        }
      }

    }
    public  void creationMatch(MatchDto matchDto){
        Session session = null;
        Transaction tx = null;
        Match match=null;
        try {
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();

            match = new Match();
            match.setEpreuve(epreuveRepository.getIdEpreuveById(matchDto.getEpreuve().getId()));
            match.setVainqueur(joueurRepository.getByIdJoueur(matchDto.getVainqueur().getId()));
            match.setFinaliste(joueurRepository.getByIdJoueur(matchDto.getFinaliste().getId()));
            Score score = new Score();
            //il s'agit d'une relation bidirectionnelle;
            score.setMatch(match);
            match.setScore(score);

            score.setSet1(match.getScore().getSet1());
            score.setSet1(match.getScore().getSet2());
            score.setSet1(match.getScore().getSet3());
            score.setSet1(match.getScore().getSet4());
            score.setSet1(match.getScore().getSet5());

            matchRepository.creermatch(match);

            tx.commit();
            System.out.println("Creation du match en base en cascade ");
        }
        catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
