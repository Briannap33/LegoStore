package controllers;

//DONE When you have the code written in this class, write a JavaDoc comment for class and also
//     for any public methods in this class.

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.LegoSet;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * The LegoSetAPI class saves all the legoSets
 * and their instruction booklets entered into the
 * primitive arraylist.
 * @author: Brianna Power
 * version: 1.0 (02.Mar.2023)
 */
public class LegoSetAPI {


    //DONE Declare an array list of lego sets
    private ArrayList<LegoSet> legoSets = new ArrayList<>();
    //-------------------------------------
    //  ARRAYLIST CRUD
    //-------------------------------------

    //DONE Add a method, addLegoSet(LegoSet). The return type is boolean.
    //     This method will add the lego set object, passed as a parameter to the arraylist of lego sets.
    //     If the add was successful, return true, otherwise, return false.

    /**
     *This method adds the legoSet object that is
     * passed through the parameter to the arraylist
     * @param legoSet
     * @return If successful, returns true, else ,
     * returns false.
     */
    public boolean addLegoSet(LegoSet legoSet) {
        if (legoSets.add(legoSet))
            return true;
        else {
            return false;
        }
    }

    //DONE Add a method, updateLegoSet(int, String, int, double, int, String, int).  The return type is boolean.
    //     This method takes in, as the first parameter, the index of the lego set object that you want to update.
    //     If the index is invalid (i.e. there is no lego set object at that location), return false.
    //     The remaining parameters hold the new data for each of the fields in LegoSet that are being updated
    //     i.e. they hold the name, code, cost, pieceCount, theme and minimum age).
    //     If the update was successful, then return true.

    /**
     * This method takes the index of the lego set object you
     * want to update as the first parameter.The rest of the
     * parameters hold the new information for each field in the
     * legoSet that is being updated.
     * @param index
     * @param name
     * @param code
     * @param cost
     * @param pieceCount
     * @param theme
     * @param minimumAge
     * @return If the index is invalid, returns false.
     * If the update is successful, returns true.
     */
    public boolean updateLegoSet(int index, String name, int code, double cost, int pieceCount, String theme, int minimumAge) {
        if (isValidIndex(index)) {
            LegoSet pickedLego = legoSets.get(index);
            pickedLego.setName(name);
            pickedLego.setCode(code);
            pickedLego.setCost(cost);
            pickedLego.setPieceCount(pieceCount);
            pickedLego.setTheme(theme);
            pickedLego.setMinimumAge(minimumAge);
            return true;
        }

        return false;
    }

    //DONE Add a method, deleteLegoSet(int).  The return type is LegoSet.
    //     This method takes in the index of the lego set object that you want to delete.
    //     If the index is invalid (i.e. there is no lego set object at that location), return null.
    //     If the index is valid, remove the object at that index location.  Return the object you just deleted.

    /**
     * This method takes the index of the legoSet object that
     * the user wants to delete. and removes the object from
     * the arraylist.
     * @param index
     * @return if the index is invalid , returns null
     * elsewise, removes the object at the chosen index location
     * and returns the deleted object.
     */
    public LegoSet deleteLegoSet(int index) {
        if (isValidIndex(index)) {
            LegoSet pickedLego = legoSets.remove(index);
            return pickedLego;
        }
        return null;
    }

    //-------------------------------------
    //  ARRAYLIST - Stock Status Update
    //-------------------------------------

    //DONE Add a method, setLegoSetInStock(int).  The return type is boolean.
    //     This method takes in the index of the lego set object that you want to update.
    //     If the index is invalid (i.e. there is no lego set object at that location), return false.
    //     If the index is valid, retrieve the object and:
    //        If the object is not in stock, set it to being in stock and return true.
    //        If the object is already in stock, return false.

    /**
     * This method takes the index of the legoSet object
     * the user wants to update.If valid retrieves object
     * and if object is not in stock, set in stock and
     * return true, if already in stock return false.
     * @param index
     * @return If invalid return false, If valid, retrieve object
     * and if object is in stock return false, if object is not in
     * stock, set to being in stock and return true.
     */
    public boolean setLegoSetInStock(int index) {
        if (index < 0)
            return false;

        if (isValidIndex(index)) {
            LegoSet foundSet = legoSets.get(index);
            if (!foundSet.isInStock()) {
                foundSet.setInStock(true);
                return true;
            }
        }
        return false;

    }

    //DONE Add a method, setLegoSetOutOfStock(int).  The return type is boolean.
    //     This method takes in the index of the lego set object that you want to update.
    //     If the index is invalid (i.e. there is no lego set object at that location), return false.
    //     If the index is valid, retrieve the object and:
    //        If the object is already in stock, set it to being out of stock and return true.
    //        If the object is not in stock, return false.

    /**
     * This method takes the index of the legoSet object
     *      * the user wants to update.If valid retrieves object
     *      * and if object is in stock, set to out of stock and
     *      * return true, if not in stock return false.
     *      * @param index
     *      * @return If invalid, return false, If valid, retrieve object
     *      * and if object is not in stock return false, if object is in
     *      * stock, set to out of stock and return true.
     */
    public boolean setLegoSetOutOfStock(int index) {
        if (isValidIndex(index)) {
            LegoSet foundSet = legoSets.get(index);
            if (foundSet.isInStock()) {
                foundSet.setInStock(false);
                return true;

            }
        }
        return false;
    }

    //-------------------------------------
    //  Counting Methods - Basic
    //-------------------------------------

    //DONE Add a method, numberOfLegoSets().  The return type is int.
    //     This method returns the number of lego set objects currently stored in the array list.

    /**
     * This method returns the number of lego set
     * objects in the array list.
     * @return an int value representing the amount
     * of objects stored in the arraylist
     */
    public int numberOfLegoSets() {
        return legoSets.size();
    }

    //DONE Add a method, numberOfLegoSetsInStock().  The return type is int.
    //     This method returns the number of lego set objects in the array list that are in currently in stock.

    /**
     * This method returns the number of legosets
     * in the arraylist that are currently in stock.
     * @return an int value representing the amount
     *  of objects stored in the arraylist that aare in stock.
     */
    public int numberOfLegoSetsInStock() {
        int numInStock = 0;
        for (int i = 0; i < numberOfLegoSets(); i++) {
            if (legoSets.get(i).isInStock())
                numInStock++;
        }
        return numInStock;
    }


    //DONE Add a method, numberOfLegoSetsOutOfStock().  The return type is int.
    //     This method returns the number of lego set objects in the array list that are out of stock.

    /**
     * This method returns the number of legosets
     * in the arraylist that are currently out of stock.
     * @return an int value representing the amount
     * of objects stored in the arraylist that are out of stock.
     */
    public int numberOfLegoSetsOutOfStock() {
        int numInStock = 0;
        for (int i = 0; i < numberOfLegoSets(); i++) {
            if (!legoSets.get(i).isInStock())
                numInStock++;
        }
        return numInStock;
    }


    //-------------------------------------
    //  Counting Methods - Advanced
    //-------------------------------------

    //DONE Add a method, numberOfLegoSetsByTheme(String).  The return type is int.
    //     This method returns the number of lego set objects in the array list that match the
    //     theme (i.e. the parameter value).

    /**
     * This method returns the number of lego sets
     * in the arraylist that match the theme.
     * @param theme
     * @return an int number that represents the
     * value of the amount of lego sets with the same chosen theme.
     */
    public int numberOfLegoSetsByTheme(String theme) {
        int numByTheme = 0;
        if (legoSets.isEmpty()) {
            return 0;
        } else {
            for (int i = 0; i < legoSets.size(); i++) {
                if (legoSets.get(i).getTheme().equalsIgnoreCase(theme))
                    numByTheme++;
            }
        }
        return numByTheme;
    }


    //DONE Add a method, numberOfLegoSetsForAgeRatingAndAbove(int).  The return type is int.
    //     This method returns the number of lego set objects in the array list that are equal to
    //     or above the age passed as a parameter value.

    /**
     * This method returns the number of legoSets in the arraylist that
     * are equal or above the inputted age that is passed as a parameter value.
     * @param age
     * @return an int number that represents the amount of legosets
     * that are equal to or above the parameter value.
     */
    public int numberOfLegoSetsForAgeRatingAndAbove(int age) {
        int numAboveAge = 0;
        if (legoSets.isEmpty()) {
            return 0;
        } else {
            for (int i = 0; i < legoSets.size(); i++) {
                if (legoSets.get(i).getMinimumAge() >= age)
                    numAboveAge++;
            }
        }
        return numAboveAge;

    }

    //DONE Add a method, totalNumberOfInstructionBooklets().  The return type is int.
    //     This method returns the total number of instruction booklets across all the lego set objects
    //     currently stored in the array list.

    /**
     * This method returns the total number of instruction booklets
     * across all legoSet objects that are stored in the arraylist.
     * @return an int number to represent the amount of instruction
     * booklets currently stored in the arraylist .
     */
    public int totalNumberOfInstructionBooklets() {
        int numBook = 0;
        for (int i = 0; i < numberOfLegoSets(); i++) {
            numBook += legoSets.get(i).numberOfInstructionBooklets();
        }
        return numBook;
    }


    //------------------------------------
    // LISTING METHODS - Basic
    //------------------------------------

    //DONE Add a method, listAllLegoSets().  The return type is String.
    //     This method returns a list of the lego sets stored in the array list.
    //     Each lego set should be on a new line and should be preceded by the index number e.g.
    //        0: Lego Set 1 Details
    //        1: Lego Set 2 Details
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".

    /**
     * This method returns a list of legoSets stored
     * in the arraylist, with each legoSet being on
     * a new line followed by index number. If no lego
     * sets are stored in the arraylist, "no lego sets"is returned
     * @return a string that returns a list of legosets
     * with index numbers, if no legosets are in the
     * arraylist, a string containing " no lego sets"
     * is returned
     */
    public String listAllLegoSets() {
        if (legoSets.isEmpty()) {
            return "No Lego Sets";
        } else {
            String listOfLegoSets = "";
            for (int i = 0; i < legoSets.size(); i++) {
                listOfLegoSets += i + ": " + legoSets.get(i) + "\n";
            }
            return listOfLegoSets;
        }
    }

    //DONE Add a method, listLegoSetsInStock().  The return type is String.
    //     This method returns a list of the IN STOCK lego sets stored in the array list.
    //     Each matching lego set should be on a new line and should be preceded by the index number e.g.
    //        0: Lego Set 1 Details
    //        3: Lego Set 4 Details
    //    If there are no IN STOCK lego sets stored in the array list, the return string should
    //    have "No Lego sets in stock".

    /**
     * This method returns a list of all the lego
     * sets in the arraylist that are in stock.With
     * each legoset on a new line with the name and
     * index number. If there are no in stock lego
     * sets stored in the arraylist, reutrn "no lego sets in stock".
     * @return If in stock returns Legoset name and
     * number with each on a new line. If no in stock,
     * returns a string containing "no lego sets in stock"
     */
    public String listLegoSetsInStock() {
        if (legoSets.isEmpty()) {
            return "No Lego Sets";
        } else {
            String listOfLegoSets = "";
            for (int i = 0; i < legoSets.size(); i++) {
                if (legoSets.get(i).isInStock())
                    listOfLegoSets += i + ": " + legoSets.get(i) + "\n";
            }
            if (listOfLegoSets.isEmpty())
                return "No Lego sets in stock";
            return listOfLegoSets;
        }
    }


    //DONE Add a method, listLegoSetsOutOfStock().  The return type is String.
    //     This method returns a list of the OUT OF STOCK lego sets stored in the array list.
    //     Each matching lego set should be on a new line and should be preceded by the index number e.g.
    //        1: Lego Set 2 Details
    //        4: Lego Set 5 Details
    //    If there are no OUT OF STOCK lego sets stored in the array list, the return string should
    //        have "No Lego sets are out of stock".
    /**
     * This method returns a list of all the lego
     * sets in the arraylist that are out of stock.With
     * each legoset on a new line with the name and
     * index number. If there are no out of stock lego
     * sets stored in the arraylist, return "no lego are out of stock".
     * @return If out of stock ,returns Legoset name and
     * number with each on a new line. If there are none out of stock,
     * returns a string containing "no lego are out of stock"
     */
    public String listLegoSetsOutOfStock() {
        if (legoSets.isEmpty()) {
            return "No Lego Sets";
        } else {
            String listOfLegoSets = "";
            for (int i = 0; i < legoSets.size(); i++) {
                if (!legoSets.get(i).isInStock())
                    listOfLegoSets += i + ": " + legoSets.get(i) + "\n";
            }
            if (listOfLegoSets.isEmpty())
                return "No Lego sets in stock";
            return listOfLegoSets;
        }
    }

    //------------------------------------
    // LISTING METHODS - Advanced
    //------------------------------------

    //DONE Add a method, listLegoSetsBySpecificTheme(String).  The return type is String.
    //    This method returns a list of the lego sets of a specific theme stored in the array list (i.e.
    //     that match the parameter value).
    //     Each matching lego set should be on a new line and should be preceded by the index number e.g.
    //        1: Lego Set 2 Details
    //        4: Lego Set 5 Details
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    //    If there are no lego sets matching the theme, the return string should have "No Lego sets with theme".

    /**
     * This method returns a list of lego sets of a specific
     * theme stored in the arraylist.With each matching legoset
     * on a new line followed by the index number.If no legosets
     * in arraylist, return "no lego sets". If no lego sets match
     * the theme return " no lego sets with theme".
     * @param theme
     * @return each matching lego set is returned on a new line
     * followed by the index number. If there are no lego sets
     * stored in the arraylist, a string containing " no lego sets"
     * is returned. If there are no lego sets that match the theme,
     * a string containing " no lego sets with theme " is returned.
     */


    public String listLegoSetsBySpecificTheme(String theme) {
        if (legoSets.isEmpty()) {
            return "no lego sets stored";
        } else {
            String listByTheme = "";
            for (int i = 0; i < legoSets.size(); i++) {
                if (legoSets.get(i).getTheme().equalsIgnoreCase(theme))
                    listByTheme += i + ": " + legoSets.get(i) + "\n";
            }
            if (listByTheme.isEmpty())
                return "no lego sets with theme";
            return listByTheme;
        }
    }


    //DONE Add a method, listLegoSetsForAgeRatingAndAbove(int).  The return type is String.
    //    This method returns a list of the lego sets that are equal or above the age supplied as a parameter.
    //     Each matching lego set should be on a new line and should be preceded by the index number e.g.
    //        1: Lego Set 2 Details
    //        4: Lego Set 5 Details
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    //    If there are no lego sets equal or above the age, the return string should have "No Lego sets available".
    /**
     * This method returns a list of lego sets that are equal
     * or above the age provided .With each matching legoset
     * on a new line followed by the index number.If no legosets
     * in arraylist, return "no lego sets". If no lego sets
     * are equal or above the age return " no lego sets available".
     * @param age
     * @return each matching lego set is returned on a new line
     * followed by the index number. If there are no lego sets
     * stored in the arraylist, a string containing " no lego sets"
     * is returned. If no lego sets are equal or above the age
     * a string containing " no lego sets available" is returned.
     */

    public String listLegoSetsForAgeRatingAndAbove(int age) {
        if (legoSets.isEmpty()) {
            return "no lego sets";
        } else {
            String listByAgeAndAbove = "";
            for (int i = 0; i < legoSets.size(); i++) {
                if (legoSets.get(i).getMinimumAge() >= age)
                    listByAgeAndAbove += i + ": " + legoSets.get(i) + "\n";
            }
            if (listByAgeAndAbove.isEmpty())
                return "no lego sets available for age";
            return listByAgeAndAbove;
        }
    }

    //DONE Add a method, listAllInstructionBooklets().  The return type is String.
    //    This method returns a list of all the instruction booklets across all the lego set objects
    //    stored in the array list.
    //    Each instruction booklet should be on a new line and should contain the lego set name and code too e.g.
    //       Booket1.pdf (Fire Station, 43544)
    //       Booket2.pdf (Fire Station, 43544)
    //       Instructions1.pdf (Titanic, 54655)
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".

    /**
     * This method returns a list of instructionBooklets
     * across all legoset objects stored
     * in the arraylist, with each legoSet being on
     * a new line followed by index number. If no lego
     * sets are stored in the arraylist, "no lego sets"is returned
     * @return a string that returns a list of instructionBooklets
     * with index numbers, if no legosets are in the
     * arraylist, a string containing " no lego sets"
     * is returned
     */
    public String listAllInstructionBooklets() {

        if (legoSets.isEmpty()) {
            return "No lego Sets";
        } else {
            String listInstructionBooklets = "";
            for (int i = 0; i < legoSets.size(); i++) {
                for (int book = 0; book < legoSets.get(i).numberOfInstructionBooklets(); book++) {
                    listInstructionBooklets += legoSets.get(i).getInstructionBooklets().get(book).getFileName() + " (" + legoSets.get(i).getName() + ", " + legoSets.get(i).getCode() + ")\n";
                }
            }
            return listInstructionBooklets;
        }

    }

    //DONE Add a method, listStockStatusBySpecificTheme(String).  The return type is String.
    //    This method returns a report (the returned String) of the stock status of the lego sets in a specific theme.
    //    The report (the returned String) should include:
    //        the number or lego sets that are IN stock and the list of these lego sets (if no lego sets
    //             are in stock, this should be included in the returned string.
    //        the number or lego sets that are OUT OF stock and the list of these lego sets (if no lego sets
    //             are out of stock, this should be included in the returned string.
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    //    If there are no lego sets matching the theme, the return string should have "No Lego sets with theme".

    /**
     * This method returns a report of the stock status
     * of the lego sets at the specific time, report includes:
     * number of and list of lego sets in stock (if none are in
     * stock this is included in string), number and list of lego
     * sets out of stock (if none are out of stock this is included
     * in the string). If no lego sets in arraylist return " no lego sets",
     * if no lego sets match the theme return " no lego sets with theme".
     * @param theme
     * @return number and list of lego sets in stock(including if none are
     * in stock), number and list of lego sets out of stock(including if none
     * are out of stock). If no lego sets are stored in arraylist, " No lego sets" is returned.
     * If no lego sets match the theme, "No lego sets with theme" is returned.
     */
    public String listStockStatusBySpecificTheme(String theme) {
        if (legoSets.isEmpty()) {
            return "no lego sets stored";
        } else {
            String listOfLegoSetsInStock = "";
            String listOfLegoSetsOutStock = "";
            int inStock, numOutStock;
            inStock = numOutStock = 0;
            for (int i = 0; i < legoSets.size(); i++) {
                if (legoSets.get(i).getTheme().equalsIgnoreCase(theme)) {
                    if (legoSets.get(i).isInStock()) {
                        listOfLegoSetsInStock += i + ": " + legoSets.get(i) + "\n";
                        inStock++;
                    } else {
                        listOfLegoSetsOutStock += i + ": " + legoSets.get(i) + "\n";
                        numOutStock++;
                    }

                }
            }

            if (listOfLegoSetsInStock.isEmpty() && listOfLegoSetsOutStock.isEmpty()) {
                return "no lego sets with theme";
            } else{
                    return " number of sets out of stock: " + listOfLegoSetsOutStock + " number of sets in stock: " + listOfLegoSetsInStock;
                }

            }

        }



    //------------------------------
    //  FINDING METHODS
    //-------------------------------

    //DONE Add a method, findLegoSet(int).  The return type is LegoSet.
    //    This method returns the lego set stored at the index that was passed as a parameter.
    //    However, if the index is not valid, null is returned.

    /**
     * This method returns the lego set stored at the
     * index the user passed as a parameter, if the
     * index is not valid return null.
     * @param index
     * @return lego set stored at index that is passed
     * as a parameter, if not valid, return null.
     */
    public LegoSet findLegoSet(int index) {
        if (isValidIndex(index)) {
            return legoSets.get(index);
        }
        return null;
    }

    //DONE Add a method, findLegoSetByCode(int).  The return type is LegoSet.
    //    This method searches the array list for a lego set with a specific code (passed as a parameter).
    //    When a lego set is found for this code, it is returned back.
    //    If no lego set exists for that code, return null.
    // NOTE: the first lego set encountered is returned, even if more exist with that code.  For extra credit,
    //       you could add in validation to ensure that the code is unique when adding a LegoSet.

    /**
     *
     * This method searches the arraylist for a lego set with a
     * specific code.Once a lego set is found it is returned back
     * If none with the code passed exits, return null.
     * @param code
     * @return if lego set with matching code exits it is returned back.
     * If no lego set has a matching code, returned null.
     */
    public LegoSet findLegoSetByCode(int code) {

            for (int i = 0; i < legoSets.size(); i++) {
                if(legoSets.get(i).getCode()== code)
                return legoSets.get(i);
            }

        return null;
    }


    //------------------------------
    //  SEARCHING METHODS
    //-------------------------------

    //DONE Add a method, searchLegoSetsByName(String).  The return type is String.
    //    This method returns a list of the lego sets whose name contains the string passed as a parameter.
    //    Each matching lego set should be on a new line and should be preceded by the index number e.g.
    //        1: Lego Set 2 Details
    //        4: Lego Set 5 Details
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    //    If there are no lego sets whose name contains the supplied string, the return string should
    //    have "No Lego sets found".
    /**
     * This method returns a list of legoSets whose
     * name contains the string the user has entered, with
     * each legoSet being on a new line followed by index number.
     * If no lego sets are stored in the arraylist, "no lego sets"
     * is returned.If no lego set contain the string passed, "no
     * lego sets found "is returned.
     * @param name
     * @return a string that returns a list of legosets
     * with index numbers, if no legosets are in the
     * arraylist, a string containing " no lego sets"
     * is returned.If no legosets contain the string passed as a parameter
     * a string containing " no lego sets" is returned
     */
    public String searchLegoSetsByName(String name) {

        if (legoSets.isEmpty()) {
            return "no lego sets stored";
        } else {
            String listLegoSetsName = "";
            for (int i = 0; i < legoSets.size(); i++) {
                if (legoSets.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                    listLegoSetsName += i + ": " + legoSets.get(i) + "\n";
                }

            }
            if (!listLegoSetsName.isEmpty())
                return listLegoSetsName;
            return "No lego sets found";
        }
    }


    //DONE Add a method, searchInstructionBookletsByFileName(String).  The return type is String.
    //    This method returns a list of instruction booklets whose file name contains the string passed
    //    as a parameter.
    //    Each matching booklet should be on a new line and should contain the lego set name and code e.g.
    //        InstructionBook1.pdf in Fire Station (45343)
    //        InstructionBk2.pdf in Titanic (65434)
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    //    If there are no lego sets whose name contains the supplied string, the return string should
    //    have "No instruction booklets found".

    /**
     * This method returns a list of instructionBooklets whose
     * file name contains the string the user has entered, with
     * each legoSet being on a new line followed by index number.
     * If no lego sets are stored in the arraylist, "no lego sets"
     * is returned.If no lego set contain the string passed, "no
     * instruction booklets found "is returned.
     * @param fileName
     * @return a string that returns a list of instructionBooklets
     * with index numbers, if no legosets are in the
     * arraylist, a string containing " no lego sets"
     * is returned.If no legosets contain the string passed as a parameter
     * a string containing " no lego sets" is returned
     */
    public String searchInstructionBookletsByFileName(String fileName) {
        if (legoSets.isEmpty()) {
            return "no lego sets stored";
        } else {
            String findInstructionBooklets = "";
            for (int i = 0; i < legoSets.size(); i++) {
                for (int z = 0; z < legoSets.get(i).numberOfInstructionBooklets(); z++) {
                    if (legoSets.get(i).findInstructionBooklet(z).getFileName().toLowerCase().contains(fileName.toLowerCase()))
                        findInstructionBooklets += i + ": " + legoSets.get(i).listInstructionBooklets() + "\n";
                }
                }
                if (!findInstructionBooklets.isEmpty())
                    return findInstructionBooklets;
                return "no instruction booklets found";
            }

        }



    //-------------------------
    // HELPER METHODS
    //-------------------------

    //DONE Add a method, isValidIndex(int).  The return type is boolean.
    //    This method returns true if the value passed as a parameter is a valid index in the arraylist.
    //    However, if the index is not valid, false is returned.

    /**
     * This method returns true id the value passed as a parameter
     * is a valid index in the arraylist,if index is not valid,
     * returns false.
     * @param index
     * @return true is value passed is a valid index in arraylist,
     * if not valid index, returns false.
     */
    public boolean isValidIndex(int index){
        if (index>=0 && index < legoSets.size())
            return true;

    return false;
}

    //-------------------------
    // PERSISTENCE METHODS
    //-------------------------

    // DONE Add a method, load().  The return type is void.
    //    This method uses the XStream component to deserialise the lego sets and their associated booklets from
    //    an XML file into the legoSets array list.

    /**
     * This method uses the XStream component to read all
     * the legoset objects from the LegoSets.xml file stored
     * on the hard disk. The read legoSets are loaded into the arraylist.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception{
Class<?>[] classes = new Class[]{ LegoSet.class};

XStream xstream = new XStream(new DomDriver());
XStream.setupDefaultSecurity(xstream);
xstream.allowTypes(classes);

    ObjectInputStream is = xstream.createObjectInputStream(new FileReader("LegoSets.xml"));
    legoSets=(ArrayList<LegoSet>) is.readObject();
    is.close();
}

    //DONE Add a method, save().  The return type is void.
    //    This method uses the XStream component to serialise the lego sets and their associated booklets to
    //    an XML file.

    /**
     * This method uses the XStream component to write all the legoSet
     * objects in the arraylist to the LegoSets.xml file stored on the
     * hard disk.
     * @throws Exception
     */
    public void save() throws Exception {
    XStream xstream = new XStream(new DomDriver());
    ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("LegoSets.xml"));
    out.writeObject(legoSets);
    out.close();

}
}

