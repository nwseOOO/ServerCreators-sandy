package pl.yspar.core.tab;

import codecrafter47.bungeetablistplus.api.bukkit.*;
import pl.yspar.core.basic.User;
import pl.yspar.core.manager.UserManager;

import org.bukkit.entity.*;


public class KDVariable extends Variable
{
    public KDVariable(final String name) {
        super(name);
    }
    
    public String getReplacement(final Player player) {
        final User u = User.get(player);
        return (u == null) ? "" : new StringBuilder().append(u.getKd()).toString();
    }
}

