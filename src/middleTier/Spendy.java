package middleTier;

import java.io.*;
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
    public static User currentUser;
    private static final String FILE_PATH = "DB.txt";

    public Spendy(){

    }

    /**
     * usage: verify the input name; if the name exists in database  return false;  otherwise return true;
     * @param name
     * @return
     */
    public static boolean validUsername(String name) {
        for(User user : users) {
            if(user.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    /**
     * login; check the username and password are corresponding to database; if the user exists in db; set the user to
     * current user and return true; otherwise; set currentUser to null and return false;
     * @param name
     * @param password
     * @return
     */
    public static boolean login(String name, String password) {
        for(User user : users) {
            if(user.getName().equals(name) && user.getPassword().equals(password)) {
                currentUser = user;
                return true;
            }
        }
        currentUser = null;
        return false;
    }

    /**
     * create a new user; and set this user to current user;
     * @param name
     * @param password
     */
    public static void register(String name, String password) {
        User u = new User(name, password);
        users.add(u);
        currentUser = u ;
    }

    /**
     * getter; get the currentUser;
     * @return
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * setter; reset the CurrentUser;
     */
    public static void cleanCurrentUser() {
        currentUser = null;
    }

    public static void logout() {
        cleanCurrentUser();
    }

    /**
     * create a new entry for currentUser
     * @param entryDate
     * @param category
     * @param value
     * @param description
     */
    public static void createNewEntry(Date entryDate, EntryType category, float value ,String description){
        currentUser.getEntries().add(new Entry (entryDate,category,value,description));
    }

    //file format should be in order -->  date(MM/dd/yyyy), category, value and description
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

    /**
     * return the tracking entries; based on the parameters; if the entry type is ALL; return all entries from the
     * startDate to endDate. if the entry type is a particular type; verify it and return the correct entries;
     * @param startDate
     * @param endDate
     * @param category
     * @return arraylist of entries
     */
    public static ArrayList<Entry> trackingResults(Date startDate, Date endDate,EntryType category){
        ArrayList<Entry> results = new ArrayList<>();
        if (category == EntryType.ALL) {
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

    /**
     * Save data; iterate each user in list of users; and iterate each entry from the list; write to a txt file;
     * @throws IOException
     */
    public static void saveFile ()throws IOException {
        File fout = new File("DB.txt");
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        for(User user : users) {
            if (user.getEntries().size()== 0 || user.getEntries() == null) {
                bw.write(user.getName() + "," + user.getPassword());
                System.out.println("no entries");
                bw.newLine();
                continue;
            }
            for(int i = 0; i < user.getEntries().size(); i++) {
                if (user.getEntries().get(i).getDescription() == null) {
                    bw.write(user.getName() + "," + user.getPassword() + "," + user.getEntries().get(i).getEntryDate().toString()
                            + "," + user.getEntries().get(i).getCategory().toString() + "," + String.valueOf(user.getEntries().get(i).getValue()));
                }else {
                    bw.write(user.getName() + "," + user.getPassword() + "," + user.getEntries().get(i).getEntryDate().toString()
                            + "," + user.getEntries().get(i).getCategory().toString() + "," + String.valueOf(user.getEntries().get(i).getValue()) + "," +
                            user.getEntries().get(i).getDescription());
                }

                bw.newLine();
            }
        }
        bw.close();
        System.out.println("Success");
    }

    /**
     * check the user is exist or not; if it already exist; append the entry to the existed user; if not create a new user
     * and add the entry to the new user.
     * @param name
     * @param password
     * @return
     */
    public static boolean duplicateUser(String name, String password) {
        for (User user : users) {
            if (user.getName().equals(name) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    /**
     * Read file; add all the data to the system.
     * @param FILE_PATH
     * @throws FileNotFoundException
     */
    public static void readFile (String FILE_PATH)throws FileNotFoundException {
        Scanner scan = new Scanner(new File(FILE_PATH));
        User current = null;
        while (scan.hasNextLine()) {
            String line = scan.nextLine().trim();
            if (line.length() == 0 || line == null) {
                break;
            }
            String[] lineArray = line.split(",");

            if (!duplicateUser(lineArray[0], lineArray[1])) {
                current = new User(lineArray[0], lineArray[1]);
                users.add(current);
            }
            if (lineArray.length <= 2) {
                break;
            } else {
                String dateStr =lineArray[2] ;
                DateFormat readFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                Date startDate = null;
                try {
                    startDate = readFormat.parse( dateStr );
                    EntryType type = null;
                    for (EntryType e : EntryType.values()) {
                        if (lineArray[3].equals(e.toString())) {
                            type = e;
                            break;
                        }
                    }
                    float value = Float.valueOf(lineArray[4]);
                    if (lineArray.length == 5) {
                        current.getEntries().add(new Entry(startDate, type, value, null));
                    } else {
                        current.getEntries().add(new Entry(startDate, type, value, lineArray[5]));
                    }
                } catch ( ParseException e ) {
                    e.printStackTrace();
                }
            }
        }
    }



}
