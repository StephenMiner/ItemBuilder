package me.stephenminer.ItemBuilder.commands;

import me.stephenminer.ItemBuilder.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateItem implements CommandExecutor {
    private Main plugin;
    public CreateItem(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("createitem")){
            int size = args.length;
            if (sender instanceof Player){
                Player player = (Player) sender;
                if (!player.hasPermission("itembuilder.commands.create"))
                    return false;
            }
            if (size < 2) {
                sender.sendMessage(ChatColor.GREEN + "");
                return false;
            }
            String id = args[0];
            String tempmat = args[1];
            Material mat = Material.matchMaterial(tempmat);
            if (mat == null)
                return false;
            createItemEntry(id, mat);
            sender.sendMessage(ChatColor.GREEN + "Created Item! To check out customizations, type /itembuilder and check out the autocompleter!");
            return true;
        }
        return false;
    }

    private boolean createItemEntry(String id, Material material){
        if (plugin.Items.getConfig().contains("items." + id))
            return false;
        plugin.Items.getConfig().set("items." + id + ".material", material.name());
        plugin.Items.saveConfig();
        return true;

    }
}
