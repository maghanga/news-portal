package dao;

import models.*;

import java.util.List;

public interface DepartmentDao {

    //create
    void add(Department department);
    void addUserToDept(Department department,User user);

    //read
    Department findById(int id);
    List<Department> allDepartments();
    List<User> allDepartmentEmployees(int departmentId);
    List<News> allDepartmentNews(int departmentId);

    //update
    void updateEmployeeCount(Department department);

    //delete
    void deleteDepartmentById(int id);
    void deleteEmployeeFromDept(Department department,User user);
    void deleteDeptNewsById(int departmentId, int newsId);
    void deleteAll();
}