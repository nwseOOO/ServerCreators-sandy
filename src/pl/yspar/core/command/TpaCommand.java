package pl.yspar.core.command;


import org.bukkit.entity.*;

import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.*;


import pl.yspar.core.basic.User;
import pl.yspar.core.manager.TpaManager;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.StringUtil;
import pl.yspar.core.utils.Util;


public class TpaCommand extends PlayerCommand
{
    public TpaCommand() {
        super("tpa", "informacje o gildii", "/incognito", "core.cmd.user", new String[] { "ic" });
    }
    
    @Override
    public boolean onCommand(final Player player, final String[] args) {
        if (args.length <= 0) {
			return Util.sendMessage(player, "/tpa gracz");
        }
		User player2;
		if ((player2 = User.get(args[0])) == null) {
			return Util.sendMessage(player, "Gracz jest offline");
		}
        if (player2.getTpaRequest() == player.getName()) {
            return Util.sendMessage((CommandSender)player, "&3Wyslales juz prosbe o teleportacje do tego gracza!");
        }
        User u2 = User.get(player2);
        u2.setlastPosition(player2.getlastPositionLocation());
        TpaManager.getInst().send(player.getName(), player2.getName());
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "ge execute all $bcproxy " + player2.getName() + " &7\n&bOtrzymales prosbe o teleportacje od gracza &6" + player.getName() + "\n&bAby akceptowac wpisz &6/tpaccept\n&bAby odrzucic wpisz &6/tpdeny");
        Util.sendMessage((CommandSender)player, StringUtil.replace(ChatUtil.fixColor("&bWyslano prosbe o teleportacje do gracza &6{PLAYER}&b! &2X: " + u2.getlastPosition() + " " ), "{PLAYER}", player2.getName()));
        return false;
    }
    

}