package pl.yspar.core.manager;

import java.util.concurrent.*;
import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;


import pl.yspar.core.Config;
import pl.yspar.core.CorePlugin;
import pl.yspar.core.basic.User;
import pl.yspar.core.listener.PlayerAntyLogoutListener;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.Logger;
import pl.yspar.core.utils.Util;

import java.io.File;
import java.io.IOException;
import java.sql.*;


public class UserManager
{
    private static List<User> users;
    private static List<User> online;
    
    static {
    	
        UserManager.users = new ArrayList<User>();
        UserManager.online = new ArrayList<User>();
    }
    
    public static List<User> getOnline() {
        return new ArrayList<User>(UserManager.online);
    }
    
    public static List<User> getUsers() {
        return new ArrayList<User>(UserManager.users);
    }
    


	public static void unregisterPlayers() {
		@SuppressWarnings("rawtypes")
		Iterator iterator;
		@SuppressWarnings("rawtypes")
		Iterator iterator2 = iterator = Bukkit.getOnlinePlayers().iterator();
		while (iterator2.hasNext()) {
			UserManager.quitPlayer((Player) iterator.next());
			iterator2 = iterator;
		}
	}
	
    public static void remove(final User user) {
        if (UserManager.users.contains(user)) {
            UserManager.users.remove(user);
        }
    }
    
    
    
	

    
	public static void registerPlayers() {
		@SuppressWarnings("rawtypes")
		Iterator iterator;
		@SuppressWarnings("rawtypes")
		Iterator iterator2 = iterator = Bukkit.getOnlinePlayers().iterator();
		while (iterator2.hasNext()) {
			UserManager.joinPlayer((Player) iterator.next());
			iterator2 = iterator;
		}
	}
    
    public static void joinPlayer(final Player player) {
    	final User user = User.getNew(player.getName(), player.getAddress().getAddress().getHostAddress());
    	if (user == null) {
    		UserManager.users.add((User) player);
    	}
        final Player player2 = player;
        player2.setScoreboard(user.getScoreboard());
        TagManager.createBoard(player2);
        UserManager.online.add(user);
        player.teleport(Config.SPAWN);
        player.getInventory().clear();
        YamlConfiguration c = YamlConfiguration.loadConfiguration(new File("plugins/core/data/" + player.getName()+".yml"));
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
    			User.restore(player);
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
        }
        return;
    }
    
    public static void quitPlayer(final Player player) {
        final Player player2 = player;
        final User user = User.get(player2);
        TagManager.removeBoard(player2);
        UserManager.online.remove(user);
        if (PlayerAntyLogoutListener.antiRelog.containsKey(player.getUniqueId())) {
            player.damage(1337.0);
            PlayerAntyLogoutListener.antiRelog.remove(player.getUniqueId());
            if (user.getPoints() > 50) {
                user.removePoints(50);
            }
        }
    }

    
    public static void RefreshTag(Player p) {
		final Iterator<Player> iterator2;
		Iterator<Player> iterator = iterator2 = (Iterator<Player>) Bukkit
				.getOnlinePlayers().iterator();
		while (iterator.hasNext()) {
			TagManager.updateBoard(iterator2.next());
			iterator = iterator2;
		}
    }
    
    public static void add(final User user) {
        if (!UserManager.users.contains(user)) {
            UserManager.users.add(user);
        }
    }
    
    public static List<String> getNames(final List<User> userList) {
        final ArrayList<String> names = new ArrayList<String>();
        Iterator<User> iterator2;
        final Iterator<User> iterator = iterator2 = userList.iterator();
        while (iterator2.hasNext()) {
            final User user = iterator.next();
            iterator2 = iterator;
            names.add(user.getName());
        }
        return names;
    }
    
    public static String getRank(final Player get) {
    	String tag = "&7 Gracz";

		if (get.getPlayer().hasPermission("nte.staff")) {
			tag = Util.fixColor("&4 Staff");
			return tag;
		}
		if (get.getPlayer().hasPermission("nte.support")) {
			tag = Util.fixColor("&b Support");
			return tag;
		}
		if (get.getPlayer().hasPermission("nte.specjalne")) {
			tag = Util.fixColor("&d Specjalne");
			return tag;
		}
		if (get.getPlayer().hasPermission("nte.nitro")) {
			tag = Util.fixColor("&5 Nitro");
			return tag;
		}
		if (get.getPlayer().hasPermission("nte.partner")) {
			tag = Util.fixColor("&6 Partner");
			return tag;
		}
		if (get.getPlayer().hasPermission("nte.yt")) {
			tag = Util.fixColor("&f Y&4T");
			return tag;
		}
		return tag;
    }
    
    public static String getJoinRank(final Player get) {
    	ChatUtil.sendTitleMessage(get, "", ChatUtil.fixColor("&8> &eSandy &8<"), 0, 30, 0);
    	ChatUtil.sendActionBar(get, ChatUtil.fixColor("&8→&7→ &6Twoja ranga&8:" + getRank(get) + " &7←&8←"));
		if (get.getPlayer().hasPermission("nte.staff")) {
			Bukkit.broadcastMessage(ChatUtil.fixColor("&8&l→ &4Staff &7" + get.getName() + " &fdołączył na tryb."));
			return null;
		}
		if (get.getPlayer().hasPermission("nte.support")) {
			Bukkit.broadcastMessage(ChatUtil.fixColor("&8&l→ &3Support &7" + get.getName() + " &fdołączył na tryb."));
			return null;
		}
		if (get.getPlayer().hasPermission("nte.specjalne")) {
			Bukkit.broadcastMessage(ChatUtil.fixColor("&8&l→ &dSpecjalne &7" + get.getName() + " &fdołączył na tryb."));
			return null;
		}
		if (get.getPlayer().hasPermission("nte.nitro")) {
			Bukkit.broadcastMessage(ChatUtil.fixColor("&8&l→ &5Nitro &7" + get.getName() + " &fdołączył na tryb."));
			return null;
		}
		if (get.getPlayer().hasPermission("nte.partner")) {
			Bukkit.broadcastMessage(ChatUtil.fixColor("&8&l→ &6Partner &7" + get.getName() + " &fdołączył na tryb."));
			return null;
		}
		if (get.getPlayer().hasPermission("nte.yt")) {
			Bukkit.broadcastMessage(ChatUtil.fixColor("&8&l→ &fYou&cTuber &7" + get.getName() + " &fdołączył na tryb."));
			return null;
		}
		return null;
    }

    
    public static void deleteUser(final User u) {
        UserManager.users.remove(u.getName());
        RankingManager.removeRanking(u);
    }
    
    public static List<User> getUsers(final List<String> names) {
        final ArrayList<User> userList = new ArrayList<User>();
        Iterator<String> iterator2;
        final Iterator<String> iterator = iterator2 = names.iterator();
        while (iterator2.hasNext()) {
            final String name = iterator.next();
            iterator2 = iterator;
            userList.add(User.get(name));
        }
        return userList;
    }
    
}
