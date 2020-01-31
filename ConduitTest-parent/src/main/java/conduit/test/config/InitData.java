package conduit.test.config;

import conduit.test.repository.AccountRepo;
import conduit.test.repository.ChefMagasinRepo;
import conduit.test.repository.dao.DaoAccount;
import conduit.test.repository.dao.DaoChefMagasin;
import conduit.test.repository.dao.DaoVendeur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitData {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    CommandLineRunner initDatabase(ChefMagasinRepo chefMagasinRepo, AccountRepo accountRepo, PasswordEncoder bcryptEncoder) {
        return args -> {
            DaoAccount newUser = new DaoAccount();
            newUser.setUsername("root");
            newUser.setPassword(bcryptEncoder.encode("password"));
            newUser.setRole("ChefMagasin");
            newUser.setManagername(null);

            DaoChefMagasin newChefMagasin = new DaoChefMagasin();
            newChefMagasin.setUsername(newUser.getUsername());
//            chefMagasinRepo.save(newChefMagasin);

//            accountRepo.save(newUser);
            logger.info("Preloading " + chefMagasinRepo.save(newChefMagasin));
            logger.info("Preloading " + accountRepo.save(newUser));
        };
    }
}
