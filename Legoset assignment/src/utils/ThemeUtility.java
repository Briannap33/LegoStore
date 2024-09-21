package utils;

import java.util.ArrayList;

public class ThemeUtility {

    private static ArrayList<String> themes = new ArrayList<>(){{
        add("Classic");
        add("City");
        add("Creator");
        add("Friends");
    }};

    public static ArrayList<String> getThemes() {
        return themes;
    }

    public static boolean isValidTheme(String themeToCheck) {
        for (String theme : themes) {
            if (theme.equalsIgnoreCase(themeToCheck)) {
                return true;
            }
        }
        return false;
    }

}
