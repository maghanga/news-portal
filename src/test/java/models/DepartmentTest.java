package models;

//import dao.DatabaseRule;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("ConstantConditions")
public class DepartmentTest {
//    @Rule
//    public DatabaseRule databaseRule = new DatabaseRule();

    private Department newdepartment(){
        return new Department("Politics","We cover news and headlines on politics countrywide", 5);
    }

    @Test
    public void initializeCorrectly(){
        Department department = newdepartment();
        assertTrue(department instanceof Department);
    }

    @Test
    public void getMethodsWorkCorrectly(){
        Department department = newdepartment();
        assertEquals("Politics",department.getName());
        assertEquals("We cover news and headlines on politics countrywide",department.getDescription());
    }

}