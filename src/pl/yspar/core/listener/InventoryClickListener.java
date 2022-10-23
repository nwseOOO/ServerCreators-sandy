package pl.yspar.core.listener;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import pl.yspar.core.Config;
import pl.yspar.core.CorePlugin;
import pl.yspar.core.basic.User;
import pl.yspar.core.gui.ProfileGui;
import pl.yspar.core.helper.ArmorType;
import pl.yspar.core.helper.ParticleType;
import pl.yspar.core.manager.DataManager;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.ItemUtils;
import pl.yspar.core.utils.PacketEquipment;
import pl.yspar.core.utils.Util;

public class InventoryClickListener implements Listener {
	
		
		@EventHandler(priority = EventPriority.MONITOR)
		public void onZapiszeq(final InventoryClickEvent e) {
			final Player p = (Player) e.getWhoClicked();
			if (!ChatUtil.fixColor("&8Zapisz ekwipunek:").equalsIgnoreCase(e.getInventory().getName())) {
				return;
			}
			e.setCancelled(true);
			final ItemStack item = e.getCurrentItem();

				final ItemMeta meta = item.getItemMeta();
				if (meta != null) {
					if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&2Zapisz swój ekwipunek"))) {
						p.closeInventory();
						try {
							User.save(p);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						ChatUtil.sendTitleMessage(p, "", ChatUtil.fixColor("&aZapisano &6ustawienie &aekwipunku"), 30, 70, 40);
						return;
					
					}
					if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&4Anuluj zapisywanie"))) {
						p.closeInventory();
						ChatUtil.sendTitleMessage(p, "", ChatUtil.fixColor("&cZamknieto menu"), 30, 70, 40);
						return;
					
					}
					
				}
		
		}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onClickPrestiz(final InventoryClickEvent e) {
		final Player p = (Player) e.getWhoClicked();	
		if (e.getInventory().getName().equalsIgnoreCase(pl.yspar.core.utils.Util.fixColor("&eDepozyt"))) {
			User user = User.get(((Player) e.getWhoClicked()));
			InventoryClickEvent inventoryClickEvent = e;
			inventoryClickEvent.setCancelled(true);
			ItemStack is = inventoryClickEvent.getCurrentItem();
			if (is == null || !is.hasItemMeta() || is.getItemMeta().getDisplayName() == null) {
				return;
			}
			String name = is.getItemMeta().getDisplayName();
			@SuppressWarnings("unused")
			Object drop = null;
			boolean vip = p.hasPermission("Core.limit.vip");
			if (name.equalsIgnoreCase(Util.fixColor(" &6&lKOXY"))) {
				wyplacKoxy(p);
			}
			if (name.equalsIgnoreCase(Util.fixColor(" &e&lREFILE"))) {
				wyplacRefy(p);
			}
			if (name.equalsIgnoreCase(Util.fixColor(" &5&lPERLY"))) {
				wyplacPerly(p);
			}
			if (name.equalsIgnoreCase(Util.fixColor(" &eDobierz caly limit"))) {
				wyplacPerly(p);
				wyplacRefy(p);
				wyplacKoxy(p);
			}
			return;
		}
		if (!ChatUtil.fixColor("&6Prestiż").equalsIgnoreCase(e.getInventory().getName())) {
			return;
		}
		e.setCancelled(true);
		final ItemStack item = e.getCurrentItem();

		User u = User.get(p);

			final ItemMeta meta = item.getItemMeta();
			if (meta != null) {
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&2Spełniasz wymagania"))) {
					p.closeInventory();
					ChatUtil.sendTitleMessage(p, "", ChatUtil.fixColor("&6Wymieniono ranking na prestiz &eGratulacje&6!"), 30, 70, 40);
					Bukkit.broadcastMessage(ChatUtil.fixColor("&8&l→ &6Gracz &7" + p.getName() + " &6wymienił ranking na prestiż &7Gratulacje&6!"));
					u.addPrestiz(1);
					u.setPoints(1000);
					return;
				
				}
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&4Posiadasz zbyt malo rankingu"))) {
					p.closeInventory();
					ChatUtil.sendTitleMessage(p, "", ChatUtil.fixColor("&4Posiadasz zbyt malo rankingu :("), 30, 70, 40);
					return;
				
				}
				
			}
	}
	

	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onClickEfekt2(final InventoryClickEvent e) {
		if (!ChatUtil.fixColor("&6Wybór trybu").equalsIgnoreCase(e.getInventory().getName())) {
			return;
		}
		e.setCancelled(true);
		final ItemStack item = e.getCurrentItem();
		final Player p = (Player) e.getWhoClicked();
		User u = User.get(p);

			final ItemMeta meta = item.getItemMeta();
			if (meta != null) {
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&7→ &e&o&lLobby serwerowe &7←"))) {
					p.closeInventory();
					ChatUtil.sendTitleMessage(p, "", ChatUtil.fixColor("&6Dodano do kolejki, twoja ranga to:" + UserManager.getRank(p)), 30, 70, 40);
					teleportServer(p, "lobby_1");
					return;
				
			}
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&7→ &e&o&lBattle stages &7←"))) {
					p.closeInventory();
					ChatUtil.sendTitleMessage(p, "", ChatUtil.fixColor("&6Dodano do kolejki, twoja ranga to:" + UserManager.getRank(p)), 30, 70, 40);
					teleportServer(p, "battle");
					return;
				
			}
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&7→ &e&o&lPractice &7←"))) {
					p.closeInventory();
					ChatUtil.sendTitleMessage(p, "", ChatUtil.fixColor("&6Dodano do kolejki, twoja ranga to:" + UserManager.getRank(p)), 30, 70, 40);
					teleportServer(p, "practice");
					return;
				
			}
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&7→ &e&o&lKrety &7←"))) {
					p.closeInventory();
					ChatUtil.sendTitleMessage(p, "", ChatUtil.fixColor("&6Dodano do kolejki, twoja ranga to:" + UserManager.getRank(p)), 30, 70, 40);
					teleportServer(p, "krety");
					return;
				
				}
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&7→ &e&o&lTrening &7←"))) {
					p.closeInventory();
					ChatUtil.sendTitleMessage(p, "", ChatUtil.fixColor("&6Dodano do kolejki, twoja ranga to:" + UserManager.getRank(p)), 30, 70, 40);
					teleportServer(p, "trening");
					return;
				
				}
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&7→ &e&o&lEvent &7←"))) {
					p.closeInventory();
					ChatUtil.sendTitleMessage(p, "", ChatUtil.fixColor("&6Dodano do kolejki, twoja ranga to:" + UserManager.getRank(p)), 30, 70, 40);
					teleportServer(p, "event");
					return;
				
				}
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&7→ &e&o&lFFa &7←"))) {
					p.closeInventory();
					ChatUtil.sendTitleMessage(p, "", ChatUtil.fixColor("&6Dodano do kolejki, twoja ranga to:" + UserManager.getRank(p)), 30, 70, 40);
					teleportServer(p, "ffa");
					return;
				
				}
			}
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onClickEfekt(final InventoryClickEvent e) {
		if (!ChatUtil.fixColor("&6Profil gracza").equalsIgnoreCase(e.getInventory().getName())) {
			return;
		}
		e.setCancelled(true);
		final ItemStack item = e.getCurrentItem();
		final Player p = (Player) e.getWhoClicked();
		User u = User.get(p);

			final ItemMeta meta = item.getItemMeta();
			if (meta != null) {
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&6Zamknij"))) {
					p.closeInventory();
					ChatUtil.sendTitleMessage(p, "", "&cZamknieto profil gracza.", 30, 70, 40);
					return;
				
			}
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&6Powrot do: &cMENU GLOWNE"))) {
					p.closeInventory();
					ProfileGui.open(p);
					return;
				
			}
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&6Particlesy"))) {
					p.closeInventory();
					ProfileGui.openParticles(p);
					return;
				
			}
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&6Serduszka"))) {
					p.closeInventory();
					ChatUtil.sendTitleMessage(p, "", "&6Zmieniono &cefekt particlesow&6!", 30, 70, 40);
					u.setParticles(true);
					u.setParticleType(ParticleType.HEART);
					ProfileGui.openParticles(p);
					return;
				
			}
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&6Ogien"))) {
					p.closeInventory();
					ChatUtil.sendTitleMessage(p, "", "&6Zmieniono &cefekt particlesow&6!", 30, 70, 40);
					u.setParticles(true);
					u.setParticleType(ParticleType.FIRE);
					ProfileGui.openParticles(p);
					return;
				
			}
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&6Efekt particlesow jest &aWlaczony"))) {
					p.closeInventory();
					ChatUtil.sendTitleMessage(p, "", "&cWylaczono &6particlesy.", 30, 70, 40);
					u.setParticles(false);
					u.setParticleType(null);
					ProfileGui.openParticles(p);
					return;
				
			}
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&6Incognito"))) {
					p.closeInventory();
					p.chat("/incognito");
					return;
				
			}
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&6Disco zbroja"))) {
					p.closeInventory();
					ChatUtil.sendTitleMessage(p, "", "&cChwilowo &4Wyłączone&c!", 30, 70, 40);
					return;
				
			}
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&6Jezyk"))) {
					p.closeInventory();
					ProfileGui.openJezyk(p);
					return;
				
			}
				if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&6Polski"))) {
					p.closeInventory();
					ChatUtil.sendTitleMessage(p, "", "&6Zmieniono &cJezyk&6!", 30, 70, 40);
					return;
				
			}

		}
	}
	
	
    public void teleportServer(Player p, String server){
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
 
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (IOException eee) {
            // Fehler
        }
 
        p.sendPluginMessage(CorePlugin.getPlugin(), "BungeeCord", b.toByteArray());
    }
    
	public void wyplacPerly(Player player ) {
		int maxKox;
		User user = User.get(((Player) player));
		boolean vip = player.hasPermission("Core.limit.vip");
		@SuppressWarnings("unused")
		int n = maxKox = vip ? Config.LIMIT_PEARL : Config.LIMIT_PEARL;
		if (user == null) {
			user = User.get(player);
		}
		if (user.getSchowekPerla() <= 0) {
			Util.sendMessage((CommandSender) player, Util.fixColor("&cBrak przedmiotu w depozycie"));
			return;
		}
		int kox = ItemUtils.getAmountOfItem(Material.ENDER_PEARL, player, (short) 0);
		if (kox >= maxKox) {
			Util.sendMessage((CommandSender) player, Util.fixColor("&cPosiadasz makymalna ilosc tego przedmiotu"));
			return;
		}
		int i = maxKox - kox;
		if (i > user.getSchowekPerla()) {
			i = user.getSchowekPerla();
		}
		user.removeSchowekPerla(i);
		ItemStack remove = new ItemStack(Material.ENDER_PEARL, i);
		ItemUtils.giveItem(player, Arrays.asList(new ItemStack[] { remove }), player.getLocation());
		Player player22 = player;
		player22.sendMessage(
				Util.fixColor("&fPomyslnie wyplaciles &6{DEPO} &fperel.".replace("{DEPO}", Integer.toString(i))));
		player22.closeInventory();
	}
	
	public void wyplacRefy(Player player ) {
		User user = User.get(((Player) player));
		boolean vip = player.hasPermission("Core.limit.vip");
		int maxKox;
		@SuppressWarnings("unused")
		int n = maxKox = vip ? Config.LIMIT_REF : Config.LIMIT_REF;
		if (user == null) {
			user = User.get(player);
		}
		if (user.getSchowekRefil() <= 0) {
			Util.sendMessage((CommandSender) player, Util.fixColor("&cBrak przedmiotu w depozycie"));
			return;
		}
		int kox = ItemUtils.getAmountOfItem(Material.GOLDEN_APPLE, player, (short) 0);
		if (kox >= maxKox) {
			Util.sendMessage((CommandSender) player, Util.fixColor("&cPosiadasz makymalna ilosc tego przedmiotu"));
			return;
		}
		int i = maxKox - kox;
		if (i > user.getSchowekRefil()) {
			i = user.getSchowekRefil();
		}
		user.removeSchowekRef(i);
		ItemStack remove = new ItemStack(Material.GOLDEN_APPLE, i);
		ItemUtils.giveItem(player, Arrays.asList(new ItemStack[] { remove }), player.getLocation());
		Player player21 = player;
		player21.sendMessage(
				Util.fixColor("&fPomyslnie wyplaciles &b{DEPO} &frefili.".replace("{DEPO}", Integer.toString(i))));
		player21.closeInventory();
		return;
	}
	
	public void wyplacKoxy(Player player) {
		User user = User.get(((Player) player));
		boolean vip = player.hasPermission("Core.limit.vip");
		int maxKox;
		@SuppressWarnings("unused")
		int n = maxKox = vip ? Config.LIMIT_KOX : Config.LIMIT_KOX;
		if (user == null) {
			user = User.get(player);
		}
		if (user.getSchowekKox() <= 0) {
			Util.sendMessage((CommandSender) player, Util.fixColor("&cBrak przedmiotu w depozycie"));
			return;
		}
		int kox = ItemUtils.getAmountOfItem(Material.GOLDEN_APPLE, player, (short) 1);
		if (kox >= maxKox) {
			Util.sendMessage((CommandSender) player, Util.fixColor("&cPosiadasz makymalna ilosc tego przedmiotu"));
			return;
		}
		int i = maxKox - kox;
		if (i > user.getSchowekKox()) {
			i = user.getSchowekKox();
		}
		user.removeSchowekKox(i);
		ItemStack remove = new ItemStack(Material.GOLDEN_APPLE, i, (short) 1);
		ItemUtils.giveItem(player, Arrays.asList(new ItemStack[] { remove }), player.getLocation());
		Player player20 = player;
		player20.sendMessage(
				Util.fixColor("&fPomyslnie wyplaciles &b{DEPO} &fkoxow.".replace("{DEPO}", Integer.toString(i))));
		player20.closeInventory();
		return;
	}
}
