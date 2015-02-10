package goodtime.mod.Industrial.common.Machine;

import goodtime.mod.Industrial.common.Machine.ic2.Generator;

public class MachineLoader {
	public static void preInit() {
		Generator.preInit();
	}
	
	public static void init() {
		Generator.init();
	}
}
