package conduit.test.functional;

import conduit.test.config.JwtTokenUtil;
import conduit.test.dto.*;
import conduit.test.service.AccountDS;
import conduit.test.service.impl.ArticleWS;
import conduit.test.service.impl.ChefMagasinWS;
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
public class ApplicationFuncTest {

    @Autowired
    private AccountDS accountDS;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ArticleWS articleWS;
    @Autowired
    private VendeurWS vendeurWS;
    @Autowired
    private ChefMagasinWS chefMagasinWS;

    private static DtoAccount account = new DtoAccount();
    private static DtoAccount accountVendeur = new DtoAccount();
    private static DtoAccount accountVendeur2 = new DtoAccount();
    private static DtoArticle articleCorrect = new DtoArticle();
    private static DtoArticle articleCorrect2 = new DtoArticle();
    private static DtoPdg pdg = new DtoPdg();
    private static DtoPdg pdg2 = new DtoPdg();
    private static DtoChefMagasin chefMagasin = new DtoChefMagasin();
    private static DtoChefMagasin chefMagasin2 = new DtoChefMagasin();
    private static DtoChefMagasin chefMagasinCorrect = new DtoChefMagasin();
    private static DtoVendeur vendeurCorrect = new DtoVendeur();

    @BeforeClass
    public static void setUpBeforeClass() {
        account.setUsername("root");
        account.setPassword("password");

        accountVendeur.setUsername("vendeur");
        accountVendeur.setPassword("password");
        accountVendeur2.setUsername("vendeur2");
        accountVendeur2.setPassword("password");

        articleCorrect.setSerial("serial");
        articleCorrect.setName("article");
        articleCorrect.setCategorie("cate");
        articleCorrect.setDescription("desc");
        articleCorrect.setPrix(7.7);
        articleCorrect.setQuantite(1);

        articleCorrect2.setSerial("serial2");
        articleCorrect2.setName("article");
        articleCorrect2.setCategorie("cate");
        articleCorrect2.setDescription("desc");
        articleCorrect2.setPrix(7.7);
        articleCorrect2.setQuantite(1);

        pdg.setUsername("root");
        pdg.setPassword("password");
        pdg2.setUsername("root2");
        pdg2.setPassword("password");

        chefMagasin.setUsername("chefmagasin");
        chefMagasin.setPassword("password");
        chefMagasin2.setUsername("chefmagasin2");
        chefMagasin2.setPassword("password");

        chefMagasinCorrect.setUsername("chefmagasinCorrect");
        chefMagasinCorrect.setPassword("password");

        vendeurCorrect.setUsername("vendeurCorrect");
        vendeurCorrect.setPassword("password");
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }

    @Test
    public void test1Vendeur() {

        try {
            setSecurityContext(accountVendeur2);
            articleWS.create(articleCorrect);
        } catch (Exception e) {
            Assert.fail("No exception should be thrown");
        }
        try {
            setSecurityContext(accountVendeur);
            articleWS.update(articleCorrect);
            Assert.fail("Exception should be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Current user is not allowed!", e.getMessage());
        }
        try {
            setSecurityContext(accountVendeur);
            vendeurWS.update(accountVendeur2);
            Assert.fail("Exception should be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Fail to update vendeur!", e.getMessage());
        }
        try {
            setSecurityContext(accountVendeur2);
            articleWS.delete(articleCorrect.getSerial());
        } catch (Exception e) {
            Assert.fail("No exception should be thrown");
        }
        try {
            setSecurityContext(accountVendeur2);
            articleWS.getByName(articleCorrect.getSerial());
            Assert.fail("Exception should be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Fail to get article!", e.getMessage());
        }
    }

    @Test
    public void test2ChefMagasin() {
        try {
            setSecurityContext(chefMagasin);
            vendeurWS.create(vendeurCorrect);
            setSecurityContext(vendeurCorrect);
            articleWS.create(articleCorrect2);
        } catch (Exception e) {
            Assert.fail("No exception should be thrown");
        }
        try {
            setSecurityContext(vendeurCorrect);
            articleWS.update(articleCorrect2);

            setSecurityContext(chefMagasin);
            articleWS.update(articleCorrect2);
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
        try {
            setSecurityContext(chefMagasin);
            articleWS.delete(articleCorrect2.getSerial());
            vendeurWS.delete(vendeurCorrect.getUsername());
        } catch (Exception e) {
            Assert.fail("No exception should be thrown");
        }
        try {
            setSecurityContext(chefMagasin);
            vendeurWS.getByName(vendeurCorrect.getUsername());
            Assert.fail("Exception should be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Current user is not allowed!", e.getMessage());
        }
        try {
            setSecurityContext(chefMagasin);
            articleWS.getByName(articleCorrect.getSerial());
            Assert.fail("Exception should be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Fail to get article!", e.getMessage());
        }
    }

    @Test
    public void test3Pdg() {
        try {
            setSecurityContext(pdg);
            chefMagasinWS.create(chefMagasinCorrect);
            setSecurityContext(chefMagasinCorrect);
            vendeurWS.create(vendeurCorrect);
            setSecurityContext(vendeurCorrect);
            articleWS.create(articleCorrect2);

            setSecurityContext(chefMagasinCorrect);
            vendeurWS.update(vendeurCorrect);
            articleWS.update(articleCorrect2);
        } catch (Exception e) {
            Assert.fail("No exception should be thrown");
        }
        try {
            setSecurityContext(chefMagasinCorrect);
            articleWS.delete(articleCorrect2.getSerial());
            vendeurWS.delete(vendeurCorrect.getUsername());
        } catch (Exception e) {
            Assert.fail("No exception should be thrown");
        }
        try {
            setSecurityContext(chefMagasinCorrect);
            vendeurWS.getByName(vendeurCorrect.getUsername());
            Assert.fail("Exception should be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Current user is not allowed!", e.getMessage());
        }
        try {
            setSecurityContext(chefMagasinCorrect);
            articleWS.getByName(articleCorrect.getSerial());
            Assert.fail("Exception should be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Fail to get article!", e.getMessage());
        }
    }

    private void setSecurityContext(DtoAccount account) {
        UserDetails userDetails = accountDS.loadUserByUsername(account.getUsername());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}
