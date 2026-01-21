package login;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.Loginpage;
import pages.SecurePage;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

        String valid_username = "tomsmith";
        String valid_password = "SuperSecretPassword!";
        String invalid = "tesr";



        @Test
        public void validlogin() throws InterruptedException {
        Loginpage loginpage = homePage.clickon_login_button();
        loginpage.write_username(valid_username);
        loginpage.write_password(valid_password);
        SecurePage securePage = loginpage.clickon_login_button();
        String Actual = securePage.actual_message();
        String Expect = "You logged into a secure area!";
        assertTrue(Actual.contains(Expect));
        }

        @Test
        public void invalidusername()
        {
                Loginpage loginpage = homePage.clickon_login_button();
                loginpage.write_username(invalid);
                loginpage.write_password(valid_password);
                loginpage.wrong_login();
                String Actual = loginpage.actual_message();
                String Expect = "Your username is invalid!";
                assertTrue(Actual.contains(Expect));
        }

        @Test
        public void invalidpassword()
        {
                Loginpage loginpage = homePage.clickon_login_button();
                loginpage.write_username(valid_username);
                loginpage.write_password(invalid);
                loginpage.wrong_login();
                String Actual = loginpage.actual_message();
                String Expect = "Your password is invalid!";
                assertTrue(Actual.contains(Expect));
        }

}
