package dao;

import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oUserDao implements UserDao {
    public Sql2oUserDao(){}

    @Override
    public void add(User user) {
        String sql = "INSERT INTO users (name,position,role,department) VALUES (:name,:position,:role,:department);";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql,true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        } catch (Sql2oException ex){
            System.out.println("User is not added: "+ex);
        }
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * from users WHERE id=:id;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public List<User> allUsers() {
        String sql = "SELECT * from users;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM users WHERE id = :id;";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM users;";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        }
    }

    @Override
    public List<News> myNews(int userId){
        List<News> newsList = new ArrayList<>();
        String jointSql = "SELECT newsId from departments_news WHERE userId = :userId;";
        try (Connection con = DB.sql2o.open()) {
            List<Integer> allIds = con.createQuery(jointSql)
                    .addParameter("userId",userId)
                    .executeAndFetch(Integer.class);

            String getSql = "SELECT * FROM news WHERE id = :id;";
            for(int id:allIds){
                newsList.add(
                        con.createQuery(getSql)
                                .addParameter("id",id)
                                .executeAndFetchFirst(News.class)
                );
            }
        }

        return newsList;
    }

}