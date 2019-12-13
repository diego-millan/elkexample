package com.spring.elk.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName = "myindex", type = "movies")
public class Movie {

    @Id
    private String id;
    private String title;
    private String director;
    private String studio;
    private Date releaseDate;

    public Movie() {}

    public Movie(String id, String title, String director, String studio, Date releaseDate) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.studio = studio;
        this.releaseDate = releaseDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", studio='" + studio + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
