package com.app.rakez.winnersprit.quiz;

/**
 * Created by RAKEZ on 11/28/2017.
 */

public interface ActivityCommunicator {
    void hideProfile();
    void showProfile();
    void updateScoreandLevel(Integer obtainedScore, Integer totalScore, Integer level);
}
