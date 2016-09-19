/*
 *      Author: patiphat mana-u-krid (dew)
 *      E-Mail: dewtx29@gmail.com
 *      facebook: https://www.facebook.com/dewddminecraft
 */
package print;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	Logger	log;

	@Override
	public void onDisable() {
		getServer().getPluginManager().disablePlugin(this);
		printAll("ptdew&dewdd : unloaded dew print api");
	}

	@Override
	public void onEnable() {

		log = this.getLogger();

		printAll("ptdew&dewdd : loaded dew print api");

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
