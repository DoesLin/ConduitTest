package conduit.test.unit;

import conduit.test.config.JwtTokenUtil;
import conduit.test.dto.DtoAccount;
import conduit.test.dto.DtoChefMagasin;
import conduit.test.dto.DtoVendeur;
import conduit.test.service.AccountDS;
import conduit.test.service.impl.ArticleWS;
import conduit.test.service.impl.VendeurWS;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminRessourcesTest {

    @Autowired
    private AccountDS accountDS;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ArticleWS articleWS;
    @Autowired
    private VendeurWS vendeurWS;

    private static DtoChefMagasin chefMagasin = new DtoChefMagasin();
    private static DtoChefMagasin chefMagasin2 = new DtoChefMagasin();
    private static DtoVendeur vendeurCorrect = new DtoVendeur();
    private static DtoVendeur vendeurIncorrect = null;

    @BeforeClass
    public static void setUpBeforeClass() {
        chefMagasin.setUsername("chefmagasin");
        chefMagasin.setPassword("password");
        chefMagasin2.setUsername("chefmagasin2");
        chefMagasin2.setPassword("password");

        vendeurCorrect.setUsername("vendeurCorrect");
        vendeurCorrect.setPassword("password");
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }

    @Test
    public void test1AjoutVendeurs() {

        try {
            setSecurityContext(chefMagasin);
            vendeurWS.create(vendeurCorrect);
        } catch (Exception e) {
            Assert.fail("No exception should be thrown");
        }
//        try {
//            setSecurityContext(account);
//            articleWS.create(chefMagasin);
//            Assert.fail("Exception should be thrown");
//        } catch (Exception e) {
//            Assert.assertEquals("Current user is not allowed!", e.getMessage());
//        }
        try {
            setSecurityContext(chefMagasin);
            vendeurWS.create(vendeurCorrect);
            Assert.fail("Exception should be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Fail to create vendeur!", e.getMessage());
        }
    }

    @Test
    public void test2ModifVendeurs() {
        try {
            setSecurityContext(chefMagasin);
            vendeurWS.update(vendeurCorrect);
        } catch (Exception e) {
            Assert.fail("No exception should be thrown");
        }

        try {
            setSecurityContext(chefMagasin2);
            vendeurWS.update(vendeurCorrect);
            Assert.fail("Exception should be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Current user is not allowed!", e.getMessage());
        }

//        try {
//            setSecurityContext(account);
//            articleWS.update(chefMagasin);
//        } catch (Exception e) {
//            Assert.fail("No exception should be thrown");
//        }
    }

    @Test
    public void test3DeleteVendeurs() {

        try {
            setSecurityContext(chefMagasin2);
            vendeurWS.delete(vendeurCorrect.getUsername());
            Assert.fail("Exception should be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Current user is not allowed!", e.getMessage());
        }

        try {
            setSecurityContext(chefMagasin);
            vendeurWS.delete(vendeurCorrect.getUsername());
        } catch (Exception e) {
            Assert.fail("No exception should be thrown");
        }
    }

    private void setSecurityContext(DtoAccount account) {
        UserDetails userDetails = accountDS.loadUserByUsername(account.getUsername());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}
