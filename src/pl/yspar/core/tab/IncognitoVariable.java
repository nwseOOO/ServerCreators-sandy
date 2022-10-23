package pl.yspar.core.tab;

import codecrafter47.bungeetablistplus.api.bukkit.*;
import pl.yspar.core.basic.User;
import pl.yspar.core.manager.UserManager;

import org.bukkit.entity.*;


public class IncognitoVariable extends Variable
{
    public IncognitoVariable(final String name) {
        super(name);
    }
    
    public String getReplacement(final Player player) {
        final User u = User.get(player);
        return (u == null) ? "" : new StringBuilder().append(u.isIncognito() ? "&aWlaczone" : "&cWylaczone").toString();
    }
}
