package pl.yspar.core.command;


import org.bukkit.entity.*;

import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;

import org.bukkit.inventory.*;

import pl.yspar.core.basic.User;
import pl.yspar.core.gui.ProfileGui;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;


public class ProfileCommand extends PlayerCommand
{
    public ProfileCommand() {
        super("profil", "informacje o gildii", "/incognito", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length == 0) {
            final User u = User.get(p);
            if (u == null) {
                return true;
            }
            ProfileGui.open(p);
        }
        return true;
    }
            
    

}