package dev.gump.mars.entity;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;

import java.util.Objects;

public class EntityAttribute {
    private final Attribute attribute;
    private final AttributeModifier modifier;

    public EntityAttribute(Attribute attribute, AttributeModifier modifier) {
        this.attribute = attribute;
        this.modifier = modifier;
    }

    public Attribute getAttribute() {
        return attribute;
    }
    public AttributeModifier getModifier() {
        return modifier;
    }

    protected static LivingEntity AddAttribute(LivingEntity entity, EntityAttribute... attributes){
        for(EntityAttribute attribute : attributes)
            Objects.requireNonNull(entity.getAttribute(attribute.getAttribute())).addModifier(attribute.getModifier());
        return entity;
    }

    protected static LivingEntity RemAttribute(LivingEntity entity, EntityAttribute... attributes){
        for(EntityAttribute attribute : attributes)
            Objects.requireNonNull(entity.getAttribute(attribute.getAttribute())).removeModifier(attribute.getModifier());
        return entity;
    }
}
