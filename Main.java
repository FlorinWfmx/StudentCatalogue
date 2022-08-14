import java.io.IOException;

public class CourseMain {

    public static void main(String[] args) throws EmptyCatalogueException, IOException {
        Catalogue.printMenu();
        Catalogue.selectOptions();
    }
}
