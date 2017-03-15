package middleTier;

import java.util.Vector;

public enum EntryType {
	ALL("all"),
	BILLS("bills"),
	ENTERTAINMENT("entertainment"),
	EDUCATION("education"),
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


