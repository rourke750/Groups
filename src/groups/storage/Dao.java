package groups.storage;

import groups.Groups;
import groups.manager.ConfigManager;
import groups.model.Group;
import groups.model.Member;

import java.util.Arrays;
import java.util.List;

public class Dao extends MyDatabase {

	Groups plugin;
	ConfigManager configManager;
	
	public Dao() {
		super(Groups.getInstance());
		
		plugin = Groups.getInstance();
		configManager = plugin.getConfigManager();
		
		initializeDatabase(
			configManager.getDriver(),
			configManager.getUrl(),
			configManager.getUsername(),
			configManager.getPassword(),
			configManager.getIsolation(),
			configManager.isLogging(),
			true
		);
		
		generateTables();
	}
	
	@Override
	protected List<Class<?>> getDatabaseClasses() {
		return Arrays.asList(
			Group.class,
			Member.class
		);
	}
	
	private void generateTables() {
		//System.out.println(getDatabase().find(Group.class));
	}
	
	public void save(Object object) {
		getDatabase().save(object);
	}

}
