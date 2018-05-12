package weather.repository.network;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import weather.repository.model.User;
import weather.repository.model.WeatherData;

public interface RestApi {
    @GET("/user/{user}/about.json")
    Call<User> getUser(@Path("user") String user);

    @GET("/user/{user}/about.json")
    Flowable<User> getUsers(@Path("user") String user);

    @GET("weather?")
    Flowable<WeatherData> getCurrentWeather(@Query("q") String city, @Query("units") String units, @Query("APPID") String apiKey);
}
