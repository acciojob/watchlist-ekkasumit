package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Component
public class MovieRepository {
    Map<String,Movie> movieMap = new HashMap<>();  //avenger
    Map<String,Director> directorMap = new HashMap<>();
    Map<Movie,Director> movieDirectorMap = new HashMap<>();  //

    public void addMovieInDB(Movie movie)                      //Add movie in DB
    {
        movieMap.put(movie.getName(),movie);
    }

    public void addDirectorInDB(Director director)             //Add Director in DB
    {
        directorMap.put(director.getName(),director);
    }

    public void addMovieDirectorPair(String movieName, String directorName){            //Add Movie and Director Pair in DB

        movieDirectorMap.put(movieMap.get(movieName),directorMap.get(directorName));
    }

    public Movie getMovie(String movieName){                  //Get Movie details from Movie Name
        return movieMap.get(movieName);
    }

    public Director getDirector(String directorName){         //Get Director Details from Director Name

        return directorMap.get(directorName);
    }

    public List<String > getListOfMovies(String directorName){   //List of All Movies By Direcor Name
        List<String> listOfMovies = new ArrayList<>();
        for(Movie m: movieDirectorMap.keySet()){
            if(movieDirectorMap.get(m).getName() == directorName)
                listOfMovies.add(m.getName());
        }
        return listOfMovies;
    }

    public List<String > getAllmoviesAdded(){                    //List of All the Movies Added
        List<String> listOfAllMovies = new ArrayList<>();
        for(Movie m:movieMap.values()){
            listOfAllMovies.add(m.getName());
        }
        return listOfAllMovies;
    }

    public void deleteDirectorAndMovies(String directorName){    //Delete Director and All of his Movies from DB

        for(String s: directorMap.keySet()){
            if(s == directorName)
                directorMap.remove(s);
        }

        for(Movie m: movieDirectorMap.keySet()){
            String movNam = m.getName();

            if(movieMap.containsKey(movNam)){
                movieMap.remove(movNam);
            }
            if(movieDirectorMap.get(m).getName() == directorName) {
                movieDirectorMap.remove(m);
            }
        }

    }


    public void deleteAllfromDirector(){                    //Delete All the Directors and their Movies from DB

        for(Movie m: movieDirectorMap.keySet()){
            movieMap.remove(m.getName());
        }

        directorMap.clear();
        movieDirectorMap.clear();

    }

}
