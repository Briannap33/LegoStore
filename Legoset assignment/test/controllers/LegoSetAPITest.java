package controllers;

import models.InstructionBooklet;
import models.LegoSet;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LegoSetAPITest {

    private LegoSet trainStation, lunarSpaceStation, skatePark, campingTrip, diner, mustang;
    private InstructionBooklet booklet1, booklet2, booklet3, booklet4, booklet5, booklet6, booklet7;

    private LegoSetAPI legoSets;
    private LegoSetAPI noLegoSets;

    @BeforeEach
    void setUp() {

        //setting up trainStation lego set with three booklets - in stock
        trainStation = new LegoSet("Train Station",60335, 99.99, 907, "City", 9 );
        booklet1 = new InstructionBooklet(0, "InstructionBook -19" );
        booklet2 = new InstructionBooklet(1, "InstructionBook - 20" );
        booklet3 = new InstructionBooklet(79, "InstructionBook - 21.");
        trainStation.addInstructionBooklet(booklet1);
        trainStation.addInstructionBooklet(booklet2);
        trainStation.addInstructionBooklet(booklet3);

        //setting up camping trip lego set with two booklets - in stock
        campingTrip = new LegoSet("Holiday Camping Trip", 41726, 25, 87, "Friends", 4);
        booklet4 = new InstructionBooklet(80, "InstructionBk19.pdf");
        booklet5 = new InstructionBooklet(81, "Instruction-Bk20.PDF");
        campingTrip.addInstructionBooklet(booklet4);
        campingTrip.addInstructionBooklet(booklet5);

        //setting up diner lego set with two booklets - out of  stock
        diner = new LegoSet("Heartlake Downtown Diner", 41728, 30, 346, "Friends", 4);
        booklet6 = new InstructionBooklet(34, "Instruction-01.pdf");
        booklet7 = new InstructionBooklet(12, "Instruction-02.pdf");
        diner.addInstructionBooklet(booklet6);
        diner.addInstructionBooklet(booklet7);
        diner.setInStock(false);

        //setting up lunar space station lego set with no booklets - out of stock
        lunarSpaceStation = new LegoSet("Lunar Space Station",60349, 79.99, 500, "Classic", 10);
        lunarSpaceStation.setInStock(false);

        //setting up skate park lego set with no booklets - in stock
        skatePark = new LegoSet("Skate Park", 41751, 50, 431, "Friends", 6);

        //setting up mustang lego set with two booklets - in  stock
        mustang = new LegoSet("Ford Mustang",10265, 170, 1471, "Creator", 16);
        mustang.addInstructionBooklet(booklet2);
        mustang.addInstructionBooklet(booklet5);

        //Adding the 6 lego sets to this collection of LegoSets
        legoSets = new LegoSetAPI();
        legoSets.addLegoSet(trainStation);
        legoSets.addLegoSet(campingTrip);
        legoSets.addLegoSet(diner);
        legoSets.addLegoSet(lunarSpaceStation);
        legoSets.addLegoSet(skatePark);
        legoSets.addLegoSet(mustang);

        //Adding NO lego sets to this collection of LegoSets
        noLegoSets = new LegoSetAPI();
    }

    @AfterEach
    void tearDown() {
        booklet1 = booklet2 = booklet3 = booklet4 = booklet5 = booklet6 = booklet7 = null;
        trainStation = lunarSpaceStation = skatePark = campingTrip = diner = mustang = null;
        legoSets = noLegoSets = null;
    }


    @Nested
    class ArrayListCRUD {

        @Test
        void addingLegoSetAddsToArrayList() {
            //Create a new lego set that we will add to the legoSets array list.
            LegoSet spaceShuttle = new LegoSet("Space Shuttle",10245, 230, 1651, "Creator", 18);

            //The new lego set should be added to the arraylist
            assertTrue(legoSets.addLegoSet(spaceShuttle));

            //Retrieve the lego set that was just added (from the last location in the list) and check it is spaceShuttle
            assertEquals(spaceShuttle, legoSets.findLegoSet(legoSets.numberOfLegoSets() - 1));
        }

        @Test
        void updatingALegoSetThatDoesNotExistReturnsFalse() {
            //There should be 6 legosets in this array list.
            assertEquals(6, legoSets.numberOfLegoSets());
            // Should not be allowed to update a legoset that doesn't exist in a populated list
            assertFalse(legoSets.updateLegoSet(6, "House", 12345, 12, 1234, "City", 4));
            assertFalse(legoSets.updateLegoSet(-1, "House", 12345, 12, 1234, "City", 4));

            //There should be no legosets in this array list.
            assertEquals(0, noLegoSets.numberOfLegoSets());
            // Should not be allowed to update a legoset in an empty list
            assertFalse(noLegoSets.updateLegoSet(0, "House", 12345, 12, 1234, "City", 4));
        }

        @Test
        void updatingALegoSetThatExistsReturnsTrueAndUpdates() {
            //check the mustang lego set exists in the array list (at location 5) and verify the contents
            assertEquals(mustang, legoSets.findLegoSet(5));
            assertEquals("Ford Mustang", legoSets.findLegoSet(5).getName());
            assertEquals(10265, legoSets.findLegoSet(5).getCode());
            assertEquals(170, legoSets.findLegoSet(5).getCost());
            assertEquals(1471, legoSets.findLegoSet(5).getPieceCount());
            assertEquals("Creator", legoSets.findLegoSet(5).getTheme());
            assertEquals(4, legoSets.findLegoSet(5).getMinimumAge());

            //update the lego set at location 5 with new information and ensure contents updated successfully
            assertTrue(legoSets.updateLegoSet(5, "House", 12345, 12, 1234, "City", 6));
            assertEquals("House", legoSets.findLegoSet(5).getName());
            assertEquals(12345, legoSets.findLegoSet(5).getCode());
            assertEquals(12, legoSets.findLegoSet(5).getCost());
            assertEquals(1234, legoSets.findLegoSet(5).getPieceCount());
            assertEquals("City", legoSets.findLegoSet(5).getTheme());
            assertEquals(6, legoSets.findLegoSet(5).getMinimumAge());
        }

        @Test
        void deletingALegoSetThatDoesNotExistReturnsNull() {
            //There should be no legosets in this array list.
            assertEquals(0, noLegoSets.numberOfLegoSets());
            // Null should be returned as we cannot delete a legoset from an empty list
            assertNull(noLegoSets.deleteLegoSet(0));

            //There should be 6 legosets in this array list.
            assertEquals(6, legoSets.numberOfLegoSets());
            // Null should be returned as we cannot delete a legoset that doesn't exist in a list
            assertNull(legoSets.deleteLegoSet(-1));
            assertNull(legoSets.deleteLegoSet(6));
        }

        @Test
        void deletingALegoSetThatExistsDeletesAndReturnsDeletedObject() {
            assertEquals(6, legoSets.numberOfLegoSets());  //should be 6 legosets in the list
            assertEquals(mustang, legoSets.deleteLegoSet(5));
            assertEquals(5, legoSets.numberOfLegoSets());  //should now be 5 legosets in the list

            // mustang code is 10265. Check it is no longer in the arraylist.
            assertNull(legoSets.findLegoSetByCode(10265));
        }
    }

    @Nested
    class ArrayListSetStockStatus {

        @Test
        void settingAnExistingLegoSetInStockReturnsBooleanResult() {
            // Camping Trip lego set is already in stock so should return false when attempting to set it in stock
            assertTrue(legoSets.findLegoSet(1).isInStock());
            assertFalse(legoSets.setLegoSetInStock(1));
            assertTrue(legoSets.findLegoSet(1).isInStock());

            // Diner lego set is out of stock so should return true when attempting to set it in stock
            assertFalse(legoSets.findLegoSet(2).isInStock());
            assertTrue(legoSets.setLegoSetInStock(2));
            assertTrue(legoSets.findLegoSet(2).isInStock());
        }

        @Test
        void settingAnExistingLegoSetOutOfStockReturnsBooleanResult() {
            // Diner lego set is already out of stock so should return false when attempting to set it out of stock
            assertFalse(legoSets.findLegoSet(2).isInStock());
            assertFalse(legoSets.setLegoSetOutOfStock(2));
            assertFalse(legoSets.findLegoSet(2).isInStock());

            // Camping Trip lego set is in stock so should return true when attempting to set it out of stock
            assertTrue(legoSets.findLegoSet(1).isInStock());
            assertTrue(legoSets.setLegoSetOutOfStock(1));
            assertFalse(legoSets.findLegoSet(1).isInStock());
        }

        @Test
        void settingStockStatusForNonExistantLegoSetsReturnsFalse() {
            assertEquals(6, legoSets.numberOfLegoSets());  //should be 6 legosets in the list
            assertFalse(legoSets.setLegoSetInStock(-1));     // false because lego set doesn't exist
            assertFalse(legoSets.setLegoSetInStock(6));      // false because lego set doesn't exist

            assertEquals(0, noLegoSets.numberOfLegoSets());  //should be 0 legosets in the list
            assertFalse(noLegoSets.setLegoSetInStock(0));            // false because lego set doesn't exist
        }
    }

    @Nested
    class CountingMethodsBasic {

        @Test
        void numberOfLegoSetsCalculatedCorrectly() {
            assertEquals(6, legoSets.numberOfLegoSets());
            assertEquals(0, noLegoSets.numberOfLegoSets());
        }

        @Test
        void numberOfInStockLegoSetsCalculatedCorrectly() {
            assertEquals(4, legoSets.numberOfLegoSetsInStock());
            assertEquals(0, noLegoSets.numberOfLegoSetsInStock());
        }

        @Test
        void numberOfOutOfStockLegoSetsCalculatedCorrectly() {
            assertEquals(2, legoSets.numberOfLegoSetsOutOfStock());
            assertEquals(0, noLegoSets.numberOfLegoSetsOutOfStock());
        }
    }


    @Nested
    class CountingMethodsAdvanced {

        @Test
        void numberOfLegoSetsByThemeCalculatedCorrectly() {
            //Checking the number of sets in each of the four categories
            assertEquals(1, legoSets.numberOfLegoSetsByTheme("City"));
            assertEquals(1, legoSets.numberOfLegoSetsByTheme("Classic"));
            assertEquals(1, legoSets.numberOfLegoSetsByTheme("Creator"));
            assertEquals(3, legoSets.numberOfLegoSetsByTheme("Friends"));

            //Checking that case sensitivity is taken into consideration in the counting
            assertEquals(3, legoSets.numberOfLegoSetsByTheme("friends"));

            //Checking that an invalid theme returns zero
            assertEquals(0, legoSets.numberOfLegoSetsByTheme("Not Valid Theme"));

            //Checking that an empty arraylist returns zero.
            assertEquals(0, noLegoSets.numberOfLegoSetsByTheme("Friends"));

        }

        @Test
        void numberOfLegoSetsForAgeRatingAndAboveCalculatedCorrectly() {
            //Checking the number of sets at or above different ages
            assertEquals(6, legoSets.numberOfLegoSetsForAgeRatingAndAbove(4));
            assertEquals(3, legoSets.numberOfLegoSetsForAgeRatingAndAbove(6));
            assertEquals(2, legoSets.numberOfLegoSetsForAgeRatingAndAbove(9));
            assertEquals(0, legoSets.numberOfLegoSetsForAgeRatingAndAbove(13));

            //Checking that an empty arraylist returns zero.
            assertEquals(0, noLegoSets.numberOfLegoSetsForAgeRatingAndAbove(4));
        }

        @Test
        void totalNumberOfInstructionBookletsCalculatedCorrectly() {
            //Checking all the booklets are counted up
            assertEquals(9, legoSets.totalNumberOfInstructionBooklets());
            //Checking that an empty arraylist returns zero.
            assertEquals(0, noLegoSets.totalNumberOfInstructionBooklets());
        }
    }

    @Nested
    class ListAllLegoSets{

        @Test
        void listAllLegoSetsReturnsNoLegoSetsStoredMessageWhenArrayListIsEmpty() {
            assertEquals(0, noLegoSets.numberOfLegoSets());
            assertTrue(noLegoSets.listAllLegoSets().toLowerCase().contains("no lego"));
        }

        @Test
        void listAllLegoSetsReturnsLegoSetsWhenArrayListNotEmpty() {
            assertEquals(6, legoSets.numberOfLegoSets());
            String legoSetString = legoSets.listAllLegoSets();
            assertTrue(legoSetString.contains("Train Station"));
            assertTrue(legoSetString.contains("Camping Trip"));
            assertTrue(legoSetString.contains("Downtown Diner"));
            assertTrue(legoSetString.contains("Space Station"));
            assertTrue(legoSetString.contains("Skate Park"));
            assertTrue(legoSetString.contains("Ford Mustang"));
        }

        @Test
        void listAllLegoSetsIncludesInstructionalBooklets() {
            assertEquals(6, legoSets.numberOfLegoSets());
            String legoSetString = legoSets.listAllLegoSets();
            assertTrue(legoSetString.contains("InstructionBook -19"));
            assertTrue(legoSetString.contains("InstructionBook - 20"));
            assertTrue(legoSetString.contains("InstructionBook - 21"));
            assertTrue(legoSetString.contains("InstructionBk19.pdf"));
            assertTrue(legoSetString.contains("Instruction-Bk20.PDF"));
            assertTrue(legoSetString.contains("Instruction-01.pdf"));
            assertTrue(legoSetString.contains("Instruction-02.pdf"));
        }
    }

    @Nested
    class ListLegoSetsByStockStatus {

        @Test
        void listAllInStockLegoSetsReturnsNoLegoSetsWhenNoInStockExists() {
            //Checking for in stock lego sets when no lego sets added to array list
            assertEquals(0, noLegoSets.numberOfLegoSetsInStock());
            assertTrue(noLegoSets.listLegoSetsInStock().toLowerCase().contains("no lego set"));
        }

        @Test
        void listAllOutOfStockLegoSetsReturnsNoLegoSetsWhenNoOutOfStockExists() {
            //Checking for out of stock lego sets when no lego sets added to array list
            assertEquals(0, noLegoSets.numberOfLegoSetsInStock());
            assertTrue(noLegoSets.listLegoSetsOutOfStock().toLowerCase().contains("no lego set"));
        }

        @Test
        void listAllInStockLegoSetsReturnsLegoSetsWhenInStockExists() {
            assertEquals(4, legoSets.numberOfLegoSetsInStock());
            String legoSetString = legoSets.listLegoSetsInStock();
            // In Stock - should be on the list
            assertTrue(legoSetString.contains("Train Station"));
            assertTrue(legoSetString.contains("Camping Trip"));
            assertTrue(legoSetString.contains("Skate Park"));
            assertTrue(legoSetString.contains("Ford Mustang"));
            // Out of Stock - should not be on the list
            assertFalse(legoSetString.contains("Downtown Diner"));
            assertFalse(legoSetString.contains("Space Station"));
        }

        @Test
        void listAllOutOfStockLegoSetsReturnsLegoSetsWhenOutOfStockExists(){
            assertEquals(2, legoSets.numberOfLegoSetsOutOfStock());
            String legoSetString = legoSets.listLegoSetsOutOfStock();
            // Out of Stock - should be on the list
            assertTrue(legoSetString.contains("Downtown Diner"));
            assertTrue(legoSetString.contains("Space Station"));
            // In Stock - should not be on the list
            assertFalse(legoSetString.contains("Train Station"));
            assertFalse(legoSetString.contains("Camping Trip"));
            assertFalse(legoSetString.contains("Skate Park"));
            assertFalse(legoSetString.contains("Ford Mustang"));
        }
    }

    @Nested
    class ListLegoSetsByTheme {

        @Test
        void listByThemeReturnsNoLegoSetsWhenNoLegoSetsExist() {
            //Checking for lego sets by a theme when no lego sets added to array list
            assertEquals(0, noLegoSets.numberOfLegoSets());
            assertTrue(noLegoSets.listLegoSetsBySpecificTheme("Classic").toLowerCase().contains("no lego sets stored"));
        }

        @Test
        void listByThemeReturnsMessageWhenLegoSetsExistButNoneMatchTheme() {
            //Checking for lego sets by a theme when  lego sets exist, but none match theme
            assertEquals(0, legoSets.numberOfLegoSetsByTheme("Architecture"));
            String legoSetString = legoSets.listLegoSetsBySpecificTheme("Architecture").toLowerCase();
            assertTrue(legoSetString.contains("no lego sets with theme"));
        }

        @Test
        void listByThemeReturnsLegoSetsWhenLegoSetsByThatThemeExists() {
            assertEquals(3, legoSets.numberOfLegoSetsByTheme("Friends"));
            String legoSetString = legoSets.listLegoSetsBySpecificTheme("Friends");
            // Should be on the list
            assertTrue(legoSetString.contains("Downtown Diner"));
            assertTrue(legoSetString.contains("Camping Trip"));
            assertTrue(legoSetString.contains("Skate Park"));
            // Should not be on the list
            assertFalse(legoSetString.contains("Space Station"));
            assertFalse(legoSetString.contains("Train Station"));
            assertFalse(legoSetString.contains("Ford Mustang"));
        }
    }

    @Nested
    class ListAllBooklets {

        @Test
        void noInstructionalBookletsReturnedWhenNoLegoSetsExist() {
            //Checking for booklets when no lego sets added to array list
            assertEquals(0, noLegoSets.totalNumberOfInstructionBooklets());
            assertTrue(noLegoSets.listAllInstructionBooklets().toLowerCase().contains("no lego set"));
        }

        @Test
        void instructionalBookletsReturnedWhenLegoSetsExist() {
            assertEquals(9, legoSets.totalNumberOfInstructionBooklets());
            String legoSetString = legoSets.listAllInstructionBooklets();
            assertTrue(legoSetString.contains("InstructionBook -19"));
            assertTrue(legoSetString.contains("InstructionBook - 20"));
            assertTrue(legoSetString.contains("InstructionBook - 21"));
            assertTrue(legoSetString.contains("InstructionBk19.pdf"));
            assertTrue(legoSetString.contains("Instruction-Bk20.PDF"));
            assertTrue(legoSetString.contains("Instruction-01.pdf"));
            assertTrue(legoSetString.contains("Instruction-02.pdf"));
        }
    }

    @Nested
    class ListLegoSetsByAge {

        @Test
        void noLegoSetsReturnedWhenNoLegoSetsExist() {
            //Checking for lego sets by age when no lego sets added to array list
            assertEquals(0, noLegoSets.numberOfLegoSets());
            assertTrue(noLegoSets.listLegoSetsForAgeRatingAndAbove(4).toLowerCase().contains("no lego set"));
        }

        @Test
        void noLegoSetsReturnedWhenNoLegoSetsExistAtOrAboveAge() {
            //Checking for lego sets by entered age when no lego sets exist for that age or above
            assertEquals(6, legoSets.numberOfLegoSets());
            assertTrue(legoSets.listLegoSetsForAgeRatingAndAbove(18).toLowerCase().contains("no lego sets available for age"));
        }

        @Test
        void legoSetsReturnedWhenLegoSetsExistAtOrAboveAge() {
            //Checking for lego sets by entered age when lego sets exist for that age or above
            assertEquals(3, legoSets.numberOfLegoSetsForAgeRatingAndAbove(6));
            String legoSetString = legoSets.listLegoSetsForAgeRatingAndAbove(6);

            // Should be on the list
            assertTrue(legoSetString.contains("Skate Park"));
            assertTrue(legoSetString.contains("Space Station"));
            assertTrue(legoSetString.contains("Train Station"));

            // Should not be on the list
            assertFalse(legoSetString.contains("Downtown Diner"));
            assertFalse(legoSetString.contains("Camping Trip"));
            assertFalse(legoSetString.contains("Ford Mustang"));
        }
    }

    @Nested
    class ListLegoSetStockByTheme {

        @Test
        void listStockStatusByThemeReturnsNoLegoSetsWhenNoLegoSetsExist() {
            //Checking for lego sets by a theme when no lego sets added to array list
            assertEquals(0, noLegoSets.numberOfLegoSets());
            assertTrue(noLegoSets.listStockStatusBySpecificTheme("Classic").toLowerCase().contains("no lego sets stored"));
        }

        @Test
        void listStockStatusByThemeReturnsMessageWhenLegoSetsExistButNoneMatchTheme() {
            //Checking for lego sets by a theme when lego sets exist, but none match theme
            assertEquals(0, legoSets.numberOfLegoSetsByTheme("Architecture"));
            String legoSetString = legoSets.listStockStatusBySpecificTheme("Architecture").toLowerCase();
            assertTrue(legoSetString.contains("no lego sets with theme"));
        }

        @Test
        void listStockStatusByThemeReturnsLegoSetsWheLegoSetsByThatThemeExists() {

            assertEquals(3, legoSets.numberOfLegoSetsByTheme("Friends"));
            String legoSetString = legoSets.listStockStatusBySpecificTheme("friends");
            // Should be in the returned String:

            assertTrue(legoSetString.contains("2"));                //number of items in stock
            assertTrue(legoSetString.contains("Camping Trip"));     //  in stock
            assertTrue(legoSetString.contains("Skate Park"));       //  in stock
            assertTrue(legoSetString.contains("1"));               //number of items not in stock
            assertTrue(legoSetString.contains("Downtown Diner"));  //   not in stock
            // Should not be in the returned String - doesn't match the Friends theme:
            assertFalse(legoSetString.contains("Space Station"));
            assertFalse(legoSetString.contains("Train Station"));
            assertFalse(legoSetString.contains("Ford Mustang"));


            assertEquals(1, legoSets.numberOfLegoSetsByTheme("Creator"));
            legoSetString = legoSets.listStockStatusBySpecificTheme("creator");
            // Should be in the returned String:
            assertTrue(legoSetString.contains("1"));              //number of items in stock
            assertTrue(legoSetString.contains("Ford Mustang"));   //  in stock
            assertTrue(legoSetString.contains("0"));              //number of items not in stock
            // Should not be in the returned String - doesn't match the Creator theme:
            assertFalse(legoSetString.contains("Space Station"));
            assertFalse(legoSetString.contains("Train Station"));
            assertFalse(legoSetString.contains("Camping Trip"));
            assertFalse(legoSetString.contains("Skate Park"));
            assertFalse(legoSetString.contains("Downtown Diner"));


            assertEquals(1, legoSets.numberOfLegoSetsByTheme("Classic"));
            legoSetString = legoSets.listStockStatusBySpecificTheme("classic");
            // Should be in the returned String:
            assertTrue(legoSetString.contains("0"));                     //number of items in stock
            assertTrue(legoSetString.contains("1"));                     //number of items not in stock
            assertTrue(legoSetString.contains("Lunar Space Station"));   //  not in stock
            // Should not be in the returned String - doesn't match the Classic theme:
            assertFalse(legoSetString.contains("Mustang"));
            assertFalse(legoSetString.contains("Train Station"));
            assertFalse(legoSetString.contains("Camping Trip"));
            assertFalse(legoSetString.contains("Skate Park"));
            assertFalse(legoSetString.contains("Downtown Diner"));
        }

    }

    @Nested
    class FindMethods {

        @Test
        void findLegoSetReturnsLegoSetWhenIndexIsValid() {
            assertEquals(6, legoSets.numberOfLegoSets());
            assertEquals(trainStation, legoSets.findLegoSet(0));
            assertEquals(mustang, legoSets.findLegoSet(5));
        }

        @Test
        void findLegoSetReturnsNullWhenIndexIsInValid() {
            //No lego set is returned as no lego sets are added to the list
            assertEquals(0, noLegoSets.numberOfLegoSets());
            assertNull(noLegoSets.findLegoSet(0));

            //No lego set is returned when lego sets exist, but the index doesn't exist in the list
            assertEquals(6, legoSets.numberOfLegoSets());
            assertNull(legoSets.findLegoSet(-1));    // index -1 doesn't exist (valid indexes are 0-5)
            assertNull(legoSets.findLegoSet(6));    // index 6 doesn't exist (valid indexes are 0-5)
        }

        @Test
        void findLegoSetReturnsLegoSetWhenCodeIsValid() {
            assertEquals(6, legoSets.numberOfLegoSets());
            assertEquals(trainStation, legoSets.findLegoSetByCode(60335));
            assertEquals(mustang, legoSets.findLegoSetByCode(10265));
        }

        @Test
        void findLegoSetReturnsNullWhenCodeIsInValid() {
            //No lego set is returned as no lego sets are added to the list
            assertEquals(0, noLegoSets.numberOfLegoSets());
            assertNull(noLegoSets.findLegoSetByCode(10265));  // no lego sets exist, so code doesn't exist.

            //No lego set is returned when lego sets exist, but the code doesn't exist in the list
            assertEquals(6, legoSets.numberOfLegoSets());
            assertNull(legoSets.findLegoSetByCode(60334));    // code doesn't exist
            assertNull(legoSets.findLegoSetByCode(60315));    // code doesn't exist
        }

      //  @Test
      //  void findLegoSetByBookletName() {
      //      //DUMMY TEST FOR WORKING WITH IGNORING TESTS
      //      assertNull(legoSets.findLegoSetByBookletName(60334));    // method doesn't exist
      //  }
    }

    @Nested
    class SearchLegoSetsByName {

        @Test
        void searchLegoSetsByNameReturnsNoLegoSetsWhenArrayListIsEmpty() {
            assertEquals(0, noLegoSets.numberOfLegoSets());  //checking no lego sets were added
            assertTrue(noLegoSets.searchLegoSetsByName("a").toLowerCase().contains("no lego sets stored"));
        }

        @Test
        void searchLegoSetsByNameReturnsMessageWhenLegoSetsExistButNoneMatch() {
            assertEquals(6, legoSets.numberOfLegoSets());  //checking lego sets added
            assertTrue(legoSets.searchLegoSetsByName("random set").toLowerCase().contains("no lego sets found"));
        }

        @Test
        void searchLegoSetsByNameReturnsLegoSetsThatMatchEnteredName() {
            assertEquals(6, legoSets.numberOfLegoSets());  //checking lego sets added

            String legoSetsString = legoSets.searchLegoSetsByName("station");  //also tests for case sensitivity of search
            //Should be included
            assertTrue(legoSetsString.contains("Train Station"));
            assertTrue(legoSetsString.contains("Lunar Space Station"));
            //Should not be included
            assertFalse(legoSetsString.contains("Diner"));
        }
    }

    @Nested
    class SearchBookletsByFileName {

        @Test
        void searchBookletsByFileNameReturnsNoLBookletsWhenArrayListIsEmpty() {
            assertEquals(0, noLegoSets.numberOfLegoSets());  //checking no lego sets were added
            assertTrue(noLegoSets.searchInstructionBookletsByFileName("a").toLowerCase().contains("no lego sets stored"));
        }

        @Test
        void searchBookletsByFileNameReturnsMessageWhenBookletsExistButNoneMatch() {
            assertEquals(6, legoSets.numberOfLegoSets());  //checking lego sets added
            assertTrue(legoSets.searchInstructionBookletsByFileName("random file").toLowerCase().contains("no instruction booklets found"));
        }

        @Test
        void searchBookletsByFileNameReturnsBookletsThatMatch() {
            assertEquals(6, legoSets.numberOfLegoSets());  //checking lego sets added

            String booklets = legoSets.searchInstructionBookletsByFileName("instruction-0");  //also tests for case sensitivity of search
            //Should be included
            assertTrue(booklets.contains("Instruction-01.pdf"));
            assertTrue(booklets.contains("Instruction-02.pdf"));
            //Should not be included
            assertFalse(booklets.contains("InstructionBk19.pdf"));
        }

    }

    @Nested
    class HelperMethods {
        @Test
        void validIndexReturnsTrueWhenIndexExists() {
            assertTrue(legoSets.isValidIndex(0)); //index of 0 exists
            assertTrue(legoSets.isValidIndex(legoSets.numberOfLegoSets() -1)); //valid indices 0-5. 5 is valid
        }

        @Test
        void validIndexReturnsFalseWhenIndexDoesNotExist() {
            assertFalse(noLegoSets.isValidIndex(0));  //no lego sets added, so index 0 is invalid
            assertFalse(legoSets.isValidIndex(-1));   //no index of -1 exists
            assertFalse(legoSets.isValidIndex(legoSets.numberOfLegoSets())); //valid indices 0-5. 6 is not valid
        }
    }

}

