package dewdd_redstone_experimental;

import org.bukkit.Bukkit;

public class ActivateAllArea implements Runnable {
	private Redex redex;
	private boolean auto = false;

	public ActivateAllArea(Redex redex, boolean auto) {
		this.redex = redex;
		this.auto =  auto;
	}

	@Override
	public void run() {

		for (int lop = 0; lop < Redex.maxPopulation; lop++) {
			AreaType at = this.redex.listEx.get(lop);

			// recall own self
			ActivateSubArea caa = new ActivateSubArea(this.redex, lop);
			caa.run();
			/*Bukkit.getScheduler().scheduleSyncDelayedTask(DigEventListener2.ac,
					caa, 1);*/
		}

		this.redex.curMode = 1; // start calculating fitness
		dprint.r.printAll("Acvitate Done");
	}
}