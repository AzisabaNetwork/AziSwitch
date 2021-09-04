package com.github.mori01231.aziswitch;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class NewSwitchGroupCommandExecutor implements CommandExecutor {
    private final String pluginName =  AziSwitch.getInstance().getDescription().getPrefix();

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
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7[&b"+pluginName+"&7]&r &cモードはsingleもしくはallで設定してください。" ));
                return true;
            }
        }

        // Make a group a switch group for a single server.
        if(creationMode.equalsIgnoreCase("single")){
            // Add the group to the list of single server groups in config.
            List<String> singleServerGroups = AziSwitch.getInstance().getConfig().getStringList("SingleServerGroups");
            singleServerGroups.add(groupName);
            configManager.setSingleServerGroups(singleServerGroups);

            createGroups(groupName);
        }

        // Make a group a switch group for all servers.
        else{
            // Add the group to the list of all server groups in config.
            List<String> allServerGroups = AziSwitch.getInstance().getConfig().getStringList("AllServerGroups");
            allServerGroups.add(groupName);
            configManager.setAllServerGroups(allServerGroups);

            createGroups(groupName);
        }

        return true;
    }

    public void createGroups(String groupName){
        sendCommand("lp creategroup " + groupName);
        sendCommand("lp g " + groupName + " permission set aziswitch.* false");
        sendCommand("lp g " + groupName + " permission set aziswitch.is" + groupName + " true");
        sendCommand("lp creategroup switch" + groupName);
        sendCommand("lp g switch" + groupName + " permission set aziswitch.* false");
        sendCommand("lp g switch" + groupName + " permission set aziswitch.switch" + groupName + " true");
    }

    public void sendCommand(String command){
        getServer().dispatchCommand(getServer().getConsoleSender(), command);
    }
}
