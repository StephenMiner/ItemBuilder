package me.stephenminer.ItemBuilder.inventories;

import me.stephenminer.ItemBuilder.Main;
import me.stephenminer.ItemBuilder.builder.RecipeBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class InventoryEvents implements Listener {
    private Main plugin;
    public InventoryEvents(Main plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onInvClick(InventoryClickEvent event){
        Player p = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        if (inv == null)
            return;
        if (inv.equals(event.getView().getBottomInventory()))
            return;
        if (!p.hasPermission("itembuilder.edit.recipes"))
            return;
        String title = event.getView().getTitle();
        for (String key : plugin.Items.getConfig().getConfigurationSection("items").getKeys(false)){
            if (title.contains(key + " Recipe")) {
                checkType(inv, CustomInventory.CustomInventoryType.RECIPE);
                RecipeDisplay m = new RecipeDisplay(plugin, key);
                ItemStack item = event.getCurrentItem();
                if (item == null)
                    return;
                if (!m.craftingSlot(event.getSlot()) || item.getType().equals(Material.BARRIER)){
                    event.setCancelled(true);
                    if (item.getType().equals(Material.BARRIER))
                        p.closeInventory();
                    return;
                }
                return;
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        Player p = (Player) event.getPlayer();
        if (!p.hasPermission("itembuilder.edit.recipes"))
            return;
        String title = event.getView().getTitle();
        for (String key : plugin.Items.getConfig().getConfigurationSection("items").getKeys(false)){
            if (title.contains(key + " Recipe")){
                boolean isType = checkType(event.getInventory(), CustomInventory.CustomInventoryType.RECIPE);
                if (!isType)
                    return;
                RecipeDisplay recipeDisplay = new RecipeDisplay(plugin, key);
                recipeDisplay.saveRecipe(event.getInventory());
                RecipeBuilder recipeBuilder = new RecipeBuilder(plugin, key);
                recipeBuilder.createRecipe(key);
                return;
            }
        }
    }

    private boolean checkType(Inventory inv, CustomInventory.CustomInventoryType targetType){
        InventoryHolder holder = inv.getHolder();
        if (holder instanceof CustomInventory){
            CustomInventory customInventory = (CustomInventory) holder;
            if (customInventory.getType() != targetType)
                return false;
            return true;
        }
        return false;
    }
}
