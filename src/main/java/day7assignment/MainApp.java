package day7assignment;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MainApp {

    public static void main(String[] args) {

        List<Movie> movies = Arrays.asList(

                new Movie("M1", "RRR", "Action", 2022, 180, 8.5, 6000000),
                new Movie("M2", "Baahubali 2", "Action", 2017, 170, 9.0, 8000000),
                new Movie("M3", "Pushpa", "Action", 2021, 150, 7.8, 5500000),
                new Movie("M4", "Jersey", "Drama", 2019, 140, 8.6, 1200000),
                new Movie("M5", "Mahanati", "Drama", 2018, 150, 8.4, 900000),
                new Movie("M6", "F2", "Comedy", 2019, 148, 7.2, 700000),
                new Movie("M7", "Jathi Ratnalu", "Comedy", 2021, 120, 7.5, 850000),

                new Movie("M8", "Agent", "Thriller", 2023, 130, 5.2, 2000000),
                new Movie("M9", "Spy", "Thriller", 2023, 135, 6.0, 400000)
        );

        List<UserWatching> watchList = Arrays.asList(
                new UserWatching("U1", "M1", 180, 100, 9, LocalDate.now()),
                new UserWatching("U2", "M2", 170, 95, 9, LocalDate.now()),
                new UserWatching("U3", "M8", 120, 90, 6, LocalDate.now()),
                new UserWatching("U4", "M8", 50, 40, 5, LocalDate.now()),
                new UserWatching("U5", "M4", 140, 100, 8, LocalDate.now())
        );

        System.out.println(" NETFLIX DASHBOARD \n");

     
        System.out.println("1. HIGH-RATED MOVIES (Rating > 8.0)");
        movies.stream()
                .filter(m -> m.rating > 8)
                .forEach(m -> System.out.println("   - " + m.title + " (" + m.genre + ", " + m.rating + ", " + m.releaseYear + ")"));

        System.out.println("\n2. GENRE DISTRIBUTION");
        Map<String, Long> genreCount = movies.stream()
                .collect(Collectors.groupingBy(m -> m.genre, Collectors.counting()));

        genreCount.forEach((g, c) ->
                System.out.println("   " + g + ": " + c + " movies"));


        System.out.println("\n3. TOP 5 TRENDING MOVIES");

        List<Movie> trending = movies.stream()
                .filter(m -> m.rating > 7)     // ✅ relaxed condition
                .sorted((a, b) -> Long.compare(b.views, a.views))
                .limit(5)
                .collect(Collectors.toList());

        if (trending.isEmpty()) {
            System.out.println("   No trending movies found");
        } else {
            for (int i = 0; i < trending.size(); i++) {
                Movie m = trending.get(i);
                System.out.println("   " + (i + 1) + ". " + m.title + " (" +
                        m.genre + ", " + m.rating + ", " + m.releaseYear +
                        ", " + (m.views / 1_000_000.0) + "M views)");
            }
        }
        System.out.println("\n4. GENRE-WISE PERFORMANCE");

        Map<String, List<Movie>> grouped = movies.stream()
                .collect(Collectors.groupingBy(m -> m.genre));

        for (String g : grouped.keySet()) {

            List<Movie> list = grouped.get(g);

            int total = list.size();
            double avg = list.stream().mapToDouble(m -> m.rating).average().orElse(0);
            long viewSum = list.stream().mapToLong(m -> m.views).sum();
            Movie top = list.stream().max(Comparator.comparingDouble(m -> m.rating)).get();

            System.out.println("\n   Genre: " + g);
            System.out.println("   - Total Movies: " + total);
            System.out.println("   - Average Rating: " + avg);
            System.out.println("   - Total Views: " + (viewSum / 1_000_000.0) + "M");
            System.out.println("   - Top Movie: " + top.title + " (" + top.rating + ")");
        }

        System.out.println("\n5. COMPLETION RATE ANALYSIS");

        long high = watchList.stream().filter(w -> w.completionPercentage > 80).count();
        long low = watchList.stream().filter(w -> w.completionPercentage < 50).count();

        double avgCompletion = watchList.stream()
                .mapToDouble(w -> w.completionPercentage)
                .average().orElse(0);

        System.out.println("   Movies with >80% completion rate: " + high);
        System.out.println("   Movies with <50% completion rate: " + low);
        System.out.println("   Average completion rate: " + avgCompletion + "%");
        movies.stream()
                .filter(m -> m.views > 1_000_000 && m.rating < 6)
                .forEach(m -> System.out.println(
                        "   - " + m.title + " (" +
                                (m.views / 1_000_000.0) + "M views, " +
                                m.rating + " rating)"
                ));
    }
}