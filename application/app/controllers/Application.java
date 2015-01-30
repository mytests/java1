package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import play.mvc.Http.RequestBody;

import views.html.*;

import java.lang.Object;

import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.mvc.Http.RequestBody;

import java.util.Map;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result login() {

        final Map<String, String[]> formData = request().body().asFormUrlEncoded();

        models.User.seedUsers();

        if(formData.get("username") == null || formData.get("password") == null){
            return badRequest("ko");
        }

        String username = formData.get("username")[0];
        String password = formData.get("password")[0];

        if(models.Authentication.authenticate(username, password) == true){
            return ok("Logged in");
        }

        models.User user = models.User.find("user1");
        if(user.username == "user1") return ok("Caca");

        ObjectNode result = Json.newObject();
        result.put("exampleField1", "foobar");
        result.put("exampleField2", "Hello world!");

        return badRequest(result);
    }

    public static Result loginForm() {
        return ok(login.render("", ""));
    }
}
