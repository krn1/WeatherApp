package weather.co.detail.epoxy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.airbnb.epoxy.ModelProp;
import com.airbnb.epoxy.ModelView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import weather.co.R;

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class WeatherListItem extends FrameLayout {
    @BindView(R.id.weather_icon)
    SimpleDraweeView weatherImg;

    @BindView(R.id.temparature)
    TextView tempTextView;

    @BindView(R.id.day)
    TextView dayTextView;

    private WeatherInfo weatherInfo;

    public WeatherListItem(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.view_content_list, this);
        ButterKnife.bind(this);
    }

    @ModelProp
    public void setContent(@NonNull WeatherInfo weather) {
        this.weatherInfo = weather;

        weatherImg.setImageURI(weatherInfo.imageUrl);
        tempTextView.setText(weatherInfo.temp);
        dayTextView.setText(weather.day);
    }
}
