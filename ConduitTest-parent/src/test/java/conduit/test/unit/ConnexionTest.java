package conduit.test.unit;

import conduit.test.controller.auth.JwtRequest;
import conduit.test.dto.DtoAccount;
import conduit.test.service.AccountDS;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConnexionTest {

    @Autowired
    private AccountDS accountDS;

    private static DtoAccount accountCorrect = new DtoAccount();
    private static DtoAccount accountIncorrect = new DtoAccount();

    @BeforeClass
    public static void setUpBeforeClass() {
        accountCorrect.setUsername("root");
        accountCorrect.setPassword("password");
        accountIncorrect.setUsername("root");
        accountIncorrect.setPassword("password1");
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }

    @Test
    public void test1Connexion() {

        try {
            accountDS.createAuthenticationToken(new JwtRequest(
                    accountCorrect.getUsername(), accountCorrect.getPassword()));
        } catch (Exception e) {
            Assert.fail("No exception should be thrown");
        }
        try {
            accountDS.createAuthenticationToken(new JwtRequest(
                    accountIncorrect.getUsername(), accountIncorrect.getPassword()));
            Assert.fail("Exception should be thrown");
        } catch (Exception e) {
            Assert.assertEquals("INVALID_CREDENTIALS", e.getMessage());
        }

    }
}
