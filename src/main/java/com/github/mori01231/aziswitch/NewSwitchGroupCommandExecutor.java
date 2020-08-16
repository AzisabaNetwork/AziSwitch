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
        if(args[1].equalsIgnoreCase("single")){
            List<String> singleServerGroups = AziSwitch.getInstance().getConfig().getStringList("SingleServerGroups");
        }
        else{
            List<String> allServerGroups = AziSwitch.getInstance().getConfig().getStringList("AllServerGroups");
        }

        return true;
    }
}
