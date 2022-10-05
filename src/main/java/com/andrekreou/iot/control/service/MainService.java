package com.andrekreou.iot.control.service;

import com.andrekreou.iot.crypto.model.CryptoNews;
import com.andrekreou.iot.crypto.repository.CryptoNewsRepo;
import com.andrekreou.iot.movies.model.Movies;
import com.andrekreou.iot.movies.repository.MoviesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

//The service layer class for business logic implementation
@Service
@Transactional
public class MainService {

    //Dependency injection to connect with Repository layer
    private final CryptoNewsRepo cryptoNewsRepo;
    private final MoviesRepo moviesRepo;

    @Autowired
    public MainService(CryptoNewsRepo cryptoNewsRepo,
                       MoviesRepo moviesRepo) {
        this.cryptoNewsRepo = cryptoNewsRepo;
        this.moviesRepo = moviesRepo;
    }

    //Method to display all contents of database
    public List<CryptoNews> showAllRates(){
        List<CryptoNews> rates = new ArrayList<>();
        rates.addAll(cryptoNewsRepo.findAll());
        return rates;
    }

    //Method to display all contents of database
    public List<Movies> showAllMovies(){
        List<Movies> movies = new ArrayList<>();
        movies.addAll(moviesRepo.findAll());
        return movies;
    }
}