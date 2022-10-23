package pl.yspar.core.tops;

import codecrafter47.bungeetablistplus.api.bukkit.*;
import pl.yspar.core.manager.PrestizManager;
import pl.yspar.core.manager.RankingManager;

import org.bukkit.entity.*;


public class TopPointsVariable extends Variable
{
    private int i;
    
    public TopPointsVariable(final String name, final int i) {
        super(name);
        this.i = i;
    }
    
    public String getReplacement(final Player p) {
        if (RankingManager.getRankings().size() >= this.i && PrestizManager.getprestiz().size() >= this.i) {
            String s = "&7#" + this.i + "&8. &7";
            if (this.i > 9) {
                s = "&7#" + this.i + "&8. &7";
            }
            return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s))))) + "&6" + RankingManager.getRankings().get(this.i - 1).getName() + "   &7(&f" + RankingManager.getRankings().get(this.i - 1).getPoints() + "&7)" + " &7(&b✰ " + RankingManager.getRankings().get(this.i - 1).getPrestiz() + "&7)";
        }
        return "&7#" + this.i + "&8. ";
    }
}

