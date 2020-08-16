package com.github.mori01231.aziswitch;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class SwitchCommandExecutor implements CommandExecutor {

    private final String servername = AziSwitch.getInstance().getConfig().getString("lp-server-name");
    private final List<String> singleServerGroups = AziSwitch.getInstance().getConfig().getStringList("SingleServerGroups");
    private final List<String> allServerGroups = AziSwitch.getInstance().getConfig().getStringList("AllServerGroups");

    private Player player;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        // Check if the sender is a player
        if (sender instanceof Player){
            player = (Player) sender;
        }
        else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&3このコマンドはコンソールから使用できません。" ));
            return true;
        }

        // Prepare a list with all groups
        List<String> allGroups = new ArrayList<>();
        allGroups.addAll(allServerGroups);
        allGroups.addAll(singleServerGroups);
        // Initialize boolean to check if a player has a switchable group
        Boolean hasGroup = false;

        // Build a list with all groups
        for (String group : allGroups) {
            if (sender.hasPermission("aziswitch.switch" + group)){
                hasGroup = true;
            }
            if (sender.hasPermission("aziswitch.is" + group)){
                hasGroup = true;
            }
        }

        // Check if player has a switchable group
        if(!hasGroup){
            sender.sendMessage(ChatColor.translateAlternateColorCodes
                    ('&',"&4権限不足です。\n&3Mori01231#9559のDMにこのメッセージのスクショをもって泣きつきましょう！" ));
            return true; // Don't have the permissions? Sucks for you.
        }

        // Call all functions to switch groups
        for (String group : singleServerGroups) {
            SwitchFromMemberInServer(player, group);
        }
        for (String group : allServerGroups) {
            SwitchFromMember(player, group);
        }
        for (String group : singleServerGroups) {
            SwitchToMemberInServer(player, group);
        }
        for (String group : allServerGroups) {
            SwitchToMember(player, group);
        }

        return true; // Correctly switched groups.
    }


    public void SwitchFromMember(Player player, String group){
        if (player.hasPermission("aziswitch.switch" + group) && !player.hasPermission("aziswitch.is" + group)){
            sendCommand("lp u " + player.getName() + " parent add " + group);
            sendPlayerMessage(player, "&3" + group + "モードになりました。");
            sendCommand("lp u " + player.getName() + " parent remove switch" + group);
        }
    }

    public void SwitchToMember(Player player, String group){
        if (player.hasPermission("aziswitch.is" + group)){
            sendCommand("lp u " + player.getName() + " parent add switch" + group);
            sendPlayerMessage(player, "&3Memberモードになりました。");
            sendCommand("lp u " + player.getName() + " parent remove " + group);
        }
    }

    public void SwitchFromMemberInServer(Player player, String group){
        if (player.hasPermission("aziswitch.switch" + group) && !player.hasPermission("aziswitch.is" + group + " server=" + servername)){
            sendCommand("lp u " + player.getName() + " parent add " + group + " server=" + servername);
            sendPlayerMessage(player, "&3" + group + "モードになりました。");
            sendCommand("lp u " + player.getName() + " parent remove switch" + group + " server=" + servername);
        }
    }

    public void SwitchToMemberInServer(Player player, String group){
        if (player.hasPermission("aziswitch.is" + group)){
            sendCommand("lp u " + player.getName() + " parent add switch" + group + " server=" + servername);
            sendPlayerMessage(player, "&3Memberモードになりました。");
            sendCommand("lp u " + player.getName() + " parent remove " + group + " server=" + servername);
        }
    }


    public void sendCommand(String command){
        getServer().dispatchCommand(getServer().getConsoleSender(), command);
    }

    public void sendPlayerMessage(Player player, String message){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',message ));
    }
}
