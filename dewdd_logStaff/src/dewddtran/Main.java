/*
 *      Author: patiphat mana-u-krid (dew)
 *      E-Mail: dewtx29@gmail.com
 *      facebook: https://www.facebook.com/dewddminecraft
 */
package dewddtran;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	Logger	log;
	tranrun	ax	= new tranrun();

	@Override
	public void onDisable() {
		getServer().getPluginManager().disablePlugin(this);
		System.out.println("ptdew&dewdd : unloaded dewdd log :D");
	}

	@Override
	public void onEnable() {

		log = this.getLogger();

		ax.ac = this;
		getServer().getPluginManager().registerEvents(ax, this);
		System.out.println("ptdew&dewdd : loaded dewdd log :D");

		tr.loadStaffFile();

	}

}
