package com.andrekreou.iot.movies.controller;

import com.andrekreou.iot.movies.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MoviesHTMLController {

    MoviesService moviesService;

    @Autowired
    public MoviesHTMLController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    //Method to handle the HTTP request for showing DB contents
    @GetMapping("/show-moviescontents")
    public String showAllMovies(HttpServletRequest request){
        request.setAttribute("movies", moviesService.showAllMovies());
        return "moviesdbcontents";
    }
}
