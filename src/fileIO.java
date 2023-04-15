import java.io.*;
import java.util.*;

public class fileIO {
    public static void writeToFile(String fileName, ArrayList<RegisteredUsers> regUsers){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(regUsers);
            System.out.println("Product information written to file successfully!");
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            System.out.println("An error occurred while writing product information to file: ");
            ex.printStackTrace();
        }
    }

    public static ArrayList<RegisteredUsers> readFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
        ArrayList<RegisteredUsers> regUsers = (ArrayList<RegisteredUsers>) ois.readObject();
        System.out.println("Product information read from file successfully!");
        ois.close();
        return regUsers;
        } catch (IOException | ClassNotFoundException ex) {
        System.out.println("An error occurred while reading product information from file: " + ex.getMessage());
        }
        return null;
    }
}
