package pl.yspar.core.command;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import org.apache.commons.lang.*;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.User;
import pl.yspar.core.command.PlayerCommand;
import pl.yspar.core.manager.GuildManager;
import pl.yspar.core.manager.RankingManager;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;

public class GraczCommand extends PlayerCommand
{
    public GraczCommand() {
        super("gracz", "informacje o gildii", "/gildia <tag>", "core.cmd.user", new String[] { "player", "ranking" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
		if (args.length == 0) {
			return ChatUtil.sendMessage(p, "&4Błąd: &cBrakujace argumenty (/gracz <nick>).");
		}
			User u;
		if ((u = User.get(args[0])) == null) {
				return ChatUtil.sendMessage(p, "&4Błąd: &cBrak gracza.");
		}
		if (args.length >= 0) {
            final Guild guild = u.getGuild();
            ChatUtil.sendMessage((CommandSender)p, "&8&m-[&e&m------&6&l " + u.getName() + " &e&m------&8&m]-");
            ChatUtil.sendMessage((CommandSender)p, " ");
            ChatUtil.sendMessage((CommandSender)p, "&6Ranking: &7" + u.getPoints());
            ChatUtil.sendMessage((CommandSender)p, "&6Zabojstw: &7" + u.getKills());
            ChatUtil.sendMessage((CommandSender)p, "&6KillStreak: &7" + u.getKs());
            ChatUtil.sendMessage((CommandSender)p, "&6KillStreak rekord: &7" + u.getKsMax());
            ChatUtil.sendMessage((CommandSender)p, "&6Zgonow &7" + u.getDeaths());
            ChatUtil.sendMessage((CommandSender)p, "&6Asyst: &7" + u.getAsists());
            ChatUtil.sendMessage((CommandSender)p, "&6Prestiż: &7" + u.getPrestiz());
            ChatUtil.sendMessage((CommandSender)p, "&6K/D ratio: &7" + u.getKd());
            ChatUtil.sendMessage((CommandSender)p, "&6Gildia: &7" + ((guild == null) ? "Brak gildii" : guild.getTag()));
            ChatUtil.sendMessage((CommandSender)p, " ");
            ChatUtil.sendMessage((CommandSender)p, "&8&m-[&e&m------&6&l " + u.getName() + " &e&m------&8&m]-");
            return false;
		}
		return false;

    }
}
