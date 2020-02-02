package conduit.test.config;

import conduit.test.repository.*;
import conduit.test.repository.dao.*;
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
    CommandLineRunner initDatabase(PdgRepo pdgRepo, ChefMagasinRepo chefMagasinRepo, VendeurRepo vendeurRepo,
                                   AccountRepo accountRepo, ArticleRepo articleRepo, PasswordEncoder bcryptEncoder) {
        return args -> {

            DaoAccount newUser = new DaoAccount();
            newUser.setUsername("root");
            newUser.setPassword(bcryptEncoder.encode("password"));
            newUser.setRole("Pdg");
            newUser.setManagername(null);

            DaoPdg newPdg = new DaoPdg();
            newPdg.setUsername(newUser.getUsername());

            logger.info("Preloading " + pdgRepo.save(newPdg));
            logger.info("Preloading " + accountRepo.save(newUser));

            newUser = new DaoAccount();
            newUser.setUsername("chefmagasin");
            newUser.setPassword(bcryptEncoder.encode("password"));
            newUser.setRole("ChefMagasin");
            newUser.setManagername(newPdg.getUsername());

            DaoChefMagasin newChefMagasin = new DaoChefMagasin();
            newChefMagasin.setUsername(newUser.getUsername());
            newChefMagasin.setPdg(newPdg);

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

            DaoArticle newArticle = new DaoArticle();
            newArticle.setSerial("serial777");
            newArticle.setName("article");
            newArticle.setCategorie("cate");
            newArticle.setDescription("desc");
            newArticle.setPrix(7.7);
            newArticle.setQuantite(1);
            newArticle.setVendeur(newVendeur);

            logger.info("Preloading " + articleRepo.save(newArticle));

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
            newUser.setUsername("chefmagasin2");
            newUser.setPassword(bcryptEncoder.encode("password"));
            newUser.setRole("ChefMagasin");
            newUser.setManagername(newPdg.getUsername());

            newChefMagasin = new DaoChefMagasin();
            newChefMagasin.setUsername(newUser.getUsername());
            newChefMagasin.setPdg(newPdg);

            logger.info("Preloading " + chefMagasinRepo.save(newChefMagasin));
            logger.info("Preloading " + accountRepo.save(newUser));

            logger.info("Finish initDatabase");
        };
    }
}
