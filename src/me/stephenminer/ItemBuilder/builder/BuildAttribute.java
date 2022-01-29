package me.stephenminer.ItemBuilder.builder;

import me.stephenminer.ItemBuilder.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class BuildAttribute{
    private Main plugin;
    private String id;
    public BuildAttribute(Main plugin, String id){
        this.plugin = plugin;
        this.id = id;
    }
    private Set<String> getConfigurationSection(String section){
        if (!plugin.Items.getConfig().contains("items." + id + "." + section))
            return null;
        return plugin.Items.getConfig().getConfigurationSection("items." + id + "."+ section).getKeys(false);
    }
    public Set<String> acceptableStrings(){
        Set<String> set = new HashSet<>();
        set.add(Attribute.GENERIC_ATTACK_DAMAGE.name());
        set.add(Attribute.GENERIC_ATTACK_KNOCKBACK.name());
        set.add(Attribute.GENERIC_ATTACK_SPEED.name());
        set.add(Attribute.GENERIC_ARMOR.name());
        set.add(Attribute.GENERIC_ARMOR_TOUGHNESS.name());
        set.add(Attribute.GENERIC_KNOCKBACK_RESISTANCE.name());
        set.add(Attribute.GENERIC_LUCK.name());
        set.add(Attribute.GENERIC_FLYING_SPEED.name());
        set.add(Attribute.GENERIC_MAX_HEALTH.name());
        return set;
    }

    public Map<Attribute, Double> getAttributes(){
        Set<String> section = getConfigurationSection("attributes");
        if (section == null || section.isEmpty())
            return null;
        Map<Attribute,Double> map = new HashMap<>();
        for (String key : section){
            if (key == null || key.isEmpty())
                continue;
            Attribute a = acceptableStrings().contains(key) ? Attribute.valueOf(key) : null;
            if (a != null){
                double i = plugin.Items.getConfig().getDouble("items." + id + ".attributes." + key + ".amount");
                if (i < 1)
                    i = 1;
                map.put(a,i);
            }
        }
        return map;
    }

    public ItemStack addAttributes(ItemStack item){
        ItemStack items = item;
        ItemMeta meta = items.getItemMeta();
        Set<Attribute> set = getAttributes() != null ? getAttributes().keySet() : new HashSet<>();
        if (set.isEmpty())
            return item;
        for (Attribute attribute : set) {
            if (attribute == null)
                continue;
            meta.addAttributeModifier(attribute, translateAttribute(attribute));
        }
        items.setItemMeta(meta);
        return items;


    }


    public EquipmentSlot getSlot(Attribute attribute){
        String s = plugin.Items.getConfig().getString("items." + id + ".attributes." + attribute.name() + ".slot");
        boolean b = s !=null;
        return b ? EquipmentSlot.valueOf(s) : EquipmentSlot.HAND;
    }


    public AttributeModifier translateAttribute(Attribute attribute){
        Map<Attribute, Double> map = getAttributes();
        AttributeModifier modifier;
        switch (attribute){
            case GENERIC_ATTACK_DAMAGE:
                 modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", map.get(attribute), AttributeModifier.Operation.ADD_NUMBER, getSlot(attribute));
                return modifier;
            case GENERIC_ARMOR:
                modifier = new AttributeModifier(UUID.randomUUID(), "generic.armor", map.get(attribute), AttributeModifier.Operation.ADD_NUMBER, getSlot(attribute));
                return modifier;
            case GENERIC_ARMOR_TOUGHNESS:
                modifier = new AttributeModifier(UUID.randomUUID(), "generic.armorToughness", map.get(attribute), AttributeModifier.Operation.ADD_NUMBER, getSlot(attribute));
                return modifier;
            case GENERIC_ATTACK_KNOCKBACK:
                modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackKnockback", map.get(attribute), AttributeModifier.Operation.ADD_NUMBER, getSlot(attribute));
                return modifier;
            case GENERIC_ATTACK_SPEED:
                modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", map.get(attribute), AttributeModifier.Operation.ADD_NUMBER, getSlot(attribute));
                return modifier;
            case GENERIC_MOVEMENT_SPEED:
                modifier = new AttributeModifier(UUID.randomUUID(), "generic.movementSpeed", map.get(attribute), AttributeModifier.Operation.ADD_NUMBER, getSlot(attribute));
                return modifier;
            case GENERIC_KNOCKBACK_RESISTANCE:
                modifier = new AttributeModifier(UUID.randomUUID(), "generic.knockbackResistance", map.get(attribute), AttributeModifier.Operation.ADD_NUMBER, getSlot(attribute));
                return modifier;
            case GENERIC_MAX_HEALTH:
                modifier = new AttributeModifier(UUID.randomUUID(), "generic.maxHealth", map.get(attribute), AttributeModifier.Operation.ADD_NUMBER, getSlot(attribute));
                return modifier;
            case GENERIC_LUCK:
                modifier = new AttributeModifier(UUID.randomUUID(), "generic.luck", map.get(attribute), AttributeModifier.Operation.ADD_NUMBER, getSlot(attribute));
                return modifier;
            case GENERIC_FLYING_SPEED:
                modifier = new AttributeModifier(UUID.randomUUID(), "generic.flyingSpeed", map.get(attribute), AttributeModifier.Operation.ADD_NUMBER, getSlot(attribute));
                return modifier;
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Attempted to create attribute modifier for attribute " + attribute.name() + ", " + attribute.name() + " is not supported!");
        return null;
    }


}
