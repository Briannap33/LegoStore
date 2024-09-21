package models;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Double.MAX_VALUE;
import static utils.MinimumAgeUtility.isValidAge;
import static utils.ThemeUtility.isValidTheme;
import static utils.Utilities.*;

public class LegoSet {

    // DONE The lego set name field (String) has a maximum 35 chars.
    //     Default value is "".
    //     When creating the lego set, truncate the name to 35 characters.
    //     When updating an existing lego set, only update the name if it is 35 characters or less.
    private String name = "";

    // DONE The code field (int) must be between 10000 and 99999 (both inclusive).  Default value is 10000.
    private int code = 10000;

    //DONE The cost field (double) must be greater than zero.  The default value is MAX_VALUE for Double.
    private double cost = MAX_VALUE;

    //DONE The piece count field (int) must be between 1 and 2000 (both inclusive). The default value is 1.
    private int pieceCount = 1;

    //DONE? The in stock field (boolean) has a default of true i.e. it is in stock.
    private boolean isInStock = true;

    //DONE The theme field (String) has valid values of Classic, City, Creator, or Friends.
    //     The default value is "Classic";
    private String theme = "Classic";

    //DONE The minimum age field (int) has valid values of 4, 6, 9, 10, 13 or 18.
    //     The default value is 4;
    //     When the value is being returned in toString, a plus should be added beside it i.e. 9+, 4+
    private int minimumAge = 4;

    //DONE The instruction booklets field is an ArrayList of InstructionBooket objects.
    private ArrayList<InstructionBooklet> instructionBooklets;


    //DONE Add the constructor, LegoSet(String, int, double, int, String, int), that adheres to the above validation rules.
    //     The order of the fields in the parameter list is the same as the order of fields above i.e. name is
    //     first, then code, then cost, and so on.
    public LegoSet(String name, int code, double cost, int pieceCount, String theme, int minimumAge) {
        this.name = truncateString(name, 35);
        setCode(code);
        setCost(cost);
        setPieceCount(pieceCount);
        //  this.isInStock = false;
        setTheme(theme);
        setMinimumAge(minimumAge);

        instructionBooklets = new ArrayList<InstructionBooklet>();
    }
    //DONE Add a getter and setter for each field, that adheres to the above validation rules.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (validStringlength(name, 35)) {
            this.name = name;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        if (validRange(code, 10000, 99999)) {
            this.code = code;
        }
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        if (cost > 0)
            this.cost = cost;
    }

    public int getPieceCount() {
        return pieceCount;
    }


    public void setPieceCount(int pieceCount) {
        if (validRange(pieceCount, 1, 2000)) {
            this.pieceCount = pieceCount;
        }
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        this.isInStock = inStock;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        if (isValidTheme(theme))
            this.theme = theme;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(int minimumAge) {
        if (isValidAge(minimumAge))
            this.minimumAge = minimumAge;
    }

    public ArrayList<InstructionBooklet> getInstructionBooklets() {
        return instructionBooklets;
    }

    public void setInstructionBooklets(ArrayList<InstructionBooklet> booklets) {
        this.instructionBooklets = booklets;
    }

    //DONE Add a generated equals method.


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LegoSet legoSet = (LegoSet) o;
        return code == legoSet.code && Double.compare(legoSet.cost, cost) == 0 && pieceCount == legoSet.pieceCount && isInStock == legoSet.isInStock && minimumAge == legoSet.minimumAge && Objects.equals(name, legoSet.name) && Objects.equals(theme, legoSet.theme) && Objects.equals(instructionBooklets, legoSet.instructionBooklets);
    }


    //DONE Add the usual toString method (return type is String).
    //     An example of the format of the String being returned would be:
    //     Train Station, City theme (60335) 907 pieces. €99.99 (in stock). Age: 9+.
    //         0: InstructionBook1.pdf (6 pages)
    //         1: InstructionBook2.pdf (0 pages)
    //         2: InstructionBook3.pdf (1 page)
    //    OR
    //     Lunar Space Station, Classic theme (60349) 500 pieces. €79.99 (not available). Age: 10+
    //         No Instruction Booklets

    public String toString() {
        String stock;
        if (isInStock) {
            stock = "in stock";
        } else {
            stock = "not available";
        }
        String instructionBook = "";
        if (instructionBooklets.size() == 0) {
            instructionBook = "No Instruction Booklets";
        } else {
            instructionBook = listInstructionBooklets();
        }
        return  name + ", " +
                theme + " theme (" +
                code + ") " +
                pieceCount + " pieces. €" +
                cost + " (" +
                stock + "). Age: " +
                minimumAge + "+.\n" +
                instructionBook;


    }



//-------------------
    // ArrayList handling
    //-------------------

    //DONE numberOfInstructionBooklets(): Add this method that has a return type of int.
    //     It should return the number of items stored in the ArrayList.

    public int numberOfInstructionBooklets() {
        return instructionBooklets.size();
    }


    // DONE addInstructionBooklet(InstructionBooklet):  Add a method that will add an instruction booklet to
    //      the ArrayList. The return type of this method is boolean.  The method should return true if
    //      successful, false otherwise.
    public boolean addInstructionBooklet(InstructionBooklet instructionBooklet) {
        if (instructionBooklets.add(instructionBooklet))
            return true;
        else {
            return false;
        }

    }

    // DONE listInstructionBooklets(): Add a method that will return a list all of the instruction booklets (return
    //      type of this method is String).  Each booklet should be on it's own line and should be preceded with
    //      the index number in the array list e.g.
    //         0: InstructionBook1.pdf (6 pages)
    //         1: InstructionBook2.pdf (1 page)
    //         2: InstructionBook3.pdf (2 pages)
    public String listInstructionBooklets() {
        String instructionBookList = "";
        for (int i = 0; i < instructionBooklets.size(); i++) {

            instructionBookList += i + ": " + instructionBooklets.get(i);
        }
        if (instructionBooklets.isEmpty())
            return "No Instruction Book";
        return instructionBookList;
    }



        //DONE isValidIndex(int): Add a method that will return true if the value of the index (passed as a
        //     parameter) is a valid index number in the instruction booklets array list.  If the index is invalid
        //     return false.  The return type of this method is boolean.
        public boolean isValidIndex ( int index){
            if (index >= 0 && index < instructionBooklets.size())
                return true;

            return false;

        }


        //DONE findInstructionBooklet(int): Add a method that will return the instruction booklet at a specific
        //     index in the array list.  If the index is invalid, null is returned instead.  The return type of this
        //     method is InstructionBooklet.
        public InstructionBooklet findInstructionBooklet (int index){
            if (isValidIndex(index)) {
                return instructionBooklets.get(index);
            }
            return null;
        }


        //DONE deleteInstructionBooklet(int): Add a method that will delete the instruction booklet at a specific
        //     index in the array list.  If the index is invalid, null is returned instead.  The return type of this
        //     method is InstructionBooklet.
        public InstructionBooklet deleteInstructionBooklet ( int index){

            if (isValidIndex(index)) {
                InstructionBooklet pickedBooklet = instructionBooklets.remove(index);
                return pickedBooklet;
            }
            return null;
        }

        //DONE updateInstructionBooklet(int, String, int): Add a method that will take in three parameters:
        //     - The first parameter is the index of the instruction booklet in the arraylist.
        //     - The second parameter is the new value for the filename.
        //     - The third parameter is the new value for the number of pages.
        //     The method will update update the fileName and numberOfPages for an instruction booklet at a
        //     specific index in the array list.
        //     It will return true if the update was successful. If the index is invalid, false is returned instead.
        //     The return type of this method is boolean.
        public boolean updateInstructionBooklet ( int index, String fileName,int numberOfPages){
            if (isValidIndex(index)) {
                InstructionBooklet pickedBooklet = instructionBooklets.get(index);
                pickedBooklet.setFileName(fileName);
                pickedBooklet.setNumberOfPages(numberOfPages);

                return true;
            }
            return false;
        }


}


