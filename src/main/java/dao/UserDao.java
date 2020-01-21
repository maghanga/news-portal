package dao;

import models.News;
import models.User;

import java.util.List;

public interface UserDao {

    //create
    void add(User user);

    //read
    User findById(int id);
    List<User> allUsers();
    List<News> myNews(int userId);

    //delete
    void deleteById(int id);
    void deleteAll();

}