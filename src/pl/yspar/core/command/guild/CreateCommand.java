package pl.yspar.core.command.guild;

import java.io.DataOutputStream;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import pl.yspar.core.CorePlugin;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.User;
import pl.yspar.core.command.PlayerCommand;
import pl.yspar.core.manager.GuildManager;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.DataUtil;
import pl.yspar.core.utils.TimeUtil;

public class CreateCommand extends PlayerCommand
{
    public CreateCommand() {
        super("zaloz", "zaklada gildie", "/zaloz <tag> <pelna nazwa>", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
    	
        if (args.length < 2) {
            ChatUtil.sendMessage((CommandSender)p, "&cZa malo argumentow");
            return false;
        }
        User u = User.get(p);
        final Guild guild = User.get(p).getGuild();

        if (guild != null) {
            return ChatUtil.sendMessage((CommandSender)p, "&4Blad: &cPosiadasz juz gildie!");
        }
        final String tag = args[0].toUpperCase();
        final String name = args[1];

        if (tag.length() > 4 || tag.length() < 2 || name.length() > 32 || name.length() < 4) {
            return ChatUtil.sendMessage((CommandSender)p, "&4Blad: &cTag gildi musi zawierac 2-4 znakow, nazwa 4-32 znakow!");
        }
        if (Guild.get(tag) != null) {
            return ChatUtil.sendMessage((CommandSender)p, "&4Blad: &cIstenieje juz gildia o takim tagu!");
        }
        if (Guild.get(name) != null) {
            return ChatUtil.sendMessage((CommandSender)p, "&4Blad: &cIstenieje juz gildia o takiej nazwie!");
        }
       
        Guild g = new Guild(tag, name, u);
        g.changes();
        u.setGuild(g);
        final ByteArrayOutputStream b = new ByteArrayOutputStream();
        final DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Message");
            out.writeUTF("ALL");
            out.writeUTF(ChatUtil.fixColor("&aGildie &8★ &6Gracz &8" + p.getName() + " &6zalozyl gildie &8[&7" + g.getTag() + "&8] &6" + g.getName() + "&c!"));
        }
        catch (Exception ed) {
            ed.printStackTrace();
        }

        p.sendPluginMessage((Plugin)CorePlugin.getPlugin(), "BungeeCord", b.toByteArray());
        String msg5 = "&6Zalozyles gildie: &c{G}";
        msg5 = msg5.replace("{G}", g.getTag());
        String msg6 = "&6Gratulacje, &c{PLAYER}&6!";
        msg6 = msg6.replace("{PLAYER}", p.getName());
        ChatUtil.sendTitleMessage(p, msg5, msg6, 30, 70, 40);


        return false;
    }
}
