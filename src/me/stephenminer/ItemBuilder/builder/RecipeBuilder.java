package me.stephenminer.ItemBuilder.builder;

import me.stephenminer.ItemBuilder.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class RecipeBuilder {
    private Main plugin;
    private String id;
    public RecipeBuilder (Main plugin, String id){
        this.plugin = plugin;
        this.id = id;
    }
    public boolean hasIngredient(String placement){
        return plugin.Recipes.getConfig().contains("recipes." + id + "." + placement);
    }

    private ItemStack getResult(){
        ItemBuilder ib = new ItemBuilder(plugin, id);
        return ib.buildItem();
    }

    public ItemStack getIngrediant(String s){
        if (!plugin.Recipes.getConfig().contains("recipes." + id))
            return null;
        ItemStack item;
        switch (s){
            case "a":
                 item = plugin.Recipes.getConfig().getItemStack("recipes." + id + ".a");
                if (item == null) {
                    String key = plugin.Recipes.getConfig().getString("recipes." +id + ".a");
                    item = new ItemStack(Material.matchMaterial(key));
                    if (item == null)
                        item = new ItemStack(Material.AIR);
                    return item;
                }
                return item;
            case "b":
                item = plugin.Recipes.getConfig().getItemStack("recipes." + id + ".b");
                if (item == null) {
                    String key = plugin.Recipes.getConfig().getString("recipes." + id + ".b");
                    item = new ItemStack(Material.matchMaterial(key));
                    if (item == null)
                        item = new ItemStack(Material.AIR);
                    return item;
                }
                return item;
            case "c":
                item = plugin.Recipes.getConfig().getItemStack("recipes." + id + ".c");
                if (item == null) {
                    String key = plugin.Recipes.getConfig().getString("recipes." + id + ".c");
                    item = new ItemStack(Material.matchMaterial(key));
                    if (item == null)
                        item = new ItemStack(Material.AIR);
                    return item;
                }
                return item;
            case "d":
                item = plugin.Recipes.getConfig().getItemStack("recipes." + id + ".d");
                if (item == null) {
                    String key = plugin.Recipes.getConfig().getString("recipes." + id + ".d");
                    item = new ItemStack(Material.matchMaterial(key));
                    if (item == null)
                        item = new ItemStack(Material.AIR);
                    return item;
                }
                return item;
            case "e":
                item = plugin.Recipes.getConfig().getItemStack("recipes." + id + ".e");
                if (item == null) {
                    String key = plugin.Recipes.getConfig().getString("recipes." + id + ".e");
                    item = new ItemStack(Material.matchMaterial(key));
                    if (item == null)
                        item = new ItemStack(Material.AIR);
                    return item;
                }
                return item;
            case "f":
                item = plugin.Recipes.getConfig().getItemStack("recipes." + id + ".f");
                if (item == null) {
                    String key = plugin.Recipes.getConfig().getString("recipes." + id + ".f");
                    item = new ItemStack(Material.matchMaterial(key));
                    if (item == null)
                        item = new ItemStack(Material.AIR);
                    return item;
                }
                return item;
            case "g":
                item = plugin.Recipes.getConfig().getItemStack("recipes." + id + ".g");
                if (item == null) {
                    String key = plugin.Recipes.getConfig().getString("recipes." + id + ".g");
                    item = new ItemStack(Material.matchMaterial(key));
                    if (item == null)
                        item = new ItemStack(Material.AIR);
                    return item;
                }
                return item;
            case "h":
                item = plugin.Recipes.getConfig().getItemStack("recipes." + id + ".h");
                if (item == null) {
                    String key = plugin.Recipes.getConfig().getString("recipes." + id + ".h");
                    item = new ItemStack(Material.matchMaterial(key));
                    if (item == null)
                        item = new ItemStack(Material.AIR);
                    return item;
                }
                return item;
            case "i":
                item = plugin.Recipes.getConfig().getItemStack("recipes." + id + ".i");
                if (item == null) {
                    String key = plugin.Recipes.getConfig().getString("recipes." + id + ".i");
                    item = new ItemStack(Material.matchMaterial(key));
                    if (item == null)
                        item = new ItemStack(Material.AIR);
                    return item;
                }
                return item;
        }
        return null;
    }
    public String setRow(String s1, String s2, String s3){
        if (hasIngredient(s1) && hasIngredient(s2) && hasIngredient(s3))
            return s1+s2+s3;
        if (hasIngredient(s1) && hasIngredient(s2) && !hasIngredient(s3))
            return s1 + s2 + " ";
        if (!hasIngredient(s1) && hasIngredient(s2) && hasIngredient(s3))
            return " " + s2+s3;
        if (hasIngredient(s1) && !hasIngredient(s2) && hasIngredient(s3))
            return s1+" " + s3;
        if (hasIngredient(s1) && !hasIngredient(s2) && !hasIngredient(s3))
            return s1 + "  ";
        if (!hasIngredient(s1) && hasIngredient(s2) && !hasIngredient(s3))
            return " " + s2 + " ";
        if (!hasIngredient(s1) && !hasIngredient(s2) && hasIngredient(s3))
            return "  " + s3;
        return "   ";
    }
    private boolean hasRecipe(){
        int i = 0;
        if (hasIngredient("a"))
            i++;
        if (hasIngredient("b"))
            i++;
        if (hasIngredient("c"))
            i++;
        if (hasIngredient("d"))
            i++;
        if (hasIngredient("e"))
            i++;
        if (hasIngredient("f"))
            i++;
        if (hasIngredient("g"))
            i++;
        if (hasIngredient("h"))
            i++;
        if (hasIngredient("i"))
            i++;
        return i >= 1;
    }
    public void createRecipe(String lens){
        if (!hasRecipe())
            return;
        NamespacedKey key = new NamespacedKey(plugin, lens);
        ShapedRecipe recipe = new ShapedRecipe(key, getResult());
        recipe.shape(setRow("a","b","c"), setRow("d","e","f"), setRow("g", "h", "i"));
        if (hasIngredient("a"))
            recipe.setIngredient('a', new RecipeChoice.ExactChoice(getIngrediant("a")));
        if (hasIngredient("b"))
            recipe.setIngredient('b', new RecipeChoice.ExactChoice(getIngrediant("b")));
        if (hasIngredient("c"))
            recipe.setIngredient('c', new RecipeChoice.ExactChoice(getIngrediant("c")));
        if (hasIngredient("d"))
            recipe.setIngredient('d', new RecipeChoice.ExactChoice(getIngrediant("d")));
        if (hasIngredient("e"))
            recipe.setIngredient('e', new RecipeChoice.ExactChoice(getIngrediant("e")));
        if (hasIngredient("f"))
            recipe.setIngredient('f', new RecipeChoice.ExactChoice(getIngrediant("f")));
        if (hasIngredient("g"))
            recipe.setIngredient('g', new RecipeChoice.ExactChoice(getIngrediant("g")));
        if (hasIngredient("h"))
            recipe.setIngredient('h', new RecipeChoice.ExactChoice(getIngrediant("h")));
        if (hasIngredient("i"))
            recipe.setIngredient('i', new RecipeChoice.ExactChoice(getIngrediant("i")));
        if (Bukkit.getRecipe(key) == null)
            Bukkit.addRecipe(recipe);
        else{
            Bukkit.removeRecipe(key);
            Bukkit.addRecipe(recipe);
        }
    }


}
