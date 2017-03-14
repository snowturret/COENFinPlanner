package middleTier;

import java.util.ArrayList;

/**
 * Created by guoyiruan on 3/6/17.
 */
public class User {

    private String name;
    private String password;
    private ArrayList<Entry> entries;

    public User(String name, String password){
        this.name = name;
        this.password = password;
        this.entries = new ArrayList<>();
    }

    public boolean validation(String password){
        if(this.password.equals(password)) {
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

    public ArrayList<Entry> getEntries(){
        return this.entries;
    }

}
