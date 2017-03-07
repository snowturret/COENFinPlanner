package middleTier;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by guoyiruan on 3/6/17.
 */
public class Spendy {

    private ArrayList<User> users;
    private User currentUser;

    public Spendy(){

    }

    // hash password in ui side
    public void login(String name, String password) {
        for(User user : users) {
            if(user.getName().equals(name) && user.getPassword().equals(password)) {
                this.currentUser = user;
                break;
            } else {
                this.currentUser = null;
            }
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void cleanCurrentUser() {
        this.currentUser = null;
    }

    public void logout() {
        cleanCurrentUser();
    }

    public void createNewUser(String name, String password, String repeatPassword){
        for(User user: users) {
            if(user.getName().equals(name)) {
                System.out.println("exist username.");
                break;
            }
        }
        if(password.equals(repeatPassword)) {
            User newUser = new User(name, password);
            users.add(newUser);
            System.out.println("Success");
        } else {
            System.out.println("Password not equal");
        }
    }

    public void createNewEntry(Date entryDate, EntryType category, float value ,String description){
        this.currentUser.getEntries().add(new Entry (entryDate,category,value,description));
    }



}
