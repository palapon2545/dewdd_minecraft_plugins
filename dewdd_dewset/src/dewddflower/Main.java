/*
 *      Author: patiphat mana-u-krid (dew)
 *      E-Mail: dewtx29@gmail.com
 *      facebook: https://www.facebook.com/dewddminecraft
 */
package dewddflower;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import dewddtran.tr;

public class Main extends JavaPlugin {
	public static dewset ds = null;
	Logger log;

	@Override
	public void onDisable() {
		getServer().getPluginManager().disablePlugin(this);
		printAll("ptdew&dewdd : " + tr.gettr("unloaded_plugin") + " dewset api");
	}

	@Override
	public void onEnable() {

		log = getLogger();

		printAll("ptdew&dewdd : " + tr.gettr("loaded_plugin") + " dewset api");
		dewddflower.dewset.ac = this;
		ds = new dewset();
		ds.loadmainfile();

		printAll("dewset ds = " + (ds == null ? "null" : "not null"));

	}

	public void printA(String abc) {
		for (Player pla : Bukkit.getOnlinePlayers()) {
			pla.sendMessage(abc);
		}
	}

	public void printAll(String abc) {
		printA(abc);
		printC(abc);
	}

	public void printC(String abc) {
		System.out.println(abc);
	}
}
