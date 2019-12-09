package com.spring.elk.demo.service;

import com.spring.elk.demo.DemoApplication;
import com.spring.elk.demo.model.Movie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Before
    public void before() {
        esTemplate.deleteIndex(Movie.class);
        esTemplate.createIndex(Movie.class);
        esTemplate.putMapping(Movie.class);
        esTemplate.refresh(Movie.class);
    }

    @Test
    public void testSave() {
        Movie movie = new Movie("100", "Jurassic Park", "Steven Spielberg",
                "Universal Studios", "13 Jun 1993");
        Movie savedMovie = movieService.save(movie);

        Assert.assertNotNull(savedMovie);
        Assert.assertEquals(savedMovie.getId(), movie.getId());
        Assert.assertEquals(savedMovie.getDirector(), movie.getDirector());
        Assert.assertEquals(savedMovie.getStudio(), movie.getStudio());
    }

    @Test
    public void testFindOne() {
        Movie movie = new Movie("100", "Jurassic Park", "Steven Spielberg",
                "Universal Studios", "13 Jun 1993");
        movieService.save(movie);
        Optional<Movie> foundMovie = movieService.findOne(movie.getId());
        Assert.assertEquals(foundMovie.get().getId(), movie.getId());
        Assert.assertEquals(foundMovie.get().getTitle(), movie.getTitle());
        Assert.assertEquals(foundMovie.get().getDirector(), movie.getDirector());
        Assert.assertEquals(foundMovie.get().getStudio(), movie.getStudio());
        Assert.assertEquals(foundMovie.get().getReleaseDate(), movie.getReleaseDate());
    }

    @Test
    public void testFindByTitle() {
        Movie movie = new Movie("100", "Jurassic Park", "Steven Spielberg",
                "Universal Studios", "13-Jun 1993");
        Movie movie2 = new Movie("101", "Jurassic Park - Lost Word", "Steven Spielberg",
                "Universal Studios", "13-Jun 1997");
        movieService.save(movie);
        movieService.save(movie2);
        List<Movie> foundMovies = movieService.findByTitle("Jurassic Park");
        Assert.assertEquals(foundMovies.size(), 2);
    }

    @Test
    public void testFindByStudio() {
        Movie movie = new Movie("100", "Jurassic Park", "Steven Spielberg",
                "Universal Studios", "13-Jun 1993");
        Movie movie2 = new Movie("101", "Jurassic Park - Lost Word", "Steven Spielberg",
                "Universal Studios", "13-Jun 1997");
        movieService.save(movie);
        movieService.save(movie2);
        Page<Movie> foundMovies = movieService.findByStudio("Universal Studios",
                PageRequest.of(0,10));
        Assert.assertEquals(foundMovies.getTotalElements(), 2);
    }

    @Test
    public void testFindByDirector() {
        Movie movie = new Movie("100", "Jurassic Park", "Steven Spielberg",
                "Universal Studios", "13-Jun 1993");
        Movie movie2 = new Movie("101", "Jurassic Park - Lost Word", "Steven Spielberg",
                "Universal Studios", "13-Jun 1997");
        movieService.save(movie);
        movieService.save(movie2);
        Page<Movie> foundMovies = movieService.findByDirector("Spielberg",
                PageRequest.of(0,10));
        Assert.assertEquals(foundMovies.getTotalElements(), 2);
    }

}
