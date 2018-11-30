package a207project.fall18.GameCenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import a207project.fall18.GameCenter.dao.SaveDao;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class SlidingTilesStartingActivity extends AppCompatActivity {

    private SaveDao savingManager;
    /**
     * The tiles manager.
     */
    private SlidingTilesBoardManager slidingTilesBoardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        slidingTilesBoardManager = new SlidingTilesBoardManager();
        MyApplication.getInstance().setBoardManager(slidingTilesBoardManager);
        savingManager = MyApplication.getInstance().getSavingManager();

        setContentView(R.layout.activity_sliding_tiles_starting_);
        addStartButtonListener();
        addLoadButtonListener();
        addSaveButtonListener();
        addScoreboardButtonListener();
    }

    /**
     * Activate the Scoreboard button.
     */
    private void addScoreboardButtonListener() {
        Button scoreboard = findViewById(R.id.Scoreboard);
        scoreboard.setOnClickListener((v) -> {
            Intent i = new Intent(this, ScoreboardActivity.class);
            startActivity(i);
        });
    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener((v) -> {
            Intent i = new Intent(this, ComplexityActivity.class);
            startActivity(i);
        });
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(v -> {

            List<BoardManager> historicalFile = savingManager.query("get slidingTilesBoardManager");

            if (historicalFile.size() != 0){
                slidingTilesBoardManager = (SlidingTilesBoardManager) historicalFile.get(0);
                MyApplication.getInstance().setBoardManager(slidingTilesBoardManager);// testing
                makeToastLoadedText();
                switchToGame();
            }
            else{
                Toast.makeText(SlidingTilesStartingActivity.this,"No History！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText() {
        Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();
    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(v -> {
//            saveToFile(SAVE_FILENAME);
//            saveToFile(TEMP_SAVE_FILENAME);
            savingManager.autoSave(slidingTilesBoardManager);
            MyApplication.getInstance().setBoardManager(slidingTilesBoardManager);
            makeToastSavedText();
        });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }
    /**
     * Read the temporary tiles from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        slidingTilesBoardManager = (SlidingTilesBoardManager)MyApplication.getInstance().getBoardManager();

    }

    /**
     * Switch to the SlidingTilesGameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, SlidingTilesGameActivity.class);
        MyApplication.getInstance().setBoardManager(slidingTilesBoardManager);
        startActivity(tmp);
    }
}
