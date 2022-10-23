package pl.yspar.core.tops;


import codecrafter47.bungeetablistplus.api.bukkit.*;
import pl.yspar.core.manager.AssistManager;
import pl.yspar.core.manager.PrestizManager;

import org.bukkit.entity.*;


public class TopPrestizVariable extends Variable
{
    private int i;
    
    public TopPrestizVariable(final String name, final int i) {
        super(name);
        this.i = i;
    }
    
    public String getReplacement(final Player p) {
        if (PrestizManager.getprestiz().size() >= this.i) {
            String s = "&7#" + this.i + "&8. &7";
            if (this.i > 9) {
                s = "&7#" + this.i + "&8. &7";
            }
            return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s))))) + "&6" + PrestizManager.getprestiz().get(this.i - 1).getName() + "   &7(&f" + PrestizManager.getprestiz().get(this.i - 1).getPrestiz() + "&7)";
        }
        return "&7#" + this.i + "&8. ";
    }
}
