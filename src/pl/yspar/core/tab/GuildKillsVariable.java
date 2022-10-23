package pl.yspar.core.tab;

import codecrafter47.bungeetablistplus.api.bukkit.*;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.User;
import pl.yspar.core.manager.GuildManager;

import org.bukkit.entity.*;


public class GuildKillsVariable extends Variable
{
    public GuildKillsVariable(final String name) {
        super(name);
    }
    
    public String getReplacement(final Player player) {
        final Guild g = User.get(player).getGuild();
        return (g == null) ? "&8---" : ("&c" + g.getKills());
    }
}
