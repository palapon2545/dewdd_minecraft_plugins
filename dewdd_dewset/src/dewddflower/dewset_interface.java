/*
 *      Author: patiphat mana-u-krid (dew)
 *      E-Mail: dewtx29@gmail.com
 *      facebook: https://www.facebook.com/dewddminecraft
 */
package dewddflower;

import org.bukkit.entity.Player;

public abstract class dewset_interface extends dewsetdatabase {

	public String	flag_everyone				= "everyone";
	public String	flag_monster				= "<monster>";
	public String	flag_noinwater				= "<noinwater>";

	public String	flag_nooutwater				= "<nooutwater>";

	public String	flag_protectanimal			= "<protectanimal>";

	public String	flag_protecthopper			= "<protecthopper>";
	public String	flag_pvp					= "<pvp>";

	public String	flag_sell					= "<sell>";

	public int		selectmax					= 29;

	public int		dewsignlimit				= 200;

	public int		dewsignnamemax				= 20;

	public String	pmaindelete					= "dewdd.main.delete";
	public String	pmaindewbuychangehost		= "dewdd.main.dewbuy.changehost";
	public String	pmaindewbuydelete			= "dewdd.main.dewbuydelete";
	public String	pmaindewbuymodifymember		= "dewdd.main.dewbuy.modifymember";

	public String	pmaindewbuynotcount			= "dewdd.main.dewbuy.notcount";

	public String	pmaindewbuyreplace			= "dewdd.main.dewbuy.replace";

	public String	pmaindewseteverywhere		= "dewdd.main.dewset.everywhere";

	public String	pmaininfinite				= "dewdd.main.dewset.infinite";

	public String	pmainoveride				= "dewdd.main.overide";

	public String	pmainplaceblocknoprotect	= "dewdd.main.placeblock.noprotect";

	public String	flag_sign					= "<sign>";

	public String	flag_stopwater				= "<stopwater>";

	public abstract boolean dewps_add(String message, Player player);

	public abstract boolean dewps_list(Player player);

	public abstract boolean dewps_remove(String message, Player player);

}
