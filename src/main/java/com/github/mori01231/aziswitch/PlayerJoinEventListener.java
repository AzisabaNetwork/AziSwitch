package com.github.mori01231.aziswitch;

import com.github.mori01231.aziswitch.configManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class PlayerJoinEventListener implements Listener {
    private final String pluginName =  AziSwitch.getInstance().getDescription().getPrefix();
    private final String servername = configManager.getServerName();
    private List<String> singleServerGroups;
    private List<String> allServerGroups;
    private List<String> OPs;

    @EventHandler
    public void PlayerJoinEventHandler(PlayerJoinEvent event){
        OPs = configManager.getOPs();
        Player player = event.getPlayer();
        if(!OPs.contains(String.valueOf(player.getUniqueId())) ) return;

        singleServerGroups = configManager.getSingleServerGroups();
        allServerGroups = configManager.getAllServerGroups();

        Boolean hasGroup = false;
        for(String group : singleServerGroups) if(player.hasPermission("aziswitch.is" + group + " server=" + servername)) hasGroup = true;
        for(String group : allServerGroups) if(player.hasPermission("aziswitch.is" + group)) hasGroup = true;
        if(!hasGroup) return;

        OPs.remove(String.valueOf(player.getUniqueId()));
        configManager.setOPs(OPs);
        player.setOp(true);
        sendPlayerMessage(player,"&4OP&3を&a付与&3しました。");
    }

    public void sendPlayerMessage(Player player, String message){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7[&b"+pluginName+"&7]&r "+message ));
    }
}
