package conduit.test.controller;

import conduit.test.dto.DtoVendeur;
import conduit.test.mock.ArticleMock;
import conduit.test.mock.ChefMagasinMock;
import conduit.test.mock.VendeurMock;
import conduit.test.repository.dao.DaoVendeur;
import conduit.test.service.impl.VendeurWS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping({"/vendeurs"})
public class VendeursController {

    @Autowired
    private VendeurWS vendeurWS;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    private List<VendeurMock> vendeurMocks = createList();

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> display() throws Exception {
        try {
            List<DtoVendeur> listVendeurs = new ArrayList<DtoVendeur>();

            List<DaoVendeur> listDaoVendeurs = vendeurWS.getAlls();
            for (DaoVendeur daoVendeur : listDaoVendeurs) {
                DtoVendeur vendeur = new DtoVendeur(daoVendeur);
                listVendeurs.add(vendeur);
            }
            return ResponseEntity.ok(listVendeurs);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @DeleteMapping(path = {"/{name}"})
    public void delete(@PathVariable("name") String name) throws Exception {
        try {
            vendeurWS.delete(name);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DtoVendeur vendeur) throws Exception {
        try {
            return ResponseEntity.ok(new DtoVendeur(vendeurWS.create(vendeur)));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody DtoVendeur vendeur) throws Exception {
        try {
            return ResponseEntity.ok(new DtoVendeur(vendeurWS.update(vendeur)));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

//    private static List<VendeurMock> createList() {
//        ChefMagasinMock chefMagasinMock = new ChefMagasinMock();
//        chefMagasinMock.setId(1);
//        chefMagasinMock.setUsername("chefMagasin1");
//
//        List<VendeurMock> tempVendeurMocks = new ArrayList<>();
//        VendeurMock ven1 = new VendeurMock();
//        ven1.setId(1);
//        ven1.setUsername("vendeur1");
//        ven1.setChefMagasinMock(chefMagasinMock);
//
//        VendeurMock ven2 = new VendeurMock();
//        ven2.setId(2);
//        ven2.setUsername("vendeur2");
//        ven1.setChefMagasinMock(chefMagasinMock);
//
//        tempVendeurMocks.add(ven1);
//        tempVendeurMocks.add(ven2);
//        return tempVendeurMocks;
//    }
}