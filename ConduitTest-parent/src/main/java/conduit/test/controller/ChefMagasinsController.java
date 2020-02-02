package conduit.test.controller;

import conduit.test.dto.DtoChefMagasin;
import conduit.test.dto.DtoVendeur;
import conduit.test.repository.dao.DaoChefMagasin;
import conduit.test.repository.dao.DaoVendeur;
import conduit.test.service.impl.ChefMagasinWS;
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
@RequestMapping({"/chefmagasins"})
public class ChefMagasinsController {

    @Autowired
    private ChefMagasinWS chefMagasinWS;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    private List<ChefMagasinMock> chefMagasinMocks = createList();

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> display() throws Exception {
        try {
            List<DtoChefMagasin> listChefMagasins = new ArrayList<DtoChefMagasin>();

            List<DaoChefMagasin> listDaoChefMagasins = chefMagasinWS.getAlls();
            for (DaoChefMagasin daoChefMagasin : listDaoChefMagasins) {
                DtoChefMagasin chefMagasin = new DtoChefMagasin(daoChefMagasin);
                listChefMagasins.add(chefMagasin);
            }
            return ResponseEntity.ok(listChefMagasins);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @DeleteMapping(path = {"/{name}"})
    public void delete(@PathVariable("name") String name) throws Exception {
        try {
            chefMagasinWS.delete(name);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DtoChefMagasin chefMagasin) throws Exception {
        try {
            return ResponseEntity.ok(new DtoChefMagasin(chefMagasinWS.create(chefMagasin)));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody DtoChefMagasin chefMagasin) throws Exception {
        try {
            return ResponseEntity.ok(new DtoChefMagasin(chefMagasinWS.update(chefMagasin)));
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