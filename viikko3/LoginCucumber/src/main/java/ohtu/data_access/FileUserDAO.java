/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sara
 */
public class FileUserDAO implements UserDao {

    private String filename;

    public FileUserDAO(String filename) {
        this.filename = filename;
    }

    @Override
    public List<User> listAll() {
        try {
            ArrayList<User> users = new ArrayList<User>();

            Scanner scanner = new Scanner(new File(this.filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splits = line.split(" ");
                users.add(new User(splits[0], splits[1]));
            }

            scanner.close();
            return users;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<User>();
        }
    }

    @Override
    public User findByName(String name) {
        List<User> all = listAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getUsername() == name) {
                return all.get(i);
            }
        }

        return null;
    }

    @Override
    public void add(User user) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(this.filename, true));
            output.write(user.getUsername() + " " + user.getPassword());
            output.newLine();

            output.flush();
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
