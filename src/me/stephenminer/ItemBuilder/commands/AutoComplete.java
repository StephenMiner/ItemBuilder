package me.stephenminer.ItemBuilder.commands;

import me.stephenminer.ItemBuilder.Main;
import me.stephenminer.ItemBuilder.builder.BuildAttribute;
import me.stephenminer.ItemBuilder.builder.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;

import java.util.*;

public class AutoComplete implements TabCompleter {
    private Main plugin;
    public AutoComplete(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        int length = args.length;
        if (!cmd.getName().equalsIgnoreCase("itembuilder"))
            return null;
        if (length == 1)
            return baseCompleter();
        String args1 = args[0];
        if (args1.equalsIgnoreCase("give")){
            if (length == 3)
                return itemCompleter();
            return playerItemCompleter();
        }
        if (args1.equalsIgnoreCase("recipe"))
            return itemCompleter();
        if (args1.equalsIgnoreCase("addEnchantment")){
            if (length == 3)
                return addEnchantment();
            return itemCompleter();
        }
        if (args1.equalsIgnoreCase("addAttribute")){
            if (length == 3)
                return addAttribute();
            if (length == 4)
                return needInt();
            if (length == 5)
                return slotList();

            return itemCompleter();
        }
        if (args1.equalsIgnoreCase("removeEnchantment")){
            if (length == 3)
                return removeEnchant(args[1]);
            return itemCompleter();
        }
        if (args1.equalsIgnoreCase("removeAttribute")){
            if (length == 3)
                return removeAttribute(args[1]);
            return itemCompleter();
        }
        if (args1.equalsIgnoreCase("setUnbreakable")){
            if (length == 3)
                return unbreakable();
            return itemCompleter();
        }
        if (args1.equalsIgnoreCase("addItemFlag")){
            if (length == 3)
                return itemFlags();
            return itemCompleter();
        }
        if (args1.equalsIgnoreCase("removeItemFlag")){
            if (length == 3)
                return removeItemFlags(args[1]);
            return itemCompleter();
        }
        if (args1.equalsIgnoreCase("setLore")){
            if (length == 3)
                return setLore();
            return itemCompleter();
        }
        if (args1.equalsIgnoreCase("setDisplayname"))
            return itemCompleter();
        if (args1.equalsIgnoreCase("removeDisplayname"))
            return itemCompleter();


        return null;
    }

    private List<String> baseCompleter(){
        List<String> completer = new ArrayList<>();
        completer.add("reloadconfig");
        completer.add("give");
        completer.add("recipe");
        completer.add("addAttribute");
        completer.add("addEnchantment");
        completer.add("removeEnchantment");
        completer.add("removeAttribute");
        completer.add("setUnbreakable");
        completer.add("addItemFlag");
        completer.add("removeItemFlag");
        completer.add("setLore");
        completer.add("setDisplayName");
        completer.add("removeLore");
        completer.add("createItem");
        completer.add("removeDisplayname");
        return completer;
    }
    private List<String> itemCompleter(){
        return new ArrayList<>(plugin.Items.getConfig().getConfigurationSection("items").getKeys(false));
    }
    private List<String> playerItemCompleter(){
        List<String> returnList = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()){
            returnList.add(player.getName());
        }
        returnList.addAll(plugin.Items.getConfig().getConfigurationSection("items").getKeys(false));
        return returnList;
    }
    private List<String> unbreakable(){
        List<String> list = new ArrayList<>();
        list.add("true");
        list.add("false");
        return list;
    }
    private List<String> addEnchantment(){
        List<String> enchants = new ArrayList<>();
        enchants.add(Enchantment.LURE.getKey().getKey());
        enchants.add(Enchantment.RIPTIDE.getKey().getKey());
        enchants.add(Enchantment.ARROW_FIRE.getKey().getKey());
        enchants.add(Enchantment.ARROW_DAMAGE.getKey().getKey());
        enchants.add(Enchantment.ARROW_INFINITE.getKey().getKey());
        enchants.add(Enchantment.BINDING_CURSE.getKey().getKey());
        enchants.add(Enchantment.PROTECTION_FIRE.getKey().getKey());
        enchants.add(Enchantment.PROTECTION_PROJECTILE.getKey().getKey());
        enchants.add(Enchantment.QUICK_CHARGE.getKey().getKey());
        enchants.add(Enchantment.SWEEPING_EDGE.getKey().getKey());
        enchants.add(Enchantment.VANISHING_CURSE.getKey().getKey());
        enchants.add(Enchantment.ARROW_KNOCKBACK.getKey().getKey());
        enchants.add(Enchantment.CHANNELING.getKey().getKey());
        enchants.add(Enchantment.DAMAGE_ALL.getKey().getKey());
        enchants.add(Enchantment.DAMAGE_ARTHROPODS.getKey().getKey());
        enchants.add(Enchantment.DAMAGE_UNDEAD.getKey().getKey());
        enchants.add(Enchantment.DEPTH_STRIDER.getKey().getKey());
        enchants.add(Enchantment.DIG_SPEED.getKey().getKey());
        enchants.add(Enchantment.DURABILITY.getKey().getKey());
        enchants.add(Enchantment.FIRE_ASPECT.getKey().getKey());
        enchants.add(Enchantment.FROST_WALKER.getKey().getKey());
        enchants.add(Enchantment.IMPALING.getKey().getKey());
        enchants.add(Enchantment.LOOT_BONUS_BLOCKS.getKey().getKey());
        enchants.add(Enchantment.KNOCKBACK.getKey().getKey());
        enchants.add(Enchantment.LOYALTY.getKey().getKey());
        enchants.add(Enchantment.LOOT_BONUS_MOBS.getKey().getKey());
        enchants.add(Enchantment.LUCK.getKey().getKey());
        enchants.add(Enchantment.MENDING.getKey().getKey());
        enchants.add(Enchantment.MULTISHOT.getKey().getKey());
        enchants.add(Enchantment.OXYGEN.getKey().getKey());
        enchants.add(Enchantment.PIERCING.getKey().getKey());
        enchants.add(Enchantment.PROTECTION_ENVIRONMENTAL.getKey().getKey());
        enchants.add(Enchantment.PROTECTION_EXPLOSIONS.getKey().getKey());
        enchants.add(Enchantment.PROTECTION_FALL.getKey().getKey());
        enchants.add(Enchantment.SILK_TOUCH.getKey().getKey());
        enchants.add(Enchantment.SOUL_SPEED.getKey().getKey());
        enchants.add(Enchantment.THORNS.getKey().getKey());
        enchants.add(Enchantment.WATER_WORKER.getKey().getKey());
        return enchants;
    }
    private List<String> addAttribute(){
        List<String> list = new ArrayList<>();
        list.add(Attribute.GENERIC_ATTACK_DAMAGE.name());
        list.add(Attribute.GENERIC_ATTACK_SPEED.name());
        list.add(Attribute.GENERIC_ATTACK_KNOCKBACK.name());
        list.add(Attribute.GENERIC_ARMOR.name());
        list.add(Attribute.GENERIC_KNOCKBACK_RESISTANCE.name());
        list.add(Attribute.GENERIC_LUCK.name());
        list.add(Attribute.GENERIC_MAX_HEALTH.name());
        list.add(Attribute.GENERIC_MOVEMENT_SPEED.name());
        list.add(Attribute.GENERIC_FLYING_SPEED.name());
        list.add(Attribute.GENERIC_ARMOR_TOUGHNESS.name());
        return list;
    }
    private List<String> slotList(){
        EquipmentSlot[] slots = EquipmentSlot.values();
        List<String> set = new ArrayList<>();
        for (EquipmentSlot slot : slots){
            set.add(slot.name());
        }
        return set;
    }
    private List<String> removeEnchant(String id){
        ItemBuilder itemBuilder = new ItemBuilder(plugin, id);
        Map<Enchantment, Integer> enchants = itemBuilder.getEnchantments();
        List<String> returnList = new ArrayList<>();
        if (enchants == null || enchants.isEmpty())
            return null;
        for (Enchantment ench : enchants.keySet()){
            returnList.add(ench.getKey().getKey());
        }
        return returnList;
    }
    private List<String> removeAttribute(String id){
        BuildAttribute buildAttribute = new BuildAttribute(plugin, id);
        Map<Attribute, Double> attributes = buildAttribute.getAttributes();
        List<String> returnList = new ArrayList<>();
        if (attributes == null || attributes.isEmpty())
            return null;
        for (Attribute attrib : attributes.keySet()){
            returnList.add(attrib.name());
        }
        return returnList;
    }
    private List<String> itemFlags(){
        List<String> flags = new ArrayList<>();
        flags.add(ItemFlag.HIDE_DYE.name());
        flags.add(ItemFlag.HIDE_ATTRIBUTES.name());
        flags.add(ItemFlag.HIDE_DESTROYS.name());
        flags.add(ItemFlag.HIDE_ENCHANTS.name());
        flags.add(ItemFlag.HIDE_PLACED_ON.name());
        flags.add(ItemFlag.HIDE_POTION_EFFECTS.name());
        flags.add(ItemFlag.HIDE_UNBREAKABLE.name());
        return flags;
    }
    private List<String> removeItemFlags(String id){
        List<String> flags = new ArrayList<>();
        ItemBuilder builder = new ItemBuilder(plugin, id);
        for (ItemFlag itemFlag : builder.getFlags()){
            flags.add(itemFlag.name());
        }
        return flags;
    }
    private List<String> setLore(){
        List<String> lore = new ArrayList<>();
        lore.add("format: [line,line2,line3]");
        return lore;
    }
    private List<String> needInt(){
        List<String> list = new ArrayList<>();
        list.add("[number]");
        return list;
    }

}
