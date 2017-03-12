
package middleTier;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class StoreUser {

    public static void storeUser(String filepath, User user) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(user).toString());
        File file = new File(filepath);
        FileWriter fw = null;
        try {
            // Below constructor argument decides whether to append or override
            fw = new FileWriter(file, true);

            fw.write(gson.toJson(user).toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
