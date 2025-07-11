package com.example.movieManagement.controllers;

import com.example.movieManagement.model.Movie;
import com.example.movieManagement.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    private static final List<Movie> movies = new ArrayList<>();

    @Autowired
    private GeminiService geminiService;

    @GetMapping("/")
    public String renderAddMovieReviewForm() {
        return """
            <html>
            <body>
            <form action='/movies/add' method='POST'>
              <p>Enter the movie name and rating:</p>
              <input type='text' name='moviename' placeholder='Movie Name' required />
              <input type='text' name='rating' placeholder='Movie Rating' required />
              <button type='submit'>Submit</button>
            </form>
            <br />
            <a href='/movies'> View All added Movies</a>
            </body>
            </html>
        """;
    }

    @PostMapping("/movies/add")
    public String processAddMovieReviewForm(
            @RequestParam(value = "moviename") String movie,
            @RequestParam(value = "rating") String rating) {

        String description = geminiService.generateMovieDescription(movie);
        String review = geminiService.generateMovieDescription(movie);
        movies.add(new Movie(movie, rating, description, review));

        return "<html><body>" +
                "<h3>Movie Added</h3>" +
                "<p><strong>" + movie + "</strong> (" + rating + ")</p>" +
                "<p>" + description + "</p>" +
                "<p>" + review + "</p>" +
                "<a href='/'>Add Another</a> | <a href='/movies'>View All</a>" +
                "</body></html>";
    }

    @GetMapping("/movies")
    public String movieList() {
        StringBuilder html = new StringBuilder("<html><body><h2>All Movies</h2><ul>");
        for (Movie m : movies) {
            html.append("<li><strong>")
                    .append(m.getTitle()).append("</strong> (")
                    .append(m.getRating()).append("):<br>")
                    .append(m.getDescription()).append("</li><br>")
                    .append(m.getReview()).append("</li><br>");
        }
        html.append("</ul><a href='/'>Back to Form</a></body></html>");
        return html.toString();
    }
}
