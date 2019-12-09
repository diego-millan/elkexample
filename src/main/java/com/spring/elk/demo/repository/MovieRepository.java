package com.spring.elk.demo.repository;

import com.spring.elk.demo.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MovieRepository extends ElasticsearchRepository <Movie, String> {

    List<Movie> findByTitle(String title);
    Page<Movie> findByDirector(String director, Pageable pageable);
    Page<Movie> findByStudio(String studio, Pageable pageable);

}
