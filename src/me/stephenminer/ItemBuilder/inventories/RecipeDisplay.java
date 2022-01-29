package me.stephenminer.ItemBuilder.inventories;

import me.stephenminer.ItemBuilder.Main;
import me.stephenminer.ItemBuilder.builder.RecipeBuilder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class RecipeDisplay {
    private Main plugin;
    private String id;

    public RecipeDisplay(Main plugin, String id){
        this.plugin = plugin;
        this.id = id;
    }

    public Inventory display(){
        DefaultItems items = new DefaultItems();
        CustomInventory type = new CustomInventory(CustomInventory.CustomInventoryType.RECIPE, 45,id + " Recipe");
        Inventory inv = type.getInventory();
        for (int i = 0; i <= 44; i++)
            inv.setItem(i, items.filler());
        for(int i = 12; i <= 14; i++)
            inv.setItem(i, null);
        for(int i = 21; i <= 23; i++)
            inv.setItem(i, null);
        for(int i = 30; i <= 32; i++)
            inv.setItem(i, null);
        inv.setItem(40, items.back());
        setRecipeLayOut(inv);
        return inv;
    }
    public void setRecipeLayOut(Inventory inv){
        if (inv.getSize() < 40)
            return;
        RecipeBuilder r = new RecipeBuilder(plugin, id);
        if (r.hasIngredient("a"))
            inv.setItem(12, r.getIngrediant("a"));
        if (r.hasIngredient("b"))
            inv.setItem(13, r.getIngrediant("b"));
        if (r.hasIngredient("c"))
            inv.setItem(14, r.getIngrediant("c"));
        if (r.hasIngredient("d"))
            inv.setItem(21, r.getIngrediant("d"));
        if (r.hasIngredient("e"))
            inv.setItem(22, r.getIngrediant("e"));
        if (r.hasIngredient("f"))
            inv.setItem(23, r.getIngrediant("f"));
        if (r.hasIngredient("g"))
            inv.setItem(30, r.getIngrediant("g"));
        if (r.hasIngredient("h"))
            inv.setItem(31, r.getIngrediant("h"));
        if (r.hasIngredient("i"))
            inv.setItem(32, r.getIngrediant("i"));

    }
    public boolean craftingSlot(int i){
        switch (i){
            case 12:
                return true;
            case 13:
                return true;
            case 14:
                return true;
            case 21:
                return true;
            case 22:
                return true;
            case 23:
                return true;
            case 30:
                return true;
            case 31:
                return true;
            case 32:
                return true;
        }
        return false;
    }

    public void saveRecipe(Inventory inv){
        plugin.Recipes.getConfig().set("recipes." + id + ".a", inv.getItem(12));
        plugin.Recipes.getConfig().set("recipes." + id + ".b", inv.getItem(13));
        plugin.Recipes.getConfig().set("recipes." + id + ".c", inv.getItem(14));
        plugin.Recipes.getConfig().set("recipes." + id + ".d", inv.getItem(21));
        plugin.Recipes.getConfig().set("recipes." + id + ".e", inv.getItem(22));
        plugin.Recipes.getConfig().set("recipes." + id + ".f", inv.getItem(23));
        plugin.Recipes.getConfig().set("recipes." + id + ".g", inv.getItem(30));
        plugin.Recipes.getConfig().set("recipes." + id + ".h", inv.getItem(31));
        plugin.Recipes.getConfig().set("recipes." + id + ".i", inv.getItem(32));
        plugin.Recipes.saveConfig();
    }
}
