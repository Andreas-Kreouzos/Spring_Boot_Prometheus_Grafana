package com.andrekreou.iot.movies.controller;

import com.andrekreou.iot.movies.model.Movies;
import com.andrekreou.iot.movies.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//The controller for the project, which handles HTTP requests
@RestController
public class MoviesController {

    //Dependency injection to connect with CryptoNewsService layer
    private final MoviesService moviesService;

    @Autowired
    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @GetMapping(path = "/movies")
    public List<List<Movies>> getData(){
        return moviesService.getData();
    }
}
