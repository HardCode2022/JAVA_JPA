package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//implementation de joueur pour la realisation d'une requete CRUD(Create,Read,Update,Delete) en base de données
public class matchRepositoryImpl {

    public Match getMatchById(Long id) {
        Session session=null;
        Match match =null;
        session= HibernateUtil.getSessionFactory().getCurrentSession();
        match = session.get(Match.class,id);

        return match;
    }
    public  void creermatch(Match match){
        Session session = null;
        session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(match);
    }

}
