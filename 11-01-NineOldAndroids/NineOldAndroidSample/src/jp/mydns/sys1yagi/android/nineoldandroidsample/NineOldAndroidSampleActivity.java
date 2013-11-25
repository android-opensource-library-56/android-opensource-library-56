package jp.mydns.sys1yagi.android.nineoldandroidsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.TypeEvaluator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.nineoldandroids.view.ViewPropertyAnimator;

public class NineOldAndroidSampleActivity extends Activity {

    private NineOldAndroidSampleActivity This() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine_old_android_sample);

        findViewById(R.id.value_anim).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                valueAnimator((TextView) findViewById(R.id.value_anim_text));
            }
        });
        findViewById(R.id.view_anim).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPropertyAnimator(view);
            }
        });
    }

    private void valueAnimator(final TextView text) {
        ValueAnimator animator = ValueAnimator.ofInt(0, 200);

        animator.setDuration(3000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setRepeatCount(1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (Integer) animation.getAnimatedValue();
                text.setText("value " + value);
            }
        });
        animator.addListener(new AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Toast.makeText(This(), "start animation", Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Toast.makeText(This(), "repeat animation", Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(This(), "end animation", Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }
        });
        animator.start();
    }

    private void viewPropertyAnimator(final View view) {
        ViewPropertyAnimator animator = ViewPropertyAnimator.animate(view);
        animator.alpha(0).x(200).y(150).scaleX(2.0f).scaleY(2.0f)
                .setDuration(1000)
                .setInterpolator(new AnticipateInterpolator())
                .setListener(new AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ViewPropertyAnimator.animate(view).setListener(null)
                                .alpha(1).x(0).y(0).scaleX(1.0f).scaleY(1.0f);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }
                });
    }

    static class Value {
        int count;
        String text;
    }

    class ValueEvaluator implements TypeEvaluator<Value> {
        private Value mValue = new Value();

        @Override
        public Value evaluate(float fraction, Value startValue, Value endValue) {
            mValue.count = startValue.count + (int) (endValue.count * fraction);
            mValue.text = "value=" + mValue.count;
            return mValue;
        }
    }

}
