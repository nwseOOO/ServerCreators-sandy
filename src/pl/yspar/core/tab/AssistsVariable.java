package pl.yspar.core.tab;

import codecrafter47.bungeetablistplus.api.bukkit.*;
import pl.yspar.core.basic.User;
import pl.yspar.core.manager.UserManager;

import org.bukkit.entity.*;


public class AssistsVariable extends Variable
{
    public AssistsVariable(final String name) {
        super(name);
    }
    
    public String getReplacement(final Player player) {
        final User u = User.get(player);
        int points = 0;
        if (u != null) {
            points = u.getAsyst();
        }
        return Integer.toString(points);
    }
}
