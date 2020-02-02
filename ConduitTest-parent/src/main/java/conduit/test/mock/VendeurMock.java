package conduit.test.mock;

public class VendeurMock extends AccountMock {

    private ChefMagasinMock chefMagasinMock;

    public ChefMagasinMock getChefMagasinMock() {
        return chefMagasinMock;
    }

    public void setChefMagasinMock(ChefMagasinMock chefMagasinMock) {
        this.chefMagasinMock = chefMagasinMock;
    }
}
