package com.github.mori01231.aziswitch;

import org.bukkit.configuration.Configuration;


import java.util.List;

public class configManager {
    private static AziSwitch plugin = AziSwitch.getInstance();
    private static String servername;
    private static List<String> singleServerGroups;
    private static List<String> allServerGroups;
    private static List<String> OPs;
    private static Configuration config = plugin.getConfig();


    public static void loadConfig(){
        servername = config.getString("lp-server-name");
        singleServerGroups = config.getStringList("SingleServerGroups");
        allServerGroups = config.getStringList("AllServerGroups");
        OPs = config.getStringList("OPs");
    }

    public static String getServerName(){
        return servername;
    }

    public static List<String> getSingleServerGroups(){
        return singleServerGroups;
    }

    public static List<String> getAllServerGroups(){

        return allServerGroups;
    }

    public static List<String> getOPs(){
        return OPs;
    }

    public static void setSingleServerGroups(List<String> Lists){
        singleServerGroups = Lists;
        config.set("SingleServerGroups", singleServerGroups);
        plugin.saveConfig();
    }

    public static void setAllServerGroups(List<String> Lists){
        allServerGroups = Lists;
        config.set("AllServerGroups", allServerGroups);
        plugin.saveConfig();
    }

    public static void setOPs(List<String> Lists){
        OPs = Lists;
        config.set("OPs", OPs);;
        plugin.saveConfig();
    }
}
