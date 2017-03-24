package middleTier;

import java.util.Vector;

/**
 * enum class; category;
 */
public enum EntryType {
	ALL("all"),
	BILLS("bills"),
	ENTERTAINMENT("entertainment"),
	SHOPPING("shopping"),
	FOOD("food"),
	INCOME ("income"),
	OTHER("other");// catch-all for required food/drink

	private final String name;
	private EntryType(String s) {
		name = s;
	}

	public String toString() {
		return this.name;
	}

	/**
	 * get the entry type except income and all; the usage of combo box
	 * @return list of entryType;
	 */
	public static Vector<EntryType> getboxes() {
		Vector<EntryType> vector = new Vector<>();
		for (EntryType type : EntryType.values()) {
			if (type != INCOME && type != ALL) {
				vector.add(type);
			}
		}
		return vector;

	}
}


