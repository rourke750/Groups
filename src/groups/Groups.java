package groups;

import groups.command.CommandHandler;
import groups.listener.PlayerListener;
import groups.manager.ConfigManager;
import groups.manager.GroupManager;
import groups.storage.Dao;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Groups extends JavaPlugin {

	private static Groups instance;
	private ConfigManager configManager;
	private Dao dao;
	private GroupManager groupManager;
	private CommandHandler commandHandler;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return commandHandler.dispatch(sender, label, args);
	}
	
	public void onEnable() {
		instance = this;
		configManager = new ConfigManager();
		dao = new Dao();
		groupManager = new GroupManager();
		commandHandler = new CommandHandler();
		commandHandler.registerCommands();
		groupManager.loadGroups();
		registerEvents();
		System.out.println("Groups Enabled");
	}
	
	public void onDisable() {
		
	}
	
	public static Groups getInstance() {
		return instance;
	}
	
	public ConfigManager getConfigManager() {
		return configManager;
	}
	
	public Dao getDao() {
		return dao;
	}
	
	public GroupManager getGroupManager() {
		return groupManager;
	}
	
	private void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerListener(), this);
	}

}
