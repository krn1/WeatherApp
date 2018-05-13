package weather.co.detail.epoxy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;

import com.airbnb.epoxy.ModelView;

import weather.co.R;


@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class EmptyListView extends FrameLayout {

    public EmptyListView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.view_empty_list, this);
    }
}