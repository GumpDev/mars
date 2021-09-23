package dev.gump.mars.scoreboard;

import dev.gump.mars.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardInstance {
    private String displayName;
    private List<String> lines = new ArrayList<>();
    Player player;
    Scoreboard board;
    Objective objective;

    public ScoreboardInstance(String displayName){
        board = Bukkit.getScoreboardManager().getNewScoreboard();

        this.displayName = displayName;
        objective = board.registerNewObjective("score", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(Utils.colorize(displayName));
    }

    public void Add(String text){
        Add(text,false);
    }

    public void Add(String text, Boolean breakLine){
        if(breakLine)
            lines.addAll(breakLines(text));
        else
            lines.add(stringLine(text));
    }

    public void Set(String text, int index){
        text = stringLine(text);
        Update(lines.get(index),text);
        lines.set(index,text);
    }

    void Update(String line, String newLine){
        if (!Bukkit.getOnlinePlayers().contains(player) || (player.getScoreboard() == null)) {
            return;
        }
        int scoreValue = -1;
        for (String str : player.getScoreboard().getEntries()) {
            if (str.contains(line) || str.equals(line)) {
                scoreValue = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(str).getScore();
                player.getScoreboard().resetScores(str);
                break;
            }
        }
        org.bukkit.scoreboard.Scoreboard score = player.getScoreboard();
        score.getObjective(DisplaySlot.SIDEBAR).getScore(newLine).setScore(scoreValue);
    }

    public void Render(Player player){

        int val = lines.size();
        for(String line : lines){
            Score score = objective.getScore(line);
            score.setScore(val);
            val--;
        }

        player.setScoreboard(board);
        this.player = player;
    }

    private String stringLine(String lines){
        lines = Utils.colorize(lines);
        return lines.length() > 39 ? lines.substring(0,36) + "..." : lines;
    }
    private List<String> breakLines(String lines){
        lines = Utils.colorize(lines);
        List<String> result = new ArrayList<>();
        String line = "";
        for(char c : lines.toCharArray()){
            line += c;
            if(line.length() >= 36){
                result.add(line);
                line = "";
            }
        }
        return result;
    }
}
