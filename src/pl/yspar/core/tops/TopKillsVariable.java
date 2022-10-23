package pl.yspar.core.tops;

import codecrafter47.bungeetablistplus.api.bukkit.*;
import pl.yspar.core.manager.KillManager;

import org.bukkit.entity.*;


public class TopKillsVariable extends Variable
{
    private int i;
    
    public TopKillsVariable(final String name, final int i) {
        super(name);
        this.i = i;
    }
    
    public String getReplacement(final Player p) {
        if (KillManager.getKills().size() >= this.i) {
            String s = "&7#" + this.i + "&8. &7";
            if (this.i > 9) {
                s = "&7#" + this.i + "&8. &7";
            }
            return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s))))) + "&6" + KillManager.getKills().get(this.i - 1).getName() + "   &7(&f" + KillManager.getKills().get(this.i - 1).getKills() + "&7)";
        }
        return "&7#" + this.i + "&8. ";
    }
}
