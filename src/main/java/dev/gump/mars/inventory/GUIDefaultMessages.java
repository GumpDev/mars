package dev.gump.mars.inventory;

public class GUIDefaultMessages {
    static String pageInfo = "&7Page: [0] of [1]";
    static String playerPrefix = "&6";

    public static String getPageInfo() {
        return pageInfo;
    }

    public static String getPlayerPrefix() {
        return playerPrefix;
    }

    public static void setPageInfo(String pageInfo) {
        GUIDefaultMessages.pageInfo = pageInfo;
    }

    public static void setPlayerPrefix(String playerPrefix) {
        GUIDefaultMessages.playerPrefix = playerPrefix;
    }
}
