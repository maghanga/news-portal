package dao;

import models.News;

import java.util.List;

public interface NewsDao {

    //create
    void add(News news);
    void addNewsToDepartment(int departmentId, int newsId, int userId);

    //read
    News findById(int id);
    List<News> allNews();
    List<News> allGeneralNews();
    List<News> allDepartmentNews();

    //update

    //delete
    void deleteById(int id);
    void deleteAll();
}