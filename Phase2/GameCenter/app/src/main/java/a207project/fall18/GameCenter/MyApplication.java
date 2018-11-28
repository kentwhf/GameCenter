package a207project.fall18.GameCenter;

import android.app.Application;
import android.util.Log;


import a207project.fall18.GameCenter.bean.Score;
import a207project.fall18.GameCenter.bean.User;
import a207project.fall18.GameCenter.dao.SaveDao;
import a207project.fall18.GameCenter.dao.ScoreDao;
import a207project.fall18.GameCenter.dao.UserDao;

public class MyApplication extends Application {

    public User user;
    public String gameType;
//    public Score currentScore;
    public BoardManager boardManager;
//    public SavingManager savingManager;


    public ScoreDao scoreDao = new ScoreDao(this);
    public UserDao userAccountManager = new UserDao(this);
    public SaveDao savingManager;

    private static MyApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public void initSavingManager(){

        Log.v("shabi", "ihiuhlijojkno");

        savingManager = new SaveDao(this, gameType, user.getUsername());
    }

    public UserDao getUserDao(){return userAccountManager;}
    public void setSUserDao(UserDao ud){this.userAccountManager = ud;}

    public ScoreDao getScoreDao(){return scoreDao;}
    public void setScoreDao(ScoreDao sd){this.scoreDao = sd;}

    public BoardManager getBoardManager(){return boardManager;}
    public void setBoardManager(BoardManager bm){this.boardManager = bm;}

    public SaveDao getSavingManager(){return this.savingManager;}
    public void setSavingManager(SaveDao sm){this.savingManager = sm;}

    public static MyApplication getInstance() {
        return instance;
    }

    public String getGame() {
        return gameType;
    }

    public void setGame(String game) {
        this.gameType = game;
    }

//    public Score getScore() {
//        return currentScore;
//    }
//
//    public void setScore(Score score) {
//        this.currentScore = score;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}