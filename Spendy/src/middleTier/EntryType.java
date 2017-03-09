package middleTier;

public enum EntryType {
	INCOME,
	ENTERTAINMENT,
	BILLS,
	FOOD;	// catch-all for required food/drink

	public static String[] type() {
		EntryType[] entryTypes = values();
		String[] types = new String[entryTypes.length];

		for (int i = 0; i < entryTypes.length; i++) {
			types[i] = entryTypes[i].name();
		}

		return types;
	}
	
}


