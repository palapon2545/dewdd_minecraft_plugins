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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Dropper;
import org.bukkit.block.Hopper;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

import api_skyblock.Constant;
import api_skyblock.api_skyblock;
import dewddtran.tr;
import li.Constant_Protect;
import li.IDDataType;
import li.LXRXLZRZType;
import li.Useful;

public class dewset extends dewset_interface {

	public boolean decreseitem1(Player player, int itemid, int itemdata, boolean forcetruedata) {
		ItemStack itm = null;
		int lenl = 0;

		if (itemid == 0)
			return false;

		for (lenl = 0; lenl < player.getInventory().getContents().length; lenl++) {
			if (player.getInventory().getItem(lenl) == null) {
				continue;
			}

			itm = player.getInventory().getItem(lenl);

			if (itemid == 8 || itemid == 9 || itemid == 326)
				return true;
			else if (itemid == 44 || itemid == 43) {
				if (itm.getTypeId() != 44 && itm.getTypeId() != 43) {
					continue;
				}
			}

			// piston
			else if (itemid == 33 || itemid == 34 || itemid == 29) {
				if (itm.getTypeId() != 33 && itm.getTypeId() != 34 && itm.getTypeId() != 29) {
					continue;
				}
			}

			// dirt
			else if (itemid == 2 || itemid == 3 || itemid == 60) {
				if (itm.getTypeId() != 3 && itm.getTypeId() != 2 && itm.getTypeId() != 60) {
					continue;
				}
			}
			// repeater
			else if (itemid == 356 || itemid == 93 || itemid == 94) {
				if (itm.getTypeId() != 356 && itm.getTypeId() != 93 && itm.getTypeId() != 94) {
					continue;
				}
			}
			// redstone torch
			else if (itemid == 75 || itemid == 76) {
				if (itm.getTypeId() != 75 && itm.getTypeId() != 76) {
					continue;
				}
			}
			// redstone wire
			else if (itemid == 331 || itemid == 55) {
				if (itm.getTypeId() != 331 && itm.getTypeId() != 55) {
					continue;
				}
			}
			// pumpkin
			else if (itemid == 361 || itemid == 104) {
				if (itm.getTypeId() != 361 && itm.getTypeId() != 104) {
					continue;
				}
			}
			// melon
			else if (itemid == 362 || itemid == 105) {
				if (itm.getTypeId() != 362 && itm.getTypeId() != 105) {
					continue;
				}
			}

			// sugar
			else if (itemid == 338 || itemid == 83) {
				if (itm.getTypeId() != 338 && itm.getTypeId() != 83) {
					continue;
				}
			}

			// wheat
			else if (itemid == 295 || itemid == 59) {
				if (itm.getTypeId() != 295 && itm.getTypeId() != 59) {
					continue;
				}
			}
			// carrot
			else if (itemid == 391 || itemid == 141) {
				if (itm.getTypeId() != 391 && itm.getTypeId() != 141) {
					continue;
				}
			}
			// potato
			else if (itemid == 392 || itemid == 142) {
				if (itm.getTypeId() != 392 && itm.getTypeId() != 142) {
					continue;
				}
			} else if (itm.getTypeId() != itemid) {
				continue;
			}

			if (forcetruedata == true)
				if (itm.getData().getData() != itemdata) {
					continue;
				}

			if (itm.getAmount() != 1) {
				itm.setAmount(itm.getAmount() - 1);
				return true;
			} else {
				ItemStack emy = player.getItemInHand();
				emy.setTypeId(0);

				player.getInventory().setItem(lenl, emy);

				return true;
			}

		}
		return false;
	}
	
	class autosortchest2_class implements Runnable {
		private Block block;
		private Player player;

		public autosortchest2_class(Block block, Player player) {
			this.block = block;
			this.player = player;
		}
		

		@Override
		public void run() {

			if (block.getTypeId() != Material.CHEST.getId() && block.getTypeId() != Material.TRAPPED_CHEST.getId()) {

				player.sendMessage(dprint.r.color(tr.gettr("auto_sort_chest_2_is_not_chest")));
				return;
			}

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

				for (l1 = 0; l1 < leng; l1++)
					// player.sendMessage(dprint.r.color("finding old data " +
					// l1);
					if (sid[l1] == it.getTypeId())
						// player.sendMessage(dprint.r.color("ax " + sid[l1]);
						if (sdata[l1].getData().getData() == it.getData().getData()) {

							founded = true;

							// player.sendMessage(dprint.r.color("s=" + l1 +
							// ",id:" + sid[l1] +
							// ",data:"
							// + sdata[l1] + ",amount" + samount[l1]);
							samount[l1] += it.getAmount();
							break;
						}

				// if not found
				if (founded == false) {
					// player.sendMessage(dprint.r.color("can't find old slot");

					founded = false;
					for (l1 = 0; l1 < leng; l1++)
						// find empty
						if (sid[l1] == -1) {
							sid[l1] = it.getTypeId();
							sdata[l1] = it;
							samount[l1] = it.getAmount();
							founded = true;
							break;
						}

					if (founded = false) {
						player.sendMessage(dprint.r.color(tr.gettr("error_auto_sort_chest2_can't_find_empty_slot")));
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
				while (samount[l1] > 0)
					if (samount[l1] >= 64) {
						// player.sendMessage(dprint.r.color("adding > " +
						// sid[l1] +
						// tr.gettr("amount") + "= " +
						// samount[l1]);
						sdata[l1].setAmount(64);
						chest.getInventory().addItem(sdata[l1]);
						samount[l1] -= 64;
					} else {
						// player.sendMessage(dprint.r.color("adding > " +
						// sid[l1] +
						// tr.gettr("amount") + " = " +
						// samount[l1]);

						sdata[l1].setAmount(samount[l1]);
						chest.getInventory().addItem(sdata[l1]);

						samount[l1] -= samount[l1];
					}

				// player.sendMessage(dprint.r.color("x data " +
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
	}

	class chestabsorb_c implements Runnable {

		public chestabsorb_c() {
			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, this, sleeptime);
		}

		@Override
		public void run() {
			int d4 = 20;
			Block block = null;
			Block block2 = null;
			int d5 = 1;
			Chest chest = null;
			int slotp = -1;

			for (Player player : Bukkit.getOnlinePlayers()) {
			
				// search nearby box and sign ... ummm yes
				block = player.getLocation().getBlock();

				/*
				 * if (checkpermissionarea(block)== false) { not protect area
				 * continue; }
				 */
				if (cando_all(block, player, "build") == false) {
					// build
					continue;
				}

				for (int gx = 0 - d4; gx <= 0 + d4; gx++) {
					for (int gy = 0 - d4; gy <= 0 + d4; gy++) {
						for (int gz = 0 - d4; gz <= 0 + d4; gz++) {
							// first search sign
							block = player.getLocation().getBlock().getRelative(gx, gy, gz);
							if (block == null) {
								continue;
							}

							if (block.getTypeId() != 63 && block.getTypeId() != 68) {
								continue;
							}

							Sign sign = (Sign) block.getState();
							if (sign.getLine(0).equalsIgnoreCase("dewtobox")) {
								sign.setLine(0, "[dewtobox]");
								sign.update(true);
							}

							if (sign.getLine(0).equalsIgnoreCase("[dewtobox]") == true) {
								// player.sendMessage(dprint.r.color("found
								// dewtobox sign : "
								// +
								// block.getLocation().getBlockX() + ","
								// + block.getLocation().getBlockY() + "," +
								// block.getLocation().getBlockZ());

								int intb = Integer.parseInt(sign.getLine(1));
								if (intb == 0) {
									continue;
								}

								// after found sign so find box

								// box
								for (int ax = 0 - d5; ax <= 0 + d5; ax++) {
									for (int ay = 0 - d5; ay <= 0 + d5; ay++) {
										for (int az = 0 - d5; az <= 0 + d5; az++) {
											block2 = block.getRelative(ax, ay, az);
											if (block2 == null) {
												continue;
											}

											if (block2.getTypeId() != 54) {
												continue;
											}

											// player.sendMessage(dprint.r.color("found
											// dewtobox chest : "
											// +
											// block2.getLocation().getBlockX()
											// +
											// ","
											// +
											// block2.getLocation().getBlockY()
											// +
											// "," +
											// block2.getLocation().getBlockZ());

											slotp = player.getInventory().first(intb);
											if (slotp == -1) {
												continue;
											}

											chest = (Chest) block2.getState();

											int chestslot = -1;
											chestslot = chest.getInventory().firstEmpty();
											if (chestslot == -1) {
												continue;
											}

											// ready to move
											chest.getInventory().addItem(player.getInventory().getItem(slotp));

											player.getInventory().clear(slotp);

											player.sendMessage(
													dprint.r.color("[dewtobox] " + tr.gettr("moved") + intb));

											// added

											// if true
											// check is empty
											// check item of player

										}
									}
								}

							} // dew to box

						} // loop
					}
				}

			} // player
		}
	}

	class chestabsorb_c2 implements Runnable {

		public chestabsorb_c2() {
			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, this, sleeptime);
		}

		@Override
		public void run() {

			long nn = System.currentTimeMillis();

			if (nn - lastsort2 < 100) {
				Bukkit.getScheduler().scheduleSyncDelayedTask(ac, this, 10);
				return;
			}

			lastsort2 = nn;

			int d4 = 30;
			Block block = null;

			for (Player player : Bukkit.getOnlinePlayers()) {
				
				// search nearby box and sign ... ummm yes
				block = player.getLocation().getBlock();

				/*
				 * if (checkpermissionarea(block)== false) { not protect area
				 * continue; }
				 */
				if (cando_all(block, player, "build") == false) {
					// build
					continue;
				}

				// player.sendMessage(dprint.r.color("searching... dewsortbox
				// sign");

				// search any sign near player
				for (int gx = 0 - d4; gx <= 0 + d4; gx++) {
					for (int gy = 0 - d4; gy <= 0 + d4; gy++) {
						for (int gz = 0 - d4; gz <= 0 + d4; gz++) {
							lastsort2 = nn;

							// first search sign
							block = player.getLocation().getBlock().getRelative(gx, gy, gz);

							if (block.getTypeId() != 63 && block.getTypeId() != 68) {
								continue;
							}

							// dewsortbox
							// dewsorttype

							Sign sign = (Sign) block.getState();
							if (sign.getLine(0).equalsIgnoreCase("dewsortbox")) {
								sign.setLine(0, "[dewsortbox]");
								sign.update(true);
							}

							if (sign.getLine(0).equalsIgnoreCase("[dewsortbox]") == true) {

								/*
								 * player.sendMessage(dprint.r.color(
								 * "cur found dewsortbox sign at " +
								 * block.getX() + "," + block.getY() + "," +
								 * block.getZ());
								 */

								String sorttype = sign.getLine(1);
								if (sorttype.equalsIgnoreCase("")) {
									player.sendMessage(dprint.r.color(tr.gettr("sorttype_name_must_not_null")));
									continue;
								}

								// got sign type
								// search current chest
								Block curchest = chestnearsign(block);

								if (curchest == null) {
									player.sendMessage(dprint.r.color("curchest == null"));

									continue;

								}

								Block curprochest = protochest(block, d4, sorttype);
								if (curprochest == null) {
									player.sendMessage(
											dprint.r.color("curprochest == null > " + d4 + " , " + sorttype));

									continue;

								}

								// player.sendMessage(dprint.r.color("opening
								// curchest..."+
								// curchest.getTypeId());
								Chest curchest1 = (Chest) curchest.getState();
								// player.sendMessage(dprint.r.color("opening
								// curchest done");

								// player.sendMessage(dprint.r.color("opening
								// curprochest..."+
								// curprochest.getTypeId());

								Chest curchestin = (Chest) curprochest.getState();
								// player.sendMessage(dprint.r.color("opening
								// curprochest done");

								// player.sendMessage(dprint.r.color("opened
								// both chest");
								// after got cur chest
								// search another dewsortbox for swap
								// *********************
								Block temp = null;
								for (int jx = 0 - d4; jx <= 0 + d4; jx++) {
									for (int jy = 0 - d4; jy <= 0 + d4; jy++) {
										for (int jz = 0 - d4; jz <= 0 + d4; jz++) {
											if (jx == 0 && jy == 0 && jz == 0) {
												continue;
											}
											// first search sign
											temp = block.getLocation().getBlock().getRelative(jx, jy, jz);

											if (temp.getTypeId() != 63 && temp.getTypeId() != 68) {
												continue;
											}

											// dewsortbox
											// dewsorttype

											Sign js = (Sign) temp.getState();

											if (js.getLine(0).equalsIgnoreCase("[dewsortbox]") == true) {

												/*
												 * player.sendMessage(dprint.r.
												 * color (
												 * "swap found dewsortbox at " +
												 * temp.getX() + "," +
												 * temp.getY() + "," +
												 * temp.getZ());
												 */

												String jsorttype = js.getLine(1);
												if (jsorttype.equalsIgnoreCase("")) {
													// player.sendMessage(dprint.r.color("swap_sorttype_name_must_not_null");
													continue;
												}

												// got sign type
												// search current chest
												Block swapchest = chestnearsign(temp);
												if (swapchest == null) {
													// player.sendMessage(dprint.r.color("swapchest
													// == null");
													continue;
												}

												if (swapchest.getLocation().distance(curchest.getLocation()) <= 1) {
													continue;
												}

												Block swapprochest = protochest(temp, d4, jsorttype);
												if (swapprochest == null) {
													// player.sendMessage(dprint.r.color("swapprochest
													// == null");
													continue;
												}

												// player.sendMessage(dprint.r.color("opening
												// swapprochest...");

												Chest swapchestin = (Chest) swapprochest.getState();
												// player.sendMessage(dprint.r.color("opening
												// swapchest...");

												Chest swapchest1 = (Chest) swapchest.getState();

												// player.sendMessage(dprint.r.color("opened
												// both chest swap");

												for (int ikn = 0; ikn < curchest1.getInventory().getSize(); ikn++) {
													ItemStack i1 = curchest1.getInventory().getItem(ikn);

													if (i1 == null) {
														continue;
													}

													// search 1 is not in
													// 1prototype
													int i1proslot = curchestin.getInventory().first(i1.getType());
													if (i1proslot > -1) {
														// player.sendMessage(dprint.r.color("i1proslot
														// > -1");
														continue;
													}

													// if not found it's mean
													// wrong item in cur chest

													// check it that item in
													// second prototype

													if (swapchestin.getInventory().first(i1.getType()) == -1) {
														// player.sendMessage(dprint.r.color("swapchestin
														// can't found i1
														// item");
														continue;
													}

													// time to swap item
													// search free item on
													// second

													int freeslot = swapchest1.getInventory().firstEmpty();
													if (freeslot == -1) {
														// player.sendMessage(dprint.r.color("free
														// slot of swapchest 1
														// is -1");
														continue;
													}

													// time to move
													// player.sendMessage(dprint.r.color("moviing
													// item");
													swapchest1.getInventory().setItem(freeslot, i1);
													curchest1.getInventory().setItem(ikn, new ItemStack(0));

													player.sendMessage(dprint.r.color(
															"swaped item " + i1.getType().name() + " " + i1.getAmount())
															+ " from " + curchest.getX() + "," + curchest.getY() + ","
															+ curchest.getZ() + "[" + sorttype + "]" + " to "
															+ swapchest.getX() + "," + swapchest.getY() + ","
															+ swapchest.getZ() + "[" + jsorttype + "]");

													lastsort2 = nn;

													new chestabsorb_c2();
													return;

												}
												// how to swap
												// loop all of item of source

											}
										} // search another chest
									}
								}

								// **********************
								// search another chest block for swap item

							} // dew to box

						} // searh any sign near player
					}
				}

			} // player
		}
	}

	class createmonster_c implements Runnable {
		private EntityType EntityTypeGot;
		private Player player;

		public createmonster_c(Player player, EntityType EntityTypeGot, int count) {
			this.player = player;
			this.EntityTypeGot = EntityTypeGot;
			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, this, sleeptime);
		}

		@Override
		public void run() {
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
								Block block2 = player.getWorld().getBlockAt(lx, ly, lz);
								if (block2.getTypeId() == 0) {
									an = false;
								} else {
									an = true;
								}
							}
						}
					}

					if (an == true) {
						y++;
						if (y > 253 || y < 1)
							return;
					}

				}

				loca = player.getLocation();
				loca.setX(x);
				loca.setY(y);
				loca.setZ(z);

				player.getWorld().spawnEntity(loca, EntityTypeGot);

			} // loop monster all
		}
	}

	// cutwoodsub

	class delaylay55 implements Runnable {
		private item55deletec ab;

		public delaylay55(item55deletec ab) {
			this.ab = ab;
		}

		@Override
		public void run() {

			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, ab, sleeptime);

		}
	}

	class DeleteRecursive_Thread implements Runnable {
		private HashMap<String, Location> bd;
		private World world;
		private LXRXLZRZType ee;
		private ArrayList<IDDataType> item;
		private int chunklimit = 0;
		private int search = 10;

		public DeleteRecursive_Thread(HashMap<String, Location> bd, World world, int firstAdded, LXRXLZRZType ee,
				ArrayList<IDDataType> item, int chunklimit, int search) {
			this.bd = bd;
			this.world = world;
			this.ee = ee;
			this.item = item;
			this.chunklimit = chunklimit;
			this.search = search;

			// random add

		}

		@Override
		public void run() {
			long blockdo = 0;

			// dprint.r.printAll("nope.avi " + id.name());

			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player == null) {
					continue;

				}

				if (player.getItemInHand().getType() == Material.STICK) {
					// bd = null;
					return;
				}
			}

			long startTime = System.currentTimeMillis();

			// dprint.r.printAll("start " + bd.size());

			if (bd.size() == 0) {

				bd = null;
				bd = new HashMap<String, Location>();
				// dprint.r.printAll("run () bd.size = " + bd.size());

				while (System.currentTimeMillis() - startTime < 1000 && world.getLoadedChunks().length <= chunklimit) {

					int x = Useful.randomInteger(ee.lx, ee.rx);
					int z = Useful.randomInteger(ee.lz, ee.rz);

					int y = Useful.randomInteger(ee.ly, ee.rz);

					Block block = world.getBlockAt(x, y, z);
					if (IDDataType.isThisItemOnTheList(item, block.getTypeId(), block.getData())) {

						blockdo++;

						if (bd.get(tr.locationToString(block.getLocation())) == null) {
							bd.put(tr.locationToString(block.getLocation()), block.getLocation());
						}

						dprint.r.printAll("delete Recursive > first add > " + block.getX() + "," + block.getY() + ","
								+ block.getZ() + " size " + bd.size() + " , id data " + block.getTypeId() + ":"
								+ block.getData());

					}

				}

				for (Player player : Bukkit.getOnlinePlayers()) {
					if (player == null) {
						continue;

					}

					Block getStack = player.getLocation().getBlock();

					for (int x = -search; x <= search; x++)
						for (int y = -search; y <= search; y++) {

							for (int z = -search; z <= search; z++) {

								Block bo = player.getWorld().getBlockAt(getStack.getX() + x, getStack.getY() + y,
										getStack.getZ() + z);

								// dprint.r.printAll("near " +
								// tr.locationToString(bo.getLocation()) + "
								// block id " + bo.getType().name() + ":" +
								// bo.getData() + " > item "
								// + id.name() + ":" + data);

								if (IDDataType.isThisItemOnTheList(item, bo.getTypeId(), bo.getData())) {
									// dprint.r.printAll(bo.getType().name()
									// + " , " + id.name());
									// dprint.r.printAll("near " +
									// tr.locationToString(bo.getLocation())
									// + " block id " +
									// bo.getType().name());

									if (bd.get(tr.locationToString(bo.getLocation())) == null) {
										blockdo++;
										bd.put(tr.locationToString(bo.getLocation()), bo.getLocation());
										// dprint.r.printAll("added " +
										// bd.size() + " " +
										// tr.locationToString(bo.getLocation())
										// + " id " + bo.getType().name() +
										// ":" + bo.getData());
									}

								}

							}

						}

				}

				if (bd.size() == 0) {

					// dprint.r.printAll("recall ... " + bd.size() + " , blockdo
					// " + blockdo + "/" +
					// (System.currentTimeMillis() - startTime) + " avg = " +
					// (blockdo / (System.currentTimeMillis() - startTime +1))
					// );

					DeleteRecursive_Thread newRun = new DeleteRecursive_Thread(bd, world, 0, ee, item, chunklimit,
							search);
					Bukkit.getScheduler().scheduleSyncDelayedTask(ac, newRun, sleeptime);

					return;
				}

			}

			startTime = System.currentTimeMillis();

			// blockdo = 0;

			int first = 0;
			while (bd.size() > 0 && System.currentTimeMillis() - startTime < 1000) {

				for (int i = 0; i < 1000000 && System.currentTimeMillis() - startTime < 1000; i++) {

					String forDeleteLoc = "";
					for (String locStr : bd.keySet()) {

						Location loc = bd.get(locStr);

						// bd.remove(locStr);
						forDeleteLoc = locStr;

						Block getStack = world.getBlockAt(loc);

						if (getStack.getWorld().getLoadedChunks().length > chunklimit) {
							break;
						}

						if (IDDataType.isThisItemOnTheList(item, getStack.getTypeId(), getStack.getData())) {

						} else {
							break;
						}

						if (first == 0) {
							dprint.r.printAll("delete recursive > break > " + getStack.getX() + "," + getStack.getY()
									+ "," + getStack.getZ() + " " + getStack.getType().name() + ":" + getStack.getData()
									+ " size " + bd.size() + " " + getStack.getWorld().getName() + " > id data "
									+ getStack.getTypeId() + ":" + getStack.getData() + " blockdo " + blockdo + "/"
									+ (System.currentTimeMillis() - startTime) + " avg = "
									+ (blockdo / (System.currentTimeMillis() - startTime + 1)));
							first = 1;
						}

						// getStack.breakNaturally();
						getStack.setType(Material.AIR);
						// dprint.r.printAdmin("break " +
						// getStack.getType().name() + " " +
						// tr.locationToString(getStack.getLocation()));

						blockdo++;

						for (int x = -search; x <= search; x++)
							for (int y = -search; y <= search; y++) {

								for (int z = -search; z <= search; z++) {

									Block bo = getStack.getWorld().getBlockAt(getStack.getX() + x, getStack.getY() + y,
											getStack.getZ() + z);
									if (bo.getX() < ee.lx || bo.getX() > ee.rx || bo.getZ() < ee.lz || bo.getZ() > ee.rz
											|| bo.getY() > 255 || bo.getY() < 0) {
										break;
									}

									if (IDDataType.isThisItemOnTheList(item, bo.getTypeId(), bo.getData())) {
										blockdo++;

										if (bd.get(tr.locationToString(bo.getLocation())) == null) {
											bd.put(tr.locationToString(bo.getLocation()), bo.getLocation());
										}

									}

								}

							}

						break;
					}

					if (forDeleteLoc.equalsIgnoreCase("")) {

					} else {
						Block bee = bd.get(forDeleteLoc).getBlock();
						// dprint.r.printAdmin("bd remove " +
						// tr.locationToString( bee.getLocation() ) + " " +
						// bee.getType().name() + ":" + bee.getData());
						bd.remove(forDeleteLoc);
					}

				}

			}

			DeleteRecursive_Thread newRun = new DeleteRecursive_Thread(bd, world, 0, ee, item, chunklimit, search);
			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, newRun, sleeptime);
			dprint.r.printAll("recalling > " + bd.size() + " " + " avg = "
					+ (int) ((double) blockdo / (System.currentTimeMillis() - startTime + 1)));

		}

	}

	class dewa_mom implements Runnable {
		public int amount;
		public Player player = null;

		@Override
		public void run() {
			int getid = getfreeselect(player);
			if (selectx1[getid] == 0 && selecty1[getid] == 0 && selectz1[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewa " + tr.gettr("please_set_block_1")));

				return;
			}
			if (selectx2[getid] == 0 && selecty2[getid] == 0 && selectz2[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewa " + tr.gettr("please_set_block_2")));

				return;
			}

			// find position

			if (amount == 0) {
				if (player.getItemInHand() == null) {
					player.sendMessage(dprint.r.color("ptdew&dewdd : " + tr.gettr("need_item_in_hand_=_amount")));

					return;
				}
				amount = player.getItemInHand().getAmount();

			}

			player.sendMessage(dprint.r.color("dewa amount = " + amount));

			Block block = player.getLocation().getBlock();

			if (selectblock[getid] == null) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewa " + tr.gettr("dewa_diamond_sword_null")));
			}

			// find diamond
			Block blockd = null;
			int blockdx = 0;
			int blockdy = 0;
			int blockdz = 0;

			for (int dx = -1; dx <= 1; dx++) {
				for (int dy = -2; dy <= 0; dy++) {
					for (int dz = -1; dz <= 1; dz++) {
						blockd = block.getRelative(dx, dy, dz);
						if (blockd.getTypeId() == 57
								|| blockd.getLocation().getBlockX() == selectblock[getid].getLocation().getBlockX()
										&& blockd.getLocation().getBlockY() == selectblock[getid].getLocation()
												.getBlockY()
								&& blockd.getLocation().getBlockZ() == selectblock[getid].getLocation().getBlockZ()) { // diamond
																														// block
							blockdx = dx;
							blockdy = dy + 1;
							blockdz = dz;
							break;
						}
					}
				}
			}

			if (blockdx == 0 && blockdy == 0 && blockdz == 0) {
				player.sendMessage(dprint.r.color(tr.gettr("dewa_diamondsword_null_mean_upper")));

				blockdy = 1;
			}

			player.sendMessage(
					dprint.r.color(tr.gettr("dewa_diamond_axis_=") + blockdx + "," + blockdy + "," + blockdz));
			// after know axis and selected block ... so start copy
			// " + tr.gettr("for") + "amount
			// " + tr.gettr("for") + "all block ... to copy to next axis
			dprint.r.printAll("ptdew&dewdd : '" + player.getName() + "'" + tr.gettr("starting") + " dewa "
					+ player.getItemInHand().getTypeId() + ":" + player.getItemInHand().getData());

			// run
			int mx = 0;
			int lx = 0;

			int my = 0;
			int ly = 0;

			int mz = 0;
			int lz = 0;

			if (selectx1[getid] >= selectx2[getid]) {
				mx = selectx1[getid];
				lx = selectx2[getid];
			} else {
				mx = selectx2[getid];
				lx = selectx1[getid];
			}

			if (selecty1[getid] >= selecty2[getid]) {
				my = selecty1[getid];
				ly = selecty2[getid];
			} else {
				my = selecty2[getid];
				ly = selecty1[getid];
			}

			if (selectz1[getid] >= selectz2[getid]) {
				mz = selectz1[getid];
				lz = selectz2[getid];
			} else {
				mz = selectz2[getid];
				lz = selectz1[getid];
			}

			dewa_thread aer = new dewa_thread();
			aer.player = player;

			aer.blockdx = blockdx;
			aer.blockdy = blockdy;
			aer.blockdz = blockdz;

			aer.amount1 = 1;

			aer.lx = lx;
			aer.ly = ly;
			aer.lz = lz;
			aer.mx = mx;
			aer.my = my;
			aer.mz = mz;
			aer.xlx = lx;
			aer.ylx = ly;
			aer.zlx = lz;
			aer.playerLocation = player.getLocation();
			
			aer.amountloop = 1;
			aer.amount = amount;

			aer.getid = getid;
			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, aer, sleeptime);

		}

	}

	class dewa_thread implements Runnable {
		public int amount;
		public int amount1;

		public int amountloop;
		public int blockdx;
		public int blockdy;
		public int blockdz;

		public int getid;

		public int lx;

		public int ly;
		public int lz;
		public int mx;
		public int my;
		public int mz;
		public Player player = null;
		public Location playerLocation = null;
		public int xlx;
		public int ylx;
		public int zlx;

		@Override
		public void run() {
			
			if (player.getItemInHand().getType() == Material.AIR) {
				dprint.r.printAll(player.getName() + tr.gettr("has cancel dewset"));
				
				return;
			}
			
			boolean arxx = !player.hasPermission(pmaininfinite);

			Block setBlock = null;
			// amountloop = start with 1
			Block hostBlock = null;

			long starttime = System.currentTimeMillis();
			long endtime = 0;

			while (amountloop <= amount) {
				// dprint.r.printAll("amountloop = " + amountloop + " / " +
				// amount);

				// player.getWorld().save();
				int ndx = 0; // now x y z
				if (blockdx != 0) {
					ndx = Math.abs(selectx1[getid] - selectx2[getid]) + 1;
					ndx = ndx * blockdx;
					ndx = ndx * amountloop;
				}

				int ndy = 0; // now x y z
				if (blockdy != 0) {
					ndy = Math.abs(selecty1[getid] - selecty2[getid]) + 1;
					ndy = ndy * blockdy;
					ndy = ndy * amountloop;
				}

				int ndz = 0; // now x y z
				if (blockdz != 0) {
					ndz = Math.abs(selectz1[getid] - selectz2[getid]) + 1;
					ndz = ndz * blockdz;
					ndz = ndz * amountloop;
				}

				player.sendMessage(dprint.r.color("blockdx = " + ndx));
				player.sendMessage(dprint.r.color("blockdy = " + ndy));
				player.sendMessage(dprint.r.color("blockdz = " + ndz));

				// dprint.r.printC("blockdx = " + ndx);
				// dprint.r.printC("blockdy = " + ndy);
				// dprint.r.printC("blockdz = " + ndz);
				
				

				while (amount1 <= 4) { // amount1 // amount1 = start with 1
					// dprint.r.printAll("amount1 = " + amount1);

					while (xlx <= mx) {

						while (ylx <= my) {
							while (zlx <= mz) {

								// dprint.r.printAll (xlx + "," + ylx + "," +
								// zlx + " mx " + mx + "," + my + "," + mz);

								endtime = System.currentTimeMillis();
								if (endtime - starttime > runtime) {

									dewa_thread xgn2 = new dewa_thread();

									xgn2.amount = amount;
									xgn2.blockdx = blockdx;
									xgn2.blockdy = blockdy;
									xgn2.blockdz = blockdz;

									xgn2.amount1 = amount1;

									xgn2.amountloop = amountloop;

									xgn2.lx = lx;
									xgn2.ly = ly;
									xgn2.lz = lz;

									xgn2.getid = getid;
									xgn2.player = player;

									xgn2.mx = mx;
									xgn2.my = my;
									xgn2.mz = mz;

									xgn2.xlx = xlx;
									xgn2.ylx = ylx;
									xgn2.zlx = zlx;
									xgn2.playerLocation = playerLocation;

									dprint.r.printC(
											"dewa  " + tr.gettr("recall") + " " + xlx + " , " + ylx + " , " + zlx);
									dprint.r.printC("low " + lx + " , " + ly + " , " + lz + " high " + mx + "," + my
											+ "," + mz);

									Bukkit.getScheduler().scheduleSyncDelayedTask(ac, xgn2, sleeptime);

									return;
								}

								hostBlock = playerLocation.getWorld().getBlockAt(xlx, ylx, zlx);

								if (hostBlock.getY() + ndy > 253 || hostBlock.getY() + ndy < 1) {
									zlx++;
									// dprint.r.printAll("out of range y");
									continue;
								}

								setBlock = hostBlock.getWorld().getBlockAt(hostBlock.getX() + ndx,
										hostBlock.getY() + ndy, hostBlock.getZ() + ndz);
								/*
								 * if (blockd.getTypeId() == 0) { continue; }
								 */

								if (amount1 == 1 || amount1 == 3) { // if first
																	// round ...
																	// only
									// block
									if (hostBlock.getType().isBlock() == false) {
										zlx++;
										// dprint.r.printAll("first is not a
										// block");
										continue;
									}
									// blockd.setTypeId(0);

									if (arxx)
										if (setBlock.getTypeId() != hostBlock.getTypeId()
												|| setBlock.getData() != hostBlock.getData())
											if (decreseitem1(player, hostBlock.getTypeId(), hostBlock.getData(),
													false) == false && hostBlock.getTypeId() != 0) {
												player.sendMessage(dprint.r
														.color("ptdew&dewdd : " + tr.gettr("don't_have_enough_item")));
												player.sendMessage(dprint.r.color("block > " + hostBlock.getTypeId()
														+ "," + hostBlock.getData()));
												return;
											}
								} else { // if secord round ... only not block
											// block
									if (hostBlock.getType().isBlock() == true) {
										zlx++;
										// dprint.r.printAll("second time is a
										// block");
										continue;
									}
									// blockd.setTypeId(0);

									if (arxx)
										if (setBlock.getTypeId() != hostBlock.getTypeId()
												|| setBlock.getData() != hostBlock.getData())
											if (decreseitem1(player, hostBlock.getTypeId(), hostBlock.getData(),
													false) == false && hostBlock.getTypeId() != 0) {
												player.sendMessage(dprint.r
														.color("ptdew&dewdd : " + tr.gettr("don't_have_enough_item")));
												player.sendMessage(dprint.r.color("block > " + hostBlock.getTypeId()
														+ "," + hostBlock.getData()));
												return;
											}
								}

								setBlock = hostBlock.getWorld().getBlockAt(hostBlock.getX() + ndx,
										hostBlock.getY() + ndy, hostBlock.getZ() + ndz);
								if (cando_all(setBlock, player, "dewset") == false)
									return;

								setBlock.setTypeIdAndData(hostBlock.getTypeId(), hostBlock.getData(), false);

								// dprint.r.printAll ("comple " + xlx + "," +
								// ylx + "," + zlx + " mx " + mx + "," + my +
								// "," + mz);

								if (amount1 == 3 && player.hasPermission(pmainalsocopyinventoryblockwhenyouusedewset)) {

									switch (hostBlock.getType()) {
									case CHEST:
									case TRAPPED_CHEST:

										Chest hostChest = (Chest) hostBlock.getState();
										Chest setChest = (Chest) setBlock.getState();

										for (ItemStack itm : hostChest.getInventory().getContents()) {
											if (itm == null) {
												continue;
											}

											setChest.getInventory().addItem(itm);
											continue;

										}

										setChest.update(true);

										break;
									case DISPENSER:

										Dispenser hostDispenser = (Dispenser) hostBlock.getState();
										Dispenser setDispenser = (Dispenser) setBlock.getState();

										for (ItemStack itm : hostDispenser.getInventory().getContents()) {
											if (itm == null) {
												continue;
											}

											setDispenser.getInventory().addItem(itm);
											continue;

										}

										setDispenser.update(true);
										break;

									case HOPPER:
										Hopper hostHopper = (Hopper) hostBlock.getState();
										Hopper setHopper = (Hopper) setBlock.getState();

										for (ItemStack itm : hostHopper.getInventory().getContents()) {
											if (itm == null) {
												continue;
											}

											setHopper.getInventory().addItem(itm);
											continue;

										}

										setHopper.update(true);

										break;

									case DROPPER:

										Dropper hostDropper = (Dropper) hostBlock.getState();
										Dropper setDropper = (Dropper) setBlock.getState();

										for (ItemStack itm : hostDropper.getInventory().getContents()) {
											if (itm == null) {
												continue;
											}

											setDropper.getInventory().addItem(itm);
											continue;

										}

										setDropper.update(true);

										break;
									}

								}

								zlx++;
							} // z
							zlx = lz;

							ylx++;
						} // y
						ylx = ly;

						xlx++;
					} // x
					xlx = lx;

					amount1++;
				} // amount 1
				amount1 = 1;

				amountloop++;
			} // amount loop

			player.sendMessage(dprint.r.color("ptdew&dewdd : dewA " + tr.gettr(tr.gettr("done"))));
			dprint.r.printAll("ptdew&dewdd : " + player.getName() + " > dewa " + tr.gettr(tr.gettr("done")));
		}
	}

	class dewbuy_class implements Runnable {

		public boolean isok = false;
		private Player player;

		public dewbuy_class(Player player) {
			this.player = player;
		}

		@Override
		public void run() {
			

			int getid = getfreeselect(player);
			if (selectx1[getid] == 0 && selecty1[getid] == 0 && selectz1[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewbuy " + tr.gettr("please_set_block_1")));
				isok = false;
				return;
			}
			if (selectx2[getid] == 0 && selecty2[getid] == 0 && selectz2[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewbuy " + tr.gettr("please_set_block_2")));

				isok = false;
				return;
			}

			int countblock = -1;

			if (player.hasPermission(pmaindewbuynotcount) == false) {
				countblock = getselectblockforbuy(getid, player);
				if (countblock < 27) {
					player.sendMessage(dprint.r.color("ptdew&dewdd : " + tr.gettr("dewbuy_smallest_block_is_27")));
					isok = false;
					return;
				}

			}

			RSWorld worldid = getWorld(player.getWorld().getName());

			if (getProtectid(player.getLocation().getBlock(), worldid) >= 0
					&& player.hasPermission(pmaindewbuyreplace) == false) {
				player.sendMessage(
						dprint.r.color("ptdew&dewdd : " + tr.gettr("can't_dewbuy_replace_another_home_zone")));
				isok = false;
				return;
			}

			player.sendMessage(dprint.r.color("ptdew&dewdd : Block 1 = (" + selectx1[getid] + "," + selecty1[getid]
					+ "," + selectz1[getid] + ") to (" + selectx2[getid] + "," + selecty2[getid] + "," + selectz2[getid]
					+ ") = " + countblock));

			if (countblock == -1) {
				countblock = 1;
				player.sendMessage(dprint.r.color("countblock == -1 , but admin we change it to 1"));
			}

			/*
			 * double buymoneyp = countblock
			 * api_private.dewddprivate.buy1blockmoney;
			 * 
			 * player.sendMessage(dprint.r.color("ptdew&dewdd :" +
			 * tr.gettr("buy") + "'" + countblock + "' use money = " +
			 * buymoneyp));
			 * 
			 * try { if (Economy.getMoney(player.getName()) < buymoneyp) {
			 * 
			 * player.sendMessage(dprint.r.color("ptdew&dewdd : " +
			 * tr.gettr("don't_have_enough_money") + " for" + tr.gettr("buy") +
			 * "these area... = " + (Economy.getMoney(player.getName()) -
			 * buymoneyp)));
			 * 
			 * isok = false; return; }
			 * 
			 * player.sendMessage(dprint.r.color("ptdew&dewdd : " +
			 * tr.gettr("dewbuy_have_enough_money_please_wait")));
			 * 
			 * dprint.r.printAll("ptdew&dewdd : '" + player.getName() + "'" +
			 * tr.gettr("starting") + " dewbuy " +
			 * player.getItemInHand().getTypeId() + ":" +
			 * player.getItemInHand().getData());
			 * 
			 * player.sendMessage(dprint.r.color("ptdew&dewdd : " +
			 * Economy.getMoney(player.getName()) + " - " + buymoneyp + " = " +
			 * (Economy.getMoney(player.getName()) - buymoneyp)));
			 * 
			 * Economy.setMoney(player.getName(),
			 * Economy.getMoney(player.getName()) - buymoneyp);
			 * 
			 * } catch (UserDoesNotExistException | NoLoanPermittedException
			 * err) { dprint.r.printAll("error economy while dewbuy " +
			 * err.getCause().getMessage()); }
			 */

			RSData tmprs = worldid.rs.get(getid);
			int x1 = tmprs.x1;
			int x2 = tmprs.x2;

			int y1 = tmprs.y1;
			int y2 = tmprs.y2;

			int z1 = tmprs.z1;
			int z2 = tmprs.z2;

			player.sendMessage(dprint.r.color("x" + "," + x1 + "," + y1 + "," + z1 + " to "

					+ "," + x2 + "," + y2 + "," + z2));

			player.sendMessage(dprint.r.color(tr.gettr("dewbuy_before_add_sign_world") + rsWorld.size()
					+ tr.gettr("sign_max") + (worldid.rs.size())));

			RSData newrs = new RSData();
			newrs.p = new String[FWMaxPlayer];

			newrs.x1 = selectx1[getid];
			newrs.y1 = selecty1[getid];
			newrs.z1 = selectz1[getid];

			newrs.x2 = selectx2[getid];
			newrs.y2 = selecty2[getid];
			newrs.z2 = selectz2[getid];

			newrs.p[0] = player.getName();

			for (int gggg = 1; gggg < FWMaxPlayer; gggg++) {
				newrs.p[gggg] = "null";
			}

			worldid.rs.add(newrs);
			player.sendMessage(dprint.r.color(tr.gettr("dewbuy_after_add_sign_world") + rsWorld.size()
					+ tr.gettr("sign_max") + (worldid.rs.size())));

			savesignfile(worldid);
			loadsignfile();
			dprint.r.printAll("ptdew&dewdd : " + player.getName() + " " + tr.gettr("buy") + tr.gettr(tr.gettr("done")));
			isok = true;
			dprint.r.printAll(tr.gettr("dewbuy_class_is_ok") + isok);
			return;
		}
	}

	class dewbuydelete_c implements Runnable {

		private Player player;

		public dewbuydelete_c(Player player) {
			this.player = player;
			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, this, sleeptime);

		}

		@Override
		public void run() {

			/*
			 * if (player.hasPermission(pmaindewbuydelete) == false) {
			 * player.sendMessage (dprint.r.color(
			 * "ptdew&dewdd : only op can use dewbuydelete"); return; }
			 */

			// find id home

			RSWorld worldid = getWorld(player.getWorld().getName());

			int getid = getProtectid(player.getLocation().getBlock(), worldid);
			if (getid == -1) {
				player.sendMessage(
						dprint.r.color("ptdew&dewdd : " + tr.gettr("dewbuydelete_this_zone_don't_have_protection")));
				return;
			}

			if (player.hasPermission(pmaindewbuydelete) == false) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : " + tr.gettr("you_don't_have_permission")
						+ tr.gettr("for") + pmaindewbuydelete));
				return;
			}

			RSData tmprs = worldid.rs.get(getid);
			int x1 = tmprs.x1;
			int x2 = tmprs.x2;

			int y1 = tmprs.y1;
			int y2 = tmprs.y2;

			int z1 = tmprs.z1;
			int z2 = tmprs.z2;

			if (tmprs.p[0].equalsIgnoreCase(player.getName()) == false
					&& player.hasPermission(pmaindewbuydelete) == false) {
				player.sendMessage(dprint.r.color(tr.gettr("dewbuydelete_this_is_not_your_zone")));
				return;
			}

			dprint.r.printA("ptdew&dewdd : '" + player.getName() + "'" + tr.gettr("starting") + " dewbuydelete "
					+ player.getItemInHand().getTypeId() + ":" + player.getItemInHand().getData());

			dprint.r.printA("before size " + worldid.rs.size());
			worldid.rs.remove(getid);
			dprint.r.printA("after size " + worldid.rs.size());

			savesignfile(worldid);
			dprint.r.printA("save.. size " + worldid.rs.size());
			loadsignfile();
			dprint.r.printA("reload size " + worldid.rs.size());

			dprint.r.printAll("ptdew&dewdd : " + player.getName() + " dewbuydelete " + tr.gettr(tr.gettr("done")));
		}
	}

	class dewbuyzone_c implements Runnable {
		private Block block2;
		private Player player;

		public dewbuyzone_c(Player player, Block block2) {
			this.player = player;
			this.block2 = block2;
			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, this, sleeptime);
		}

		@Override
		public void run() {
			

			RSWorld worldid = getWorld(player.getWorld().getName());
			int getid = getProtectid(block2, worldid);
			if (getid == -1) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : " + tr.gettr("this_area_don't_have_protect")));
				return;
			}

			RSData tmprs = worldid.rs.get(getid);
			int x1 = tmprs.x1;
			int x2 = tmprs.x2;

			int y1 = tmprs.y1;
			int y2 = tmprs.y2;

			int z1 = tmprs.z1;
			int z2 = tmprs.z2;

			// String abab = dewsignname[homeid][18] ;
			if (tmprs.p[0].equalsIgnoreCase(Constant_Protect.flag_sell) == true) {
				dprint.r.printAll("ptdew&dewdd : '" + player.getName() + "'" + tr.gettr("starting") + " dewbuyzone "
						+ player.getItemInHand().getTypeId() + ":" + player.getItemInHand().getData());

				int mon = Integer.parseInt(tmprs.p[1]);
				player.sendMessage(dprint.r.color("ptdew&dewdd : " + tr.gettr("dewbuyzone_thiszonepriceis") + mon));

				try {
					if (Economy.getMoney(player.getName()) < mon) {
						player.sendMessage(dprint.r.color("ptdew&dewdd : " + tr.gettr("don't_have_enough_money")
								+ " for" + tr.gettr("buy") + "this zone > " + mon));
						return;
					}
				} catch (UserDoesNotExistException e) {

					e.printStackTrace();
				}

				try {
					Economy.subtract(player.getName(), mon);
				} catch (UserDoesNotExistException | NoLoanPermittedException e) {

					e.printStackTrace();
				}

				tmprs.p = new String[FWMaxPlayer];

				for (int g = 0; g < 20; g++) {
					tmprs.p[g] = "null";
				}

				tmprs.p[0] = player.getName();

				dprint.r.printAll("ptdew&dewdd : " + player.getName() + " dewbuyzone " + tr.gettr("complete") + "...");
				savesignfile(worldid);

			} else {
				player.sendMessage(dprint.r.color("ptdew&dewdd : " + tr.gettr("dewbuyzone_thiszone_not_for_sell")));
				return;
			}
		}
	}

	class dewcopy_c_mom implements Runnable {
		private Player player;

		public dewcopy_c_mom(Player player) {
			this.player = player;
		}

		@Override
		public void run() {
			boolean arxx = !player.hasPermission(pmaininfinite);

			int getid = getfreeselect(player);
			if (selectx1[getid] == 0 && selecty1[getid] == 0 && selectz1[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewcopy " + tr.gettr("please_set_block_1")));

				return;
			}
			if (selectx2[getid] == 0 && selecty2[getid] == 0 && selectz2[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewcopy " + tr.gettr("please_set_block_2")));

				return;

			}

			boolean xc = false;
			boolean yc = false;
			boolean zc = false;

			// x
			if (selectx1[getid] <= selectx2[getid]) {
				if (player.getLocation().getBlockX() >= selectx1[getid]
						&& player.getLocation().getBlockX() <= selectx2[getid]) {
					xc = true;
				}
			} else if (player.getLocation().getBlockX() <= selectx1[getid]
					&& player.getLocation().getBlockX() >= selectx2[getid]) {
				xc = true;
			}

			// y
			if (selecty1[getid] <= selecty2[getid]) {
				if (player.getLocation().getBlockY() >= selecty1[getid]
						&& player.getLocation().getBlockY() <= selecty2[getid]) {
					yc = true;
				}
			} else if (player.getLocation().getBlockY() <= selecty1[getid]
					&& player.getLocation().getBlockY() >= selecty2[getid]) {
				yc = true;
			}

			// z
			if (selectz1[getid] <= selectz2[getid]) {
				if (player.getLocation().getBlockY() >= selectz1[getid]
						&& player.getLocation().getBlockY() <= selectz2[getid]) {
					zc = true;
				}
			} else if (player.getLocation().getBlockY() <= selectz1[getid]
					&& player.getLocation().getBlockY() >= selectz2[getid]) {
				zc = true;
			}

			if ((xc == true && yc == true && zc == true) == true) {
				player.sendMessage(
						dprint.r.color("ptdew&dewdd : " + tr.gettr("dewcopy_can't_run_cuz_you_stand_on_source_place")));
				return;
			}

			dprint.r.printAll("ptdew&dewdd : '" + player.getName() + "'" + tr.gettr("starting") + " dewcopy "
					+ player.getItemInHand().getTypeId() + ":" + player.getItemInHand().getData());
			// grab mxlx
			int mx = 0;
			int lx = 0;
			int my = 0;
			int ly = 0;
			int mz = 0;
			int lz = 0;

			if (selectx1[getid] >= selectx2[getid]) {
				mx = selectx1[getid];
				lx = selectx2[getid];
			} else {
				mx = selectx2[getid];
				lx = selectx1[getid];
			}

			if (selecty1[getid] >= selecty2[getid]) {
				my = selecty1[getid];
				ly = selecty2[getid];
			} else {
				my = selecty2[getid];
				ly = selecty1[getid];
			}

			if (selectz1[getid] >= selectz2[getid]) {
				mz = selectz1[getid];
				lz = selectz2[getid];
			} else {
				mz = selectz2[getid];
				lz = selectz1[getid];
			}

			dewcopy_thread aer = new dewcopy_thread(player, mx, my, mz, lx, ly, lz, lx, ly, lz, 1, selectx1[getid],
					selecty1[getid], selectz1[getid], selectworldname[getid], player.getLocation().clone());

			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, aer, sleeptime);

			// grab mxlx

		}
	}

	class dewcopy_thread implements Runnable {

		private int lx = 0;
		private int ly = 0;
		private int lz = 0;
		private int mx = 0;

		private int my = 0;
		private int mz = 0;
		private Player player = null;

		private int xlx = 0;
		private int ylx = 0;
		private int zlx = 0;
		private int amountloop = 1;
		private int selectx1 = 0;
		private int selecty1 = 0;
		private int selectz1 = 0;
		private String selectworldname = "";
		private Location playerLocation;

		public dewcopy_thread(Player player, int mx, int my, int mz, int lx, int ly, int lz, int xlx, int ylx, int zlx,
				int amountloop, int selectx1, int selecty1, int selectz1, String selectworldname,
				Location playerLocation) {
			this.player = player;
			this.mx = mx;
			this.my = my;
			this.mz = mz;
			this.lx = lx;
			this.ly = ly;
			this.lz = lz;
			this.xlx = xlx;
			this.ylx = ylx;
			this.zlx = zlx;
			this.amountloop = amountloop;
			this.selectx1 = selectx1;
			this.selecty1 = selecty1;

			this.selectz1 = selectz1;

			this.selectworldname = selectworldname;
			this.playerLocation = playerLocation;

			/*
			 * player.sendMessage("l " + lx + "," + ly + "," + lz + " ... m " +
			 * mx + "," + my + "," + mz + " = " + playerLocation.getX() + "," +
			 * playerLocation.getY() + "," + playerLocation.getZ() + " world " +
			 * selectworldname);
			 */
		}

		@Override
		public void run() {
			if (player.getItemInHand().getType() == Material.AIR) {
				dprint.r.printAll(player.getName() + tr.gettr("has cancel dewset"));
				
				return;
			}
			
			long starttime = System.currentTimeMillis();
			long endtime = 0;

			Block hostBlock = null;
			boolean arxx = !player.hasPermission(pmaininfinite);

			while (xlx <= mx) {
				while (ylx <= my) {
					while (zlx <= mz) {

						endtime = System.currentTimeMillis();
						if (endtime - starttime > runtime) {

							dewcopy_thread xgn2 = new dewcopy_thread(player, mx, my, mz, lx, ly, lz, xlx, ylx, zlx,
									amountloop, selectx1, selecty1, selectz1, selectworldname, playerLocation);

							dprint.r.printC(
									"time out dewcopy  " + tr.gettr("recall") + " " + xlx + " , " + ylx + " , " + zlx);
							dprint.r.printC("low " + lx + " , " + ly + " , " + lz + " high " + mx + "," + my + "," + mz
									+ " amountloop " + amountloop);

							Bukkit.getScheduler().scheduleSyncDelayedTask(ac, xgn2, sleeptime);

							return;
						}

						hostBlock = Bukkit.getWorld(selectworldname).getBlockAt(xlx, ylx, zlx);

						if (amountloop == 1 || amountloop == 3) { // if first
																	// round ...
																	// only
																	// block
							if (hostBlock.getType().isBlock() == false) {
								zlx++;
								continue;
							}
						}

						if (amountloop == 2 || amountloop == 4) {
							if (hostBlock.getType().isBlock() == true) {
								zlx++;
								continue;
							}
						}

						Block setBlock = playerLocation.getBlock().getRelative(hostBlock.getX() - selectx1,
								hostBlock.getY() - selecty1, hostBlock.getZ() - selectz1);

						if (cando_all(setBlock, player, "dewset") == false)
							return;

						if (arxx)
							if (decreseitem1(player, hostBlock.getTypeId(), hostBlock.getData(), true) == false) {
								player.sendMessage(
										dprint.r.color("ptdew&dewdd : dewcopy " + tr.gettr("don't_have_enough_item")
												+ hostBlock.getTypeId() + ":" + hostBlock.getData()));
								return;
							}

						// if (setBlock.getType() != hostBlock.getType() &&
						// setBlock.getData() != hostBlock.getData()) {
						setBlock.setTypeIdAndData(hostBlock.getTypeId(), hostBlock.getData(), false);
						// }

						if (hostBlock.getType() == Material.SIGN_POST || hostBlock.getType() == Material.WALL_SIGN) {

							Sign hostSign = (Sign) hostBlock.getState();
							Sign setSign = (Sign) setBlock.getState();

							for (int i = 0; i < 4; i++)
								setSign.setLine(i, hostSign.getLine(i));

							setSign.update(true);
						}

						if (amountloop == 3 && player.hasPermission(pmainalsocopyinventoryblockwhenyouusedewset)) {

							switch (hostBlock.getType()) {
							case CHEST:
							case TRAPPED_CHEST:

								Chest hostChest = (Chest) hostBlock.getState();
								Chest setChest = (Chest) setBlock.getState();

								for (ItemStack itm : hostChest.getInventory().getContents()) {
									if (itm == null) {
										continue;
									}

									setChest.getInventory().addItem(itm);

									continue;

								}

								setChest.update(true);

								break;
							case DISPENSER:

								Dispenser hostDispenser = (Dispenser) hostBlock.getState();
								Dispenser setDispenser = (Dispenser) setBlock.getState();

								for (ItemStack itm : hostDispenser.getInventory().getContents()) {
									if (itm == null) {
										continue;
									}

									setDispenser.getInventory().addItem(itm);
									continue;

								}

								setDispenser.update(true);
								break;

							case HOPPER:
								Hopper hostHopper = (Hopper) hostBlock.getState();
								Hopper setHopper = (Hopper) setBlock.getState();

								for (ItemStack itm : hostHopper.getInventory().getContents()) {
									if (itm == null) {
										continue;
									}

									setHopper.getInventory().addItem(itm);
									continue;

								}

								setHopper.update(true);

								break;

							case DROPPER:

								Dropper hostDropper = (Dropper) hostBlock.getState();
								Dropper setDropper = (Dropper) setBlock.getState();

								for (ItemStack itm : hostDropper.getInventory().getContents()) {
									if (itm == null) {
										continue;
									}

									setDropper.getInventory().addItem(itm);
									continue;

								}

								setDropper.update(true);

								break;
							}

						}

						zlx++;
					}
					zlx = lz;

					ylx++;
				}
				ylx = ly;

				xlx++;
			}

			xlx = lx;

			amountloop++;

			if (amountloop <= 4) {
				dewcopy_thread xgn2 = new dewcopy_thread(player, mx, my, mz, lx, ly, lz, xlx, ylx, zlx, amountloop,
						selectx1, selecty1, selectz1, selectworldname, playerLocation);

				dprint.r.printC("dewcopy  " + tr.gettr("recall") + " " + xlx + " , " + ylx + " , " + zlx);
				dprint.r.printC("low " + lx + " , " + ly + " , " + lz + " high " + mx + "," + my + "," + mz
						+ " amountloop " + amountloop);

				Bukkit.getScheduler().scheduleSyncDelayedTask(ac, xgn2, sleeptime);
				return;
			}

			dprint.r.printAll("ptdew&dewdd : dewcopy " + tr.gettr("done") + " : " + player.getName());
		}
	}

	class dewdown_c implements Runnable {
		private ArrayList<IDDataType> item;
		private Player player;

		public dewdown_c(Player player, ArrayList<IDDataType> item) {
			this.player = player;
			this.item = item;

			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, this, sleeptime);
		}

		@Override
		public void run() {

			Block block = player.getLocation().getBlock().getRelative(0, -2, 0);
			if (isprotectitemid(block.getType()) == true)
				return;

			if (cando_all(block, player, "dewset") == false) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewdown " + tr.gettr("this_is_not_your_zone")));
				return;
			}

			addItemIfItemIsZero(item, player);
			if (item.size() == 0) {
				return;
			}
			int handid = item.get(0).id;
			byte handdata = item.get(0).data;

			dprint.r.printC("ptdew&dewdd : '" + player.getName() + "'" + tr.gettr("starting") + " dewdown " + handid
					+ ":" + handdata);

			block.setTypeId(handid);
			block.setData(handdata);

			if (decreseitem1(player, handid, handdata, true) == false) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewdown " + tr.gettr("don't_have_enough_item")));
				return;
			}

			dprint.r.printC("ptdew&dewdd : dewdown " + player.getName() + " " + tr.gettr("complete"));
		}
	}

	class dewextend_c implements Runnable {
		private Player player;

		public dewextend_c(Player player) {
			this.player = player;
			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, this, sleeptime);
		}

		@Override
		public void run() {
			player.sendMessage(dprint.r.color("ptdew&dewdd : dewextend" + tr.gettr("starting")));
			int getid = getfreeselect(player);
			selecty1[getid] = 0;
			selecty2[getid] = 255;

			player.sendMessage(dprint.r.color(
					"ptdew&dewdd : selected area = (" + selectx1[getid] + "," + selecty1[getid] + "," + selectz1[getid]
							+ ") to (" + selectx2[getid] + "," + selecty2[getid] + "," + selectz2[getid] + ") = "));

			player.sendMessage(dprint.r.color("ptdew&dewdd : dewextend " + tr.gettr("complete") + "d"));
		}
	}

	class dewselectcube_c implements Runnable {
		private Player player;
		private int rad;

		public dewselectcube_c(Player player, int rad) {
			this.player = player;
			this.rad = rad;
			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, this, sleeptime);
		}

		@Override
		public void run() {
			player.sendMessage(dprint.r.color("ptdew&dewdd : dew select cube" + tr.gettr("starting")));
			int getid = getfreeselect(player);

			selecty1[getid] = player.getLocation().getBlockY();
			selecty2[getid] = player.getLocation().getBlockY();

			selectx1[getid] = player.getLocation().getBlockX();
			selectx2[getid] = player.getLocation().getBlockX();

			selectz1[getid] = player.getLocation().getBlockZ();
			selectz2[getid] = player.getLocation().getBlockZ();

			selecty1[getid] -= rad;
			selecty2[getid] += rad;
			selectx1[getid] -= rad;
			selectx2[getid] += rad;
			selectz1[getid] -= rad;
			selectz2[getid] += rad;

			player.sendMessage(dprint.r.color(
					"ptdew&dewdd : selected area = (" + selectx1[getid] + "," + selecty1[getid] + "," + selectz1[getid]
							+ ") to (" + selectx2[getid] + "," + selecty2[getid] + "," + selectz2[getid] + ") = "));

			player.sendMessage(dprint.r.color("ptdew&dewdd : dew select cube " + tr.gettr("complete") + "d"));
		}
	}

	// Bigdigthread

	class dewselectprotect_c implements Runnable {
		private Player player;

		public dewselectprotect_c(Player player) {
			this.player = player;
			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, this, sleeptime);
		}

		@Override
		public void run() {
			player.sendMessage(dprint.r.color("ptdew&dewdd : dew select protect" + tr.gettr("starting")));
			Block block = player.getLocation().getBlock();

			RSWorld worldid = getWorld(player.getWorld().getName());

			int getid = getProtectid(block, worldid);
			if (getid == -1) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : " + tr.gettr("this_zone_don't_have_protect")));
				return;
			}

			int getid2 = getfreeselect(player);

			RSData tmprs = worldid.rs.get(getid2);
			int x1 = tmprs.x1;
			int y1 = tmprs.y1;
			int z1 = tmprs.z1;

			int x2 = tmprs.x2;
			int y2 = tmprs.y2;
			int z2 = tmprs.z2;

			selectx1[getid2] = x1;
			selectx2[getid2] = x2;

			selecty1[getid2] = y1;
			selecty2[getid2] = y2;

			selectz1[getid2] = z1;
			selectz2[getid2] = z2;

			player.sendMessage(dprint.r.color("ptdew&dewdd : selected area = (" + selectx1[getid2] + ","
					+ selecty1[getid2] + "," + selectz1[getid2] + ") to (" + selectx2[getid2] + "," + selecty2[getid2]
					+ "," + selectz2[getid2] + ") = "));

			player.sendMessage(dprint.r.color("ptdew&dewdd : dew select protect " + tr.gettr("complete") + "d"));
		}
	}

	class dewset_mom implements Runnable {
		private ArrayList<IDDataType> item;
		private ArrayList<IDDataType> itemSearch;
		private boolean invert;
		private Player player = null;

		public dewset_mom(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch, boolean invert) {
			this.player = player;
			this.item = item;
			this.itemSearch = itemSearch;
			this.invert = invert;
		}

		@Override
		public void run() {

			int lx = 0;
			int mx = 0;
			int ly = 0;
			int my = 0;
			int lz = 0;
			int mz = 0;

			int getid = getfreeselect(player);

			if (selectx1[getid] == 0 && selecty1[getid] == 0 && selectz1[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewset " + tr.gettr("please_set_block_1")));
				return;
			}
			if (selectx2[getid] == 0 && selecty2[getid] == 0 && selectz2[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewset " + tr.gettr("please_set_block_2")));
				return;
			}

			// player.sendMessage(dprint.r.color(". " + e1 + "," + e2 + "|" + e3
			// + "," + e4);

			if (invert == false) {
				dprint.r.printAll(
						"ptdew&dewdd : '" + player.getName() + "'" + tr.gettr("starting") +
						" dewset " + IDDataType.arrayListToString(item) + " >_< " + 
								IDDataType.arrayListToString(itemSearch));
			} else {
				dprint.r.printAll(
						"ptdew&dewdd : '" + player.getName() + "'" + tr.gettr("starting") + " dewxet " +
								IDDataType.arrayListToString(item) + " >_< " + 
								IDDataType.arrayListToString(itemSearch));
			}

			if (selectx1[getid] >= selectx2[getid]) {
				mx = selectx1[getid];
				lx = selectx2[getid];
			} else {
				mx = selectx2[getid];
				lx = selectx1[getid];
			}

			if (selecty1[getid] >= selecty2[getid]) {
				my = selecty1[getid];
				ly = selecty2[getid];
			} else {
				my = selecty2[getid];
				ly = selecty1[getid];
			}

			if (selectz1[getid] >= selectz2[getid]) {
				mz = selectz1[getid];
				lz = selectz2[getid];
			} else {
				mz = selectz2[getid];
				lz = selectz1[getid];
			}
			
			Location playerLocation = player.getLocation();

			dewset_thread xgn = new dewset_thread(player, item, itemSearch, invert, mx, my, mz, lx, ly, lz, lx, ly, lz,
					getid, playerLocation);

			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, xgn);

			// > > >

			// run thread

		}
	}

	// skyblock
	// nether
	// invert
	// old_1
	// flat
	// old_2
	// float

	class dewset_thread implements Runnable {
		private ArrayList<IDDataType> item;
		private ArrayList<IDDataType> itemSearch;
		private int getid;
		private boolean invert;
		private int lx;

		private int ly;
		private int lz;
		private int mx;

		private int my;
		private int mz;
		private Player player = null;

		private int xlx;
		private int ylx;
		private int zlx;
		private Location playerLocation ;

		public dewset_thread(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch,
				boolean invert, int mx, int my, int mz, int lx, int ly, int lz, int xlx, int ylx, int zlx, int getid, Location playerLocation) {
			this.player = player;
			this.item = item;
			this.itemSearch = itemSearch;
			this.invert = invert;
			this.playerLocation = playerLocation;

			this.mx = mx;
			this.my = my;
			this.mz = mz;
			this.lx = lx;
			this.ly = ly;
			this.lz = lz;

			this.xlx = xlx;
			this.ylx = ylx;
			this.zlx = zlx;

			this.getid = getid;

		}

		@Override
		public void run() {

			if (player.getItemInHand().getType() == Material.AIR) {
				dprint.r.printAll(player.getName() + tr.gettr("has cancel dewset"));
				
				return;
			}

			boolean arxx = !player.hasPermission(pmaininfinite);

			boolean pppp = player.hasPermission(pmaindelete) == false;

			Block blb = null;

			long starttime = System.currentTimeMillis();
			long endtime = 0;

			while (xlx <= mx) {

				while (ylx <= my) {
					while (zlx <= mz) {

						blb = playerLocation.getWorld().getBlockAt(xlx, ylx, zlx);

						endtime = System.currentTimeMillis();
						if (endtime - starttime > runtime) {

							dewset_thread xgn2 =

							new dewset_thread(player, item, itemSearch, invert, mx, my, mz, lx, ly, lz, xlx, ylx, zlx,
									getid,playerLocation);

							dprint.r.printC("dewset  " + tr.gettr("recall") + " " + xlx + " , " + ylx + " , " + zlx);
							dprint.r.printC(
									"low " + lx + " , " + ly + " , " + lz + " high " + mx + "," + my + "," + mz +  " world " + playerLocation.getWorld().getName());

							Bukkit.getScheduler().scheduleSyncDelayedTask(ac, xgn2, sleeptime);

							return;
						}

						if (itemSearch.size() > 0) {
							boolean gen = IDDataType.isThisItemOnTheList(itemSearch, blb.getTypeId(), blb.getData());
							if (gen == false && invert == false) {
								zlx++;
								continue;
							}
							if (gen == true && invert == true) {
								zlx++;
								continue;
							}
						}

						if (cando_all(blb, player, "dewset") == false)
							return;

						int randid = rnd.nextInt(item.size());
						int id = item.get(randid).id;
						byte data = item.get(randid).data;
						
						if (blb.getTypeId() == id && blb.getData() == data){
							zlx++;
							continue;
						}

						if (id == 0) { // if delete
							if (pppp) {
								player.sendMessage(
										dprint.r.color(tr.gettr("don't_have_permission_for_access_delete_command")));
								return;
							}

							blb.setTypeId(0);

						}

						else { // if place
							if (arxx)
								if (decreseitem1(player, id, data, true) == false) {
									player.sendMessage(
											dprint.r.color("ptdew&dewdd : " + tr.gettr("don't_have_enough_item")));
									return;
								}

							blb.setTypeIdAndData(id, data, false);
							//

						}

						zlx++;
					} // z
					zlx = lz;

					ylx++;
				} // y
				ylx = ly;

				xlx++;
			} // x
			xlx = lx;

			if (invert == false) {
				dprint.r.printAll("ptdew&dewdd : dewset " + tr.gettr("done") + " : " + player.getName());
			} else {
				dprint.r.printAll("ptdew&dewdd : dewxet " + tr.gettr("done") + " : " + player.getName());
			}
		}
	}

	class dewsetblock_mom implements Runnable {

		private ArrayList<IDDataType> item;
		private ArrayList<IDDataType> itemSearch;
		private boolean isfillmode = false;
		private Player player = null;

		public dewsetblock_mom(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch) {
			this.player = player;
			this.item = item;
			this.itemSearch = itemSearch;
			this.isfillmode = isfillmode;
		}

		@Override
		public void run() {

			int getid = getfreeselect(player);
			if (selectx1[getid] == 0 && selecty1[getid] == 0 && selectz1[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewset(fill)block " + tr.gettr("please_set_block_1")));
				return;
			}
			if (selectx2[getid] == 0 && selecty2[getid] == 0 && selectz2[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewset(fill)block " + tr.gettr("please_set_block_2")));
				return;
			}

			dprint.r.printAll(
					"ptdew&dewdd : '" + player.getName() + "'" + tr.gettr("starting") + " dewset(fill)block " + 
							IDDataType.arrayListToString(item) + " >_< " + 
							IDDataType.arrayListToString(itemSearch));
			int mx = 0;
			int lx = 0;
			int my = 0;
			int ly = 0;
			int mz = 0;
			int lz = 0;

			if (selectx1[getid] >= selectx2[getid]) {
				mx = selectx1[getid];
				lx = selectx2[getid];
			} else {
				mx = selectx2[getid];
				lx = selectx1[getid];
			}

			if (selecty1[getid] >= selecty2[getid]) {
				my = selecty1[getid];
				ly = selecty2[getid];
			} else {
				my = selecty2[getid];
				ly = selecty1[getid];
			}

			if (selectz1[getid] >= selectz2[getid]) {
				mz = selectz1[getid];
				lz = selectz2[getid];
			} else {
				mz = selectz2[getid];
				lz = selectz1[getid];
			}

			Location playerLocation  = player.getLocation();
			dewsetblock_thread aer = new dewsetblock_thread(player, item, itemSearch, mx, my, mz, lx, ly, lz, lx, ly,
					lz, getid, playerLocation);
			// Player player, int handid , byte handdata , int mx , int my , int
			// mz
			// , int lx , int ly , int lz , int xlx , int ylx , int zlx , int
			// getid , boolean isfillmode

			
			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, aer, sleeptime);

		}
	}

	class dewsetblock_thread implements Runnable {

		private int lx = 0;
		private int ly = 0;

		private int lz = 0;
		private int mx = 0;
		private int my = 0;

		private int mz = 0;
		private Player player = null;
		private int xlx = 0;

		private int ylx = 0;

		private int zlx = 0;

		private ArrayList<IDDataType> item;
		private ArrayList<IDDataType> itemSearch;
		private int getid;
		private Location playerLocation;

		public dewsetblock_thread(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch, int mx,
				int my, int mz, int lx, int ly, int lz, int xlx, int ylx, int zlx, int getid , Location playerLocation) {
			this.player = player;
			this.item = item;
			this.itemSearch = itemSearch;
			this.playerLocation = playerLocation;

			this.mx = mx;
			this.my = my;
			this.mz = mz;
			this.lx = lx;
			this.ly = ly;
			this.lz = lz;
			this.xlx = xlx;
			this.ylx = ylx;
			this.zlx = zlx;
			this.getid = getid;

		}

		@Override
		public void run() {
			if (player.getItemInHand().getType() == Material.AIR) {
				dprint.r.printAll(player.getName() + tr.gettr("has cancel dewsetblock"));
				
				return;
			}
			
			long endtime = 0;
			long starttime = System.currentTimeMillis();
			Block blb = null;
			boolean arxx = !player.hasPermission(pmaininfinite);

			boolean t1 = false;
			boolean t2 = false;

			addItemIfItemIsZero(item, player);

			while (xlx <= mx) {
				while (ylx <= my) {
					while (zlx <= mz) {
						t1 = ylx == selecty1[getid] || ylx == selecty2[getid];

						t2 = xlx == selectx1[getid] || zlx == selectz1[getid] || xlx == selectx2[getid]
								|| zlx == selectz2[getid];

						if (!(t1 && t2 || !t1 && xlx == selectx1[getid] && zlx == selectz1[getid]
								|| xlx == selectx2[getid] && zlx == selectz2[getid]
								|| xlx == selectx1[getid] && zlx == selectz2[getid]
								|| xlx == selectx2[getid] && zlx == selectz1[getid]

						)

						) {
							zlx++;
							continue;
						}
						endtime = System.currentTimeMillis();
						if (endtime - starttime > runtime) {

							dewsetblock_thread xgn2 = new dewsetblock_thread(player, item, itemSearch, mx, my, mz, lx,
									ly, lz, xlx, ylx, zlx, getid, playerLocation);

							dprint.r.printC(
									"dewsetblock  " + tr.gettr("recall") + " " + xlx + " , " + ylx + " , " + zlx);
							dprint.r.printC(
									"low " + lx + " , " + ly + " , " + lz + " high " + mx + "," + my + "," + mz);

							Bukkit.getScheduler().scheduleSyncDelayedTask(ac, xgn2, sleeptime);

							return;
						}

						blb = playerLocation.getWorld().getBlockAt(xlx, ylx, zlx);

						/*
						 * if (isfillmode == true) if (blb.getTypeId() != 0) {
						 * zlx++; continue; }
						 */
						if (itemSearch.size() != 0) {
							if (!IDDataType.isThisItemOnTheList(itemSearch, blb.getTypeId(), blb.getData())) {
								zlx++;
								continue;
							}

						} 

						if (cando_all(blb, player, "dewset") == false)
							return;

						// coner

						int randid = rnd.nextInt(item.size());
						int id = item.get(randid).id;
						byte data = item.get(randid).data;

						if (blb.getTypeId() == id && blb.getData() == data){
							zlx++;
							continue;
						}						
						
						if (arxx)
							if (decreseitem1(player, id, data, true) == false) {
								player.sendMessage(
										dprint.r.color("ptdew&dewdd : " + tr.gettr("don't_have_enough_item")));
								return;
							}

						blb.setTypeIdAndData(id, data, false);

						zlx++;
					}
					zlx = lz;

					ylx++;
				}
				ylx = ly;

				xlx++;
			}
			xlx = lx;

			dprint.r.printAll("ptdew&dewdd : dew(set)block " + tr.gettr("done") + " : " + player.getName());
		}
	}

	class dewsetFullSphere_c implements Runnable {
		private ArrayList<IDDataType> item;
		private ArrayList<IDDataType> itemSearch;
		private Player player;
		

		public dewsetFullSphere_c(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch) {
			this.player = player;
			this.item = item;
			this.itemSearch = itemSearch;
		}

		@Override
		public void run() {

			int getid = getfreeselect(player);
			if (selectx1[getid] == 0 && selecty1[getid] == 0 && selectz1[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewfullcircle " + tr.gettr("please_set_block_1")));
				return;
			}
			if (selectx2[getid] == 0 && selecty2[getid] == 0 && selectz2[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewfullcircle " + tr.gettr("please_set_block_2")));
				return;
			}

			double midx = ((double) selectx1[getid] + (double) selectx2[getid]) / 2;
			double midy = ((double) selecty1[getid] + (double) selecty2[getid]) / 2;
			double midz = ((double) selectz1[getid] + (double) selectz2[getid]) / 2;

			if (midx == selectx1[getid] && midy == selecty1[getid] && midz == selectz1[getid]
					|| midx == selectx2[getid] && midy == selecty2[getid] && midz == selectz2[getid]) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : " + tr.gettr("small_circle_can't_run_program")));
				return;
			}

			double temp1 = 0;

			double temp5 = 0;
			double temp2 = 0;
			double temp3 = 0;
			temp1 = Math.pow((double) selectx1[getid] - (double) selectx2[getid], 2);

			temp2 = Math.pow((double) selecty1[getid] - (double) selecty2[getid], 2);

			temp3 = Math.pow((double) selectz1[getid] - (double) selectz2[getid], 2);

			double midty = (selecty1[getid] + selecty2[getid]) / 2;

			double midtx = (selectx1[getid] + selectx2[getid]) / 2;

			double midtz = (selectz1[getid] + selectz2[getid]) / 2;
			temp5 = Math.pow(temp1 + temp2 + temp3, 0.5);

			double midr = temp5 / 3;
			Block blockmid = player.getWorld().getBlockAt((int) midtx, (int) midty, (int) midtz);

			player.sendMessage(dprint.r.color("cir=" + midtx + "," + midty + "," + midtz));
			player.sendMessage(dprint.r.color("R=" + temp5));

			dprint.r.printAll("ptdew&dewdd : '" + player.getName() + "'" + tr.gettr("starting") + " dewfullcircle " + 
					IDDataType.arrayListToString(item) + " >_< " + 
					IDDataType.arrayListToString(itemSearch));

			boolean arxx = !player.hasPermission(pmaininfinite);
			
			for (Block blb : getselectblock(getid, player)) {

				/*
				 * if (PreciousStones.API().canPlace(player,
				 * blb.getLocation())== false) {
				 * player.sendMessage(dprint.r.color (
				 * "ptdew&dewdd :Can't dewfullcircle here (" + blb.getX() + ","
				 * + blb.getY() + "," + blb.getZ() + ")"); continue; }
				 */

				if (blb.getLocation().distance(blockmid.getLocation()) > midr) {
					continue;
				}

				if (itemSearch.size() > 0) {
					if (!IDDataType.isThisItemOnTheList(itemSearch, blb.getTypeId(), blb.getData())) {
						continue;
					}
				} 

				if (cando_all(blb, player, "dewset") == false)
					return;

				int ranslot = rnd.nextInt(item.size());
				int id = item.get(ranslot).id;
				byte data = item.get(ranslot).data;

				
				
				if (blb.getTypeId() == id && blb.getData() == data){
					continue;
				}
				
				if (arxx)
				if (decreseitem1(player, id, data, true) == false) {
					player.sendMessage(dprint.r.color("ptdew&dewdd : " + tr.gettr("don't_have_enough_item")));
					return;
				}
				blb.setTypeIdAndData(id, data, true);
				//
			} // for

			dprint.r.printAll("ptdew&dewdd : dewfullcircle " + tr.gettr("done") + " : " + player.getName());
		}
	}

	class dewsetl_thread implements Runnable {

		private int getid;
		private ArrayList<IDDataType> item;
		private int lx = 0;
		private int ly = 0;
		private int lz = 0;

		private int mx = 0;
		private int my = 0;
		private int mz = 0;

		private Player player = null;
		private int xlx = 0;
		private int ylx = 0;

		private int zlx = 0;
		private Location playerLocation;

		public dewsetl_thread(Player player, ArrayList<IDDataType> item, int mx, int my, int mz, int lx, int ly, int lz,
				int xlx, int ylx, int zlx, int getid,Location playerLocation) {
			this.player = player;
			this.item = item;
			this.mx = mx;
			this.my = my;
			this.mz = mz;
			this.lx = lx;
			this.ly = ly;
			this.lz = lz;
			this.xlx = xlx;
			this.ylx = ylx;
			this.zlx = zlx;
			this.getid = getid;
			this.playerLocation = playerLocation;
		}

		@Override
		public void run() {
			if (player.getItemInHand().getType() == Material.AIR) {
				dprint.r.printAll(player.getName() + tr.gettr("has cancel dewsetLight"));
				
				return;
			}
			
			long starttime = System.currentTimeMillis();
			long endtime = 0;

			Block blb = null;
			boolean ne = false;
			boolean arxx = !player.hasPermission(pmaininfinite);

			addItemIfItemIsZero(item, player);

			while (xlx <= mx) {
				while (ylx <= my) {
					while (zlx <= mz) {

						endtime = System.currentTimeMillis();
						if (endtime - starttime > runtime) {

							dewsetl_thread xgn2 = new dewsetl_thread(player, item, mx, my, mz, lx, ly, lz, xlx, ylx,
									zlx, getid,playerLocation);

							dprint.r.printC("dewsetl  " + tr.gettr("recall") + " " + xlx + " , " + ylx + " , " + zlx);
							dprint.r.printC(
									"low " + lx + " , " + ly + " , " + lz + " high " + mx + "," + my + "," + mz);

							Bukkit.getScheduler().scheduleSyncDelayedTask(ac, xgn2, sleeptime);

							return;
						}

						blb = playerLocation.getWorld().getBlockAt(xlx, ylx, zlx);

						if (blb.getTypeId() != 0) {
							zlx++;
							continue;
						}

						if (blb.getLightLevel() >= 9) {
							zlx++;
							continue;
						}

						ne = false;
						ne = blb.getRelative(-1, 0, 0).getTypeId() != 0;
						ne = ne || blb.getRelative(1, 0, 0).getTypeId() != 0;
						ne = ne || blb.getRelative(0, 0, -1).getTypeId() != 0;
						ne = ne || blb.getRelative(0, 0, 1).getTypeId() != 0;
						ne = ne || blb.getRelative(0, -1, 0).getTypeId() != 0;

						if (ne == false) {
							zlx++;
							continue;
						}

						if (cando_all(blb, player, "dewset") == false)
							return;

						int randid = rnd.nextInt(item.size());
						int id = item.get(randid).id;
						byte data = item.get(randid).data;

						if (arxx)
							if (decreseitem1(player, id, data, true) == false) {
								player.sendMessage(
										dprint.r.color("ptdew&dewdd : " + tr.gettr("don't_have_enough_item")));
								return;
							}

						blb.setTypeIdAndData(id, data, true);

						zlx++;
					}
					zlx = lz;

					ylx++;
				}
				ylx = ly;

				xlx++;
			}
			xlx = lx;

			dprint.r.printAll("ptdew&dewdd : dewsetlight " + tr.gettr("done") + " : " + player.getName());
		}
	}

	class dewsetLight_mom implements Runnable {

		private ArrayList<IDDataType> item;
		private Player player = null;

		public dewsetLight_mom(Player player, ArrayList<IDDataType> item) {
			this.player = player;
			this.item = item;
		}

		@Override
		public void run() {

			int getid = getfreeselect(player);
			if (selectx1[getid] == 0 && selecty1[getid] == 0 && selectz1[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewsetlight " + tr.gettr("please_set_block_1")));
				return;
			}
			if (selectx2[getid] == 0 && selecty2[getid] == 0 && selectz2[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewsetlight " + tr.gettr("please_set_block_2")));
				return;
			}

			dprint.r.printAll("ptdew&dewdd : '" + player.getName() + "'" + tr.gettr("starting") + " dewsetlight " + IDDataType.arrayListToString(item) + " >_< " );
			int mx = 0;
			int lx = 0;
			int my = 0;
			int ly = 0;
			int mz = 0;
			int lz = 0;

			if (selectx1[getid] >= selectx2[getid]) {
				mx = selectx1[getid];
				lx = selectx2[getid];
			} else {
				mx = selectx2[getid];
				lx = selectx1[getid];
			}

			if (selecty1[getid] >= selecty2[getid]) {
				my = selecty1[getid];
				ly = selecty2[getid];
			} else {
				my = selecty2[getid];
				ly = selecty1[getid];
			}

			if (selectz1[getid] >= selectz2[getid]) {
				mz = selectz1[getid];
				lz = selectz2[getid];
			} else {
				mz = selectz2[getid];
				lz = selectz1[getid];
			}

			dewsetl_thread aer = new dewsetl_thread(player, item, mx, my, mz, lx, ly, lz, lx, ly, lz, getid,player.getLocation());
			// Player player, int handid , byte handdata , int mx , int my , int
			// mz
			// , int lx , int ly , int lz , int xlx , int ylx , int zlx , int
			// getid , boolean isfillmode

			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, aer, sleeptime);

		}
	}

	class dewsetroom_mom implements Runnable {

		private ArrayList<IDDataType> item;
		private ArrayList<IDDataType> itemSearch;
		private Player player = null;
	

		public dewsetroom_mom(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch) {
			this.player = player;
			this.item = item;
			this.itemSearch = itemSearch;
		}

		@Override
		public void run() {

			int getid = getfreeselect(player);
			if (selectx1[getid] == 0 && selecty1[getid] == 0 && selectz1[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewset(fill)room " + tr.gettr("please_set_block_1")));
				return;
			}
			if (selectx2[getid] == 0 && selecty2[getid] == 0 && selectz2[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewset(fill)room " + tr.gettr("please_set_block_2")));
				return;
			}

			dprint.r.printAll("ptdew&dewdd : '" + player.getName() + "'" + tr.gettr("starting") + " dewset(fill)room "
					 + IDDataType.arrayListToString(item) + " >_< " + 
						IDDataType.arrayListToString(itemSearch));
			int mx = 0;
			int lx = 0;
			int my = 0;
			int ly = 0;
			int mz = 0;
			int lz = 0;

			if (selectx1[getid] >= selectx2[getid]) {
				mx = selectx1[getid];
				lx = selectx2[getid];
			} else {
				mx = selectx2[getid];
				lx = selectx1[getid];
			}

			if (selecty1[getid] >= selecty2[getid]) {
				my = selecty1[getid];
				ly = selecty2[getid];
			} else {
				my = selecty2[getid];
				ly = selecty1[getid];
			}

			if (selectz1[getid] >= selectz2[getid]) {
				mz = selectz1[getid];
				lz = selectz2[getid];
			} else {
				mz = selectz2[getid];
				lz = selectz1[getid];
			}

			 Location playerLocation = player.getLocation();
			dewsetroom_thread aer = new dewsetroom_thread(player, item, itemSearch, mx, my, mz, lx, ly, lz, lx, ly, lz,
					getid, playerLocation);
			// Player player, int handid , byte handdata , int mx , int my , int
			// mz
			// , int lx , int ly , int lz , int xlx , int ylx , int zlx , int
			// getid , boolean isfillmode

			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, aer, sleeptime);

		}
	}

	class dewsetroom_thread implements Runnable {

		private int getid;
		private ArrayList<IDDataType> item;
		private ArrayList<IDDataType> itemSearch;

		private boolean isfillmode = false;
		private int lx = 0;
		private int ly = 0;

		private int lz = 0;
		private int mx = 0;
		private int my = 0;

		private int mz = 0;
		private Player player = null;
		private int xlx = 0;

		private int ylx = 0;

		private int zlx = 0;
		private Location playerLocation;

		public dewsetroom_thread(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch, int mx,
				int my, int mz, int lx, int ly, int lz, int xlx, int ylx, int zlx, int getid ,Location playerLocation) {
			this.player = player;
			this.isfillmode = isfillmode;
			this.item = item;
			this.itemSearch = itemSearch;
			this.mx = mx;
			this.my = my;
			this.mz = mz;
			this.lx = lx;
			this.ly = ly;
			this.lz = lz;
			this.xlx = xlx;
			this.ylx = ylx;
			this.zlx = zlx;
			this.getid = getid;
			this.playerLocation = playerLocation;
		}

		@Override
		public void run() {
			if (player.getItemInHand().getType() == Material.AIR) {
				dprint.r.printAll(player.getName() + tr.gettr("has cancel dewsetRoom"));
				
				return;
			}
			long starttime = System.currentTimeMillis();
			long endtime = 0;

			boolean arxx = !player.hasPermission(pmaininfinite);

			addItemIfItemIsZero(item, player);
			Block blb = null;

			while (xlx <= mx) {
				while (ylx <= my) {
					while (zlx <= mz) {

						if (!(xlx == selectx1[getid] || xlx == selectz1[getid] || xlx == selectx2[getid]
								|| xlx == selectz2[getid] || zlx == selectx1[getid] || zlx == selectz1[getid]
								|| zlx == selectx2[getid] || zlx == selectz2[getid] || ylx == selecty1[getid]
								|| ylx == selecty2[getid]

						)) {
							zlx++;
							continue;
						}

						endtime = System.currentTimeMillis();
						if (endtime - starttime > runtime) {

							dewsetroom_thread xgn2 = new dewsetroom_thread(player, item, itemSearch, mx, my, mz, lx, ly,
									lz, xlx, ylx, zlx, getid,playerLocation);

							dprint.r.printC(
									"dewsetroom  " + tr.gettr("recall") + " " + xlx + " , " + ylx + " , " + zlx);
							dprint.r.printC(
									"low " + lx + " , " + ly + " , " + lz + " high " + mx + "," + my + "," + mz);

							Bukkit.getScheduler().scheduleSyncDelayedTask(ac, xgn2, sleeptime);

							return;
						}

						blb = playerLocation.getWorld().getBlockAt(xlx, ylx, zlx);

						if (itemSearch.size() > 0) {
							if (!IDDataType.isThisItemOnTheList(itemSearch, blb.getTypeId(), blb.getData())) {
								zlx++;
								continue;
							}
						} 

						if (cando_all(blb, player, "dewset") == false)
							return;

						int randid = rnd.nextInt(item.size());
						int id = item.get(randid).id;
						byte data = item.get(randid).data;

						if (blb.getTypeId() == id && blb.getData() == data){
							zlx ++;
							continue;
						}
						
						if (arxx)
							if (decreseitem1(player, id, data, true) == false) {
								player.sendMessage(
										dprint.r.color("ptdew&dewdd : " + tr.gettr("don't_have_enough_item")));
								return;
							}

						blb.setTypeIdAndData(id, data, false);

						zlx++;
					}
					zlx = lz;

					ylx++;
				}
				ylx = ly;

				xlx++;
			}
			xlx = lx;

			dprint.r.printAll("ptdew&dewdd : dew(set)room " + tr.gettr("done") + " : " + player.getName());
		}
	}

	class dewsetwall_mom implements Runnable {

		private ArrayList<IDDataType> item;
		private ArrayList<IDDataType> itemSearch;
		private Player player = null;

		public dewsetwall_mom(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch) {
			this.player = player;
			this.item = item;
			this.itemSearch = itemSearch;
		}

		@Override
		public void run() {

			int getid = getfreeselect(player);
			if (selectx1[getid] == 0 && selecty1[getid] == 0 && selectz1[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewset(fill)wall " + tr.gettr("please_set_block_1")));
				return;
			}
			if (selectx2[getid] == 0 && selecty2[getid] == 0 && selectz2[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewset(fill)wall " + tr.gettr("please_set_block_2")));
				return;
			}

			dprint.r.printAll("ptdew&dewdd : '" + player.getName() + "'" + tr.gettr("starting") + " dewset(fill)wall "
					 + IDDataType.arrayListToString(item) + " >_< " + 
						IDDataType.arrayListToString(itemSearch));
			int mx = 0;
			int lx = 0;
			int my = 0;
			int ly = 0;
			int mz = 0;
			int lz = 0;

			if (selectx1[getid] >= selectx2[getid]) {
				mx = selectx1[getid];
				lx = selectx2[getid];
			} else {
				mx = selectx2[getid];
				lx = selectx1[getid];
			}

			if (selecty1[getid] >= selecty2[getid]) {
				my = selecty1[getid];
				ly = selecty2[getid];
			} else {
				my = selecty2[getid];
				ly = selecty1[getid];
			}

			if (selectz1[getid] >= selectz2[getid]) {
				mz = selectz1[getid];
				lz = selectz2[getid];
			} else {
				mz = selectz2[getid];
				lz = selectz1[getid];
			}

			dewsetwall_thread aer = new dewsetwall_thread(player, item, itemSearch, mx, my, mz, lx, ly, lz, lx, ly, lz,
					getid,player.getLocation());
			// Player player, int handid , byte handdata , int mx , int my , int
			// mz
			// , int lx , int ly , int lz , int xlx , int ylx , int zlx , int
			// getid , boolean isfillmode

			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, aer, sleeptime);

		}
	}

	class dewsetwall_thread implements Runnable {

		private int getid;
		private ArrayList<IDDataType> item;
		private ArrayList<IDDataType> itemSearch;

		private int lx = 0;
		private int ly = 0;

		private int lz = 0;
		private int mx = 0;
		private int my = 0;

		private int mz = 0;
		private Player player = null;
		private int xlx = 0;

		private int ylx = 0;
		private Location playerLocation;
		private int zlx = 0;

		public dewsetwall_thread(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch, int mx,
				int my, int mz, int lx, int ly, int lz, int xlx, int ylx, int zlx, int getid,Location playerLocation) {
			this.player = player;
			this.item = item;
			this.itemSearch = itemSearch;
			this.mx = mx;
			this.my = my;
			this.mz = mz;
			this.lx = lx;
			this.ly = ly;
			this.lz = lz;
			this.xlx = xlx;
			this.ylx = ylx;
			this.zlx = zlx;
			this.getid = getid;
			this.playerLocation = playerLocation;
		}

		@Override
		public void run() {
			if (player.getItemInHand().getType() == Material.AIR) {
				dprint.r.printAll(player.getName() + tr.gettr("has cancel dewsetWall"));
				
				return;
			}
			
			long starttime = System.currentTimeMillis();
			long endtime = 0;

			Block blb = null;
			boolean arxx = !player.hasPermission(pmaininfinite);

			addItemIfItemIsZero(item, player);

			while (xlx <= mx) {
				while (ylx <= my) {
					while (zlx <= mz) {

						if (!(xlx == selectx1[getid] || zlx == selectz1[getid] || xlx == selectx2[getid]
								|| zlx == selectz2[getid] || xlx == selectx1[getid] || zlx == selectz1[getid]
								|| xlx == selectx2[getid] || zlx == selectz2[getid])) {
							zlx++;
							continue;
						}

						endtime = System.currentTimeMillis();
						if (endtime - starttime > runtime) {

							dewsetwall_thread xgn2 = new dewsetwall_thread(player, item, itemSearch, mx, my, mz, lx, ly,
									lz, xlx, ylx, zlx, getid,playerLocation);

							dprint.r.printC(
									"dewsetwall  " + tr.gettr("recall") + " " + xlx + " , " + ylx + " , " + zlx);
							dprint.r.printC(
									"low " + lx + " , " + ly + " , " + lz + " high " + mx + "," + my + "," + mz);

							Bukkit.getScheduler().scheduleSyncDelayedTask(ac, xgn2, sleeptime);

							return;
						}

						blb = playerLocation.getWorld().getBlockAt(xlx, ylx, zlx);

						if (itemSearch.size() > 0) {
							if (!IDDataType.isThisItemOnTheList(itemSearch, blb.getTypeId(), blb.getData())) {
								zlx++;
								continue;
							}
						} 

						if (cando_all(blb, player, "dewset") == false)
							return;

						int randid = rnd.nextInt(item.size());
						int id = item.get(randid).id;
						byte data = item.get(randid).data;

						if (blb.getTypeId() == id && blb.getData() == data){
							zlx ++;
							continue;
						}
						
						if (arxx)
							if (decreseitem1(player, id, data, true) == false) {
								player.sendMessage(
										dprint.r.color("ptdew&dewdd : " + tr.gettr("don't_have_enough_item")));
								return;
							}
						blb.setTypeIdAndData(id, data, false);
						//

						zlx++;
					}
					zlx = lz;

					ylx++;
				}
				ylx = ly;

				xlx++;
			}
			xlx = lx;

			dprint.r.printAll("ptdew&dewdd : dew(set)fill " + tr.gettr("done") + " : " + player.getName());
		}
	}

	class dewsetWallSphere_c implements Runnable {

		private ArrayList<IDDataType> item;
		private ArrayList<IDDataType> itemSearch;
		private boolean isfillmode = false;
		private Player player = null;

		public dewsetWallSphere_c(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch) {
			this.player = player;
			this.item = item;
			this.itemSearch = itemSearch;
		}

		@Override
		public void run() {

			// ..........
			int getid = getfreeselect(player);
			if (selectx1[getid] == 0 && selecty1[getid] == 0 && selectz1[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewwallcircle " + tr.gettr("please_set_block_1")));
				return;
			}
			if (selectx2[getid] == 0 && selecty2[getid] == 0 && selectz2[getid] == 0) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : dewwallcircle " + tr.gettr("please_set_block_2")));
				return;
			}

			double midx = ((double) selectx1[getid] + (double) selectx2[getid]) / 2;
			double midy = ((double) selecty1[getid] + (double) selecty2[getid]) / 2;
			double midz = ((double) selectz1[getid] + (double) selectz2[getid]) / 2;

			if (midx == selectx1[getid] && midy == selecty1[getid] && midz == selectz1[getid]
					|| midx == selectx2[getid] && midy == selecty2[getid] && midz == selectz2[getid]) {
				player.sendMessage(dprint.r.color("ptdew&dewdd : small circle can't run program"));
				return;
			}

			double temp1 = 0;

			double temp5 = 0;
			double temp2 = 0;
			double temp3 = 0;
			temp1 = Math.pow((double) selectx1[getid] - (double) selectx2[getid], 2);

			temp2 = Math.pow((double) selecty1[getid] - (double) selecty2[getid], 2);

			temp3 = Math.pow((double) selectz1[getid] - (double) selectz2[getid], 2);

			double midty = (selecty1[getid] + selecty2[getid]) / 2;

			double midtx = (selectx1[getid] + selectx2[getid]) / 2;

			double midtz = (selectz1[getid] + selectz2[getid]) / 2;
			temp5 = Math.pow(temp1 + temp2 + temp3, 0.5);

			double midr = temp5 / 3;

			player.sendMessage(dprint.r.color("cir=" + midtx + "," + midty + "," + midtz));
			player.sendMessage(dprint.r.color("R=" + temp5));

			dprint.r.printAll("ptdew&dewdd : '" + player.getName() + "'" + tr.gettr("starting") + " dewwallcircle "
					+ IDDataType.arrayListToString(item) + " >_< " + 
					IDDataType.arrayListToString(itemSearch));

			// ........

			int mx = 0;
			int lx = 0;
			int my = 0;
			int ly = 0;
			int mz = 0;
			int lz = 0;

			if (selectx1[getid] >= selectx2[getid]) {
				mx = selectx1[getid];
				lx = selectx2[getid];
			} else {
				mx = selectx2[getid];
				lx = selectx1[getid];
			}

			if (selecty1[getid] >= selecty2[getid]) {
				my = selecty1[getid];
				ly = selecty2[getid];
			} else {
				my = selecty2[getid];
				ly = selecty1[getid];
			}

			if (selectz1[getid] >= selectz2[getid]) {
				mz = selectz1[getid];
				lz = selectz2[getid];
			} else {
				mz = selectz2[getid];
				lz = selectz1[getid];
			}

			
			dewwallcircle_thread aer = new dewwallcircle_thread(player, item, itemSearch, mx, my, mz, lx, ly, lz, lx,
					ly, lz, getid, midr, midtx, midty, midtz, player.getLocation());
			// Player player, int handid , byte handdata , int mx , int my , int
			// mz
			// , int lx , int ly , int lz , int xlx , int ylx , int zlx , int
			// getid , boolean isfillmode

			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, aer, sleeptime);

		}
	}

	class dewspreadcircle_c implements Runnable {
		private ArrayList<IDDataType> item;
		private ArrayList<IDDataType> itemSearch;

		private Player player;

		public dewspreadcircle_c(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch) {
			this.player = player;
			this.item = item;
			this.itemSearch = itemSearch;
			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, this, sleeptime);
		}

		@Override
		public void run() {
			Block block = player.getLocation().getBlock();
			Queue<Block> bd = new LinkedList<Block>();

			addItemIfItemIsZero(item, player);

			boolean ne = false;
			Block blb = null;
			int x = 0;
			int y = 0;
			int z = 0;

			for (x = -1; x <= 1; x++) {
				for (y = -1; y <= 1; y++) {
					for (z = -1; z <= 1; z++) {
						blb = block.getRelative(x, y, z);

						bd.add(blb);
					}
				}
			}

			Block b3 = null;

			if (bd.size() <= 0)
				return;

			while (bd.size() > 0) { // bll
				b3 = bd.poll();

				ne = false;

				for (x = -1; x <= 1; x++) {
					for (y = -1; y <= 1; y++) {
						for (z = -1; z <= 1; z++) {
							blb = b3.getRelative(x, y, z);

							if (itemSearch.size() > 0) {
								if (!IDDataType.isThisItemOnTheList(itemSearch, blb.getTypeId(), blb.getData())) {
									continue;
								}
							} 

							if (cando_all(blb, player, "dewset") == false) {
								continue;
							}

							int randid = rnd.nextInt(item.size());
							int id = item.get(randid).id;
							byte data = item.get(randid).data;

							if (blb.getTypeId() == id && blb.getData() == data){
								continue;
							}
							
							if (decreseitem1(player, id, data, true) == false) {
								player.sendMessage(dprint.r.color(tr.gettr("don't_have_enough_item")));
								return;
							}
							blb.setTypeId(id);
							blb.setData(data);

							ne = true;
							break;

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

							blb = b3.getRelative(x, y, z);
							// dprint.r.printAll("ptdew&dewdd : delete near call
							// sub ("
							// +
							// b2.getX() + "," + b2.getY() + "," + b2.getZ() +
							// ") "
							// + amount);

							bd.add(blb);

						}

					}
				}

			} // bll

			player.sendMessage(dprint.r.color("dewspreadcircle " + tr.gettr(tr.gettr("done"))));
		}
	}

	class dewspreadq_c implements Runnable {
		private ArrayList<IDDataType> item;
		private ArrayList<IDDataType> itemSearch;
		private Player player;
		private Boolean isfirst;
		private Queue<Block> bd;
		private Location playerLocation;

		public dewspreadq_c(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch,
				boolean isfirst, Queue<Block> bd,Location playerLocation) {
			this.player = player;
			this.item = item;
			this.itemSearch = itemSearch;
			this.isfirst = isfirst;
			this.bd = bd;
			this.playerLocation  = playerLocation;

			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, this, sleeptime);
		}

		@Override
		public void run() {

			Block block = playerLocation.getBlock();
			addItemIfItemIsZero(item, player);

			boolean ne = false;
			Block b2 = null;
			int x = 0;
			int z = 0;

			if (isfirst == true) {
				isfirst = false;
				for (x = -1; x <= 1; x++) {
					for (z = -1; z <= 1; z++) {
						b2 = block.getRelative(x, 0, z);

						bd.add(b2);
					}
				}

			}

			Block blb = null;

			int ccc = 0;

			if (bd.size() <= 0) {
				// bd.clear();
				return;

			}

			while (bd.size() > 0) { // bll
				blb = bd.poll();

				ne = false;

				if (itemSearch.size() > 0) {
					if (!IDDataType.isThisItemOnTheList(itemSearch, blb.getTypeId(), blb.getData())) {
						continue;
					}
				} 

				if (cando_all(blb, player, "dewset") == false) {
					continue;
				}

				int randid = rnd.nextInt(item.size());
				int id = item.get(randid).id;
				byte data = item.get(randid).data;

				
				if (blb.getTypeId() == id && blb.getData() == data){
					continue;
				}
				
				if (decreseitem1(player, id, data, true) == false) {
					player.sendMessage(dprint.r.color(tr.gettr("don't_have_enough_item")));
					return;
				}

				
				
				blb.setTypeId(id);
				blb.setData(data);
				ccc++;
				ne = true;

				if (ne == false) {
					continue;
				}

				for (x = -1; x <= 1; x++) {
					for (z = -1; z <= 1; z++) {

						if (x != 0 && z != 0) {
							continue;
						}
						b2 = blb.getRelative(x, 0, z);
						// dprint.r.printAll("ptdew&dewdd : delete near call sub
						// ("
						// +
						// b2.getX() + "," + b2.getY() + "," + b2.getZ() + ") "
						// +
						// amount);

						bd.add(b2);

					}
				}

				if (ccc > 10) {
					new dewspreadq_c(player, item, itemSearch, false, bd,playerLocation);
					return;
				}

			} // bll

			player.sendMessage(dprint.r.color("dewspreadq " + tr.gettr(tr.gettr("done"))));
		}
	}

	class dewwallcircle_thread implements Runnable {

		private int getid;
		private ArrayList<IDDataType> item;
		private ArrayList<IDDataType> itemSearch;

		private int lx = 0;
		private int ly = 0;

		private int lz = 0;
		private double midr;
		private double midtx;

		private double midty;
		private double midtz;
		private int mx = 0;

		private int my = 0;
		private int mz = 0;

		private Player player = null;

		private int xlx = 0;
		private int ylx = 0;
		private int zlx = 0;
		private Location playerLocation;

		public dewwallcircle_thread(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch, int mx,
				int my, int mz, int lx, int ly, int lz, int xlx, int ylx, int zlx, int getid, double midr, double midtx,
				double midty, double midtz ,Location playerLocation) {
			this.player = player;
			this.item = item;
			this.playerLocation = playerLocation;
			this.itemSearch = itemSearch;
			this.mx = mx;
			this.my = my;
			this.mz = mz;
			this.lx = lx;
			this.ly = ly;
			this.lz = lz;
			this.xlx = xlx;
			this.ylx = ylx;
			this.zlx = zlx;
			this.getid = getid;
			this.midr = midr;
			this.midtx = midtx;
			this.midty = midty;
			this.midtz = midtz;
		}

		@Override
		public void run() {
			if (player.getItemInHand().getType() == Material.AIR) {
				dprint.r.printAll(player.getName() + tr.gettr("has cancel  WallCircle"));
				
				return;
			}

			long endtime = 0;
			long starttime = System.currentTimeMillis();
			Block blb = null;

			addItemIfItemIsZero(item, player);
			Block blockmid = player.getWorld().getBlockAt((int) midtx, (int) midty, (int) midtz);
			
			boolean arxx = !player.hasPermission(pmaininfinite);

			while (xlx <= mx) {
				while (ylx <= my) {
					while (zlx <= mz) {

						endtime = System.currentTimeMillis();
						if (endtime - starttime > runtime) {

							dewwallcircle_thread xgn2 = new dewwallcircle_thread(player, item, itemSearch, mx, my, mz,
									lx, ly, lz, xlx, ylx, zlx, getid, midr, midtx, midty, midtz , playerLocation);

							dprint.r.printC(
									"dewwallcircle  " + tr.gettr("recall") + " " + xlx + " , " + ylx + " , " + zlx);
							dprint.r.printC(
									"low " + lx + " , " + ly + " , " + lz + " high " + mx + "," + my + "," + mz);

							Bukkit.getScheduler().scheduleSyncDelayedTask(ac, xgn2, sleeptime);

							return;
						}

						blb = playerLocation.getWorld().getBlockAt(xlx, ylx, zlx);

						if ((int) blb.getLocation().distance(blockmid.getLocation()) != (int) midr) {
							zlx++;
							continue;
						}

						if (itemSearch.size() > 0) {
							if (!IDDataType.isThisItemOnTheList(itemSearch, blb.getTypeId(), blb.getData())) {
								zlx++;
								continue;
							}
						} 

						// wallc

						if (cando_all(blb, player, "dewset") == false)
							return;

						int randid = rnd.nextInt(item.size());
						int id = item.get(randid).id;
						byte data = item.get(randid).data;

						if (blb.getTypeId() == id && blb.getData() == data){
							zlx ++;
							continue;
						}
						
						if (arxx)
						if (decreseitem1(player, id, data, true) == false) {
							player.sendMessage(dprint.r.color("ptdew&dewdd : " + tr.gettr("don't_have_enough_item")));
							return;
						}

						blb.setTypeIdAndData(id, data, true);
						//

						// wallc

						zlx++;
					}
					zlx = lz;

					ylx++;
				}
				ylx = ly;

				xlx++;
			}
			xlx = lx;

			dprint.r.printAll("ptdew&dewdd : dewwallcircle " + tr.gettr("done") + " : " + player.getName());
		}
	}

	class gift_thread implements Runnable {
		private Player player;

		private int a1;
		private byte a2;

		public gift_thread(Player player, int a1, byte a2) {
			this.player = player;
			this.a1 = a1;
			this.a2 = a2;
			
			if (player.getItemInHand().getType() == Material.AIR) {
				dprint.r.printAll(player.getName() + tr.gettr("has cancel gift"));
				
				return;
			}

			long lo = (rnd.nextInt(20) + 1) * 20;

			Bukkit.getScheduler().scheduleSyncDelayedTask(ac, this, lo);

		}

		@Override
		public void run() {
			int moveyet = 0;
			boolean okok = false;

			Block b = null;

			ArrayList<Block> blockList = new ArrayList<Block>();
			blockList.clear();

			for (int y = -10; y <= +10; y++)
				for (int z = -10; z <= +10; z++)
					for (int x = -10; x <= +10; x++) {

						Block curBlock = giftblock.getRelative(x, y, z);
						searchRecursiveBlock(blockList, curBlock, Material.CHEST, (byte) -29);
						searchRecursiveBlock(blockList, curBlock, Material.TRAPPED_CHEST, (byte) -29);
					}

			for (int y = -10; y <= +10; y++)
				for (int z = -10; z <= +10; z++)
					for (int x = -10; x <= +10; x++) {

						Block curBlock = player.getLocation().getBlock().getRelative(x, y, z);
						searchRecursiveBlock(blockList, curBlock, Material.CHEST, (byte) -29);
						searchRecursiveBlock(blockList, curBlock, Material.TRAPPED_CHEST, (byte) -29);
					}

			player.sendMessage("blockList.size() == " + blockList.size() + "  , gift position " + giftblock.getX() + ","
					+ giftblock.getY() + "," + giftblock.getZ());

			for (int index = 0; index < blockList.size(); index++) {

				b = blockList.get(index);

				if (cando_all(b, player, "right") == false) {
					continue;
				}

				if (b.getTypeId() == Material.CHEST.getId() || b.getTypeId() == Material.TRAPPED_CHEST.getId()) {
					Chest c = (Chest) b.getState();

					for (ItemStack ic : c.getInventory().getContents()) {
						if (ic == null) {
							continue;
						}

						okok = false;
						if (ic.getTypeId() == a1)
							if (a2 == -29) {
								okok = true;
							} else if (a2 == ic.getData().getData()) {
								okok = true;
							}

						if (moveyet > 10) {
							player.sendMessage(
									dprint.r.color("ptdew&dewdd : " + tr.gettr("gift_you_got_item_10_times_enough")));
							return;
						}

						if (okok == true) {
							ItemStack gj = new ItemStack(ic);
							player.getLocation().getWorld().dropItem(player.getLocation(), gj);
							moveyet++;
							c.getInventory().remove(ic);
							c.update();
							player.sendMessage(dprint.r.color(moveyet + " ... " + gj.getTypeId() + ":" + gj.getData()
									+ tr.gettr("amount") + " = " + gj.getAmount()));
						}

					}
				}

			}

			if (moveyet == 0) {
				player.sendMessage(dprint.r.color(tr.gettr("gift_not_found_item")));
			}

		}

	}

	class gotohellt implements Runnable {
		private Location lo1 = null;
		private Location lo2 = null;
		private Player player = null;

		public gotohellt(Player player, Location lo1, Location lo2) {
			this.player = player;
			this.lo1 = lo1;
			this.lo2 = lo2;
		}

		@Override
		public void run() {
			int dx = 15;
			Block blo = null;
			Block blo2 = null;

			for (int d = 1; d <= 2; d++) {
				for (int x = 0 - dx; x <= 0 + dx; x++) {
					for (int z = 0 - dx; z <= 0 + dx; z++) {
						for (int y = 0 - dx; y <= 0 + dx; y++) {
							blo = player.getWorld().getBlockAt(lo1).getRelative(x, y, z);
							if (d == 1) {
								if (blo.getType().isBlock() == false) {
									continue;
								}
							} else if (d == 2)
								if (blo.getType().isBlock() == true) {
									continue;
								}

							blo2 = Bukkit.getWorld("world_nether").getBlockAt(lo2).getRelative(x, y, z);

							switch (blo.getTypeId()) {
							case 2:
							case 35:
								blo2.setTypeId(88);
								break;

							case 3:
							case 60:
								blo2.setTypeId(87);
								break;
							case 8:
							case 9:
							case 10:
							case 11:
							case 159:
							case 97:
							case 24:
							case 89:

								blo2.setTypeId(20);
								break;
							case 41:
							case 42:
							case 57:
							case 56:
							case 133:
							case 155:

							case 15:
							case 16:
							case 14:
							case 121:
							case 129:
							case 112:
							case 125:
							case 138:
							case 124:
							case 145:
							case 84:
							case 130:
							case 47:
							case 123:
							case 122:

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

			/*
			 * if (player.getInventory().getHelmet() != null) {
			 * Bukkit.getWorld("world_nether").dropItem(lo2,
			 * player.getInventory().getHelmet());
			 * player.getInventory().getHelmet().setTypeId(0); }
			 * 
			 * ItemStack itm = new ItemStack(397, 1);
			 * player.getInventory().setHelmet(itm.getData().toItemStack());
			 */
			player.teleport(lo2);
			player.getInventory().remove(7);
			dprint.r.printAll("ptdew&dewdd : '" + player.getName() + "' " + tr.gettr("what_the_hell"));
			dprint.r.printC("ptdew&dewdd : go to hell '" + player.getName() + "'");
		}
	}

	class item55deletec implements Runnable {

		private Block block = null;
		private byte dat = 0;
		private int id = 0;
		private Player player = null;
		private int maxRecursive = 0;
		private int searchSpace = 1;
		private long reTime = 0;

		public item55deletec(Block block, Player player, int id, byte dat, int maxRecursive, int searchSpace,
				long reTime) {
			this.block = block;
			this.player = player;
			this.id = id;
			this.dat = dat;
			this.maxRecursive = maxRecursive;
			this.searchSpace = searchSpace;
			this.reTime = reTime;
		}

		@Override
		public void run() {

			// dprint.r.printC("55 starter > " + id + ":" + dat + " < " +
			// block.getTypeId() + ":" + block.getData());

			/*
			 * if (block.getTypeId() != id && block.getData() != dat) return;
			 */

			/*
			 * try { if (Economy.getMoney(player.getName()) < 100) return; }
			 * catch (UserDoesNotExistException e1) {
			 * 
			 * e1.printStackTrace(); }
			 */

			if (cando_all(block, player, "delete") == false)
				return;

			if (isprotectitemid(block.getType()) == true)
				return;

			/*
			 * try { Economy.subtract(player.getName(), 10); } catch
			 * (UserDoesNotExistException | NoLoanPermittedException e) {
			 * 
			 * e.printStackTrace(); }
			 */if (amountRecursiveCount > maxRecursive) {
				return;
			}

			if (block.getTypeId() == id && block.getData() == dat) {

				block.breakNaturally();
				amountRecursiveCount++;
			}

			Block b2 = null;

			for (int x = -searchSpace; x <= searchSpace; x++) {
				for (int y = -searchSpace; y <= searchSpace; y++) {
					for (int z = -searchSpace; z <= searchSpace; z++) {

						b2 = block.getRelative(x, y, z);

						// dprint.r.printC("b2 " + b2.getX() + "," + b2.getY() +
						// "," + b2.getZ() + " > " + id + ":" + dat + " < " +
						// b2.getTypeId() + ":" + b2.getData());

						if (b2.getTypeId() == id && b2.getData() == dat) {

							/*
							 * player.sendMessage( dprint.r.color(
							 * "maxRecursive : " + amountRecursiveCount + "/" +
							 * maxRecursive ));
							 * 
							 */

							item55delete(b2, player, id, dat, maxRecursive, searchSpace, reTime + 40);

						}
					}
				}
			}

		}
	}

	class randomplaynote_c implements Runnable {
		private Location player;

		public randomplaynote_c(Location player) {
			this.player = player;
		}

		@Override
		public void run() {
			if (randomG.nextInt(100) > 10)
				return;

			for (Player pla : player.getWorld().getPlayers()) {
				pla.playSound(player, Sound.BLOCK_NOTE_PLING, randomG.nextInt(50), randomG.nextFloat() + 1);
			}
		}
	}

	class redtorchchestt implements Runnable {
		private Block block = null;
		private Player player = null;

		public redtorchchestt(Block block, Player player) {
			this.block = block;
			this.player = player;
		}

		@Override
		public void run() {
			Chest chest = (Chest) block.getState();
			Player pxp[] = new Player[selectmax];
			int pxpmax = -1;
			for (Player pxpsub : Bukkit.getOnlinePlayers()) {
				if (pxpsub.getName().equalsIgnoreCase(player.getName())) {
					continue;
				}

			

				// if normal player or vip so can do
				pxpmax++;
				pxp[pxpmax] = pxpsub;

			}
			// after get all normall playr all vip
			int pxpluck = -1;

			for (ItemStack itm : chest.getInventory().getContents()) {
				if (itm == null) {
					continue;
				}

				int itmmax = itm.getAmount();
				itm.setAmount(1);
				for (int ama = 1; ama <= itmmax; ama++) {
					pxpluck++;
					if (pxpluck > pxpmax) {
						pxpluck = 0;
					}

					dprint.r.printAll("ptdew&dewdd : '" + player.getName() + "' " + tr.gettr("gived_item") + " '"
							+ itm.getType().name() + "' to '" + "[" + pxpluck + "/" + pxpmax + "] "
							+ pxp[pxpluck].getName() + "'");
					Location lox = pxp[pxpluck].getLocation();
					lox.setY(lox.getY() + 5);
					pxp[pxpluck].getWorld().dropItem(lox, itm);

				}

			}

			// remove block
			chest.getInventory().clear();
			chest.update(true);
			for (@SuppressWarnings("unused")
			Object obj : pxp) {
				obj = null;
			}
			pxp = null;

		}
	}

	
	// redim" + tr.gettr("for") + "each world each protect = 100

	class soiladdseedc implements Runnable {
		private Block block = null;
		private boolean first = false;
		private Player player = null;
		private Material seedid;

		public soiladdseedc(Block block, Player player, Material seedid, boolean first) {
			this.block = block;
			this.player = player;
			this.seedid = seedid;
			this.first = first;
		}

		@Override
		public void run() {
			if (first == true) {
				block.setTypeId(0);
			}
			int digX = block.getX();
			int digY = block.getY();
			int digZ = block.getZ();

			Material itemaddid;
			switch (seedid) {
			case POTATO: // potato
				itemaddid = Material.POTATO;
				break;
			case CARROT: // carot
				itemaddid = Material.CARROT;
				break;
			case CROPS:
				itemaddid = Material.CROPS;
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

			if (blockCut.getRelative(0, -1, 0).getType() != Material.SOIL)
				return;

			if (blockCut.getTypeId() != 0)
				return;

			if (decreseitem1(player, seedid.getId(), 0, false) == false) {
				player.saveData();
				return;
			}

			blockCut.setType(itemaddid);

			blockCut.setData(itemadddata);
			// dprint.r.printA(digX + "," + digY + "," + digZ);

			// randomplaynote(blockCut.getLocation());

			/*
			 * if (player.getFoodLevel() > 0) {
			 * player.setFoodLevel(player.getFoodLevel() - 1); }
			 */

			// start call sub
			for (int xx3 = digX + 4; xx3 >= digX - 4; xx3--) {
				for (int yy3 = digY - 4; yy3 <= digY + 4; yy3++) {
					for (int zz3 = digZ + 4; zz3 >= digZ - 4; zz3--) {
						if (digX == xx3 && digY == yy3 && digZ == zz3) {
							continue;
						}
						blockCut = world.getBlockAt(xx3, yy3, zz3);

						soiladdseedrecusive(blockCut, player, seedid, false);
					}
				}
			}

		}
	}

	public static JavaPlugin ac = null;

	public static ArrayList<RSWorld> rsWorld = new ArrayList<RSWorld>();

	public static boolean cando_Main(Block block, Player player, String mode) {
		if ((player.hasPermission(pmainoveride) == true)) {
			return true;
		}

		if (!isrunworld(block.getWorld().getName())) {
			return true;
		}

		RSWorld worldid = getWorld(player.getWorld().getName());
		int getid = getProtectid(block, worldid);

		if (getid == -1) {
			// player.sendMessage(dprint.r.color("this is not anyone skyblock");
			return true; // can't do it
		}

		// found
		int getslot = getplayerinslot(player.getName(), getid, worldid);

		worldid.rs.get(getid).lastUsed = System.currentTimeMillis();

		if (getplayerinslot(Constant_Protect.flag_noprotect, getid, worldid) > -1) {

			return true;
		}

		if (getplayerinslot(Constant_Protect.flag_everyone, getid, worldid) > -1 && mode.equalsIgnoreCase("right")) {

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

	public static int getplayerinslot(String player, Block block) {
		RSWorld worldid = getWorld(block.getWorld().getName());

		int getid = getProtectid(block, worldid);
		if (getid == -1) {
			return -2;
		}

		int slot = getplayerinslot(player, getid, worldid);

		return slot;
	}

	public static int getplayerinslot(String player, int rsID, RSWorld worldid) {
		RSData rs = worldid.rs.get(rsID);
		for (int lop = 0; lop < FWMaxPlayer; lop++) {
			if (rs.p[lop].equalsIgnoreCase(player)) {
				return lop;
			}
		}

		return -1;
	}

	public static String getPositionSignAsString(RSWorld worldid, RSData rs) {
		String abc = "(" + rs.x1 + "," + rs.y1 + "," + rs.z1 + ") to (" +

		rs.x2 + "," + rs.y2 + "," + rs.z2 + ")";
		return abc;

	}

	public static int getProtectid(Block block, RSWorld worldid) {
		// must check world

		for (int every = 0; every <= 1; every++) {

			for (int lop = 0; lop < worldid.rs.size(); lop++) {
				RSData rs = worldid.rs.get(lop);
				if (rs.p[0].equalsIgnoreCase(Constant_Protect.flag_everyone)) {
					if (every == 0) {
						continue;
					}
				}

				if (block.getX() >= (rs.x1) && block.getX() <= (rs.x2) && block.getY() >= (rs.y1)
						&& block.getY() <= (rs.y2) && block.getZ() >= (rs.z1) && block.getZ() <= (rs.z2)) {
					return lop;
				}
			}
		}

		return -1;
	}

	// public double buy1blockmoney = 0.0890932504689118445732202345959;

	public static String getSignWorldFleName(RSWorld idworld) {
		String aa = "ptdew_dewdd_" + idworld.worldName + ".txt";
		return aa;
	}

	// *********

	// cutwoodsub

	public static RSWorld getWorld(String wowo) {
		for (int d = 0; d < rsWorld.size(); d++)
			if (wowo.equalsIgnoreCase(rsWorld.get(d).worldName) == true)
				return rsWorld.get(d);

		// dprint.r.printAll("Error getworldid " + wowo + " so return -1");
		return null;
	}

	// Chat Event.class
	// BlockBreakEvent
	public static boolean isrunworld(String worldName) {
		return tr.isrunworld(ac.getName(), worldName);
	}
	// randomplaynote

	public static void loadsignfile() {
		for (RSWorld wow : rsWorld) {
			wow.rs = new ArrayList<RSData>();
			wow.rs.clear();
		}

		File dir = new File(folder_name);
		dir.mkdir();

		int wlo = 0;
		for (wlo = 0; wlo < rsWorld.size(); wlo++) {
			RSWorld curWorld = rsWorld.get(wlo);
			// ****************************
			try { // try

				// Open the file that is the first
				// command line parameter

				String filena = folder_name + File.separator + getSignWorldFleName(curWorld);
				File fff = new File(filena);
				fff.createNewFile();

				dprint.r.printC("ptdew&dewdd : Starting Loading " + filena);

				FileInputStream fstream = new FileInputStream(filena);
				// Get the object of DataInputStream
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;
				// Read File Line By Line

				while ((strLine = br.readLine()) != null) {
					// Print the content on the console
					RSData newrs = new RSData();

					String m[] = strLine.split(" ");

					newrs.x1 = Integer.parseInt(m[0]);

					newrs.y1 = Integer.parseInt(m[1]);

					newrs.z1 = Integer.parseInt(m[2]);

					newrs.x2 = Integer.parseInt(m[3]);

					newrs.y2 = Integer.parseInt(m[4]);

					newrs.z2 = Integer.parseInt(m[5]);

					newrs.p = new String[FWMaxPlayer];
					for (int i = 0; i < FWMaxPlayer; i++) {
						newrs.p[i] = m[i + 6];
					}

					LXRXLZRZType ee = new LXRXLZRZType(newrs.x1, newrs.y1, newrs.z1, newrs.x2, newrs.y2, newrs.z2);

					newrs.x1 = ee.lx;
					newrs.x2 = ee.rx;

					newrs.y1 = ee.ly;
					newrs.y2 = ee.ry;

					newrs.z1 = ee.lz;
					newrs.z2 = ee.rz;

					curWorld.rs.add(newrs);
					dprint.r.printC("loaded sign of world " + wlo + " sign id = " + curWorld.rs.size());

				}

				dprint.r.printC("ptdew&dewdd : loaded " + filena);

				in.close();
			} catch (Exception e) {// Catch exception if any
				System.err.println("LoadSignFile Error: " + e.getMessage());
				e.printStackTrace();
			} // catch
		}

		dprint.r.printC("loaded all sign all world");
	}

	// decrese item 1

	public static void loadworldfile() {
		String worldf = "ptdew_dewdd_worlds_sign.txt";

		File dir = new File(folder_name);
		dir.mkdir();

		String filena = folder_name + File.separator + worldf;
		File fff = new File(filena);

		try {

			rsWorld = new ArrayList<RSWorld>();

			fff.createNewFile();

			System.out.println("ptdeW&DewDD Main : " + filena);
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

			while ((strLine = br.readLine()) != null) {
				RSWorld newworld = new RSWorld();
				// Print the content on the console

				newworld.worldName = strLine;
				rsWorld.add(newworld);
				dprint.r.printC("world sign " + (rsWorld.size() - 1) + " = " + newworld.worldName);
			}

			System.out.println("ptdew&DewDD Main: Loaded " + filena);

			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error load " + filena + e.getMessage());
		}
	}

	public static void main(String abc[]) {
		dewset ds = new dewset();
		// ds.loadmainfile();
		loadworldfile();
		loadsignfile();

		for (RSWorld wow : dewset.rsWorld) {
			System.out.println("main static loop " + wow.worldName + " " + wow.rs.size());

			for (int i = 0; i < wow.rs.size(); i++) {
				System.out.println(wow.rs.get(i).p[0] + " " + getPositionSignAsString(wow, wow.rs.get(i)));

			}
		}

		/*
		 * for (int i = 0 ; i < ds.rsWorld.size() ; i ++ ) {
		 * ds.savesignfile2(-1, i); }
		 */

	}

	public long lastsort2 = 0;

	// Check Permission Area block player mode

	Random rnd = new Random();

	public int amountRecursiveCount = 0; // recursive
	// 55
	// 55

	public int d4[] = new int[selectmax + 1];

	public Block giftblock = null;

	public Random randomG = new Random();

	// cut seem block

	public int runtime = (int) tr.gettrint("dewset runtime as milisecond");

	public long sleeptime = (int) tr.gettrint("dewset sleeptime as tick");

	public Block selectblock[] = new Block[selectmax + 1];

	public String selectname[] = new String[selectmax + 1];

	public String selectworldname[] = new String[selectmax + 1];

	public int selectx1[] = new int[selectmax + 1];

	// Check Permission Area block

	public int selectx2[] = new int[selectmax + 1];

	public int selecty1[] = new int[selectmax + 1];

	public int selecty2[] = new int[selectmax + 1];

	public int selectz1[] = new int[selectmax + 1];

	public int selectz2[] = new int[selectmax + 1];

	public dewset() {
		// if (firstrun19 == false){

		// loadadminlist();

		// firstrun19 = true;
		// }
	}

	public void addItemIfItemIsZero(ArrayList<IDDataType> item, Player player) {
		if (item.size() == 0) {
			if (player.getItemInHand() == null) {
				return;
			}

			IDDataType ne = new IDDataType(player.getItemInHand().getTypeId(),
					player.getItemInHand().getData().getData());
			item.add(ne);
		}
	}

	public void autosortchest2(Block block, Player player) {
		autosortchest2_class ar = new autosortchest2_class(block, player);
		Bukkit.getScheduler().scheduleSyncDelayedTask(ac, ar);
	}

	public boolean canaddtorch(Block bbc) {
		if (bbc.getTypeId() != 0)
			return false;

		if (canaddtorchatblock(bbc.getRelative(0, -1, 0)) == true
				|| canaddtorchatblock(bbc.getRelative(1, 0, 0)) == true
				|| canaddtorchatblock(bbc.getRelative(-1, 0, 0)) == true
				|| canaddtorchatblock(bbc.getRelative(0, 0, 1)) == true
				|| canaddtorchatblock(bbc.getRelative(0, 0, -1)) == true)
			return true;

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

	public static boolean cando_all(Block block, Player player, String modeevent) {
		boolean ar = cando_Main(block, player, modeevent);
		
		boolean sky = false;
		boolean cre = false;
		
		boolean hasProtect = false;
		
		RSWorld rsWorld = getWorld(block.getWorld().getName());
		hasProtect = getProtectid(block , rsWorld) > -1 ? true : false;
		
		
		if (Bukkit.getPluginManager().getPlugin("dewddskyblock") == null) {
			sky = true;
		} else {
			sky = api_skyblock.cando(block, player, modeevent);
			
			if (api_skyblock.getProtectid(block) > -1)  {
				hasProtect = true;
			}
		}
		
		if (Bukkit.getPluginManager().getPlugin("dewddcreative") == null) {
			cre = true;
		} else {
			cre = dewddcreative.api_creative.cando(block.getX(),block.getY(),block.getZ() ,player);
			
			if (dewddcreative.api_creative.isProtectedArea(block) == true) {
				hasProtect = true;
			}
			
		}

		boolean allProtect = (ar == true && sky == true && cre == true);
		boolean isDewset = (modeevent.equalsIgnoreCase("dewset") && hasProtect == true) ||
				(!modeevent.equalsIgnoreCase("dewset"));
		
		return allProtect && isDewset;
	}

	/*public boolean cando_Main(Block block, Player player, String modeevent) {
		RSWorld worldid = getWorld(block.getWorld().getName());

		if (worldid == null)
			if (api_admin.dewddadmin.is2moderator(player) == true)
				return false;
			else
				return true;

		if (worldid.rs.size() == 0)
			if (api_admin.dewddadmin.is2moderator(player) == true)
				return false;
			else
				return true;

		block.getX();
		block.getY();
		block.getZ();

		int playerInSlot = -1;
		int playerInSlot2 = -1;

		int dewsignnow = getProtectid(block, worldid);

		boolean logic1 = false;

		if (dewsignnow >= 0) { // true if that is protect

			if (api_admin.dewddadmin.is2moderator(player) == true) {
				playerInSlot = getplayerinslot(player.getName(), dewsignnow, worldid);
				return !(playerInSlot == -1);
			}

			if (modeevent.equalsIgnoreCase("right") == true) { // right everyone
				playerInSlot = getplayerinslot(Constant_Protect.flag_everyone, dewsignnow, worldid);

				if (playerInSlot > -1) { // has flag everyone

					if (api_admin.dewddadmin.is2moderator(player) == true) {
						// staff
						// can't
						// do
						// it
						logic1 = false;
					} else {
						logic1 = true;
					}

				} else { // don't have flag everyone
					playerInSlot2 = getplayerinslot(player.getName(), dewsignnow, worldid);
					logic1 = !(playerInSlot2 == -1); // if have name return true
				}

				if (logic1 == false)
					if (player.hasPermission(pmainoveride) == true) {
						logic1 = true;
					}
				return logic1;

			} // right click
			else { // not right click

				playerInSlot = getplayerinslot(player.getName(), dewsignnow, worldid);

				logic1 = !(playerInSlot == -1); // don't have name can't !

				if (logic1 == false)
					if (player.hasPermission(pmainoveride) == true) {
						logic1 = true;
					}
				return logic1;

			} // right click or not

		} else { // If not who each home
					// staff should't have permission to place block
			if (api_admin.dewddadmin.is2moderator(player) == true) {
				return player.hasPermission(pmainplaceblocknoprotect);

			}

			if (modeevent.equalsIgnoreCase("dewset") == true)
				return player.hasPermission(pmaindewseteverywhere);

			return true;
		}

	}*/

	public boolean checkbetweenblock(int digx, int digy, int digz, int x1, int y1, int z1, int x2, int y2, int z2) {

		boolean goodc1 = false;

		goodc1 = false;

		// x 2 type
		// x1 <= x2
		if (x1 <= x2) {
			if (digx > x1 - 1 && digx < x2 + 1)
				if (y1 <= y2) {
					if (digy > y1 - 1 && digy < y2 + 1)
						// y true
						if (z1 <= z2) {
							if (digz > z1 - 1 && digz < z2 + 1) {
								// z true
								goodc1 = true;
							}
						} else if (digz < z1 + 1 && digz > z2 - 1) {
							// z true
							goodc1 = true;
						}
				} else if (digy < y1 + 1 && digy > y2 - 1)
					// y true
					if (z1 <= z2) {
						if (digz > z1 - 1 && digz < z2 + 1) {
							// z true
							goodc1 = true;
						}
					} else if (digz < z1 + 1 && digz > z2 - 1) {
						// z true
						goodc1 = true;
					}
		} else if (digx < x1 + 1 && digx > x2 - 1)
			if (y1 <= y2) {
				if (digy > y1 - 1 && digy < y2 + 1)
					// y true
					if (z1 <= z2) {
						if (digz > z1 - 1 && digz < z2 + 1) {
							// z true
							goodc1 = true;
						}
					} else if (digz < z1 + 1 && digz > z2 - 1) {
						// z true
						goodc1 = true;
					}
			} else if (digy < y1 + 1 && digy > y2 - 1)
				// y true
				if (z1 <= z2) {
					if (digz > z1 - 1 && digz < z2 + 1) {
						// z true
						goodc1 = true;
					}
				} else if (digz < z1 + 1 && digz > z2 - 1) {
					// z true
					goodc1 = true;
				}

		return goodc1;

	}

	public void chestabsorb() {

		new chestabsorb_c();
	}

	public void chestabsorb2() {

		new chestabsorb_c2();
	}

	public Block chestnearsign(Block temp) {
		int d5 = 1;
		Block typebox = null;

		Block b3 = null;
		for (int ax = -d5; ax <= d5; ax++) {
			for (int ay = -d5; ay <= d5; ay++) {
				for (int az = -d5; az <= +d5; az++) {

					b3 = temp.getRelative(ax, ay, az);

					if (b3.getTypeId() != 54) {
						continue;
					}

					/*
					 * dprint.r.printAll("chestnearsign " + b3.getTypeId() +
					 * " > " + b3.getX() + "," + b3.getZ());
					 */
					// search chest type
					typebox = b3;
					break;
				}
			}
		}

		return typebox;
	}

	public void createmonster(Player player, EntityType EntityTypeGot, int count) {
		new createmonster_c(player, EntityTypeGot, count);
	}

	// decrese item 1
	

	public void DeleteRecursive_mom(HashMap<String, Location> bd, World world, int firstAdded, LXRXLZRZType ee,
			ArrayList<IDDataType> item, int chunklimit, int search) {
		DeleteRecursive_Thread dr = new DeleteRecursive_Thread(bd, world, firstAdded, ee, item, chunklimit, search);
		Bukkit.getScheduler().scheduleSyncDelayedTask(ac, dr);

	}

	public void dewa(Player player, int amount) {

		dewa_mom r = new dewa_mom();
		r.amount = amount;
		r.player = player;
		Bukkit.getScheduler().scheduleSyncDelayedTask(ac, r);

	} // dew a

	public boolean dewbuy(Player player) {
		dewbuy_class ar = new dewbuy_class(player);

		Bukkit.getScheduler().runTask(ac, ar);

		return ar.isok;

	}

	public void dewbuydelete(Player player) {
		new dewbuydelete_c(player);
	}

	public void dewbuyzone(Player player, Block block2) {

		new dewbuyzone_c(player, block2);
	}

	public void dewcopy(Player player) {
		dewcopy_c_mom abr = new dewcopy_c_mom(player);
		Bukkit.getScheduler().scheduleSyncDelayedTask(ac, abr);
	}

	public void dewdown(Player player, ArrayList<IDDataType> item) {

		new dewdown_c(player, item);
	}

	// fixtool

	public void dewextend(Player player) {
		new dewextend_c(player);
	}

	// getfreeselect

	public void dewselectcube(Player player, int rad) {
		new dewselectcube_c(player, rad);
	}

	public void dewselectprotect(Player player) {
		new dewselectprotect_c(player);
	}

	public void dewset(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch, boolean invert) {
		dewset_mom aer = new dewset_mom(player, item, itemSearch, invert);

		Bukkit.getScheduler().scheduleSyncDelayedTask(ac, aer);
	}

	public void dewsetblock(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch) {

		dewsetblock_mom aer = new dewsetblock_mom(player, item, itemSearch);

		Bukkit.getScheduler().scheduleSyncDelayedTask(ac, aer);

	}

	// riceblockiron

	public void dewsetFullSphere(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch) {
		dewsetFullSphere_c abx = new dewsetFullSphere_c(player, item, itemSearch);
		Bukkit.getScheduler().scheduleSyncDelayedTask(ac, abx);

	}

	public void dewsetLight(Player player, ArrayList<IDDataType> item) {

		dewsetLight_mom aer = new dewsetLight_mom(player, item);
		Bukkit.getScheduler().scheduleSyncDelayedTask(ac, aer);

	}

	public void dewsetLightAround(Player player, ArrayList<IDDataType> item) {
		dewselectcube(player, 3);

		dewsetLight_mom aer = new dewsetLight_mom(player, item);
		Bukkit.getScheduler().scheduleSyncDelayedTask(ac, aer);

	}

	public void dewsetroom(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch) {

		dewsetroom_mom aer = new dewsetroom_mom(player, item, itemSearch);

		Bukkit.getScheduler().scheduleSyncDelayedTask(ac, aer);

	}

	// sandmelon

	// ironorefreeze

	public void dewsetwall(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch) {

		dewsetwall_mom aer = new dewsetwall_mom(player, item, itemSearch);

		Bukkit.getScheduler().scheduleSyncDelayedTask(ac, aer);

	}

	public void dewsetWallSphere(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch) {

		dewsetWallSphere_c aer = new dewsetWallSphere_c(player, item, itemSearch);

		Bukkit.getScheduler().scheduleSyncDelayedTask(ac, aer);

	}

	public void dewspreadcircle(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch) {

		new dewspreadcircle_c(player, item, itemSearch);
	}

	// obsidianabsorb

	// boolean firstrun19 = false;

	// Check Permission Area block
	// checkidhome

	public void dewspreadq(Player player, ArrayList<IDDataType> item, ArrayList<IDDataType> itemSearch) {
		Queue<Block> bd = new LinkedList<Block>();
		
		dprint.r.printAll("ptdew&dewdd: " + player.getName() + " starting dsq " +   IDDataType.arrayListToString(item) + " >_< " + 
								IDDataType.arrayListToString(itemSearch));
		
		new dewspreadq_c(player, item, itemSearch, true, bd,player.getLocation());
	}

	public int distance2d(int x1, int z1, int x2, int z2) {
		double t1 = Math.pow(x1 - x2, 2);
		double t2 = Math.pow(z1 - z2, 2);
		double t3 = Math.pow(t1 + t2, 0.5);
		int t4 = (int) t3;
		return t4;
	}

	public void fw_list(Player player) {
		RSWorld worldid = dewset.getWorld(player.getWorld().getName());
		Block block = player.getLocation().getBlock();
		int getid = dewset.getProtectid(block, worldid);
		RSData tmprs;

		if (getid == -1) {
			tmprs = null;
		} else {
			tmprs = worldid.rs.get(getid);
		}

		int meInSlot = 0;
		if (getid == -1) {
			meInSlot = -1;
		} else {
			meInSlot = dewset.getplayerinslot(player.getName(), getid, worldid);
		}

		if (getid == -1) {
			player.sendMessage(dprint.r.color(tr.gettr("this_zone_don't_have_any_protect")));
			return;
		}

		player.sendMessage(dprint.r.color("protect id ") + getid + " , position "
				+ dewset.getPositionSignAsString(worldid, tmprs));

		for (int i = 0; i < dewset_interface.FWMaxPlayer; i++)
			if (tmprs.p[i].equalsIgnoreCase("null") == false) {
				player.sendMessage(dprint.r.color("Member " + i + " = " + tmprs.p[i]));

			}
	}

	public ArrayList<Integer> getAllHomeIDThatHaveMyName(String playerName, int worldid) {
		ArrayList<Integer> tmp = new ArrayList<Integer>();

		return null;
	}

	// fixtool itemstack and player
	// getfreeselect
	public int getfreeselect(Player player) {
		int lp1 = 0;

		boolean foundza = false;
		// clear select array
		for (lp1 = 0; lp1 < selectmax; lp1++)
			if (selectname[lp1] == null || selectname[lp1].equalsIgnoreCase("") == true) {
				selectname[lp1] = "null";
				selectx1[lp1] = 0;
				selecty1[lp1] = 0;
				selectz1[lp1] = 0;
				selectx2[lp1] = 0;
				selecty2[lp1] = 0;
				selectz2[lp1] = 0;

				d4[lp1] = 1;
				selectblock[lp1] = null;
			}

		// clear ofline player
		for (lp1 = 0; lp1 < selectmax; lp1++) {
			foundza = false;
			// loop player" + tr.gettr("for") + "clear
			for (Player pla : Bukkit.getOnlinePlayers())
				if (pla.getName().equalsIgnoreCase(selectname[lp1]) == true) {
					foundza = true;
					break;
					// found
				}

			if (foundza == false) { // clear
				selectname[lp1] = "null";
				selectx1[lp1] = 0;
				selecty1[lp1] = 0;
				selectz1[lp1] = 0;
				selectx2[lp1] = 0;
				selecty2[lp1] = 0;
				selectz2[lp1] = 0;

				d4[lp1] = 1;
				selectblock[lp1] = null;
			}

		}

		// if fonund return
		for (lp1 = 0; lp1 < selectmax; lp1++)
			if (player.getName().equalsIgnoreCase(selectname[lp1]) == true)
				return lp1;

		// if not found
		for (lp1 = 0; lp1 < selectmax; lp1++)
			if (selectname[lp1].equalsIgnoreCase("null") == true) {
				selectname[lp1] = player.getName();
				return lp1;
			}

		dprint.r.printC("ptdew&dewdd : " + tr.gettr("error_getfreeselect_return_-1"));
		return -1;
	}

	public LinkedList<String> getmaterialrealname(String gname) {
		LinkedList<String> ab = new LinkedList<String>();

		for (Material en : Material.values())
			if (en.name().toLowerCase().indexOf(gname.toLowerCase()) > -1) {

				dprint.r.printC("found material real name = " + en.name());
				ab.add(en.name());
			}

		return ab;
	}

	
	// getselectblock //" + tr.gettr("for") + "dewset or dewfill or dewdelete
	public Block[] getselectblock(int getid, Player player) {

		int adderB = -1;
		int countblock = (2 + Math.abs(selectx1[getid] - selectx2[getid]))
				* (2 + Math.abs(selecty1[getid] - selecty2[getid])) * (2 + Math.abs(selectz1[getid] - selectz2[getid]));

		

		player.sendMessage(dprint.r.color("countblock = " + countblock));

		int lx = 0;
		int mx = 0;
		int ly = 0;
		int my = 0;
		int lz = 0;
		int mz = 0;

		if (selectx1[getid] >= selectx2[getid]) {
			mx = selectx1[getid];
			lx = selectx2[getid];
		} else {
			mx = selectx2[getid];
			lx = selectx1[getid];
		}

		if (selecty1[getid] >= selecty2[getid]) {
			my = selecty1[getid];
			ly = selecty2[getid];
		} else {
			my = selecty2[getid];
			ly = selecty1[getid];
		}

		if (selectz1[getid] >= selectz2[getid]) {
			mz = selectz1[getid];
			lz = selectz2[getid];
		} else {
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

					/*
					 * if (isprotectitemid(blb.getTypeId()) == true) { continue;
					 * }
					 */

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
	
	// getselectblock //" + tr.gettr("for") + "dewbuy check wa mee kee block
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
		} else {
			mx = selectx2[getid];
			lx = selectx1[getid];
		}

		if (selecty1[getid] >= selecty2[getid]) {
			my = selecty1[getid];
			ly = selecty2[getid];
		} else {
			my = selecty2[getid];
			ly = selecty1[getid];
		}

		if (selectz1[getid] >= selectz2[getid]) {
			mz = selectz1[getid];
			lz = selectz2[getid];
		} else {
			mz = selectz2[getid];
			lz = selectz1[getid];
		}

		for (int xl = lx; xl <= mx; xl++) {
			for (int yl = ly; yl <= my; yl++) {
				for (int zl = lz; zl <= mz; zl++) {
					Block blb = player.getWorld().getBlockAt(xl, yl, zl);
					if (cando_all(blb, player, "buy") == false) {
						// dprint.r.printAll("...");
						countall = -1;
						return countall;
					} else {
						countall++;
					}

				}
			}
		}

		return countall;

	} // getselectblock

	/*
	 * public void protectrail(Block block, Player player) {
	 * 
	 * if (api_private.dewddprivate.checkpermissionareasign(block) == false) {
	 * protectrailrun(block, player); // add protect }
	 * 
	 * Block b2 = null;
	 * 
	 * for (int x = -4; x <= 4; x++) { for (int y = -4; y <= 4; y++) { for (int
	 * z = -4; z <= 4; z++) {
	 * 
	 * if (x == 0 && y == 0 && z == 0) { continue; }
	 * 
	 * b2 = block.getRelative(x, y, z);
	 * 
	 * if (b2.getTypeId() != 27 && b2.getTypeId() != 66) { continue; } if
	 * (api_private.dewddprivate.checkpermissionareasign(b2) == true) {
	 * continue; }
	 * 
	 * dprint.r.printAll(tr.gettr("running") + " " + b2.getX() + "," + b2.getY()
	 * + "," + b2.getZ()); protectrail(b2, player);
	 * dprint.r.printAll(tr.gettr("running") + " " + tr.gettr("done") + " " +
	 * b2.getX() + "," + b2.getY() + "," + b2.getZ());
	 * 
	 * } } } }
	 * 
	 * public boolean protectrailrun(Block block, Player player) {
	 * 
	 * boolean ok = false; boolean spa = false;
	 * 
	 * // searh near" + tr.gettr("for") + "space and create sign Block b2 =
	 * null; for (int x = -1; x <= 1; x++) { for (int y = -1; y <= 1; y++) { for
	 * (int z = -1; z <= 1; z++) { if (x == 0 && y == 0 && z == 0) { continue; }
	 * 
	 * b2 = block.getRelative(x, y, z);
	 * 
	 * if (b2.getTypeId() == 0) { // search near spa = false; if
	 * (b2.getRelative(0, -1, 0).getTypeId() == 0) { b2.getRelative(0, -1,
	 * 0).setTypeId(5); spa = true; } else { spa = true; }
	 * 
	 * if (spa == false) { continue; }
	 * 
	 * dprint.r.printAll(tr.gettr("found_space") + b2.getX() + "," + b2.getY() +
	 * "," + b2.getY()); b2.setTypeId(63); Sign sign = (Sign) b2.getState();
	 * sign.setLine(0, "[dewdd]"); sign.setLine(1, player.getName());
	 * sign.update(true); ok = true; dprint.r.printAll( tr.gettr("created_sign")
	 * + " at : " + b2.getX() + "," + b2.getY() + "," + b2.getY()); break; }
	 * 
	 * } if (ok == true) { break; }
	 * 
	 * } if (ok == true) { break; } }
	 * 
	 * if (ok == true) return true;
	 * 
	 * boolean ok2 = false; if (ok == false) { for (int x = -1; x <= 1; x++) {
	 * for (int y = -1; y <= 1; y++) { for (int z = -1; z <= 1; z++) { if (x ==
	 * 0 && y == 0 && z == 0) { continue; } b2 = block.getRelative(x, y, z);
	 * 
	 * ok2 = protectrailrun(b2, player); if (ok2 == true) return true; } } } }
	 * 
	 * return false; }
	 */

	public int getwallid() {
		int g = 0;
		Random rn = new Random();

		while (g <= 0 || isdewset(g) == false) {
			g = rn.nextInt(500);
		}

		return g;
	}

	public void gift(Player player, int a1, byte a2) {
		gift_thread xyz = new gift_thread(player, a1, a2);

	}

	public void gotohell(Player player, Location lo1, Location lo2) {
		// copy to hell
		gotohellt ae = new gotohellt(player, lo1, lo2);
		Bukkit.getScheduler().scheduleSyncDelayedTask(ac, ae);
	}

	public boolean isNumeric(String str) {
		for (char c : str.toCharArray())
			if (!Character.isDigit(c))
				return false;
		return true;
	}

	public boolean isprotectitemid(Material importid) {
		switch (importid) {
		case CHEST: // chest
		case SPONGE: // sponge
		case DISPENSER: // dispenser
		case FURNACE: // fu
		case BURNING_FURNACE: // fu
		case SIGN_POST: // sign post
		case WALL_SIGN: // wall sign
		case TRAPPED_CHEST: // lock chest
		case ANVIL: // anvil
		case BEACON: // beacon
		case ENDER_CHEST: // enderchest
		case ENCHANTMENT_TABLE: // enchant
		case MOB_SPAWNER: // monster spawner
		case BREWING_STAND: // brewing
		case HOPPER: // hopper
		case DROPPER:

			return true;
		default:
			return false;
		}
	}

	// savesignfile

	public boolean isunsortid(ItemStack impo) {

		if (impo.getType().getMaxDurability() > 0)
			return true;

		if (impo.getEnchantments().size() > 0)
			return true;

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

	public void item55delete(Block block, Player player, int id, byte dat, int maxRecursive, int searchSpace,
			long reTime) {
		item55deletec ab = new item55deletec(block, player, id, dat, maxRecursive, searchSpace, reTime);

		// delaylay55 bb = new delaylay55(ab);
		Bukkit.getScheduler().scheduleSyncDelayedTask(ac, ab);

		// amountRecursiveCount = 0;
		/*
		 * player.sendMessage(dprint.r.color("55 " + id + ":" + dat +
		 * " maxRecursive " + maxRecursive + ",searchSpace " + searchSpace +
		 * ", block "/ + block.getX() + "," + block.getY() + "," +
		 * block.getZ());
		 */
		// Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ac, ab,
		// rnd.nextInt(100) );
	}

	public void linkurl(Player player, String url) {
		if (url.endsWith("?fb") == true || url.endsWith("?facebook") == true) {
			dprint.r.printA("ptdew&dewdd : my facebook > https://www.facebook.com/dewddminecraft");
		}

		if (url.endsWith("?e-mail") == true || url.endsWith("?mail") == true) {
			dprint.r.printA("ptdew&dewdd : my e-mail > dewtx29@gmail.com");
		}

		if (url.endsWith("?youtube") == true || url.endsWith("?video") == true) {
			dprint.r.printA("ptdew&dewdd : my youtube > http://www.youtube.com/ptdew");
			dprint.r.printA("ptdew&dewdd : my youtube 2 > http://www.youtube.com/ptdew2");
		}

		if (url.endsWith("?plugin") == true || url.endsWith("?pl") == true) {
			dprint.r.printA(
					"ptdew&dewdd : my plugin > http://www.youtube.com/playlist?list=PLlM9Jjda8OZeMEuUtVxyXu2XF62rqzt2j");
		}

	}

	public void loadgiftfile() {
		String worldf = "ptdew_dewdd_gift_position.txt";

		File dir = new File(folder_name);
		dir.mkdir();

		String filena = folder_name + File.separator + worldf;
		File fff = new File(filena);

		try {

			giftblock = null;
			fff.createNewFile();

			System.out.println("ptdeW&DewDD : " + tr.gettr("starting_loading_file") + filena);
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

			String[] m;

			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				m = strLine.split("\\s+");
				giftblock = Bukkit.getWorld(m[0]).getBlockAt(Integer.parseInt(m[1]), Integer.parseInt(m[2]),
						Integer.parseInt(m[3]));

			}

			System.out.println("ptdew&DewDD Main: Loaded " + filena);

			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println(tr.gettr("error_while_loading_file") + filena + " " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadmainfile() {
		loadworldfile();
		loadsignfile();
		loadgiftfile();
		loaddewsetlistblockfile();
	}

	public Block protochest(Block block, int d4, String sorttype) {
		Block temp = null;
		Sign sign2 = null;
		Block typebox = null;

		// search sign

		for (int gx2 = 0 - d4; gx2 <= 0 + d4; gx2++) {
			for (int gy2 = 0 - d4; gy2 <= 0 + d4; gy2++) {
				for (int gz2 = 0 - d4; gz2 <= 0 + d4; gz2++) {
					temp = block.getRelative(gx2, gy2, gz2);
					if (temp.getTypeId() != 63 && temp.getTypeId() != 68) {
						continue;
					}

					sign2 = (Sign) temp.getState();
					if (sign2.getLine(0).equalsIgnoreCase("dewsorttype")) {
						sign2.setLine(0, "[dewsorttype]");
						sign2.update(true);
					}

					if (sign2.getLine(0).equalsIgnoreCase("[dewsorttype]")) {
						if (sign2.getLine(1).equalsIgnoreCase(sorttype)) {
							// found proto type

							// searh box of proto sign
							typebox = chestnearsign(temp);
							if (typebox != null) {
								return typebox;
							}

						}
					}
				} // search sign
			}
		}

		return typebox;

	}

	// savesignfiledefrag

	public int randomnumberint() {
		int randomInt = randomG.nextInt(50);
		randomInt += 1;
		return randomInt;
	}

	public void randomplaynote(Location player) {
		randomplaynote_c arr = new randomplaynote_c(player);
		Bukkit.getScheduler().scheduleSyncDelayedTask(ac, arr);
	}

	public void redtorchchest(Block block, Player player) {

		// auto give item" + tr.gettr("for") + "all player on server
		redtorchchestt ee = new redtorchchestt(block, player);

		Bukkit.getScheduler().scheduleSyncDelayedTask(ac, ee);

	}

	public void savesignfile(RSWorld worldid) {
		File dir = new File(folder_name);
		dir.mkdir();

		String filena = folder_name + File.separator + getSignWorldFleName(worldid);
		File fff = new File(filena);

		FileWriter fwriter;
		try {
			fff.createNewFile();

			dprint.r.printC("ptdew&dewdd : Start saving " + filena);
			fwriter = new FileWriter(fff);

			// fwriter.write(worldid.rs.size() +
			// System.getProperty("line.separator"));

			for (int loopSign = 0; loopSign < worldid.rs.size(); loopSign++) {

				// System.getProperty("line.separator")
				RSData tmprs = worldid.rs.get(loopSign);

				fwriter.write(tmprs.x1 + " ");
				fwriter.write(tmprs.y1 + " ");
				fwriter.write(tmprs.z1 + " ");

				fwriter.write(tmprs.x2 + " ");
				fwriter.write(tmprs.y2 + " ");
				fwriter.write(tmprs.z2 + " ");

				for (int whome = 0; whome < FWMaxPlayer; whome++)
					if (tmprs.p[whome] != null) {
						fwriter.write(tmprs.p[whome] + " ");
					} else {
						fwriter.write("null" + " ");
					}

				fwriter.write(System.getProperty("line.separator"));
				fwriter.flush();
				// dprint.r.printC ("ptdew&dewdd : Saved y= " + y );

			}

			fwriter.close();
			dprint.r.printC("ptdew&dewdd : saved " + filena);
			return;

		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void saveworld() {
		for (World world : Bukkit.getWorlds()) {
			world.save();
			for (Player pla : world.getPlayers()) {
				pla.saveData();
			}
		}
	}

	public void searchRecursiveBlock(ArrayList<Block> blockList, Block curBlock, Material searchMaterial,
			Byte searchData) {
		int search = 3;

		/*
		 * dprint.r.printAll(curBlock.getX() + "," + curBlock.getY() + "," +
		 * curBlock.getZ() + " = " + curBlock.getType().name() + ":" +
		 * curBlock.getData());
		 */

		if (curBlock.getType() == searchMaterial)
			if (curBlock.getData() == searchData || searchData == -29) {

				boolean found = false;
				for (int i = 0; i < blockList.size(); i++) {
					Block bo = blockList.get(i);
					if (bo.getX() == curBlock.getX() && bo.getY() == curBlock.getY() && bo.getZ() == curBlock.getZ())

						found = true;
					break;

				}

				if (found == false) {

					blockList.add(curBlock);
					// dprint.r.printAll("gift " + curBlock.getX() + "," +
					// curBlock.getY() + "," + curBlock.getZ());
				}
			}

		Block tm;
		for (int x = -search; x <= search; x++)
			for (int y = -search; y <= search; y++)
				for (int z = -search; z <= search; z++) {

					tm = curBlock.getRelative(x, y, z);
					if (tm == null) {
						continue;
					}

					if (tm.getType() == searchMaterial)
						if (tm.getData() == searchData || searchData == -29) {

							// check Block
							boolean sea = false;
							for (int i = 0; i < blockList.size(); i++) {
								Block te = blockList.get(i);
								if (te.getX() == tm.getX() && te.getY() == tm.getY() && te.getZ() == tm.getZ()) {
									sea = true;
									break;
								}
							}

							if (sea == false) {

								searchRecursiveBlock(blockList, tm, searchMaterial, searchData);

								continue;
							}

						}

				}

	}



	public void showpos(Player player, int idc) {
		player.sendMessage("cur select " + "(" + selectx1[idc] + "," 
				+ selecty1[idc] + ","+ selectz1[idc] + ") to ( "  + 
				selectx2[idc] + "," 
				+ selecty2[idc] + ","+ selectz2[idc] + ")");
	}

	public void soiladdseedrecusive(Block block, Player player, Material seedid, boolean first) {

		soiladdseedc ab = new soiladdseedc(block, player, seedid, first);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ac, ab, rnd.nextInt(20));
	}

	public void superdestroy(Block block, Player player, int dleft, int typeid, byte typedata) {
		if (player.getItemInHand().getDurability() < player.getItemInHand().getType().getMaxDurability()) {

			if (block.getTypeId() != typeid || block.getData() != typedata)
				return;

		

			if (cando_all(block, player, "delete") == false)
				return;

			player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability() + 1));
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
						if (dleft == 0)
							return;
						superdestroy(block2, player, dleft, typeid, typedata);
					}
				}
			}

		}
	}

	public void teleportPlayerToProtectID(int id, Player player) {
		RSWorld worldid = getWorld(player.getWorld().getName());
		RSData tmprs = worldid.rs.get(id);

		LXRXLZRZType ee = new LXRXLZRZType(tmprs.x1, tmprs.y1, tmprs.z1,

				tmprs.x2, tmprs.y2, tmprs.z2);

		int mid[] = ee.getmiddle();

		Location lox = player.getWorld().getBlockAt(mid[0], mid[1], mid[2]).getLocation();

		player.getWorld().loadChunk((int) lox.getX(), (int) lox.getZ());
		player.teleport(lox);
		player.sendMessage("ptdew&dewdd : " + tr.gettr("teleported_your_to_zone_id") + id
				+ getPositionSignAsString(worldid, tmprs));

	}

} // dew minecraft