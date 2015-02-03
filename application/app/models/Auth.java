package models;

import java.lang.Boolean;

public class Auth{

    public Auth(){

    }

    public static Boolean authenticate(String username, String password){

        if(username == null || password == null) return false;

        models.User user = models.User.find(username);

        if(user == null){
            return false;
        }

        if(user.password.equals(password)){
            return true;
        }else{
            return false;
        }
    }


    public static Boolean authorize(String username, int requiredRoleId){

        models.User user = models.User.find(username);

        if(user == null){
            return false;
        }

        if(user.roleId == requiredRoleId){
            return true;
        }

        return false;
    }

}