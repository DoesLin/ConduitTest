package conduit.test.mock;


import java.util.List;

public class ChefMagasinMock extends AccountMock {

    private List<VendeurMock> listeVendeursMock;

    public List<VendeurMock> getListeVendeursMock() {
        return listeVendeursMock;
    }

    public void setListeVendeursMock(List<VendeurMock> listeVendeursMock) {
        this.listeVendeursMock = listeVendeursMock;
    }

}
