package dev.gump.mars.inventory;

import dev.gump.mars.Mars;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class GUIPagination{
    Material itemMaterial = null;
    GUIPaginationEvents action;
    String title = null;
    String description = null;

    final ItemStack back = GUIDefaultItems.getBack();
    final ItemStack prev = GUIDefaultItems.getPrev();
    final ItemStack next = GUIDefaultItems.getNext();
    final ItemStack none = GUIDefaultItems.getNone();

    public GUIPagination(String title, String description, Material itemMaterial, GUIPaginationEvents action) {
        this.itemMaterial = itemMaterial;
        this.action = action;
        this.title = title;
        this.description = description;
    }

    public GUI generateGUI(int page, int totalItems) {
        List<PageItem> items = this.action.onPageChange(page);
        int maxPage = (int) Math.ceil((float)totalItems / 54);
        ItemStack itemTitle = (this.itemMaterial != null) ? Mars.items.createItemStack(this.itemMaterial, page, title,
                Arrays.asList(GUIDefaultMessages.getPageInfo().replace("[0]", page+"").replace("[1]", maxPage+""), " ", this.description)) : null;
        GUI gui = Mars.inventory.createGUI(this.title, 54, back, none, none, none, itemTitle != null ? itemTitle : none, none,
                none, (page != 1 ? prev : none), (page < maxPage ? next : none));
        int index = 9;

        GUIPagination guiPagination = this;
        if (page != 1)
            gui.OnClick(7, event -> {
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        guiPagination.open((Player) event.getWhoClicked(), page - 1);
                    }
                }.runTaskLater(Mars.getPlugin(), 5);
            });
        if (page < maxPage)
            gui.OnClick(8, event -> {
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        guiPagination.open((Player) event.getWhoClicked(), page + 1);
                    }
                }.runTaskLater(Mars.getPlugin(), 5);
            });

        gui.OnClick(0, event -> {
            new BukkitRunnable(){
                @Override
                public void run() {
                    guiPagination.action.onBack(event);
                }
            }.runTaskLater(Mars.getPlugin(), 5);
        });

        if(items.size() > 0)
            for (PageItem item : items) {
                gui.setItem(index, item.stack, event -> {
                    this.action.onClick(event, item.id);
                });
                index++;
                if (index >= 54)
                    break;
            }
        else
            gui.getInventory().setItem(31, GUIDefaultItems.getError());

        return gui;
    }

    public void open(Player player) {
        open(player, 1);
    }

    public void open(Player player, int page) {
        int maxPage = this.action.getMaxItems();
        GUI gui = generateGUI(page, maxPage);
        player.openInventory(gui.getInventory());
    }
}
