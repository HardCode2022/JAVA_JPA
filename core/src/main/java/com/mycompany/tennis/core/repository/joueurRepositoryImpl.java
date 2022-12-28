package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//implementation de joueur pour la realisation d'une requete CRUD(Create,Read,Update,Delete) en base de données
public class joueurRepositoryImpl {

    public List<Joueur> listJoueurs(char sexe){
        Session session=null;
        session=HibernateUtil.getSessionFactory().getCurrentSession();
       // Query<Joueur> query = session.createQuery("select j from Joueur j where j.sexe=?0",Joueur.class);
        //Utlisation d'une name query qui marche aussi (Name query definie dans l'entité Joueur)
        Query<Joueur> query = session.createNamedQuery("retour_sexe",Joueur.class);
        query.setParameter(0,sexe);
        List<Joueur> joueurs=query.getResultList();
        return joueurs;
    }

    //Mise en place d'Hibernate
    public void createJouer(Joueur joueur){
             Session session = null;
             // Transaction tx = null;
           session=HibernateUtil.getSessionFactory().getCurrentSession();
               // tx=session.beginTransaction();
               //requete permettant de créer un nouveau joueur en base de données avec Hibernate
           session.persist(joueur);
                //permet de valider(commit automatique)
                // tx.commit();
                //session.flush();
    }
    public void deleteJoueur(Long id){
        Joueur joueur=getByIdJoueur(id);
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(joueur);
    }
    //Utilisation d'Hibernate pour la modification du nom d'un joueur en base
    //Commenter suite à une refactorisation de code pour utiliser une seule methode
    // au lieu d'en creer plusieurs
                /*
                public void modifierNomJoueur(Long id , String nouveauNom){

                    Session session=null;
                    Joueur joueur = null;
                    Transaction tx=null;
                    try {
                        //Utilisation de la connexion Hibernate grace à la session factory
                        session= HibernateUtil.getSessionFactory().openSession();
                        tx=session.beginTransaction();
                        //Permet de recuperer le joueur dont l'identifiant est passé en parametre
                        joueur=session.get(Joueur.class,id);
                        joueur.setNom(nouveauNom);
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
                }*/

    //Recuperation du Joueur par ID en base de données Avec Hibernate
    public Joueur getByIdJoueur(Long id){
        Joueur joueur = null;
        Session session = null;
                    //Utilisation de la connexion Hibernate grace à la session factory
                    //Première manière de proceder avec une seule session
                         // session= HibernateUtil.getSessionFactory().openSession();
                    //deuxième methode de proceder en utilisant la session courante s
            session=HibernateUtil.getSessionFactory().getCurrentSession();
                   //Permet de recuperer le joueur dont l'identifiant est passé en parametre
           joueur=session.get(Joueur.class,id);

        return joueur;
    }


    //Avec JDBC
    public void updateJoueur(Joueur joueur){
        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn=dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?,PRENOM=?,SEXE=? WHERE ID=?");

            preparedStatement.setString(1,joueur.getNom());
            preparedStatement.setString(2,joueur.getPrenom());
            preparedStatement.setString(3,joueur.getSexe().toString());
            preparedStatement.setLong(4,joueur.getId());

            preparedStatement.executeUpdate();

            System.out.println("Modification du joueur en base de données");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn!=null) conn.rollback();
            } catch (SQLException el) {
                el.printStackTrace();
            }
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
