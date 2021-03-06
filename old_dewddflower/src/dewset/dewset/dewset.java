/*
 *      Author: patiphat mana-u-krid (dew)
 *      E-Mail: dewtx29@gmail.com
 *      facebook: https://www.facebook.com/dewddminecraft
 */
package dewddflower;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

public class dewset extends dewddadmin {

	// cutwoodsub
	class cutwoodsubc implements Runnable {
		Player	player123	= null;
		Block	block123	= null;

		public void run() {
			try {
				int idc = getfreeselect(player123);
				if (dewaxe[idc] == false) {
					return;
				}
				switch (player123.getItemInHand().getTypeId()) {
				case 279:
				case 258:
				case 271:
				case 275:
				case 286:
					break;
				default:
					return;
				}

				int gx = 0;
				int gy = 0;
				int gz = 0;
				int g4 = 2;

				if (block123.getTypeId() == 0) {
					return;
				}

				switch (block123.getTypeId()) {
				case 103:
				case 86:
				case 17:

					for (int bx = -3; bx <= 3; bx++) {
						for (int by = -3; by <= 3; by++) {
							for (int bz = -3; bz <= 3; bz++) {
								Block block2 = block123.getRelative(bx, by, bz);
								if (block2.getTypeId() == 18) {
									block2.breakNaturally();
								}

							}
						}
					}

					break;
				case 83:
					if (block123.getRelative(0, -1, 0).getTypeId() != 83) {
						return;
					}
					break;
				case 59:
					if (block123.getData() != 7) {
						return;
					}
					break;
				case 141:
					if (block123.getData() != 7) {
						return;
					}
					break;
				case 142:
					if (block123.getData() != 7) {
						return;
					}
					break;

				default:
					return;
				}

				if (checkpermissionarea(block123, player123, "delete") == true) {
					return;
				}

				switch (player123.getItemInHand().getTypeId()) {
				case 279:
				case 258:
				case 271:
				case 275:
				case 286:

					// player123.sendMessage("ptdew&dewdd: "+
					// block123.getTypeId() +
					// " cut wood (" + block123.getX() + "," + block123.getY() +
					// ","
					// + block123.getZ() + ")");

					switch (block123.getRelative(0, -1, 0).getTypeId()) {
					case 2:
					case 3:
						if (block123.getTypeId() == 17) {
							int datate = block123.getData();
							if (decreseitem1(player123, 6, datate, true) == true) {
								block123.breakNaturally(player123
										.getItemInHand());
								block123.setTypeId(6);
								block123.setData((byte) datate);
							}
							else {
								block123.breakNaturally(player123
										.getItemInHand());
							}
						}
						else {
							block123.breakNaturally(player123.getItemInHand());
						}
						break;
					default:
						block123.breakNaturally(player123.getItemInHand());
						break;
					}

					if (player123.getItemInHand().getDurability() >= player123
							.getItemInHand().getType().getMaxDurability()) {
						return;
					}
					player123.getItemInHand()
							.setDurability(
									(short) (player123.getItemInHand()
											.getDurability() + 1));

					break;
				default:
					return;
				}

				World world = block123.getWorld();

				for (gx = block123.getX() - g4; gx <= block123.getX() + g4; gx++) {

					for (gz = block123.getZ() - g4; gz <= block123.getZ() + g4; gz++) {
						for (gy = block123.getY() + g4; gy >= block123.getY()
								- g4; gy--) {
							Block blockt1 = world.getBlockAt(gx, gy, gz);

							if (block123.getTypeId() == 17) {
								switch (block123.getRelative(0, -1, 0)
										.getTypeId()) {
								case 17:
								case 2:
								case 3:
								case 0:
								case 18:
									break;
								default:
									continue;
								}
							}

							cutwoodsub(blockt1, player123);

						}
					}
				}

				return;

			}
			catch (Exception e) {// Catch exception if any
				System.err.println("Error cutwood: " + e.getMessage());
			}
		}
	}

	// blockgoldsugarcane
	class harvestblockgoldsugarcanec implements Runnable {
		Block	block	= null;
		Player	player	= null;

		public void run() {

			int digX = block.getX();
			int digY = block.getY();
			int digZ = block.getZ();
			int d5 = d4[getfreeselect(player)];

			int ex = 0;
			int ey = 0;
			int ez = 0;

			try {
				World world = block.getWorld();

				for (ex = digX - d5; ex <= digX + d5; ex++) {
					for (ey = digY + 1; ey <= digY + 2; ey++) {
						for (ez = digZ - d5; ez <= digZ + d5; ez++) {

							Block blockCut = world.getBlockAt(ex, ey, ez);
							if (checkpermissionarea(blockCut, player, "destroy") == true) {
								continue;
							}

							if (blockCut.getTypeId() == 83) {

								Location ac = player.getLocation();
								ac.setX(player.getLocation().getX());
								ac.setY(player.getLocation().getY() + 1);
								ac.setZ(player.getLocation().getZ());

								Block aboveplayer = world.getBlockAt(ac);
								aboveplayer.setTypeId(83);
								blockCut.setTypeId(0);

								aboveplayer.breakNaturally();
								// money = money - 0.0359;

							}

						} // for
					} // for
				} // for

				player.sendMessage("ptdew&dewdd: BlockOfGold sugar cane done");

			}
			catch (Exception e) {
				return;
			}
		}
	}
	// blockgoldsugarcane
	class harvestgoldpumpkinc implements Runnable {
		Block	block	= null;
		Player	player	= null;

		public void run() {

			int digX = block.getX();
			int digY = block.getY();
			int digZ = block.getZ();
			int d5 = d4[getfreeselect(player)];
			int ex = 0;

			int ez = 0;

			try {
				World world = block.getWorld();
				d5 = d4[getfreeselect(player)];

				for (ex = digX - d5; ex <= digX + d5; ex++) {
					// for (ey = digY-d4; ey <= digY+d4; ey++) {
					for (ez = digZ - d5; ez <= digZ + d5; ez++) {

						Block blockCut = world.getBlockAt(ex, digY, ez);
						if (checkpermissionarea(blockCut, player, "destroy") == true) {
							continue;
						}

						if (blockCut.getTypeId() == 86) {
							Location ac = player.getLocation();
							ac.setX(player.getLocation().getX());
							ac.setY(player.getLocation().getY() + 1);
							ac.setZ(player.getLocation().getZ());

							Block aboveplayer = world.getBlockAt(ac);
							aboveplayer.setTypeId(86);
							blockCut.setTypeId(0);

							aboveplayer.breakNaturally();

							blockCut.breakNaturally();
							// money = money - 0.04875;
							// 0.734375
						}
					}
					// }
				}
				player.sendMessage("ptdew&dewdd:goldore Pumkin done");
			}
			catch (Exception e) {
				return;
			}
		}
	}

	// goldpumpkin
	class harvestriceblockironc implements Runnable {
		Block	block	= null;
		Player	player	= null;

		public void run() {
			int digX = block.getX();
			int digY = block.getY();
			int digZ = block.getZ();
			int d5 = d4[getfreeselect(player)];
			int ex = 0;

			int ez = 0;

			try {

				World world = block.getWorld();
				d5 = d4[getfreeselect(player)];

				for (ex = digX - d5; ex <= digX + d5; ex++) {
					// for (ey = digY-d4; ey <= digY+d4; ey++) {
					for (ez = digZ - d5; ez <= digZ + d5; ez++) {

						Block blockCut = world.getBlockAt(ex, digY, ez);
						if (checkpermissionarea(blockCut, player, "destroy") == true) {
							continue;
						}

						if (blockCut.getTypeId() == 59
								&& blockCut.getData() == 7
								|| blockCut.getTypeId() == 141
								&& blockCut.getData() == 7
								|| blockCut.getTypeId() == 142
								&& blockCut.getData() == 7) {
							Location ac = player.getLocation();
							ac.setX(player.getLocation().getX());
							ac.setY(player.getLocation().getY() + 1);
							ac.setZ(player.getLocation().getZ());

							for (ItemStack dropja : blockCut.getDrops()) {

								world.dropItemNaturally(ac, dropja);
								if (blockCut.getTypeId() == 59) {
									dropja.setAmount(1);
									dropja.setTypeId(295);
									world.dropItemNaturally(ac, dropja);

									if (randomG.nextInt(100) < 50) {
										dropja.setAmount(1);
										dropja.setTypeId(295);
										world.dropItemNaturally(ac, dropja);
									}
								}

								if (blockCut.getTypeId() == 141) {
									dropja.setAmount(1);
									dropja.setTypeId(295);
									world.dropItemNaturally(ac, dropja);

									if (randomG.nextInt(600) < 500) {
										dropja.setAmount(1);
										dropja.setTypeId(391);
										world.dropItemNaturally(ac, dropja);
									}
									if (randomG.nextInt(600) < 500) {
										dropja.setAmount(1);
										dropja.setTypeId(391);
										world.dropItemNaturally(ac, dropja);
									}
									if (randomG.nextInt(600) < 500) {
										dropja.setAmount(1);
										dropja.setTypeId(391);
										world.dropItemNaturally(ac, dropja);
									}
									if (randomG.nextInt(600) < 500) {
										dropja.setAmount(1);
										dropja.setTypeId(391);
										world.dropItemNaturally(ac, dropja);
									}

								}

								if (blockCut.getTypeId() == 142) {
									dropja.setAmount(1);
									dropja.setTypeId(295);
									world.dropItemNaturally(ac, dropja);

									if (randomG.nextInt(600) < 500) {
										dropja.setAmount(1);
										dropja.setTypeId(392);
										world.dropItemNaturally(ac, dropja);
									}
									if (randomG.nextInt(600) < 500) {
										dropja.setAmount(1);
										dropja.setTypeId(392);
										world.dropItemNaturally(ac, dropja);
									}
									if (randomG.nextInt(600) < 500) {
										dropja.setAmount(1);
										dropja.setTypeId(392);
										world.dropItemNaturally(ac, dropja);
									}
									if (randomG.nextInt(600) < 500) {
										dropja.setAmount(1);
										dropja.setTypeId(392);
										world.dropItemNaturally(ac, dropja);
									}

								}

							}

							blockCut.setTypeId(0);
						}
					}
					// }
				}
				player.sendMessage("ptdew&dewdd:Iron Rice done");
			}
			catch (Exception e) {
				return;
			}
		}
	}
	class harvestsandmelonc implements Runnable {
		Block	block	= null;
		Player	player	= null;

		public void run() {
			int digX = block.getX();
			int digY = block.getY();
			int digZ = block.getZ();
			int d5 = d4[getfreeselect(player)];
			int ex = 0;

			int ez = 0;

			try {
				World world = block.getWorld();
				d5 = d4[getfreeselect(player)];

				for (ex = digX - d5; ex <= digX + d5; ex++) {
					// for (ey = digY-d4; ey <= digY+d4; ey++) {
					for (ez = digZ - d5; ez <= digZ + d5; ez++) {

						Block blockCut = world.getBlockAt(ex, digY, ez);
						if (checkpermissionarea(blockCut, player, "destroy") == true) {
							continue;
						}

						if (blockCut.getTypeId() == 103) {
							Location ac = player.getLocation();
							ac.setX(player.getLocation().getX());
							ac.setY(player.getLocation().getY() + 1);
							ac.setZ(player.getLocation().getZ());

							Block aboveplayer = world.getBlockAt(ac);
							aboveplayer.setTypeId(103);
							blockCut.setTypeId(0);

							aboveplayer.breakNaturally();

							blockCut.breakNaturally();

							// 2.03125

						}
					}
					// }
				}
				player.sendMessage("ptdew&dewdd:goldore Pumkin done");
			}
			catch (Exception e) {
				return;
			}

		}
	}

	class item55deletec implements Runnable {

		Block	block	= null;
		Player	player	= null;
		int		id		= -29;
		int		dat		= -29;

		public void run() {

			if (block.getTypeId() != id) {
				return;
			}

			if (dat != -29 && block.getData() != dat) {
				return;
			}

			try {
				if (Economy.getMoney(player.getName()) < 100) {
					return;
				}
			}
			catch (UserDoesNotExistException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (amount3 > player.getItemInHand().getAmount() * 100) {
				return;
			}

			if (checkpermissionarea(block, player, "delete") == true) {
				return;
			}
			if (isprotectitemid(block.getTypeId()) == true) {
				return;
			}

			try {
				Economy.subtract(player.getName(), 10);
			}
			catch (UserDoesNotExistException | NoLoanPermittedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			amount3++;

			block.breakNaturally();
			Block b2 = null;

			for (int x = -1; x <= 1; x++) {
				for (int y = -1; y <= 1; y++) {
					for (int z = -1; z <= 1; z++) {
						b2 = block.getRelative(x, y, z);

						if (amount2 > 100) {
							return;
						}
						amount2++;
						try {
							item55delete(b2, player, id, dat);
						}
						catch (UserDoesNotExistException
								| NoLoanPermittedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						amount2--;
					}
				}
			}

		}
	}

	class soiladdseedc implements Runnable {
		Block	block	= null;
		Player	player	= null;
		int		seedid	= 0;

		public void run() {
			int digX = block.getX();
			int digY = block.getY();
			int digZ = block.getZ();

			int itemaddid = 0;
			switch (seedid) {
			case 392: // potato
				itemaddid = 142;
				break;
			case 391: // carot
				itemaddid = 141;
				break;
			case 295:
				itemaddid = 59;
				break;
			/*
			 * case 361: itemaddid = 104; break; case 362: itemaddid = 105;
			 * break;
			 */
			default:
				return;
			}

			byte itemadddata = 0;

			World world = player.getWorld();

			Block blockCut = world.getBlockAt(digX, digY, digZ);

			if (blockCut.getRelative(0, -1, 0).getTypeId() != 60) {
				return;
			}

			if (blockCut.getTypeId() != 0) {
				return;
			}

			if (checkpermissionarea(blockCut, player, "build") == true) {
				return;
			}
			if (checkpermissionarea(blockCut.getRelative(0, -1, 0), player,
					"build") == true) {
				return;
			}

			boolean haswater = false;

			for (int xx3 = 4; xx3 >= -4; xx3--) {
				for (int zz3 = 4; zz3 >= -4; zz3--) {
					if (blockCut.getRelative(xx3, -1, zz3).getTypeId() == 9
							|| blockCut.getRelative(xx3, -1, zz3).getTypeId() == 8
							|| blockCut.getRelative(xx3, 0, zz3).getTypeId() == 9
							|| blockCut.getRelative(xx3, 0, zz3).getTypeId() == 8) {
						haswater = true;
						break;
					}
				}

				if (haswater == true) {
					break;
				}
			}

			if (haswater == false) {
				return;
			}

			if (blockCut.getLightLevel() < 9) {
				return;
			}

			if (decreseitem1(player, seedid, 0, false) == false) {
				player.saveData();
				return;
			}

			blockCut.setTypeId(itemaddid);
			blockCut.setData(itemadddata);
			// printA(digX + "," + digY + "," + digZ);

			// randomplaynote(blockCut.getLocation());

			/*
			 * if (player.getFoodLevel() > 0) {
			 * player.setFoodLevel(player.getFoodLevel() - 1);
			 * }
			 */

			// start call sub
			for (int xx3 = digX + 4; xx3 >= digX - 4; xx3--) {
				for (int yy3 = digY - 4; yy3 <= digY + 4; yy3++) {
					for (int zz3 = digZ + 4; zz3 >= digZ - 4; zz3--) {
						if (digX == xx3 && digY == yy3 && digZ == zz3) {
							continue;
						}
						blockCut = world.getBlockAt(xx3, yy3, zz3);

						soiladdseedrecusive(blockCut, player, seedid);
					}
				}
			}

		}
	}
	public JavaPlugin			ac						= null;
	// automessage

	public String				pmainoveride			= "dewdd.main.overide";

	// Bigdigthread

	public String				pmaindewbuyreplace		= "dewdd.main.dewbuy.replace";
	public String				pmaindewbuynotcount		= "dewdd.main.dewbuy.notcount";

	public String				pmaindewbuymodifymember	= "dewdd.main.dewbuy.modifymember";

	// skyblock
	// nether
	// invert
	// old_1
	// flat
	// old_2
	// float

	public String				pmaindewbuydelete		= "dewdd.main.dewbuydelete";
	public String				pmaindewbuychangehost	= "dewdd.main.dewbuy.changehost";
	dewddprivate.dewddprivate	dewpri					= new dewddprivate.dewddprivate();
	public int					max_b					= 29000;
	public boolean				allownight				= true;

	public int					dewsignlimit			= 200;
	public String				dewsignname[][][];
	public int					dewsignnamemax			= 20;

	public int					dewsignmax[];

	// redim for each world each protect = 100

	public int					dewsignloop[][];

	public int					dewsignx1[][];
	public int					dewsigny1[][];
																							public int					timechunkx[];

	public int					timechunkz[];
	public int					timechunkmax			= -1;

	public int					dewsignz1[][];

	public int					dewsignx2[][];
	public int					dewsigny2[][];

	public int					amount2					= 0;								// recursive

	// 55
	public int					amount3					= 0;								// count
																							// 55
	public int					dewsignz2[][];
	public boolean				allowfly				= true;
	public int					selectmax				= 29;

	public String				selectname[]			= new String[selectmax + 1];
	public int					selectx1[]				= new int[selectmax + 1];
	public int					selecty1[]				= new int[selectmax + 1];

	public int					selectz1[]				= new int[selectmax + 1];
	public int					d4[]					= new int[selectmax + 1];

	public int					selectx2[]				= new int[selectmax + 1];

	public int					selecty2[]				= new int[selectmax + 1];
	public int					selectz2[]				= new int[selectmax + 1];
	public boolean				hidemode[]				= new boolean[selectmax + 1];

	// public double buy1blockmoney = 0.0890932504689118445732202345959;

	public boolean				dewaxe[]				= new boolean[selectmax + 1];

	public boolean				chatever[]				= new boolean[selectmax + 1];
	public boolean				moninvi					= false;

	public boolean				monfast					= false;
	public boolean				monjump					= true;

	public String				dewworldlist[];

	public int					dewworldlistmax			= -1;

	public Random				randomG					= new Random();

	public int					deadpositionx			= 0;

	public int					deadpositiony			= 0;

	public int					deadpositionz			= 0;

	// *********

	// autosearchsub

	int							randomInt				= randomG.nextInt(500);

	public dewset() {
		// if (firstrun19 == false){

		// loadadminlist();

		timechunkx = new int[10000];
		timechunkz = new int[10000];

		// firstrun19 = true;
		// }
	}

	public void adderarraysignfile(int worldid) {
		this.dewsignmax[worldid]++;
	}

	// addfood withmoney
	public void addfoodwithmoney(Player player) {
		if (player.getName().equalsIgnoreCase("") == false) {
			return;
		}

		try {
			while (player.getFoodLevel() < 20
					&& Economy.getMoney(player.getName()) > 1) {

				Economy.setMoney(player.getName(),
						Economy.getMoney(player.getName()) - 1);
				player.setFoodLevel(player.getFoodLevel() + 1);

			}
		}
		catch (UserDoesNotExistException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		catch (NoLoanPermittedException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void artofminecraft(Player player) {

		int digX = (int) player.getLocation().getX();
		int digY = (int) player.getLocation().getY();
		int digZ = (int) player.getLocation().getZ();

		World world = player.getWorld();
		byte moden = 1;

		int ex = 0;
		int ey = 0;
		int ez = 0;
		int colore = 0;

		try {
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(
					"ptdew&dewddpicture.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line

			while ((strLine = br.readLine()) != null) {
				// Print the content on the console

				switch (moden) {
				case 1:
					ex = Integer.parseInt(strLine);
					ex += digX;

					moden++;
					break;
				case 2:
					ey = digY + (Integer.parseInt(strLine));

					moden++;
					break;
				case 3:
					ez = Integer.parseInt(strLine);
					ez += digZ;

					moden++;
					break;
				case 4:
					colore = Integer.parseInt(strLine);
					moden = 1;

					Block block = world.getBlockAt(ex, ey, ez);

					if (checkpermissionarea(block) == true) {
						continue;
					}

					if (block.getTypeId() == 0) {
						block.setTypeId(35);
						block.setData((byte) colore);
					}

					ex = digX;
					ey = digY;
					ez = digZ;
					// printC ("alL:" + ex + "," + ey + "," + ez);
					break;
				}

				// printC (strLine);
			}
			// Close the input stream
			in.close();
		}
		catch (Exception e) {// Catch exception if any
			System.err.println("ArtOfMinecraft Error: " + e.getMessage());
		}
	}

	public void autosortchest(Block block, Player player) {
		// player.sendMessage("ptdew&dewdd: auto sort chest");
		// player.sendMessage("ptdew&dewdd: �?สด�?�?วาม�?อ�?�?ุณ�?ห�? �?ลั�?�?อิ�?เรีย�?�?อ�?�?�?�?ล�?อ�?อัต�?�?มัติ by (ptdew&dewdd)");
		Chest chest = (Chest) block.getState();
		int leng = chest.getInventory().getSize();

		int sid[] = new int[leng];
		ItemStack sdata[] = new ItemStack[leng];
		int samount[] = new int[leng];
		// clear
		int l1 = 0;
		for (l1 = 0; l1 < leng; l1++) {
			sid[l1] = -1;
			samount[l1] = 0;
		}

		// loop all
		for (ItemStack it : chest.getInventory().getContents()) {

			if (it == null) {
				continue;
			}

			if (isunsortid(it) == true) {
				continue;
			}

			// add to my array
			// find

			boolean founded = false;

			for (l1 = 0; l1 < leng; l1++) {
				// player.sendMessage("finding old data " + l1);
				if (sid[l1] == it.getTypeId()) {
					// player.sendMessage("ax " + sid[l1]);
					if (sdata[l1].getData().getData() == it.getData().getData()) {

						founded = true;

						// player.sendMessage("s=" + l1 + ",id:" + sid[l1] +
						// ",data:"
						// + sdata[l1] + ",amount" + samount[l1]);
						samount[l1] += it.getAmount();
						break;
					}
				}
			}

			// if not found
			if (founded == false) {
				// player.sendMessage("can't find old slot");

				founded = false;
				for (l1 = 0; l1 < leng; l1++) {

					// find empty
					if (sid[l1] == -1) {
						sid[l1] = it.getTypeId();
						sdata[l1] = it.getData().toItemStack();
						samount[l1] = it.getAmount();
						founded = true;
						break;
					}
				}

				if (founded = false) {
					System.out
							.println("Error autosortchest: can't find empty slot");
					return;
				}

			}

		} // loop all itemstack

		// now add back : O

		// clear inventory chest
		for (int itx = 0; itx < chest.getInventory().getSize(); itx++) {

			if (chest.getInventory().getItem(itx) == null) {
				continue;
			}

			if (isunsortid(chest.getInventory().getItem(itx)) == true) {
				continue;
			}

			chest.getInventory().clear(itx);
		}
		// chest.getInventory().clear();

		// now add back : (

		for (l1 = 0; l1 < leng; l1++) {
			if (sid[l1] == -1) {
				continue;
			}

			// add until empty slot
			while (samount[l1] > 0) {
				if (samount[l1] >= 64) {
					// player.sendMessage("adding > " + sid[l1] + " amount= " +
					// samount[l1]);
					sdata[l1].setAmount(64);
					chest.getInventory().addItem(sdata[l1]);
					samount[l1] -= 64;
				}
				else {
					// player.sendMessage("adding > " + sid[l1] + " amount = " +
					// samount[l1]);

					sdata[l1].setAmount(samount[l1]);
					chest.getInventory().addItem(sdata[l1]);

					samount[l1] -= samount[l1];
				}
			}

			// player.sendMessage("x data " +
			// chest.getInventory().getItem(0).getData().getData());

		}

		for (@SuppressWarnings("unused")
		Object obj : sid) {
			obj = null;
		}
		sid = null;
		for (@SuppressWarnings("unused")
		Object obj : sdata) {
			obj = null;
		}
		sdata = null;
		for (@SuppressWarnings("unused")
		Object obj : samount) {
			obj = null;
		}
		samount = null;

	}

	public void autosortInventory(Player player) {
		// player.sendMessage("ptdew&dewdd: auto sort Inventory");
		// player.sendMessage("ptdew&dewdd: �?สด�?�?วาม�?อ�?�?ุณ�?ห�? �?ลั�?�?อิ�?เรีย�?�?อ�?�?�?�?ระเ�?�?าอัต�?�?มัติ by (ptdew&dewdd)");
		Inventory chest = player.getInventory();
		int leng = chest.getSize();

		int sid[] = new int[leng];
		ItemStack sdata[] = new ItemStack[leng];
		int samount[] = new int[leng];
		// clear
		int l1 = 0;
		for (l1 = 0; l1 < leng; l1++) {
			sid[l1] = -1;
			samount[l1] = 0;
		}

		// loop all
		for (ItemStack it : chest.getContents()) {

			if (it == null) {
				continue;
			}

			if (isunsortid(it) == true) {
				continue;
			}

			// player.sendMessage("ID" + it.getTypeId() + ",leng = " + leng);
			// add to my array
			// find

			boolean founded = false;

			for (l1 = 0; l1 < leng; l1++) {
				// player.sendMessage("finding old data " + l1);
				if (sid[l1] == it.getTypeId()) {
					// player.sendMessage("ax " + sid[l1]);
					if (sdata[l1].getData().getData() == it.getData().getData()) {

						founded = true;

						// player.sendMessage("s=" + l1 + ",id:" + sid[l1] +
						// ",data:"
						// + sdata[l1] + ",amount" + samount[l1]);
						samount[l1] += it.getAmount();
						break;
					}
				}
			}

			// if not found
			if (founded == false) {
				// player.sendMessage("can't find old slot");

				founded = false;
				for (l1 = 0; l1 < leng; l1++) {

					// find empty
					if (sid[l1] == -1) {
						sid[l1] = it.getTypeId();
						sdata[l1] = it.getData().toItemStack();
						samount[l1] = it.getAmount();
						founded = true;
						break;
					}
				}

				if (founded = false) {
					System.out
							.println("Error autosortchest: can't find empty slot");
					return;
				}

			}

		} // loop all itemstack

		// now add back : O

		// clear inventory chest
		for (int itx = 0; itx < chest.getSize(); itx++) {

			if (chest.getItem(itx) == null) {
				continue;
			}

			if (isunsortid(chest.getItem(itx)) == true) {
				continue;
			}

			chest.clear(itx);
		}
		// chest.getInventory().clear();

		// now add back : (

		for (l1 = 0; l1 < leng; l1++) {
			if (sid[l1] == -1) {
				continue;
			}

			// add until empty slot
			while (samount[l1] > 0) {
				if (samount[l1] >= 64) {
					// player.sendMessage("adding > " + sid[l1] + " amount= " +
					// samount[l1]);
					int g = chest.firstEmpty();
					sdata[l1].setAmount(64);
					chest.setItem(g, sdata[l1]);
					samount[l1] -= 64;
				}
				else {
					// player.sendMessage("adding > " + sid[l1] + " amount = " +
					// samount[l1]);

					sdata[l1].setAmount(samount[l1]);
					chest.addItem(sdata[l1]);

					samount[l1] -= samount[l1];
				}
			}

			// player.sendMessage("adding > " + sid[l1] + " amount= " +
			// samount[l1]);
			/*
			 * int g = chest.firstEmpty();
			 * sdata[l1].setAmount(samount[l1]);
			 * chest.setItem(g,sdata[l1]);
			 * samount[l1] -= 128;
			 */

			// player.sendMessage("x data " +
			// chest.getInventory().getItem(0).getData().getData());

		}
		for (@SuppressWarnings("unused")
		Object obj : sid) {
			obj = null;
		}
		sid = null;
		for (@SuppressWarnings("unused")
		Object obj : sdata) {
			obj = null;
		}
		sdata = null;
		for (@SuppressWarnings("unused")
		Object obj : samount) {
			obj = null;
		}
		samount = null;
	}

	public boolean blockdewset(int blockid) {
		switch (blockid) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 19:
		case 20:
		case 21:
		case 22:
		case 24:
		case 30:
		case 35:
		case 36:
		case 41:
		case 42:
		case 43:
		case 44:
		case 45:
		case 46:
		case 47:
		case 48:
		case 49:
		case 53:
		case 56:
		case 57:
		case 58:
		case 60:
		case 61:
		case 62:
		case 67:
		case 73:
		case 74:
		case 79:
		case 80:
		case 82:
		case 86:
		case 87:
		case 88:
		case 89:
		case 91:
		case 97:
		case 98:
		case 103:
		case 108:
		case 109:
		case 110:
		case 112:
		case 114:
		case 121:
		case 123:
		case 124:
		case 125:
		case 126:
		case 128:
		case 129:
		case 133:
		case 134:
		case 135:
		case 136:
		case 152:
		case 153:
		case 155:
		case 156:
		case 159:
		case 170:
		case 172:
		case 173:
		case 174:
			return true;

		}

		return false;

	}

	public boolean blocktypeallowspread(Block block, Player player) {
		if (checkpermissionarea(block, player, "build") == true) {
			return false;
		}

		switch (block.getTypeId()) {
		case 0:
		case 8:
		case 9:
		case 10:
		case 11:

			return true;
		default:
			return false;
		}

	}

	public boolean canaddtorch(Block bbc) {
		if (bbc.getTypeId() != 0) {
			return false;
		}

		if (canaddtorchatblock(bbc.getRelative(0, -1, 0)) == true
				|| canaddtorchatblock(bbc.getRelative(1, 0, 0)) == true
				|| canaddtorchatblock(bbc.getRelative(-1, 0, 0)) == true
				|| canaddtorchatblock(bbc.getRelative(0, 0, 1)) == true
				|| canaddtorchatblock(bbc.getRelative(0, 0, -1)) == true) {
			return true;

		}

		return false;
	}

	// canaddtorchatblock
	public boolean canaddtorchatblock(Block bbc) {
		if (bbc.getTypeId() != 0 && bbc.isLiquid() == false) {

			switch (bbc.getTypeId()) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 7:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
			case 17:
			case 21:
			case 22:
			case 24:
			case 35:
			case 41:
			case 42:
			case 43:
			case 45:
			case 56:
			case 57:
			case 73:
			case 87:
			case 88:
			case 97:
			case 98:
			case 125:
			case 133:
			case 112:
				return true;
			}

		}
		return false;

	}

	public boolean checkbetweenblock(int digx, int digy, int digz, int x1,
			int y1, int z1, int x2, int y2, int z2) {

		boolean goodc1 = false;

		goodc1 = false;

		// x 2 type
		// x1 <= x2
		if (x1 <= x2) {
			if (digx > x1 - 1 && digx < x2 + 1) {
				// x true

				if (y1 <= y2) {
					if (digy > y1 - 1 && digy < y2 + 1) {
						// y true
						if (z1 <= z2) {
							if (digz > z1 - 1 && digz < z2 + 1) {
								// z true
								goodc1 = true;
							}
						}
						else { // z1 > z2
							if (digz < z1 + 1 && digz > z2 - 1) {
								// z true
								goodc1 = true;
							}
						}

					}
				}
				else { // y1 > y2
					if (digy < y1 + 1 && digy > y2 - 1) {
						// y true
						if (z1 <= z2) {
							if (digz > z1 - 1 && digz < z2 + 1) {
								// z true
								goodc1 = true;
							}
						}
						else { // z1 > z2
							if (digz < z1 + 1 && digz > z2 - 1) {
								// z true
								goodc1 = true;
							}
						}
					}
				}

			}
		}
		else { // x1 > x2
			if (digx < x1 + 1 && digx > x2 - 1) {
				// x true

				if (y1 <= y2) {
					if (digy > y1 - 1 && digy < y2 + 1) {
						// y true
						if (z1 <= z2) {
							if (digz > z1 - 1 && digz < z2 + 1) {
								// z true
								goodc1 = true;
							}
						}
						else { // z1 > z2
							if (digz < z1 + 1 && digz > z2 - 1) {
								// z true
								goodc1 = true;
							}
						}

					}
				}
				else { // y1 > y2
					if (digy < y1 + 1 && digy > y2 - 1) {
						// y true
						if (z1 <= z2) {
							if (digz > z1 - 1 && digz < z2 + 1) {
								// z true
								goodc1 = true;
							}
						}
						else { // z1 > z2
							if (digz < z1 + 1 && digz > z2 - 1) {
								// z true
								goodc1 = true;
							}
						}
					}
				}

			}
		}

		return goodc1;

	}

	// Check Permission Area block
	public boolean checkpermissionarea(Block block) {
		if (getworldid(block.getWorld().getName()) == -1) {
			return false;
		}
		if (dewsignmax[getworldid(block.getWorld().getName())] == -1) {
			return false;
		}

		int digx = block.getX();
		int digy = block.getY();
		int digz = block.getZ();

		boolean goodc1 = false;

		goodc1 = false;

		int dewsignnow = 0;
		for (dewsignnow = 0; dewsignnow < dewsignmax[getworldid(block
				.getWorld().getName())]; dewsignnow++) {

			// x 2 type
			// x1 <= x2
			if (dewsignx1[getworldid(block.getWorld().getName())][dewsignnow] <= dewsignx2[getworldid(block
					.getWorld().getName())][dewsignnow]) {
				if (digx > dewsignx1[getworldid(block.getWorld().getName())][dewsignnow] - 1
						&& digx < dewsignx2[getworldid(block.getWorld()
								.getName())][dewsignnow] + 1) {
					// x true

					if (dewsigny1[getworldid(block.getWorld().getName())][dewsignnow] <= dewsigny2[getworldid(block
							.getWorld().getName())][dewsignnow]) {
						if (digy > dewsigny1[getworldid(block.getWorld()
								.getName())][dewsignnow] - 1
								&& digy < dewsigny2[getworldid(block.getWorld()
										.getName())][dewsignnow] + 1) {
							// y true
							if (dewsignz1[getworldid(block.getWorld().getName())][dewsignnow] <= dewsignz2[getworldid(block
									.getWorld().getName())][dewsignnow]) {
								if (digz > dewsignz1[getworldid(block
										.getWorld().getName())][dewsignnow] - 1
										&& digz < dewsignz2[getworldid(block
												.getWorld().getName())][dewsignnow] + 1) {
									// z true
									goodc1 = true;
									break;
								}
							}
							else { // z1 > z2
								if (digz < dewsignz1[getworldid(block
										.getWorld().getName())][dewsignnow] + 1
										&& digz > dewsignz2[getworldid(block
												.getWorld().getName())][dewsignnow] - 1) {
									// z true
									goodc1 = true;
									break;
								}
							}

						}
					}
					else { // y1 > y2
						if (digy < dewsigny1[getworldid(block.getWorld()
								.getName())][dewsignnow] + 1
								&& digy > dewsigny2[getworldid(block.getWorld()
										.getName())][dewsignnow] - 1) {
							// y true
							if (dewsignz1[getworldid(block.getWorld().getName())][dewsignnow] <= dewsignz2[getworldid(block
									.getWorld().getName())][dewsignnow]) {
								if (digz > dewsignz1[getworldid(block
										.getWorld().getName())][dewsignnow] - 1
										&& digz < dewsignz2[getworldid(block
												.getWorld().getName())][dewsignnow] + 1) {
									// z true
									goodc1 = true;
									break;
								}
							}
							else { // z1 > z2
								if (digz < dewsignz1[getworldid(block
										.getWorld().getName())][dewsignnow] + 1
										&& digz > dewsignz2[getworldid(block
												.getWorld().getName())][dewsignnow] - 1) {
									// z true
									goodc1 = true;
									break;
								}
							}
						}
					}

				}
			}
			else { // x1 > x2
				if (digx < dewsignx1[getworldid(block.getWorld().getName())][dewsignnow] + 1
						&& digx > dewsignx2[getworldid(block.getWorld()
								.getName())][dewsignnow] - 1) {
					// x true

					if (dewsigny1[getworldid(block.getWorld().getName())][dewsignnow] <= dewsigny2[getworldid(block
							.getWorld().getName())][dewsignnow]) {
						if (digy > dewsigny1[getworldid(block.getWorld()
								.getName())][dewsignnow] - 1
								&& digy < dewsigny2[getworldid(block.getWorld()
										.getName())][dewsignnow] + 1) {
							// y true
							if (dewsignz1[getworldid(block.getWorld().getName())][dewsignnow] <= dewsignz2[getworldid(block
									.getWorld().getName())][dewsignnow]) {
								if (digz > dewsignz1[getworldid(block
										.getWorld().getName())][dewsignnow] - 1
										&& digz < dewsignz2[getworldid(block
												.getWorld().getName())][dewsignnow] + 1) {
									// z true
									goodc1 = true;
									break;
								}
							}
							else { // z1 > z2
								if (digz < dewsignz1[getworldid(block
										.getWorld().getName())][dewsignnow] + 1
										&& digz > dewsignz2[getworldid(block
												.getWorld().getName())][dewsignnow] - 1) {
									// z true
									goodc1 = true;
									break;
								}
							}

						}
					}
					else { // y1 > y2
						if (digy < dewsigny1[getworldid(block.getWorld()
								.getName())][dewsignnow] + 1
								&& digy > dewsigny2[getworldid(block.getWorld()
										.getName())][dewsignnow] - 1) {
							// y true
							if (dewsignz1[getworldid(block.getWorld().getName())][dewsignnow] <= dewsignz2[getworldid(block
									.getWorld().getName())][dewsignnow]) {
								if (digz > dewsignz1[getworldid(block
										.getWorld().getName())][dewsignnow] - 1
										&& digz < dewsignz2[getworldid(block
												.getWorld().getName())][dewsignnow] + 1) {
									// z true
									goodc1 = true;
									break;
								}
							}
							else { // z1 > z2
								if (digz < dewsignz1[getworldid(block
										.getWorld().getName())][dewsignnow] + 1
										&& digz > dewsignz2[getworldid(block
												.getWorld().getName())][dewsignnow] - 1) {
									// z true
									goodc1 = true;
									break;
								}
							}
						}
					}

				}
			}

			if (goodc1 == true) {
				dewsignloop[getworldid(block.getWorld().getName())][dewsignnow]++;
				break;
			}
		}

		return goodc1;

	}

	public int checkpermissionarea(Block block, boolean gethomeid) {

		if (getworldid(block.getWorld().getName()) == -1) {

			return -1;
		}
		if (dewsignmax[getworldid(block.getWorld().getName())] == -1) {
			return -1;

		}

		int digx = block.getX();
		int digy = block.getY();
		int digz = block.getZ();

		boolean goodc1 = false;

		goodc1 = false;

		int dewsignnow = 0;

		for (int prioL = 1; prioL <= 2; prioL++) {
			for (dewsignnow = 0; dewsignnow < dewsignmax[getworldid(block
					.getWorld().getName())]; dewsignnow++) { // for
				if (prioL == 1) {
					if (dewsignname[getworldid(block.getWorld().getName())][dewsignnow][0]
							.equalsIgnoreCase("everyone") == true) {
						continue;
					}
				}
				else {
					if (dewsignname[getworldid(block.getWorld().getName())][dewsignnow][0]
							.equalsIgnoreCase("everyone") == false) {
						continue;
					}
				}
				// x 2 type
				// x1 <= x2
				if (dewsignx1[getworldid(block.getWorld().getName())][dewsignnow] <= dewsignx2[getworldid(block
						.getWorld().getName())][dewsignnow]) {
					if (digx > dewsignx1[getworldid(block.getWorld().getName())][dewsignnow] - 1
							&& digx < dewsignx2[getworldid(block.getWorld()
									.getName())][dewsignnow] + 1) {
						// x true

						if (dewsigny1[getworldid(block.getWorld().getName())][dewsignnow] <= dewsigny2[getworldid(block
								.getWorld().getName())][dewsignnow]) {
							if (digy > dewsigny1[getworldid(block.getWorld()
									.getName())][dewsignnow] - 1
									&& digy < dewsigny2[getworldid(block
											.getWorld().getName())][dewsignnow] + 1) {
								// y true
								if (dewsignz1[getworldid(block.getWorld()
										.getName())][dewsignnow] <= dewsignz2[getworldid(block
										.getWorld().getName())][dewsignnow]) {
									if (digz > dewsignz1[getworldid(block
											.getWorld().getName())][dewsignnow] - 1
											&& digz < dewsignz2[getworldid(block
													.getWorld().getName())][dewsignnow] + 1) {
										// z true
										goodc1 = true;
										break;
									}
								}
								else { // z1 > z2
									if (digz < dewsignz1[getworldid(block
											.getWorld().getName())][dewsignnow] + 1
											&& digz > dewsignz2[getworldid(block
													.getWorld().getName())][dewsignnow] - 1) {
										// z true
										goodc1 = true;
										break;
									}
								}

							}
						}
						else { // y1 > y2
							if (digy < dewsigny1[getworldid(block.getWorld()
									.getName())][dewsignnow] + 1
									&& digy > dewsigny2[getworldid(block
											.getWorld().getName())][dewsignnow] - 1) {
								// y true
								if (dewsignz1[getworldid(block.getWorld()
										.getName())][dewsignnow] <= dewsignz2[getworldid(block
										.getWorld().getName())][dewsignnow]) {
									if (digz > dewsignz1[getworldid(block
											.getWorld().getName())][dewsignnow] - 1
											&& digz < dewsignz2[getworldid(block
													.getWorld().getName())][dewsignnow] + 1) {
										// z true
										goodc1 = true;
										break;
									}
								}
								else { // z1 > z2
									if (digz < dewsignz1[getworldid(block
											.getWorld().getName())][dewsignnow] + 1
											&& digz > dewsignz2[getworldid(block
													.getWorld().getName())][dewsignnow] - 1) {
										// z true
										goodc1 = true;
										break;
									}
								}
							}
						}

					}
				}
				else { // x1 > x2
					if (digx < dewsignx1[getworldid(block.getWorld().getName())][dewsignnow] + 1
							&& digx > dewsignx2[getworldid(block.getWorld()
									.getName())][dewsignnow] - 1) {
						// x true

						if (dewsigny1[getworldid(block.getWorld().getName())][dewsignnow] <= dewsigny2[getworldid(block
								.getWorld().getName())][dewsignnow]) {
							if (digy > dewsigny1[getworldid(block.getWorld()
									.getName())][dewsignnow] - 1
									&& digy < dewsigny2[getworldid(block
											.getWorld().getName())][dewsignnow] + 1) {
								// y true
								if (dewsignz1[getworldid(block.getWorld()
										.getName())][dewsignnow] <= dewsignz2[getworldid(block
										.getWorld().getName())][dewsignnow]) {
									if (digz > dewsignz1[getworldid(block
											.getWorld().getName())][dewsignnow] - 1
											&& digz < dewsignz2[getworldid(block
													.getWorld().getName())][dewsignnow] + 1) {
										// z true
										goodc1 = true;
										break;
									}
								}
								else { // z1 > z2
									if (digz < dewsignz1[getworldid(block
											.getWorld().getName())][dewsignnow] + 1
											&& digz > dewsignz2[getworldid(block
													.getWorld().getName())][dewsignnow] - 1) {
										// z true
										goodc1 = true;
										break;
									}
								}

							}
						}
						else { // y1 > y2
							if (digy < dewsigny1[getworldid(block.getWorld()
									.getName())][dewsignnow] + 1
									&& digy > dewsigny2[getworldid(block
											.getWorld().getName())][dewsignnow] - 1) {
								// y true
								if (dewsignz1[getworldid(block.getWorld()
										.getName())][dewsignnow] <= dewsignz2[getworldid(block
										.getWorld().getName())][dewsignnow]) {
									if (digz > dewsignz1[getworldid(block
											.getWorld().getName())][dewsignnow] - 1
											&& digz < dewsignz2[getworldid(block
													.getWorld().getName())][dewsignnow] + 1) {
										// z true
										goodc1 = true;
										break;
									}
								}
								else { // z1 > z2
									if (digz < dewsignz1[getworldid(block
											.getWorld().getName())][dewsignnow] + 1
											&& digz > dewsignz2[getworldid(block
													.getWorld().getName())][dewsignnow] - 1) {
										// z true
										goodc1 = true;
										break;
									}
								}
							}
						}

					}
				}

				if (goodc1 == true) {
					dewsignloop[getworldid(block.getWorld().getName())][dewsignnow]++;
					break;
				}
			} // loog sign

			if (goodc1 == true) {
				return dewsignnow;
			}

		} // loop prio

		if (goodc1 == true) {
			return dewsignnow;
		}

		return -1;

	}

	// Check Permission Area block player mode
	public boolean checkpermissionarea(Block block, Player player,
			String modeevent) {

		if (isspyname(player.getName()) == true) { // if vip can't do anything
			return true;
		}

		if (getworldid(block.getWorld().getName()) == -1) {

			if (issubsubadminname(player.getName()) == true) {
				return true;
			}
			else {
				return false;
			}

		}

		if (dewsignmax[getworldid(block.getWorld().getName())] == -1) {

			if (issubsubadminname(player.getName()) == true) {
				return true;
			}
			else {
				return false;
			}

		}

		int digx = block.getX();
		int digy = block.getY();
		int digz = block.getZ();

		boolean goodc1 = false;

		int dewsignnow = 0;

		for (int prioL = 1; prioL <= 2; prioL++) {
			for (dewsignnow = 0; dewsignnow < dewsignmax[getworldid(block
					.getWorld().getName())]; dewsignnow++) { // for
				if (prioL == 1) {
					if (dewsignname[getworldid(block.getWorld().getName())][dewsignnow][0]
							.equalsIgnoreCase("everyone") == true) {
						continue;
					}
				}
				else {
					if (dewsignname[getworldid(block.getWorld().getName())][dewsignnow][0]
							.equalsIgnoreCase("everyone") == false) {
						continue;
					}
				}
				// x 2 type
				// x1 <= x2
				if (dewsignx1[getworldid(block.getWorld().getName())][dewsignnow] <= dewsignx2[getworldid(block
						.getWorld().getName())][dewsignnow]) {
					// player.sendMessage("" + dewsignnow + "||" + dewsignmax +
					// ","
					// + dewsignx1.length);

					if (digx > dewsignx1[getworldid(block.getWorld().getName())][dewsignnow] - 1
							&& digx < dewsignx2[getworldid(block.getWorld()
									.getName())][dewsignnow] + 1) {
						// x true

						if (dewsigny1[getworldid(block.getWorld().getName())][dewsignnow] <= dewsigny2[getworldid(block
								.getWorld().getName())][dewsignnow]) {
							if (digy > dewsigny1[getworldid(block.getWorld()
									.getName())][dewsignnow] - 1
									&& digy < dewsigny2[getworldid(block
											.getWorld().getName())][dewsignnow] + 1) {
								// y true
								if (dewsignz1[getworldid(block.getWorld()
										.getName())][dewsignnow] <= dewsignz2[getworldid(block
										.getWorld().getName())][dewsignnow]) {
									if (digz > dewsignz1[getworldid(block
											.getWorld().getName())][dewsignnow] - 1
											&& digz < dewsignz2[getworldid(block
													.getWorld().getName())][dewsignnow] + 1) {
										// z true
										goodc1 = true;
										break;
									}
								}
								else { // z1 > z2
									if (digz < dewsignz1[getworldid(block
											.getWorld().getName())][dewsignnow] + 1
											&& digz > dewsignz2[getworldid(block
													.getWorld().getName())][dewsignnow] - 1) {
										// z true
										goodc1 = true;
										break;
									}
								}

							}
						}
						else { // y1 > y2
							if (digy < dewsigny1[getworldid(block.getWorld()
									.getName())][dewsignnow] + 1
									&& digy > dewsigny2[getworldid(block
											.getWorld().getName())][dewsignnow] - 1) {
								// y true
								if (dewsignz1[getworldid(block.getWorld()
										.getName())][dewsignnow] <= dewsignz2[getworldid(block
										.getWorld().getName())][dewsignnow]) {
									if (digz > dewsignz1[getworldid(block
											.getWorld().getName())][dewsignnow] - 1
											&& digz < dewsignz2[getworldid(block
													.getWorld().getName())][dewsignnow] + 1) {
										// z true
										goodc1 = true;
										break;
									}
								}
								else { // z1 > z2
									if (digz < dewsignz1[getworldid(block
											.getWorld().getName())][dewsignnow] + 1
											&& digz > dewsignz2[getworldid(block
													.getWorld().getName())][dewsignnow] - 1) {
										// z true
										goodc1 = true;
										break;
									}
								}
							}
						}

					}
				}
				else { // x1 > x2
					if (digx < dewsignx1[getworldid(block.getWorld().getName())][dewsignnow] + 1
							&& digx > dewsignx2[getworldid(block.getWorld()
									.getName())][dewsignnow] - 1) {
						// x true

						if (dewsigny1[getworldid(block.getWorld().getName())][dewsignnow] <= dewsigny2[getworldid(block
								.getWorld().getName())][dewsignnow]) {
							if (digy > dewsigny1[getworldid(block.getWorld()
									.getName())][dewsignnow] - 1
									&& digy < dewsigny2[getworldid(block
											.getWorld().getName())][dewsignnow] + 1) {
								// y true
								if (dewsignz1[getworldid(block.getWorld()
										.getName())][dewsignnow] <= dewsignz2[getworldid(block
										.getWorld().getName())][dewsignnow]) {
									if (digz > dewsignz1[getworldid(block
											.getWorld().getName())][dewsignnow] - 1
											&& digz < dewsignz2[getworldid(block
													.getWorld().getName())][dewsignnow] + 1) {
										// z true
										goodc1 = true;
										break;
									}
								}
								else { // z1 > z2
									if (digz < dewsignz1[getworldid(block
											.getWorld().getName())][dewsignnow] + 1
											&& digz > dewsignz2[getworldid(block
													.getWorld().getName())][dewsignnow] - 1) {
										// z true
										goodc1 = true;
										break;
									}
								}

							}
						}
						else { // y1 > y2
							if (digy < dewsigny1[getworldid(block.getWorld()
									.getName())][dewsignnow] + 1
									&& digy > dewsigny2[getworldid(block
											.getWorld().getName())][dewsignnow] - 1) {
								// y true
								if (dewsignz1[getworldid(block.getWorld()
										.getName())][dewsignnow] <= dewsignz2[getworldid(block
										.getWorld().getName())][dewsignnow]) {
									if (digz > dewsignz1[getworldid(block
											.getWorld().getName())][dewsignnow] - 1
											&& digz < dewsignz2[getworldid(block
													.getWorld().getName())][dewsignnow] + 1) {
										// z true
										goodc1 = true;
										break;
									}
								}
								else { // z1 > z2
									if (digz < dewsignz1[getworldid(block
											.getWorld().getName())][dewsignnow] + 1
											&& digz > dewsignz2[getworldid(block
													.getWorld().getName())][dewsignnow] - 1) {
										// z true
										goodc1 = true;
										break;
									}
								}
							}
						}

					}
				}

			} // for all protect

			if (goodc1 == true) {
				break;
			}
		} // prioL

		if (goodc1 == true) { // true if don't have permissiotn
			boolean che1 = false;
			dewsignloop[getworldid(block.getWorld().getName())][dewsignnow]++;

			if (modeevent.equalsIgnoreCase("right") == true) {
				che1 = false;
				for (int cheL = 0; cheL < dewsignnamemax; cheL++) {
					if (dewsignname[getworldid(block.getWorld().getName())][dewsignnow][cheL]
							.equalsIgnoreCase("everyone") == true) {
						che1 = true;
						break;
					}
				}

				if (che1 == true) { // if for everyone
					goodc1 = false;
					if (issubsubadminname(player.getName()) == true) { // if
																		// staff
																		// can't
																		// do it
						goodc1 = true;
					}

				}
				else { // not for everyone
					che1 = false;
					for (int cheL = 0; cheL < dewsignnamemax; cheL++) {
						if (dewsignname[getworldid(block.getWorld().getName())][dewsignnow][cheL]
								.equalsIgnoreCase(player.getName()) == true) {
							che1 = true;
							break;
						}
					}

					if (che1 == true) { // found player in home list
						goodc1 = false; // allow
					}
					else {
						goodc1 = true; // not found not allow
					}
				}

			} // right click
			else if (modeevent.equalsIgnoreCase("delete") == true
					|| modeevent.equalsIgnoreCase("build") == true
					|| modeevent.equalsIgnoreCase("damage") == true
					|| modeevent.equalsIgnoreCase("HangingBreakByEntity") == true
					|| modeevent.equalsIgnoreCase("HangingPlaceEvent") == true) { // build
																					// block
																					// delete
																					// block
																					// picture
				if (block.getTypeId() == 63
						|| block.getTypeId() == 68
						|| block.getTypeId() == 54
						|| modeevent.equalsIgnoreCase("HangingBreakByEntity") == true
						|| modeevent.equalsIgnoreCase("HangingPlaceEvent") == true) {

					che1 = false;
					for (int cheL = 0; cheL < dewsignnamemax; cheL++) {
						if (dewsignname[getworldid(block.getWorld().getName())][dewsignnow][cheL]
								.equalsIgnoreCase("<sign>") == true) {
							che1 = true;
							break;
						}
					}

					if (che1 == true) { // if for everyone
						goodc1 = false;
						if (issubsubadminname(player.getName()) == true) { // if
																			// staff
																			// can't
																			// do
																			// it
							goodc1 = true;
						}

					}
					else { // not for everyone
						che1 = false;
						for (int cheL = 0; cheL < dewsignnamemax; cheL++) {
							if (dewsignname[getworldid(block.getWorld()
									.getName())][dewsignnow][cheL]
									.equalsIgnoreCase(player.getName()) == true) {
								che1 = true;
								break;
							}
						}

						if (che1 == true) { // found player in home list
							goodc1 = false; // allow
						}
						else {
							goodc1 = true; // not found not allow
						}
					}

				} // neary <sign>
				else {
					che1 = false;
					for (int cheL = 0; cheL < dewsignnamemax; cheL++) {
						if (dewsignname[getworldid(block.getWorld().getName())][dewsignnow][cheL]
								.equalsIgnoreCase(player.getName()) == true) {
							che1 = true;
							break;
						}
					}

					if (che1 == true) { // found player in home list
						goodc1 = false; // allow
					}
					else {
						goodc1 = true; // not found not allow
					}
				}

			}
			else if (modeevent.equalsIgnoreCase("getentity") == true) {
				goodc1 = false;
			}
			else { // not right click
				che1 = false;
				for (int cheL = 0; cheL < dewsignnamemax; cheL++) {
					if (dewsignname[getworldid(block.getWorld().getName())][dewsignnow][cheL]
							.equalsIgnoreCase(player.getName()) == true) {
						che1 = true;
						break;
					}
				}

			} // right click or not

			if (issubsubadminname(player.getName()) == true) {
				goodc1 = !havethisnameinthishome(getworldid(block.getWorld()
						.getName()), dewsignnow, player.getName());
			}

			if (goodc1 == true) {

				if (player.hasPermission(pmainoveride) == true) { // overide
					goodc1 = false;
				}

				else {
					/*
					 * player.sendMessage("ptdew&dewdd: You don't have permission "
					 * + "for '"
					 * + modeevent
					 * + "' block|"
					 * + "("
					 * +
					 * dewsignx1[getworldid(block.getWorld().getName())][dewsignnow
					 * ]
					 * + ","
					 * +
					 * dewsigny1[getworldid(block.getWorld().getName())][dewsignnow
					 * ]
					 * + ","
					 * +
					 * dewsignz1[getworldid(block.getWorld().getName())][dewsignnow
					 * ]
					 * + ") to ("
					 * +
					 * 
					 * dewsignx2[getworldid(block.getWorld().getName())][dewsignnow
					 * ]
					 * + ","
					 * +
					 * dewsigny2[getworldid(block.getWorld().getName())][dewsignnow
					 * ]
					 * + ","
					 * +
					 * dewsignz2[getworldid(block.getWorld().getName())][dewsignnow
					 * ]
					 * + ")");
					 * showwhohome(block,player);
					 */
				}
			}

		}
		else { // If not who each home
			// staff should't have permission to place block
			if (issubsubadminname(player.getName()) == true) {
				goodc1 = true;
			}

		}

		return goodc1;

	}

	// Check Permission Area block

	// Check Permission Area block player mode

	public void chestabsorb() {
		int d4 = 20;
		Block block = null;
		Block block2 = null;
		int d5 = 1;
		Chest chest = null;
		int slotp = -1;

		for (Player player : Bukkit.getOnlinePlayers()) {
			if (issubsubadminname(player.getName()) == true) {
				continue;
			}
			// search nearby box and sign ... ummm yes
			block = player.getLocation().getBlock();

			/*
			 * if (checkpermissionarea(block)== false) { not protect area
			 * continue;
			 * }
			 */
			if (checkpermissionarea(block, player, "build") == true) { // can't
																		// build
				continue;
			}

			for (int gx = (0 - d4); gx <= (0 + d4); gx++) {
				for (int gy = (0 - d4); gy <= (0 + d4); gy++) {
					for (int gz = (0 - d4); gz <= (0 + d4); gz++) {
						// first search sign
						block = player.getLocation().getBlock()
								.getRelative(gx, gy, gz);
						if (block == null) {
							continue;
						}

						if (block.getTypeId() != 63 && block.getTypeId() != 68) {
							continue;
						}

						Sign sign = (Sign) block.getState();
						if (sign.getLine(0).equalsIgnoreCase("[dewtobox]") == true) {
							// player.sendMessage("found dewtobox sign : " +
							// block.getLocation().getBlockX() + ","
							// + block.getLocation().getBlockY() + "," +
							// block.getLocation().getBlockZ());

							int intb = Integer.parseInt(sign.getLine(1));
							if (intb == 0) {
								continue;
							}

							// after found sign so find box

							// box
							for (int ax = (0 - d5); ax <= (0 + d5); ax++) {
								for (int ay = (0 - d5); ay <= (0 + d5); ay++) {
									for (int az = (0 - d5); az <= (0 + d5); az++) {
										block2 = block.getRelative(ax, ay, az);
										if (block2 == null) {
											continue;
										}

										if (block2.getTypeId() != 54) {
											continue;
										}

										// player.sendMessage("found dewtobox chest : "
										// + block2.getLocation().getBlockX() +
										// ","
										// + block2.getLocation().getBlockY() +
										// "," +
										// block2.getLocation().getBlockZ());

										slotp = player.getInventory().first(
												intb);
										if (slotp == -1) {
											continue;
										}

										chest = (Chest) block2.getState();

										int chestslot = -1;
										chestslot = chest.getInventory()
												.firstEmpty();
										if (chestslot == -1) {
											continue;
										}

										// ready to move
										chest.getInventory().addItem(
												player.getInventory().getItem(
														slotp));

										player.getInventory().clear(slotp);
										player.updateInventory();

										player.sendMessage("[dewtobox] moved "
												+ intb);

										// added

										// if true
										// check is empty
										// check item of player

									}
								}
							}
							// box

						}

					}
				}
			}

		}

	}

	public void createmonster(Player player, EntityType EntityTypeGot, int count) {
		Location loca = player.getLocation();
		int x = (int) loca.getX();
		int y = (int) loca.getY();
		int z = (int) loca.getZ();
		for (int xx = 0; xx <= player.getItemInHand().getAmount(); xx++) {

			int lx = x;
			int ly = y;
			int lz = z;
			int d4 = 4;

			boolean an = true;
			z++;
			x++;

			while (an == true) {
				an = true;

				for (lx = x - d4; lx <= x + d4; lx++) {
					for (ly = y - d4; ly <= y + d4; ly++) {
						for (lz = z - d4; lz <= z + d4; lz++) {
							Block block2 = player.getWorld().getBlockAt(lx, ly,
									lz);
							if (block2.getTypeId() == 0) {
								an = false;
							}
							else {
								an = true;
							}
						}
					}
				}

				if (an == true) {
					y++;
					if (y > 253 || y < 1) {
						return;
					}
				}

			}

			loca = player.getLocation();
			loca.setX(x);
			loca.setY(y);
			loca.setZ(z);

			player.getWorld().spawnCreature(loca, EntityTypeGot);

		} // loop monster all
	}

	// cut seem block
	public void cutseemblock(Block block123, Player player123, int countja) {
		try {
			int gx = 0;
			int gy = 0;
			int gz = 0;
			int g4 = 1;

			if (isadminname(player123.getName()) == false) {
				return;
			}

			if (checkpermissionarea(block123) == true) {
				return;
			}

			int seemblockid = block123.getTypeId();

			switch (player123.getItemInHand().getTypeId()) {
			case 7:
				// player123.sendMessage("ptdew&dewdd:" + countja +
				// " 7 cut seem block (" + block123.getX() + "," +
				// block123.getY() + "," + block123.getZ() + ")");
				block123.setTypeId(0);
				countja++;
				if (countja > 50) {
					return;

				}
				break;
			default:
				return;
			}

			World world = block123.getWorld();

			for (gx = block123.getX() - g4; gx <= block123.getX() + g4; gx++) {
				for (gy = block123.getY() - g4; gy <= block123.getY() + g4; gy++) {
					for (gz = block123.getZ() - g4; gz <= block123.getZ() + g4; gz++) {
						Block blockt1 = world.getBlockAt(gx, gy, gz);

						if (blockt1.getTypeId() == seemblockid) {
							if (checkpermissionarea(block123) == true) {
								continue;
							}

							countja++;
							if (countja > 50) {
								return;
							}
							cutseemblock(blockt1, player123, countja);

						}

					}
				}
			}

			return;
		}
		catch (Exception e) {// Catch exception if any
			// System.err.println("Error: " + e.getMessage() );
		}
	}

	// dewbuy

	public void cutwoodsub(Block block123, Player player123) {
		cutwoodsubc ab = new cutwoodsubc();
		ab.block123 = block123;
		ab.player123 = player123;

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ac, ab);
	}

	// cut seem block

	// decrese item 1
	public boolean decreseitem1(Player player, int itemid, int itemdata,
			boolean forcetruedata) {
		ItemStack itm = null;
		int len = player.getInventory().getContents().length;
		int lenl = 0;

		for (lenl = 0; lenl < len - 1; lenl++) {
			if (player.getInventory().getItem(lenl) == null) {
				continue;
			}

			itm = player.getInventory().getItem(lenl);

			if ((itemid == 8) || (itemid == 9) || (itemid == 326)) {
				if ((itm.getTypeId() != 8) && (itm.getTypeId() != 9)
						&& (itm.getTypeId() != 326)) {
					continue;
				}
			}
			// dirt
			else if ((itemid == 2) || (itemid == 3) || (itemid == 60)) {
				if ((itm.getTypeId() != 3) && (itm.getTypeId() != 2)
						&& (itm.getTypeId() != 60)) {
					continue;
				}
			}
			// repeater
			else if ((itemid == 356) || (itemid == 93) || (itemid == 94)) {
				if ((itm.getTypeId() != 356) && (itm.getTypeId() != 93)
						&& (itm.getTypeId() != 94)) {
					continue;
				}
			}
			// redstone torch
			else if ((itemid == 75) || (itemid == 76)) {
				if ((itm.getTypeId() != 75) && (itm.getTypeId() != 76)) {
					continue;
				}
			}
			// redstone wire
			else if ((itemid == 331) || (itemid == 55)) {
				if ((itm.getTypeId() != 331) && (itm.getTypeId() != 55)) {
					continue;
				}
			}
			// pumpkin
			else if ((itemid == 361) || (itemid == 104)) {
				if ((itm.getTypeId() != 361) && (itm.getTypeId() != 104)) {
					continue;
				}
			}
			// melon
			else if ((itemid == 362) || (itemid == 105)) {
				if ((itm.getTypeId() != 362) && (itm.getTypeId() != 105)) {
					continue;
				}
			}
			// wheat
			else if ((itemid == 295) || (itemid == 59)) {
				if ((itm.getTypeId() != 295) && (itm.getTypeId() != 59)) {
					continue;
				}
			}
			// carrot
			else if ((itemid == 391) || (itemid == 141)) {
				if ((itm.getTypeId() != 391) && (itm.getTypeId() != 141)) {
					continue;
				}
			}
			// potato
			else if ((itemid == 392) || (itemid == 142)) {
				if ((itm.getTypeId() != 392) && (itm.getTypeId() != 142)) {
					continue;
				}
			}
			else {
				if (itm.getTypeId() != itemid) {
					continue;
				}

			}

			if (forcetruedata == true) {
				if (itm.getData().getData() != itemdata) {
					continue;
				}
			}

			if (itm.getAmount() != 1) {
				itm.setAmount(itm.getAmount() - 1);
				return true;
			}
			else {
				ItemStack emy = player.getItemInHand();
				emy.setTypeId(0);

				player.getInventory().setItem(lenl, emy);

				return true;
			}

		}
		return false;
	}

	// cutwoodsub

	public void dewa(Player player) {

		/*
		 * if (isadminname(player.getName())==false &&
		 * issubsubadminname(player.getName()) == false) {
		 * player.sendMessage("ptdew&dewdd:only admin or staff can use it (dewa)"
		 * );
		 * return;
		 * }
		 */
		int getid = getfreeselect(player);
		if (selectx1[getid] == 0 && selecty1[getid] == 0
				&& selectz1[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewa please set block1");
			player.sendMessage("ptdew&dewdd:dewa �?รุณา�?ำห�?ดเ�?ต�?ล�?อ�? 1");

			return;
		}
		if (selectx2[getid] == 0 && selecty2[getid] == 0
				&& selectz2[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewa please set block2");
			player.sendMessage("ptdew&dewdd:dewa �?รุณา�?ำห�?ดเ�?ต�?ล�?อ�? 2");

			return;
		}

		// find position
		if (player.getItemInHand() == null) {
			player.sendMessage("ptdew&dewdd: Item in hand = amount   (but you don't have item in hand");
			player.sendMessage("ptdew&dewdd: �?อ�?�?�?มือ�?ือ �?ำ�?ว�?�?ารยืด .. (�?ต�?�?ุณ�?ม�?มี�?อ�?�?�?มือ เราต�?อ�?�?ารรู�?ว�?า�?ุณ�?ะยึดเท�?า�?ร) ");
			return;
		}
		int amount = player.getItemInHand().getAmount();

		player.sendMessage("dewa amount = " + amount);
		player.sendMessage("dewa �?ำ�?ว�?�?รั�?�?  = " + amount);

		Block block = player.getLocation().getBlock();
		if (block.getRelative(0, -1, 0).getTypeId() != 133) { // emerald block
			player.sendMessage("ptdew&dewdd: please stand on emerald block");
			player.sendMessage("ptdew&dewdd: �?รุณายื�?�?�?�?ล�?อ�?มร�?ต");
			return;
		}

		player.sendMessage("ptdew&dewdd: ok you already stand on emerald block");
		player.sendMessage("ptdew&dewdd: �?ุณยื�?อยู�?�?�?�?ล�?อ�?มร�?ต�?ล�?ว");
		// find diamond
		Block blockd = null;
		int blockdx = 0;
		int blockdy = 0;
		int blockdz = 0;

		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -2; dy <= 0; dy++) {
				for (int dz = -1; dz <= 1; dz++) {
					blockd = block.getRelative(dx, dy, dz);
					if (blockd.getTypeId() == 57) { // diamond block
						blockdx = dx;
						blockdy = dy + 1;
						blockdz = dz;
						break;
					}
				}

			}

		}

		if (blockdx == 0 && blockdy == 0 && blockdz == 0) {
			player.sendMessage("diamond block can't found so... It mean upper");
			player.sendMessage("diamond �?ม�?�?�?�?ล�?อ�?เ�?�?ร รอ�?�?�?ล�?อ�?มร�?ต �?�?ลว�?า�?ุณ�?ะยืด�?ึ�?�?ด�?า�?�?�?");
			blockdy = 1;
		}

		player.sendMessage("diamond block axis = " + blockdx + "," + blockdy
				+ "," + blockdz);
		// after know axis and selected block ... so start copy
		// for amount
		// for all block ... to copy to next axis
		printAll("ptdew&dewdd : '" + player.getName() + "'starting dewa "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());

		for (int amountloop = 1; amountloop <= amount; amountloop++) {
			player.getWorld().save();
			int ndx = 0; // now x y z
			if (blockdx != 0) {
				ndx = ((Math.abs(selectx1[getid] - selectx2[getid])) + 1);
				ndx = ndx * blockdx;
				ndx = ndx * amountloop;
			}

			int ndy = 0; // now x y z
			if (blockdy != 0) {
				ndy = ((Math.abs(selecty1[getid] - selecty2[getid])) + 1);
				ndy = ndy * blockdy;
				ndy = ndy * amountloop;
			}

			int ndz = 0; // now x y z
			if (blockdz != 0) {
				ndz = ((Math.abs(selectz1[getid] - selectz2[getid])) + 1);
				ndz = ndz * blockdz;
				ndz = ndz * amountloop;
			}

			player.sendMessage("blockdx = " + ndx);
			player.sendMessage("blockdy = " + ndy);
			player.sendMessage("blockdz = " + ndz);

			printC("blockdx = " + ndx);
			printC("blockdy = " + ndy);
			printC("blockdz = " + ndz);

			for (int amount1 = 1; amount1 <= 2; amount1++) {
				/*
				 * for (Entity en : block.getWorld().getEntities()){
				 * if (en.getType() == EntityType.DROPPED_ITEM){
				 * en.remove();
				 * }
				 * }
				 */

				for (Block blb : getselectblock(getid, player)) {
					if (blockdewset(blb.getTypeId()) == false) {
						// player.sendMessage("ptdew&dewdd: error  not allow this item for dewset");
						continue;
					}

					if ((blb.getY() + ndy) > 253 || (blb.getY() + ndy) < 1) {
						continue;
					}
					if (isprotectitemid(blb.getTypeId()) == true) {
						continue;
					}
					blockd = blb.getWorld().getBlockAt((blb.getX() + ndx),
							(blb.getY() + ndy), (blb.getZ() + ndz));
					/*
					 * if (blockd.getTypeId() == 0) {
					 * continue;
					 * }
					 */

					if (amount1 == 1) { // if first round ... only block

						if (isneednearblock(blb.getTypeId()) == true) {
							continue;
						}
						// blockd.setTypeId(0);

						if (isadminname(player.getName()) == false
								&& issubsubadminname(player.getName()) == false) {
							if (blockd.getTypeId() != blb.getTypeId()
									|| blockd.getData() != blb.getData()) {
								if (decreseitem1(player, blb.getTypeId(),
										blb.getData(), false) == false
										&& blb.getTypeId() != 0) {
									player.sendMessage("ptdew&dewdd: dont have enough item");
									player.sendMessage("block > "
											+ blb.getTypeId() + ","
											+ blb.getData());
									return;
								}
							}
						}
					}
					else if (isneednearblock(blb.getTypeId()) == false) { // if
																			// secord
																			// round
																			// ...
																			// only
																			// not
																			// block
																			// block
						if (blb.getType().isBlock() == true) {
							continue;
						}
						// blockd.setTypeId(0);

						if (isadminname(player.getName()) == false
								&& issubsubadminname(player.getName()) == false) {
							if (blockd.getTypeId() != blb.getTypeId()
									|| blockd.getData() != blb.getData()) {
								if (decreseitem1(player, blb.getTypeId(),
										blb.getData(), false) == false
										&& blb.getTypeId() != 0) {
									player.sendMessage("ptdew&dewdd: dont have enough item");
									player.sendMessage("block > "
											+ blb.getTypeId() + ","
											+ blb.getData());
									return;
								}
							}
						}
					}

					blockd = blb.getWorld().getBlockAt((blb.getX() + ndx),
							(blb.getY() + ndy), (blb.getZ() + ndz));
					if (checkpermissionarea(blockd, player, "build") == true) {
						return;
					}

					blockd.setTypeIdAndData(blb.getTypeId(), blb.getData(),
							false);

					// player.sendMessage("." + blockd.getX() + "," +
					// blockd.getY() + "," + blockd.getZ());

				}
				// player.sendMessage("ptdew&dewdd: dewa phrase " + amount1
				// +" /3  done!");
			}

		}

		player.sendMessage("ptdew&dewdd: dewA done");
		printAll("ptdew&dewdd : " + player.getName() + " > dewa done");
	} // dew a

	// decrese item 1

	public void dewbuy(Player player) throws UserDoesNotExistException,
			NoLoanPermittedException {
		if (issubsubadminname(player.getName()) == true) {
			player.sendMessage("ptdew&dewdd: Staff can't buy area");
			return;
		}

		int getid = getfreeselect(player);
		if (selectx1[getid] == 0 && selecty1[getid] == 0
				&& selectz1[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewbuy please set block1");
			player.sendMessage("ptdew&dewdd:dewbuy �?รุณา�?ำห�?ดเ�?ต�?ล�?อ�?ที�? 1");
			return;
		}
		if (selectx2[getid] == 0 && selecty2[getid] == 0
				&& selectz2[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewbuy �?รุณา�?ำห�?ดเ�?ต�?ล�?อ�?ที�?  2");
			return;
		}

		int countblock = -1;

		if (player.hasPermission(pmaindewbuynotcount) == false) {
			countblock = getselectblockforbuy(getid, player);
			if (countblock < 27) {
				player.sendMessage("ptdew&dewdd : smallest area you can buy is 27 blocks");
				player.sendMessage("ptdew&dewdd : ที�?ดิ�?�?�?าดเล�?�?ที�? �?ือ 27 �?ล�?อ�? (�?ุณเลือ�?เ�?ต�?�?อยเ�?ิ�?�?�?)");

				return;
			}

		}
		if (checkpermissionarea(player.getLocation().getBlock(), true) >= 0
				&& ((player.hasPermission(pmaindewbuyreplace)) == false)) {
			player.sendMessage("ptdew&dewdd : You can't buy these area... cuz There are another player's block(home)");
			player.sendMessage("ptdew&dewdd : �?ุณ�?ื�?อที�?ดิ�?ตร�?�?ี�?�?ม�?�?ด�? เ�?ราะมี�?�?�?ื�?อ�?�?�?ล�?ว");
			return;
		}

		player.sendMessage("ptdew&dewdd: Block 1 = (" + selectx1[getid] + ","
				+ selecty1[getid] + "," + selectz1[getid] + ") to ("
				+ selectx2[getid] + "," + selecty2[getid] + ","
				+ selectz2[getid] + ") = " + countblock);

		double buymoneyp = countblock * dewpri.buy1blockmoney;

		player.sendMessage("ptdew&dewdd : Buy '" + countblock
				+ "' use money = " + buymoneyp);
		player.sendMessage("ptdew&dewdd : �?ื�?อที�?ดิ�?�?ำ�?ว�? '"
				+ countblock + "' �?�?�?เ�?ิ�?  = " + buymoneyp);

		if (Economy.getMoney(player.getName()) < buymoneyp) {
			player.sendMessage("ptdew&dewdd: you don't have enough money for buy these area... = "
					+ (Economy.getMoney(player.getName()) - buymoneyp));
			player.sendMessage("ptdew&dewdd: �?ุณมีเ�?ิ�?�?ม�?�?อที�?�?ะ�?ื�?อที�?ดิ�?�?ี�?  = "
					+ (Economy.getMoney(player.getName()) - buymoneyp));

			return;
		}

		player.sendMessage("ptdew&dewdd : ok you have enough money please wait...");
		player.sendMessage("ptdew&dewdd : �?ุณมีเ�?ิ�?มา�?�?อ ที�?�?ะ�?ื�?อ... �?อเ�? รอ�?�?�?�?�?ึ�?�?ะ�?รั�?");

		printAll("ptdew&dewdd : '" + player.getName() + "'starting dewbuy "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());

		player.sendMessage("ptdew&dewdd : "
				+ Economy.getMoney(player.getName()) + " - " + buymoneyp
				+ " = " + (Economy.getMoney(player.getName()) - buymoneyp));

		Economy.setMoney(player.getName(),
				(Economy.getMoney(player.getName()) - buymoneyp));

		player.sendMessage("x"
				+ dewsignmax[getworldid(player.getWorld().getName())] + " , "
				+ dewsignx1[getworldid(player.getWorld().getName())].length);
		player.sendMessage("y"
				+ dewsignmax[getworldid(player.getWorld().getName())] + " , "
				+ dewsigny1[getworldid(player.getWorld().getName())].length);
		player.sendMessage("z"
				+ dewsignmax[getworldid(player.getWorld().getName())] + " , "
				+ dewsignz1[getworldid(player.getWorld().getName())].length);
		adderarraysignfile(getworldid(player.getWorld().getName()));

		dewsignx1[getworldid(player.getWorld().getName())][dewsignmax[getworldid(player
				.getWorld().getName())] - 1] = selectx1[getid];
		dewsigny1[getworldid(player.getWorld().getName())][dewsignmax[getworldid(player
				.getWorld().getName())] - 1] = selecty1[getid];
		dewsignz1[getworldid(player.getWorld().getName())][dewsignmax[getworldid(player
				.getWorld().getName())] - 1] = selectz1[getid];

		dewsignx2[getworldid(player.getWorld().getName())][dewsignmax[getworldid(player
				.getWorld().getName())] - 1] = selectx2[getid];
		dewsigny2[getworldid(player.getWorld().getName())][dewsignmax[getworldid(player
				.getWorld().getName())] - 1] = selecty2[getid];
		dewsignz2[getworldid(player.getWorld().getName())][dewsignmax[getworldid(player
				.getWorld().getName())] - 1] = selectz2[getid];

		dewsignname[getworldid(player.getWorld().getName())][dewsignmax[getworldid(player
				.getWorld().getName())] - 1][0] = player.getName();
		for (int gggg = 1; gggg <= (dewsignnamemax - 1); gggg++) {
			dewsignname[getworldid(player.getWorld().getName())][dewsignmax[getworldid(player
					.getWorld().getName())] - 1][gggg] = "null";
		}

		dewsignloop[getworldid(player.getWorld().getName())][dewsignmax[getworldid(player
				.getWorld().getName())] - 1] = 0;

		savesignfile(-1, getworldid(player.getWorld().getName()));
		loadsignfile();
		printAll("ptdew&dewdd : " + player.getName() + "buy done");
	}

	public void dewbuydelete(Player player) {
		if (player.hasPermission(pmaindewbuydelete) == false) {
			player.sendMessage("ptdew&dewdd: only op can use dewbuydelete");
			return;
		}
		// find id home
		int xyz = checkpermissionarea(player.getLocation().getBlock(), true);
		if (xyz == -1) {
			player.sendMessage("ptdew&dewdd : this area is not protection ... nor home");
			return;
		}

		printAll("ptdew&dewdd : '" + player.getName()
				+ "'starting dewbuydelete "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());

		savesignfile(xyz, getworldid(player.getWorld().getName()));
		loadsignfile();

		printAll("ptdew&dewdd : " + player.getName() + " dewbuydelete done");
	}

	public void dewbuyzone(Player player, Block block2)
			throws UserDoesNotExistException, NoLoanPermittedException {
		if (issubsubadminname(player.getName()) == true) {
			player.sendMessage("ptdew&dewdd: Staff can't buy zone");
			return;
		}

		int homeid = checkpermissionarea(block2, true);
		if (homeid == -1) {
			player.sendMessage("ptdew&dewdd : This is not protect zone...");
			return;
		}

		// String abab = dewsignname[homeid][18] ;
		if (dewsignname[getworldid(player.getWorld().getName())][homeid][18]
				.equalsIgnoreCase("<sell>") == true) {
			printAll("ptdew&dewdd : '" + player.getName()
					+ "'starting dewbuyzone "
					+ player.getItemInHand().getTypeId() + ":"
					+ player.getItemInHand().getData());

			int mon = Integer.parseInt(dewsignname[getworldid(player.getWorld()
					.getName())][homeid][19]);
			player.sendMessage("ptdew&dewdd : This zone need money > " + mon);

			if (Economy.getMoney(player.getName()) < mon) {
				player.sendMessage("ptdew&dewdd: you don't have enough money for buy this zone > "
						+ mon);
				return;
			}

			Economy.subtract(player.getName(), mon);
			for (int g = 0; g <= 19; g++) {
				dewsignname[getworldid(player.getWorld().getName())][homeid][g] = "null";
			}

			dewsignname[getworldid(player.getWorld().getName())][homeid][0] = player
					.getName();
			printAll("ptdew&dewdd: " + player.getName()
					+ "dewbuy zone complete...");
			this.savesignfile(-1, getworldid(block2.getWorld().getName()));

		}
		else {
			player.sendMessage("ptdew&dewdd : This zone  don't allow you to buy sorry...");
			return;
		}

	}

	public void dewclearchunk(World world) {

		double temp1 = 0;
		double temp2 = 0;
		double temp4 = 0;
		double temp5 = 0;
		@SuppressWarnings("unused")
		boolean bo = false;

		for (Chunk chunk : world.getLoadedChunks()) {
			if (chunk == null) {
				continue;
			}
			bo = true;
			if (world.isChunkInUse(chunk.getX(), chunk.getZ()) == true) {
				continue;
			}

			for (Player pla : world.getPlayers()) {
				Block block = pla.getLocation().getBlock();

				int digX = block.getX();
				int digZ = block.getZ();
				temp1 = Math.pow(digX - chunk.getX(), 2);
				temp2 = Math.pow(digZ - chunk.getZ(), 2);
				temp5 = Math.pow(temp1 + temp2, 0.5);
				temp4 = (int) (temp5);

				if (temp4 < 160) {
					bo = false;
					break;
				}
			}

			if (bo = true) {
				if (chunk.isLoaded() == true) {
					printC("ptdew&dewdd:clean Chunk " + chunk.getX() + ",256,"
							+ chunk.getZ() + " >distance " + temp4);
					chunk.unload(true, true);
				}
			}

		}
		printC("ptdew&dewdd:Cleaned Chunk");
	}

	public void dewcopy(Player player) {

		int getid = getfreeselect(player);
		if (selectx1[getid] == 0 && selecty1[getid] == 0
				&& selectz1[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewcopy please set block1");
			player.sendMessage("ptdew&dewdd:dewcopy �?รุณา�?ำห�?ดเ�?ต�?ล�?อ�?ที�? 1");
			return;
		}
		if (selectx2[getid] == 0 && selecty2[getid] == 0
				&& selectz2[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewcopy �?รุณา�?ำห�?ดเ�?ต�?ล�?อ�?ที�?  2");
			return;
		}

		boolean xc = false;
		boolean yc = false;
		boolean zc = false;

		// x
		if (selectx1[getid] <= selectx2[getid]) {
			if ((player.getLocation().getBlockX() >= selectx1[getid])
					&& (player.getLocation().getBlockX() <= selectx2[getid])) {
				xc = true;
			}
		}
		else {
			if ((player.getLocation().getBlockX() <= selectx1[getid])
					&& (player.getLocation().getBlockX() >= selectx2[getid])) {
				xc = true;
			}
		}

		// y
		if (selecty1[getid] <= selecty2[getid]) {
			if ((player.getLocation().getBlockY() >= selecty1[getid])
					&& (player.getLocation().getBlockY() <= selecty2[getid])) {
				yc = true;
			}
		}
		else {
			if ((player.getLocation().getBlockY() <= selecty1[getid])
					&& (player.getLocation().getBlockY() >= selecty2[getid])) {
				yc = true;
			}
		}

		// z
		if (selectz1[getid] <= selectz2[getid]) {
			if ((player.getLocation().getBlockY() >= selectz1[getid])
					&& (player.getLocation().getBlockY() <= selectz2[getid])) {
				zc = true;
			}
		}
		else {
			if ((player.getLocation().getBlockY() <= selectz1[getid])
					&& (player.getLocation().getBlockY() >= selectz2[getid])) {
				zc = true;
			}
		}

		if ((xc == true && yc == true && zc == true) == true) {
			player.sendMessage("ptdew&dewdd: dewcopy can't run cuz you stand on copy area");
			player.sendMessage("ptdew&dewdd: dewcopy �?ม�?อา�?ทำ�?า�?�?ด�? เ�?ราะ�?ุณยื�?อยู�?�?�?เ�?ตที�?เลือ�?�?ว�?");
			return;
		}

		printAll("ptdew&dewdd : '" + player.getName() + "'starting dewcopy "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());

		// 11 - 50
		// 66 = 11-66 = -55

		for (Block bbd : getselectblock(getid, player)) {
			if (bbd.getTypeId() == 0) {
				continue;
			}

			if (player
					.getLocation()
					.getBlock()
					.getRelative(bbd.getX() - selectx1[getid],
							bbd.getY() - selecty1[getid],
							bbd.getZ() - selectz1[getid]).getTypeId() != 0) {
				continue;
			}

			if (blockdewset(bbd.getTypeId()) == false) {
				// player.sendMessage("ptdew&dewdd: error  not allow this item for dewset");
				continue;
			}

			if (decreseitem1(player, bbd.getTypeId(), bbd.getData(), true) == false) {
				player.sendMessage("ptdew&dewdd : dewdown not enought item   > "
						+ bbd.getTypeId() + ":" + bbd.getData());
				continue;
			}

			if (checkpermissionarea(
					player.getLocation()
							.getBlock()
							.getRelative(bbd.getX() - selectx1[getid],
									bbd.getY() - selecty1[getid],
									bbd.getZ() - selectz1[getid]), player,
					"build") == true) {
				return;
			}

			player.getLocation()
					.getBlock()
					.getRelative(bbd.getX() - selectx1[getid],
							bbd.getY() - selecty1[getid],
							bbd.getZ() - selectz1[getid])
					.setTypeIdAndData(bbd.getTypeId(), bbd.getData(), false);
		}

		printAll("ptdew&dewdd: " + player.getName() + " dewcopy done");

	}

	public void dewdelete(Player player) {

		int getid = getfreeselect(player);
		if (selectx1[getid] == 0 && selecty1[getid] == 0
				&& selectz1[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewdelete please set block1");
			return;
		}
		if (selectx2[getid] == 0 && selecty2[getid] == 0
				&& selectz2[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewdelete please set block2");
			return;
		}

		int handid = player.getItemInHand().getTypeId();
		byte handdata = player.getItemInHand().getData().getData();

		printAll("ptdew&dewdd : '" + player.getName() + "'starting dewdelete "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());

		for (Block blb : getselectblock(getid, player)) {
			if (blb.getTypeId() != handid || blb.getData() != handdata) {
				continue;
			}
			if (checkpermissionarea(blb, player, "delete") == true) {
				return;
			}

			if (isadminname(player.getName()) == false
					&& issubsubadminname(player.getName()) == false) {

				if (checkpermissionarea(blb) == false) {
					continue;
				}
			}
			/*
			 * if (PreciousStones.API().canBreak(player, blb.getLocation())==
			 * false) { player.sendMessage("ptdew&dewdd:Can't dewdelete here ("
			 * + blb.getX() + "," + blb.getY() + "," + blb.getZ() + ")");
			 * continue; }
			 */

			/*
			 * if
			 * (PreciousStones.API().isFieldProtectingArea(FieldFlag.PREVENT_PLACE
			 * , blb.getLocation())==false) { player.sendMessage(
			 * "ptdew&dewdd:Can't dewdelete if not your home zome (" +
			 * blb.getX() + "," + blb.getY() + "," + blb.getZ() + ")");
			 * continue; }
			 */

			blb.setTypeId(0, false);

		}

		printAll("ptdew&dewdd: dewdelete done : " + player.getName());
	}

	public void dewdig(Player player) {
		int getid = getfreeselect(player);
		if (selectx1[getid] == 0 && selecty1[getid] == 0
				&& selectz1[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewdig please set block1");
			return;
		}
		if (selectx2[getid] == 0 && selecty2[getid] == 0
				&& selectz2[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewdig please set block2");
			return;
		}

		if (player.getItemInHand().getType().getMaxDurability() <= 0) {
			player.sendMessage("ptdew&dewdd:dewdig item : max Durability = "
					+ player.getItemInHand().getType().getMaxDurability());
			return;
		}

		printAll("ptdew&dewdd : '" + player.getName() + "'starting dewdddig "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());

		for (Block blb : getselectblock(getid, player)) {
			if (checkpermissionarea(blb, player, "destroy") == true) {
				return;
			}

			/*
			 * if (PreciousStones.API().canBreak(player, blb.getLocation())==
			 * false) { player.sendMessage("ptdew&dewdd:Can't dewdig here (" +
			 * blb.getX() + "," + blb.getY() + "," + blb.getZ() + ")");
			 * continue; }
			 */

			if (blb.getTypeId() == 0) {
				continue;
			}

			if (player.getItemInHand().getDurability() > player.getItemInHand()
					.getType().getMaxDurability()) {

				return;
			}

			if (issubsubadminname(player.getName()) == false) {
				for (ItemStack it : blb.getDrops(player.getItemInHand())) {
					player.getWorld().dropItem(player.getLocation(), it);
				}
				blb.setTypeId(0);

			}
			else {
				blb.setTypeId(0);

			}

			player.getItemInHand()
					.setDurability(
							(short) ((short) (player.getItemInHand()
									.getDurability()) + ((short) (1))));

		}

		printAll("ptdew&dewdd: dewdig done : " + player.getName());
	}

	public void dewdot(Player player) throws UserDoesNotExistException,
			NoLoanPermittedException {

		int amo = 0;
		if (player.getInventory().getItem(0) != null) {
			amo += player.getInventory().getItem(0).getAmount();
		}
		if (player.getInventory().getItem(1) != null) {
			amo += player.getInventory().getItem(0).getAmount();
		}

		if (amo == 0) {
			player.sendMessage("ptdew&dewdd: dewdot count radius from amount of item1 and item2");
			player.sendMessage("ptdew&dewdd: dewdot you don't have any item");
			return;
		}

		if (Economy.getMoney(player.getName()) < 500) {
			player.sendMessage("ptdew&dewdd: dewdot need money 500");
			return;
		}

		printAll("ptdew&dewdd : '" + player.getName() + "'starting dewdot "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());

		Economy.setMoney(player.getName(),
				Economy.getMoney(player.getName()) - 500);

		int xx = (int) player.getLocation().getX();
		int yy = (int) player.getLocation().getY();
		int zz = (int) player.getLocation().getZ();

		for (int x = 0 - amo; x <= 0 + amo; x++) {
			if ((x != 0 - amo) && (x != 0 + amo) && (x != 0)) {
				continue;
			}

			for (int y = 0 - amo; y <= 0 + amo; y++) {
				if ((y != 0 - amo) && (y != 0 + amo) && (y != 0)) {
					continue;
				}

				for (int z = 0 - amo; z <= 0 + amo; z++) {
					if ((z != 0 - amo) && (z != 0 + amo) && (z != 0)) {
						continue;
					}

					Block blockk = player.getWorld().getBlockAt(xx, yy, zz)
							.getRelative(x, y, z);
					if (checkpermissionarea(blockk, player, "build") == true) {
						continue;
					}

					blockk.setTypeId(20);
					randomplaynote(blockk.getLocation());
				}
			}
		}

		printAll("ptdew&dewdd: " + player.getName() + "dewdot done!");

	}

	public void dewdown(Player player) {
		if (blockdewset(player.getItemInHand().getTypeId()) == false) {
			player.sendMessage("ptdew&dewdd: error  not allow this item for dewset");
			return;
		}

		if (player.getItemInHand() == null) {
			player.sendMessage("ptdew&dewdd: dewdown  item in hand = null");
			return;
		}

		if (player.getItemInHand().getType().isBlock() == false) {
			player.sendMessage("ptdew&dewdd : dewdown that is not a block");
			return;
		}

		Block block = player.getLocation().getBlock().getRelative(0, -2, 0);
		if (isprotectitemid(block.getTypeId()) == true) {
			return;
		}

		if (checkpermissionarea(block, player, "build") == true) {
			player.sendMessage("ptdew&dewdd: dewdown not complete ; that is another player area");
			return;
		}

		printC("ptdew&dewdd : '" + player.getName() + "'starting dewdown "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());

		block.setTypeId(player.getItemInHand().getTypeId());
		block.setData(player.getItemInHand().getData().getData());

		if (decreseitem1(player, player.getItemInHand().getTypeId(), player
				.getItemInHand().getData().getData(), true) == false) {
			player.sendMessage("ptdew&dewdd : dewdown not enought item");
			return;
		}

		printC("ptdew&dewdd : dewdown " + player.getName() + " complete");

	}

	public void dewfill(Player player) {
		if (blockdewset(player.getItemInHand().getTypeId()) == false) {
			player.sendMessage("ptdew&dewdd: error  not allow this item for dewset");
			return;
		}

		int getid = getfreeselect(player);
		if (selectx1[getid] == 0 && selecty1[getid] == 0
				&& selectz1[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewfill please set block1");
			return;
		}
		if (selectx2[getid] == 0 && selecty2[getid] == 0
				&& selectz2[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewfill please set block2");
			return;
		}

		if (player.getItemInHand().getType().isBlock() == false) {
			player.sendMessage("ptdew&dewdd:dewfill item "
					+ player.getItemInHand().getType().name()
					+ " is not a block");
			return;
		}

		int handid = player.getItemInHand().getTypeId();
		byte handdata = player.getItemInHand().getData().getData();

		printAll("ptdew&dewdd : '" + player.getName() + "'starting dewfill "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());

		for (int amount1 = 1; amount1 <= 2; amount1++) { // 2 time
			for (Entity en : player.getWorld().getEntities()) {
				if (en.getType() == EntityType.DROPPED_ITEM) {
					en.remove();
				}
			}

			for (Block blb : getselectblock(getid, player)) {
				if (amount1 == 1) {
					if (isneednearblock(blb.getTypeId()) == true) {
						continue;
					}
				}
				else {
					if (isneednearblock(blb.getTypeId()) == false) {
						continue;
					}
				}

				/*
				 * if (PreciousStones.API().canPlace(player,
				 * blb.getLocation())==
				 * false) {
				 * player.sendMessage("ptdew&dewdd:Can't dewfill here (" +
				 * blb.getX() + "," + blb.getY() + "," + blb.getZ() + ")");
				 * continue; }
				 */

				if (blb.getTypeId() != 0) {
					continue;
				}
				if (checkpermissionarea(blb, player, "build") == true) {
					return;
				}

				if (isadminname(player.getName()) == false
						&& issubsubadminname(player.getName()) == false) {
					if (decreseitem1(player, handid, handdata, true) == false) {
						player.sendMessage("ptdew&dewdd: dont have enough item");
						return;
					}
				}
				blb.setTypeIdAndData(handid, handdata, false);
				//
			}

		} // 2 itme

		printAll("ptdew&dewdd: dewfill done : " + player.getName());

	}

	public void dewfillblock(Player player) {

		if (blockdewset(player.getItemInHand().getTypeId()) == false) {
			player.sendMessage("ptdew&dewdd: error  not allow this item for dewset");
			return;
		}

		int getid = getfreeselect(player);
		if (selectx1[getid] == 0 && selecty1[getid] == 0
				&& selectz1[getid] == 0) {
			player.sendMessage("ptdew&dewdd: dewfillblock please set block1");
			return;
		}
		if (selectx2[getid] == 0 && selecty2[getid] == 0
				&& selectz2[getid] == 0) {
			player.sendMessage("ptdew&dewdd: dewfillblock please set block2");
			return;
		}

		if (player.getItemInHand().getType().isBlock() == false) {
			player.sendMessage("ptdew&dewdd: dewfillblock item "
					+ player.getItemInHand().getType().name()
					+ " is not a block");
			return;
		}

		int handid = player.getItemInHand().getTypeId();
		byte handdata = player.getItemInHand().getData().getData();

		printAll("ptdew&dewdd : '" + player.getName()
				+ "'starting dewfillblock "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());

		for (int amount1 = 1; amount1 <= 2; amount1++) { // 2 time
			for (Entity en : player.getWorld().getEntities()) {
				if (en.getType() == EntityType.DROPPED_ITEM) {
					en.remove();
				}
			}

			for (Block blb : getselectblock(getid, player)) {
				if (amount1 == 1) {
					if (isneednearblock(blb.getTypeId()) == true) {
						continue;
					}
				}
				else {
					if (isneednearblock(blb.getTypeId()) == false) {
						continue;
					}
				}
				/*
				 * if (PreciousStones.API().canPlace(player,
				 * blb.getLocation())==
				 * false) {
				 * player.sendMessage("ptdew&dewdd:Can't dewsetwall here ("
				 * + blb.getX() + "," + blb.getY() + "," + blb.getZ() + ")");
				 * continue; }
				 */

				if (blb.getTypeId() != 0) {
					continue;
				}

				if (checkpermissionarea(blb, player, "build") == true) {
					return;
				}

				if (blb.getLocation().getBlockY() == selecty1[getid]
						|| blb.getLocation().getBlockY() == selecty2[getid]) {

				}

				if (blb.getLocation().getBlockY() == selecty1[getid]
						|| blb.getLocation().getBlockY() == selecty2[getid]) {

					if (blb.getLocation().getBlockX() == selectx1[getid]
							|| blb.getLocation().getBlockX() == selectz1[getid]
							|| blb.getLocation().getBlockX() == selectx2[getid]
							|| blb.getLocation().getBlockX() == selectz2[getid]
							|| blb.getLocation().getBlockZ() == selectx1[getid]
							|| blb.getLocation().getBlockZ() == selectz1[getid]
							|| blb.getLocation().getBlockZ() == selectx2[getid]
							|| blb.getLocation().getBlockZ() == selectz2[getid]

					) {

						if (isadminname(player.getName()) == false
								&& issubsubadminname(player.getName()) == false) {
							if (decreseitem1(player, handid, handdata, true) == false) {
								player.sendMessage("ptdew&dewdd: dont have enough item");
								return;
							}
						}

						blb.setTypeIdAndData(handid, handdata, false);
						//
					}

				} // up and down
				else { // conner
					if (blb.getLocation().getBlockX() == selectx1[getid]
							&& blb.getLocation().getBlockZ() == selectz1[getid]) {
						if (isadminname(player.getName()) == false
								&& issubsubadminname(player.getName()) == false) {
							if (decreseitem1(player, handid, handdata, true) == false) {
								player.sendMessage("ptdew&dewdd: dont have enough item");
								return;
							}
						}

						blb.setTypeIdAndData(handid, handdata, false);
					}
					if (blb.getLocation().getBlockX() == selectx1[getid]
							&& blb.getLocation().getBlockZ() == selectz2[getid]) {
						if (isadminname(player.getName()) == false
								&& issubsubadminname(player.getName()) == false) {
							if (decreseitem1(player, handid, handdata, true) == false) {
								player.sendMessage("ptdew&dewdd: dont have enough item");
								return;
							}
						}

						blb.setTypeIdAndData(handid, handdata, false);
					}
					if (blb.getLocation().getBlockX() == selectx2[getid]
							&& blb.getLocation().getBlockZ() == selectz1[getid]) {
						if (isadminname(player.getName()) == false
								&& issubsubadminname(player.getName()) == false) {
							if (decreseitem1(player, handid, handdata, true) == false) {
								player.sendMessage("ptdew&dewdd: dont have enough item");
								return;
							}
						}

						blb.setTypeIdAndData(handid, handdata, false);
					}
					if (blb.getLocation().getBlockX() == selectx2[getid]
							&& blb.getLocation().getBlockZ() == selectz2[getid]) {
						if (isadminname(player.getName()) == false
								&& issubsubadminname(player.getName()) == false) {
							if (decreseitem1(player, handid, handdata, true) == false) {
								player.sendMessage("ptdew&dewdd: dont have enough item");
								return;
							}
						}

						blb.setTypeIdAndData(handid, handdata, false);
					}

				}

			}
		} // 2 time

		printAll("ptdew&dewdd: dewfillblock done : " + player.getName());
	}

	public void dewfillroom(Player player) {

		if (blockdewset(player.getItemInHand().getTypeId()) == false) {
			player.sendMessage("ptdew&dewdd: error  not allow this item for dewset");
			return;
		}

		int getid = getfreeselect(player);
		if (selectx1[getid] == 0 && selecty1[getid] == 0
				&& selectz1[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewfillroom please set block1");
			return;
		}
		if (selectx2[getid] == 0 && selecty2[getid] == 0
				&& selectz2[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewfillroom please set block2");
			return;
		}

		if (player.getItemInHand().getType().isBlock() == false) {
			player.sendMessage("ptdew&dewdd:dewfillroom item "
					+ player.getItemInHand().getType().name()
					+ " is not a block");
			return;
		}

		int handid = player.getItemInHand().getTypeId();
		byte handdata = player.getItemInHand().getData().getData();

		printAll("ptdew&dewdd : '" + player.getName()
				+ "'starting dewfillroom " + player.getItemInHand().getTypeId()
				+ ":" + player.getItemInHand().getData());

		for (int amount1 = 1; amount1 <= 2; amount1++) { // 2 time
			for (Entity en : player.getWorld().getEntities()) {
				if (en.getType() == EntityType.DROPPED_ITEM) {
					en.remove();
				}
			}

			for (Block blb : getselectblock(getid, player)) {
				if (amount1 == 1) {
					if (isneednearblock(blb.getTypeId()) == true) {
						continue;
					}
				}
				else {
					if (isneednearblock(blb.getTypeId()) == false) {
						continue;
					}
				}
				/*
				 * if (PreciousStones.API().canPlace(player,
				 * blb.getLocation())==
				 * false) {
				 * player.sendMessage("ptdew&dewdd:Can't dewsetwall here ("
				 * + blb.getX() + "," + blb.getY() + "," + blb.getZ() + ")");
				 * continue; }
				 */

				if (blb.getTypeId() != 0) {
					continue;
				}

				if (checkpermissionarea(blb, player, "build") == true) {
					return;
				}

				if (blb.getLocation().getBlockX() == selectx1[getid]
						|| blb.getLocation().getBlockX() == selectz1[getid]
						|| blb.getLocation().getBlockX() == selectx2[getid]
						|| blb.getLocation().getBlockX() == selectz2[getid]
						|| blb.getLocation().getBlockZ() == selectx1[getid]
						|| blb.getLocation().getBlockZ() == selectz1[getid]
						|| blb.getLocation().getBlockZ() == selectx2[getid]
						|| blb.getLocation().getBlockZ() == selectz2[getid]
						|| blb.getLocation().getBlockY() == selecty1[getid]
						|| blb.getLocation().getBlockY() == selecty2[getid]

				) {

					if (isadminname(player.getName()) == false
							&& issubsubadminname(player.getName()) == false) {
						if (decreseitem1(player, handid, handdata, true) == false) {
							player.sendMessage("ptdew&dewdd: dont have enough item");
							return;
						}
					}

					blb.setTypeIdAndData(handid, handdata, false);
					//
				}
			}
		} // 2 time

		printAll("ptdew&dewdd: dewfillroom done : " + player.getName());
	}

	public void dewfillwall(Player player) {
		if (blockdewset(player.getItemInHand().getTypeId()) == false) {
			player.sendMessage("ptdew&dewdd: error  not allow this item for dewset");
			return;
		}

		int getid = getfreeselect(player);
		if (selectx1[getid] == 0 && selecty1[getid] == 0
				&& selectz1[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewfillwall please set block1");
			return;
		}
		if (selectx2[getid] == 0 && selecty2[getid] == 0
				&& selectz2[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewfillwall please set block2");
			return;
		}

		if (player.getItemInHand().getType().isBlock() == false) {
			player.sendMessage("ptdew&dewdd:dewfillwall item "
					+ player.getItemInHand().getType().name()
					+ " is not a block");
			return;
		}

		int handid = player.getItemInHand().getTypeId();
		byte handdata = player.getItemInHand().getData().getData();

		printAll("ptdew&dewdd : '" + player.getName()
				+ "'starting dewfillwall " + player.getItemInHand().getTypeId()
				+ ":" + player.getItemInHand().getData());

		for (Block blb : getselectblock(getid, player)) {

			/*
			 * 
			 * if (PreciousStones.API().canPlace(player, blb.getLocation())==
			 * false) {
			 * player.sendMessage("ptdew&dewdd:Can't dewfillwall here (" +
			 * blb.getX() + "," + blb.getY() + "," + blb.getZ() + ")");
			 * continue; }
			 */

			if (blb.getTypeId() != 0) {
				continue;
			}

			if (checkpermissionarea(blb, player, "build") == true) {
				return;
			}

			if (blb.getLocation().getBlockX() == selectx1[getid]
					|| blb.getLocation().getBlockX() == selectz1[getid]
					|| blb.getLocation().getBlockX() == selectx2[getid]
					|| blb.getLocation().getBlockX() == selectz2[getid]
					|| blb.getLocation().getBlockZ() == selectx1[getid]
					|| blb.getLocation().getBlockZ() == selectz1[getid]
					|| blb.getLocation().getBlockZ() == selectx2[getid]
					|| blb.getLocation().getBlockZ() == selectz2[getid]

			) {

				if (isadminname(player.getName()) == false
						&& issubsubadminname(player.getName()) == false) {
					if (decreseitem1(player, handid, handdata, true) == false) {
						player.sendMessage("ptdew&dewdd: dont have enough item");
						return;
					}
				}
				blb.setTypeIdAndData(handid, handdata, true);

			}
		} // fill

		printAll("ptdew&dewdd: dewfillwall done : " + player.getName());
	}

	public void dewfullcircle(Player player) {
		if (blockdewset(player.getItemInHand().getTypeId()) == false) {
			player.sendMessage("ptdew&dewdd: error  not allow this item for dewset");
			return;
		}

		int getid = getfreeselect(player);
		if (selectx1[getid] == 0 && selecty1[getid] == 0
				&& selectz1[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewfullcircle please set block1");
			return;
		}
		if (selectx2[getid] == 0 && selecty2[getid] == 0
				&& selectz2[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewfullcircle please set block2");
			return;
		}

		if (player.getItemInHand().getType().isBlock() == false) {
			player.sendMessage("ptdew&dewdd:dewfullcircle item "
					+ player.getItemInHand().getType().name()
					+ " is not a block");
			return;
		}

		int handid = player.getItemInHand().getTypeId();
		byte handdata = player.getItemInHand().getData().getData();

		double midx = ((double) (selectx1[getid]) + (double) (selectx2[getid])) / 2;
		double midy = ((double) (selecty1[getid]) + (double) (selecty2[getid])) / 2;
		double midz = ((double) (selectz1[getid]) + (double) (selectz2[getid])) / 2;

		if ((midx == selectx1[getid] && midy == selecty1[getid] && midz == selectz1[getid])
				|| (midx == selectx2[getid] && midy == selecty2[getid] && midz == selectz2[getid])) {
			player.sendMessage("ptdew&dewdd: small circle can't run program");
			return;
		}

		double temp1 = 0;

		double temp5 = 0;
		double temp2 = 0;
		double temp3 = 0;
		temp1 = Math.pow((double) (selectx1[getid])
				- ((double) (selectx2[getid])), 2);

		temp2 = Math.pow((double) (selecty1[getid])
				- ((double) (selecty2[getid])), 2);

		temp3 = Math.pow((double) (selectz1[getid])
				- ((double) (selectz2[getid])), 2);

		double midty = ((selecty1[getid]) + (selecty2[getid])) / 2;

		double midtx = ((selectx1[getid]) + (selectx2[getid])) / 2;

		double midtz = ((selectz1[getid]) + (selectz2[getid])) / 2;
		temp5 = Math.pow(temp1 + temp2 + temp3, 0.5);

		double midr = temp5 / 3;
		Block blockmid = player.getWorld().getBlockAt((int) midtx, (int) midty,
				(int) midtz);

		player.sendMessage("cir=" + midtx + "," + midty + "," + midtz);
		player.sendMessage("R=" + temp5);

		printAll("ptdew&dewdd : '" + player.getName()
				+ "'starting dewfullcircle "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());

		for (Block blb : getselectblock(getid, player)) {

			/*
			 * if (PreciousStones.API().canPlace(player, blb.getLocation())==
			 * false) {
			 * player.sendMessage("ptdew&dewdd:Can't dewfullcircle here (" +
			 * blb.getX() + "," + blb.getY() + "," + blb.getZ() + ")");
			 * continue; }
			 */

			if (blb.getLocation().distance(blockmid.getLocation()) > midr) {
				continue;
			}

			if (blb.getTypeId() != 0) {
				continue;
			}
			if (checkpermissionarea(blb, player, "build") == true) {
				return;
			}
			if (decreseitem1(player, handid, handdata, true) == false) {
				player.sendMessage("ptdew&dewdd: dont have enough item");
				return;
			}
			blb.setTypeIdAndData(handid, handdata, true);
			//
		} // for

		printAll("ptdew&dewdd: dewfullcircle done : " + player.getName());
	}

	public void dewset(Player player, int e1, byte e2, int e3, byte e4,
			boolean invert) {

		if (blockdewset(e3) == false && (e3 != 0)) {
			player.sendMessage("ptdew&dewdd: error  not allow this item for dewset");
			return;
		}

		int getid = getfreeselect(player);
		if (selectx1[getid] == 0 && selecty1[getid] == 0
				&& selectz1[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewset please set block1");
			return;
		}
		if (selectx2[getid] == 0 && selecty2[getid] == 0
				&& selectz2[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewset please set block2");
			return;
		}

		int handid = e3;
		byte handdata = e4;
		// player.sendMessage(". " + e1 + "," + e2 + "|" + e3 + "," + e4);

		printAll("ptdew&dewdd : '" + player.getName() + "'starting dewset "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());

		for (int amount1 = 1; amount1 <= 2; amount1++) { // 2 time
			for (Entity en : player.getWorld().getEntities()) {
				if (en.getType() == EntityType.DROPPED_ITEM) {
					en.remove();
				}
			}

			for (Block blb : getselectblock(getid, player)) {
				if (amount1 == 1) {
					if (isneednearblock(blb.getTypeId()) == true) {
						continue;
					}
				}
				else {
					if (isneednearblock(blb.getTypeId()) == false) {
						continue;
					}
				}

				if (e1 != -29) {
					if (invert == false) {
						if (blb.getTypeId() != e1) {
							continue;
						}
					}
					else {
						if (blb.getTypeId() == e1) {
							continue;
						}
					}
				}

				if (e2 != -29) {
					if (invert == false) {
						if (blb.getData() != e2) {
							continue;
						}
					}
					else {
						if (blb.getData() == e2) {
							continue;
						}
					}
				}

				if (checkpermissionarea(blb, player, "build") == true) {
					return;
				}

				if (blb.getTypeId() == handid && blb.getData() == handdata) {
					continue;
				}

				if (e3 == 0) { // if delete
					if (isadminname(player.getName()) == false
							&& issubsubadminname(player.getName()) == false) {
						player.sendMessage("ptdew&dewdd: dewsetadvance member can't use");
						return;
					}

					blb.setTypeId(0);

				}

				else { // if place
					if (isadminname(player.getName()) == false
							&& issubsubadminname(player.getName()) == false) {
						if (decreseitem1(player, handid, handdata, true) == false) {
							player.sendMessage("ptdew&dewdd: dont have enough item");
							return;
						}
					}

					blb.setTypeIdAndData(handid, handdata, false);
					//
				}

			} // blb
		} // 2 time

		printAll("ptdew&dewdd: dewset done : " + player.getName());
	}

	public void dewsetblock(Player player) {

		if (blockdewset(player.getItemInHand().getTypeId()) == false) {
			player.sendMessage("ptdew&dewdd: error  not allow this item for dewset");
			return;
		}

		int getid = getfreeselect(player);
		if (selectx1[getid] == 0 && selecty1[getid] == 0
				&& selectz1[getid] == 0) {
			player.sendMessage("ptdew&dewdd: dewsetblock please set block1");
			return;
		}
		if (selectx2[getid] == 0 && selecty2[getid] == 0
				&& selectz2[getid] == 0) {
			player.sendMessage("ptdew&dewdd: dewsetblock please set block2");
			return;
		}

		if (player.getItemInHand().getType().isBlock() == false) {
			player.sendMessage("ptdew&dewdd: dewsetblock item "
					+ player.getItemInHand().getType().name()
					+ " is not a block");
			return;
		}

		int handid = player.getItemInHand().getTypeId();
		byte handdata = player.getItemInHand().getData().getData();

		printAll("ptdew&dewdd : '" + player.getName()
				+ "'starting dewsetblock " + player.getItemInHand().getTypeId()
				+ ":" + player.getItemInHand().getData());

		for (int amount1 = 1; amount1 <= 2; amount1++) { // 2 time
			for (Entity en : player.getWorld().getEntities()) {
				if (en.getType() == EntityType.DROPPED_ITEM) {
					en.remove();
				}
			}

			for (Block blb : getselectblock(getid, player)) {
				if (amount1 == 1) {
					if (isneednearblock(blb.getTypeId()) == true) {
						continue;
					}
				}
				else {
					if (isneednearblock(blb.getTypeId()) == false) {
						continue;
					}
				}
				/*
				 * if (PreciousStones.API().canPlace(player,
				 * blb.getLocation())==
				 * false) {
				 * player.sendMessage("ptdew&dewdd:Can't dewsetwall here ("
				 * + blb.getX() + "," + blb.getY() + "," + blb.getZ() + ")");
				 * continue; }
				 */

				if (blb.getTypeId() == handid && blb.getData() == handdata) {
					continue;
				}

				if (checkpermissionarea(blb, player, "build") == true) {
					return;
				}

				if (blb.getLocation().getBlockY() == selecty1[getid]
						|| blb.getLocation().getBlockY() == selecty2[getid]) {

				}

				if (blb.getLocation().getBlockY() == selecty1[getid]
						|| blb.getLocation().getBlockY() == selecty2[getid]) {

					if (blb.getLocation().getBlockX() == selectx1[getid]
							|| blb.getLocation().getBlockX() == selectz1[getid]
							|| blb.getLocation().getBlockX() == selectx2[getid]
							|| blb.getLocation().getBlockX() == selectz2[getid]
							|| blb.getLocation().getBlockZ() == selectx1[getid]
							|| blb.getLocation().getBlockZ() == selectz1[getid]
							|| blb.getLocation().getBlockZ() == selectx2[getid]
							|| blb.getLocation().getBlockZ() == selectz2[getid]

					) {

						if (isadminname(player.getName()) == false
								&& issubsubadminname(player.getName()) == false) {
							if (decreseitem1(player, handid, handdata, true) == false) {
								player.sendMessage("ptdew&dewdd: dont have enough item");
								return;
							}
						}

						blb.setTypeIdAndData(handid, handdata, false);
						//
					}

				} // up and down
				else { // conner
					if (blb.getLocation().getBlockX() == selectx1[getid]
							&& blb.getLocation().getBlockZ() == selectz1[getid]) {
						if (isadminname(player.getName()) == false
								&& issubsubadminname(player.getName()) == false) {
							if (decreseitem1(player, handid, handdata, true) == false) {
								player.sendMessage("ptdew&dewdd: dont have enough item");
								return;
							}
						}

						blb.setTypeIdAndData(handid, handdata, false);
					}
					if (blb.getLocation().getBlockX() == selectx1[getid]
							&& blb.getLocation().getBlockZ() == selectz2[getid]) {
						if (isadminname(player.getName()) == false
								&& issubsubadminname(player.getName()) == false) {
							if (decreseitem1(player, handid, handdata, true) == false) {
								player.sendMessage("ptdew&dewdd: dont have enough item");
								return;
							}
						}

						blb.setTypeIdAndData(handid, handdata, false);
					}
					if (blb.getLocation().getBlockX() == selectx2[getid]
							&& blb.getLocation().getBlockZ() == selectz1[getid]) {
						if (isadminname(player.getName()) == false
								&& issubsubadminname(player.getName()) == false) {
							if (decreseitem1(player, handid, handdata, true) == false) {
								player.sendMessage("ptdew&dewdd: dont have enough item");
								return;
							}
						}

						blb.setTypeIdAndData(handid, handdata, false);
					}
					if (blb.getLocation().getBlockX() == selectx2[getid]
							&& blb.getLocation().getBlockZ() == selectz2[getid]) {
						if (isadminname(player.getName()) == false
								&& issubsubadminname(player.getName()) == false) {
							if (decreseitem1(player, handid, handdata, true) == false) {
								player.sendMessage("ptdew&dewdd: dont have enough item");
								return;
							}
						}

						blb.setTypeIdAndData(handid, handdata, false);
					}

				}

			}
		} // 2 time

		printAll("ptdew&dewdd: dewsetblock done : " + player.getName());
	}

	public void dewsetprivate(Player player) throws UserDoesNotExistException,
			NoLoanPermittedException {

		int getid = getfreeselect(player);
		if (selectx1[getid] == 0 && selecty1[getid] == 0
				&& selectz1[getid] == 0) {
			player.sendMessage("ptdew&dewdd: dewsetprivate please set block1");
			return;
		}
		if (selectx2[getid] == 0 && selecty2[getid] == 0
				&& selectz2[getid] == 0) {
			player.sendMessage("ptdew&dewdd: dewsetprivate please set block2");
			return;
		}

		int handid = player.getItemInHand().getTypeId();
		byte handdata = player.getItemInHand().getData().getData();

		printAll("ptdew&dewdd : '" + player.getName()
				+ "'starting dewsetprivate "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());

		for (Block blb : getselectblock(getid, player)) {

			if (blb.getTypeId() == 0) {
				continue;
			}

			if (checkpermissionarea(blb, player, "build") == true) {
				continue;
			}

			if (Economy.getMoney(player.getName()) < dewpri.buy1blockmoney) {
				player.sendMessage("ptdew&dewdd: dewsetprivate need more money...");
				return;
			}

			if (dewpri.checkpermissionareasign(blb) == true) {
				continue;
			}
			protectrailrun(blb, player);
			Economy.subtract(player.getName(), dewpri.buy1blockmoney);

		}

		printAll("ptdew&dewdd: dewsetprivate done : " + player.getName());
	}

	public void dewsetroom(Player player) {

		if (blockdewset(player.getItemInHand().getTypeId()) == false) {
			player.sendMessage("ptdew&dewdd: error  not allow this item for dewset");
			return;
		}

		int getid = getfreeselect(player);
		if (selectx1[getid] == 0 && selecty1[getid] == 0
				&& selectz1[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewsetroom please set block1");
			return;
		}
		if (selectx2[getid] == 0 && selecty2[getid] == 0
				&& selectz2[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewsetroom please set block2");
			return;
		}

		if (player.getItemInHand().getType().isBlock() == false) {
			player.sendMessage("ptdew&dewdd:dewsetroom item "
					+ player.getItemInHand().getType().name()
					+ " is not a block");
			return;
		}

		int handid = player.getItemInHand().getTypeId();
		byte handdata = player.getItemInHand().getData().getData();

		printAll("ptdew&dewdd : '" + player.getName() + "'starting dewsetroom "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());

		for (int amount1 = 1; amount1 <= 2; amount1++) { // 2 time
			for (Entity en : player.getWorld().getEntities()) {
				if (en.getType() == EntityType.DROPPED_ITEM) {
					en.remove();
				}
			}

			for (Block blb : getselectblock(getid, player)) {
				if (amount1 == 1) {
					if (isneednearblock(blb.getTypeId()) == true) {
						continue;
					}
				}
				else {
					if (isneednearblock(blb.getTypeId()) == false) {
						continue;
					}
				}
				/*
				 * if (PreciousStones.API().canPlace(player,
				 * blb.getLocation())==
				 * false) {
				 * player.sendMessage("ptdew&dewdd:Can't dewsetwall here ("
				 * + blb.getX() + "," + blb.getY() + "," + blb.getZ() + ")");
				 * continue; }
				 */

				if (blb.getTypeId() == handid && blb.getData() == handdata) {
					continue;
				}

				if (checkpermissionarea(blb, player, "build") == true) {
					return;
				}

				if (blb.getLocation().getBlockX() == selectx1[getid]
						|| blb.getLocation().getBlockX() == selectz1[getid]
						|| blb.getLocation().getBlockX() == selectx2[getid]
						|| blb.getLocation().getBlockX() == selectz2[getid]
						|| blb.getLocation().getBlockZ() == selectx1[getid]
						|| blb.getLocation().getBlockZ() == selectz1[getid]
						|| blb.getLocation().getBlockZ() == selectx2[getid]
						|| blb.getLocation().getBlockZ() == selectz2[getid]
						|| blb.getLocation().getBlockY() == selecty1[getid]
						|| blb.getLocation().getBlockY() == selecty2[getid]

				) {

					if (isadminname(player.getName()) == false
							&& issubsubadminname(player.getName()) == false) {
						if (decreseitem1(player, handid, handdata, true) == false) {
							player.sendMessage("ptdew&dewdd: dont have enough item");
							return;
						}
					}

					blb.setTypeIdAndData(handid, handdata, false);
					//
				}
			}
		} // 2 time

		printAll("ptdew&dewdd: dewsetroom done : " + player.getName());
	}

	public void dewsetwall(Player player) {

		if (blockdewset(player.getItemInHand().getTypeId()) == false) {
			player.sendMessage("ptdew&dewdd: error  not allow this item for dewset");
			return;
		}

		int getid = getfreeselect(player);
		if (selectx1[getid] == 0 && selecty1[getid] == 0
				&& selectz1[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewsetwall please set block1");
			return;
		}
		if (selectx2[getid] == 0 && selecty2[getid] == 0
				&& selectz2[getid] == 0) {
			player.sendMessage("ptdew&dewdd:dewsetwall please set block2");
			return;
		}

		if (player.getItemInHand().getType().isBlock() == false) {
			player.sendMessage("ptdew&dewdd:dewsetwall item "
					+ player.getItemInHand().getType().name()
					+ " is not a block");
			return;
		}

		int handid = player.getItemInHand().getTypeId();
		byte handdata = player.getItemInHand().getData().getData();

		printAll("ptdew&dewdd : '" + player.getName() + "'starting dewsetwall "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());

		for (int amount1 = 1; amount1 <= 2; amount1++) { // 2 time
			for (Entity en : player.getWorld().getEntities()) {
				if (en.getType() == EntityType.DROPPED_ITEM) {
					en.remove();
				}
			}

			for (Block blb : getselectblock(getid, player)) {
				if (amount1 == 1) {
					if (isneednearblock(blb.getTypeId()) == true) {
						continue;
					}
				}
				else {
					if (isneednearblock(blb.getTypeId()) == false) {
						continue;
					}
				}
				/*
				 * if (PreciousStones.API().canPlace(player,
				 * blb.getLocation())==
				 * false) {
				 * player.sendMessage("ptdew&dewdd:Can't dewsetwall here ("
				 * + blb.getX() + "," + blb.getY() + "," + blb.getZ() + ")");
				 * continue; }
				 */

				if (blb.getTypeId() == handid && blb.getData() == handdata) {
					continue;
				}

				if (checkpermissionarea(blb, player, "build") == true) {
					return;
				}

				if (blb.getLocation().getBlockX() == selectx1[getid]
						|| blb.getLocation().getBlockX() == selectz1[getid]
						|| blb.getLocation().getBlockX() == selectx2[getid]
						|| blb.getLocation().getBlockX() == selectz2[getid]
						|| blb.getLocation().getBlockZ() == selectx1[getid]
						|| blb.getLocation().getBlockZ() == selectz1[getid]
						|| blb.getLocation().getBlockZ() == selectx2[getid]
						|| blb.getLocation().getBlockZ() == selectz2[getid]

				) {

					if (isadminname(player.getName()) == false
							&& issubsubadminname(player.getName()) == false) {
						if (decreseitem1(player, handid, handdata, true) == false) {
							player.sendMessage("ptdew&dewdd: dont have enough item");
							return;
						}
					}

					blb.setTypeIdAndData(handid, handdata, false);
					//
				}
			}
		} // 2 time

		printAll("ptdew&dewdd: dewsetwall done : " + player.getName());
	}

	public void dewspread4(Player player) {

		if (blockdewset(player.getItemInHand().getTypeId()) == false) {
			player.sendMessage("ptdew&dewdd: error  not allow this item for dewset");
			return;
		}

		player.sendMessage("dew spread 4");
		int xid = player.getItemInHand().getTypeId();
		Byte xdata = (byte) player.getItemInHand().getData().getData();

		player.sendMessage("xid = " + xid);
		player.sendMessage("xdata = " + xdata);

		Block block = player.getLocation().getBlock();

		// 64 = all
		int xmax = 2304;
		Block blockxlist[] = new Block[xmax];
		int xnow = -1;
		// loop

		Block blockC;
		int gy = 0;

		printAll("ptdew&dewdd : '" + player.getName() + "'starting dewspread4 "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());
		// search
		for (int gx = -1; gx <= 1; gx++) {

			for (int gz = -1; gz <= 1; gz++) {
				/*
				 * -1 0
				 * 1 0
				 * 0 -1
				 * 0 1
				 */
				if ((gx != -1 && gz != 0) && (gx != 1 && gz != 0)
						&& (gx != 0 && gz != -1) && (gx != 0 && gz != 1)

				) {
					continue;
				}

				blockC = block.getRelative(gx, gy, gz);
				if (blockC.getTypeId() != 0) {
					continue;
				}

				if (checkpermissionarea(blockC, player, "build") == true) {
					continue;
				}

				if (xnow >= xmax) {
					break;
				}

				xnow++;
				blockxlist[xnow] = blockC;
			}
		}

		// Super loop
		boolean theend = false;
		int superl = -1;

		while (theend == false) {
			superl++;
			blockC = blockxlist[superl];
			if (blockC == null) {
				theend = true;
				break;
			}

			if (xnow >= xmax - 1) {
				theend = true;
				break;
			}

			// player.sendMessage("superl 1 = " + superl);
			// player.sendMessage("xnow = " + xnow);
			for (int gx = -1; gx <= 1; gx++) {
				gy = 0;
				for (int gz = -1; gz <= 1; gz++) {
					if ((gx != -1 && gz != 0) && (gx != 1 && gz != 0)
							&& (gx != 0 && gz != -1) && (gx != 0 && gz != 1)

					) {
						continue;
					}

					blockC = blockxlist[superl].getRelative(gx, gy, gz);
					if (blockC.getTypeId() != 0) {
						continue;
					}

					// loop all check old

					boolean checktrue = false;
					for (int gcheck = 0; gcheck <= xnow; gcheck++) {
						if (blockC.getLocation().getBlockX() == blockxlist[gcheck]
								.getLocation().getBlockX()
								&& blockC.getLocation().getBlockY() == blockxlist[gcheck]
										.getLocation().getBlockY()
								&& blockC.getLocation().getBlockZ() == blockxlist[gcheck]
										.getLocation().getBlockZ()) {
							checktrue = true;
							break;
						}

					}
					if (checktrue == true) {
						continue;
					}

					if (xnow >= xmax - 1) {
						break;
					}

					if (checkpermissionarea(blockC, player, "build") == true) {
						continue;
					}

					/*
					 * player.sendMessage("added " + xnow + " ("
					 * + blockC.getLocation().getBlockX() + ","
					 * + blockC.getLocation().getBlockY() + ","
					 * + blockC.getLocation().getBlockZ() +
					 * ")");
					 */

					xnow++;
					blockxlist[xnow] = blockC;

				}

			} // loop gx

		} // loop superl

		player.sendMessage("starting spread");

		for (int ee = 0; ee < xnow; ee++) {
			if (decreseitem1(player, xid, xdata, true) == false) {
				player.sendMessage("ptdew&dewdd : dewspread not enough item");
				return;
			}
			blockxlist[ee].setTypeId(xid);
			blockxlist[ee].setData(xdata);
			// randomplaynote(blockxlist[ee].getLocation());
		}

		for (@SuppressWarnings("unused")
		Object obj : blockxlist) {
			obj = null;
		}
		blockxlist = null;

		printAll("ptdew&dewdd: dewspread4 done : " + player.getName());
	}

	public void dewspread9(Player player) {
		if (blockdewset(player.getItemInHand().getTypeId()) == false) {
			player.sendMessage("ptdew&dewdd: error  not allow this item for dewset");
			return;
		}

		player.sendMessage("dew spread 9");
		int xid = player.getItemInHand().getTypeId();
		Byte xdata = (byte) player.getItemInHand().getData().getData();

		player.sendMessage("xid = " + xid);
		player.sendMessage("xdata = " + xdata);

		Block block = player.getLocation().getBlock();

		// 64 = all
		int xmax = 2304;
		Block blockxlist[] = new Block[xmax];
		int xnow = -1;
		// loop

		Block blockC;
		int gy = 0;

		printAll("ptdew&dewdd : '" + player.getName() + "'starting dewspread9 "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());

		// search
		for (int gx = -1; gx <= 1; gx++) {

			for (int gz = -1; gz <= 1; gz++) {
				blockC = block.getRelative(gx, gy, gz);
				if (blockC.getTypeId() != 0) {
					continue;
				}

				if (checkpermissionarea(blockC, player, "build") == true) {
					continue;
				}

				if (xnow >= xmax) {
					break;
				}

				xnow++;
				blockxlist[xnow] = blockC;
			}
		}

		// Super loop
		boolean theend = false;
		int superl = -1;

		while (theend == false) {
			superl++;
			blockC = blockxlist[superl];
			if (blockC == null) {
				theend = true;
				break;
			}

			if (xnow >= xmax - 1) {
				theend = true;
				break;
			}

			// player.sendMessage("superl 1 = " + superl);
			// player.sendMessage("xnow = " + xnow);
			for (int gx = -1; gx <= 1; gx++) {
				gy = 0;
				for (int gz = -1; gz <= 1; gz++) {

					blockC = blockxlist[superl].getRelative(gx, gy, gz);
					if (blockC.getTypeId() != 0) {
						continue;
					}

					// loop all check old

					boolean checktrue = false;
					for (int gcheck = 0; gcheck <= xnow; gcheck++) {
						if (blockC.getLocation().getBlockX() == blockxlist[gcheck]
								.getLocation().getBlockX()
								&& blockC.getLocation().getBlockY() == blockxlist[gcheck]
										.getLocation().getBlockY()
								&& blockC.getLocation().getBlockZ() == blockxlist[gcheck]
										.getLocation().getBlockZ()) {
							checktrue = true;
							break;
						}

					}
					if (checktrue == true) {
						continue;
					}

					if (xnow >= xmax - 1) {
						break;
					}

					/*
					 * player.sendMessage("added " + xnow + " ("
					 * + blockC.getLocation().getBlockX() + ","
					 * + blockC.getLocation().getBlockY() + ","
					 * + blockC.getLocation().getBlockZ() +
					 * ")");
					 */

					if (checkpermissionarea(blockC, player, "build") == true) {
						continue;
					}

					xnow++;
					blockxlist[xnow] = blockC;

				}

			} // loop gx

		} // loop superl

		player.sendMessage("starting spread");

		for (int ee = 0; ee < xnow; ee++) {
			if (decreseitem1(player, xid, xdata, true) == false) {
				player.sendMessage("ptdew&dewdd : dewspread not enough item");
				return;
			}
			blockxlist[ee].setTypeId(xid);
			blockxlist[ee].setData(xdata);
			// randomplaynote(blockxlist[ee].getLocation());
		}
		for (@SuppressWarnings("unused")
		Object obj : blockxlist) {
			obj = null;
		}
		blockxlist = null;

		printAll("ptdew&dewdd: dewspread9 done : " + player.getName());
	}

	public void dewspreadcircle(Player player) {
		if (blockdewset(player.getItemInHand().getTypeId()) == false) {
			player.sendMessage("ptdew&dewdd: error  not allow this item for dewset");
			return;
		}

		int handid = player.getItemInHand().getTypeId();
		byte handdata = player.getItemInHand().getData().getData();

		Block block = player.getLocation().getBlock();
		Queue<Block> bd = new LinkedList<Block>();

		boolean ne = false;
		Block b2 = null;
		int x = 0;
		int y = 0;
		int z = 0;

		for (x = -1; x <= 1; x++) {
			for (y = -1; y <= 1; y++) {
				for (z = -1; z <= 1; z++) {
					b2 = block.getRelative(x, y, z);

					bd.add(b2);
				}
			}
		}

		Block b3 = null;

		if (bd.size() <= 0) {
			return;
		}

		while (bd.size() > 0) { // bll
			b3 = bd.poll();

			ne = false;

			for (x = -1; x <= 1; x++) {
				for (y = -1; y <= 1; y++) {
					for (z = -1; z <= 1; z++) {
						b2 = b3.getRelative(x, y, z);

						if (b2.getTypeId() == 0) {
							if (checkpermissionarea(b2, player, "build") == true) {
								continue;
							}
							if (decreseitem1(player, handid, handdata, true) == false) {
								player.sendMessage("not enough item");
								return;
							}
							b2.setTypeId(handid);
							b2.setData(handdata);

							ne = true;
							break;
						}

					}
				}
			}

			if (ne == false) {
				continue;
			}

			for (x = -1; x <= 1; x++) {
				for (y = -1; y <= 1; y++) {
					if (y + b3.getY() < 1 || y + b3.getY() > 254) {
						continue;
					}

					for (z = -1; z <= 1; z++) {

						b2 = b3.getRelative(x, y, z);
						// printAll("ptdew&dewdd: delete near call sub (" +
						// b2.getX() + "," + b2.getY() + "," + b2.getZ() + ") "
						// + amount);

						bd.add(b2);

					}

				}

			}

		}// bll

		player.sendMessage("dewspreadcircle done");
	}

	public void dewspreadq(Player player) {
		if (blockdewset(player.getItemInHand().getTypeId()) == false) {
			player.sendMessage("ptdew&dewdd: error  not allow this item for dewset");
			return;
		}

		int handid = player.getItemInHand().getTypeId();
		byte handdata = player.getItemInHand().getData().getData();

		Block block = player.getLocation().getBlock();
		Queue<Block> bd = new LinkedList<Block>();

		boolean ne = false;
		Block b2 = null;
		int x = 0;
		int z = 0;

		for (x = -1; x <= 1; x++) {

			for (z = -1; z <= 1; z++) {
				b2 = block.getRelative(x, 0, z);

				bd.add(b2);
			}

		}

		Block b3 = null;

		if (bd.size() <= 0) {
			return;
		}

		while (bd.size() > 0) { // bll
			b3 = bd.poll();

			ne = false;

			if (b3.getTypeId() == 0) {
				if (checkpermissionarea(b3, player, "build") == true) {
					continue;
				}
				if (decreseitem1(player, handid, handdata, true) == false) {
					player.sendMessage("not enough item");
					return;
				}

				b3.setTypeId(handid);
				b3.setData(handdata);

				ne = true;
			}

			if (ne == false) {
				continue;
			}

			for (x = -1; x <= 1; x++) {

				for (z = -1; z <= 1; z++) {

					if (x != 0 && z != 0) {
						continue;
					}
					b2 = b3.getRelative(x, 0, z);
					// printAll("ptdew&dewdd: delete near call sub (" +
					// b2.getX() + "," + b2.getY() + "," + b2.getZ() + ") " +
					// amount);

					bd.add(b2);

				}

			}

		}// bll

		player.sendMessage("dewspreadq done");
	}

	public void dewteleport(Player player) {
		Block block = player.getLocation().getBlock().getRelative(0, -2, 0);
		if (block.getTypeId() == 68 || block.getTypeId() == 68) {
			Sign sign = (Sign) block.getState();
			if (sign.getLine(0).equalsIgnoreCase("[dewteleport]") == true) {

				int xh = Integer.parseInt(sign.getLine(1));
				int yh = Integer.parseInt(sign.getLine(2));
				int zh = Integer.parseInt(sign.getLine(3));

				Location loca = player.getLocation();
				loca.setX(xh);
				loca.setY(yh);
				loca.setZ(zh);
				player.teleport(loca);
				randomplaynote(block.getLocation());
			}
		}
	}

	public void dewteleport(Vehicle player) {
		Block block = player.getLocation().getBlock().getRelative(0, -2, 0);
		if (block.getTypeId() == 68) {
			Sign sign = (Sign) block.getState();
			if (sign.getLine(0).equalsIgnoreCase("[dewteleport]") == true) {

				int xh = Integer.parseInt(sign.getLine(1));
				int yh = Integer.parseInt(sign.getLine(2));
				int zh = Integer.parseInt(sign.getLine(3));

				Location loca = player.getLocation();
				loca.setX(xh);
				loca.setY(yh);
				loca.setZ(zh);
				player.teleport(loca);
				randomplaynote(block.getLocation());
			}
		}
	}

	public void dewtime(Player player) {

		int getid = getfreeselect(player);
		if (selectx1[getid] == 0 && selecty1[getid] == 0
				&& selectz1[getid] == 0) {
			player.sendMessage("ptdew&dewdd: dewtime please set block1");
			return;
		}
		if (selectx2[getid] == 0 && selecty2[getid] == 0
				&& selectz2[getid] == 0) {
			player.sendMessage("ptdew&dewdd: dewtime please set block2");
			return;
		}

		printAll("ptdew&dewdd : '" + player.getName() + "'starting dewtime "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());

		int mx = 0;
		int my = 0;
		int mz = 0;

		int lx = 0;
		int ly = 0;
		int lz = 0;

		if (selectx1[getid] >= selectx2[getid]) {
			mx = selectx1[getid];
			lx = selectx2[getid];
		}
		else {
			lx = selectx1[getid];
			mx = selectx2[getid];
		}

		if (selecty1[getid] >= selecty2[getid]) {
			my = selecty1[getid];
			ly = selecty2[getid];
		}
		else {
			ly = selecty1[getid];
			my = selecty2[getid];
		}

		if (selectz1[getid] >= selectz2[getid]) {
			mz = selectz1[getid];
			lz = selectz2[getid];
		}
		else {
			lz = selectz1[getid];
			mz = selectz2[getid];
		}

		Block block = null;
		Chunk chunk = null;

		for (int nx = lx; nx <= mx; nx += 16) {
			for (int nz = lz; nz <= mz; nz += 16) {

				block = player.getWorld().getBlockAt(nx, 1, nz);
				block.getChunk().load();
				chunk = block.getChunk();

				// add to new

				boolean isad = false;

				for (int ddx = 0; ddx <= timechunkmax; ddx++) {
					if (chunk.getX() * 16 == timechunkx[ddx]
							&& chunk.getZ() * 16 == timechunkz[ddx]) {
						isad = true;
						break;
					}
				}

				if (isad == false) {
					timechunkmax++;
					timechunkx[timechunkmax] = chunk.getX() * 16;
					timechunkz[timechunkmax] = chunk.getZ() * 16;
					printAll("dewtime added new chunk = [" + timechunkmax
							+ "] > " + timechunkx[timechunkmax] + ","
							+ timechunkz[timechunkmax]);
				}

			}

		}

		printAll("ptdew&dewdd:  dewtime  done : " + player.getName());
	}

	public void dewwallcircle(Player player) {
		if (blockdewset(player.getItemInHand().getTypeId()) == false) {
			player.sendMessage("ptdew&dewdd: error  not allow this item for dewset");
			return;
		}

		int getid = getfreeselect(player);
		if (selectx1[getid] == 0 && selecty1[getid] == 0
				&& selectz1[getid] == 0) {
			player.sendMessage("ptdew&dewdd: dewwallcircle please set block1");
			return;
		}
		if (selectx2[getid] == 0 && selecty2[getid] == 0
				&& selectz2[getid] == 0) {
			player.sendMessage("ptdew&dewdd: dewwallcircle please set block2");
			return;
		}

		if (player.getItemInHand().getType().isBlock() == false) {
			player.sendMessage("ptdew&dewdd: dewwallcircle item "
					+ player.getItemInHand().getType().name()
					+ " is not a block");
			return;
		}

		int handid = player.getItemInHand().getTypeId();
		byte handdata = player.getItemInHand().getData().getData();

		double midx = ((double) (selectx1[getid]) + (double) (selectx2[getid])) / 2;
		double midy = ((double) (selecty1[getid]) + (double) (selecty2[getid])) / 2;
		double midz = ((double) (selectz1[getid]) + (double) (selectz2[getid])) / 2;

		if ((midx == selectx1[getid] && midy == selecty1[getid] && midz == selectz1[getid])
				|| (midx == selectx2[getid] && midy == selecty2[getid] && midz == selectz2[getid])) {
			player.sendMessage("ptdew&dewdd: small circle can't run program");
			return;
		}

		double temp1 = 0;

		double temp5 = 0;
		double temp2 = 0;
		double temp3 = 0;
		temp1 = Math.pow((double) (selectx1[getid])
				- ((double) (selectx2[getid])), 2);

		temp2 = Math.pow((double) (selecty1[getid])
				- ((double) (selecty2[getid])), 2);

		temp3 = Math.pow((double) (selectz1[getid])
				- ((double) (selectz2[getid])), 2);

		double midty = ((selecty1[getid]) + (selecty2[getid])) / 2;

		double midtx = ((selectx1[getid]) + (selectx2[getid])) / 2;

		double midtz = ((selectz1[getid]) + (selectz2[getid])) / 2;
		temp5 = Math.pow(temp1 + temp2 + temp3, 0.5);

		double midr = temp5 / 3;
		Block blockmid = player.getWorld().getBlockAt((int) midtx, (int) midty,
				(int) midtz);

		player.sendMessage("cir=" + midtx + "," + midty + "," + midtz);
		player.sendMessage("R=" + temp5);

		printAll("ptdew&dewdd : '" + player.getName()
				+ "'starting dewwallcircle "
				+ player.getItemInHand().getTypeId() + ":"
				+ player.getItemInHand().getData());

		for (Block blb : getselectblock(getid, player)) {

			/*
			 * if (PreciousStones.API().canPlace(player, blb.getLocation())==
			 * false) {
			 * player.sendMessage("ptdew&dewdd:Can't dewfullcircle here (" +
			 * blb.getX() + "," + blb.getY() + "," + blb.getZ() + ")");
			 * continue; }
			 */
			if ((int) blb.getLocation().distance(blockmid.getLocation()) != (int) midr) {
				continue;
			}

			if (blb.getTypeId() != 0) {
				continue;
			}
			if (checkpermissionarea(blb, player, "build") == true) {
				return;
			}
			if (decreseitem1(player, handid, handdata, true) == false) {
				player.sendMessage("ptdew&dewdd: dont have enough item");
				return;
			}
			blb.setTypeIdAndData(handid, handdata, true);
			//
		} // for

		printAll("ptdew&dewdd: dewwallcircle done : " + player.getName());
	}

	// fixtool

	public int distance2d(int x1, int z1, int x2, int z2) {
		double t1 = Math.pow(x1 - x2, 2);
		double t2 = Math.pow(z1 - z2, 2);
		double t3 = Math.pow(t1 + t2, 0.5);
		int t4 = (int) t3;
		return t4;
	}

	public void fixfreeall() throws UserDoesNotExistException,
			NoLoanPermittedException {
		for (Player pd : Bukkit.getOnlinePlayers()) {

			Economy.add(pd.getName(), 500);

		}
		printC("dew fix all player");
	}

	public String fundeathmessage(String st, Player player) {
		String sx = st;
		int per = 0;
		player.sendMessage("(" + st + ")");
		if (st.equalsIgnoreCase(player.getName() + " hit the ground too hard") == true) {
			per = randomG.nextInt(8);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " �?ิดว�?าตัวเอ�?เ�?�?�?�?�? �?ึ�?�?ระ�?ดดล�?�?า�?�?�?า";
				break;
			case 1:
				sx = player.getName()
						+ " ลอ�?�?ระ�?ดด�?า�?�?ั�?�? 255 �?ะว�?าล�?�?�?ำ�?อดี...";
				break;
			case 2:
				sx = player.getName()
						+ " ต�?�?ล�?อ�?สู�?ห�?ึ�?�?�?ั�?�? อาย�?�?ตาย";
				break;
			case 3:
				sx = player.getName()
						+ " �?ระ�?ดดร�?ม�?า�?เ�?รื�?อ�?�?ิ�? �?ต�?ลืมเอาร�?ม�?ู�?ี�?ออ�?มา";
				break;
			case 4:
				sx = player.getName()
						+ " ทดลอ�?�?ิ�?�?�?มาย�?รา�?�?รั�?�?�?ร�? ต�?ล�?มาตาย";
				break;
			case 5:
				sx = player.getName()
						+ " เห�?�?�?�?อ�?เม�? �?ึ�?�?ระ�?ดด�?�?ยื�? �?ล�?ว... ต�?ล�?มาตาย";
				break;
			case 6:
				sx = player.getName()
						+ " �?ด�?ต�?ล�?มา�?ระท�?�?ื�?�?อั�?�?ุ�?ม�?ิ�?ม�?ล�?ว";
				break;
			case 7:
				sx = player.getName() + " ต�?ล�?�?ื�?�?ดั�?ตุ�?!@#!@";
				break;

			}

		}

		if (st.equalsIgnoreCase(player.getName() + " tried to swim in lava") == true) {
			per = randomG.nextInt(10);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " ล�?�?�?ว�?าย�?�?ำ�?�?ลาวา (�?�?�?อะ�?ร�?ิด)";
				break;
			case 1:
				sx = player.getName()
						+ " �?ม�?เ�?ยเห�?�?ลาวา �?ึ�?�?ระ�?ดดล�?�?�?";
				break;
			case 2:
				sx = player.getName()
						+ " เลเวล 100 �?ล�?วลื�?�?ต�?ล�?�?�?อลาวา";
				break;
			case 3:
				sx = player.getName() + " �?ะทิ�?�?�?ยะ �?ด w �?า�?�?�?ห�?�?อย";
				break;
			case 4:
				sx = player.getName() + " ร�?อ�?�?ั�?เยย";
				break;
			case 5:
				sx = player.getName()
						+ " �?ะตั�?ลาวาทั�?�?ที เลย�?ระ�?ดดล�?�?�?�?�?ทะเลลาวา";
				break;
			case 6:
				sx = player.getName() + " ทดลอ�?ว�?าย�?�?ำ�?�?ลาวา";
				break;
			case 7:
				sx = player.getName() + " ต�?�?�?อลาวา เ�?ื�?อ�?�?ม�?�?�?วย";
				break;
			case 8:
				sx = player.getName() + " ทำเ�?�?รต�?ลาวา �?ระ�?ดดล�?�?�?�?ม";
				break;
			case 9:
				sx = player.getName()
						+ " ดื�?มยา�?ั�?�?�?�?ล�?วล�?ลาวา หาทา�?�?ึ�?�?�?ม�?เ�?อ 555";
				break;

			}

		}

		if (st.equalsIgnoreCase(player.getName() + " fell out of the world") == true) {
			per = randomG.nextInt(8);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " �?ุด�?ร�?เ�?ลิ�? ทำ bedrock �?ต�? ,เ�?ว�?�?�?ว�?า�?อ�?ะ...";
				break;
			case 1:
				sx = player.getName()
						+ " ลอ�?�?อ�?เอา�?�?�?�?ตี bedrock �?ม�?�?ิดว�?ามั�?�?ะ�?ต�? เ�?ิ�?เลย";
				break;
			case 2:
				sx = player.getName()
						+ " อยา�?รู�?ว�?าหลุมลึ�?�?�?�?�?ห�? �?ึ�?�?ระ�?ดดล�?�?�?";
				break;
			case 3:
				sx = player.getName() + " �?ด�?�?ร�?ระเ�?ิดอัด ลอยต�?�?ล�?";
				break;
			case 4:
				sx = player.getName()
						+ " สู�?�?วาวเวิ�?�?ว�?า�?อั�?�?�?ล�?�?�?�?...";
				break;
			case 5:
				sx = player.getName() + " สามารถล�?�?�?�?ต�?�?ล�?�?ด�?...";
				break;
			case 6:
				sx = player.getName()
						+ " �?ด�?ออ�?�?อ�?�?ล�?�?�?�?ล�?ว�?�?า�?�?�?�?";
				break;
			case 7:
				sx = player.getName() + " �?ด�?�?รณีสู�?....";
				break;

			}

		}

		if (st.equalsIgnoreCase(player.getName() + " was killed by magic") == true) {
			per = randomG.nextInt(6);
			switch (per) {
			case 0:
				sx = player.getName() + " ถู�?�?�?าตายด�?วยเวทยม�?ต�?";
				break;
			case 1:
				sx = player.getName()
						+ " เอา�?ม�?�?ายสิท�?ิ�?�?ี�?ตัวเอ�? �?ุย�?ั�?เ�?ื�?อ�?เรื�?อ�?�?าถา อวาดา เ�? ดา�? ลา";
				break;
			case 2:
				sx = player.getName()
						+ " magic เ�?�?�?�?ั�?�?�?า... 'what did you said admin?!'";
				break;
			case 3:
				sx = player.getName() + " ร�?าย�?ำสา�?�?ส�?ตัวเอ�?...";
				break;
			case 4:
				sx = player.getName()
						+ " �?ด�?�?ลั�?เวทย�?�?รา�?วิ�?�?า�?�?�?�?ล�?ว...";
				break;
			case 5:
				sx = player.getName()
						+ " ...เรีย�?เวทย�?ม�?ต�? �?ต�?�?ูด�?ม�?�?ัด ตายเลย ";
				break;
			}

		}

		if (st.equalsIgnoreCase(player.getName() + " drowned") == true) {
			per = randomG.nextInt(10);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " ออ�?ทะเลตาม�?วาม�?ั�? ลืมว�?าว�?าย�?�?ำ�?ม�?เ�?�?�?";
				break;
			case 1:
				sx = player.getName()
						+ " ว�?าย�?�?ำอยู�?ดี�?�?ุ�?ม spacebar �?ด�?ม�?�?ด�?";
				break;
			case 2:
				sx = player.getName() + " �?ม�?�?ำสู�?ห�?ึ�?�?�?ล�?อ�?ตาย";
				break;
			case 3:
				sx = player.getName()
						+ " เอา�?ิ�?ว�?�?�?�?�?�?�?�?�?ำตะ�?ริว�?ิ�?ตาย";
				break;
			case 4:
				sx = player.getName()
						+ " เรือดำ�?�?ำ�?ต�?�?ต�?ทะเลหมื�?�?�?ิ�?ล ว�?าย�?�?ำ�?ึ�?�?มา�?ม�?ทั�?...";
				break;
			case 5:
				sx = player.getName() + " สำลั�?�?�?ำหยดเดียวตาย";
				break;
			case 6:
				sx = player.getName()
						+ " �?อ�?หลั�?ระหว�?า�?สึ�?ามิมา �?ม�?�?ำตาย";
				break;
			case 7:
				sx = player.getName()
						+ " ลืม�?ิด�?�?ำ เล�?�?�?อมเ�?ลิ�? �?�?ำท�?วม�?�?า�?... _#!@#!@#";
				break;
			case 8:
				sx = player.getName()
						+ " �?ุดเหมือ�?�?ต�?ทะเล�?ห�?�?�?ำเทเ�?�?ามา";
				break;
			case 9:
				sx = player.getName() + " อา�?�?�?ำ�?ั�?�?ัวหาย�?�?�?ม�?ออ�?";
				break;
			}

		}

		if (st.equalsIgnoreCase(player.getName() + " was pricked to death") == true) {
			per = randomG.nextInt(8);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " �?ม�?เ�?ยเห�?�?�?ระ�?อ�?เ�?�?ร �?ระ�?ดด�?อดเต�?มเห�?ี�?ยว";
				break;
			case 1:
				sx = player.getName()
						+ " �?ะทิ�?�?�?ยะ ดั�?ต�?ล�?�?�?�?ด�?ต�?�?�?ระ�?อ�?เ�?�?ร";
				break;
			case 2:
				sx = player.getName()
						+ " เ�?ื�?อ�?ท�?า เอามือต�?ต�?�?�?ระ�?อ�?เ�?�?ร,�?�?รอั�?ทำตาม";
				break;
			case 3:
				sx = player.getName()
						+ " ทะเลทาย�?ห�?�?�?ล�?�? มี�?ระ�?อ�?เ�?�?รต�?�?เดียว ดั�?เดิ�?�?�?�?�?...";
				break;
			case 4:
				sx = player.getName() + " �?ด�?ห�?ามทิ�?มเ�?�?�?�?ั�?เลย";
				break;
			case 5:
				sx = player.getName()
						+ " ทำ�?ั�?ดั�?�?ระ�?อ�?เ�?�?ร เ�?ลอต�?ล�?�?�?";
				break;
			case 6:
				sx = player.getName()
						+ " ตัดต�?�?�?ระ�?อ�?เ�?�?ร �?ระ�?อ�?เ�?�?รทั�?เท�?า เ�?�?�?�?�?ตาย";
				break;
			case 7:
				sx = player.getName() + " เอาหมอ�?�?ระ�?อ�?เ�?�?รมา�?อ�?ห�?ุ�?";
				break;

			}

		}

		if (st.startsWith(player.getName() + " was slain by ") == true) {

			per = randomG.nextInt(8);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " รวยเ�?ิ�?ห�?�?าเ�?ิ�?ตา �?ด�?เ�?ื�?อ�?�?ล�?�?";
				break;
			case 1:
				sx = player.getName()
						+ " หั�?หลั�?�?ห�?เ�?ื�?อ�? �?ด�?เ�?ื�?อ�?เอาดา�?�?ท�?";
				break;
			case 2:
				sx = player.getName()
						+ " เล�?�?�?ั�?ดา�?�?ม�?�?ั�?เ�?ื�?อ�? ,�?ต�?เ�?ื�?อ�?�?�?�?ดา�?เ�?�?ร...";
				break;
			case 3:
				sx = player.getName()
						+ " เรีย�?เ�?ื�?อ�?มา�?ั�?�?ิ�?�?�?าว,เ�?ื�?อ�?เดิ�?มา สะดุดล�?ม ดา�?�?ั�?�?ลา�?หัว�?�?..";
				break;
			case 4:
				sx = player.getName()
						+ " �?ม�?อยู�?�?�?�?�?เดียว �?ด�?เ�?ื�?อ�?�?�?ล�?�?";
				break;
			case 5:
				sx = player.getName() + " ถู�?�?�?า�?ดยเ�?ื�?อ�?�?ี�?";
				break;
			case 6:
				sx = player.getName()
						+ " เ�?ายิ�?�?ุ�?�?ั�?เ�?ื�?อ�?�?�?�? เ�?ื�?อ�?�?อ�?�?ห�?�?�?ตาย ... เ�?ื�?อเ�?ื�?อ�?";
				break;
			case 7:
				sx = player.getName() + " �?ด�?เ�?ื�?อ�?หั�?หลั�?";
				break;

			}

		}

		if (st.equalsIgnoreCase(player.getName() + " was slain by zombie") == true) {

			per = randomG.nextInt(8);
			switch (per) {
			case 0:
				sx = player.getName() + " �?ด�?�?ี�?อด�?อเ�?ิ�?ตาย";
				break;
			case 1:
				sx = player.getName() + " �?ด�?อาเ�?ะตุ�?ยตรอม�?�?ตาย";
				break;
			case 2:
				sx = player.getName()
						+ " �?ุด�?ร�?อยู�?�?ึ�?ว�?าเ�?ื�?อ�? เดิ�?เ�?�?า�?�?ทั�?,�?ด�?อาเ�?ะ�?ัดหูตาย";
				break;
			case 3:
				sx = player.getName()
						+ " เห�?�?�?ียื�?�?�?�?�? �?ึ�?ว�?ามั�?เรีย�? , �?ึ�?เดิ�?เ�?�?า�?�?หา";
				break;
			case 4:
				sx = player.getName() + " อยา�?เ�?�?�?�?ี �?ห�?�?ี�?ัด";
				break;
			case 5:
				sx = player.getName() + " �?ด�?�?ี�?ัด หายารั�?ษา�?ม�?ทั�?";
				break;
			case 6:
				sx = player.getName() + " �?ด�?�?ีต�?";
				break;
			case 7:
				sx = player.getName() + " เ�?อ�?ี �?ัด เลย�?ัด�?ี";
				break;

			}

		}
		if (st.equalsIgnoreCase(player.getName() + " was slain by magma cube") == true) {

			per = randomG.nextInt(4);
			switch (per) {
			case 0:
				sx = player.getName() + " �?ด�?ตัวเด�?�?ดิ�?�?�?�?าตาย";
				break;
			case 1:
				sx = player.getName()
						+ " สู�? magma cube �?ม�?�?ด�?,มั�?�?�?ารั�?เ�?ิ�?";
				break;
			case 2:
				sx = player.getName()
						+ " �?�?า magma cube ,เห�?�?ลู�?มั�?ออ�?มา �?�?ามั�?�?ม�?ล�?";
				break;
			case 3:
				sx = player.getName()
						+ " �?ด�? magma cube รุ�?ม�?ระทื�?,ดั�?เ�?�?�?อริ�?ั�? slime";
				break;
			}
		}
		if (st.equalsIgnoreCase(player.getName() + " was slain by skeleton") == true) {

			per = randomG.nextInt(4);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " ท�?า�?ี�?ระดู�?�?ห�?ยิ�?�?อ�?เ�?ิ�?ล�?�?หัว �?ต�?มั�?ยิ�?�?ลาด เ�?ิ�?";
				break;
			case 1:
				sx = player.getName()
						+ " ทำเ�?�?�?เ�?�?�?วิ�?�?หล�?�?�?ู�?า�? skeleton ลืม�?�?ว�?า มั�?มาเ�?�?�?�?ู�?";
				break;
			case 2:
				sx = player.getName() + " �?ด�?�?�?ู�?ั�?หัวเ�?�?าตาย";
				break;
			case 3:
				sx = player.getName()
						+ " ยิ�?�?�?ู�?ม�?เ�?�?�?�?ห�? skeleton สอ�?";
				break;
			}
		}

		if (st.equalsIgnoreCase(player.getName() + " blew up") == true) {
			per = randomG.nextInt(4);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " �?ุย�?ั�? creeper อยู�?ดี�?,เล�?าเรื�?อ�?ต�?�?�?�?ห�?มั�?�?ั�?...";
				break;
			case 1:
				sx = player.getName()
						+ " �?ึ�?ว�?าระเ�?ิดเ�?ิ�?�?ี�?�?ม�?�?ร�?, �?ล�?อ�?�?ต�?..555";
				break;
			case 2:
				sx = player.getName()
						+ " �?ุด�?ร�?อยู�?ดี�?�?ด�?ยิ�?เสีย�? �?ี�?�?�? �?ล�?วทุ�?อย�?า�?�?�?ดั�?�?�?...";
				break;
			case 3:
				sx = player.getName()
						+ " �?ุดระเ�?ิด�?ล�?วยื�?�?ุย�?ั�?เ�?ื�?อ�? หั�?มาอี�?ที...";
				break;
			}

		}
		if (st.equalsIgnoreCase(player.getName() + " was slain by spider") == true) {

			per = randomG.nextInt(4);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " อยา�?รู�?ว�?า�?ม�?มุมมี�?ั�?�?ี�?�?ี�?�?ึ�?�?�?ดู�?�?ล�?�?";
				break;
			case 1:
				sx = player.getName() + " �?ยายาม�?ี�?�?ม�?มุม, �?ต�?มั�?�?ยศ";
				break;
			case 2:
				sx = player.getName()
						+ " หิวมา�?เ�?ิ�? ลอ�?�?ิ�?ลู�?ตา�?ม�?มุม";
				break;
			case 3:
				sx = player.getName()
						+ " เ�?อ�?ร�?ส�?าว�?ม�?มุม เดิ�?เ�?�?า�?�?ดู.. �?ลาดติด�?ย�?ม�?มุม...";
				break;
			}
		}
		if (st.equalsIgnoreCase(player.getName() + " was slain by cave spider") == true) {

			per = randomG.nextInt(4);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " �?ึ�?ว�?า redstone �?ึ�?เอา�?อ�?�?�?�?ุด�?ต�?�?ด�?มั�?เ�?มือ�?";
				break;
			case 1:
				sx = player.getName()
						+ " ดู�?าร�?มา�?�?�?ห�?�?อย,�?ึ�?เ�?�?า�?�?�?อลายเ�?�?�?ต�?�?ั�? spiderman";
				break;
			case 2:
				sx = player.getName()
						+ " รีด�?ิษ�?ม�?มุม�?ว�? , �?ะดื�?ม�?�?ำ หยิ�?�?ิดถ�?วย";
				break;
			case 3:
				sx = player.getName()
						+ " เห�?�? cave_spider ตัวเล�?�?�?ึ�?�?ระมาท�?�?ห�?�?อย";
				break;
			}
		}
		if (st.equalsIgnoreCase(player.getName() + " was slain by enderman") == true) {

			per = randomG.nextInt(4);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " �?ด�? enderman �?�?มย�?ล�?อ�? เอา�?ต�?ร�?อ�?�?ห�? มั�?เดิ�?มาต�?...";
				break;
			case 1:
				sx = player.getName()
						+ " เห�?�?�?า�? enderman อ�?า�?ว�?า�?�?ึ�?ว�?ามั�?หิว ,�?�?ดีเดิ�?�?�?หามั�?";
				break;
			case 2:
				sx = player.getName()
						+ " �?ั�? enderman �?�?ที , เห�?�?มั�?หาย�?�?�?ะล�?�?า�?�?เ�?�?�?ดา�?เ�?�?า�?ั�?, fuc----!";
				break;
			case 3:
				sx = player.getName()
						+ " �?ห�? enderman �?ี�?�?อ�?ด�?มั�?�?ัดหัว";
				break;
			}
		}
		if (st.equalsIgnoreCase(player.getName() + " was slain by slime") == true) {

			per = randomG.nextInt(4);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " �?ด�? slime ทั�?ตาย�?ึ�?ว�?ามั�?ตัวเ�?า";
				break;
			case 1:
				sx = player.getName()
						+ " �?ั�? slime ตายตัวห�?ึ�?�?มั�?ออ�?มา 4.. ";
				break;
			case 2:
				sx = player.getName()
						+ " �?ห�? slime �?ี�?�?อ �?ะ�?า�?ลั�?�?�?า�?,หาย�?�?�?ม�?ออ�?";
				break;
			case 3:
				sx = player.getName()
						+ " ห�?ี slime มา�?�?ล �?�?�?�?ทรมา �?ุยเ�?ลิ�?, มั�?ตามมา�?�?าทั�?";
				break;
			}
		}
		if (st.equalsIgnoreCase(player.getName() + " was fireballed by blaze") == true) {

			per = randomG.nextInt(4);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " รั�?ลู�?�?�?�?า�? blaze �?ด�?ทุ�?ลู�?,อาเม�?...";
				break;
			case 1:
				sx = player.getName()
						+ " �?ยายามตีลู�?ระเ�?ิด �?า�? blaze �?ห�?�?ด�? �?ต�?มั�?�?ลาด...";
				break;
			case 2:
				sx = player.getName()
						+ " �?ด�?�?ร�?ระเ�?ิด�?า�?ลู�?�?�?�?อ�? blaze ตาย";
				break;
			case 3:
				sx = player.getName()
						+ " �?ี�?�?อ blaze มั�?�?ม�?ยอม�?�? รู�?ตัวอี�?ที�?า�?เ�?�?�?หม�?";
				break;
			}
		}
		if (st.equalsIgnoreCase(player.getName() + " went up in flames") == true) {

			per = randomG.nextInt(4);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " �?�?�?หม�?�?ม�?�?ลัว.. �?ต�?ลืมเอาถั�?�?�?ำมา";
				break;
			case 1:
				sx = player.getName()
						+ " �?ุด�?ร�? �?�?วดิ�?�? ต�?ลาวาตาย,เลิ�?เล�?�?เ�?ิ�?�?ี�? .. อ�?าว";
				break;
			case 2:
				sx = player.getName()
						+ " ตอ�?เ�?�?�?เวล�?ด�?ยา�?ั�?�?�?,วิ�?�?ลุย�?�?�?�?ว�?เ�?ื�?อ�? ลืม�?�?ว�?ามั�?�?ั�?�?ด�?�?�?�? 10 วิ";
				break;
			case 3:
				sx = player.getName()
						+ " �?ุด�?�?ที�? netherack �?ว�?�?�?�?�?า�?,�?ม�?เรีย�?�?ลั�?มาอี�?ที ... ดำเ�?�?�?ตอตะ�?�?";
				break;
			}
		}
		if (st.equalsIgnoreCase(player.getName() + " was killed by witch") == true) {

			per = randomG.nextInt(4);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " �?อยา�?า�?�?ม�?มดดื�?ม �?ต�?ลืมถามว�?ายาอะ�?ร";
				break;
			case 1:
				sx = player.getName()
						+ " วิ�?�?ล�?อ�?ม�?มด �?ด�?มั�?�?า ยาเ�?ื�?อ�?�?�?า�?ส�?,ร�?อ�?�?ห�?หา...เ�?อหรือ";
				break;
			case 2:
				sx = player.getName()
						+ " เ�?�?า�?�?�?ิด�?ิดว�?าเ�?�?�? npc เดิ�?�?�?�?ะ�?ล�?�?อ�?,�?ด�?�?ั�?�?ู�?ายั�?";
				break;
			case 3:
				sx = player.getName()
						+ " �?ด�?ยา�?าดเ�?�?�?�?ั�?�?ลั�?�?อ�?�?ม�?มด,�?ิม�?�? /home �?ม�?ทั�?,ตาย, �?ึ�?�?ิม�?�?�?ำว�?�?า fu_k";
				break;
			}
		}
		if (st.equalsIgnoreCase(player.getName() + " was slain by giant") == true) {

			per = randomG.nextInt(4);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " วิ�?�?เล�?�?�?ล�?�?ั�?�?ั�?ยั�?ษ�?  �?ต�?�?าสั�?�?�?ด�?เหยีย�?�?�?�?";
				break;
			case 1:
				sx = player.getName()
						+ " �?ม�?เ�?ยเห�?�?อาเ�?ะยั�?ษ�? เ�?�?า�?�?สะ�?ิดมั�?,�?ด�?มั�?ต�?";
				break;
			case 2:
				sx = player.getName()
						+ " เอา�?ุ�?ย�?ห�?�?ี �?ล�?วมั�?ตัว�?ต(ล�?อเล�?�?),�?ต�?�?�?ด�?วย�?ารตายเหมือ�?�?ั�?";
				break;
			case 3:
				sx = player.getName()
						+ " �?ด�?เ�?ื�?อ�?�?ูเ�?�?อะ รั�?�?�? (sorry)";
				break;
			}
		}

		if (st.equalsIgnoreCase(player.getName() + " was slain by wolf") == true) {

			per = randomG.nextInt(4);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " �?ม�?หเตะหมา �?ต�?ลืมดูมั�?มาเ�?�?�?�?ู�?...";
				break;
			case 1:
				sx = player.getName()
						+ " �?�?�?�?า�?เ�?ื�?อ�?ลืมเรา�?ระดู�?มา,�?ด�?หมาลา�?�?�?�?ิ�?";
				break;
			case 2:
				sx = player.getName()
						+ " ล�?ามอ�?อยู�?ดี�? �?ลาด�?�?ตี�?ด�?หมาตัวเอ�?,วิ�?�?�?ั�?�?ม�?ทั�?เลยทีเดียว";
				break;
			case 3:
				sx = player.getName()
						+ " เห�?�?หมาตา�?ด�?�?เ�?�?า�?�?ดูด�?วย�?วามเ�?�?�?ห�?ว�?,�?ด�?หมา�?ัด";
				break;
			}
		}

		if (st.equalsIgnoreCase(player.getName()
				+ " was slain by zombie pigman") == true) {

			per = randomG.nextInt(4);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " หิว�?�?ตาลายเห�?�?หมูยื�?สอ�?�?า�?ิดว�?า�?ะอร�?อย �?ึ�?�?ั�?มั�?�?�?ทีห�?ึ�?�?,�?ด�?�?ั�?�?ลั�?�?ั�?�?�?ล�?ม�?ถ�?ว�?";
				break;
			case 1:
				sx = player.getName()
						+ " อยา�?�?ด�?ดา�?�?า�? �?อม�?ี�?หมู,เมาส�?�?�?า�?...";
				break;
			case 2:
				sx = player.getName()
						+ " เดิ�?เ�?�?า�?�?�?�?�?ู�?�?อ�? pig zombie,เ�?ื�?อ�?มือ�?หม�?ลอ�?�?�?ตีมั�?ดู";
				break;
			case 3:
				sx = player.getName()
						+ " เลี�?ย�?หมูอยู�?ดี�? มั�?เดิ�?�?�?า�?�?ระตู nether";
				break;
			}
		}
		if (st.equalsIgnoreCase(player.getName() + " died") == true) {

			per = randomG.nextInt(4);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " หมด�?ำลั�?�?�?เล�?�?มาย�?รา�? ตรอม�?�?ตาย";
				break;
			case 1:
				sx = player.getName() + " ดื�?มยา�?ิด hp หมด";
				break;
			case 2:
				sx = player.getName() + " หิว�?�?ตัวเอ�?ตาย";
				break;
			case 3:
				sx = player.getName() + " �?ิดถึ�?ห�?�?า creeper ต�?�?�?ตาย";
				break;
			}
		}

		if (st.startsWith(player.getName() + " was shot by ") == true) {

			per = randomG.nextInt(4);
			switch (per) {
			case 0:
				sx = player.getName()
						+ " เ�?ื�?อ�?�?ะยิ�?�?�?ู�?ส�?�?ี ดั�?เอาตัว�?�?�?วา�?";
				break;
			case 1:
				sx = player.getName()
						+ " เ�?ื�?อ�?สอ�?ยิ�?�?�?ู ดั�?ถือ�?ิดด�?า�?";
				break;
			case 2:
				sx = player.getName()
						+ " ยิ�?�?�?ู�?�?�?�?�?ั�?เ�?ื�?อ�? ดั�?ยื�?�?ิด�?ั�?�?";
				break;
			case 3:
				sx = player.getName()
						+ " ยิ�?�?�?ู�?ึ�?�?�?�?าวั�?�?ี�?หม�? มาวั�?�?ี�?มั�?ต�?ล�?มา�?ด�?หัวตาย";
				break;
			}
		}

		return sx;
	}

	// getfreeselect

	// fixtool itemstack and player
	// getfreeselect
	public int getfreeselect(Player player) {
		int lp1 = 0;

		boolean foundza = false;
		// clear select array
		for (lp1 = 0; lp1 < selectmax; lp1++) {

			if (selectname[lp1] == null
					|| selectname[lp1].equalsIgnoreCase("") == true) {
				selectname[lp1] = "null";
				selectx1[lp1] = 0;
				selecty1[lp1] = 0;
				selectz1[lp1] = 0;
				selectx2[lp1] = 0;
				selecty2[lp1] = 0;
				selectz2[lp1] = 0;
				dewaxe[lp1] = true;
				hidemode[lp1] = false;
				d4[lp1] = 1;
				chatever[lp1] = false;
			}
		} // loop allselect

		// clear ofline player
		for (lp1 = 0; lp1 < selectmax; lp1++) {
			foundza = false;
			// loop player for clear
			for (Player pla : Bukkit.getOnlinePlayers()) {
				if (pla.getName().equalsIgnoreCase(selectname[lp1]) == true) {
					foundza = true;
					break;
					// found
				}

			} // loop all player

			if (foundza == false) { // clear
				selectname[lp1] = "null";
				selectx1[lp1] = 0;
				selecty1[lp1] = 0;
				selectz1[lp1] = 0;
				selectx2[lp1] = 0;
				selecty2[lp1] = 0;
				selectz2[lp1] = 0;
				hidemode[lp1] = false;
				dewaxe[lp1] = true;
				d4[lp1] = 1;
				chatever[lp1] = false;
			}

		}

		// if fonund return
		for (lp1 = 0; lp1 < selectmax; lp1++) {
			if (player.getName().equalsIgnoreCase(selectname[lp1]) == true) {
				return lp1;
			}
		}

		// if not found
		for (lp1 = 0; lp1 < selectmax; lp1++) {
			if (selectname[lp1].equalsIgnoreCase("null") == true) {
				selectname[lp1] = player.getName();
				return lp1;
			}
		}

		printC("ptdew&dewdd: GetFreeselect return -1");
		return -1;
	}

	// getselectblock // for dewset or dewfill or dewdelete
	public Block[] getselectblock(int getid, Player player) {

		int adderB = -1;
		int countblock = ((2 + Math.abs(selectx1[getid] - selectx2[getid]))
				* (2 + Math.abs(selecty1[getid] - selecty2[getid])) * (2 + Math
				.abs(selectz1[getid] - selectz2[getid])));

		if (countblock > 160000 && isadminname(player.getName()) == false) {
			player.sendMessage("ห�?าม�?ู�?เล�?�?�?รรมดา�?�?�? �?ำสั�?�?ดอ�?�?ม�?  มี�?ำ�?ว�?�?ล�?อ�?มา�?�?ว�?า 16 หมื�?�?�?ล�?อ�?");
			Block blocktemp[] = null;

			return blocktemp;
		}

		player.sendMessage("countblock = " + countblock);

		int lx = 0;
		int mx = 0;
		int ly = 0;
		int my = 0;
		int lz = 0;
		int mz = 0;

		if (selectx1[getid] >= selectx2[getid]) {
			mx = selectx1[getid];
			lx = selectx2[getid];
		}
		else {
			mx = selectx2[getid];
			lx = selectx1[getid];
		}

		if (selecty1[getid] >= selecty2[getid]) {
			my = selecty1[getid];
			ly = selecty2[getid];
		}
		else {
			my = selecty2[getid];
			ly = selecty1[getid];
		}

		if (selectz1[getid] >= selectz2[getid]) {
			mz = selectz1[getid];
			lz = selectz2[getid];
		}
		else {
			mz = selectz2[getid];
			lz = selectz1[getid];
		}

		Block blocktemp[] = new Block[countblock];
		// > > >

		for (int xl = lx; xl <= mx; xl++) {
			for (int yl = ly; yl <= my; yl++) {
				for (int zl = lz; zl <= mz; zl++) {
					Block blb = player.getWorld().getBlockAt(xl, yl, zl);
					if (blb == null) {
						continue;
					}

					if (isprotectitemid(blb.getTypeId()) == true) {
						continue;
					}

					adderB++;
					blocktemp[adderB] = blb;
				}
			}
		}

		Block blockmain[] = new Block[adderB + 1];
		int adderc = 0;

		for (adderc = 0; adderc <= adderB; adderc++) {
			blockmain[adderc] = blocktemp[adderc];
		}

		for (@SuppressWarnings("unused")
		Object obj : blocktemp) {
			obj = null;
		}
		blocktemp = null;

		return blockmain;

	} // getselectblock

	// getselectblock // for dewbuy check wa mee kee block
	public int getselectblockforbuy(int getid, Player player) {

		int countall = 0;

		int lx = 0;
		int mx = 0;
		int ly = 0;
		int my = 0;
		int lz = 0;
		int mz = 0;

		if (selectx1[getid] >= selectx2[getid]) {
			mx = selectx1[getid];
			lx = selectx2[getid];
		}
		else {
			mx = selectx2[getid];
			lx = selectx1[getid];
		}

		if (selecty1[getid] >= selecty2[getid]) {
			my = selecty1[getid];
			ly = selecty2[getid];
		}
		else {
			my = selecty2[getid];
			ly = selecty1[getid];
		}

		if (selectz1[getid] >= selectz2[getid]) {
			mz = selectz1[getid];
			lz = selectz2[getid];
		}
		else {
			mz = selectz2[getid];
			lz = selectz1[getid];
		}

		for (int xl = lx; xl <= mx; xl++) {
			for (int yl = ly; yl <= my; yl++) {
				for (int zl = lz; zl <= mz; zl++) {
					Block blb = player.getWorld().getBlockAt(xl, yl, zl);
					if (checkpermissionarea(blb, player, "buy") == true) {
						countall = -1;
						return countall;
					}
					else {
						countall++;
					}

				}
			}
		}

		return countall;

	} // getselectblock

	public int getwallid() {
		int g = 0;
		Random rn = new Random();

		while (g <= 0 || blockdewset(g) == false) {
			g = rn.nextInt(200);
		}

		return g;
	}

	public int getworldid(String wowo) {
		for (int d = 0; d <= dewworldlistmax; d++) {
			if (wowo.equalsIgnoreCase(dewworldlist[d]) == true) {
				return d;
			}
		}

		return -1;
	}

	public String getworldname(int idworld) {
		String aa = "ptdew_dewdd_" + dewworldlist[idworld] + ".txt";
		return aa;
	}

	public void gotohell(Player player, Location lo1, Location lo2) {
		if (player.getName().equalsIgnoreCase("xxxx") == false) {
			return;
		}
		// copy to hell
		int dx = 15;
		Block blo = null;
		Block blo2 = null;

		for (int d = 1; d <= 2; d++) {
			for (int x = (0 - dx); x <= (0 + dx); x++) {
				for (int z = (0 - dx); z <= (0 + dx); z++) {
					for (int y = (0 - dx); y <= (0 + dx); y++) {
						blo = Bukkit.getWorld("world").getBlockAt(lo1)
								.getRelative(x, y, z);
						if (d == 1) {
							if (isneednearblock(blo.getTypeId()) == true) {
								continue;
							}
						}
						else if (d == 2) {
							if (isneednearblock(blo.getTypeId()) == false) {
								continue;
							}
						}
						blo2 = Bukkit.getWorld("world_nether").getBlockAt(lo2)
								.getRelative(x, y, z);

						switch (blo.getTypeId()) {
						case 2:
							blo2.setTypeId(88);
							break;
						case 3:
							blo2.setTypeId(87);
							break;
						case 8:
						case 9:
						case 10:
						case 11:
							blo2.setTypeId(20);
							break;
						case 41:
						case 42:
						case 57:
						case 56:
						case 133:
							blo2.setTypeId(46);
							break;
						default:
							blo2.setTypeId(blo.getTypeId());
						}
						blo2.setData(blo.getData());
					}
				}
			}

		}

		if (player.getInventory().getHelmet() != null) {
			Bukkit.getWorld("world_nether").dropItem(lo2,
					player.getInventory().getHelmet());
			player.getInventory().getHelmet().setTypeId(0);
		}

		ItemStack itm = new ItemStack(397, 1);
		player.getInventory().setHelmet(itm.getData().toItemStack());
		player.teleport(lo2);
		player.getInventory().remove(7);
		// dew.printA("ptdew&dewdd: !@#!@# ????");
		printC("ptdew&dewdd: go to hell '" + player.getName() + "'");
	}

	public void harvestblockgoldsugarcane(Block block, Player player) {
		harvestblockgoldsugarcanec ab = new harvestblockgoldsugarcanec();
		ab.block = block;
		ab.player = player;

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ac, ab);
	}

	// riceblockiron

	// goldpumpkin
	public void harvestgoldpumpkin(Block block, Player player) {

		harvestgoldpumpkinc ab = new harvestgoldpumpkinc();
		ab.block = block;
		ab.player = player;

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ac, ab);

	}

	// riceblockiron
	public void harvestriceblockiron(Block block, Player player) {
		harvestriceblockironc ab = new harvestriceblockironc();
		ab.block = block;
		ab.player = player;

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ac, ab);
	}

	// sandmelon

	// sandmelon
	public void harvestsandmelon(Block block, Player player) {
		harvestsandmelonc ab = new harvestsandmelonc();
		ab.block = block;
		ab.player = player;

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ac, ab);
	}

	// Check Permission Area block
	// checkidhome
	public boolean havethisnameinthishome(int worldid, int homeid, String namee) {
		if (homeid < 0 || homeid > (dewsignmax[worldid] - 1)) {
			return false;
		}
		for (int cheL = 0; cheL < dewsignnamemax; cheL++) {
			if (dewsignname[worldid][homeid][cheL].equalsIgnoreCase(namee) == true) {
				return true;
			}
		}
		return false;
	}

	// ironorefreeze

	public void hideshowrun(Player player) {
		// player.sendMessage("hide show run = " + player.getName());

		Player ptdewv = null;
		int gga = -1;
		for (Player pla : Bukkit.getOnlinePlayers()) {

			ptdewv = null;
			if (isadminname(pla.getName()) == true
					|| isspyname(pla.getName()) == true) { // if admin name

				ptdewv = pla;
				// printC("found fir'" + pla.getName() + "' ");
				// player.sendMessage("found fir '" + pla.getName() + "' ");

			}

			if (ptdewv != null) { // if found
				// printC("found '" + ptdewv.getName() + "' ");
				// player.sendMessage("found '" + ptdewv.getName() + "' ");

				for (Player pla2 : Bukkit.getOnlinePlayers()) {
					if (pla2.getName().equalsIgnoreCase(pla.getName()) == true) {
						continue;
					}

					// admin can see everyone
					if (isadminname(ptdewv.getName()) == true) {
						ptdewv.showPlayer(pla2); // make admin can see everyone
					}

					// spy can see admin or not
					if (isspyname(ptdewv.getName()) == true
							&& isadminname(pla2.getName()) == true) {
						gga = getfreeselect(pla2);

						if (hidemode[gga] == true) {
							ptdewv.hidePlayer(pla2); // make spy can't see admin
						}
						else {
							ptdewv.showPlayer(pla2); // make
						}
					}
					// ptdewv is not admin not spy
					if (isspyname(pla2.getName()) == false
							&& isadminname(pla2.getName()) == false) {
						gga = getfreeselect(ptdewv);
						if (hidemode[gga] == true) {
							pla2.hidePlayer(ptdewv); // make member can't see
														// admin
						}
						else {
							pla2.showPlayer(ptdewv); // make
						}
					}
					// admin or not

				}
			}

		}

		// ***********

	}

	// ironorefreeze
	public void ironorefreeze(Block block, Player player)
			throws UserDoesNotExistException, NoLoanPermittedException {
		int digX = block.getX();
		int digY = block.getY();
		int digZ = block.getZ();

		int ex = digX;
		int ey = digY;
		int ez = digZ;

		World world = block.getWorld();
		Block blockToChange = block;
		int d5 = d4[getfreeselect(player)];

		for (ex = digX - d5; ex <= digX + d5; ex++) {
			for (ey = digY - d5; ey <= digY + d5; ey++) {
				for (ez = digZ - d5; ez <= digZ + d5; ez++) {
					blockToChange = world.getBlockAt(ex, ey, ez);
					if (checkpermissionarea(blockToChange) == true) {
						continue;
					}
					if (blockToChange.getTypeId() == 8
							|| blockToChange.getTypeId() == 9) {

						world.getBlockAt(ex, ey, ez).setTypeId(0);
						blockToChange.setTypeId(9);
						world.getBlockAt(ex, ey, ez).setTypeId(9);

					}
				}
			}
		}
		player.sendMessage("IronoreFreezed Water");
	}

	public boolean isneednearblock(int impo) {

		switch (impo) {
		case 6:
		case 26:
		case 27:
		case 28:
		case 31:
		case 32:
		case 37:
		case 38:
		case 39:
		case 40:
		case 50:
		case 51:
		case 55:
		case 59:
		case 63:
		case 64:
		case 65:
		case 66:
		case 68:
		case 69:
		case 70:
		case 71:
		case 72:
		case 75:
		case 76:
		case 78:
		case 83:
		case 93:
		case 94:
		case 96:
		case 104:
		case 105:
		case 111:
		case 127:
		case 131:
		case 132:
		case 140:
		case 141:
		case 142:
		case 144:
		case 147:
		case 148:
		case 149:
		case 150:
		case 151:
		case 157:

			return true;
		default:
			return false;
		}
	}

	public boolean isprotectitemid(int importid) {
		switch (importid) {
		case 54: // chest
		case 19: // sponge
		case 23: // dispenser
		case 61: // fu
		case 62: // fu
		case 63: // sign post
		case 68: // wall sign
		case 95: // lock chest
		case 145: // anvil
		case 138: // beacon
		case 130: // enderchest
		case 116: // enchant
		case 52: // monster spawner
		case 117: // brewing
		case 154: // hopper

			return true;
		default:
			return false;
		}
	}

	public boolean isunsortid(ItemStack impo) {
		if (impo.getType().getMaxDurability() > 0) {
			return true;
		}

		switch (impo.getTypeId()) {
		case 403: // enchant book
		case 373: // potion
		case 401: // rocket
		case 402: // firework star
		case 397: // mod head
		case 395: // empty map
		case 144: // head
		case 387: // writting book
		case 386: // book and quill
		case 351:// ink

			return true;
		default:
			return false;
		}
	}

	// obsidianabsorb

	public void item55delete(Block block, Player player, int id, int dat)
			throws UserDoesNotExistException, NoLoanPermittedException {
		item55deletec ab = new item55deletec();
		ab.block = block;
		ab.player = player;
		ab.id = id;
		ab.dat = dat;

		amount3 = 0;
		amount2 = 0;

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ac, ab);
	}

	// boolean firstrun19 = false;

	public void linkurl(Player player, String url) {
		if (url.endsWith("?fb") == true || url.endsWith("?facebook") == true) {
			printA("ptdew&dewdd: my facebook > https://www.facebook.com/dewddminecraft");

		}

		if (url.endsWith("?e-mail") == true || url.endsWith("?mail") == true) {
			printA("ptdew&dewdd: my e-mail > dew_patipat@hotmail.com");
		}

		if (url.endsWith("?youtube") == true || url.endsWith("?video") == true) {
			printA("ptdew&dewdd: my youtube > http://www.youtube.com/ptdew");
			printA("ptdew&dewdd: my youtube 2 > http://www.youtube.com/ptdew2");
		}

		if (url.endsWith("?plugin") == true || url.endsWith("?pl") == true) {
			printA("ptdew&dewdd: my plugin > http://www.youtube.com/playlist?list=PLlM9Jjda8OZeMEuUtVxyXu2XF62rqzt2j");
		}

	}

	public void loaddeadpositionfile() {
		String deadf = "ptdew_dewdd_deadposition.txt";
		try {

			System.out.println("ptdeW&DewDD Main : Starting Loading " + deadf);
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(deadf);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			strLine = br.readLine();
			deadpositionx = Integer.parseInt(strLine);

			strLine = br.readLine();
			deadpositiony = Integer.parseInt(strLine);

			strLine = br.readLine();
			deadpositionz = Integer.parseInt(strLine);

			System.out.println("ptdew&DewDD Main: Loaded " + deadf);

			in.close();
		}
		catch (Exception e) {// Catch exception if any
			System.err.println("Error : while loading " + deadf
					+ e.getMessage());
		}
	}

	public void loadmainfile() {
		loadworldfile();
		loadsignfile();
		loaddeadpositionfile();
	}

	// loadsignfile
	public void loadsignfile() {

		dewsignx1 = new int[dewworldlistmax + 1][dewsignlimit];
		dewsigny1 = new int[dewworldlistmax + 1][dewsignlimit];
		dewsignz1 = new int[dewworldlistmax + 1][dewsignlimit];

		dewsignx2 = new int[dewworldlistmax + 1][dewsignlimit];
		dewsigny2 = new int[dewworldlistmax + 1][dewsignlimit];
		dewsignz2 = new int[dewworldlistmax + 1][dewsignlimit];

		dewsignloop = new int[dewworldlistmax + 1][dewsignlimit];
		dewsignname = new String[dewworldlistmax + 1][dewsignlimit][dewsignnamemax];

		dewsignmax = new int[dewworldlistmax + 1];
		for (int cx = 0; cx < this.dewworldlistmax + 1; cx++) {
			dewsignmax[cx] = -1;
		}

		int wlo = 0;
		for (wlo = 0; wlo < dewworldlistmax + 1; wlo++) {

			// ****************************
			try { // try

				int dewsignnow = 0;

				// Open the file that is the first
				// command line parameter
				String filena = getworldname(wlo);

				printC("ptdew&dewdd: Starting Loading " + filena);

				FileInputStream fstream = new FileInputStream(filena);
				// Get the object of DataInputStream
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
				String strLine;
				// Read File Line By Line
				int moden = 0;

				while ((strLine = br.readLine()) != null) {
					// Print the content on the console

					switch (moden) {
					case 0: // dewsignmax
						dewsignmax[wlo] = Integer.parseInt(strLine);

						// printC("Dewsignmax = " + dewsignmax);

						/*
						 * dewsignx1 = new int[dewsignmax];
						 * dewsigny1 = new int[dewsignmax];
						 * dewsignz1 = new int[dewsignmax];
						 * 
						 * dewsignx2 = new int[dewsignmax];
						 * dewsigny2 = new int[dewsignmax];
						 * dewsignz2 = new int[dewsignmax];
						 * 
						 * dewsignloop = new int[dewsignmax];
						 * dewsignname = new String[dewsignmax][dewsignnamemax];
						 */

						dewsignnow = 0;

						break;

					case 1: // name1
						dewsignname[wlo][dewsignnow][0] = strLine;
						break;
					case 2: // name2
						dewsignname[wlo][dewsignnow][1] = strLine;
						break;
					case 3: // name3
						dewsignname[wlo][dewsignnow][2] = strLine;
						break;
					case 4: // name4
						dewsignname[wlo][dewsignnow][3] = strLine;
						break;
					case 5: // name5
						dewsignname[wlo][dewsignnow][4] = strLine;
						break;
					case 6: // name6
						dewsignname[wlo][dewsignnow][5] = strLine;
						break;
					case 7: // name7
						dewsignname[wlo][dewsignnow][6] = strLine;
						break;
					case 8: // name8
						dewsignname[wlo][dewsignnow][7] = strLine;
						break;
					case 9: // name9
						dewsignname[wlo][dewsignnow][8] = strLine;
						break;
					case 10: // name10
						dewsignname[wlo][dewsignnow][9] = strLine;
						break;
					case 11: // name11
						dewsignname[wlo][dewsignnow][10] = strLine;
						break;
					case 12: // name12
						dewsignname[wlo][dewsignnow][11] = strLine;
						break;
					case 13: // name13
						dewsignname[wlo][dewsignnow][12] = strLine;
						break;
					case 14: // name14
						dewsignname[wlo][dewsignnow][13] = strLine;
						break;
					case 15: // name15
						dewsignname[wlo][dewsignnow][14] = strLine;
						break;
					case 16: // name16
						dewsignname[wlo][dewsignnow][15] = strLine;
						break;
					case 17: // name17
						dewsignname[wlo][dewsignnow][16] = strLine;
						break;
					case 18: // name18
						dewsignname[wlo][dewsignnow][17] = strLine;
						break;
					case 19: // name19
						dewsignname[wlo][dewsignnow][18] = strLine;
						break;
					case 20: // name20
						dewsignname[wlo][dewsignnow][19] = strLine;
						break;

					case 21: // x1
						dewsignx1[wlo][dewsignnow] = Integer.parseInt(strLine);
						break;
					case 22: // y1
						dewsigny1[wlo][dewsignnow] = Integer.parseInt(strLine);
						break;
					case 23: // z1
						dewsignz1[wlo][dewsignnow] = Integer.parseInt(strLine);
						break;
					case 24: // x2
						dewsignx2[wlo][dewsignnow] = Integer.parseInt(strLine);
						break;
					case 25: // y2
						dewsigny2[wlo][dewsignnow] = Integer.parseInt(strLine);
						break;
					case 26: // z2
						dewsignz2[wlo][dewsignnow] = Integer.parseInt(strLine);
						break;
					case 27: // loop count
						dewsignloop[wlo][dewsignnow] = Integer
								.parseInt(strLine);
						dewsignnow++;
						dewsignmax[wlo] = dewsignnow;
						moden = 0;
						break;
					}

					moden++;

				}

				printC("ptdew&dewdd: loaded " + filena);

				in.close();
			}
			catch (Exception e) {// Catch exception if any
				System.err.println("LoadSignFile Error: " + e.getMessage());
			} // catch

			// ************************
		} // all world
	}

	// savesignfiledefrag

	public void loadworldfile() {
		String worldf = "ptdew_dewdd_worlds_sign.txt";

		try {

			System.out.println("ptdeW&DewDD Main : " + worldf);
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(worldf);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line

			dewworldlist = new String[15];
			dewworldlistmax = -1;

			// sthae
			// aosthoeau
			// * save
			String nowmode = "";

			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				dewworldlistmax++;
				dewworldlist[dewworldlistmax] = strLine;

			}

			System.out.println("ptdew&DewDD Main: Loaded " + worldf);

			in.close();
		}
		catch (Exception e) {// Catch exception if any
			System.err.println("Error load " + worldf + e.getMessage());
		}
	}

	// obsidianabsorb
	public void obsidianabsorb(Block block, Player player) {
		int digX = block.getX();
		int digY = block.getY();
		int digZ = block.getZ();

		int ex = digX;
		int ey = digY;
		int ez = digZ;

		World world = block.getWorld();
		Block blockToChange = block;
		int d5 = d4[getfreeselect(player)];

		for (ex = digX - d5; ex <= digX + d5; ex++) {
			for (ey = digY - d5; ey <= digY + d5; ey++) {
				for (ez = digZ - d5; ez <= digZ + d5; ez++) {
					blockToChange = world.getBlockAt(ex, ey, ez);
					if (checkpermissionarea(blockToChange, player, "absorb") == true) {
						continue;
					}

					if (blockToChange.getTypeId() == 8
							|| blockToChange.getTypeId() == 9) {

						blockToChange.setTypeId(0);

					}

				}
			}

		}
		player.sendMessage("ptdew&dewdd:obsidian absorteb water");
	}

	public void printA(String abc) {
		for (Player pla : Bukkit.getOnlinePlayers()) {
			pla.sendMessage(abc);
		}
	}

	public void printADMIN(String abc) {
		for (Player pla : Bukkit.getOnlinePlayers()) {
			if (is2admin(pla) == true) {
				pla.sendMessage(abc);
			}
		}
	}

	public void printAll(String abc) {
		printA(abc);
		printC(abc);
	}

	public void printC(String abc) {
		System.out.println(abc);
	}

	public void protectrail(Block block, Player player) {
		boolean ok = false;

		if (dewpri.checkpermissionareasign(block) == false) {
			protectrailrun(block, player); // add protect
		}

		Block b2 = null;

		for (int x = -4; x <= 4; x++) {
			for (int y = -4; y <= 4; y++) {
				for (int z = -4; z <= 4; z++) {

					if (x == 0 && y == 0 && z == 0) {
						continue;
					}

					b2 = block.getRelative(x, y, z);

					if (b2.getTypeId() != 27 && b2.getTypeId() != 66) {
						continue;
					}
					if (dewpri.checkpermissionareasign(b2) == true) {
						continue;
					}

					printAll("runing " + b2.getX() + "," + b2.getY() + ","
							+ b2.getZ());
					protectrail(b2, player);
					printAll("runing done " + b2.getX() + "," + b2.getY() + ","
							+ b2.getZ());

				}
			}
		}
	}

	public boolean protectrailrun(Block block, Player player) {

		boolean ok = false;
		boolean spa = false;

		// searh near for space and create sign
		Block b2 = null;
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				for (int z = -1; z <= 1; z++) {
					if (x == 0 && y == 0 && z == 0) {
						continue;
					}

					b2 = block.getRelative(x, y, z);

					if (b2.getTypeId() == 0) { // search near
						spa = false;
						if (b2.getRelative(0, -1, 0).getTypeId() == 0) {
							b2.getRelative(0, -1, 0).setTypeId(5);
							spa = true;
						}
						else {
							spa = true;
						}

						if (spa == false) {
							continue;
						}

						printAll("found space : " + b2.getX() + "," + b2.getY()
								+ "," + b2.getY());
						b2.setTypeId(63);
						Sign sign = (Sign) b2.getState();
						sign.setLine(0, "[dewdd]");
						sign.setLine(1, player.getName());
						sign.update(true);
						ok = true;
						printAll("created sign done at : " + b2.getX() + ","
								+ b2.getY() + "," + b2.getY());
						break;
					}

				}
				if (ok == true) {
					break;
				}

			}
			if (ok == true) {
				break;
			}
		}

		if (ok == true) {
			return true;
		}

		boolean ok2 = false;
		if (ok == false) { // loop when false
			for (int x = -1; x <= 1; x++) {
				for (int y = -1; y <= 1; y++) {
					for (int z = -1; z <= 1; z++) {
						if (x == 0 && y == 0 && z == 0) {
							continue;
						}
						b2 = block.getRelative(x, y, z);

						ok2 = protectrailrun(b2, player);
						if (ok2 == true) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	public int randomnumberint() {
		randomInt = randomG.nextInt(50);
		randomInt += 1;
		return randomInt;
	}

	// randomplaynote
	public void randomplaynote(Location player) {
		if (randomG.nextInt(100) > 10) {
			return;
		}

		for (Player pla : player.getWorld().getPlayers()) {
			pla.playSound(player, Sound.NOTE_PIANO, randomG.nextInt(50),
					randomG.nextFloat() + (float) 1);
		}
	}

	public void redstoneoredestroyworldnothread(Block block, Player player) {

		int digX = block.getX();
		int digY = block.getY();

		int digZ = block.getZ();

		int ex = digX;
		int ey = digY;
		int ez = digZ;

		World world = block.getWorld();

		Block blockCut = world.getBlockAt(ex, ey, ez);

		int d4loop = 1;

		ey = digY;
		int d5 = d4[getfreeselect(player)];

		while (d4loop <= d5 && ey > 0) {

			for (ex = digX - d4loop; ex <= digX + d4loop; ex++) {

				for (ez = digZ - d4loop; ez <= digZ + d4loop; ez++) {

					blockCut = world.getBlockAt(ex, ey, ez);

					if (checkpermissionarea(blockCut) == true
							|| blockCut.getLocation().distance(
									block.getLocation()) > d5 * 2) {
						continue;
					}

					blockCut.setTypeId(0);

				} // z

				// player.sendMessage("ptdew&dewdd:redstone delete :"+ (((ex-
				// (digX-d4)) * 100) / ((digX+d4)- (digX-d4))) + "%");
				// printC("ptdew&dewdd:redstone delete :"+ (((ex-
				// (digX-d4)) * 100) / ((digX+d4)- (digX-d4))) + "%");
			}

			ey--;
			d4loop++;
		}
	}

	// savesignfile
	public void savesignfile(int exceptint, int worldid) {
		String aa = getworldname(worldid);

		File inFile = new File(aa);

		FileWriter fwriter;
		try {
			printC("ptdew&dewdd:Start saving " + aa);
			fwriter = new FileWriter(inFile);

			fwriter.write(dewsignmax[worldid]
					+ System.getProperty("line.separator"));

			for (int y = 0; y < dewsignmax[worldid]; y++) {
				if (exceptint > -1) {
					if (y == exceptint) {
						continue;
					}
				}

				for (int whome = 0; whome <= (dewsignnamemax - 1); whome++) {
					if (dewsignname[worldid][y][whome] != null) {
						fwriter.write(dewsignname[worldid][y][whome]
								+ System.getProperty("line.separator"));
					}
					else {
						fwriter.write("null"
								+ System.getProperty("line.separator"));
					}
				}

				fwriter.write(dewsignx1[worldid][y]
						+ System.getProperty("line.separator"));
				fwriter.write(dewsigny1[worldid][y]
						+ System.getProperty("line.separator"));
				fwriter.write(dewsignz1[worldid][y]
						+ System.getProperty("line.separator"));

				fwriter.write(dewsignx2[worldid][y]
						+ System.getProperty("line.separator"));
				fwriter.write(dewsigny2[worldid][y]
						+ System.getProperty("line.separator"));
				fwriter.write(dewsignz2[worldid][y]
						+ System.getProperty("line.separator"));

				fwriter.write(dewsignloop[worldid][y]
						+ System.getProperty("line.separator"));

				// printC ("ptdew&dewdd: Saved y= " + y );

			}

			fwriter.close();
			printC("ptdew&dewdd:saved " + aa);
			return;

		}
		catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

		// printC ("ptdew&dewdd: save Done...");

		// ***************************88

		// ******************************
	}

	public void saveworld() {
		for (World world : Bukkit.getWorlds()) {
			world.save();
			for (Player pla : world.getPlayers()) {
				pla.saveData();
			}
		}
	}

	public void showwhohome(Block block, Player player) {
		int xyz = checkpermissionarea(block, true);
		if (xyz == -1) {
			player.sendMessage("ptdew&dewdd: This area , is not protection");
			return;
		}

		player.sendMessage("ptdew&dewdd: This area, protection by ");

		for (int xxx = 0; xxx < dewsignnamemax; xxx++) {
			if (dewsignname[getworldid(block.getWorld().getName())][xyz][xxx]
					.equalsIgnoreCase("null") == false) {
				player.sendMessage(xxx
						+ " = "
						+ dewsignname[getworldid(block.getWorld().getName())][xyz][xxx]);
			}

		}

		return;
	}

	public void soiladdseedrecusive(Block block, Player player, int seedid) {

		soiladdseedc ab = new soiladdseedc();
		ab.block = block;
		ab.player = player;
		ab.seedid = seedid;

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ac, ab);
	}

	public void superdestroy(Block block, Player player, int dleft, int typeid,
			byte typedata) {
		if (player.getItemInHand().getDurability() < player.getItemInHand()
				.getType().getMaxDurability()) {

			if (block.getTypeId() != typeid || block.getData() != typedata) {
				return;
			}

			if (is2vip(player) == false) {
				return;
			}

			if (checkpermissionarea(block, player, "delete") == true) {
				return;
			}

			player.getItemInHand().setDurability(
					(short) (player.getItemInHand().getDurability() + 1));
			block.breakNaturally(player.getItemInHand());

			Block block2 = null;

			for (int gx = -1; gx <= 1; gx++) {
				for (int gy = -1; gy <= 1; gy++) {
					for (int gz = -1; gz <= 1; gz++) {
						block2 = block.getRelative(gx, gy, gz);

						if (block2.getTypeId() == 0) {
							continue;
						}
						dleft--;
						if (dleft == 0) {
							return;
						}
						superdestroy(block2, player, dleft, typeid, typedata);
					}
				}
			}

		}
	}

} // dew minecraft