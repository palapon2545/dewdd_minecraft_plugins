package a_dewdd_skyblock_lv_1000_generator;

public class LV1000Type {
	public int lvmode = 0; // 0 - 5
	
	// to done
	public String[] needItem = new String[Core.maxItemForCompleteMission];
	public byte[] needData = new byte[Core.maxItemForCompleteMission];
	public int[] needAmount = new int[Core.maxItemForCompleteMission];
	
	public String[] rewardItem = new String[Core.maxRewardDiffItemType + Core.maxRewardDiffBlockType];
	public byte[] rewardData = new byte[Core.maxRewardDiffItemType + Core.maxRewardDiffBlockType];
	public int[] rewardAmount = new int[Core.maxRewardDiffItemType + Core.maxRewardDiffBlockType];
	
	
	
}


