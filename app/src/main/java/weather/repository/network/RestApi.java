package weather.repository.network;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import weather.co.detail.model.WeatherData;
import weather.co.detail.model.forecast.ForecastData;

public interface RestApi {

    @GET("weather?")
    Flowable<WeatherData> getCurrentWeather(@Query("q") String city, @Query("units") String units, @Query("APPID") String apiKey);

    @GET("forecast/daily?")
    Flowable<ForecastData> getForecastWeather(@Query("q") String city,
                                              @Query("units") String units,
                                              @Query("cnt") String days,
                                              @Query("APPID") String appId);
}
