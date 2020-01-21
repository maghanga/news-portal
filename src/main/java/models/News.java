package models;

import java.util.Objects;

public class News {
    private String title;
    private String description;
    private String type;
    private String author;
    private int id;

    public News(String title, String description) {
        this.title = title;
        this.description = description;
        this.type = "General";
    }


}
