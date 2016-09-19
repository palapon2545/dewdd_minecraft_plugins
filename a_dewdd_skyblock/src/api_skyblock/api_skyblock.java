/*
 *      Author: patiphat mana-u-krid (dew)
 *      E-Mail: dewtx29@gmail.com
 *      facebook: https://www.facebook.com/dewddminecraft
 */
package api_skyblock;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import dewddtran.tr;

public class api_skyblock {

	class AdjustProtect implements Runnable {
		private Block midBlockX0Z0;
		private Player player;

		public AdjustProtect(Block midBlockX0Z0, Player player) {
			this.midBlockX0Z0 = midBlockX0Z0;
			this.player = player;
		}

		@Override
		public void run() {

			RSData tmprs[] = new RSData[Constant.rsBuffer];
			int curID = 0;

			for (int i = 0; i < Constant.rsBuffer; i++) {
				tmprs[i] = new RSData();
				tmprs[i].p = new String[RSMaxPlayer];

			}

			for (int i = 0; i < rsMax; i++) {
				Block bo = midBlockX0Z0.getWorld().getBlockAt(rs[i].x, rs[i].y, rs[i].z);

				boolean thereBlock = checkIsThatAreBlockOrNot(bo, player);
				if (thereBlock == true) {
					// copy

					tmprs[curID] = rs[i].copyIt();

					curID++;

					continue;
				} else {
					// delete protec
					continue;

				}

			}

			// copyBack
			rsMax = curID;
			for (int i = 0; i < curID; i++) {
				rs[i] = tmprs[i].copyIt();
			}

			// player.sendMessage("thereBlock " + thereBlock);

		}
	}

	class AdjustProtect2 implements Runnable {
		private Block midBlockX0Z0;
		private Player player;

		public AdjustProtect2(Block midBlockX0Z0, Player player) {
			this.midBlockX0Z0 = midBlockX0Z0;
			this.player = player;
		}

		@Override
		public void run() {

			// search top left

			// search top left

			LXRXLZRZType ee = getPositionLXRXLZRZ();

			Block bb = null;

			for (int tmpx = ee.lx; tmpx <= ee.rx; tmpx += 300) {
				for (int tmpz = ee.lz; tmpz <= ee.rz; tmpz += 300) {

					// check protect if there so skip
					bb = midBlockX0Z0.getLocation().getWorld().getBlockAt(tmpx, 150, tmpz);
					int checkid = getprotectid(bb);
					if (checkid > -1) {
						continue;
					}

					player.sendMessage(dprint.r.color("searching no protect zone " + tmpx + ",150," + tmpz));

					boolean thereBlock = checkIsThatAreBlockOrNot(bb, player);

					if (thereBlock == true) {
						// buy new protect

						rs[rsMax] = new RSData();

						rs[rsMax].x = tmpx;
						rs[rsMax].z = tmpz;
						rs[rsMax].y = 150;
						rs[rsMax].p = new String[RSMaxPlayer];

						for (int i = 0; i < RSMaxPlayer; i++) {
							rs[rsMax].p[i] = "null";
						}

						rs[rsMax].p[0] = getNewOwnerName();

						player.sendMessage(dprint.r.color(
								"bought new protect " + tmpx + ",150," + tmpz + " owner name " + rs[rsMax].p[0]));

						rsMax++;

					}

				}
			}

		}
	}

	class CreateSkyblockRS implements Runnable {

		private Player player;

		public CreateSkyblockRS(Player player) {
			this.player = player;

		}

		@Override
		public void run() {

			if (System.currentTimeMillis() - lastcreate < 10000) {
				dprint.r.printAll("ptdew&dewdd : " + tr.gettr("don't_spam_this_command"));

				lastcreate = System.currentTimeMillis();
				return;
			}

			dprint.r.printAll("ptdew&dewdd : " + tr.gettr("searching_freezone_for_build_skyblock"));
			lastcreate = System.currentTimeMillis();

			player.getInventory().clear();

			// random x , y ,z

			boolean buildcomplete = false;

			int x = 0;
			int y = 0;
			int z = 0;

			int searchRadius = 1;
			int searchCount = 0;

			while (buildcomplete == false) {
				searchCount++;

				if (searchCount > 1000) {
					searchRadius += 1;
				}

				// random x y z this is not near old rs list
				buildcomplete = true;

				x = rnd.nextInt(searchRadius) * 300 * (rnd.nextInt(2) == 1 ? 1 : -1);
				z = rnd.nextInt(searchRadius) * 300 * (rnd.nextInt(2) == 1 ? 1 : -1);
				y = rnd.nextInt(200) + 50;

				dprint.r.printAll("searching..." + x + "," + y + "," + z);

				boolean checkrs = true;

				for (int lop = 0; lop < rsMax; lop++) {
					if (rs[lop].x == x && rs[lop].z == z) {
					//	dprint.r.printAll(">>> owner " + rs[lop].p[0]);
						checkrs = false;
						break;
					}
				}

				if (checkrs == true) {
					for (int i = 1; i < 200; i++) {

						Block bd = player.getWorld().getBlockAt(x, i, z);
						if (bd.getType() != Material.AIR) {
							checkrs = false;
						}
					}
				}

				if (checkrs == false) {
					buildcomplete = false;
				} else { // if x y z this can do
					dprint.r.printAll("random " + x + "," + y + "," + z);

					int oldID = getOWNIslandID(player.getName(), false);
					if (oldID > -1) {
						rs[oldID].p[0] = getNewOwnerName();

					}

					int newid = getOWNIslandID(player.getName(), true);
					// get free slot for this player
					rs[newid].x = x;
					rs[newid].y = y;
					rs[newid].z = z;

					for (int lop = 0; lop < RSMaxPlayer; lop++) {
						rs[newid].p[lop] = "null";
					}
					rs[newid].p[0] = player.getName();
					rs[newid].p[1] = Constant.flag_autocut;
					rs[newid].p[2] = Constant.flag_autoabsorb;
					rs[newid].mission = 0;
					player.getWorld().getBlockAt(x - 16, y, z - 16).getChunk().getX();
					player.getWorld().getBlockAt(x - 16, y, z - 16).getChunk().getZ();

					player.getWorld().getBlockAt(x + 16, y, z + 16).getChunk().getX();
					player.getWorld().getBlockAt(x + 16, y, z + 16).getChunk().getZ();

					// clear for skyblock
					for (int nx = -60; nx <= 60; nx++) {
						for (int nz = -60; nz <= 60; nz++) {
							for (int ny = 0; ny <= 255; ny++) {

								player.getWorld().getBlockAt(x + nx, y + ny, z + nz).setType(Material.AIR);

							}
						}
					}

					// build skyblock
					Block block = player.getWorld().getBlockAt(x, y, z);
					Block block2 = null;

					for (int dy = 3; dy >= 0; dy--) {

						for (int dx = -3; dx <= 3; dx++) {

							for (int dz = -3; dz <= 0; dz++) {

								block2 = block.getRelative(dx, dy, dz);
								block2.setType(Material.GRASS);

							}
						}

					}

					// add item
					block2 = block.getRelative(0, 4, 0);

					block2.setType(Material.CHEST);
					block2 = block.getRelative(0, 4, 0);
					Chest chest = (Chest) block2.getState();
					chest.getInventory().clear();

					ItemStack itm = new ItemStack(Material.SAPLING, 3);
					chest.getInventory().addItem(itm.getData().toItemStack(3));

					itm = new ItemStack(Material.SAPLING, 3);
					itm.getData().setData((byte) 1);
					chest.getInventory().addItem(itm.getData().toItemStack(3));

					itm = new ItemStack(Material.SAPLING, 3);
					itm.getData().setData((byte) 2);
					chest.getInventory().addItem(itm.getData().toItemStack(3));

					itm = new ItemStack(Material.SAPLING, 3);
					itm.getData().setData((byte) 3);
					chest.getInventory().addItem(itm.getData().toItemStack(3));

					itm = new ItemStack(Material.SAPLING, 3);
					itm.getData().setData((byte) 4);
					chest.getInventory().addItem(itm.getData().toItemStack(3));

					itm = new ItemStack(Material.SAPLING, 3);
					itm.getData().setData((byte) 5);
					chest.getInventory().addItem(itm.getData().toItemStack(3));

					itm = new ItemStack(Material.CARROT, 3);

					chest.getInventory().addItem(itm.getData().toItemStack(10));
					itm = new ItemStack(Material.POTATO, 3);

					chest.getInventory().addItem(itm.getData().toItemStack(10));
					itm = new ItemStack(Material.PUMPKIN_SEEDS, 3);

					chest.getInventory().addItem(itm.getData().toItemStack(10));
					itm = new ItemStack(Material.MELON_SEEDS, 3);

					chest.getInventory().addItem(itm.getData().toItemStack(10));
					itm = new ItemStack(Material.SEEDS, 3);

					chest.getInventory().addItem(itm.getData().toItemStack(10));
					itm = new ItemStack(Material.SUGAR_CANE, 3);
					chest.getInventory().addItem(itm.getData().toItemStack(10));

					itm = new ItemStack(Material.TORCH, 30);
					chest.getInventory().addItem(itm.getData().toItemStack(3));

					itm = new ItemStack(Material.INK_SACK, 64);
					itm.getData().setData((byte) 15);
					chest.getInventory().addItem(itm.getData().toItemStack(64));

					itm = new ItemStack(Material.ICE, 2);

					itm.setData(itm.getData());
					chest.getInventory().addItem(itm);

					itm = new ItemStack(Material.LAVA_BUCKET, 1);

					itm.setData(itm.getData());
					chest.getInventory().addItem(itm);

					itm = new ItemStack(Material.CACTUS, 1);

					itm.setData(itm.getData());
					chest.getInventory().addItem(itm);

					itm = new ItemStack(Material.BROWN_MUSHROOM, 2);

					itm.setData(itm.getData());
					chest.getInventory().addItem(itm);

					itm = new ItemStack(Material.RED_MUSHROOM, 2);

					itm.setData(itm.getData());
					chest.getInventory().addItem(itm);

					itm = new ItemStack(Material.SAND, 7);

					itm.setData(itm.getData());
					chest.getInventory().addItem(itm);

					chest.update();

					block.getWorld().loadChunk(block2.getX(), block2.getZ(), true);
					block2 = block.getRelative(0, 5, 0);
					player.teleport(block2.getLocation());

					dprint.r.printA(tr.gettr("good_luck_createlsdone") + " '" + player.getName() + "'(" + x + "," + y
							+ "," + z + ")");

					dprint.r.printA(tr.gettr("if_you_want_to_back_to_is_type") + " /skyblock home");

					lastcreate = System.currentTimeMillis();

					player.sendMessage(tr.gettr("cur_lv_is") + (rs[newid].mission));

					// add item done

					// save file
					saveRSProtectFile();
					buildcomplete = true;
				}

			} // loop build complete
		}
	}

	class MissionNotification implements Runnable {

		@Override
		public void run() {

			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player == null) {
					continue;
				}

				// int id = getOWNIslandID(player, false);

				int id = getprotectid(player.getLocation().getBlock());

				// island not found
				if (id == -1) {
					continue;
				}

				if (getplayerinslot(player.getName(), id) > -1) {

					player.sendMessage(dprint.r.color(tr.gettr("cur_lv_is") + (rs[id].mission)));
				}

				// check his
			}

		}

	}

	public static int RSMaxPlayer = 20;

	public static RSData rs[] = new RSData[100];

	public static int rsMax = 0;

	public static JavaPlugin ac = null;

	public static ArrayList<LV1000Type> lv1000 = new ArrayList<LV1000Type>();

	public static boolean cando(Block block, Player player, String mode) {
		if ((player.hasPermission(Constant.poveride)) == true) {
			return true;
		}

		if (!isrunworld(block.getWorld().getName())) {
			return true;
		}

		int getid = getprotectid(block);

		if (getid == -1) {
			// player.sendMessage(dprint.r.color("this is not anyone skyblock");
			return true; // can't do it
		}

		// found
		int getslot = getplayerinslot(player.getName(), getid);
		
		if (getplayerinslot(Constant.flag_noprotect, getid) > -1 ) {

			return true;
		}
		
		if (getplayerinslot(Constant.flag_everyone, getid) > -1 && mode.equalsIgnoreCase("right")) {

			// player.sendMessage(dprint.r.color("this is not your skyblock ,
			// host is "
			// +
			// dew.rs[getid].p[0]);
			return true;
		}

		if (getslot == -1) {

			// player.sendMessage(dprint.r.color("this is not your skyblock ,
			// host is "
			// +
			// dew.rs[getid].p[0]);
			return false;
		} else {

			return true;
		}

	}

	public static String getNewOwnerName() {
		String abc = "";
		boolean found = false;
		do {
			found = false;
			Random rnd = new Random();
			abc = "freeZone" + rnd.nextInt(1000);
			for (int i = 0; i < rsMax; i++) {
				if (rs[i].p[0].equalsIgnoreCase(abc) == true) {

					found = true;
					break;
				}
			}

		} while (found == true);

		return abc;
	}

	public static int getplayerinslot(String player, Block block) {

		int getid = getprotectid(block);
		if (getid == -1) {
			return -2;
		}

		for (int lop = 0; lop < RSMaxPlayer; lop++) {
			if (rs[getid].p[lop].equalsIgnoreCase(player)) {
				return lop;
			}
		}

		return -1;
	}

	public static int getplayerinslot(String player, int rsID) {
		for (int lop = 0; lop < RSMaxPlayer; lop++) {
			if (rs[rsID].p[lop].equalsIgnoreCase(player)) {
				return lop;
			}
		}

		return -1;
	}

	public static LXRXLZRZType getPositionLXRXLZRZ() {
		LXRXLZRZType ee = new LXRXLZRZType();

		ee.lx = rs[0].x;
		ee.rx = rs[0].x;
		ee.lz = rs[0].z;
		ee.rz = rs[0].z;

		for (int i = 0; i < rsMax; i++) {
			if (rs[i].x < ee.lx) {
				ee.lx = rs[i].x;
			}

			if (rs[i].x > ee.rx) {
				ee.rx = rs[i].x;
			}

			if (rs[i].z < ee.lz) {
				ee.lz = rs[i].z;
			}

			if (rs[i].z > ee.rz) {
				ee.rz = rs[i].z;
			}

		}

		return ee;
	}

	public static int getprotectid(Block block) {
		// must check world

		for (int lop = 0; lop < rsMax; lop++) {
			if (block.getX() >= (rs[lop].x - 150) && block.getX() <= (rs[lop].x + 149)
					&& block.getZ() >= (rs[lop].z - 150) && block.getZ() <= (rs[lop].z + 149)) {
				return lop;
			}
		}

		return -1;
	}

	// Chat Event.class
	// BlockBreakEvent
	public static boolean isrunworld(String worldName) {
		return tr.isrunworld(ac.getName(), worldName);
	}

	// check if it is running
	Random rnd = new Random();

	public int maxautocut = 5;

	public String lastmessage = "";

	long lastcreate = 0;

	long lastclean = 0;

	int amount = 0; // recusive
					// count

	public api_skyblock() {
		loadRSProtectFile();
		loadLVFile();
	}

	public void adjustProtect(Block block, Player player) {

		AdjustProtect abc = new AdjustProtect(block, player);
		Bukkit.getScheduler().scheduleSyncDelayedTask(ac, abc, 1);

	}

	public void adjustProtect2(Block block, Player player) {

		AdjustProtect2 abc = new AdjustProtect2(block, player);
		Bukkit.getScheduler().scheduleSyncDelayedTask(ac, abc, 1);

	}

	public void applyReward(int rsID) {

		switch (rs[rsID].mission) {
		case 0: // get cobble stone

			Block bo = getBlockMiddleRS(rsID);
			Block bo2 = searchSpaceCube(bo, 5, 5);

			for (int i = 0; i < 5; i++) {
				for (int i2 = 0; i2 < 5; i2++) {
					for (int i3 = 0; i3 < 5; i3++) {

						Block bo3 = bo2.getRelative(i, i2, i3);
						if (bo3.getType() != Material.AIR) {
							dprint.r.printAll(tr.gettr("error while applyReward lv 0 block is != air"));
							break;
						}

						if (rnd.nextInt(100) > 80) {
							bo3.setType(Material.STONE);
						}

					}
				}
			}

			printToAllPlayerOnRS(rsID,
					tr.gettr("generated_small_island_at") + " " + bo2.getX() + "," + bo2.getY() + "," + bo2.getZ());

			printToAllPlayerOnRS(rsID, tr.gettr("got_reward_lv_" + rs[rsID].mission));

			break;
		case 1:

			bo = getBlockMiddleRS(rsID);
			bo2 = searchSpaceCube(bo, 5, 5);

			for (int i = 0; i < 5; i++) {
				for (int i2 = 0; i2 < 5; i2++) {
					for (int i3 = 0; i3 < 5; i3++) {

						Block bo3 = bo2.getRelative(i, i2, i3);
						if (bo3.getType() != Material.AIR) {
							dprint.r.printAll(tr.gettr("error while applyReward lv 0 block is != air"));
							break;
						}

						if (rnd.nextInt(100) > 80) {
							bo3.setType(Material.GRASS);
						}

					}
				}
			}

			printToAllPlayerOnRS(rsID,
					tr.gettr("generated_small_island_at") + " " + bo2.getX() + "," + bo2.getY() + "," + bo2.getZ());

			printToAllPlayerOnRS(rsID, tr.gettr("got_reward_lv_" + rs[rsID].mission));

			rs[rsID].tmpForCountingBone1 = 0;

			break;
		case 2:

			bo = getBlockMiddleRS(rsID);
			bo2 = searchSpaceCube(bo, 5, 5);

			bo2.setType(Material.CHEST);

			Chest chest = (Chest) bo2.getState();

			ItemStack itm = new ItemStack(Material.SAPLING, 3);
			chest.getInventory().addItem(itm.getData().toItemStack(3));

			itm = new ItemStack(Material.SAPLING, 3);
			itm.getData().setData((byte) 1);
			chest.getInventory().addItem(itm.getData().toItemStack(3));

			itm = new ItemStack(Material.SAPLING, 3);
			itm.getData().setData((byte) 2);
			chest.getInventory().addItem(itm.getData().toItemStack(3));

			itm = new ItemStack(Material.SAPLING, 3);
			itm.getData().setData((byte) 3);
			chest.getInventory().addItem(itm.getData().toItemStack(3));

			itm = new ItemStack(Material.CARROT, 3);

			chest.getInventory().addItem(itm.getData().toItemStack(10));
			itm = new ItemStack(Material.POTATO, 3);

			chest.getInventory().addItem(itm.getData().toItemStack(10));
			itm = new ItemStack(Material.PUMPKIN_SEEDS, 3);

			chest.getInventory().addItem(itm.getData().toItemStack(10));
			itm = new ItemStack(Material.MELON_SEEDS, 3);

			chest.getInventory().addItem(itm.getData().toItemStack(10));
			itm = new ItemStack(Material.SEEDS, 3);

			chest.getInventory().addItem(itm.getData().toItemStack(10));
			itm = new ItemStack(Material.SUGAR_CANE, 3);
			chest.getInventory().addItem(itm.getData().toItemStack(10));

			itm = new ItemStack(Material.CACTUS, 1);

			itm.setData(itm.getData());
			chest.getInventory().addItem(itm);

			itm = new ItemStack(Material.BROWN_MUSHROOM, 2);

			itm.setData(itm.getData());
			chest.getInventory().addItem(itm);

			itm = new ItemStack(Material.RED_MUSHROOM, 2);

			itm.setData(itm.getData());
			chest.getInventory().addItem(itm);

			itm = new ItemStack(Material.SAND, 7);

			itm.setData(itm.getData());
			chest.getInventory().addItem(itm);

			bo2.getRelative(BlockFace.DOWN).setType(Material.BOOKSHELF);
			bo2.getRelative(0, -1, 1).setType(Material.TORCH);

			printToAllPlayerOnRS(rsID,
					tr.gettr("generated_small_island_at") + " " + bo2.getX() + "," + bo2.getY() + "," + bo2.getZ());

			printToAllPlayerOnRS(rsID, tr.gettr("got_reward_lv_" + rs[rsID].mission));

			break;

		case 3:

			bo = getBlockMiddleRS(rsID);
			bo2 = searchSpaceCube(bo, 5, 5);

			for (int i = 0; i < 5; i++) {
				for (int i2 = 0; i2 < 5; i2++) {
					for (int i3 = 0; i3 < 5; i3++) {

						Block bo3 = bo2.getRelative(i, i2, i3);
						if (bo3.getType() != Material.AIR) {
							dprint.r.printAll(tr.gettr("error while applyReward lv 0 block is != air"));
							break;
						}

						if (rnd.nextInt(100) > 90) {
							bo3.setType(Material.IRON_ORE);
						}

					}
				}
			}

			printToAllPlayerOnRS(rsID,
					tr.gettr("generated_small_island_at") + " " + bo2.getX() + "," + bo2.getY() + "," + bo2.getZ());

			printToAllPlayerOnRS(rsID, tr.gettr("got_reward_lv_" + rs[rsID].mission));

			break;

		case 4:

			bo = getBlockMiddleRS(rsID);
			bo2 = searchSpaceCube(bo, 5, 5);

			for (int i = 0; i < 5; i++) {
				for (int i2 = 0; i2 < 5; i2++) {
					for (int i3 = 0; i3 < 5; i3++) {

						Block bo3 = bo2.getRelative(i, i2, i3);
						if (bo3.getType() != Material.AIR) {
							dprint.r.printAll(tr.gettr("error while applyReward lv 0 block is != air"));
							break;
						}

						if (rnd.nextInt(100) > 91) {
							bo3.setType(Material.GOLD_ORE);
						}

					}
				}
			}

			bo2 = searchSpaceCube(bo, 5, 5);

			for (int i = 0; i < 5; i++) {
				for (int i2 = 0; i2 < 5; i2++) {
					for (int i3 = 0; i3 < 5; i3++) {

						Block bo3 = bo2.getRelative(i, i2, i3);
						if (bo3.getType() != Material.AIR) {
							dprint.r.printAll(tr.gettr("error while applyReward lv 0 block is != air"));
							break;
						}

						if (rnd.nextInt(100) > 90) {
							bo3.setType(Material.COAL_ORE);
						}

					}
				}
			}

			// add mon

			int count = 0;
			do {
				bo2 = searchSpaceCube(bo, 5, 5);
				count++;

				if (count > 5000) {
					dprint.r.printAll(tr.gettr("this_is_land_cannot_search_anyblock_for_applyReward_LV4" + " " + rsID
							+ " xyz = " + rs[rsID].x + "," + rs[rsID].y + "," + rs[rsID].z));
					break;
				}
			} while (bo2.getRelative(-1, 0, 0).getType() == Material.AIR);

			for (int i = 0; i < 5; i++) {
				for (int i2 = 0; i2 <= 0; i2++) {
					for (int i3 = 0; i3 < 5; i3++) {

						Block bo3 = bo2.getRelative(i, i2, i3);
						if (bo3.getType() != Material.AIR) {
							dprint.r.printAll(tr.gettr("error while applyReward lv 0 block is != air"));
							break;
						}

						bo3.setType(Material.EMERALD_ORE);

					}
				}
			}

			Block bo3 = bo2.getRelative(2, 1, 2);
			bo3.setType(Material.NETHERRACK);
			bo3.getRelative(BlockFace.UP).setType(Material.FIRE);

			// add sign

			bo.getWorld().getBlockAt(bo.getX(), 0, bo.getZ()).setType(Material.BEDROCK);

			Block signAdder = bo.getWorld().getBlockAt(bo.getX(), 1, bo.getZ());
			signAdder.setType(Material.SIGN_POST);

			Sign sign = (Sign) signAdder.getState();
			sign.setLine(0, "" + rs[rsID].mission);
			sign.setLine(1, "" + bo3.getX());
			sign.setLine(2, "" + bo3.getY());
			sign.setLine(3, "" + bo3.getZ());
			sign.update(true);

			printToAllPlayerOnRS(rsID,
					tr.gettr("generated_small_island_at") + " " + bo2.getX() + "," + bo2.getY() + "," + bo2.getZ());

			// mon
			bo2 = searchSpaceCube(bo, 5, 5);

			printToAllPlayerOnRS(rsID, tr.gettr("got_reward_lv_" + rs[rsID].mission));

			break;

		case 5:

			bo = getBlockMiddleRS(rsID);
			bo2 = searchSpaceCube(bo, 5, 5);

			bo2.setType(Material.CHEST);

			chest = (Chest) bo2.getState();

			itm = new ItemStack(Material.LAVA_BUCKET, 1);
			chest.getInventory().addItem(itm.getData().toItemStack(2));

			itm = new ItemStack(Material.ICE, 1);
			chest.getInventory().addItem(itm.getData().toItemStack(2));

			itm = new ItemStack(Material.GRAVEL, 32);
			chest.getInventory().addItem(itm.getData().toItemStack(32));

			itm = new ItemStack(Material.SAND, 3);
			chest.getInventory().addItem(itm.getData().toItemStack(17));

			itm = new ItemStack(Material.FEATHER, 3);
			chest.getInventory().addItem(itm.getData().toItemStack(10));

			itm = new ItemStack(Material.NAME_TAG, 3);
			chest.getInventory().addItem(itm.getData().toItemStack(1));

			itm = new ItemStack(Material.MOSSY_COBBLESTONE, 3);
			chest.getInventory().addItem(itm.getData().toItemStack(4));

			itm = new ItemStack(Material.WEB, 3);
			chest.getInventory().addItem(itm.getData().toItemStack(2));

			bo2.getRelative(BlockFace.DOWN).setType(Material.LAPIS_ORE);
			bo2.getRelative(0, -1, 1).setType(Material.TORCH);

			printToAllPlayerOnRS(rsID,
					tr.gettr("generated_small_island_at") + " " + bo2.getX() + "," + bo2.getY() + "," + bo2.getZ());

			printToAllPlayerOnRS(rsID, tr.gettr("got_reward_lv_" + rs[rsID].mission));

			break;

		case 6:

			bo = getBlockMiddleRS(rsID);
			// bo2 = searchSpaceCube(bo, 5, 5);

			for (int i = -10; i < 10; i++) {
				for (int i2 = 255; i2 <= 255; i2++) {
					for (int i3 = -10; i3 < 10; i3++) {

						bo3 = bo.getWorld().getBlockAt(bo.getX() + i, i2, bo.getZ() + i3);

						if (bo3.getType() != Material.AIR) {
							dprint.r.printAll(tr.gettr("error while applyReward lv 0 block is != air"));
							break;
						}

						bo3.setType(Material.GRAVEL);

					}
				}
			}

			printToAllPlayerOnRS(rsID, tr.gettr("got_reward_lv_" + rs[rsID].mission));

			break;
		default:

			printToAllPlayerOnRS(rsID, tr.gettr("got_reward_lv_" + rs[rsID].mission));

			break;
		}
	}

	public boolean checkIsThatAreBlockOrNot(Block midBlockX0Z0, Player player) {
		int count = 0;
		boolean thereBlock = false;

		int search = 10;
		for (int x = -search; x <= search; x++) {
			for (int y = 0; y <= 256; y++) {

				for (int z = -search; z <= search; z++) {

					Block bbo = midBlockX0Z0.getWorld().getBlockAt(midBlockX0Z0.getX() + x, y, midBlockX0Z0.getZ() + z);

					if (bbo.getType() != Material.AIR) {

						player.sendMessage(bbo.getX() + "," + bbo.getY() + "," + bbo.getZ() + " = "
								+ bbo.getType().name() + ":" + bbo.getData());
						thereBlock = true;
						break;
					}
				}

				if (thereBlock == true) {
					break;
				}
			}

			if (thereBlock == true) {
				break;
			}

		}

		while (count < 100000 && thereBlock == false) {
			count++;

			int x = rnd.nextInt(300) - 150;
			int y = rnd.nextInt(256);
			int z = rnd.nextInt(300) - 150;

			Block bbo = midBlockX0Z0.getWorld().getBlockAt(midBlockX0Z0.getX() + x, y, midBlockX0Z0.getZ() + z);

			if (bbo.getType() != Material.AIR) {

				player.sendMessage(bbo.getX() + "," + bbo.getY() + "," + bbo.getZ() + " = " + bbo.getType().name() + ":"
						+ bbo.getData());
				thereBlock = true;
				break;
			}

		}

		return thereBlock;
	}

	public void createSkyblockRS(Player player) {
		CreateSkyblockRS ab = new CreateSkyblockRS(player);

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ac, ab);

	}

	public int distance2d(int x1, int z1, int x2, int z2) {
		double t1 = Math.pow(x1 - x2, 2);
		double t2 = Math.pow(z1 - z2, 2);
		double t3 = Math.pow(t1 + t2, 0.5);
		int t4 = (int) t3;
		return t4;
	}

	public Block getBlockMiddleRS(int rsID) {

		Block b = Bukkit.getWorld("world").getBlockAt(rs[rsID].x, rs[rsID].y, rs[rsID].z);

		return b;
	}

	public int getOWNIslandID(String player, boolean rebuild) {
		// loop for check exits rs id and return or not build new one

		// find
		for (int lop = 0; lop < rsMax; lop++) {
			if (rs[lop].p[0].equalsIgnoreCase(player)) {
				return lop;
			}
		}

		if (rebuild == false) {
			return -1;
		}
		// build new one

		rsMax++;
		rs[rsMax - 1].p = new String[RSMaxPlayer];
		for (int gg = 0; gg < RSMaxPlayer; gg++) {
			rs[rsMax - 1].p[gg] = new String();
			rs[rsMax - 1].p[gg] = "null";
		}

		return rsMax - 1; // rsmax -1 ; (
	}

	public void giveItemToAllPlayerInRS(int rsID, ItemStack itm) {
		for (int i = 0; i < RSMaxPlayer; i++) {
			Player player = Bukkit.getPlayer(rs[rsID].p[i]);

			if (player == null) {
				continue;
			}

			player.getInventory().addItem(itm);
		}

	}

	public void loadLVFile() {
		String worldf = Constant.rsLV_filename;

		File dir = new File(Constant.folder_name);
		dir.mkdir();

		String filena = Constant.folder_name + File.separator + worldf;
		File fff = new File(filena);

		try {

			lv1000.clear();

			fff.createNewFile();

			System.out.println("ptdeW&DewDD skyblock , lv loading : " + filena);
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(filena);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line

			// sthae
			// aosthoeau
			// * save

			String m[];

			while ((strLine = br.readLine()) != null) {
				LV1000Type lvo = new LV1000Type();

				m = strLine.split("\\s+");

				lvo.needSize = Integer.parseInt(m[0]);
				lvo.rewardSize = Integer.parseInt(m[1]);

				for (int i = 0; i < lvo.needSize; i++) {
					strLine = br.readLine();

					String bem[] = strLine.split(" ");

					lvo.needNameData[i] = bem[0];
					lvo.needAmount[i] = Integer.parseInt(bem[1]);

				}

				for (int i = 0; i < lvo.rewardSize; i++) {
					strLine = br.readLine();

					String bem[] = strLine.split(" ");
					lvo.rewardNameData[i] = bem[0];
					lvo.rewardAmount[i] = Integer.parseInt(bem[1]);

				}

				lv1000.add(lvo);

			}

			System.out.println("ptdew&DewDD skyblock : lv loaded " + filena);

			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error load " + filena + " " + e.getMessage());
		}
	}

	public void loadRSProtectFile() {
		String worldf = Constant.rsProtect_filename;

		File dir = new File(Constant.folder_name);
		dir.mkdir();

		String filena = Constant.folder_name + File.separator + worldf;
		File fff = new File(filena);

		try {

			rs = new RSData[Constant.rsBuffer];
			for (int lop = 0; lop < Constant.rsBuffer; lop++) {
				rs[lop] = new RSData();
			}
			rsMax = 0;

			fff.createNewFile();

			dprint.r.printAll("ptdeW&DewDD Skyblock : " + filena);
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(filena);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line

			// sthae
			// aosthoeau
			// * save
			String m[];

			while ((strLine = br.readLine()) != null) {

				m = strLine.split("\\s+");
				// Print the content on the console
				rsMax++;
				rs[rsMax - 1].x = Integer.parseInt(m[0]);
				rs[rsMax - 1].y = Integer.parseInt(m[1]);
				rs[rsMax - 1].z = Integer.parseInt(m[2]);
				rs[rsMax - 1].p = new String[RSMaxPlayer];

				for (int i = 0; i < RSMaxPlayer; i++) {
					rs[rsMax - 1].p[i] = m[i + 3];

				}

				if (m.length == 24) {
					int bb = (int) Double.parseDouble(m[23]);

					// bb = 0;
					rs[rsMax - 1].mission = (bb);

				}

				// rs[rsMax - 1].mission = 0;

			}

			// rename All Duplicate owner
			for (int i = 0; i < rsMax; i++) {
				boolean fou = false;

				for (int j = 0; j < rsMax; j++) {
					if (j == i) {
						continue;
					}

					if (rs[i].p[0].equalsIgnoreCase(rs[j].p[0])) {
						fou = true;
						break;
					}
				}

				if (fou == true) { // duplicate
					// rename it
					Random rnd = new Random();
					String abc = "dupi" + rnd.nextInt(10000);

					// search

					boolean kfou = false;

					do {
						abc = "dupi" + rnd.nextInt(10000);
						kfou = false;

						for (int k = 0; k < rsMax; k++) {
							if (rs[k].p[0].equalsIgnoreCase(abc)) {
								kfou = true;
								break;
							}

						}

					} while (kfou == true);

					dprint.r.printAll("duplicate rs owner name > " + rs[i].p[0] + " renamed to " + abc);
					rs[i].p[0] = abc;
				}

			}

			dprint.r.printAll("ptdew&DewDD Skyblock: Loaded " + filena);

			for (int i = rsMax - 1; i >= 0; i--) {

				for (int j = 0; j < i; j++) {

					if (rs[i].x == rs[j].x && rs[i].z == rs[j].z) {
						dprint.r.printAll("duplicate x y on id " + j + " and " + i);
						continue;
					}
				}

			}

			in.close();
		} catch (Exception e) {// Catch exception if any
			dprint.r.printAll("Error load " + filena + e.getMessage());
		}
	}

	public synchronized void nextMission(int rsID, int cur) {
		printToAllPlayerOnRS(rsID, ((rs[rsID].mission) + " " + tr.gettr("mission_complete")));

		// dprint.r.printAll("0 calling apply reward");
		applyReward(rsID);

		printToAllPlayerOnRS(rsID, tr.gettr("next_mission"));

		dprint.r.printAdmin(tr.gettr("owner_of_island_name") + rs[rsID].p[0] + " " + tr.gettr("did_mission_complete")
				+ " " + (rs[rsID].mission));

		int tmpID = (cur);
		tmpID++;
		rs[rsID].mission = (tmpID);

		printToAllPlayerOnRS(rsID, ((rs[rsID].mission) + " ..."));

		saveRSProtectFile();

	}

	public void printToAllPlayerOnRS(int rsID, String message) {
		// dprint.r.printA("printToallplayeronrs " + rsID + " , " + message);

		for (int i = 0; i < RSMaxPlayer; i++) {

			Player player = Bukkit.getPlayer(rs[rsID].p[i]);

			if (player == null) {
				continue;
			}

			// dprint.r.printA("player " + i + " = " + player.getName());

			if (player.isOnline() == false) {
				continue;
			}

			// dprint.r.printA("online player " + i + " = " + player.getName());

			player.sendMessage(dprint.r.color(message));
		}
	}

	public int random16() {
		int g = rnd.nextInt(360);
		g -= 180;
		g = g * 16;
		return g;

		// 180
	}

	public void saveRSProtectFile() {

		File dir = new File(Constant.folder_name);
		dir.mkdir();

		String filena = Constant.folder_name + File.separator + Constant.rsProtect_filename;
		File fff = new File(filena);

		FileWriter fwriter;
		try {
			fff.createNewFile();

			dprint.r.printC("ptdew&dewdd:Start saving " + filena);
			fwriter = new FileWriter(fff);

			for (int y = 0; y < rsMax; y++) {
				if (rs[y].p[0].equalsIgnoreCase("")) {
					continue;
				}
				String wr = "";
				wr = rs[y].x + " " + rs[y].y + " " + rs[y].z + " ";

				for (int y2 = 0; y2 < RSMaxPlayer; y2++) {
					wr = wr + rs[y].p[y2];

					if (y2 != RSMaxPlayer)
						wr = wr + " ";

				}

				wr = wr + " " + rs[y].mission;

				fwriter.write(wr + System.getProperty("line.separator"));

			}

			fwriter.close();
			dprint.r.printC("ptdew&dewdd:saved " + filena);
			return;

		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Block searchSpaceCube(Block startPoint, int radiusCubeNeed, int rad) {
		int x = startPoint.getX();
		int y = startPoint.getY();
		int z = startPoint.getZ();

		// rad = 100;

		boolean foundx = false;
		Block b;

		int timeSearch = 0;
		do {

			timeSearch++;
			if (timeSearch > 100) {
				rad++;
			}

			int x2 = x + (rnd.nextInt(rad * 2) - rad);
			int y2 = y + (rnd.nextInt(rad * 2) - rad);
			int z2 = z + (rnd.nextInt(rad * 2) - rad);

			b = startPoint.getWorld().getBlockAt(x2, y2, z2);
			foundx = true;
			for (int x3 = x2; x3 <= x2 + radiusCubeNeed; x3++) {
				for (int y3 = y2; y3 <= y2 + radiusCubeNeed; y3++) {
					for (int z3 = z2; z3 <= z2 + radiusCubeNeed; z3++) {
						Block bSpace = b.getWorld().getBlockAt(x3, y3, z3);
						if (bSpace.getType() != Material.AIR) {
							foundx = false;
							break;
						}

					}

					if (foundx == false) {
						break;
					}

				}

				if (foundx == false) {
					break;
				}

			}

		} while (foundx == false);

		return b;

	}

	public void startMissionNotificationLoopShowing() {
		Bukkit.getScheduler().cancelTasks(ac);

		Bukkit.getScheduler().scheduleSyncRepeatingTask(ac, new MissionNotification(), 1, 12000);
	}
}