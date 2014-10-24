package com.nacion.android.nacioncostarica.views.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.nacion.android.nacioncostarica.NacionCostaRicaActivity;
import com.nacion.android.nacioncostarica.R;

/**
 * Created by Gustavo Matarrita on 14/10/2014.
 */
public class SplashScreenActivity  extends Activity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, NacionCostaRicaActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
