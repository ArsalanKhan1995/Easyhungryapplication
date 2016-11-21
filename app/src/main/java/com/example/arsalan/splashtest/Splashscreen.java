package com.example.arsalan.splashtest;



        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Typeface;
        import android.support.annotation.Nullable;
        import android.support.v4.view.ViewCompat;
        import android.support.v4.view.ViewPropertyAnimatorCompat;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.os.Handler;
        import android.util.AttributeSet;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.DecelerateInterpolator;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import com.example.arsalan.splashtest.MainActivity;


public class Splashscreen extends AppCompatActivity {
    public static final int STARTUP_DELAY = 300;
    public static final int ANIM_ITEM_DURATION = 1000;
    public static final int ITEM_DELAY = 300;
    private boolean animationStarted = false;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme1);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if (!hasFocus || animationStarted) {
            return;
        }
        animate();
        super.onWindowFocusChanged(hasFocus);
    }


    private void animate() {
        ImageView logoImageView = (ImageView)findViewById(R.id.img_logo);
        ViewGroup container = (ViewGroup)findViewById(R.id.container);

        ViewCompat.animate(logoImageView)
                .translationY(-250)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_ITEM_DURATION).setInterpolator(
                new DecelerateInterpolator(1.2f)).start();

        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            ViewPropertyAnimatorCompat viewAnimator;

            if (!(v instanceof Button)) {
                viewAnimator = ViewCompat.animate(v)
                        .translationY(50).alpha(1)
                        .setStartDelay((ITEM_DELAY * i) + 500)
                        .setDuration(1000);

            } else {
                viewAnimator = ViewCompat.animate(v)
                        .scaleY(1).scaleX(1)
                        .setStartDelay((ITEM_DELAY * i) + 500)
                        .setDuration(500);
            }
            viewAnimator.setInterpolator(new DecelerateInterpolator()).start();
        }
            findViewById(R.id.btn_choice).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myintent = new Intent(Splashscreen.this,MainActivity.class);
                    myintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(myintent);
                }
            });
    }

}