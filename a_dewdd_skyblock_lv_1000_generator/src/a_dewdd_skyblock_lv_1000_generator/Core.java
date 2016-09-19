package a_dewdd_skyblock_lv_1000_generator;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

public class Core {
	public static String sellablePath = File.separator + "ramdisk" + File.separator + "sellableblock.txt";
	public static String missionPath = File.separator + "ramdisk" + File.separator + "missionblock.txt";

	HashMap<String, SellableType> sell = new HashMap<String, SellableType>();
	HashMap<String, AllBlockInGameType> allBlockInGame = new HashMap<String, AllBlockInGameType>();

	LinkedList<SellableType> sellAsList = new LinkedList<SellableType>();
	LinkedList<AllBlockInGameType> allBlockInGameAsList = new LinkedList<AllBlockInGameType>();

	public static int dnaSize = 2000;

	public static int maxItemForCompleteMission = 10;
	public static int minItemForCompleteMission = 3;

	public static int maxRewardDiffBlockType = 10;
	public static int minRewardDiffBlockType = 1;
	public static int maxRewardDiffItemType = 10;
	public static int minRewardDiffItemType = 0;

	public static long MaxTickToCompleteAllLV = 60 * 60 * 24 * 31;

	public static int maxShopSize = 10;
	public static int minShopSize = 3;

	public static int swapMultipy = 100;

	public static double SellMaxCost = 1000;
	public static double ShopMaxCost = 10000;

	public static int maxLV = 100;

	// 415 unique item

	public void dnaDecoder(final double[] chromosome, LinkedList<AllShop> tmpAllShop, LinkedList<SellableType> tmpSell,
			LinkedList<LV1000Type> tmpLV) {

		LinkedList<Double> tmpReading = new LinkedList<Double>();

		int tmpAllShopUniqueDone = 0;
		int curMissionItemSwapPosition = 0;

		boolean usedItShopList[] = new boolean[Main.co.allBlockInGameAsList.size()];

		for (int i = 0; i < Main.co.allBlockInGameAsList.size(); i++) {
			usedItShopList[i] = false;
		}

		int curChro = 0;
		
		boolean usedItNeedList[] = new boolean[Main.co.allBlockInGameAsList.size()];
		boolean usedItRewardList[] = new boolean[Main.co.allBlockInGameAsList.size()];
		int tmpUseItLVUniqueCount = 0;
		int tmpUsedItRewardUniqueCount = 0;
		
		while (curChro < dnaSize) {
			// d.pl("while " + curChro + " / " + dnaSize);

			// ****************************************************

			// deal with player sell price
			if (tmpSell.size() < sell.size()) {
				
				double tmpReadChro = Math.abs(chromosome[curChro]);
			

				
					SellableType x = sellAsList.get(tmpSell.size()).copyIt();

					x.sellPerPrice = tmpReadChro * SellMaxCost;
					tmpSell.add(x);
					// d.pl("chro sell = " + curChro);
					curChro++;
					continue;
				

			}

			// ****************************************************

			// price shift <amount> shift <amount> shift <amount>
			
			

			if (tmpAllShopUniqueDone < allBlockInGameAsList.size() ) {
				d.pl("tmpAllShop > "  + curChro);
				
				d.pl("tmpAllShop : " + tmpAllShopUniqueDone + " = " + (allBlockInGameAsList.size() ));
				
					if ( allBlockInGameAsList.size() - tmpAllShopUniqueDone  < minShopSize && 
							 allBlockInGameAsList.size() - tmpAllShopUniqueDone  > 0) {
						d.pl("last shop can't have item to fit minshopsize = " + minShopSize + " , " + tmpAllShopUniqueDone + "/" + allBlockInGameAsList.size());
					
						return ;
					}
				

				if (  (tmpAllShop.size() < allBlockInGameAsList.size()-1  && tmpReading.size() >= (1 + (minShopSize * 2)) && tmpReading.size() <= (1 + maxShopSize * 2)
						&& tmpReading.size() % 2 == 1)
						
						|| ( (tmpAllShop.size() == allBlockInGameAsList.size()-1  && tmpReading.size() >= (1 + (minShopSize * 2)) && tmpReading.size() <= (1 + maxShopSize * 2)
						&& tmpReading.size() % 2 == 1))
						
						) {

					
					
					double tmpReadChro = chromosome[curChro];
				
					
					if (tmpReadChro <= 0 || tmpReading.size() == (1 + maxShopSize * 2)) { // that
																									// mean
																									// do
																									// it
																									// now
						tmpReadChro = Math.abs(chromosome[curChro]);
						d.pl(" the shop curchro " + curChro + " | " + tmpReading.size() + " | " + tmpAllShop.size() + " | " + tmpAllShopUniqueDone);
						
						 d.pl("size = " + tmpReading.size() + " .. " + curChro
						 + " unique " + tmpAllShopUniqueDone);

						AllShop x = new AllShop();

						x.PlayPrice = tmpReading.get(0) * ShopMaxCost;

						for (int i = 1; i < tmpReading.size(); i++) {
							int itemSlot = getNextUnuseMissionItem(
									(int) (tmpReading.get(i).doubleValue() * swapMultipy), curMissionItemSwapPosition,
									usedItShopList);
							curMissionItemSwapPosition = itemSlot;

							x.Item[x.size] = allBlockInGameAsList.get(itemSlot).theName;
							x.data[x.size] = allBlockInGameAsList.get(itemSlot).data;
							x.amount[x.size] = (int) (tmpReading.get(i + 1).doubleValue()
									* allBlockInGameAsList.get(itemSlot).maxStack);
							i++;

							tmpAllShopUniqueDone++;
							x.size++;
						}

						tmpAllShop.add(x);
						tmpReading.clear();
						curChro++;
						continue;

					} else {

						tmpReading.add(Math.abs(chromosome[curChro]));

						curChro++;
						continue;

					}

				} 
				
				else { // add to tmpReading

					tmpReading.add(Math.abs(chromosome[curChro]));

					curChro++;
					continue;

				}

			} // mission

			
			/*d.pl("tmpAllShop !@ " + tmpAllShop.size() + " , unique " + tmpAllShopUniqueDone);
			 d.pl("done shop");*/
			// ****************************************************
			// ****************************************************

			// grab level
			// <lv Type> id:data:amount (1 item then end)
			// if that are lv greb Inventory <lv type> id:data:amount
			// id:data:amount etc...
			// then reward <10 times for block> <10 times for item>

			// <lv type> shift : amount ?:?:? ?:?:?
			// <10 times for block> <amount> <10 times for item> <amount>

		
			//d.pl("space " + curChro);
			
			
			for (int i = 0; i < Main.co.allBlockInGameAsList.size(); i++) {
				usedItNeedList[i] = false;
				usedItRewardList[i] = false;
			}

			if (tmpLV.size() <= maxLV) {
				// d.pl("tmpLV " + tmpLV.size() + " , tmpReading " +
				// tmpReading.size() + " , curChro " + curChro);

				if (tmpReading.size() >= 2) {
					int epicMode = ((int) (tmpReading.get(0).doubleValue() * 100) % 4);
					LV1000Type x = new LV1000Type();
					x.lvmode = epicMode;

					if (epicMode < 3 || (epicMode == 3 && tmpReading.size() - 1 >= minItemForCompleteMission
							&& (tmpReading.size() - 1 <= maxItemForCompleteMission || chromosome[curChro] <= 0))) { // 1
																													// break
						// it's mean LV break drop place MODE

						// add lv need item list
						for (int i = 1; i < tmpReading.size(); i++) {
							int itemSlot = getNextBlockSwapMissionType(
									(int) (tmpReading.get(i).doubleValue() * swapMultipy), curMissionItemSwapPosition,
									2, usedItNeedList);
							curMissionItemSwapPosition = itemSlot;

							int tmpAmount = (int) (tmpReading.get(i).doubleValue()
									* allBlockInGameAsList.get(itemSlot).maxStack);
							if (tmpAmount <= 0) {
								tmpAmount = 1;
							}

							x.needItem[x.needSize] = allBlockInGameAsList.get(itemSlot).theName;
							x.needData[x.needSize] = allBlockInGameAsList.get(itemSlot).data;
							x.needAmount[x.needSize] = tmpAmount;
							
							tmpUseItLVUniqueCount ++;

							x.needSize++;
						}

						// add reward part

						// add reward block part
						for (int i = 0; i < maxRewardDiffBlockType && curChro < dnaSize; i++) {
							double tmpReadChro = chromosome[curChro];
							if (i == 0) {
								tmpReadChro = Math.abs(tmpReadChro);
							}

							// next Add part
							if (tmpReadChro <= 0) {
								continue;
							}

							int itemSlot = getNextBlockSwapMissionType((int) (tmpReadChro * swapMultipy),
									curMissionItemSwapPosition, 0, usedItRewardList);
							curMissionItemSwapPosition = itemSlot;

							x.rewardItem[x.rewardSize] = allBlockInGameAsList.get(itemSlot).theName;
							x.rewardData[x.rewardSize] = allBlockInGameAsList.get(itemSlot).data;

							double tmpAmount = Math.abs(chromosome[curChro]);
							curChro++;

							if (tmpAmount > 1) {
								tmpAmount = 1;
							}
							double tmpAmountInt = tmpAmount * allBlockInGameAsList.get(itemSlot).maxStack;
							if ((int) tmpAmountInt < 0) {
								tmpAmountInt = 1;
							}

							x.rewardAmount[x.rewardSize] = (int) (tmpAmountInt);

							x.rewardSize++;
							tmpUsedItRewardUniqueCount ++;
						}

				

						tmpLV.add(x);
						tmpReading.clear();
						// curChro++;
						continue;

					} else {
						tmpReading.add(Math.abs(chromosome[curChro]));

						curChro++;
						continue;
					}

				} else { // add to tmpReading

					tmpReading.add(Math.abs(chromosome[curChro]));

					curChro++;
					continue;

				}

			} else {
				d.pl("break as curChro = " + curChro);
				break; // lv complete
			}
		} // while

		d.pl("abc");
	}

	public int getNextBlockSwapMissionType(int swapNextxTime, int curMissionItemSwapPosition, int iNeedBlock,
			boolean usedItList[]) {
		// ineedblock 0 = block , 1 = item , 2 = both
		int looping = curMissionItemSwapPosition;
		int returnId = -1;

		do {

			do {
				looping++;
				if (looping >= allBlockInGameAsList.size()) {
					looping = 0;
				}

			} while (usedItList[looping] == true);
			

			if (iNeedBlock == 2) { // both
				if (swapNextxTime > 0) {
					swapNextxTime--;
					continue;
				}
				returnId = looping;
				usedItList[looping] = true;
				break;
			} else if (iNeedBlock == 0) {
				if (allBlockInGameAsList.get(looping).isBlock == true) {
					if (swapNextxTime > 0) {
						swapNextxTime--;
						continue;
					}
					returnId = looping;
					usedItList[looping] = true;
					
					break;
				}
			} else if (iNeedBlock == 1) {
				if (allBlockInGameAsList.get(looping).isBlock == false) {
					if (swapNextxTime > 0) {
						swapNextxTime--;
						continue;
					}
					returnId = looping;
					usedItList[looping] = true;
					
					break;
				}
			}

		} while (true);

		return returnId;
	}

	public int getNextUnuseMissionItem(int swapNextxTime, int curMissionItemSwapPosition, boolean usedItList[]) {

		int looping = curMissionItemSwapPosition;
		int returnId = -1;

		do {
			looping++;
			if (looping >= allBlockInGameAsList.size()) {
				looping = 0;
			}

			if (usedItList[looping] == false) {
				if (swapNextxTime > 0) {
					swapNextxTime--;
					continue;
				}
				returnId = looping;
				usedItList[looping] = true;

				break;
			}

		} while (true);

		return returnId;
	}

	public LinkedList<SellableType> sortSell(LinkedList<SellableType> sortPls) {
		int dataSize = sell.size();

		int indexSorted[] = new int[sell.size()];

		for (int i = 0; i < dataSize; i++) {
			indexSorted[i] = i;
		}

		for (int i = 0; i < dataSize; i++) {
			for (int j = 0; j < (dataSize - 1 - i); j++) {

				if (sortPls.get(indexSorted[j]).timeToGet < sortPls.get(indexSorted[j + 1]).timeToGet) {
					int t = indexSorted[j];

					indexSorted[j] = indexSorted[j + 1];

					indexSorted[j + 1] = t;

				}
			}

		}

		LinkedList<SellableType> tmp2 = new LinkedList<SellableType>();
		for (int i = 0; i < dataSize; i++) {
			tmp2.add(sortPls.get(indexSorted[i]));
		}
		return tmp2;
	}

	public String convertTimeToString(double abc) {
		String letter = "";
		double ceo = 0;

		if (abc >= 60 * 60) {
			letter = "h";
			ceo = abc / 60 / 60;

		} else if (abc >= 60) {
			letter = "m";

			ceo = abc / 60;
		} else if (abc >= 0) {
			letter = "s";

			ceo = abc / 60;
		}

		return ceo + " " + letter;

	}

	public double convertStringToTime(String abc) {
		String m[] = abc.split(" ");

		double cd = Double.parseDouble(m[0]);

		if (m[1].equalsIgnoreCase("h")) {
			cd = cd * 60 * 60;

		} else if (m[1].equalsIgnoreCase("m")) {
			cd = cd * 60;

		} else if (m[1].equalsIgnoreCase("s")) {
			cd = cd * 1;

		}

		return cd;

	}

	public void loadMissionBlockFile() {

		String filena = missionPath;
		File fff = new File(filena);

		try {

			allBlockInGame.clear();
			allBlockInGameAsList.clear();

			fff.createNewFile();

			d.pl("loading mission file : " + filena);
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(filena);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line

			String m[];

			while ((strLine = br.readLine()) != null) {

				m = strLine.split("\\s+");
				m = strLine.split(":");
				// Print the content on the console

				AllBlockInGameType miss = new AllBlockInGameType();
				miss.theName = m[0];
				miss.data = Byte.parseByte(m[1]);
				miss.maxStack = Integer.parseInt(m[2]);
				miss.isBlock = Boolean.parseBoolean(m[3]);

				// d.pl("...");
				// rs[rsMax - 1].mission = 0;

				if (allBlockInGame.get(miss.theName + ":" + miss.data) != null) {
					d.pl("loading not null");

				}
				allBlockInGame.put(miss.theName + ":" + miss.data, miss);
				allBlockInGameAsList.add(miss);
			}

			d.pl(" Loaded " + filena);

			in.close();
		} catch (Exception e) {// Catch exception if any
			d.pl("Error load " + filena + e.getMessage());
		}
	}

	public void loadSellableFile() {

		String filena = sellablePath;
		File fff = new File(filena);

		try {

			sell.clear();
			sellAsList.clear();

			fff.createNewFile();

			d.pl("loading sellable file : " + filena);
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(filena);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line

			String m[];

			while ((strLine = br.readLine()) != null) {

				m = strLine.split("\\s+");
				m = strLine.split(":");
				// Print the content on the console

				SellableType sb = new SellableType();
				sb.theName = m[0];
				sb.data = Byte.parseByte(m[1]);
				sb.maxStack = Integer.parseInt(m[2]);
				sb.timeToGet = convertStringToTime(m[3]);
				// rs[rsMax - 1].mission = 0;

				sellAsList.add(sb);
				sell.put(sb.theName + ":" + sb.data, sb);
			}

			d.pl(" Loaded " + filena);

			in.close();
		} catch (Exception e) {// Catch exception if any
			d.pl("Error load " + filena + e.getMessage());
		}
	}
}
