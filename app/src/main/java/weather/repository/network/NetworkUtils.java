package weather.repository.network;

public class NetworkUtils {
    static String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static String API_KEY = "adb4503a31093fed77c0a5f39d4c512b";
    private static String BASE_IMG_URL = "http://openweathermap.org/img/w/";

    public static String getImageUrl(String imgUri) {
        return BASE_IMG_URL + imgUri +
                ".png";
    }
}
