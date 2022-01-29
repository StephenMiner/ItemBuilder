package me.stephenminer.ItemBuilder.builder;

import org.bukkit.attribute.Attribute;

public class StringTranslators {


    public Attribute stringToAttribute(String string){
        switch (string){
            case "attack":
                return Attribute.GENERIC_ATTACK_DAMAGE;
            case "attack speed":
                return Attribute.GENERIC_ATTACK_SPEED;
            case "knockback":
                return Attribute.GENERIC_ATTACK_KNOCKBACK;
            case "armor":
                return Attribute.GENERIC_ARMOR;
            case "armor toughness":
                return Attribute.GENERIC_ARMOR_TOUGHNESS;
            case "knockback resistance":
                return Attribute.GENERIC_KNOCKBACK_RESISTANCE;
            case "luck":
                return Attribute.GENERIC_LUCK;
            case "fly speed":
                return Attribute.GENERIC_FLYING_SPEED;
            case "movement speed":
                return Attribute.GENERIC_MOVEMENT_SPEED;
            case "health":
                return Attribute.GENERIC_MAX_HEALTH;
        }
        return null;
    }
    public String attributeToString(Attribute attribute){
        switch (attribute){
            case GENERIC_ATTACK_DAMAGE:
                return "attack";
            case GENERIC_ATTACK_SPEED:
                return "attack speed";
            case GENERIC_ATTACK_KNOCKBACK:
                return "knockback";
            case GENERIC_ARMOR:
                return "armor";
            case GENERIC_ARMOR_TOUGHNESS:
                return "armor tougness";
            case GENERIC_KNOCKBACK_RESISTANCE:
                return "knockback resistance";
            case GENERIC_LUCK:
                return "luck";
            case GENERIC_FLYING_SPEED:
                return "fly speed";
            case GENERIC_MOVEMENT_SPEED:
                return "movement speed";
            case GENERIC_MAX_HEALTH:
                return "health";
        }
        return null;
    }
}
