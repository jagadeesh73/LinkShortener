import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LinkShortener {
    private Map<String, String> shortToLongUrlMap;
    private Map<String, String> longToShortUrlMap;
    private static final String BASE_URL = "http://short.url/";

    public LinkShortener() {
        shortToLongUrlMap = new HashMap<>();
        longToShortUrlMap = new HashMap<>();
    }

    // Generate a random code for the short URL
    private String generateRandomCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            code.append(characters.charAt(random.nextInt(characters.length())));
        }

        return code.toString();
    }

    // Shorten a long URL
    public String shortenUrl(String longUrl) {
        if (longToShortUrlMap.containsKey(longUrl)) {
            return BASE_URL + longToShortUrlMap.get(longUrl);
        }

        String shortCode = generateRandomCode();
        shortToLongUrlMap.put(shortCode, longUrl);
        longToShortUrlMap.put(longUrl, shortCode);

        return BASE_URL + shortCode;
    }

    // Expand a short URL
    public String expandUrl(String shortUrl) {
        String shortCode = shortUrl.replace(BASE_URL, "");

        if (shortToLongUrlMap.containsKey(shortCode)) {
            return shortToLongUrlMap.get(shortCode);
        } else {
            return "Short URL not found.";
        }
    }

    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();

        // Example: Shorten and expand a URL
        String longUrl = "https://www.example.com";
        String shortUrl = linkShortener.shortenUrl(longUrl);
        System.out.println("Shortened URL: " + shortUrl);

        String expandedUrl = linkShortener.expandUrl(shortUrl);
        System.out.println("Expanded URL: " + expandedUrl);
    }
}

// This is a basic example, and in a real-world scenario, you would need to
// consider things like database storage for better persistence and scalability.
// Additionally, error handling and edge cases should be taken into account for
// a production-ready solution.