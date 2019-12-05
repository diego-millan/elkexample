package com.spring.elk.demo.service;

import com.spring.elk.demo.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Movie save(Movie movie);

    void delete(Movie movie);

    Optional<Movie> findOne(String id);

    Iterable<Movie> findAll();

    Page<Movie> findByDirector(String director, PageRequest pageRequest);

    Page<Movie> findByStudio(String studio, PageRequest pageRequest);

    List<Movie> findByTitle(String title);

}
