package middleTier;

public enum EntryType {
	INCOME ("income"),
	ENTERTAINMENT("entertainment"),
	BILLS("bills"),
	FOOD("food");	// catch-all for required food/drink

	private final String name;
	private EntryType(String s) {
		name = s;
	}

	public String toString() {
		return this.name;
	}
	
}


