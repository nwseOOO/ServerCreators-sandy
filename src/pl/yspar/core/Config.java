package pl.yspar.core;


import org.bukkit.configuration.file.*;


import pl.yspar.core.utils.ChatUtil;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

import org.bukkit.*;

public class Config
{
    public static String DATABASE_MODE;
    public static String DATABASE_TABLEPREFIX;
    public static String DATABASE_MYSQL_HOST;
    public static int DATABASE_MYSQL_PORT;
    public static int LIMIT_KOX;
    public static int LIMIT_REF;
    public static int LIMIT_PEARL;
    public static String DATABASE_MYSQL_USER;
    public static String DATABASE_MYSQL_PASS;
    public static String DATABASE_MYSQL_NAME;
    public static String DATABASE_SQLITE_NAME;
    public static List<String> SIDEBAR_LIST;
    public static String SIDEBAR_HEADER;
    public static List<String> ANTILOGOUT_BLOCKED_COMMANDS;
    public static String TRYB;
    public static Location SPAWN;
    
    static {
        Config.DATABASE_MODE = "mysql";
        Config.DATABASE_TABLEPREFIX = "medpvpguild_";
        Config.DATABASE_MYSQL_HOST = "88.99.166.154";
        Config.DATABASE_MYSQL_PORT = 3306;
        Config.LIMIT_KOX = 1;
        Config.LIMIT_REF = 10;
        Config.LIMIT_PEARL = 3;
        Config.DATABASE_MYSQL_USER = "db_81046";
        Config.DATABASE_MYSQL_PASS = "grqzLjV7iIP2";
        Config.DATABASE_MYSQL_NAME = "db_81046";
        Config.DATABASE_SQLITE_NAME = "minecraft.db";
        Config.ANTILOGOUT_BLOCKED_COMMANDS = Arrays.asList("/spawn","/tpa", "/home","/dom", "/sethome", "ustawdom", "/depozyt", "/schowek", "/tpaccept", "/ec", "/kit", "/repair", "/wb", "/craft");
        Config.TRYB = "&6Battle Stages";
        Config.SIDEBAR_HEADER = ChatUtil.fixColor("");
        final int n5 = 15;
        final String[] array5 = new String[n5];
        array5[0] = " ";
        array5[1] = "      &8%> &6&lInformacje&8 <%";
        array5[2] = "  �8� �7Online: �e{ONLINE}";
        array5[3] = "  &8� &7Ping: &e{PING} ";
        array5[4] = " �8� �7TPS: �e19.99";
        array5[5] = " ";
        array5[6] = "      �8� �6�lStatystyki�8 �";
        array5[7] = "  �8� �7Gildia: �e{GUILD} ";
        array5[8] = "  �8� �7Ranking: �e{POINTS} ";
        array5[9] = "  &8� &7Zabojstwa: &e{KILLS} ";
        array5[10] = "  �8� �7Zgony: �e{DEATHS} ";
        array5[11] = "  &8� &7Asysty: &e{ASISTS} ";
        array5[12] = "  �8� �7K/D Ratio: �e{KD} ";
        array5[13] = " ";
        array5[14] = "  �8� �7/sidebar �eAby Wylaczyc ";
        Config.SIDEBAR_LIST = Arrays.asList(array5);
        final World world = Bukkit.getWorlds().get(0);
        final double n7 = 100.0;
        final double n8 = 0.0;
        Config.SPAWN = new Location(world, n8, n7, n8);
    }
    
    public static void loadConfig() {
        try {
            CorePlugin.getPlugin().saveDefaultConfig();
            final FileConfiguration c = CorePlugin.getPlugin().getConfig();
            Field[] fields;
            for (int length = (fields = Config.class.getFields()).length, i = 0; i < length; ++i) {
                final Field f = fields[i];
                if (c.isSet("config." + f.getName().toLowerCase().replace("_", "."))) {
                    f.set(null, c.get("config." + f.getName().toLowerCase().replace("_", ".")));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void saveConfig() {
        try {
            final FileConfiguration c = CorePlugin.getPlugin().getConfig();
            Field[] fields;
            for (int length = (fields = Config.class.getFields()).length, i = 0; i < length; ++i) {
                final Field f = fields[i];
                c.set("config." + f.getName().toLowerCase().replace("_", "."), f.get(null));
            }
            CorePlugin.getPlugin().saveConfig();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void setSpawn(final Location loc) {
    	Config.SPAWN = null;
    	saveConfig();
    	Config.SPAWN = loc;
        reloadConfig();
        loadConfig();
        saveConfig();
    }

    
    public static void reloadConfig() {
        CorePlugin.getPlugin().reloadConfig();
        loadConfig();
        saveConfig();
    }
}
