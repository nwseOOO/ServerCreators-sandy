package pl.yspar.core.tops;

import codecrafter47.bungeetablistplus.api.bukkit.*;
import pl.yspar.core.manager.AssistManager;

import org.bukkit.entity.*;


public class TopAssistsVariable extends Variable
{
    private int i;
    
    public TopAssistsVariable(final String name, final int i) {
        super(name);
        this.i = i;
    }
    
    public String getReplacement(final Player p) {
        if (AssistManager.getAssists().size() >= this.i) {
            String s = "&f" + this.i + "&8. &7";
            if (this.i > 9) {
                s = "&f" + this.i + "&8. &7";
            }
            return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s))))) + AssistManager.getAssists().get(this.i - 1).getName() + " &7(&f" + AssistManager.getAssists().get(this.i - 1).getAsyst() + "&7)";
        }
        return "&f" + this.i + "&8. ";
    }
}
