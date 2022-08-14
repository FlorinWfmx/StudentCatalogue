import java.io.IOException;

public class CatalogueMain {

    public static void main(String[] args) throws EmptyCatalogueException, IOException {
        Catalogue.printMenu();
        Catalogue.selectOptions();
    }
}
