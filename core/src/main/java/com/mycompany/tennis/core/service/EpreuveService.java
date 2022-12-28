package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLiteDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.epreuveRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EpreuveService {

    private epreuveRepositoryImpl epreuveRepository;

    public EpreuveService(){

        this.epreuveRepository=new epreuveRepositoryImpl();
    }

    public EpreuveFullDto afficherDetailleEpreuveJoueur(Long id) {
        Epreuve epreuve = null;
        Session session = null;
        Transaction tx = null;
        EpreuveFullDto epreuveFullDto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getIdEpreuveById(id);
            //Utiliser dans le cadre le LAZY au niveau de l'entite epreuve
           // Hibernate.initialize(epreuve.getTournoi());


            epreuveFullDto=new EpreuveFullDto();
            epreuveFullDto.setId(epreuve.getId());
            epreuveFullDto.setAnnee(epreuve.getAnnee());
            epreuveFullDto.setTypeEpreuve(epreuve.getTypeEpreuve());

            TournoiDto tournoiDto =new TournoiDto();
            tournoiDto.setId(epreuve.getTournoi().getId());
            tournoiDto.setCode(epreuve.getTournoi().getCode());
            tournoiDto.setNom(epreuve.getTournoi().getNom());
            epreuveFullDto.setTournoi(tournoiDto);

            epreuveFullDto.setParticipants(new HashSet<>());

            for(Joueur joueur: epreuve.getParticipants()) {
                final JoueurDto joueurDto = new JoueurDto();
                joueurDto.setId(joueur.getId());
                joueurDto.setNom(joueur.getNom());
                joueurDto.setPrenom(joueur.getPrenom());
                joueurDto.setSexe(joueur.getSexe());

                epreuveFullDto.getParticipants().add(joueurDto);
            }

            tx.commit();
            System.out.println("Epreuve Lu en base de données ");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return epreuveFullDto;
    }
    public EpreuveLiteDto getEpreuveByIdSansTournoi(Long id) {
        Epreuve epreuve = null;
        Session session = null;
        Transaction tx = null;
        EpreuveLiteDto epreuveLiteDto=null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getIdEpreuveById(id);


            epreuveLiteDto=new EpreuveLiteDto();
            epreuveLiteDto.setId(epreuve.getId());
            epreuveLiteDto.setAnnee(epreuve.getAnnee());
            epreuveLiteDto.setTypeEpreuve(epreuve.getTypeEpreuve());

            tx.commit();
            System.out.println("Epreuve Lu en base de données ");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return epreuveLiteDto;
    }

    public List<EpreuveFullDto> afficherListeEpreuves(String codeTournoi){
        Session session=null;
        Transaction tx = null;
        List<EpreuveFullDto> dtos=new ArrayList<>();

        try {
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();

            for (Epreuve epreuve:epreuveRepository.listEpreuves(codeTournoi)) {
                EpreuveFullDto epreuveFullDto = new EpreuveFullDto();
                epreuveFullDto.setId(epreuve.getId());
                epreuveFullDto.setAnnee(epreuve.getAnnee());
                epreuveFullDto.setTypeEpreuve(epreuve.getTypeEpreuve());

                TournoiDto tournoiDto =new TournoiDto();
                tournoiDto.setId(epreuve.getTournoi().getId());
                tournoiDto.setCode(epreuve.getTournoi().getCode());
                tournoiDto.setNom(epreuve.getTournoi().getNom());
                epreuveFullDto.setTournoi(tournoiDto);
                dtos.add(epreuveFullDto);
            }
            tx.commit();

            System.out.println("Epreuves lus en base " );
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
        return dtos;
    }
}
