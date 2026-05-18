package day7assignment;
public class Movie {
    String movieId;
    String title;
    String genre;
    int releaseYear;
    int duration;
    double rating;
    long views;

    public Movie(String movieId, String title, String genre, int releaseYear,
                 int duration, double rating, long views) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.rating = rating;
        this.views = views;
    }

    public String toString() {
        return title + " (" + genre + ", " + rating + ", " + releaseYear + ")";
    }
}