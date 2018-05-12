package weather.repository.network;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import weather.repository.model.WeatherData;

public interface RestApi {

    @GET("weather?")
    Flowable<WeatherData> getCurrentWeather(@Query("q") String city, @Query("units") String units, @Query("APPID") String apiKey);

//    @GET("forecast?")
//    Flowable<ForecastWeatherData> getForecastWeather(@Query("q") String city,
//                                                       @Query("units") String units,
//                                                       @Query("cnt") String days,
//                                                       @Query("APPID") String appId);
}
