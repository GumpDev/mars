package dev.gump.mars.tab;

import java.util.List;

public class TabUtils {
    public static TabInstance CreateTab(List<String> header){
        return CreateTab(String.join("\n", header));
    }
    public static TabInstance CreateTab(List<String> header, List<String> footer){
        return CreateTab(String.join("\n", header), String.join("\n", footer));
    }
    public static TabInstance CreateTab(List<String> header, String footer){
        return CreateTab(String.join("\n", header), footer);
    }
    public static TabInstance CreateTab(String header, List<String> footer){
        return CreateTab(header, String.join("\n", footer));
    }
    public static TabInstance CreateTab(String header){
        return CreateTab(header, "");
    }
    public static TabInstance CreateTab(String header, String footer){
        return new TabInstance(header,footer);
    }
}
