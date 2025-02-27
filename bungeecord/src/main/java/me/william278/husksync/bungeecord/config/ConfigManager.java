package me.william278.husksync.bungeecord.config;

import me.william278.husksync.HuskSyncBungeeCord;
import me.william278.husksync.Settings;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;

public class ConfigManager {

    private static final HuskSyncBungeeCord plugin = HuskSyncBungeeCord.getInstance();

    public static void loadConfig() {
        try {
            if (!plugin.getDataFolder().exists()) {
                if (plugin.getDataFolder().mkdir()) {
                    plugin.getBungeeLogger().info("Created HuskSync data folder");
                }
            }
            File configFile = new File(plugin.getDataFolder(), "config.yml");
            if (!configFile.exists()) {
                Files.copy(plugin.getResourceAsStream("proxy-config.yml"), configFile.toPath());
                plugin.getBungeeLogger().info("Created HuskSync config file");
            }
        } catch (Exception e) {
            plugin.getBungeeLogger().log(Level.CONFIG, "An exception occurred loading the configuration file", e);
        }
    }

    public static void saveConfig(Configuration config) {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(plugin.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            plugin.getBungeeLogger().log(Level.CONFIG, "An exception occurred loading the configuration file", e);
        }
    }

    public static void loadMessages() {
        try {
            if (!plugin.getDataFolder().exists()) {
                if (plugin.getDataFolder().mkdir()) {
                    plugin.getBungeeLogger().info("Created HuskSync data folder");
                }
            }
            File messagesFile = new File(plugin.getDataFolder(), "messages_" + Settings.language + ".yml");
            if (!messagesFile.exists()) {
                Files.copy(plugin.getResourceAsStream("languages/" + Settings.language + ".yml"), messagesFile.toPath());
                plugin.getBungeeLogger().info("Created HuskSync messages file");
            }
        } catch (Exception e) {
            plugin.getBungeeLogger().log(Level.CONFIG, "An exception occurred loading the messages file", e);
        }
    }

    public static Configuration getConfig() {
        try {
            File configFile = new File(plugin.getDataFolder(), "config.yml");
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException e) {
            plugin.getBungeeLogger().log(Level.CONFIG, "An IOException occurred fetching the configuration file", e);
            return null;
        }
    }

    public static Configuration getMessages() {
        try {
            File configFile = new File(plugin.getDataFolder(), "messages_" + Settings.language + ".yml");
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException e) {
            plugin.getBungeeLogger().log(Level.CONFIG, "An IOException occurred fetching the messages file", e);
            return null;
        }
    }

}

