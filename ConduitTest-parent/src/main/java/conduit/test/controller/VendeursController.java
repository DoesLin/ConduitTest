package conduit.test.controller;

import conduit.test.mock.ArticleMock;
import conduit.test.mock.ChefMagasinMock;
import conduit.test.mock.VendeurMock;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping({"/vendeurs"})
public class VendeursController {

    private List<VendeurMock> vendeurMocks = createList();

    @GetMapping(produces = "application/json")
    public List<VendeurMock> firstPage() {
        return vendeurMocks;
    }

    @DeleteMapping(path = {"/{id}"})
    public VendeurMock delete(@PathVariable("id") int id) {
        VendeurMock deletedArt = null;
        for (VendeurMock ven : vendeurMocks) {
            if (ven.getId() == id) {
                vendeurMocks.remove(ven);
                deletedArt = ven;
                break;
            }
        }
        return deletedArt;
    }

    @PostMapping
    public VendeurMock create(@RequestBody VendeurMock vendeur) {
        vendeurMocks.add(vendeur);
        return vendeur;
    }

    private static List<VendeurMock> createList() {
        ChefMagasinMock chefMagasinMock = new ChefMagasinMock();
        chefMagasinMock.setId(1);
        chefMagasinMock.setUsername("chefMagasin1");

        List<VendeurMock> tempVendeurMocks = new ArrayList<>();
        VendeurMock ven1 = new VendeurMock();
        ven1.setId(1);
        ven1.setUsername("vendeur1");
        ven1.setChefMagasinMock(chefMagasinMock);

        VendeurMock ven2 = new VendeurMock();
        ven2.setId(2);
        ven2.setUsername("vendeur2");
        ven1.setChefMagasinMock(chefMagasinMock);

        tempVendeurMocks.add(ven1);
        tempVendeurMocks.add(ven2);
        return tempVendeurMocks;
    }
}