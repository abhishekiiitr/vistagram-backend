package com.vista.utils;

import com.vista.entity.PostEntity;
import com.vista.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class SeedData implements CommandLineRunner {

    private final PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        if (postRepository.count() == 0) {
            seedSamplePosts();
        }
    }

    private void seedSamplePosts() {
        List<String> usernames = Arrays.asList(
                "traveler_sarah", "food_explorer", "nature_lover", "city_walker",
                "photo_hunter", "adventure_seeker", "coffee_nomad", "art_enthusiast",
                "beach_wanderer", "mountain_climber"
        );

        List<String> captions = Arrays.asList(
                "Amazing sunset at the Eiffel Tower! The golden hour never disappoints. 🌅 #ParisVibes #TravelDiary",
                "Fresh croissants from this hidden bakery in Montmartre. Best breakfast spot ever! 🥐 #LocalFinds #FoodieLife",
                "Hiking through the Alps - breathtaking views at every turn. Nature is incredible! 🏔️ #MountainLife #Adventure",
                "Street art discovery in Berlin. This city's creativity knows no bounds. 🎨 #StreetArt #UrbanExploration",
                "Perfect beach day in Santorini. Crystal clear waters and endless blue skies. 🌊 #GreekIslands #BeachLife",
                "Morning coffee with a view of the Tokyo skyline. What a way to start the day! ☕ #TokyoMornings #CoffeeTime",
                "Ancient ruins in Rome telling stories of centuries past. History comes alive here. 🏛️ #RomeWalks #HistoryBuff",
                "Vibrant spices at the Marrakech market. Colors and aromas everywhere! 🌶️ #Morocco #MarketLife",
                "Northern Lights dancing across the Norwegian sky. Pure magic! ✨ #NorthernLights #Norway",
                "Cozy bookshop cafe in Prague. Perfect reading spot with incredible atmosphere. 📚 #BookLovers #PragueLife"
        );

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            LocalDateTime createdAt = LocalDateTime.now()
                    .minusDays(random.nextInt(30))
                    .minusHours(random.nextInt(24))
                    .minusMinutes(random.nextInt(60));

            int likeCount = random.nextInt(50) + 1;

            PostEntity post = PostEntity.builder()
                    .username(usernames.get(i))
                    .caption(captions.get(i))
                    .imageBase64("https://picsum.photos/400/400?random=" + (i + 100)) // Using placeholder for demo
                    .createdAt(createdAt)
                    .likes(likeCount)
                    .shares(random.nextInt(20) + 1)
                    .likedByUsers(new HashSet<>()) // Empty set initially
                    .build();

            postRepository.save(post);
        }

        System.out.println("✅ Seeded " + postRepository.count() + " posts successfully!");
    }
}