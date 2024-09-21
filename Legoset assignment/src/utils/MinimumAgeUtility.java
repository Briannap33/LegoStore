package utils;

import java.util.ArrayList;
import java.util.Arrays;

public class MinimumAgeUtility {

    private static ArrayList<Integer> ages = new ArrayList<>(
            Arrays.asList(4,6,9,10,13,18));

    public static ArrayList<Integer> getAges() {
        return ages;
    }

    public static boolean isValidAge(int ageToCheck) {
        return ages.contains(ageToCheck);
    }

}
