package pl.yspar.core.command;


import org.bukkit.entity.*;

import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;

import org.bukkit.inventory.*;

import pl.yspar.core.basic.User;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;


public class ApiCommand extends PlayerCommand
{
    public ApiCommand() {
        super("$api", "informacje o gildii", "/incognito", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final User u = User.get(p);
        if (args[0].equalsIgnoreCase("krety_atakujacy")) {
            u.setKrety(ChatUtil.fixColor("&9Atak"));
            return true;
        }
        if (args[0].equalsIgnoreCase("krety_broniacy")) {
            u.setKrety(ChatUtil.fixColor("&eObrona"));
            return true;
        }
        if (args[0].equalsIgnoreCase("krety_usun")) {
            u.setKrety("");
            return true;
        }
        return true;
    }
            
    

}