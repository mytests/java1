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


public class AuthenticationTest {

    @Test
    public void authenticateExisting() {
        models.User user = new models.User("testuser", "testpassword", 0);
        user.save();
        boolean auth = models.Authentication.authenticate("testuser", "testpassword");
        assertThat(auth).isTrue();
    }

    @Test
    public void authenticateNonExisting() {

        boolean auth = models.Authentication.authenticate("idontexist", "testpassword");
        assertThat(auth).isFalse();
    }

    @Test
    public void authenticateNonWrongPass() {
        models.User user = new models.User("testuser", "testpassword", 0);
        user.save();
        boolean auth = models.Authentication.authenticate("testuser", "wrongpassword");
        assertThat(auth).isFalse();
    }


}
