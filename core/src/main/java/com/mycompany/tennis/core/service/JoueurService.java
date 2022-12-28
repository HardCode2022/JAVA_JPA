package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.joueurRepositoryImpl;
import com.mycompany.tennis.core.repository.tournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class JoueurService {
    //Repository pour Joueur
    private joueurRepositoryImpl joueurRepository ;

    public JoueurService(){

        this.joueurRepository=new joueurRepositoryImpl();
    }

    public Joueur lireJoueur(Long id){
        Joueur joueur=null;
        Session session =null;
        Transaction tx = null;

        try {
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            joueur=joueurRepository.getByIdJoueur(id);
            tx.commit();

            System.out.println("Joueur Lu en base " );
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

        return joueur;
    }
    public void createJoueur(Joueur joueur){
        Session session =null;
        Transaction tx = null;

        try {
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            joueurRepository.createJouer(joueur);
            tx.commit();

            System.out.println("Joueur créer en base " );
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

    //Pas recommander de gerer les problématique Hibernate dans une classe de service
    public  void modifierNomJoueurNouveau(Long id,String nouveauNom){
        Joueur joueur=lireJoueur(id);
        Session session=null;
        Transaction tx=null;
        try {
            //Utilisation de la connexion Hibernate grace à la session factory
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            //Permet de recuperer le joueur dont l'identifiant est passé en parametre
            //Joueur joueur=joueurRepository.getByIdJoueur(id);
            joueur.setNom(nouveauNom);
            //utiliser dans le cadre d'un objet detacher avec un cast de Joueur
            session.merge(joueur);
            tx.commit();

            System.out.println("Nom du joueur a modifier en base " );
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

    public  void modifierSexeJoueur(Long id,char sexe){
        Joueur joueur=lireJoueur(id);
        Session session=null;
        Transaction tx=null;
        try {
            //Utilisation de la connexion Hibernate grace à la session factory
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            //Permet de recuperer le joueur dont l'identifiant est passé en parametre
            //Joueur joueur=joueurRepository.getByIdJoueur(id);
            joueur.setSexe(sexe);
            //utiliser dans le cadre d'un objet detacher avec un cast de Joueur
            session.merge(joueur);
            tx.commit();

            System.out.println("Sexe du joueur modifier en base " );
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
    public  void supprimerUnJoueurEnBaseDonnees(Long id){
        Session session =null;
        Transaction tx = null;

        try {
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            joueurRepository.deleteJoueur(id);
            tx.commit();

            System.out.println("Joueur supprimer en base de données" );
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
    public List<JoueurDto> afficherListeJoueurs(char sexe){
        Session session=null;
        Transaction tx = null;
        List<JoueurDto> dtos=new ArrayList<>();

        try {
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();

            for (Joueur joueurs:joueurRepository.listJoueurs(sexe)) {
                JoueurDto joueurDto = new JoueurDto();
                joueurDto.setId(joueurs.getId());
                joueurDto.setNom(joueurs.getNom());
                joueurDto.setPrenom(joueurs.getPrenom());
                joueurDto.setSexe(joueurs.getSexe());
                dtos.add(joueurDto);
            }
            tx.commit();
            System.out.println("Joueurs lus en base " );
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
