package pl.yspar.core.task;

import org.bukkit.scheduler.*;

import pl.yspar.core.helper.ArmorType;
import pl.yspar.core.manager.DataManager;
import pl.yspar.core.utils.ColorUtils;
import pl.yspar.core.utils.PacketEquipment;

import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.Iterator;

import org.bukkit.*;
import org.bukkit.entity.*;

public class DiscoTask extends BukkitRunnable
{
    public void run() {

		Iterator iterator;
		Iterator iterator2 = iterator = Bukkit
				.getOnlinePlayers().iterator();
		while (iterator2.hasNext()) {
			Player player = (Player) iterator.next();
            final ArmorType armor = DataManager.getDisco().get(player.getName());
            if (armor != null) {
                switch (armor) {
                    case RANDOM: {
                        final Color color = ColorUtils.randomColor();
                        for (int i = 1; i < 5; ++i) {
                            final ItemStack item = new ItemStack(Material.getMaterial(297 + i), 1);
                            final LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();
                            meta.setColor(color);
                            item.setItemMeta((ItemMeta)meta);
                                if (!player.getName().equals(player.getName())) {
                                    PacketEquipment.sendEquipment(player, player.getEntityId(), i, item);
                                
                            }
                        }
                        if (player.isSneaking() && DataManager.getShiftArmor().containsKey(player.getName())) {
                            for (int i = 0; i < 4; ++i) {
                                final ItemStack item = new ItemStack(Material.getMaterial(298 + i), 1);
                                final LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();
                                meta.setColor(color);
                                item.setItemMeta((ItemMeta)meta);
                                player.getInventory().setItem(36 + i, item);
                            }
                            break;
                        }
                        break;
                    }
                    case ULTRA: {
                        for (int j = 1; j < 5; ++j) {
                            final ItemStack item2 = new ItemStack(Material.getMaterial(297 + j), 1);
                            final LeatherArmorMeta meta2 = (LeatherArmorMeta)item2.getItemMeta();
                            meta2.setColor(ColorUtils.randomColor());
                            item2.setItemMeta((ItemMeta)meta2);
                                if (!player.getName().equals(player.getName())) {
                                    PacketEquipment.sendEquipment(player, player.getEntityId(), j, item2);
                                
                            }
                        }
                        if (player.isSneaking() && DataManager.getShiftArmor().containsKey(player.getName())) {
                            for (int j = 0; j < 4; ++j) {
                                final ItemStack item2 = new ItemStack(Material.getMaterial(298 + j), 1);
                                final LeatherArmorMeta meta2 = (LeatherArmorMeta)item2.getItemMeta();
                                meta2.setColor(ColorUtils.randomColor());
                                item2.setItemMeta((ItemMeta)meta2);
                                player.getInventory().setItem(36 + j, item2);
                            }
                            break;
                        }
                        break;
                    }
                    case SMOOTH: {
                        final Color color = ColorUtils.nextColor(DataManager.getLastColor().get(player.getName()));
                        DataManager.getLastColor().put(player.getName(), color);
                        for (int i = 1; i < 5; ++i) {
                            final ItemStack item = new ItemStack(Material.getMaterial(297 + i), 1);
                            final LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();
                            meta.setColor(color);
                            item.setItemMeta((ItemMeta)meta);
                                if (!player.getName().equals(player.getName())) {
                                    PacketEquipment.sendEquipment(player, player.getEntityId(), i, item);
                                }
                            }
                        }
                        if (player.isSneaking() && DataManager.getShiftArmor().containsKey(player.getName())) {
                            for (int i = 0; i < 4; ++i) {
                                final ItemStack item = new ItemStack(Material.getMaterial(298 + i), 1);
                                final LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();

                                item.setItemMeta((ItemMeta)meta);
                                player.getInventory().setItem(36 + i, item);
                            }
                            break;
                        
                    }
                    case GRAY: {
                        Color color = ColorUtils.nextColor(DataManager.getLastColor().get(player.getName()));
                        DataManager.getLastColor().put(player.getName(), color);
                        color = Color.fromRGB(color.getRed(), color.getRed(), color.getRed());
                        for (int i = 1; i < 5; ++i) {
                            final ItemStack item = new ItemStack(Material.getMaterial(297 + i), 1);
                            final LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();
                            meta.setColor(color);
                            item.setItemMeta((ItemMeta)meta);
                                if (!player.getName().equals(player.getName())) {
                                    PacketEquipment.sendEquipment(player, player.getEntityId(), i, item);
                                }
                            }
                        }
                        if (player.isSneaking() && DataManager.getShiftArmor().containsKey(player.getName())) {
                            for (int i = 0; i < 4; ++i) {
                                final ItemStack item = new ItemStack(Material.getMaterial(298 + i), 1);
                                final LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();
                                item.setItemMeta((ItemMeta)meta);
                                player.getInventory().setItem(36 + i, item);
                            }
                            break;
                        }
                        break;
                }
                
            }
        }
    }
}
