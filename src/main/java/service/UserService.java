package service;

import com.fasterxml.jackson.databind.SerializationFeature;
import model.User;
import model.Users ;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final String XML_FILE_PATH = "users.xml";
    private XmlMapper xmlObjectMapper = new XmlMapper();
    private static List<User> users = new ArrayList<>();

    public UserService() {
        users = fileRead(XML_FILE_PATH);
    }

    private boolean hasUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public User addUser(User user) {
        if (hasUsername(user.getUsername())) {
            return null;
        }
        users.add(user);
        fileWrite(users, XML_FILE_PATH);
        return user;
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void deleteUser(User user) {
        users.remove(user);
        fileWrite(users, XML_FILE_PATH);
    }

    private void fileWrite(List<User> users, String path) {
        try {
            xmlObjectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            Users usersWrapper = new Users(users);
            xmlObjectMapper.writeValue(new File(path), usersWrapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<User> fileRead(String path) {
        File file = new File(path);
        if (!file.exists() || file.length() == 0) {
            System.out.println("File is empty");
            return new ArrayList<>();
        }
        try {
            Users usersWrapper = xmlObjectMapper.readValue(file, Users.class);
            return usersWrapper.getUsers();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error!");
            return new ArrayList<>();
        }
    }

}
