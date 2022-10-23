package pl.yspar.core.command;


import org.bukkit.entity.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;


import pl.yspar.core.Config;
import pl.yspar.core.CorePlugin;
import pl.yspar.core.basic.User;
import pl.yspar.core.gui.LobbyGui;
import pl.yspar.core.gui.ProfileGui;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;


public class SpawnCommand extends PlayerCommand
{
    public SpawnCommand() {
        super("spawn", "informacje o gildii", "/incognito", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        p.teleport(Config.SPAWN);
        ChatUtil.sendMessage(p, "&aPomyślnie przeteleportowano.");
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
	        p.getInventory().addItem(sword);
	        p.getInventory().addItem(sword2);
	        p.getInventory().addItem(kox);
	        p.getInventory().addItem(ref);
	        p.getInventory().addItem(woda);
	        p.getInventory().addItem(mieso);
	        p.getInventory().addItem(slime);
	        p.getInventory().addItem(cobbel);
	        p.getInventory().addItem(perly);
	        p.getInventory().addItem(bow);
	        p.getInventory().addItem(arrow);
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
	        p.getInventory().setHelmet(h1);
	        p.getInventory().setChestplate(h2);
	        p.getInventory().setLeggings(h3);
	        p.getInventory().setBoots(h4);
            return false;
        }
        if (c.get("inventory.content") != null){
    		try {
    			User.restore(p);
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
        }
		return false;
    }
          
    


}