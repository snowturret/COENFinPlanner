package middleTier;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by guoyiruan on 3/6/17.
 */
public class Spendy {

    private static ArrayList<User> users = new ArrayList<>();
    private static User currentUser;

    public Spendy(){
//        try {
//            importFile("src/test.csv");
//        }catch (FileNotFoundException ex) {
//            System.out.println("something wrong with file");
//        } finally {
//
//        }
    }

    public static boolean login(String name, String password) {
        for(User user : users) {
            if(user.getName().equals(name) && md5(user.getPassword()).equals(md5(password))) {
                currentUser = user;
                return true;
            } else {
                currentUser = null;
            }
        }
        return false;
    }

    public static void register(String name, String password) {
        String md5Password = md5(password);
        User u = new User(name, md5Password);
        users.add(u);
        currentUser = u ;
    }
    
    public static String md5(String input) {
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
    
    public static User getCurrentUser() {
        return currentUser;
    }

    public void cleanCurrentUser() {
        this.currentUser = null;
    }

    public void logout() {
        cleanCurrentUser();
    }

    public static void createNewEntry(Date entryDate, EntryType category, float value ,String description){
        currentUser.getEntries().add(new Entry (entryDate,category,value,description));
    }

    //file format should be date --> MM/dd/yyyy, category, value and description
    public static void importFile(String FILE_PATH) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(FILE_PATH));
        while (scan.hasNextLine()) {
            String line = scan.nextLine().trim();
            String[] lineArray = line.split(",");
            for(int i = 0; i < lineArray.length; i++) {
                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    Date startDate = formatter.parse(lineArray[0]);
                    EntryType type = null;
                    for(EntryType e : EntryType.values()){
                        if (lineArray[1].equals(e.toString())){
                            type = e;
                            break;
                        }
                    }
                    float value = Float.valueOf(lineArray[2]);
                    createNewEntry(startDate,type,value,lineArray[3]);
                }catch (ParseException e) {
                    System.out.println(e);
                }
            }
        }
    }

    public static ArrayList<Entry> trackingResults(Date startDate, Date endDate,EntryType category){
        ArrayList<Entry> results = new ArrayList<>();
        if (category == null) {
            if (startDate == null && endDate == null) {
                return currentUser.getEntries();
            } else {
                if (startDate == null) {
                    for(Entry e: currentUser.getEntries()) {
                        if (e.getEntryDate().compareTo(endDate) <= 0) {
                            results.add(e);
                        }
                    }
                } else if (endDate == null){
                    for(Entry e: currentUser.getEntries()) {
                        if (e.getEntryDate().compareTo(startDate) >= 0) {
                            results.add(e);
                        }
                    }
                } else {
                    for(Entry e: currentUser.getEntries()) {
                        if (e.getEntryDate().compareTo(startDate) >= 0 && e.getEntryDate().compareTo(endDate) <= 0) {
                            results.add(e);
                        }
                    }
                }
            }
        } else {
            if (startDate == null && endDate == null) {
                for(Entry e: currentUser.getEntries()) {
                    if (e.getCategory().equals(category)) {
                        results.add(e);
                    }
                }
            } else {
                if (startDate == null) {
                    for(Entry e: currentUser.getEntries()) {
                        if (e.getEntryDate().compareTo(endDate) <= 0 && e.getCategory().equals(category)) {
                            results.add(e);
                        }
                    }
                } else if (endDate == null){
                    for(Entry e: currentUser.getEntries()) {
                        if (e.getEntryDate().compareTo(startDate) >= 0 && e.getCategory().equals(category)) {
                            results.add(e);
                        }
                    }
                } else {
                    for(Entry e: currentUser.getEntries()) {
                        if (e.getEntryDate().compareTo(startDate) >= 0 && e.getEntryDate().compareTo(endDate) <= 0
                                && e.getCategory().equals(category)) {
                            results.add(e);
                        }
                    }
                }
            }
        }
        return results;
    }
}
