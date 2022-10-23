package pl.yspar.core.listener;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import pl.yspar.core.Config;
import pl.yspar.core.basic.User;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.Util;


public class PlayerRespawnListener implements Listener {

	
	
    @EventHandler
    public void onEntityDamage(final PlayerRespawnEvent e) {
            final Player p = (Player)e.getPlayer();
            final Player player = (Player)e.getPlayer();
                p.setHealth(20.0);
                p.setFoodLevel(20);
                p.setSaturation(20.0f);
                p.setFireTicks(0);
                p.teleport(Config.SPAWN);
    	        p.getInventory().clear();
                YamlConfiguration c = YamlConfiguration.loadConfiguration(new File("plugins/core/data/" + p.getName()+".yml"));
                if (c.get("inventory.content") == null){
        	        ItemStack sword = new ItemStack(Material.IRON_SWORD); 
        	        sword.addEnchantment(Enchantment.DAMAGE_ALL, 4); 
        	        sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
        	        ItemStack sword2 = new ItemStack(Material.IRON_SWORD); 
        	        sword2.addEnchantment(Enchantment.KNOCKBACK, 2); 
        	        ItemStack kox = new ItemStack(Material.GOLDEN_APPLE, 1, (short)1); 
        	        ItemStack ref = new ItemStack(Material.GOLDEN_APPLE, 10); 
        	        ItemStack mieso = new ItemStack(Material.COOKED_BEEF, 64); 
        	        ItemStack woda = new ItemStack(Material.WATER_BUCKET, 1); 
        	        ItemStack slime = new ItemStack(Material.SLIME_BLOCK, 3); 
        	        ItemStack cobbel = new ItemStack(Material.COBBLESTONE, 64); 
        	        ItemStack perly = new ItemStack(Material.ENDER_PEARL, 2); 
        	        ItemStack bow = new ItemStack(Material.BOW, 1); 
        	        bow.addEnchantment(Enchantment.ARROW_DAMAGE, 3); 
        	        bow.addEnchantment(Enchantment.ARROW_FIRE,1);
        	        ItemStack arrow = new ItemStack(Material.ARROW, 16); 
        	        player.getInventory().addItem(sword);
        	        player.getInventory().addItem(sword2);
        	        player.getInventory().addItem(kox);
        	        player.getInventory().addItem(ref);
        	        player.getInventory().addItem(woda);
        	        player.getInventory().addItem(mieso);
        	        player.getInventory().addItem(slime);
        	        player.getInventory().addItem(cobbel);
        	        player.getInventory().addItem(perly);
        	        player.getInventory().addItem(bow);
        	        player.getInventory().addItem(arrow);
        	        ItemStack h1 = new ItemStack(Material.IRON_HELMET); 
        	        h1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4); 
        	        h1.addEnchantment(Enchantment.DURABILITY, 3); 
        	        
        	        ItemStack h2 = new ItemStack(Material.IRON_CHESTPLATE); 
        	        h2.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4); 
        	        h2.addEnchantment(Enchantment.DURABILITY, 3); 
        	        
        	        ItemStack h3 = new ItemStack(Material.IRON_LEGGINGS); 
        	        h3.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4); 
        	        h3.addEnchantment(Enchantment.DURABILITY, 3); 
        	        
        	        ItemStack h4 = new ItemStack(Material.IRON_BOOTS); 
        	        h4.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4); 
        	        h4.addEnchantment(Enchantment.DURABILITY, 3); 
        	        player.getInventory().setHelmet(h1);
        	        player.getInventory().setChestplate(h2);
        	        player.getInventory().setLeggings(h3);
        	        player.getInventory().setBoots(h4);
                    return;
                }
                if (c.get("inventory.content") != null){
            		try {
            			User.restore(p);
            		} catch (IOException e1) {
            			e1.printStackTrace();
            		}
                }
  	        	ChatUtil.sendTitleMessage(p, Util.fixColor("&eZginąłęś"), Util.fixColor("&fNie poddawaj się!"), 0, 20, 0);

            
        
    }
	
}
