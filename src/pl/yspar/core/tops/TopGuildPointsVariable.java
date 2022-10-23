package pl.yspar.core.tops;

import org.bukkit.entity.*;

import codecrafter47.bungeetablistplus.api.bukkit.Variable;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.manager.RankingManager;
import pl.yspar.core.utils.ChatUtil;


public class TopGuildPointsVariable extends Variable
{
    private int i;
    
    public TopGuildPointsVariable(final String name, final int i) {
        super(name);
        this.i = i;
    }
    
    public String getReplacement(final Player player) {
        if (RankingManager.getGuildRankings().size() >= this.i) {
            final Guild guild = RankingManager.getGuildRankings().get(this.i - 1);
            String s = "&7#" + this.i + "&8. &7";
            if (this.i > 9) {
                s = "&7#" + this.i + "&8. &7";
            }
            return ChatUtil.fixColor(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s))))) + "   &6" + guild.getTag() + "    &7(&f" + guild.getPoints() + "&7)");
        }
        return "&7#" + this.i + "&8. ";
    }
}
