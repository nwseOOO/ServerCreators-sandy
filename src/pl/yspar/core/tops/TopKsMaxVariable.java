package pl.yspar.core.tops;

import codecrafter47.bungeetablistplus.api.bukkit.*;
import pl.yspar.core.manager.KillManager;
import pl.yspar.core.manager.KsMaxManager;

import org.bukkit.entity.*;


public class TopKsMaxVariable extends Variable
{
    private int i;
    
    public TopKsMaxVariable(final String name, final int i) {
        super(name);
        this.i = i;
    }
    
    public String getReplacement(final Player p) {
        if (KsMaxManager.getKills().size() >= this.i) {
            String s = "&7#" + this.i + "&8. &7";
            if (this.i > 9) {
                s = "&7#" + this.i + "&8. &7";
            }
            return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s))))) + "&6" + KsMaxManager.getKills().get(this.i - 1).getName() + "   &8[&7" + KsMaxManager.getKills().get(this.i - 1).getKills() + "&8]";
        }
        return "&7#" + this.i + "&8. ";
    }
}
