package org.xdevs23.ui.widget;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import org.xdevs23.R;

public class ProgressBarView extends RelativeLayout {

    private boolean hideOnFinish = false;

    private int lastProgress = 0;

    private RelativeLayout innerProgressBar;

    public ProgressBarView(Context context) {
        super(context);
        init(context);
    }

    public ProgressBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProgressBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        innerProgressBar = new RelativeLayout(context);

        this.setBackgroundColor(ContextCompat.getColor(context, R.color.blue_600_t));

        LayoutParams p = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        p.setMargins(0, 0, 0, 0);

        innerProgressBar.setLayoutParams(p);

        innerProgressBar.setBackgroundColor(ContextCompat.getColor(context, R.color.blue_600));

        this.addView(innerProgressBar);

        if(this.getChildCount() < 1)this.setVisibility(INVISIBLE);
    }

    public void setProgress(int progress) {

        if (this.getVisibility() == INVISIBLE) {
            makeVisible();
        }

        if (hideOnFinish && progress == 100) { endProgress(); return; }

        if(progress != lastProgress) {
            innerProgressBar.animate()
                    .setDuration(100)
                    .translationX(-(this.getWidth() - (this.getWidth() * progress / 100)));
            innerProgressBar.bringToFront();
            this.bringToFront();
            lastProgress = progress;
        }
    }

    public void setProgress(float progress) {
        setProgress((int)progress*100);
    }

    public void endProgress() {
        innerProgressBar.animate()
                .setDuration(480)
                .translationX(0);
        makeInvisible();
    }

    public void makeVisible() {
        this.setVisibility(VISIBLE);
        this.animate()
                .setDuration(120)
                .scaleY(1);
    }

    public void makeInvisible() {
        final ProgressBarView fView = this;
        this.animate()
                .setDuration(180)
                .scaleY(0)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        // Not needed
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        fView.setVisibility(INVISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        // Not needed
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        // Not needed
                    }
                });

    }

    public void setOnCompletedAutoProgressFinish(boolean enable) {
        hideOnFinish = enable;
    }

}
