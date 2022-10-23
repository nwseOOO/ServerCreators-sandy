package pl.yspar.core.command.guild;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.yspar.core.command.PlayerCommand;
import pl.yspar.core.manager.GuildManager;
import pl.yspar.core.utils.ChatUtil;

public class GuildHelpCommand extends PlayerCommand
{
    public GuildHelpCommand() {
        super("gildie", "info o gildiach", "/gildie", "core.cmd.user", new String[] { "gildiepomoc", "g", "gildia" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
            ChatUtil.sendMessage((CommandSender)p, "&8&m-[&e&m------&e&l &6&lGildie &e&m------&8&m]-");
            ChatUtil.sendMessage((CommandSender)p, "");
            ChatUtil.sendMessage((CommandSender)p, "");
            ChatUtil.sendMessage((CommandSender)p, "&r &8» &6/zaloz <tag> <nazwa> &8- &6Zaklada nowa gildie");
            ChatUtil.sendMessage((CommandSender)p, "&r &8» &6/dolacz <tag/nazwa> &8- &6dolaczasz do gildii");
            ChatUtil.sendMessage((CommandSender)p, "&r &8» &6/opusc &8- &6opuszczasz gildie");
            ChatUtil.sendMessage((CommandSender)p, "&r &8» &6/wyrzuc <nick> &8- &6wyrzuca gracza z gildii");
            ChatUtil.sendMessage((CommandSender)p, "&r &8» &6/zapros <nick> &8- &6zaprasza gracza do gildii");
            ChatUtil.sendMessage((CommandSender)p, "&r &8» &6/zastepca <nick> &8- &6zmienia zastepce gildii");
            ChatUtil.sendMessage((CommandSender)p, "&r &8» &6/ginfo <gildia> &8- &6informacje o gildii");
            ChatUtil.sendMessage((CommandSender)p, "");
            ChatUtil.sendMessage((CommandSender)p, " &8&l»  &6Liczba gildii (tryb)&8: &f" + GuildManager.getGuilds().size());
            ChatUtil.sendMessage((CommandSender)p, " &8&l»  &cPamietaj: &6Gildia jest tylko na tym trybie na ktorym zalozysz!");
            ChatUtil.sendMessage((CommandSender)p, "");
            return ChatUtil.sendMessage((CommandSender)p, "&8&m-[&e&m------&e&l &6&lGildie &e&m------&8&m]-");
        }
}
