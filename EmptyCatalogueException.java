package Exercitii.StudentCatalogue;

class EmptyCatalogueException extends Exception{

    EmptyCatalogueException (){
        super ("Catalogue is empty. Cannot delete any entry!");
    }
}
