package weather.co;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;
import weather.co.app.ApplicationComponent;
import weather.co.app.WeatherApp;
import weather.repository.model.User;
import weather.repository.network.RestApi;

public class MainActivity extends AppCompatActivity {

    private ApplicationComponent component;

    @Inject
    WeatherApp weatherApp;

    @Inject
    RestApi apiService;

    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getComponent().inject(this);
        Timber.e("Start Action ");
        weatherApp.print();
        rest();
        restRx();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    private void restRx() {
        disposable.add(apiService.getUsers("reddit")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<User>() {
                    @Override
                    public void onNext(User user) {
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        String jsonOutput = gson.toJson(user);
                        Timber.e("Print pretty Rx :\n" + jsonOutput);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Timber.d(throwable);
                        handleError(throwable);
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }

    private void handleError(Throwable throwable) {
        Timber.e("Error");
    }


    private void rest() {
        apiService.getUser("reddit").enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonOutput = gson.toJson(response.body().getData());
                Timber.e("Print pretty :\n" + response.body().toString());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Timber.e("Call failed with exception: " + t.getMessage());
            }


        });
    }

    private MainActivityComponent getComponent() {
        return DaggerMainActivityComponent.builder().applicationComponent(((WeatherApp) getApplication()).getComponent()).build();
    }
}
