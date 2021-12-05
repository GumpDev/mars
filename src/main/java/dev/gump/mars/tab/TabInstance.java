package dev.gump.mars.tab;

import dev.gump.mars.Utils;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class TabInstance {
    String header, footer;
    public TabInstance(String header, String footer){
        setHeader(header);
        setFooter(footer);
    }

    public List<String> getHeaderList(){
        return Arrays.asList(header.split("\n"));
    }
    public String getHeader(){
        return header;
    }
    public List<String> getFooterList(){
        return Arrays.asList(footer.split("\n"));
    }
    public String getFooter(){
        return footer;
    }

    public void setHeader(String header) {
        this.header = Utils.colorize(header);
    }
    public void setFooter(String footer) {
        this.footer = Utils.colorize(footer);
    }

    public void render(Player player){
        if(getHeader().length() > 0 && getFooter().length() > 0)
            player.setPlayerListHeaderFooter(getHeader(),getFooter());
        else if(getHeader().length() > 0)
            player.setPlayerListHeader(getFooter());
        else if(getFooter().length() > 0)
            player.setPlayerListHeader(getFooter());
    }
}
