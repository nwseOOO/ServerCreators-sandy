package pl.yspar.core.gui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import pl.yspar.core.CorePlugin;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.User;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.ItemBuilder;
import pl.yspar.core.utils.Util;

public class LobbyGui
{
  
  public static boolean open(Player p)
  {
	    final User u = User.get(p);  
	    Guild g = User.get(p).getGuild();
    	final ItemBuilder powrot = new ItemBuilder(Material.getMaterial(405), CorePlugin.getPlugin().getPlayerCount("lobby_1")).setName(ChatUtil.fixColor("&7→ &e&o&lLobby serwerowe &7←"))
        		.addLore(ChatUtil.fixColor(" "))
        		.addLore(ChatUtil.fixColor("&7Graczy na trybie&8: &6" + CorePlugin.getPlugin().getPlayerCount("lobby_1")))
        		.addLore(ChatUtil.fixColor(" "))
        		.addLore(ChatUtil.fixColor("&7→ &7Status: &6Online"))
        		.addLore(ChatUtil.fixColor("&7→ &e&oKliknij aby dołączyć"));
        Inventory inv = Bukkit.createInventory((InventoryHolder) p, 54, Util.fixColor("&6Wybór trybu"));
        final ItemBuilder battle = new ItemBuilder(Material.getMaterial(299),  CorePlugin.getPlugin().getPlayerCount("battle"))
        		.setName(ChatUtil.fixColor("&7→ &e&o&lBattle stages &7←"))
        		.addLore(ChatUtil.fixColor(" "))
        		.addLore(ChatUtil.fixColor("&7Graczy na trybie&8: &6" + CorePlugin.getPlugin().getPlayerCount("battle")))
        		.addLore(ChatUtil.fixColor(" "))
        		.addLore(ChatUtil.fixColor("&7→ &7Status: &6Online"))
        		.addLore(ChatUtil.fixColor("&7→ &e&oKliknij aby dołączyć"));
        
        final ItemBuilder practice = new ItemBuilder(Material.getMaterial(276), CorePlugin.getPlugin().getPlayerCount("practice"))
        		.setName(ChatUtil.fixColor("&7→ &e&o&lPractice &7←"))
        		.addLore(ChatUtil.fixColor(" "))
        		.addLore(ChatUtil.fixColor("&7Graczy na trybie&8: &6" + CorePlugin.getPlugin().getPlayerCount("practice")))
        		.addLore(ChatUtil.fixColor(" "))
        		.addLore(ChatUtil.fixColor("&7→ &7Status: &6Online"))
        		.addLore(ChatUtil.fixColor("&7→ &e&oKliknij aby dołączyć"));
        
        final ItemBuilder krety = new ItemBuilder(Material.getMaterial(257), CorePlugin.getPlugin().getPlayerCount("krety"))
        		.setName(ChatUtil.fixColor("&7→ &e&o&lKrety &7←"))
        		.addLore(ChatUtil.fixColor(" "))
        		.addLore(ChatUtil.fixColor("&7Graczy na trybie&8: &6" + CorePlugin.getPlugin().getPlayerCount("krety")))

        		.addLore(ChatUtil.fixColor(" "))
        		.addLore(ChatUtil.fixColor("&7→ &7Status: &6Online"))
        		.addLore(ChatUtil.fixColor("&7→ &e&oKliknij aby dołączyć"));
        
        final ItemBuilder trening = new ItemBuilder(Material.getMaterial(165), CorePlugin.getPlugin().getPlayerCount("trening"))
        		.setName(ChatUtil.fixColor("&7→ &e&o&lTrening &7←"))
        		.addLore(ChatUtil.fixColor(" "))
        		.addLore(ChatUtil.fixColor("&7Graczy na trybie&8: &6" + CorePlugin.getPlugin().getPlayerCount("trening")))
        		.addLore(ChatUtil.fixColor(" "))
        		.addLore(ChatUtil.fixColor("&7→ &7Status: &6Online"))
        		.addLore(ChatUtil.fixColor("&7→ &e&oKliknij aby dołączyć"));
        
        final ItemBuilder event = new ItemBuilder(Material.getMaterial(399), CorePlugin.getPlugin().getPlayerCount("event"))
        		.setName(ChatUtil.fixColor("&7→ &e&o&lEvent &7←"))
        		.addLore(ChatUtil.fixColor(" "))
        		.addLore(ChatUtil.fixColor("&7Graczy na trybie&8: &6" + CorePlugin.getPlugin().getPlayerCount("event")))
        		.addLore(ChatUtil.fixColor(" "))
        		.addLore(ChatUtil.fixColor("&7→ &7Status: &6Online"))
        		.addLore(ChatUtil.fixColor("&7→ &e&oKliknij aby dołączyć"));
        
        final ItemBuilder ffa = new ItemBuilder(Material.getMaterial(322), CorePlugin.getPlugin().getPlayerCount("ffa"))
        		.setName(ChatUtil.fixColor("&7→ &e&o&lFFa &7←"))
        		.addLore(ChatUtil.fixColor(" "))
        		.addLore(ChatUtil.fixColor("&7Graczy na trybie&8: &6" + CorePlugin.getPlugin().getPlayerCount("ffa")))
        		.addLore(ChatUtil.fixColor(" "))
        		.addLore(ChatUtil.fixColor("&7→ &7Status: &6Online"))
        		.addLore(ChatUtil.fixColor("&7→ &e&oKliknij aby dołączyć"));
        
        ItemStack cipa2 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta cips2 = (SkullMeta) cipa2.getItemMeta();
        cips2.setDisplayName(p.getName());
        ArrayList<String> xlores22 = new ArrayList<String>();
        cips2.setDisplayName(ChatUtil.fixColor("&6Wybierz swój tryb."));
        cips2.setLore(xlores22);
        cips2.setOwner("" + p.getName());
        cipa2.setItemMeta(cips2);
        final ItemBuilder s1 = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)15).setName(ChatUtil.fixColor(""));
        final ItemBuilder s2 = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)15).setName(ChatUtil.fixColor(""));
        final ItemBuilder s3 = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)15).setName(ChatUtil.fixColor(""));
        inv.setItem(inv.getSize() - 1, s1.build()); //koniec
        inv.setItem(inv.getSize() - 2, s2.build());
        inv.setItem(inv.getSize() - 3, s2.build());
        inv.setItem(inv.getSize() - 4, s3.build());
        inv.setItem(inv.getSize() - 5, powrot.build());
        inv.setItem(inv.getSize() - 6, s3.build());
        inv.setItem(inv.getSize() - 7, s2.build());
        inv.setItem(inv.getSize() - 8, s2.build());
        inv.setItem(inv.getSize() - 9, s1.build());
        inv.setItem(inv.getSize() - 10, s2.build());
        inv.setItem(inv.getSize() - 18, s2.build());
        inv.setItem(inv.getSize() - 19, s2.build());
        inv.setItem(inv.getSize() - 27, s2.build());
        inv.setItem(inv.getSize() - 43, battle.build());
        inv.setItem(inv.getSize() - 41, practice.build());
        inv.setItem(inv.getSize() - 39, krety.build());
        inv.setItem(inv.getSize() - 25, trening.build());
        inv.setItem(inv.getSize() - 23, event.build());
        inv.setItem(inv.getSize() - 21, ffa.build());
        inv.setItem(inv.getSize() - 28, s2.build());
        inv.setItem(inv.getSize() - 36, s2.build());
        inv.setItem(inv.getSize() - 37, s2.build());
        inv.setItem(inv.getSize() - 45, s2.build());
        inv.setItem(inv.getSize() - 46, s1.build());
        inv.setItem(inv.getSize() - 47, s2.build());
        inv.setItem(inv.getSize() - 48, s2.build());
        inv.setItem(inv.getSize() - 49, s3.build());
        inv.setItem(inv.getSize() - 50, cipa2);
        inv.setItem(inv.getSize() - 51, s3.build());
        inv.setItem(inv.getSize() - 52, s2.build());
        inv.setItem(inv.getSize() - 53, s2.build());
        inv.setItem(inv.getSize() - 54, s1.build()); //poczatek
        p.openInventory(inv);
		return false;
  }
  
  public static int getOnlinePlayers(String server, Player p) {
      ByteArrayInputStream b = new ByteArrayInputStream(new byte[0]);
      DataInputStream in = new DataInputStream(b);
      ByteArrayDataOutput out = ByteStreams.newDataOutput();
      out.writeUTF("PlayerCount");
      out.writeUTF(server);
      p.sendPluginMessage(CorePlugin.getPlugin(), "BungeeCord", out.toByteArray());
      try {
          return in.readInt();
      } catch (IOException e) {}
      return 0;
  }
  

}