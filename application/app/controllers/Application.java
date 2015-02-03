package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import play.mvc.Http.RequestBody;

import play.mvc.Http.Session;


import views.html.*;

import java.lang.Object;

import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.mvc.Http.RequestBody;

import java.util.Map;

public class Application extends Controller {

    public static Result index() {
        if(!sessionCheck(session("username"))){
            return redirect("/login");
        }

        return ok(index.render(session("username")));
    }

    public static Result logout() {
        session("expiration_time", "0");

        return ok("Logged out");
    }

    public static Result login() {

        final Map<String, String[]> formData = request().body().asFormUrlEncoded();

        models.User.seedUsers();

        if(formData.get("username") == null || formData.get("password") == null){
            return badRequest("ko");
        }

        String username = formData.get("username")[0];
        String password = formData.get("password")[0];

        if(models.Auth.authenticate(username, password) == true){
            sessionUpdate(username);
            return ok("Logged in");
        }

        //login failed
        return redirect("/login");

        //ObjectNode result = Json.newObject();
        //result.put("result", "ok");

        //return badRequest(result);
    }

    public static Result loginForm() {
        return ok(login.render("", ""));
    }


    public static Result restricted(int id){
        if(!sessionCheck(session("username"))){
            return redirect("/login");
        }

        int roleId=0;
        switch(id){
            case 1:
                roleId = 1;
                break;
            case 2:
                roleId = 2;
                break;
            case 3:
                roleId = 3;
                break;
        }

        if(!models.Auth.authorize(session("username"), roleId)){
            return unauthorized("Acceso restringido");
        }

        return ok(restricted.render(session("username"), roleId));
    }

    private static boolean sessionCheck(String username){

        if(username == "") {
            return false;
        }

        models.User user = models.User.find(username);

        if(user == null){
            return false;
        }

        //check if session has expired
        long sessionExpirationTime = Long.valueOf(session("expiration_time")).longValue();

        long currentTime = System.currentTimeMillis() / 1000L;

        if(sessionExpirationTime < currentTime){
            return false;
        }

        sessionUpdate(username);

        return true;
    }


    private static void sessionUpdate(String username){
        //update expiration time
        long currentTime = System.currentTimeMillis() / 1000L;
        long newExpirationTime = (currentTime + 300000L);
        session("expiration_time", String.valueOf(newExpirationTime));//5 minutes in miliseconds
        session("username", username);
    }
}
