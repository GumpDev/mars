package dev.gump.mars.tab;

import java.util.List;

public class TabManager {
    public TabInstance createTab(List<String> header){
        return TabUtils.CreateTab(header);
    }
    public TabInstance createTab(List<String> header, List<String> footer){
        return TabUtils.CreateTab(header, footer);
    }
    public TabInstance createTab(List<String> header, String footer){
        return TabUtils.CreateTab(header, footer);
    }
    public TabInstance createTab(String header, List<String> footer){
        return TabUtils.CreateTab(header, footer);
    }
    public TabInstance createTab(String header){
        return TabUtils.CreateTab(header);
    }
    public TabInstance createTab(String header, String footer){
        return TabUtils.CreateTab(header, footer);
    }
}
