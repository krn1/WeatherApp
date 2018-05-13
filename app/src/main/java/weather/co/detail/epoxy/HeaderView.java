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
class HeaderView extends FrameLayout {

    @BindView(R.id.weather_icon)
    SimpleDraweeView weatherImg;

    @BindView(R.id.temparature)
    TextView tempTextView;

    private WeatherInfo weatherInfo;

    public HeaderView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.view_header_list, this);
        ButterKnife.bind(this);
    }

    @ModelProp
    public void setHeader(@NonNull WeatherInfo weather) {
        this.weatherInfo = weather;

        weatherImg.setImageURI(weatherInfo.imageUrl);
        tempTextView.setText(weatherInfo.temp);
    }
}
