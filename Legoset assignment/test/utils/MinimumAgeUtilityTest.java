package utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MinimumAgeUtilityTest {

    @Test
    void getAgesReturnsFullListOfAges() {
        ArrayList<Integer> ages = MinimumAgeUtility.getAges();
        assertEquals(6, ages.size());
        assertTrue(ages.contains(4));
        assertTrue(ages.contains(6));
        assertTrue(ages.contains(9));
        assertTrue(ages.contains(10));
        assertTrue(ages.contains(13));
        assertTrue(ages.contains(18));
    }

    @Test
    void isValidAgeReturnsTrueWhenAgeExists() {
        assertTrue(MinimumAgeUtility.isValidAge(4));
        assertTrue(MinimumAgeUtility.isValidAge(18));
    }

    @Test
    void isValidAgeReturnsFalseWhenAgeDoesNotExist() {
        assertFalse(MinimumAgeUtility.isValidAge(0));
        assertFalse(MinimumAgeUtility.isValidAge(3));
        assertFalse(MinimumAgeUtility.isValidAge(17));
    }
}