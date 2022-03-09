package dev.gump.mars.entity;

import dev.gump.mars.Mars;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataType;

import java.util.Locale;

public class EntityDataPersistent {
    protected static <T> Entity SetPersistentData(Entity entity, PersistentDataType type, String key, T value){
        NamespacedKey nKey = new NamespacedKey(Mars.getPlugin(), key.toLowerCase(Locale.ROOT));
        entity.getPersistentDataContainer().set(nKey, type, value);
        return entity;
    }
    protected static Boolean HasPersistentData(Entity entity, PersistentDataType type, String key){
        NamespacedKey nKey = new NamespacedKey(Mars.getPlugin(), key.toLowerCase(Locale.ROOT));
        return entity.getPersistentDataContainer().has(nKey, type);
    }
    protected static <T> T GetPersistentData(Entity entity, PersistentDataType type, String key){
        NamespacedKey nKey = new NamespacedKey(Mars.getPlugin(), key.toLowerCase(Locale.ROOT));
        return (T) entity.getPersistentDataContainer().get(nKey, type);
    }
    protected static Entity RemovePersistentData(Entity entity, String key){
        NamespacedKey nKey = new NamespacedKey(Mars.getPlugin(), key.toLowerCase(Locale.ROOT));
        entity.getPersistentDataContainer().remove(nKey);
        return entity;
    }
}
