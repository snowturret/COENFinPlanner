package middleTier;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;

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

    // could add it in UI part.
    public String md5(String input) {
        String md5 = null;
        if(null == input) return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

    public boolean validation(String password){
        if(this.password.equals(password)) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


    public ArrayList<Entry> getEntries() {
        return entries;
    }
}
