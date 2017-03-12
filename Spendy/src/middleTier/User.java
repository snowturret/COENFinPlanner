package middleTier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/**
 * Created by guoyiruan on 3/6/17.
 */
public class User {

    private String name;
    private String password;
    private ArrayList<Entry> entries;

    public User(String name, String password) {
        this.name = name;
        this.password = Spendy.md5(password);
        this.entries = new ArrayList<>();
    }

    public boolean validation(String password) {
        if (this.password.equals(Spendy.md5(password))) {
            return true;
        }
        return false;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public ArrayList<Entry> getEntries() {
        return this.entries;
    }

    public ArrayList<Entry> sortByDate() {
        Comparator<Entry> resultComparator = new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return o2.getEntryDate().compareTo(o1.getEntryDate());
            }
        };
        ArrayList<Entry> result = new ArrayList<Entry>((Collection<? extends Entry>) resultComparator);
        return result;
    }

    public ArrayList<Entry> sortByCategory() {
        Comparator<Entry> resultComparator = new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                // ascending order
                return o1.getCategory().compareTo(o2.getCategory());
            }
        };
        ArrayList<Entry> result = new ArrayList<Entry>((Collection<? extends Entry>) resultComparator);
        return result;
    }
}
