package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.tournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TournoiService {

    //Repository Pour Tournoi
    private tournoiRepositoryImpl tournoiRepository;

    public TournoiService(){
        this.tournoiRepository=new tournoiRepositoryImpl();
    }

    public TournoiDto getTournoi(Long id) {
        Session session = null;
        Transaction tx = null;
        Tournoi tournoi=null;
        TournoiDto tournoiDto=null;
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            tournoi=tournoiRepository.getByIdTournoi(id);

            tournoiDto = new TournoiDto();
            tournoiDto.setNom(tournoi.getNom());
            tournoiDto.setCode(tournoi.getCode());
            tournoiDto.setId(tournoi.getId());

            tx.commit();
            System.out.println("Tournoi Lu en base de données ");
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
        return tournoiDto;

    }

    public void creationTournoi (TournoiDto tournoiDto){
        Session session = null;
        Transaction tx = null;
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();

            Tournoi tournoi=new Tournoi();

            tournoi.setNom(tournoiDto.getNom());
            tournoi.setCode(tournoiDto.getCode());
            tournoi.setId(tournoiDto.getId());

            tournoiRepository.createTournoi(tournoi);
            tx.commit();
            System.out.println("Tournoi  dont  l'id est : "  + tournoi.getId() + " a été Créer en base de données" );
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

    public void supprimerTournoiEnBase(Long Id){
        Session session =null;
        Transaction tx = null;
        try {
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            tournoiRepository.deleteTournoi(Id);
            tx.commit();

            System.out.println("Tournoi supprimer  dont  l'id est :" + Id + " a été supprimer en base de données" );
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

    }
}
