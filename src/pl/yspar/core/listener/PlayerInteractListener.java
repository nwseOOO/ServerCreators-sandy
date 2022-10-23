package pl.yspar.core.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;


import pl.yspar.core.CorePlugin;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.Util;

public class PlayerInteractListener implements Listener {
	
	
	@EventHandler
	public void onEnerPerals(final PlayerInteractEvent e) {
		final Player p;
		if ((p = e.getPlayer()).getItemInHand().getType() != Material.ENDER_PEARL) {
			return;
		}
        Location location = p.getLocation();

		Block under = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
		if (under.getType() == Material.AIR) {

				final boolean cancelled = true;
				e.setCancelled(cancelled);
				p.sendMessage(Util.fixColor("&4[!] &cAby cyknac perle musisz stac na bloku!"));
				return;
			
			
		}
	}
	



        	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
            public void asd(PlayerDropItemEvent event) {
                Player player = event.getPlayer();
                for(ProtectedRegion r : WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation())) {
                    if (r.getId().equalsIgnoreCase("spawn")) {
                    	event.setCancelled(true);
                    	
                    }
                }
        	}
}
