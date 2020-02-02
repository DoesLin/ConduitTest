package conduit.test;

import conduit.test.controller.AuthenticationController;
import conduit.test.dto.DtoAccount;
import conduit.test.service.AccountDS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConduitTestApplication {

    private final static Logger logger = LoggerFactory.getLogger("ConduitTest-parent");
    @Autowired
    private static AccountDS accountDS;
//    private static AuthenticationController authenticationController;

    public static void main(String[] args) {
        SpringApplication.run(ConduitTestApplication.class, args);

//            DtoAccount account = new DtoAccount();
//            account.setUsername("root");
//            account.setPassword("password");
//            account.setRole("PDG");
//            accountDS.create(account);
    }
}
