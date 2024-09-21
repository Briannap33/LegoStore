import controllers.LegoSetAPI;
import models.InstructionBooklet;
import models.LegoSet;
import utils.ScannerInput;
import utils.Utilities;

public class Driver {

    //DONE Define an object of the LegoSetAPI here.  It should be declared private.
    private LegoSetAPI legoSetAPI = new LegoSetAPI();

    public static void main(String[] args) {
        new Driver();
    }

    public Driver() {
        runMenu();
    }
    //DONE Refer to the tutors instructions for building this class.  You are free to deviate in any way
    //     from the Driver menu that is in the tutors instructions, once you have these included:
    //       //- CRUD on LegoSet
    //      // - CRUD on Instruction Booklets
    //      // - Search facility (for LegoSets and Booklets)
    //      //- Reports
    //       //- Persistence
    // Note:  This is the ONLY class that can talk to the user i.e. have System.out.print and Scanner reads in it.

    //----------------------------------------------------------------------------
    // Private methods for displaying the menu and processing the selected options
    //----------------------------------------------------------------------------
    private int mainMenu() {
        return ScannerInput.readNextInt("""
                                
                                
                                       LegoSet MENU
                -----------------------------------------------------------
                1) Add a LegoSet
                2) List LegoSets
                3) Update LegoSets
                4) Delete LegoSets
                -----------------------------------------------------------
                5) Add an Instruction Booklet
                6) List instruction Booklets
                7) Update Instruction Booklets
                8) Delete Instruction Booklets
                -----------------------------------------------------------
                9) Search LegoSets by name
                10) Search instruction Booklets by name
                11) search for lego set by code
                12) search for lego set by index number
                -----------------------------------------------------------
                13) Status of stock by theme
                -----------------------------------------------------------
                14) Save Legoset
                15) Load Legoset
                -----------------------------------------------------------
                0) Exit
                -----------------------------------------------------------
                ==>> """);

    }

    private void runMenu() {
        int option = mainMenu();

        while (option != 0) {

            switch (option) {
                case 1 -> addLegoSet();
                case 2 -> printLegoSet();
                case 3 -> updateLegoSet();
                case 4 -> deleteLegoSet();
                case 5 -> addInstructionBooklet();
                case 6 -> printInstructionBooklet();
                case 7 -> updateInstructionBooklet();
                case 8 -> deleteInstructionBooklet();
                case 9 -> searchLegoSetByName();
                case 10 -> searchInstructionBookletByName();
                case 11 -> findLegoSetByCode();
                case 12 -> findLegoSet();
                case 13 -> stockReportByTheme();
                case 14 -> save();
                case 15 -> load();
                default -> System.out.println("Invalid option entered: " + option);

            }
            ScannerInput.readNextLine("\nPress enter key to continue...");

            option = mainMenu();
        }
        System.out.println("Exiting...bye!");
        System.exit(0);
    }

    //------------------------------------
    // Private methods for CRUD on LegoSet
    //------------------------------------
    private void addLegoSet() {
        String name = ScannerInput.readNextLine("Enter name of Lego Set:   ");
        int code = ScannerInput.readNextInt("Enter Lego Set code:  ");
        double cost = ScannerInput.readNextDouble("Enter cost of Lego Set:  ");
        int pieceCount = ScannerInput.readNextInt("Enter amount of pieces in Lego Set:   ");
        char currentSet = ScannerInput.readNextChar("Is this Lego Set in stock(y/n)?: ");
        boolean isInStock = Utilities.YNtoBoolean(currentSet);
        String theme = ScannerInput.readNextLine("Enter a theme for the lego Set:  ");
        int minimumAge = ScannerInput.readNextInt("Minimum age for the Lego Set is :");

        boolean isAdded = legoSetAPI.addLegoSet(new LegoSet(name, code, cost, pieceCount, theme, minimumAge));
        if (isAdded) {
            System.out.println("Lego Set added successfully!");
        } else {
            System.out.println("No lego sets added");
        }

    }

    private void printLegoSet() {
        System.out.println("List of lego Sets are: ");
        System.out.println(legoSetAPI.listAllLegoSets());
    }

    private void deleteLegoSet() {
        printLegoSet();
        if (legoSetAPI.numberOfLegoSets() > 0) {
            int indexToDelete = ScannerInput.readNextInt("Enter the index number of the lego set you want to delete: ");

            LegoSet legoSetToDelete = legoSetAPI.deleteLegoSet(indexToDelete);
            if (legoSetToDelete != null) {
                System.out.println("Lego set deleted successfully! Deleted lego set was:  " + legoSetToDelete.getName());
            } else {
                System.out.println(" Delete unseccessful!");
            }
        }
    }

    private void updateLegoSet() {
        printLegoSet();
        if (legoSetAPI.numberOfLegoSets() > 0) {
            int indexToUpdate = ScannerInput.readNextInt("Enter the index number of the lego set you want to update: ");
            if (legoSetAPI.isValidIndex(indexToUpdate)) {
                String name = ScannerInput.readNextLine("Enter name of Lego Set:   ");
                int code = ScannerInput.readNextInt("Enter Lego Set code:  ");
                double cost = ScannerInput.readNextDouble("Enter cost of Lego Set:  ");
                int pieceCount = ScannerInput.readNextInt("Enter amount of pieces in Lego Set:   ");
                char currentSet = ScannerInput.readNextChar("Is this Lego Set in stock(y/n)?: ");
                boolean isInStock = Utilities.YNtoBoolean(currentSet);
                String theme = ScannerInput.readNextLine("Enter a theme for the lego Set:  ");
                int minimumAge = ScannerInput.readNextInt("Minimum age for the Lego Set is :");

                if (legoSetAPI.updateLegoSet(indexToUpdate, name, code, cost, pieceCount, theme, minimumAge)) ;

                System.out.println("Lego set update successfully!");
            } else {
                System.out.println("Update unseccessful!");
            }
        } else {
            System.out.println("There are no lego sets at this index number");
        }
    }

    //--------------------------------------------------
    //  Private methods for CRUD on Instruction Booklets
    //--------------------------------------------------
    private void addInstructionBooklet() {
        String fileName = ScannerInput.readNextLine("Enter name of instruction booklet:   ");
        int numberOfPages = ScannerInput.readNextInt("Enter number of pages in instruction booklet:   ");
        int index = ScannerInput.readNextInt("which set:  " + legoSetAPI.listAllLegoSets());
        boolean isAdded = legoSetAPI.findLegoSet(index).addInstructionBooklet(new InstructionBooklet(numberOfPages, fileName));
        if (isAdded) {
            System.out.println("Instruction Booklet added successfully!");
        } else {
            System.out.println("No instruction booklet added");
        }

    }

    private void printInstructionBooklet() {
        System.out.println("List of instruction booklets are: ");
        System.out.println(legoSetAPI.listAllInstructionBooklets());
    }

    private void deleteInstructionBooklet() {
        System.out.println("List of legosets: " + legoSetAPI.listAllLegoSets());

        if (legoSetAPI.totalNumberOfInstructionBooklets() > 0) {
            int indexToDelete = ScannerInput.readNextInt("Enter the index number of the lego set you want to delete a booklet from: ");
            LegoSet setFound = legoSetAPI.findLegoSet(indexToDelete);
            System.out.println(setFound.listInstructionBooklets());
            int instructionBookl = ScannerInput.readNextInt("Ender index of booklet to delete:  ");
            InstructionBooklet bookletToDelete = legoSetAPI.findLegoSet(indexToDelete).deleteInstructionBooklet(instructionBookl);

            if (bookletToDelete != null) {
                System.out.println("Instruction booklet deleted successfully! Deleted instruction booklet was:  " + bookletToDelete.getFileName());
            } else {
                System.out.println(" Delete unseccessful ");
            }
        }

    }
    private void updateInstructionBooklet() {

        System.out.println("List of legosets: " + legoSetAPI.listAllLegoSets());

        if (legoSetAPI.totalNumberOfInstructionBooklets() > 0) {
            int indexToUpdate = ScannerInput.readNextInt("Enter the index number of the  Lego set you want to update: ");
            if (legoSetAPI.isValidIndex(indexToUpdate)) {
                LegoSet setFound = legoSetAPI.findLegoSet(indexToUpdate);
                System.out.println(setFound.listInstructionBooklets());
                int indexInstruc = ScannerInput.readNextInt("enter index of one to update");
                if(setFound.isValidIndex(indexInstruc)) {
                    String fileName = ScannerInput.readNextLine("Enter name of instruction booklet:   ");
                    int numberOfPages = ScannerInput.readNextInt("Enter number of pages in instruction booklet:   ");
                    setFound.updateInstructionBooklet(indexInstruc, fileName, numberOfPages);
                    System.out.println("Instruction booklet updated successfully!");
                }
            } else {
                System.out.println("Update unseccessful");
            }
        } else {
            System.out.println("There are no instruction booklets at this index number");
        }
    }


    //-----------------------------------------------------------------
    //  Private methods for Search facility (for LegoSets and Booklets)
    //-----------------------------------------------------------------
    private void searchLegoSetByName(){

        String setName = ScannerInput.readNextLine("enter name: ");
        String result = legoSetAPI.searchLegoSetsByName(setName);
        System.out.println(result);

    }
    private void searchInstructionBookletByName(){

        String setName = ScannerInput.readNextLine("enter name: ");
        String result = legoSetAPI.searchInstructionBookletsByFileName(setName);
        System.out.println(result);


    }

    private void findLegoSetByCode(){

        int setsCode = ScannerInput.readNextInt(" Enter lego set code:  ");
        LegoSet codeResult = legoSetAPI.findLegoSetByCode(setsCode);
        System.out.println(codeResult);


    }
    private void findLegoSet(){

        int setsIndex = ScannerInput.readNextInt(" Enter index number of lego set:  ");
        LegoSet indexResult = legoSetAPI.findLegoSet(setsIndex);
        System.out.println(indexResult);

    }


    //-----------------------------
    //  Private methods for Reports
    // ----------------------------
  private void stockReportByTheme(){

        String stockReport = ScannerInput.readNextLine("Enter Theme to view report: ");
        String report = legoSetAPI.listStockStatusBySpecificTheme(stockReport);
        System.out.println(report);


  }

    //---------------------------------
    //  Private methods for Persistence
    // --------------------------------
private void save(){
        try{
            legoSetAPI.save();
        }
        catch (Exception e){
            System.err.println("Error writing to file:   " + e);
        }
}

private void load(){
        try {
            legoSetAPI.load();
        } catch (Exception e){
            System.err.println("Error reading from file:   "+ e);
        }
}
}