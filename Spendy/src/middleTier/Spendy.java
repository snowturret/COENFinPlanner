package middleTier;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by guoyiruan on 3/6/17.
 */
public class Spendy {

    private static ArrayList<User> users = new ArrayList<>();
    private static User currentUser;

    public Spendy(){

    }

    public static boolean login(String name, String password) {
        for(User user : users) {
            if(user.getName().equals(name) && user.getPassword().equals(password)) {
                currentUser = user;
                return true;
            } else {
                currentUser = null;
            }
        }
        return false;
    }

    public static void register(String name, String password) {
        User u = new User(name, password);
        users.add(u);
        currentUser = u ;
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

    public void importFile(String FILE_PATH) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(FILE_PATH));
        String[] entries;
        while (scan.hasNextLine()) {
            String line = scan.nextLine().trim();
//			System.out.println(line);
            String[] lineArray = line.split(" ");
            for(int i = 0; i < lineArray.length; i++) {

            }
        }
    }



}
