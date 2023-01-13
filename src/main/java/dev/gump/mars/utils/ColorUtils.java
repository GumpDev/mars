package dev.gump.mars.utils;

import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.ChatColor;

public class ColorUtils {
  private static char DEFAULT_COLOR_CHAR = '&';

  public static char getDefaultColorChar() {
    return DEFAULT_COLOR_CHAR;
  }

  public static void setDefaultColorChar(char defaultColorChar) {
    DEFAULT_COLOR_CHAR = defaultColorChar;
  }

  public static List<String> colorize(List<String> s) {
    return colorize(DEFAULT_COLOR_CHAR, s);
  }

  public static List<String> colorize(char c, List<String> s) {
    return s.stream().map(t -> colorize(c, t)).collect(Collectors.toList());
  }

  public static String colorize(String s) {
    return ChatColor.translateAlternateColorCodes(DEFAULT_COLOR_CHAR, s);
  }

  public static String colorize(char c, String s) {
    return ChatColor.translateAlternateColorCodes(c, s);
  }
}
