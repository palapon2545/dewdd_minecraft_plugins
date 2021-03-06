/*
 *      Author: patiphat mana-u-krid (dew)
 *      E-Mail: dewtx29@gmail.com
 *      facebook: https://www.facebook.com/dewddminecraft
 */
package dewddautoseed;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import dewddtran.tr;

public class Main extends JavaPlugin {
	Logger log;
	dewddautoseed ax = new dewddautoseed();

	@Override
	public void onDisable() {
		getServer().getPluginManager().disablePlugin(this);
		dprint.r.printAll("ptdew&dewdd : " + tr.gettr("unloaded_plugin") + " dewdd autoseed");
	}

	@Override
	public void onEnable() {

		log = this.getLogger();

		ax.ac = this;

		getServer().getPluginManager().registerEvents(ax, this);

		dprint.r.printAll("ptdew&dewdd : " + tr.gettr("loaded_plugin") + " dewdd autoseed");

	}

}
