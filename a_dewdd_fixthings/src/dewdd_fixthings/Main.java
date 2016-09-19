/*
 *      Author: patiphat mana-u-krid (dew)
 *      E-Mail: dewtx29@gmail.com
 *      facebook: https://www.facebook.com/dewddminecraft
 */
package dewdd_fixthings;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	Logger				log;
	DigEventListener2	ax	= new DigEventListener2();

	@Override
	public void onDisable() {

		this.getServer().getPluginManager().disablePlugin(this);
		d.pl("ptdew&dewdd : unloaded dewdd fixThings "
				+ DigEventListener2.version);
	}

	@Override
	public void onEnable() {

		this.log = this.getLogger();

		this.ax.ac = this;
		this.getServer().getPluginManager().registerEvents(this.ax, this);
		d.pl("ptdew&dewdd : loaded dewdd fixThings "
				+ DigEventListener2.version);

	}

}
