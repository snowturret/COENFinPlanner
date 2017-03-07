package middleTier;

import java.util.Date;

public class Entry {
	// TODO mutator/accessor for entry's values
	private Date entryDate;
	private EntryType category;
	private float value;
	private String description;
	
	
	public Entry(Date entrydate, EntryType category, float value, String description)
	{
		this.entryDate = entrydate;
		this.category = category;
		this.value = value;
		this.description = description;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public EntryType getCategory() {
		return category;
	}

	public float getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}


	// Just leaving here for now until we have a better place to put it
    public static void main(String[] args) {
        System.out.println("Hello, World");
    }


}
