package utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by horaciogarza on 24/04/16.
 */
public class RobotoButton extends Button {
    public RobotoButton(Context context) {
        super(context);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf"));
    }

    public RobotoButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf"));
    }

    public RobotoButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf"));
    }

    public RobotoButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf"));
    }
}
