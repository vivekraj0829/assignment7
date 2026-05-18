package day7assignment;
import java.time.LocalDate;

public class UserWatching {
    String userId;
    String movieId;
    int watchedMinutes;
    double completionPercentage;
    double userRating;
    LocalDate watchedDate;

    public UserWatching(String userId, String movieId, int watchedMinutes,
                        double completionPercentage, double userRating,
                        LocalDate watchedDate) {
        this.userId = userId;
        this.movieId = movieId;
        this.watchedMinutes = watchedMinutes;
        this.completionPercentage = completionPercentage;
        this.userRating = userRating;
        this.watchedDate = watchedDate;
    }
}