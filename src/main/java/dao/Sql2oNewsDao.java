package dao;

import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oNewsDao implements NewsDao {

    public Sql2oNewsDao(){}

    @Override
    public void add(News news) {
        String checkedType = crossCheckDepartments(news.getType());

        String sql = "INSERT INTO news (title,description,type,author) VALUES (:title,:description,:type,:author)";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql,true)
                    .addParameter("title",news.getTitle())
                    .addParameter("description",news.getDescription())
                    .addParameter("type",checkedType)
                    .addParameter("author",news.getAuthor())
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void addNewsToDepartment(int deptid, int newsId, int userId) {
        String sql = "INSERT INTO departments_news (deptId,newsId,user  Id) VALUES (:departmentId,:newsId,:userId)";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("departmentId",deptid)
                    .addParameter("newsId",newsId)
                    .addParameter("userId",userId)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public News findById(int id) {
        String sql = "SELECT * from news WHERE id=:id;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(News.class);
        }
    }

    @Override
    public List<News> allNews() {
        String sql = "SELECT * from news;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public List<News> allGeneralNews() {
        String sql = "SELECT * from news where type='General';";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public List<News> allDepartmentNews() {
        String sql = "SELECT * from news where type!='General';";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from news WHERE id=:id;";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println("Delete by Id error: "+ex);
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE from news;";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    private String crossCheckDepartments(String departmentName) {
        String sql = "SELECT name from departments;";
        try (Connection con = DB.sql2o.open()) {
            List<String> allNames = con.createQuery(sql)
                    .executeAndFetch(String.class);

            if(departmentName.equalsIgnoreCase("general")){
                departmentName = "General";
            }
            else {
                for(String name:allNames){
                    if(departmentName.equalsIgnoreCase(name)){
                        departmentName = name;
                        break;
                    }
                }
            }
        }
        return departmentName;
    }
}