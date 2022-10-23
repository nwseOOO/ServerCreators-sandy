package pl.yspar.core.task;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import pl.yspar.core.basic.User;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.TitleAPI;
import pl.yspar.core.utils.Util;



public class ActionTask extends BukkitRunnable {

    public void run() {
  
        for (final Player p : Bukkit.getOnlinePlayers()) {
            User u = User.get(p);
            if (u.isVanish()) {
            	ChatUtil.sendActionBar(p, Util.fixColor("&8-» &6Jestes &cNiewidzialny &6dla graczy &e(VANISH) &8«-"));
            	continue;
            }
            if (isBoost()) {
            	ChatUtil.sendActionBar(p, Util.fixColor("&8-» &7AKTYWNY &c&LBOOSTER COINS &7W GODZINACH &f(&7&l12-17-23&f) &8«-"));
            	continue;
            }
        }	
    }
    
    
    public static boolean isBoost(){	
	    SimpleDateFormat df = new SimpleDateFormat("HH");
	    String time = df.format(Long.valueOf(System.currentTimeMillis()));
	    if(time.contains("12")){
	    	return true;
	    }
	    
	    if(time.contains("17")){
	    	return true;
	    }
	    if(time.contains("23")){
	    	return true;
	    }
	    return false;
		
	}
}