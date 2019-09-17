package com.paly.zv.latty.ec.main.index;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.paly.zv.latty.ec.R;
import com.paly.zv.latty.ui.recyler.RgbValue;

public class TransluncentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    private int distenceY = 0;

    private static final int SHOW_SPEED = 3;

    private final RgbValue RGB_VALUE = RgbValue.create(255, 255, 255);


    public TransluncentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TransluncentBehavior() {

    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull Toolbar child, @NonNull View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        distenceY += dy;
        final int targetHeight = child.getBottom();

        if (distenceY > 0 && distenceY <= targetHeight) {
            final float scale = (float) distenceY / targetHeight;
            final float alpha = scale * 255;
            child.setBackgroundColor(Color.argb((int) alpha, RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        } else if (distenceY > targetHeight) {
            child.setBackgroundColor(Color.rgb(RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        }else if (distenceY == 0){
            child.setBackgroundColor(Color.argb(0,RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        }

    }
}
