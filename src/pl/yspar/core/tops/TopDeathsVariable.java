package pl.yspar.core.tops;

import codecrafter47.bungeetablistplus.api.bukkit.*;
import pl.yspar.core.manager.DeathManager;

import org.bukkit.entity.*;


public class TopDeathsVariable extends Variable
{
    private int i;
    
    public TopDeathsVariable(final String name, final int i) {
        super(name);
        this.i = i;
    }
    
    public String getReplacement(final Player p) {
        if (DeathManager.getDeaths().size() >= this.i) {
            String s = "&7#" + this.i + "&8. &7";
            if (this.i > 9) {
                s = "&7#" + this.i + "&8. &7";
            }
            return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s))))) + "&6" + DeathManager.getDeaths().get(this.i - 1).getName() + "   &7(&f" + DeathManager.getDeaths().get(this.i - 1).getDeaths() + "&7)";
        }
        return "&7#" + this.i + "&8. ";
    }
}

