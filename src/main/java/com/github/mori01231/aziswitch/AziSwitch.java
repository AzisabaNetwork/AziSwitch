package com.github.mori01231.aziswitch;

import org.bukkit.plugin.java.JavaPlugin;

public final class AziSwitch extends JavaPlugin {

    private static AziSwitch instance;

    public AziSwitch (){
        instance = this;
    }

    public static AziSwitch getInstance() {
        return instance;
    }


    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("AziSwitch has been enabled.");
        this.getCommand("switch").setExecutor(new SwitchCommandExecutor());
        this.getCommand("newswitchgroup").setExecutor(new NewSwitchGroupCommandExecutor());

        this.saveDefaultConfig();

    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("AziSwitch has been disabled.");
    }
}
