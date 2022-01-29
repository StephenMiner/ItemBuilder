package me.stephenminer.ItemBuilder.inventories;

import me.stephenminer.ItemBuilder.Main;
import me.stephenminer.ItemBuilder.builder.BuildAttribute;
import me.stephenminer.ItemBuilder.builder.ItemBuilder;
import me.stephenminer.ItemBuilder.builder.StringTranslators;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultItems {
    public ItemStack filler(){
        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack back(){
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Back");
        item.setItemMeta(meta);
        return item;
    }

    public Set<Enchantment> validEnchants(){
        Set<Enchantment> enchants = new HashSet<>();
        enchants.add(Enchantment.LURE);
        enchants.add(Enchantment.RIPTIDE);
        enchants.add(Enchantment.ARROW_FIRE);
        enchants.add(Enchantment.ARROW_DAMAGE);
        enchants.add(Enchantment.ARROW_INFINITE);
        enchants.add(Enchantment.BINDING_CURSE);
        enchants.add(Enchantment.PROTECTION_FIRE);
        enchants.add(Enchantment.PROTECTION_PROJECTILE);
        enchants.add(Enchantment.QUICK_CHARGE);
        enchants.add(Enchantment.SWEEPING_EDGE);
        enchants.add(Enchantment.VANISHING_CURSE);
        enchants.add(Enchantment.ARROW_KNOCKBACK);
        enchants.add(Enchantment.CHANNELING);
        enchants.add(Enchantment.DAMAGE_ALL);
        enchants.add(Enchantment.DAMAGE_ARTHROPODS);
        enchants.add(Enchantment.DAMAGE_UNDEAD);
        enchants.add(Enchantment.DEPTH_STRIDER);
        enchants.add(Enchantment.DIG_SPEED);
        enchants.add(Enchantment.DURABILITY);
        enchants.add(Enchantment.FIRE_ASPECT);
        enchants.add(Enchantment.FROST_WALKER);
        enchants.add(Enchantment.IMPALING);
        enchants.add(Enchantment.LOOT_BONUS_BLOCKS);
        enchants.add(Enchantment.KNOCKBACK);
        enchants.add(Enchantment.LOYALTY);
        enchants.add(Enchantment.LOOT_BONUS_MOBS);
        enchants.add(Enchantment.LUCK);
        enchants.add(Enchantment.MENDING);
        enchants.add(Enchantment.MULTISHOT);
        enchants.add(Enchantment.OXYGEN);
        enchants.add(Enchantment.PIERCING);
        enchants.add(Enchantment.PROTECTION_ENVIRONMENTAL);
        enchants.add(Enchantment.PROTECTION_EXPLOSIONS);
        enchants.add(Enchantment.PROTECTION_FALL);
        enchants.add(Enchantment.SILK_TOUCH);
        enchants.add(Enchantment.SOUL_SPEED);
        enchants.add(Enchantment.THORNS);
        enchants.add(Enchantment.WATER_WORKER);
        return enchants;
    }


    public ItemStack attributes(Attribute attribute){
        StringTranslators translators = new StringTranslators();
        String name = translators.attributeToString(attribute);
        ItemStack item = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + name);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.ITALIC + "Click me to add attribute to item,");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack enchantedBook(Enchantment enchantment){
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(enchantment, 1, true);
        meta.setDisplayName(ChatColor.BLUE + "Click to add enchantment");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.ITALIC + "Click to add enchantment ");
        lore.add(ChatColor.ITALIC + "and edit its level!");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack currentLevel(String id, Enchantment enchantment, Main plugin){
        ItemBuilder builder = new ItemBuilder(plugin, id);
        int level = builder.getEnchantments().get(enchantment);
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Current Level: " + level);
        meta.addEnchant(enchantment, level, true);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack currentValue(String id, Attribute attribute, Main plugin){
        StringTranslators stringTranslators = new StringTranslators();
        String attrib = stringTranslators.attributeToString(attribute);
        BuildAttribute buildattribute = new BuildAttribute(plugin, id);
        double value = buildattribute.getAttributes().get(attribute);
        ItemStack item = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Current Value: " + value);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack addNumbers(int i){
        ItemStack item = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta meta = item.getItemMeta();
        String name = intToString(i);
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
    private String intToString(int i){
        switch (i){
            case -5:
                return "-5";
            case -4:
                return "-4";
            case -1:
                return "-1";
            case 5:
                return "5";
            case 4:
                return "4";
            case 1:
                return "1";
        }
        return "1";
    }
}

