package com.github.mori01231.aziswitch;

import com.github.mori01231.aziswitch.configManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class SwitchCommandExecutor implements CommandExecutor {

    private final String pluginName =  AziSwitch.getInstance().getDescription().getPrefix();
    private final String servername = configManager.getServerName();
    private List<String> singleServerGroups;
    private List<String> allServerGroups;
    private List<String> OPs;

    private Player player;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        singleServerGroups = configManager.getSingleServerGroups();
        allServerGroups = configManager.getAllServerGroups();
        OPs = configManager.getOPs();

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
        Boolean hasSwitchGroup = false;
        Boolean hasIsGroup = false;

        // Build a list with all groups
        for (String group : allGroups) {
            if (sender.hasPermission("aziswitch.switch" + group)){
                hasSwitchGroup = true;
            }
            if (sender.hasPermission("aziswitch.is" + group)){
                hasIsGroup = true;
            }
        }

        // Check if player has a switchable group
        if(!hasSwitchGroup&&!hasIsGroup){
            sender.sendMessage(ChatColor.translateAlternateColorCodes
                    ('&',"&4権限不足です。\n&3Mori01231#9559のDMにこのメッセージのスクショをもって泣きつきましょう！" ));
            return true; // Don't have the permissions? Sucks for you.
        }

        if(hasSwitchGroup) {
            // Call all functions to switch groups
            for (String group : singleServerGroups) SwitchFromMemberInServer(player, group);
            for (String group : allServerGroups) SwitchFromMember(player, group);
            addOP(player);
        }
        if(hasIsGroup){
            removeOP(player);
            // Call all functions to switch groups
            for (String group : singleServerGroups) SwitchToMemberInServer(player, group);
            for (String group : allServerGroups) SwitchToMember(player, group);
        }

        return true; // Correctly switched groups.
    }


    public void SwitchFromMember(Player player, String group){
        if (player.hasPermission("aziswitch.switch" + group) && !player.hasPermission("aziswitch.is" + group)){
            sendCommand("lp u " + player.getName() + " parent add " + group);
            sendPlayerMessage(player, "&c" + group + "モード&3になりました。");
            sendCommand("lp u " + player.getName() + " parent remove switch" + group);
        }
    }

    public void SwitchToMember(Player player, String group){
        if (player.hasPermission("aziswitch.is" + group)){
            sendCommand("lp u " + player.getName() + " parent add switch" + group);
            sendPlayerMessage(player, "&7Memberモード&3になりました。");
            sendCommand("lp u " + player.getName() + " parent remove " + group);
        }
    }

    public void SwitchFromMemberInServer(Player player, String group){
        if (player.hasPermission("aziswitch.switch" + group) && !player.hasPermission("aziswitch.is" + group + " server=" + servername)){
            sendCommand("lp u " + player.getName() + " parent add " + group + " server=" + servername);
            sendPlayerMessage(player, "&c" + group + "モード&3になりました。");
            sendCommand("lp u " + player.getName() + " parent remove switch" + group + " server=" + servername);
        }
    }

    public void SwitchToMemberInServer(Player player, String group){
        if (player.hasPermission("aziswitch.is" + group)){
            sendCommand("lp u " + player.getName() + " parent add switch" + group + " server=" + servername);
            sendPlayerMessage(player, "&7Memberモード&3になりました。");
            sendCommand("lp u " + player.getName() + " parent remove " + group + " server=" + servername);
        }
    }

    private void addOP(Player player){
        if(!OPs.contains(String.valueOf(player.getUniqueId())) ) return;
        OPs.remove(String.valueOf(player.getUniqueId()));
        configManager.setOPs(OPs);
        player.setOp(true);
        sendPlayerMessage(player,"&4OP&3を&a付与&3しました。");
        System.out.println(OPs);
    }

    private void removeOP(Player player){
        if (!player.isOp()) return;
        if(!OPs.contains(String.valueOf(player.getUniqueId())) ) {
            OPs.add(String.valueOf(player.getUniqueId()));
            configManager.setOPs(OPs);
        }
        player.setOp(false);
        sendPlayerMessage(player,"&4OP&3を&c削除&3しました。");
        System.out.println(OPs);
    }

    public void sendCommand(String command){
        getServer().dispatchCommand(getServer().getConsoleSender(), command);
    }

    public void sendPlayerMessage(Player player, String message){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7[&b"+pluginName+"&7]&r "+message ));
    }
}
