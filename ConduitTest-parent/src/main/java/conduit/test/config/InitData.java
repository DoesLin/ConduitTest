package conduit.test.config;

import conduit.test.repository.AccountRepo;
import conduit.test.repository.ChefMagasinRepo;
import conduit.test.repository.VendeurRepo;
import conduit.test.repository.dao.DaoAccount;
import conduit.test.repository.dao.DaoArticle;
import conduit.test.repository.dao.DaoChefMagasin;
import conduit.test.repository.dao.DaoVendeur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitData {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    CommandLineRunner initDatabase(ChefMagasinRepo chefMagasinRepo, VendeurRepo vendeurRepo, AccountRepo accountRepo, PasswordEncoder bcryptEncoder) {
        return args -> {
            DaoAccount newUser = new DaoAccount();
            newUser.setUsername("root");
            newUser.setPassword(bcryptEncoder.encode("password"));
            newUser.setRole("ChefMagasin");
            newUser.setManagername(null);

            DaoChefMagasin newChefMagasin = new DaoChefMagasin();
            newChefMagasin.setUsername(newUser.getUsername());

            logger.info("Preloading " + chefMagasinRepo.save(newChefMagasin));
            logger.info("Preloading " + accountRepo.save(newUser));

            newUser = new DaoAccount();
            newUser.setUsername("vendeur");
            newUser.setPassword(bcryptEncoder.encode("password"));
            newUser.setRole("Vendeur");
            newUser.setManagername(newChefMagasin.getUsername());

            DaoVendeur newVendeur = new DaoVendeur();
            newVendeur.setUsername(newUser.getUsername());
            newVendeur.setChefMagasin(newChefMagasin);

            logger.info("Preloading " + vendeurRepo.save(newVendeur));
            logger.info("Preloading " + accountRepo.save(newUser));

            newUser = new DaoAccount();
            newUser.setUsername("vendeur2");
            newUser.setPassword(bcryptEncoder.encode("password"));
            newUser.setRole("Vendeur");
            newUser.setManagername(newChefMagasin.getUsername());

            newVendeur = new DaoVendeur();
            newVendeur.setUsername(newUser.getUsername());
            newVendeur.setChefMagasin(newChefMagasin);

            logger.info("Preloading " + vendeurRepo.save(newVendeur));
            logger.info("Preloading " + accountRepo.save(newUser));

            newUser = new DaoAccount();
            newUser.setUsername("chefmagasin");
            newUser.setPassword(bcryptEncoder.encode("password"));
            newUser.setRole("ChefMagasin");
            newUser.setManagername(null);

            newChefMagasin = new DaoChefMagasin();
            newChefMagasin.setUsername(newUser.getUsername());

            logger.info("Preloading " + chefMagasinRepo.save(newChefMagasin));
            logger.info("Preloading " + accountRepo.save(newUser));

            newUser = new DaoAccount();
            newUser.setUsername("chefmagasin2");
            newUser.setPassword(bcryptEncoder.encode("password"));
            newUser.setRole("ChefMagasin");
            newUser.setManagername(null);

            newChefMagasin = new DaoChefMagasin();
            newChefMagasin.setUsername(newUser.getUsername());

            logger.info("Preloading " + chefMagasinRepo.save(newChefMagasin));
            logger.info("Preloading " + accountRepo.save(newUser));
        };
    }
}
