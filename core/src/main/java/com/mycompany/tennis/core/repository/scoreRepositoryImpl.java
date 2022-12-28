package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Score;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.*;

//implementation de joueur pour la realisation d'une requete CRUD(Create,Read,Update,Delete) en base de données
public class scoreRepositoryImpl {

    public void createScore(Score score){
        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn=dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO SCORE_VAINQUEUR (ID_MATCH,SET_1,SET_2,SET_3,SET_4,SET_5) VALUES(?,?,?,?,?,?)" , Statement.RETURN_GENERATED_KEYS);

            if (score.getMatch().getId()==null){
              preparedStatement.setNull(1,Types.TINYINT);
            }else {
              preparedStatement.setLong(1,score.getMatch().getId());
           }
            if (score.getSet1()==null){
                preparedStatement.setNull(2,Types.TINYINT);
            }else {
                preparedStatement.setByte(2,score.getSet1());
            }
            if (score.getSet2()==null){
                preparedStatement.setNull(3,Types.TINYINT);
            }else {
                preparedStatement.setByte(3,score.getSet2());
            }
            if (score.getSet3()==null){
                preparedStatement.setNull(4,Types.TINYINT);
            }else {
                preparedStatement.setByte(4,score.getSet3());
            }
            if (score.getSet4()==null){
                preparedStatement.setNull(5,Types.TINYINT);
            }else {
                preparedStatement.setByte(5,score.getSet4());
            }
            if (score.getSet5()==null){
                preparedStatement.setNull(6,Types.TINYINT);
            }else {
                preparedStatement.setByte(6,score.getSet4());
            }

            preparedStatement.executeUpdate();
            //Permet d'afficher la valeur de l'id par autoincrementation
            ResultSet rs =preparedStatement.getGeneratedKeys();
            if (rs.next()){
               score.setId(rs.getLong(1));
            }

            System.out.println("Score créé en base de données");

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
  public Score getIdScoreId(Long Id){

         Score score=null;
         Session session=null;

         session= HibernateUtil.getSessionFactory().getCurrentSession();
         score = session.get(Score.class,Id);

        return score;
  }
}
