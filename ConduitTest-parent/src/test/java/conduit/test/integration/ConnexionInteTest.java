package conduit.test.integration;

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
public class ConnexionInteTest {

    @Autowired
    private AccountDS accountDS;

    private static DtoAccount accountCorrect = new DtoAccount();
    private static DtoAccount accountUsernameIncorrect = new DtoAccount();
    private static DtoAccount accountMdpIncorrect = new DtoAccount();

    @BeforeClass
    public static void setUpBeforeClass() {
        accountCorrect.setUsername("root");
        accountCorrect.setPassword("password");
        accountUsernameIncorrect.setUsername("root1");
        accountUsernameIncorrect.setPassword("password");
        accountMdpIncorrect.setUsername("root");
        accountMdpIncorrect.setPassword("password1");
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }

    @Test
    public void test1ConnexionMauvaisMdp() {

        try {
            accountDS.createAuthenticationToken(new JwtRequest(
                    accountMdpIncorrect.getUsername(), accountMdpIncorrect.getPassword()));
            Assert.fail("Exception should be thrown");
        } catch (Exception e) {
            Assert.assertEquals("INVALID_CREDENTIALS", e.getMessage());
        }

    }

    @Test
    public void test2ConnexionMauvaisAccount() {

        try {
            accountDS.createAuthenticationToken(new JwtRequest(
                    accountUsernameIncorrect.getUsername(), accountUsernameIncorrect.getPassword()));
            Assert.fail("Exception should be thrown");
        } catch (Exception e) {
            Assert.assertEquals("INVALID_CREDENTIALS", e.getMessage());
        }

    }
}
