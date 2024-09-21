package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LegoSetTest {

    private LegoSet trainStation, lunarSpaceStation;
    private InstructionBooklet booklet1, booklet2, booklet3, booklet4, booklet5;

    @BeforeEach
    void setUp() {
        booklet1 = new InstructionBooklet(0, "InstructionBook -19" );
        booklet2 = new InstructionBooklet(1, "InstructionBook - 20" );
        booklet3 = new InstructionBooklet(79, "InstructionBook - 21.");
        booklet4 = new InstructionBooklet(80, "InstructionBk19.pdf");
        booklet5 = new InstructionBooklet(81, "Instruction-Bk20.PDF");

        //setting up trainStation with three booklets - in stock lego set
        trainStation = new LegoSet("Train Station",60335, 99.99, 907, "City", 9 );
        trainStation.addInstructionBooklet(booklet1);
        trainStation.addInstructionBooklet(booklet2);
        trainStation.addInstructionBooklet(booklet3);

        //setting up trainStation with no booklets - out of stock lego set
        lunarSpaceStation = new LegoSet("Lunar Space Station",60349, 79.99, 500, "Classic", 10);
        lunarSpaceStation.setInStock(false);
    }

    @AfterEach
    void tearDown() {
        booklet1 = booklet2 = booklet3 = booklet4 = booklet5 = null;
        trainStation = lunarSpaceStation = null;
    }

    @Nested
    class Constructors {

        @Test
        void constructorTestingTheSettingOfDefaultValues() {
            //testing invalid values (lower boundary) for each variable at constructor level
            LegoSet legoSet = new LegoSet("Testing names with exactly 34 char",
                    9999, 0, 0, "Architecture", 5);
            assertEquals("Testing names with exactly 34 char", legoSet.getName());   // value accepted - max 35 chars
            assertEquals(10000, legoSet.getCode());       // 9999 not a valid code, default applied
            assertEquals(Double.MAX_VALUE, legoSet.getCost());    // 0 not valid, defaults to Max int value
            assertEquals(1, legoSet.getPieceCount());     // 0 not valid, defaults to 1
            assertEquals("Classic", legoSet.getTheme());  // Architecture not valid, defaults Classic
            assertEquals(4, legoSet.getMinimumAge());     // 5 is not valid, default to 4.
            assertEquals(true, legoSet.isInStock());      // default value is true.

            //testing invalid values (upper boundary) for each variable at constructor level
            legoSet = new LegoSet("Testing names with exactly 35 chars",
                    100000, 1234567, 2001, "Cities", 19);
            assertEquals("Testing names with exactly 35 chars", legoSet.getName()); // value accepted - max 35 chars
            assertEquals(10000, legoSet.getCode());       // 100000 not a valid code, default applied
            assertEquals(1234567, legoSet.getCost());     // value accepted - no limit on cost (must be min 1)
            assertEquals(1, legoSet.getPieceCount());     // 2001 not valid, defaults to 1
            assertEquals("Classic", legoSet.getTheme());  // Cities not valid, defaults Classic
            assertEquals(4, legoSet.getMinimumAge());     // 19 is not valid, default to 4.
            assertEquals(true, legoSet.isInStock());      // default value is true.
        }

        @Test
        void constructorTestingValidValues() {
            //testing for values that lie on the lower boundary.
            LegoSet legoSet = new LegoSet("Testing names with exactly 35 chars",
                    10000, 1, 1, "city", 4);
            assertEquals("Testing names with exactly 35 chars", legoSet.getName());  // value accepted - max 35 chars
            assertEquals(10000, legoSet.getCode());   // value accepted - max value is 99999
            assertEquals(1, legoSet.getCost());       // value accepted - no limit on cost (must be min 1)
            assertEquals(1, legoSet.getPieceCount()); // value accepted - between 1 and 2000 (both inclusive)
            assertEquals("city", legoSet.getTheme());  // value accepted - city is valid (check ignores case)
            assertEquals(4, legoSet.getMinimumAge());  // value accepted - 4 is  valid.
            assertEquals(true, legoSet.isInStock());      // default value is true.

            //testing for values that lie on the upper boundary.
            legoSet = new LegoSet("Testing names with exactly 36 chars!", 99999, 9876543, 2000, "FRIENDS", 18);
            assertEquals("Testing names with exactly 36 chars", legoSet.getName());   // value accepted - max 35 chars
            assertEquals(99999, legoSet.getCode());        // value accepted - max value is 99999
            assertEquals(9876543, legoSet.getCost());      // value accepted - no limit on cost (must be min 1)
            assertEquals(2000, legoSet.getPieceCount());   // value accepted - max value is 2000
            assertEquals("FRIENDS", legoSet.getTheme());   // value accepted - FRIENDS is valid entry (check ignores case)
            assertEquals(18, legoSet.getMinimumAge());     // value accepted - 18 is a valid entry
            assertEquals(true, legoSet.isInStock());      // default value is true.
        }

    }

    @Nested
    class GettersAndSetters {

        @Test
        void legoSetNameGetAndSetWorkingCorrectly() {
            //testing 34 chars - update performed
            assertEquals("Lunar Space Station", lunarSpaceStation.getName());
            lunarSpaceStation.setName("Lunar Space Station-Apollo Edition");
            assertEquals("Lunar Space Station-Apollo Edition", lunarSpaceStation.getName());

            //testing 35 chars - update performed
            lunarSpaceStation.setName("Lunar Space Station-Apollo Edition!");
            assertEquals("Lunar Space Station-Apollo Edition!", lunarSpaceStation.getName());

            //tests 36 chars - update should be ignored
            lunarSpaceStation.setName("Lunar Space Station: APOLLO Edition!");
            assertEquals("Lunar Space Station-Apollo Edition!", lunarSpaceStation.getName());
        }

        @Test
        void legoSetCodeGetAndSetWorkingCorrectly() {
            //NOTE: valid values are 10000 to 99999 inclusive
            assertEquals(60335, trainStation.getCode());  //current value

            //Value 9999 - outside range; update should be ignored
            trainStation.setCode(9999);
            assertEquals(60335, trainStation.getCode());

            //Value 100000 - outside range; update should be ignored
            trainStation.setCode(100000);
            assertEquals(60335, trainStation.getCode());

            //Value 10000 - update should be performed
            trainStation.setCode(10000);
            assertEquals(10000, trainStation.getCode());

            //Value 99999 - update should be performed
            trainStation.setCode(99999);
            assertEquals(99999, trainStation.getCode());
        }

        @Test
        void legoSetCostGetAndSetWorkingCorrectly() {
            //NOTE: valid values is greater than zero (no upper limit)
            assertEquals(99.99, trainStation.getCost());  //current value

            //Value 0 - outside range; update should be ignored
            trainStation.setCost(0);
            assertEquals(99.99, trainStation.getCost());

            //Value 119.99 - update should be performed
            trainStation.setCost(119.99);
            assertEquals(119.99, trainStation.getCost());
        }

        @Test
        void legoSetPieceCountGetAndSetWorkingCorrectly() {
            //NOTE: valid values must be between 1 and 2000 (both inclusive)
            assertEquals(907, trainStation.getPieceCount());  //current value

            //Value 0 - outside range; update should be ignored
            trainStation.setPieceCount(0);
            assertEquals(907, trainStation.getPieceCount());

            //Value 2001 - outside range; update should be ignored
            trainStation.setPieceCount(2001);
            assertEquals(907, trainStation.getPieceCount());

            //Value 1 - update should be performed
            trainStation.setPieceCount(1);
            assertEquals(1, trainStation.getPieceCount());

            //Value 2000 - update should be performed
            trainStation.setPieceCount(2000);
            assertEquals(2000, trainStation.getPieceCount());
        }

        @Test
        void instructionalBookletListGetAndSetWorkingCorrectly() {
            //The lego set, lunarSpaceStation, has no instructional booklets
            assertEquals(0, lunarSpaceStation.numberOfInstructionBooklets());
            assertEquals(0, lunarSpaceStation.getInstructionBooklets().size());

            //Creating an arraylist of instructional booklets with two booklets added to it
            ArrayList<InstructionBooklet> newList = new ArrayList<>(){{
                    add(booklet4);
                    add(booklet5);
            }};

            //Updating the lunarSpaceStation lego set to have the new list of booklets (2 booklets in it).
            lunarSpaceStation.setInstructionBooklets(newList);

            //The lego set, lunarSpaceStation, should now have two instructional booklets
            assertEquals(2, lunarSpaceStation.numberOfInstructionBooklets());
            assertEquals(2, lunarSpaceStation.getInstructionBooklets().size());
        }

        @Test
        void legoSetThemeGetAndSetWorkingCorrectly() {
            //NOTE: valid values listed in ThemeUtility are City, Classic, Creator, Friends.
            assertEquals("City", trainStation.getTheme());

            //Value "Architecture" - not in list of themes; update should be ignored
            trainStation.setTheme("Architecture");
            assertEquals("City", trainStation.getTheme());

            //Value "friends" - in list of themes; case sensitivity is ignored in the check.  Value updated
            trainStation.setTheme("friends");
            assertEquals("friends", trainStation.getTheme());

            //Value "City" - in list of themes; case sensitivity is ignored in the check.  Value updated
            trainStation.setTheme("City");
            assertEquals("City", trainStation.getTheme());
        }


        @Test
        void legoSetMinAgeGetAndSetWorkingCorrectly() {
            //NOTE: valid values listed in MinimumAgeUtility are 4, 6, 9, 10, 13, 18
            assertEquals(9, trainStation.getMinimumAge());

            //Value 8 - not in list of min ages; update should be ignored
            trainStation.setMinimumAge(8);
            assertEquals(9, trainStation.getMinimumAge());

            //Value 10 - is in list of min ages; update should be done
            trainStation.setMinimumAge(10);
            assertEquals(10, trainStation.getMinimumAge());
        }
    }

    @Nested
    class ArrayListTests {
        @Test
        void numberOfInstructionBookletsCalculatedCorrectly() {
            assertEquals(3, trainStation.numberOfInstructionBooklets());
            assertEquals(0, lunarSpaceStation.numberOfInstructionBooklets());
        }

        @Test
        void addInstructionBookletsIncreasesSizeOfArrayList(){
            //adding booklets to a lego set when booklets already exist
            assertEquals(3, trainStation.numberOfInstructionBooklets());
            assertTrue(trainStation.addInstructionBooklet(booklet4));
            assertEquals(4, trainStation.numberOfInstructionBooklets());
            assertEquals(booklet4, trainStation.findInstructionBooklet(3));

            //adding booklets to a lego set when no booklets exist yet
            assertEquals(0, lunarSpaceStation.numberOfInstructionBooklets());
            assertTrue(lunarSpaceStation.addInstructionBooklet(booklet5));
            assertEquals(1, lunarSpaceStation.numberOfInstructionBooklets());
            assertEquals(booklet5, lunarSpaceStation.findInstructionBooklet(0));
        }

        @Test
        void updateInstructionBookletChangesArrayListContentsWhenBookletExists() {

            assertEquals(3, trainStation.numberOfInstructionBooklets());  // verify 3 booklets in lego set

            //verify the third booklet's contents
            assertEquals(79, trainStation.getInstructionBooklets().get(2).getNumberOfPages());
            assertEquals("InstructionBook - 21", trainStation.getInstructionBooklets().get(2).getFileName());

            //update third booklet's filename and number of pages
            trainStation.updateInstructionBooklet(2, "NewFileName", 7);

            //verify the update was successful
            assertEquals(3, trainStation.numberOfInstructionBooklets());  // verify 3 booklets still in lego set
            assertEquals(7, trainStation.getInstructionBooklets().get(2).getNumberOfPages());
            assertEquals("NewFileName", trainStation.getInstructionBooklets().get(2).getFileName());
        }

        @Test
        void updateInstructionBookletDoesNotChangeArrayListContentsWhenNoBookletExists(){

            assertEquals(3, trainStation.numberOfInstructionBooklets());  // verify 3 booklets in lego set

            //attempt to update a fourth booklet's filename and number of pages should return false i.e. no booklet exists at location 3.
            assertFalse(trainStation.updateInstructionBooklet(3, "NewFileName", 7));

            //attempt to update a booklet at location -1 should return false i.e. no booklet exists at that location
            assertFalse(trainStation.updateInstructionBooklet(-1, "NewFileName", 7));
        }

        @Test
        void deleteItemDecreasesSizeOfArrayList(){
            //deleting booklets from a lego set when booklets already exist
            assertEquals(3, trainStation.numberOfInstructionBooklets());  // 3 booklets in lego set
            assertEquals(booklet1, trainStation.deleteInstructionBooklet(0));  // delete booklet1 from lego set
            assertEquals(2, trainStation.numberOfInstructionBooklets());  // 2 booklets in lego set
            assertEquals(booklet2, trainStation.deleteInstructionBooklet(0));  // delete booklet2 from lego set
            assertEquals(1, trainStation.numberOfInstructionBooklets());  // 1 booklet in lego set

            //deleting booklets when no booklets exist
            assertEquals(0, lunarSpaceStation.numberOfInstructionBooklets());  // no booklets in lego set
            assertNull(lunarSpaceStation.deleteInstructionBooklet(0));  //returns null as no booklets exist for lego set
        }

        @Test
        void validateIndexValidatesCorrectly(){
            //boundary testing valid indices when booklets exist for a lego set
            assertEquals(3, trainStation.numberOfInstructionBooklets());  // 3 booklets in lego set
            assertTrue(trainStation.isValidIndex(0));
            assertTrue(trainStation.isValidIndex(2));
            assertFalse(trainStation.isValidIndex(-1));
            assertFalse(trainStation.isValidIndex(3));

            //boundary testing valid indices when NO booklets exist for a lego set
            assertEquals(0, lunarSpaceStation.numberOfInstructionBooklets());  // No booklets in lego set
            assertFalse(lunarSpaceStation.isValidIndex(0));
            assertFalse(lunarSpaceStation.isValidIndex(-1));
            assertFalse(lunarSpaceStation.isValidIndex(1));
        }

        @Test
        void findInstructionBookletReturnsCorrectBooklet(){
            assertEquals(3, trainStation.numberOfInstructionBooklets());  // 3 booklets in lego set

            assertEquals(trainStation.findInstructionBooklet(0), booklet1);  //booklet1 is stored in location 0
            assertEquals(trainStation.findInstructionBooklet(1), booklet2);  //booklet2 is stored in location 1
            assertEquals(trainStation.findInstructionBooklet(2), booklet3);  //booklet3 is stored in location 2

            assertNull(trainStation.findInstructionBooklet(-1));  //null returned as no booklet is at location -1
            assertNull(trainStation.findInstructionBooklet(3));   //null returned as no booklet is at location 3
        }

        @Test
        void listInstructionBooksReturnsCorrectDetails(){
            //when a lego set has books added, check the details of the books are included in the string
            assertTrue(trainStation.listInstructionBooklets().contains("(1 page)"));  //checking for booklet1 details
            assertTrue(trainStation.listInstructionBooklets().contains("InstructionBook -19"));  //checking for booklet1 details
            assertTrue(trainStation.listInstructionBooklets().contains("(1 page)"));  //checking for booklet2 details
            assertTrue(trainStation.listInstructionBooklets().contains("InstructionBook - 20"));  //checking for booklet2 details
            assertTrue(trainStation.listInstructionBooklets().contains("(79 pages)"));  //checking for booklet3 details
            assertTrue(trainStation.listInstructionBooklets().contains("InstructionBook - 21"));  //checking for booklet3 details

            //when no instruction books are added to a lego set, check the string contains information signaling this
            assertTrue(lunarSpaceStation.listInstructionBooklets().contains("No Instruction Book"));
        }
    }

    @Nested
    class BoilerPlateTests {
        @Test
        void validatingTheEqualsMethod() {
            //Creating the same lego set (with same booklets) as the trainStation object above
            LegoSet otherTrainStation = new LegoSet("Train Station",60335, 99.99, 907, "City", 9 );
            otherTrainStation.addInstructionBooklet(booklet1);
            otherTrainStation.addInstructionBooklet(booklet2);
            otherTrainStation.addInstructionBooklet(booklet3);

            //checking the trainStation and otherTrainStation objects are not the same identity i.e. memory location
            assertNotSame(trainStation, otherTrainStation);

            //checking the trainStation and otherTrainStation objects have the same object state i.e. variable contents.
            assertEquals(trainStation, otherTrainStation);
        }

        @Test
        void toStringContainsAllFieldsInObject() {
            assertTrue(trainStation.toString().contains("Train Station"));   //Checking that the name is included
            assertTrue(trainStation.toString().contains("99.99"));           //Checking that the cost is included
            assertTrue(trainStation.toString().contains("60335"));           //Checking that the code is included
            assertTrue(trainStation.toString().contains("907"));             //Checking that the piece count is included
            assertTrue(trainStation.toString().contains("City"));           //Checking that the theme is included
            assertTrue(trainStation.toString().contains("9"));              //Checking that the age is included
        }

        @Test
        void toStringDoesFormattingAsSpeced() {

            assertTrue(trainStation.toString().contains("€99.99"));         //Checking that the cost has €
            assertTrue(trainStation.toString().contains("907 pieces"));     //Checking that the piece count has pieces beside it
            assertTrue(trainStation.toString().contains("in stock"));       //Checking that it is "in stock"
            assertTrue(trainStation.toString().contains("9+"));             //Checking that the age + is included

            assertTrue(lunarSpaceStation.toString().contains("not available"));  //Checking that it is NOT in stock

        }

        @Test
        void toStringContainsInstructionBookletsInObject() {
            //Checking that the instruction booklets are included:
            assertTrue(trainStation.listInstructionBooklets().contains("(1 page)"));  //checking for booklet1 details
            assertTrue(trainStation.listInstructionBooklets().contains("InstructionBook -19"));  //checking for booklet1 details
        }

    }

}
