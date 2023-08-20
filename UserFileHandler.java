package dz3.2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserFileHandler {
    public static List<User> readUsersFromFile(String fileName) {
        List<User> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    String email = parts[2];
                    userList.add(new User(name, age, email));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return userList;
    }

    public static void writeUsersToJsonFile(List<User> userList, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("[\n");
            for (int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                writer.write("  {\n");
                writer.write("    \"name\": \"" + user.getName() + "\",\n");
                writer.write("    \"age\": " + user.getAge() + ",\n");
                writer.write("    \"email\": \"" + user.getEmail() + "\"\n");
                writer.write("  }");
                if (i < userList.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]\n");
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static class Main {
        public static void main(String[] args) {
            List<User> users = UserFileHandler.readUsersFromFile("users.txt");

            for (User user : users) {
                System.out.println("Name: " + user.getName());
                System.out.println("Age: " + user.getAge());
                System.out.println("Email: " + user.getEmail());
                System.out.println();
            }

            UserFileHandler.writeUsersToJsonFile(users, "users.json");
        }
    }
}
