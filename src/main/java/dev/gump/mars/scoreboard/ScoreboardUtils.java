package dev.gump.mars.scoreboard;

public class ScoreboardUtils {
    public static ScoreboardInstance CreateScoreboard(String name, String... texts){
        ScoreboardInstance scoreboard = new ScoreboardInstance(name);
        for(String text : texts)
            scoreboard.Add(text);
        return scoreboard;
    }
}
