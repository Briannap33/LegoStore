package utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ThemeUtilityTest {

    @Test
    void getThemesReturnsFullThemesSet() {
        ArrayList<String> themes = ThemeUtility.getThemes();
        assertEquals(4, themes.size());
        assertTrue(themes.contains("City"));
        assertTrue(themes.contains("Classic"));
        assertTrue(themes.contains("Creator"));
        assertTrue(themes.contains("Friends"));
    }

    @Test
    void isValidThemeReturnsTrueWhenThemeExists() {
        //also checking that case is ignored when looking for valid themes
        assertTrue(ThemeUtility.isValidTheme("Classic"));
        assertTrue(ThemeUtility.isValidTheme("classic"));
        assertTrue(ThemeUtility.isValidTheme("FRIENDS"));
    }

    @Test
    void isValidThemeReturnsFalseWhenThemeDoesNotExist() {
        assertFalse(ThemeUtility.isValidTheme("Classi"));
        assertFalse(ThemeUtility.isValidTheme("Creatorr"));
        assertFalse(ThemeUtility.isValidTheme(""));
    }
}