package com.example.librarysample;

import org.junit.Test;

import static org.junit.Assert.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MyActivity extends Activity {
    private InterstitialAd interstitialAd;
    private Button nextLevelButton;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a full screen content callback.
        FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
                interstitialAd = null;
                // Proceed to the next level.
                goToNextLevel();
            }
        };

        // Load an interstitial ad. When a natural transition in the app occurs (such as a level
        // ending in a game), show the interstitial. In this simple example, the press of a
        // button is used instead.
        //
        // If the button is clicked before the interstitial is loaded, the user should proceed to
        // the next part of the app (in this case, the next level).
        //
        // If the interstitial is finished loading, the user will view the interstitial before
        // proceeding.
        InterstitialAd.load(
                this,
                "myAdUnitId",
                new AdRequest.Builder().build(),
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd ad) {
                        interstitialAd = ad;
                        interstitialAd.setFullScreenContentCallback(fullScreenContentCallback);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                        // Code to be executed when an ad request fails.
                    }
                });

        // Create the button to go to the next level.
        nextLevelButton = new Button(this);
        nextLevelButton.setText("Next Level");
        nextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the interstitial if it is ready. Otherwise, proceed to the next level
                // without ever showing it.
                if (interstitialAd != null) {
                    interstitialAd.show(MyActivity.this);
                } else {
                    // Proceed to the next level.
                    goToNextLevel();
                }
            }
        });

//        // Add the next level button to the layout.
//        LinearLayout layout = new LinearLayout(this);
//        layout.setOrientation(LinearLayout.VERTICAL);
//        layout.addView(nextLevelButton);
//
//        // Create a TextView to display the current level.
//        textView = new TextView(this);
//        textView.setText("Level 1");
//        layout.addView(textView);
//
//        setContentView(layout);
    }

    public void goToNextLevel() {
        // Show the next level, and disable the next level button since there are no more levels.
        nextLevelButton.setEnabled(false);
        textView.setText("Level 2");
    }
}