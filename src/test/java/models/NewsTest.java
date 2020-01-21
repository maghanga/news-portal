package models;

//import dao.DatabaseRule;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("ConstantConditions")
public class NewsTest {
//    @Rule
//    public DatabaseRule databaseRule = new DatabaseRule();

    private News simpleNews(){
        return new News("New debt","Uhuru Kenyatta secures 170 Billion investment");
    }
    private News altNews(){
        return new News("Attempted murder","Babu Owino cold heartedly shoots a DJ at BClub on the neck");
    }

    @Test
    public void initializeCorrectly(){
        News news = altNews();
        assertTrue(news instanceof News);
    }

    @Test
    public void getFunctionsWorkCorrectly(){
        News news = simpleNews();
        assertEquals(news.getTitle(),simpleNews().getTitle());
        assertEquals(news.getDescription(),simpleNews().getDescription());
        assertEquals(news.getType(),simpleNews().getType());
    }

    @Test
    public void getDifferentNewsType(){
        News news = simpleNews();
        News news2 = altNews();
        news2.setType("Crime");
        news2.setAuthor("Lil Holmes");
        assertEquals(news.getType(),"General");
        assertEquals(news2.getType(),"Crime");
        assertEquals(news2.getAuthor(),"Lil Holmes");
    }

}