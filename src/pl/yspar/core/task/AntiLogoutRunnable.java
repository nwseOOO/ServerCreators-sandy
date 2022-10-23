package pl.yspar.core.task;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import pl.yspar.core.CorePlugin;
import pl.yspar.core.basic.Damage;
import pl.yspar.core.listener.PlayerAntyLogoutListener;
import pl.yspar.core.utils.ChatUtil;


public class AntiLogoutRunnable {
	public static void start() {
		Bukkit.getScheduler().runTaskTimer((Plugin) CorePlugin.getPlugin(),
				new Runnable() {

					@Override
					public void run() {
						Iterator iterator;
						Iterator iterator2 = iterator = Bukkit
								.getOnlinePlayers().iterator();
						while (iterator2.hasNext()) {
							Player OnlinePlayers = (Player) iterator.next();
							if (PlayerAntyLogoutListener.antiRelog == null) {
								return;
							}
							Damage damageData = PlayerAntyLogoutListener.antiRelog
									.get(OnlinePlayers.getUniqueId());
							if (damageData != null) {
								Long lastHit = System.currentTimeMillis()
										- damageData.time;
								if (lastHit <= 30000) {
									String time = String
											.valueOf((30000 - lastHit) / 1000);
									iterator2 = iterator;
									ChatUtil.sendActionBar(
											OnlinePlayers,
											ChatUtil.fixColor("&cAntyLogout &7{TIME}s&c.")
													.replace("{TIME}",
															(CharSequence) time));
									continue;
								}
								ChatUtil.sendTitleMessage(OnlinePlayers, ChatUtil.fixColor("&7&lWalka"), ChatUtil.fixColor("&6Możesz się bezpiecznie wylogować :)"), 0, 50, 0);
								ChatUtil.sendActionBar(
										OnlinePlayers,
										ChatUtil.fixColor("&6Mozesz sie wylogowac!"));
								PlayerAntyLogoutListener.antiRelog
										.remove(OnlinePlayers.getUniqueId());
							}
							iterator2 = iterator;
						}
					}
				}, 10, 10);
	}

}
