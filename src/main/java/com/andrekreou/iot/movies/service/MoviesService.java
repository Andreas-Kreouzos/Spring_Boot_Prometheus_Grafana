package com.andrekreou.iot.movies.service;

import com.andrekreou.iot.movies.model.Movies;
import com.andrekreou.iot.movies.repository.MoviesRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//The service layer class for business logic implementation
@org.springframework.stereotype.Service
@Transactional
public class MoviesService {

    //Dependency injection to connect with Repository layer
    private final MoviesRepo moviesRepo;

    @Autowired
    public MoviesService(MoviesRepo moviesRepo) {
        this.moviesRepo = moviesRepo;
    }

    public List<List<Movies>> getData() {
        return Collections.singletonList(moviesRepo.findAll());
    }

    //Method to display all contents of database
    public List<Movies> showAllMovies(){
        List<Movies> movies = new ArrayList<>();
        movies.addAll(moviesRepo.findAll());
        return movies;
    }
}