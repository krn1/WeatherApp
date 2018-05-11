package weather.repository.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import weather.repository.model.User;

public interface RestApi {
    @GET("/user/{user}/about.json")
    Call<User> getUser(@Path("user") String user);
}
