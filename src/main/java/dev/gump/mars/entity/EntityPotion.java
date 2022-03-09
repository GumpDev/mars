package dev.gump.mars.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class EntityPotion {
    private final PotionEffectType type;
    private final int time;
    private final int amplifier;

    public EntityPotion(PotionEffectType type) {
        this.type = type;
        this.time = 9999;
        this.amplifier = 0;
    }
    public EntityPotion(PotionEffectType type, int time) {
        this.type = type;
        this.time = time;
        this.amplifier = 0;
    }
    public EntityPotion(PotionEffectType type, int time, int amplifier) {
        this.type = type;
        this.time = time;
        this.amplifier = amplifier;
    }

    public PotionEffectType getType() {
        return type;
    }
    public int getAmplifier() {
        return amplifier;
    }
    public int getTime() {
        return time;
    }

    protected static LivingEntity AddPotion(LivingEntity entity, EntityPotion ...potions){
        for(EntityPotion potion : potions)
            entity.addPotionEffect(new PotionEffect(potion.getType(), potion.getTime(), potion.getAmplifier()));
        return entity;
    }
    protected static LivingEntity RemPotion(LivingEntity entity, PotionEffectType ...potions){
        for(PotionEffectType potion : potions)
            entity.removePotionEffect(potion);
        return entity;
    }
    protected static LivingEntity ClearPotions(LivingEntity entity){
        for(PotionEffect potion: entity.getActivePotionEffects())
            entity.removePotionEffect(potion.getType());
        return entity;
    }
}
