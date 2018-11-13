package a207project.fall18.GameCenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class GameSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);
        setTitle("Select Game");

        setupGame1ButtonListener();
        LogoutButtonListener();
    }

    /**
     * Button for SlidingTiles
     */
    private void setupGame1ButtonListener(){
        Button Game1 = findViewById(R.id.Game1);
        Game1.setOnClickListener((v) -> {
            Intent i = new Intent(this, StartingActivity.class);
            startActivity(i);
        });
    }

    /**
     * Logout button
     */
    private void LogoutButtonListener(){
        Button Logout= findViewById(R.id.Logout);
        Logout.setOnClickListener((v) -> {
            Intent i = new Intent(this, SignInActivity.class);
            startActivity(i);
        });
    }
}