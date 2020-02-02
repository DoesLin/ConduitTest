package conduit.test.unit;

import conduit.test.config.JwtTokenUtil;
import conduit.test.dto.DtoAccount;
import conduit.test.dto.DtoArticle;
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
public class AdminArticlesUnitTest {

    @Autowired
    private AccountDS accountDS;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ArticleWS articleWS;
    @Autowired
    private VendeurWS vendeurWS;

    private static DtoAccount account = new DtoAccount();
    private static DtoAccount accountVendeur = new DtoAccount();
    private static DtoAccount accountVendeur2 = new DtoAccount();
    private static DtoArticle articleCorrect = new DtoArticle();
    private static DtoArticle articleIncorrect = null;

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
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }

    @Test
    public void test1AjoutArticles() {

        try {
            setSecurityContext(accountVendeur);
            articleWS.create(articleCorrect);
        } catch (Exception e) {
            Assert.fail("No exception should be thrown");
        }
        try {
            setSecurityContext(account);
            articleWS.create(articleCorrect);
            Assert.fail("Exception should be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Current user is not allowed!", e.getMessage());
        }
        try {
            setSecurityContext(accountVendeur);
            articleWS.create(articleIncorrect);
            Assert.fail("Exception should be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Fail to create article!", e.getMessage());
        }
    }

    @Test
    public void test2ModifArticles() {
        try {
            setSecurityContext(accountVendeur);
            articleWS.update(articleCorrect);
        } catch (Exception e) {
            Assert.fail("No exception should be thrown");
        }

        try {
            setSecurityContext(accountVendeur2);
            articleWS.update(articleCorrect);
            Assert.fail("Exception should be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Current user is not allowed!", e.getMessage());
        }

        try {
            setSecurityContext(account);
            articleWS.update(articleCorrect);
        } catch (Exception e) {
            Assert.fail("No exception should be thrown");
        }
    }

    @Test
    public void test3DeleteArticles() {

        try {
            setSecurityContext(accountVendeur2);
            articleWS.delete(articleCorrect.getSerial());
            Assert.fail("Exception should be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Current user is not allowed!", e.getMessage());
        }

        try {
            setSecurityContext(accountVendeur);
            articleWS.delete(articleCorrect.getSerial());
        } catch (Exception e) {
            Assert.fail("No exception should be thrown");
        }
        try {
            setSecurityContext(accountVendeur);
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
