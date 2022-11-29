package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public void addMovieToDB(Movie m){
        movieRepository.addMovieInDB(m);
    }
    public void addDirectorToDB(Director d){
        movieRepository.addDirectorInDB(d);
    }

    public void addMovieDirectorPair(String movieName, String directorName){
        movieRepository.addMovieDirectorPair(movieName,directorName);
    }

    public Movie getMovie(String movieName){
        return movieRepository.getMovie(movieName);
    }

    public Director getDirector(String directorName){
        return movieRepository.getDirector(directorName);
    }

    public List<String> getMovies(String directorName){
        return movieRepository.getListOfMovies(directorName);
    }

    public List<String> getAllMovies(){
        return movieRepository.getAllmoviesAdded();
    }

    public void deleteMoviesByDirector(String directorName){

//        List<String> moviesofDirector = getMovies(directorName);
        movieRepository.deleteDirectorAndMovies(directorName);
    }

    public void deleteAllFromDirector(){
        movieRepository.deleteAllfromDirector();
    }





    



}
