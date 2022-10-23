package pl.yspar.core.command;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import pl.yspar.core.helper.SaveEqGui;
import pl.yspar.core.utils.ChatUtil;

import org.apache.commons.lang.*;


public class SaveEqCommand extends PlayerCommand
{
    public SaveEqCommand() {
        super("saveeq", "zapisywanie eq gracza", "/saveeq", "saveeq.use", new String[] { "zapiszeq"});
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        for(ProtectedRegion r : WGBukkit.getRegionManager(p.getWorld()).getApplicableRegions(p.getLocation())) {
            if (r.getId().equalsIgnoreCase("spawn")) {
            	SaveEqGui.open(p);
            	return false;
            }
        	ChatUtil.sendMessage(p, "&cZapisac eq mozesz tylko na spawnie");
        	return false;
        }
		return false;

    }
    

    
}

