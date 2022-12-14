package pl.yspar.core.listener;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import pl.yspar.core.CorePlugin;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.User;
import pl.yspar.core.manager.DataManager;
import pl.yspar.core.utils.ChatUtil;


public class EntityDamageByEntityListener implements Listener {

	List<String> mm = new ArrayList<String>();

	@EventHandler
	public void onDDmg(BlockDamageEvent e) {
		if (mm.contains(e.getPlayer().getName())) {
			return;
		} else {
			mm.add(e.getPlayer().getName());
			Bukkit.getScheduler().runTaskLaterAsynchronously(CorePlugin.getPlugin(), new Runnable(){
				public void run(){
					if(mm.contains(e.getPlayer().getName())){
						mm.remove(e.getPlayer().getName());
					}
				}
			}, 20L);
		}

	}
	
	
	    @EventHandler(priority = EventPriority.HIGHEST)
	    public void onDamage(final EntityDamageEvent event) {
	        final Entity entity = event.getEntity();
	        Player player = null;
	        if (!(entity instanceof Player)) {
	            return;
	        }
	        player = (Player)entity;
	        if (player.isSneaking() && DataManager.getShiftArmor().containsKey(player.getName())) {
	            final ItemStack[] armor = DataManager.getShiftArmor().get(player.getName());
	            DataManager.getShiftArmor().remove(player.getName());
	            player.getInventory().setArmorContents(armor);
	            player.updateInventory();
	            event.setCancelled(false);
	        }
	    }
	
	    @EventHandler
	    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
	    if (event.getDamager() instanceof Snowball) {
	    	Snowball snowball = (Snowball) event.getDamager();
	    	Entity hitBySnowball = event.getEntity();
	    	LivingEntity shooter = (LivingEntity) snowball.getShooter();
	    	if (hitBySnowball instanceof Player) {
	    		Player player = (Player) hitBySnowball;
	    		BigDecimal bigDecimal = new BigDecimal(player.getHealth()/2.0);
	    		bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
	    		double health = bigDecimal.doubleValue();
	    		User u = User.get(player);
	    		ChatUtil.sendMessage(shooter, "&cGracz " + (u.isIncognito() ? "&c&k" : "&c") + player.getName() + "&c " + health + " ???");
	    		return;
	    
	    		}
	    	}
	    }
	    
	    @EventHandler
	    public void onToggle(final PlayerToggleSneakEvent event) {
	        final Player player = event.getPlayer();
	        if (player.isSneaking()) {
	            if (DataManager.getShiftArmor().containsKey(player.getName())) {
	                final ItemStack[] armor = DataManager.getShiftArmor().get(player.getName());
	                DataManager.getShiftArmor().remove(player.getName());
	                player.getInventory().setArmorContents(armor);
	                player.updateInventory();
	            }
	        }
	        else if (DataManager.getDisco().containsKey(player.getName())) {
	            DataManager.getShiftArmor().put(player.getName(), player.getInventory().getArmorContents());
	        }
	    }


	@EventHandler
    public void onDamagex(EntityDamageByEntityEvent e) {
    	if(e.getDamager() instanceof Arrow){
            final Arrow arrow = (Arrow) e.getDamager();
            if(arrow.getShooter() instanceof Player){
                if(!(e.getEntity() instanceof Player)){
                	return;
                }
                Player player = (Player) e.getEntity();
                Player damager = (Player) arrow.getShooter();
                if(mm.contains(player.getName())){
                	if(damager.getItemInHand().getEnchantments().containsKey(Enchantment.ARROW_KNOCKBACK)){
                		e.setCancelled(true);
                		e.setDamage(0);
                	}
                }
            }
        }
    }
	

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (Bukkit.getServerName().equals("spawn")) {
	        e.setCancelled(true);
	        return;
		}
		if (!(e.getDamager() instanceof Player)
				|| !(e.getEntity() instanceof Player)) {
			return;
		}
		Player playerUser = (Player) e.getEntity();
		Player damager = (Player) e.getDamager();
		if (damager.equals((Object) (e.getEntity()))) {
			return;
		}
		User damagerUser = User.get(damager);
		User user = User.get(playerUser);
		if (!damagerUser.hasGuild()) {
			return;
		}
		if (!user.hasGuild()) {
			return;
		}
		Guild damagerGuild = damagerUser.getGuild();
		if (damagerGuild.isMember(User.get(playerUser))) {
			if (damagerGuild.isPvp()) {
				return;
			}
			e.setCancelled(true);
			damager.sendMessage(ChatUtil.fixColor("&4[!] &cPvp w gildii jest wylaczone"));
			return;
		}
	}
}
