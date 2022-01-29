package me.stephenminer.ItemBuilder.inventories;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class CustomInventory implements InventoryHolder {
    private CustomInventoryType inventoryType;
    private Inventory inventory;
    public CustomInventory(CustomInventoryType invType, int size, String title){
        this.inventory = Bukkit.createInventory(this, size,title);
        this.inventoryType = invType;

    }



    @Override
    public Inventory getInventory() {
        return  inventory;
    }

    public CustomInventoryType getType(){
        return inventoryType;
    }

    public enum CustomInventoryType{
        MAINMENU,
        ATTRIBUTES,
        ENCHANTMENTS,
        EDITVALUE,
        EDITLEVEL,
        EDITMATERIAL,
        RECIPE,
        AREYOUSUREATTRIBUTE,
        AREYOUSUREENCHANT,
        DELETEORADDVALUE,
        DELETEORADDLEVEL
    }
}
