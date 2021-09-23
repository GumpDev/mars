package dev.gump.mars.items;

import dev.gump.mars.Mars;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class ItemsEvent {
    public static void OnTryPickup(Item item, PickupEvent dropEvent){
        ItemsListener.dropActions.put(item,dropEvent);
    }
    public static ItemStack OnItemInteract(String id, ItemStack item, InteractEvent interactEvent){
        ItemsListener.interactActions.put(id, interactEvent);
        return Mars.items.setPersistentData(item, PersistentDataType.STRING, ConstItem.marsInteractEvent, id);
    }
    public static ItemStack OnDrop(String id, ItemStack item, DropEvent dropEvent){
        ItemsListener.dropItemActions.put(id, dropEvent);
        return Mars.items.setPersistentData(item, PersistentDataType.STRING, ConstItem.marsDropEvent, id);
    }

    public static void RemoveOnTryPickup(Item item){
        ItemsListener.dropActions.remove(item,ItemsListener.dropActions.get(item));
    }
    public static ItemStack RemoveOnItemInteract(ItemStack item){
        if(!Mars.items.hasPersistentData(item, PersistentDataType.STRING, ConstItem.marsInteractEvent)) return item;
        String id = Mars.items.getPersistentData(item, PersistentDataType.STRING, ConstItem.marsInteractEvent);
        if(!ItemsListener.interactActions.containsKey(id)) return item;
        ItemsListener.interactActions.remove(id, ItemsListener.interactActions.get(id));
        return Mars.items.removePersistentData(item, ConstItem.marsInteractEvent);
    }
    public static ItemStack RemoveOnDrop(ItemStack item){
        if(!Mars.items.hasPersistentData(item, PersistentDataType.STRING, ConstItem.marsDropEvent)) return item;
        String id = Mars.items.getPersistentData(item, PersistentDataType.STRING, ConstItem.marsDropEvent);
        if(!ItemsListener.interactActions.containsKey(id)) return item;
        ItemsListener.dropItemActions.remove(id, ItemsListener.dropItemActions.get(id));
        return Mars.items.removePersistentData(item, ConstItem.marsDropEvent);
    }
}
