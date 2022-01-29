package me.stephenminer.ItemBuilder;

import me.stephenminer.ItemBuilder.builder.RecipeBuilder;
import me.stephenminer.ItemBuilder.commands.AutoComplete;
import me.stephenminer.ItemBuilder.commands.CreateItem;
import me.stephenminer.ItemBuilder.commands.CreateItemCompleter;
import me.stephenminer.ItemBuilder.commands.ItemBuilderCmds;
import me.stephenminer.ItemBuilder.inventories.InventoryEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin {
    public ConfigFiles Recipes;
    public ConfigFiles Items;

    @Override
    public void onEnable(){
        Recipes = new ConfigFiles(this, "recipes");
        Items = new ConfigFiles(this, "items");
        registerCommands();
        addRecipes();
        registerEvents();

    }
    @Override
    public void onDisable(){
        Recipes.saveConfig();
        Items.saveConfig();
    }
    private void registerCommands(){
        getCommand("itembuilder").setExecutor(new ItemBuilderCmds(this));
        getCommand("itembuilder").setTabCompleter(new AutoComplete(this));
        getCommand("createitem").setExecutor(new CreateItem(this));
        getCommand("createitem").setTabCompleter(new CreateItemCompleter());

    }
    private void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new InventoryEvents(this), this);

    }
    private void addRecipes(){
        new BukkitRunnable(){
            final Main plugin = Main.this;
            @Override
            public void run(){
                if (!Recipes.getConfig().contains("recipes"))
                    return;
                for (String id : Recipes.getConfig().getConfigurationSection("recipes").getKeys(false)){
                    if (id == null || id.isEmpty())
                        continue;
                    RecipeBuilder rb = new RecipeBuilder(plugin, id);
                    rb.createRecipe(id);
                }
            }
        }.runTaskLater(this, 2);

    }

}
