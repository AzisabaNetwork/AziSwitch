package com.github.mori01231.aziswitch;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class SwitchCommandExecutor implements CommandExecutor {

    private final String servername = AziSwitch.getInstance().getConfig().getString("lp-server-name");
    private final List singleServerGroups = AziSwitch.getInstance().getConfig().getStringList("SingleServerGroups");
    private final List allServerGroups = AziSwitch.getInstance().getConfig().getStringList("AllServerGroups");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player){

            Player player = (Player) sender;

            //NO PERMISSION

            if(!(sender.hasPermission("aziswitch.switchmoderator") || sender.hasPermission("aziswitch.switchadminmember")|| sender.hasPermission("aziswitch.switchadmin") || sender.hasPermission("aziswitch.switchdeveloper") || sender.hasPermission("aziswitch.switchowner")
                    || sender.hasPermission("aziswitch.ismoderator") || sender.hasPermission("aziswitch.isadminmember") || sender.hasPermission("aziswitch.isadmin") || sender.hasPermission("aziswitch.isdeveloper") || sender.hasPermission("aziswitch.isowner"))){

                sender.sendMessage(ChatColor.translateAlternateColorCodes
                        ('&',"&4権限不足です。\n&3Mori01231#9559のDMにこのメッセージのスクショをもって泣きつきましょう！" ));
                return true;
            }


            //CHANGE TO ADMIN MODE

            //member to builder
            if (sender.hasPermission("aziswitch.switchbuilder") && !sender.hasPermission("aziswitch.isbuilder")){

                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent add builder server=" + servername);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&3Builderモードになりました。" ));
                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent remove switchbuilder server=" + servername);
            }

            //member to moderator
            if (sender.hasPermission("aziswitch.switchmoderator") && !sender.hasPermission("aziswitch.ismoderator")){

                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent add moderator server=" + servername);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&3Moderatorモードになりました。" ));
                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent remove switchmoderator server=" + servername);
            }

            //member to admin
            if (sender.hasPermission("aziswitch.switchadmin") && !sender.hasPermission("aziswitch.isadmin")){

                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent add admin server=" + servername);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&3Adminモードになりました。" ));
                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent remove switchadmin server=" + servername);
            }

            //member to adminmember
            if (sender.hasPermission("aziswitch.switchadminmember") && !sender.hasPermission("aziswitch.isadminmember")){

                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent add adminmember server=" + servername);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&3AdminMemberモードになりました。" ));
                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent remove switchadminmember server=" + servername);
            }

            //member to developer
            if (sender.hasPermission("aziswitch.switchdeveloper") && !sender.hasPermission("aziswitch.isdeveloper")){

                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent add developer");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&3Developerモードになりました。" ));
                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent remove switchdeveloper");
            }

            //member to owner
            if (sender.hasPermission("aziswitch.switchowner") && !sender.hasPermission("aziswitch.isowner")){

                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent add owner");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&3Ownerモードになりました。" ));
                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent remove switchowner");

            }




            //CHANGE TO MEMBER MODE

            //builder to member
            if (sender.hasPermission("aziswitch.isbuilder")){

                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent add switchbuilder server=" + servername);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&3Memberモードになりました。" ));
                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent remove builder server=" + servername);

            }

            //moderator to member
            if (sender.hasPermission("aziswitch.ismoderator")){

                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent add switchmoderator server=" + servername);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&3Memberモードになりました。" ));
                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent remove moderator server=" + servername);

            }

            //admin to member
            if (sender.hasPermission("aziswitch.isadmin")){

                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent add switchadmin server=" + servername);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&3Memberモードになりました。" ));
                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent remove admin server=" + servername);

            }

            //adminmember to member
            if (sender.hasPermission("aziswitch.isadminmember")){

                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent add switchadminmember server=" + servername);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&3Memberモードになりました。" ));
                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent remove adminmember server=" + servername);

            }

            //developer to member
            if (sender.hasPermission("aziswitch.isdeveloper")){

                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent add switchdeveloper");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&3Memberモードになりました。" ));
                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent remove developer");

            }

            //owner to member
            if (sender.hasPermission("aziswitch.isowner")){

                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent add switchowner");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&3Memberモードになりました。" ));
                getServer().dispatchCommand(getServer().getConsoleSender(), "lp u " + player.getName() + " parent remove owner");

            }


        }
        else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&3このコマンドはコンソールから使用できません。" ));
        }

        return true;
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
            sendCommand("lp u " + player.getName() + " parent add " + group);
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
