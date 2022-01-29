package me.stephenminer.ItemBuilder.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CreateItemCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("createitem")){
            int size = args.length;
            if (size < 2)
                return null;
            return materialCompleter();
        }
        return null;
    }
    private List<String> materialCompleter(){
        Material[] materials = Material.values();
        List<String> matList = new ArrayList<>();
        for (Material material : materials){
            matList.add(material.name());
        }
        return matList;
    }
}
