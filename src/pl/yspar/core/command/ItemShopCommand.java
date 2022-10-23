package pl.yspar.core.command;


import org.bukkit.entity.*;

import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.*;

import pl.yspar.core.basic.User;
import pl.yspar.core.gui.LobbyGui;
import pl.yspar.core.gui.ProfileGui;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.Util;


public class ItemShopCommand extends Command
{
    public ItemShopCommand() {
        super("is", "informacje o gildii", "/incognito", "core.cmd.admin", new String[0]);
    }
    

    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
    	 if (args.length < 2) {

             ChatUtil.sendMessage(sender, "   &e&m----&8&m]-&6&l SC &8&m]-&e&m----"); 
             ChatUtil.sendMessage(sender, " "); 
             ChatUtil.sendMessage(sender, " &6SPECJALNE");
             ChatUtil.sendMessage(sender, " &6SLOT");
             ChatUtil.sendMessage(sender, " &6PARTNER &7( Moze nadac tylko admin )");
             ChatUtil.sendMessage(sender, " &6YT &7( Moze nadac tylko admin )");
             ChatUtil.sendMessage(sender, " &6DISCO");
             ChatUtil.sendMessage(sender, " &6CASE");
             ChatUtil.sendMessage(sender, " &6UNBAN");
             ChatUtil.sendMessage(sender, " ");
             ChatUtil.sendMessage(sender, " ");
             ChatUtil.sendMessage(sender, " &6Aby nadac komus &7/is <usluga> <nick>");
             ChatUtil.sendMessage(sender, " ");
             ChatUtil.sendMessage(sender, "   &e&m----&8&m]-&6&l SC &8&m]-&e&m----"); 
            
             return true;
         }
         if (args[0].equalsIgnoreCase("specjalne")) {

        	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&f Server&6Creators &8&m-----"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &6Gracz &7" + args[1] + " &6zakupił usługę: &dSPECJALNE"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &6Dziekujemy za wsparcie naszego serwera!"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &cStrona internetowa: &6www.servercreators.pl"));
          	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&f Server&6Creators &8&m-----"));
           
          	 Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)("lp user " + args[1] + " parent set specjalne"));
             return true;
         }
         if (args[0].equalsIgnoreCase("partner")) {

        	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&f Server&6Creators &8&m-----"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &6Gracz &7" + args[1] + " &6dostał range: &ePARTNER"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &6Dziekujemy za wsparcie naszego serwera!"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &cStrona internetowa: &6www.servercreators.pl"));
          	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&f Server&6Creators &8&m-----"));
           
          	 Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)("lp user " + args[1] + " parent set partner"));
             return true;
         }
         if (args[0].equalsIgnoreCase("slot")) {

        	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&f Server&6Creators &8&m-----"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &6Gracz &7" + args[1] + " &6zakupił usługę: &7Rezerwacja slota"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &6Dziekujemy za wsparcie naszego serwera!"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &cStrona internetowa: &6www.servercreators.pl"));
          	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&f Server&6Creators &8&m-----"));
           
          	 Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)("lp user " + args[1] + " permission set slot true"));
             return true;
         }
         if (args[0].equalsIgnoreCase("disco")) {

        	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&f Server&6Creators &8&m-----"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &6Gracz &7" + args[1] + " &6zakupił usługę: &7Disco zbroja"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &6Dziekujemy za wsparcie naszego serwera!"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &cStrona internetowa: &6www.servercreators.pl"));
          	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&f Server&6Creators &8&m-----"));
           
          	 Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)("lp user " + args[1] + " permission set disco true"));
             return true;
         }
         if (args[0].equalsIgnoreCase("case")) {

        	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&f Server&6Creators &8&m-----"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &6Gracz &7" + args[1] + " &6zakupił usługę: &7x5 case"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &6Dziekujemy za wsparcie naszego serwera!"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &cStrona internetowa: &6www.servercreators.pl"));
          	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&f Server&6Creators &8&m-----"));
             return true;
         }
         if (args[0].equalsIgnoreCase("unban")) {

        	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&f Server&6Creators &8&m-----"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &6Gracz &7" + args[1] + " &6zakupił usługę: &7Unban"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &6Dziekujemy za wsparcie naszego serwera!"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &cStrona internetowa: &6www.servercreators.pl"));
          	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&f Server&6Creators &8&m-----"));
             return true;
         }
         if (args[0].equalsIgnoreCase("yt")) {

        	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&f Server&6Creators &8&m-----"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &6Gracz &7" + args[1] + " &6zostaje nowym &fYou&4Tuberem"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &6Dziekujemy za wsparcie naszego serwera!"));
          	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&f Server&6Creators &8&m-----"));
           
          	 Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)("lp user " + args[1] + " parent set yt"));
             return true;
         }
         if (args[0].equalsIgnoreCase("discord")) {

        	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&f Server&6Creators &8&m-----"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &6Gracz &7" + args[1] + " &6odebral darmowego &eVIPA &6na 1 dzien z naszego discorda! &7/nagrody"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &6Dziekujemy za wsparcie naszego serwera!"));
          	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&f Server&6Creators &8&m-----"));
           
          	 Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)("lp user " + args[1] + " parent set vip"));
             return true;
         }
		return false;
    }
            
    

}