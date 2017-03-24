package middleTier;

import java.util.Date;

public class Entry {
	// TODO mutator/accessor for entry's values
	private Date entryDate;
	private EntryType category;
	private float value;
	private String description;

	/**
	 * constructor
	 * @param entrydate
	 * @param category
	 * @param value amount of spending
	 * @param description comments
	 */
	public Entry(Date entrydate, EntryType category, float value, String description)
	{
		this.entryDate = entrydate;
		this.category = category;
		this.value = value;
		this.description = description;
	}

	/**
	 * getter; get the entry date;
	 * @return date
	 */
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * getter; get the category;
	 * @return EntryType
	 */
	public EntryType getCategory() {
		return category;
	}

	/**
	 * getter; get the amount of spending;
	 * @return float value
	 */
	public float getValue() {
		return value;
	}

	/**
	 * getter; get the comments
	 * @return string description.
	 */
	public String getDescription() {
		return description;
	}

}
