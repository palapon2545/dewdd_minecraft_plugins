/*
 *      Author: patiphat mana-u-krid (dew)
 *      E-Mail: dewtx29@gmail.com
 *      facebook: https://www.facebook.com/dewddminecraft
 */
package dewddsetline;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	Logger				log;
	DigEventListener2	ax	= new DigEventListener2();

	@Override
	public void onDisable() {
		getServer().getPluginManager().disablePlugin(this);
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage("ptdew&dewdd: unloaded dewdd setline");
		}

	}

	@Override
	public void onEnable() {

		log = this.getLogger();

		ax.ac = this;
		getServer().getPluginManager().registerEvents(ax, this);

		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage("ptdew&dewdd: loaded dewdd setline");
		}

	}
}
