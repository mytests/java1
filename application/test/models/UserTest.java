import java.lang.Boolean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


public class UserTest {

    @Test
    public void userConstruct() {
        models.User user = new models.User("testuser", "testpassword", 0);
        assertThat(user.username).isEqualTo("testuser");
        assertThat(user.password).isEqualTo("testpassword");
        assertThat(user.roleId).isEqualTo(0);
    }

    @Test
    public void userSave() {
        models.User user = new models.User("testuser", "testpassword", 0);
        user.save();
        models.User savedUser = models.User.find("testuser");
        assertThat(savedUser.username).isEqualTo("testuser");
        assertThat(savedUser.password).isEqualTo("testpassword");
        assertThat(savedUser.roleId).isEqualTo(0);
    }

    @Test
    public void userFindExisting() {
        models.User user = models.User.find("testuser");
        assertThat(user.username).isEqualTo("testuser");
    }

    @Test
    public void userFindNonExisting() {
        models.User user = models.User.find("idontexist");
        assertThat(user).isNull();
    }

    @Test
    public void userFindSeed() {
        models.User.seedUsers();
        models.User user = models.User.find("user2");
        assertThat(user.username).isEqualTo("user2");
    }


}
