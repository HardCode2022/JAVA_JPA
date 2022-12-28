package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class tournoiRepositoryImpl {

    public void createTournoi(Tournoi tournoi){
        Session session = null;
        session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(tournoi);
    }

    //Utilisation d'hibernate pour la recuperation d'un tournoi en base
    public Tournoi getByIdTournoi(Long id){
            Session session = null;
            Tournoi tournoi = null;
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tournoi=session.get(Tournoi.class,id);

       return tournoi;
    }

    public void deleteTournoi(Long id) {
        Session session = null;
        Tournoi tournoi = getByIdTournoi(id);
        session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(tournoi);
    }













    //exemple avec JDBC
    public void updateTournoi(Tournoi tournoi){
        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn=dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE TOURNOI SET NOM=?,CODE=? WHERE ID=?");

            preparedStatement.setString(1,tournoi.getNom());
            preparedStatement.setString(2,tournoi.getCode());
            preparedStatement.setLong(3,tournoi.getId());

            preparedStatement.executeUpdate();

            System.out.println("Modification du tournoi en base de données");

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
