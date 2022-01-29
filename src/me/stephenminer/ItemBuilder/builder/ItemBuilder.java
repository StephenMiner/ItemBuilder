package me.stephenminer.ItemBuilder.builder;

import me.stephenminer.ItemBuilder.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class ItemBuilder {
    private Main plugin;
    private String id;
    public ItemBuilder(Main plugin, String id){
        this.plugin = plugin;
        this.id = id;

    }
    public boolean hasEntry(){
        return plugin.Items.getConfig().contains("items." + id);
    }

    private List<String> getStringList(String section){
        if (!plugin.Items.getConfig().contains("items." + id + "." + section))
            return null;
        return plugin.Items.getConfig().getStringList("items." + id + "."+ section);
    }
    private Set<String> getConfigurationSection(String section){
        if (!plugin.Items.getConfig().contains("items." + id + "." + section))
            return null;
        return plugin.Items.getConfig().getConfigurationSection("items." + id + "."+ section).getKeys(false);
    }

    private String getDisplayName(){
        if (!plugin.Items.getConfig().contains("items." + id + ".display-name"))
            return null;
        String name = plugin.Items.getConfig().getString("items." + id + ".display-name");
        if (name == null)
            return null;
        return ChatColor.translateAlternateColorCodes('&', name);
    }
    private List<String> getLore(){
        List<String> tempList = getStringList("lore");
        List<String> returnList = new ArrayList<>();
        if (tempList == null)
            tempList = new ArrayList<>();
        if (tempList.size() > 0)
            for (String entry : tempList){
                if (entry != null && !entry.isEmpty())
                    returnList.add(ChatColor.translateAlternateColorCodes('&', entry));
            }
        returnList.add(ChatColor.GRAY + id);
        return returnList;
    }
    public Map<Enchantment, Integer> getEnchantments(){
        Set<String> section = getConfigurationSection("enchantments");
        Map<Enchantment, Integer> enchants = new HashMap<>();
        if (section == null)
            return null;
        for (String key : section){
            if (key == null)
                continue;
            Enchantment enchant = Enchantment.getByKey(NamespacedKey.minecraft(key));
            int level = plugin.Items.getConfig().getInt("items." + id + ".enchantments." + key + ".level");
            if (level < 1) {
                enchants.put(enchant, 1);
                continue;
            }
            enchants.put(enchant, level);
        }
        return enchants;
    }
    public ItemFlag[] getFlags(){
        List<String> flags = getStringList("item-flags");
        List<ItemFlag> returnList = new ArrayList<>();
        if (flags == null)
            return null;
        for (String key : flags){
            if (key == null)
                continue;
            returnList.add(ItemFlag.valueOf(key));
        }
        return returnList.toArray(new ItemFlag[0]);
    }
    private Material getMaterial(){
        String s = plugin.Items.getConfig().getString("items." + id + ".material");
        Material mat = Material.matchMaterial(s);
        if (mat == null)
            return Material.FEATHER;
        return mat;
    }
    private boolean getUnbreakable(){
        if (!plugin.Items.getConfig().contains("items." + id + ".unbreakable"))
            return false;
        boolean b = plugin.Items.getConfig().getBoolean("items." + id + ".unbreakable");
        return b;
    }


    public ItemStack buildItem(){
        ItemStack item = new ItemStack(getMaterial());
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(getDisplayName());
        if (!getLore().isEmpty())
            meta.setLore(getLore());
        Map<Enchantment, Integer> map = getEnchantments();
        if (map != null && !map.isEmpty())
            for (Enchantment entry : map.keySet()){
                meta.addEnchant(entry, map.get(entry), true);
            }
        if (getFlags() != null && getFlags().length != 0)
            meta.addItemFlags(getFlags());
        meta.setUnbreakable(getUnbreakable());
        item.setItemMeta(meta);
        BuildAttribute ba = new BuildAttribute(plugin, id);
        ItemStack newItem = ba.addAttributes(item);
        return newItem;
    }
}
