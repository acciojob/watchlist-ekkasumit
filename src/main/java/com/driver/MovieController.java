package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody(required = true)Movie m){
        movieService.addMovieToDB(m);
        return new ResponseEntity("success",HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity<String > addDirector(@RequestBody(required = true)Director d){
        movieService.addDirectorToDB(d);
        return new ResponseEntity("Successfully added the Director",HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movieName,
                                                        @RequestParam("director") String directorName){
        try {
            movieService.addMovieDirectorPair(movieName, directorName);
        }
        catch (Exception e){
            return new ResponseEntity("Some error in method", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Successfully added Movie and Director",HttpStatus.CREATED);

    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName){
        return new ResponseEntity<>(movieService.getMovie(movieName),HttpStatus.ACCEPTED);

    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String directorName){
        return new ResponseEntity<>(movieService.getDirector(directorName),HttpStatus.ACCEPTED);

    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String directorName){
        return new ResponseEntity<>(movieService.getMovies(directorName),HttpStatus.ACCEPTED);
    }

    @GetMapping("movies/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        return new ResponseEntity<>(movieService.getAllMovies(),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("director") String directorName){
        movieService.deleteMoviesByDirector(directorName);
        return new ResponseEntity<>("Successfully Deleted the Director and all of his Movies",HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectorAndMovies(){
        movieService.deleteAllFromDirector();
        return new ResponseEntity<>("Successfully Deleted All the Director and all of their Movies",HttpStatus.ACCEPTED);
    }


}
