package com.github.mori01231.aziswitch;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class NewSwitchGroupCommandExecutor implements CommandExecutor {

    List<String> singleServerGroups = AziSwitch.getInstance().getConfig().getStringList("SingleServerGroups");
    List<String> allServerGroups = AziSwitch.getInstance().getConfig().getStringList("AllServerGroups");


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // First argument is the group name.
        String groupName = args[0];

        // Initial variable to indicate mode.
        String creationMode = "all";

        // Determine mode
        if(args.length >= 2){
            // Set creation mode to single if the second argument is "single"
            if(args[1].equalsIgnoreCase("single")){
                creationMode = "single";
            }

            // if the second argument is invalid, give an error and return
            else if(!args[1].equalsIgnoreCase("all")){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cモードはsingleもしくはallで設定してください。" ));
                return true;
            }
        }

        // Make a group a switch group for a single server.
        if(creationMode.equalsIgnoreCase("single")){
            // Get the current list of groups for a single server
            List<String> singleServerGroups = AziSwitch.getInstance().getConfig().getStringList("SingleServerGroups");
            singleServerGroups.add(groupName);
            AziSwitch.getInstance().getConfig().set("SingleServerGroups", singleServerGroups);
        }
        else{
            List<String> allServerGroups = AziSwitch.getInstance().getConfig().getStringList("AllServerGroups");
            allServerGroups.add(groupName);
            AziSwitch.getInstance().getConfig().set("AllServerGroups", allServerGroups);
        }

        return true;
    }
}
