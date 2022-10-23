package pl.yspar.core.command;


import org.bukkit.entity.*;

import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;

import org.bukkit.inventory.*;


import pl.yspar.core.basic.User;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.StringUtil;
import pl.yspar.core.utils.Util;


public class TpCommand extends PlayerCommand
{
    public TpCommand() {
        super("tp", "informacje o gildii", "/incognito", "core.cmd.admin", new String[] { "teleport" });
    }
    

    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
		if (p instanceof Player) {
			final Player player = (Player) p;
			switch (args.length) {

			case 1: {
				final Player player2;
				if ((player2 = Bukkit.getPlayer(args[0])) == null) {
					final boolean b = false;
					p.sendMessage("Brak gracza");
					return b;
				}
				player.teleport((Entity) player2);
				player.sendMessage(StringUtil.replace(ChatUtil.fixColor("&6Przeteleportowano do &7{PLAYER}"), "{PLAYER}", player2.getName()));
				return true;
			}
			case 2: {
				if (!p.hasPermission("Core.command.others.tp.other")) {
					final boolean b2 = false;

					return b2;
				}
				final Player player2;
				if ((player2 = Bukkit.getPlayer(args[0])) == null) {
					final boolean b3 = false;
					p.sendMessage("Brak gracza");
					return b3;
				}
				final Player player3;
				if ((player3 = Bukkit.getPlayer(args[1])) == null) {
					final boolean b4 = false;
					p.sendMessage("Brak gracza");
					return b4;
				}
				player2.teleport((Entity) player3);
				String message = StringUtil.replace(message = StringUtil.replace(ChatUtil.fixColor("&6Tepnieto &7{PLAYER1} &6do &7{PLAYER2}"),
						"{PLAYER1}", player2.getName()), "{PLAYER2}", player3.getName());
				p.sendMessage(message);
				return true;
			}

			case 4: {
				if (!p.hasPermission("permisjazmien")) {
					final boolean b5 = false;
					// Util.sendPermission(p,
					// Settings.K("\u001aH+BwD6J4F7CwH-O<U*\t-WwH-O<U"));
					return b5;
				}

				final Player player2;
				if ((player2 = Bukkit.getPlayer(args[0])) == null) {
					final boolean b6 = false;
					player.sendMessage("Brak gracza");
					return b6;
				}
			}
			default: {
				// Util.sendUsage(p, Settings.K("\b") + command +
				// Settings.K("\u0007\u0002I0D2z"));
				break;
			}
			}
		} else {
			switch (args.length) {
			case 2: {
				final Player player4;
				if ((player4 = Bukkit.getPlayer(args[0])) == null) {
					final boolean b8 = false;
					p.sendMessage("Brak gracza");
					return b8;
				}
				final Player player5;
				if ((player5 = Bukkit.getPlayer(args[1])) == null) {
					final boolean b9 = false;
					p.sendMessage("Brak gracza");
					return b9;
				}
				player4.teleport((Entity) player5);
				String message2 = StringUtil.replace(message2 = StringUtil
						.replace(ChatUtil.fixColor("&6Przeteleportowano gracza &7{PLAYER1} &6do &7{PLAYER2}"), "{PLAYER1}", player4.getName()), "{PLAYER2}",
						player5.getName());
				p.sendMessage(message2);
				return true;
			}

			default: {
				Util.sendMessage(p, "/tp [nick] [x] [y] [z]");
				break;
			}
			}
		}
		return false;
	}

}