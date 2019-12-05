package com.spring.elk.demo.service.impl;

import com.spring.elk.demo.model.Movie;
import com.spring.elk.demo.repository.MovieRepository;
import com.spring.elk.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }

    @Override
    public Optional<Movie> findOne(String id) {
        return movieRepository.findById(id);
    }

    @Override
    public Iterable<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Page<Movie> findByDirector(String director, PageRequest pageRequest) {
        return movieRepository.findByDirector(director, pageRequest);
    }

    @Override
    public Page<Movie> findByStudio(String studio, PageRequest pageRequest) {
        return movieRepository.findByStudio(studio, pageRequest);
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }
}
