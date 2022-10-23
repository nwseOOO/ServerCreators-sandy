package pl.yspar.core.listener;


import org.bukkit.command.*;
import org.bukkit.entity.*;

import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;

import pl.yspar.core.Config;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.User;
import pl.yspar.core.manager.GuildManager;
import pl.yspar.core.manager.TagManager;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.TitleAPI;
import pl.yspar.core.utils.Util;

import org.bukkit.*;

public class JoinQuitListener implements Listener
{
    @EventHandler(priority = EventPriority.LOW)
    public void onJoin(final PlayerJoinEvent e) {
        e.setJoinMessage((String)null);
        final Player p = e.getPlayer();
        if (p.hasPlayedBefore() ==  false) {
        	Bukkit.broadcastMessage(ChatUtil.fixColor("&8&l» &7Witamy &6" + p.getName() + " &7Pierwszy raz na trybie sandy!"));
        	return;
        }
        UserManager.joinPlayer(p);
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, "&8&m-[&e&m------&6&l SANDY &e&m------&8&m]-");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " &8&l» &fPołączono z&8: &esandy");
        ChatUtil.sendMessage((CommandSender)p, " &8&l» &fGraczy na trybie&8: &a" + Bukkit.getOnlinePlayers().size());
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, "&8&m-[&e&m------&6&l SANDY &e&m------&8&m]-");
        final Guild x = User.get(p).getGuild();
        final User u = User.get(p);
        UserManager.getJoinRank(p);
        TitleAPI.sendTitle(p, 0, 40, 0, "", ChatUtil.fixColor("&8→&7→ &6" + Config.TRYB + " &7←&8←"));
        if (u.isVanish() ==  true) {
            TitleAPI.sendTitle(p, 0, 30, 0, "", Util.fixColor("&6Vanish na tym trybie jest &aWlaczony"));
            for (final Player online : Bukkit.getServer().getOnlinePlayers()) {
                if (online.hasPermission("core.cmd.vanish")) {
                    continue;
                }
                online.hidePlayer(p);
            }
        }


    }
    
    
    @EventHandler
    public void onQuit(final PlayerQuitEvent e) {
        final Location loc = e.getPlayer().getLocation();
        e.setQuitMessage((String)null);
        final Player p = e.getPlayer();
        UserManager.quitPlayer(p);
        return;
    }
}

