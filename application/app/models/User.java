package models;

import java.util.HashMap;

public class User{

    private static HashMap<String, models.User> users = new HashMap();

    public String username;
    public String password;
    public int roleId;

    public User(String username, String password, int roleId) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }

    public static void seedUsers(){
        models.User user = new models.User("user1", "pass", 1);
        user.save();
        models.User user1 = new models.User("user2", "pass", 2);
        user1.save();
        models.User user2 = new models.User("user3", "pass", 3);
        user2.save();
    }

    public void save(){
        users.put(this.username, this);
    }

    private void delete(){
        users.remove(this.username);
    }

    public static models.User find(String username){

        models.User user = users.get(username);

        if(user != null){
            return user;
        }

        return null;
    }
}