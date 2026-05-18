package day7assignment;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

public class NetflixService {

    List<Movie> movies;
    List<UserWatching> watchList;

    public NetflixService(List<Movie> movies, List<UserWatching> watchList) {
        this.movies = movies;
        this.watchList = watchList;
    }
    public void getHighRatedMovies() {
        movies.stream()
                .filter(m -> m.rating > 8)
                .forEach(System.out::println);
    }
    public void getActionMovies() {
        movies.stream()
                .filter(m -> m.genre.equals("Action"))
                .forEach(System.out::println);
    }
    public void countMovies() {
        System.out.println("Total Movies: " + movies.size());
    }

    public void sortByRating() {
        movies.stream()
                .sorted((a, b) -> Double.compare(b.rating, a.rating))
                .forEach(System.out::println);
    }

    public void moviesAfter2020() {
        movies.stream()
                .filter(m -> m.releaseYear > 2020)
                .forEach(System.out::println);
    }

    public void avgRating() {
        double avg = movies.stream()
                .mapToDouble(m -> m.rating)
                .average().orElse(0);
        System.out.println("Average Rating: " + avg);
    }

    public void genreCount() {
        Map<String, Long> map = movies.stream()
                .collect(Collectors.groupingBy(m -> m.genre, Collectors.counting()));

        map.forEach((g, c) -> System.out.println(g + " : " + c));
    }

    public void top5Watched() {
        movies.stream()
                .sorted((a, b) -> Long.compare(b.views, a.views))
                .limit(5)
                .forEach(System.out::println);
    }

 
    public void completionRate() {
        double rate = watchList.stream()
                .filter(w -> w.completionPercentage >= 90)
                .count() * 100.0 / watchList.size();

        System.out.println("Completion Rate: " + rate + "%");
    }

 
    public void trendingMovies() {
        int year = LocalDate.now().getYear();

        movies.stream()
                .filter(m -> m.releaseYear >= year - 2)
                .filter(m -> m.rating > 7.5 && m.views > 500000)
                .forEach(System.out::println);
    }


    public void recommendMovies(String genre) {
        Set<String> watched = watchList.stream()
                .map(w -> w.movieId)
                .collect(Collectors.toSet());

        movies.stream()
                .filter(m -> m.genre.equals(genre))
                .filter(m -> !watched.contains(m.movieId))
                .sorted((a, b) -> Double.compare(b.rating, a.rating))
                .limit(5)
                .forEach(System.out::println);
    }
}