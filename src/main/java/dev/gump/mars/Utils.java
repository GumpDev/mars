package dev.gump.mars;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<String> colorize(List<String> s){
        return colorize('&',s);
    }
    public static List<String> colorize(char c, List<String> s){
        List<String> retorno = new ArrayList<>();
        for(String t : s)
            retorno.add(colorize(c,t));
        return retorno;
    }

    public static String colorize(String s){
        return ChatColor.translateAlternateColorCodes('&',s);
    }
    public static String colorize(char c, String s){
        return ChatColor.translateAlternateColorCodes(c,s);
    }
}
