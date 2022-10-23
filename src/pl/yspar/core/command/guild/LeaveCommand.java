package pl.yspar.core.command.guild;



import org.bukkit.entity.*;

import org.bukkit.command.*;

import java.io.*;

import org.bukkit.plugin.*;


import pl.yspar.core.CorePlugin;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.User;
import pl.yspar.core.command.PlayerCommand;
import pl.yspar.core.manager.GuildManager;
import pl.yspar.core.utils.ChatUtil;


public class LeaveCommand extends PlayerCommand
{
    public LeaveCommand() {
        super("opusc", "dolacza do gildii", "/dolacz <tag>", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Player player;
        final User user;
        final Guild guild;
        if ((guild = (user = User.get(player = (Player)p)).getGuild()) == null) {
            final boolean b = false;
            player.sendMessage(ChatUtil.fixColor("&4Blad: &cNie posiadasz gildii"));
            return b;
        }
        if (guild.isOwner(user)) {
            final boolean b2 = false;
            player.sendMessage(ChatUtil.fixColor("&4Blad: &cLider nie moze opuscic gildii"));
            return b2;
        }

        guild.removeMember(user);
        final Player player2 = player;
        user.setGuild(null);
        final ByteArrayOutputStream b = new ByteArrayOutputStream();
        final DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Message");
            out.writeUTF("ALL");
            out.writeUTF(ChatUtil.fixColor("&aGildie &8★ &6Gracz &8" + p.getName() + " &6opuscil gildie &8[&7" + guild.getTag() + "&8]"));
        }
        catch (Exception ed) {
            ed.printStackTrace();
        }
        p.sendPluginMessage((Plugin)CorePlugin.getPlugin(), "BungeeCord", b.toByteArray());
        return false;
    }
}

