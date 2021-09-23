package dev.gump.mars.scoreboard;

public class ScoreboardManager {
    public ScoreboardInstance createScoreboard(String name, String... texts){ return ScoreboardUtils.CreateScoreboard(name, texts); }
}
