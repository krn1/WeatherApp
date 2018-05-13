package weather.repository.network;

public class NetworkUtils {
    static String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static String API_KEY = "a8ea818749aab75c2eaca231e9071e8a";
    private static String BASE_IMG_URL = "http://openweathermap.org/img/w/";

    public static String getImageUrl(String imgUri) {
        return BASE_IMG_URL + imgUri +
                ".png";
    }
}
