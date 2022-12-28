package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Score;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;


public class epreuveRepositoryImpl {

  public Epreuve getIdEpreuveById(Long Id){

         Epreuve epreuve=null;
         Session session=null;
         session= HibernateUtil.getSessionFactory().getCurrentSession();
         epreuve = session.get(Epreuve.class,Id);

        return epreuve;
  }

    public List<Epreuve> listEpreuves(String codeTournoi){
        Session session=null;
        session=HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Epreuve> query = session.createQuery("select e from Epreuve e where e.tournoi.code=?0",Epreuve.class);
        query.setParameter(0,codeTournoi);
        List<Epreuve> epreuves=query.getResultList();
        return epreuves;
    }
}
