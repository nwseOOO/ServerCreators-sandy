package pl.yspar.core;


import org.bukkit.plugin.java.*;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import codecrafter47.bungeetablistplus.api.bukkit.BungeeTabListPlusBukkitAPI;
import codecrafter47.bungeetablistplus.api.bukkit.Variable;
import pl.yspar.core.command.ApiCommand;
import pl.yspar.core.command.BroadcastCommand;
import pl.yspar.core.command.Command;
import pl.yspar.core.command.CommandManager;
import pl.yspar.core.command.DepositCommand;
import pl.yspar.core.command.GamemodeCommand;
import pl.yspar.core.command.GraczCommand;
import pl.yspar.core.command.IncognitoCommand;
import pl.yspar.core.command.ItemShopCommand;
import pl.yspar.core.command.KoszCommand;
import pl.yspar.core.command.LobbyCommand;
import pl.yspar.core.command.PrestizCommand;
import pl.yspar.core.command.ProfileCommand;
import pl.yspar.core.command.ResetujRankingCommand;
import pl.yspar.core.command.SaveEqCommand;
import pl.yspar.core.command.SetSpawnCommand;
import pl.yspar.core.command.SpawnCommand;
import pl.yspar.core.command.TpCommand;
import pl.yspar.core.command.TpaCommand;
import pl.yspar.core.command.VanishCommand;
import pl.yspar.core.command.sBCommand;
import pl.yspar.core.command.guild.CreateCommand;
import pl.yspar.core.command.guild.DeleteCommand;
import pl.yspar.core.command.guild.GuildHelpCommand;
import pl.yspar.core.command.guild.InfoCommand;
import pl.yspar.core.command.guild.InviteCommand;
import pl.yspar.core.command.guild.JoinCommand;
import pl.yspar.core.command.guild.LeaveCommand;
import pl.yspar.core.listener.EntityDamageByEntityListener;
import pl.yspar.core.listener.InventoryClickListener;
import pl.yspar.core.listener.ItemConsumeListener;
import pl.yspar.core.listener.JoinQuitListener;
import pl.yspar.core.listener.PlayerAntyLogoutListener;
import pl.yspar.core.listener.PlayerCommandPreprocessListener;
import pl.yspar.core.listener.PlayerDeathListener;
import pl.yspar.core.listener.PlayerInteractListener;
import pl.yspar.core.listener.PlayerRespawnListener;
import pl.yspar.core.manager.GuildManager;
import pl.yspar.core.manager.TagManager;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.sidebar.ScoreboardStack;
import pl.yspar.core.sidebar.SidebarRunnable;
import pl.yspar.core.store.MySQL;
import pl.yspar.core.tab.AssistsVariable;
import pl.yspar.core.tab.DeathsVariable;
import pl.yspar.core.tab.GuildDeathsVariable;
import pl.yspar.core.tab.GuildKdVariable;
import pl.yspar.core.tab.GuildKillsVariable;
import pl.yspar.core.tab.GuildPointsVariable;
import pl.yspar.core.tab.GuildVariable;
import pl.yspar.core.tab.IncognitoVariable;
import pl.yspar.core.tab.KDVariable;
import pl.yspar.core.tab.KillsVariable;
import pl.yspar.core.tab.PointsVariable;
import pl.yspar.core.task.ActionTask;
import pl.yspar.core.task.AntiLogoutRunnable;
import pl.yspar.core.task.DiscoTask;
import pl.yspar.core.task.LimitRunnable;
import pl.yspar.core.task.ParticleRunnable;
import pl.yspar.core.task.RefreshThread;
import pl.yspar.core.task.TagRunnable;
import pl.yspar.core.tops.TopAssistsVariable;
import pl.yspar.core.tops.TopDeathsVariable;
import pl.yspar.core.tops.TopGuildPointsVariable;
import pl.yspar.core.tops.TopKillsVariable;
import pl.yspar.core.tops.TopKsVariable;
import pl.yspar.core.tops.TopPointsVariable;
import pl.yspar.core.tops.TopPrestizVariable;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.Logger;
import pl.yspar.core.utils.Util;

import org.bukkit.plugin.*;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;

import org.bukkit.event.*;


public class CorePlugin extends JavaPlugin implements PluginMessageListener {
    private static CorePlugin plugin;
    private static PluginManager pluginManager;
    
    public void onLoad() {
        CorePlugin.plugin = this;
    }
    
    
    public void onEnable() {
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        this.initTab();
		TagManager.init();
	      Config.reloadConfig();
		Bukkit.getConsoleSender()
		.sendMessage(
				Util.fixColor("&aTrwa ladowanie bazy &cMYSQL &7..."));
		MySQL.getInst().save();
		MySQL.getInst().load();
        this.registerListener();
        Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin)this, (Runnable)new ParticleRunnable(), 11L, 11L);
		new ActionTask().runTaskTimerAsynchronously((Plugin)this, 40L, 20L);
		new RefreshThread().start();
		new LimitRunnable().start();
		AntiLogoutRunnable.start();
		new ScoreboardStack().start();
		new TagRunnable().start();
		new SidebarRunnable().start();
        registerCommand();
		Bukkit.getConsoleSender() 
		.sendMessage(
				Util.fixColor("&aTrwa ladowanie graczy..."));
		Bukkit.getScheduler().runTaskLater((Plugin) this,
		(Runnable) new Runnable() {
			@Override
			public void run() {
				UserManager.registerPlayers();
			}
		}, 60L);

    }
    private static String serverName;
    private static ByteArrayOutputStream b = new ByteArrayOutputStream();
    private static DataOutputStream out = new DataOutputStream(b);
    private static int playerCount;



public int getPlayerCount(String serverName) {
    try {
        out.writeUTF("PlayerCount");
        out.writeUTF(serverName);
        Player p = Bukkit.getPlayer(Bukkit.getOnlinePlayers().iterator().next().getName());
        p.sendPluginMessage(this, "BungeeCord", b.toByteArray());
    } catch (IOException e1) {
        e1.printStackTrace();
    }
    return playerCount;
}

@SuppressWarnings("static-access")
public void onPluginMessageReceived(String channel, Player player,
        byte[] message) {
    if (!channel.equals("BungeeCord")) {
        return;
    }

    DataInputStream in = new DataInputStream(new ByteArrayInputStream(
            message));

    try {
        String subchannel = in.readUTF();
        if (subchannel.equals("PlayerCount")) {
            this.serverName = in.readUTF();
            this.playerCount = in.readInt();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
    
    public void onDisable() {

        Bukkit.getScheduler().cancelTasks((Plugin)this);
        Bukkit.savePlayers();
        for (final World w : Bukkit.getWorlds()) {
            w.save();
        }
        try {
            Thread.sleep(2000L);
        }
        catch (InterruptedException e2) {
            e2.printStackTrace();
        }
		UserManager.unregisterPlayers();
		MySQL.getInst().save();
    }
    
    public static CorePlugin getPlugin() {
        return CorePlugin.plugin;
    }
    

    public static void registerCommand(final Command command) {
        CommandManager.register(command);
    }
    
    public static void registerListener(final Plugin plugin, final Listener... listeners) {
        if (CorePlugin.pluginManager == null) {
            CorePlugin.pluginManager = Bukkit.getPluginManager();
        }
        for (final Listener listener : listeners) {
            CorePlugin.pluginManager.registerEvents(listener, plugin);
        }
    }
    
    public static void registerCommand() {
        registerCommand(new IncognitoCommand());
        registerCommand(new sBCommand());
        registerCommand(new CreateCommand());
        registerCommand(new DeleteCommand());
        registerCommand(new GuildHelpCommand());
        registerCommand(new InfoCommand());
        registerCommand(new InviteCommand());
        registerCommand(new JoinCommand());
        registerCommand(new KoszCommand());
        registerCommand(new GraczCommand());
        registerCommand(new LeaveCommand());
        registerCommand(new ItemShopCommand());
        registerCommand(new VanishCommand());
        registerCommand(new SetSpawnCommand());
        registerCommand(new SpawnCommand());
        registerCommand(new ApiCommand());
        registerCommand(new DepositCommand());
        registerCommand(new BroadcastCommand());
        registerCommand(new PrestizCommand());
        registerCommand(new SaveEqCommand());
        registerCommand(new LobbyCommand());
        registerCommand(new GamemodeCommand());
        registerCommand(new TpCommand());
        registerCommand(new ProfileCommand());
        registerCommand(new ResetujRankingCommand());
    }
   
    
    private void initTab() {
        BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new PointsVariable("points"));
        BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new IncognitoVariable("incognito"));
        BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new KillsVariable("kills"));
        BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new DeathsVariable("deaths"));
        BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new AssistsVariable("assists"));
        BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new KDVariable("kd"));
        BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new GuildVariable("guild"));
        BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new GuildPointsVariable("gpoints"));
        BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new GuildKillsVariable("gkills"));
        BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new GuildDeathsVariable("gdeaths"));
        BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new GuildKdVariable("gkd"));
        for (int i = 1; i < 16; ++i) {
            BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new TopPointsVariable("pointstop" + i, i));
            BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new TopKillsVariable("killstop" + i, i));
            BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new TopKsVariable("kstop" + i, i));
            BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new TopKsVariable("ksmaxtop" + i, i));
            BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new TopDeathsVariable("deathstop" + i, i));
            BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new TopAssistsVariable("assiststop" + i, i));
            BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new TopGuildPointsVariable("gpointstop" + i, i));
            BungeeTabListPlusBukkitAPI.registerVariable((Plugin)this, (Variable)new TopPrestizVariable("prestizstop" + i, i));
        }
    }
    
    public void registerListener() {
    	
        registerListener((Plugin)this, (Listener)new PlayerCommandPreprocessListener());
        registerListener((Plugin)this, (Listener)new JoinQuitListener());
        registerListener((Plugin)this, (Listener)new PlayerInteractListener());
        registerListener((Plugin)this, (Listener)new ItemConsumeListener());
        registerListener((Plugin)this, (Listener)new InventoryClickListener());
        registerListener((Plugin)this, (Listener)new PlayerRespawnListener());
        registerListener((Plugin)this, (Listener)new PlayerDeathListener());
        registerListener((Plugin)this, (Listener)new EntityDamageByEntityListener());
        registerListener((Plugin)this, (Listener)new PlayerAntyLogoutListener());
    }
}

