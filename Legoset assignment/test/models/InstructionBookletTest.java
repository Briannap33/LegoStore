package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InstructionBookletTest {

    private InstructionBooklet booklet1, booklet2, booklet3, booklet4, booklet5;

    @BeforeEach
     void setUp() {
        booklet1 = new InstructionBooklet(0, "InstructionBook -19");
        booklet2 = new InstructionBooklet(1, "InstructionBook - 20");
        booklet3 = new InstructionBooklet(80, "InstructionBook - 21.");
        booklet4 = new InstructionBooklet(79, "InstructionBk19.pdf");
        booklet5 = new InstructionBooklet(81, "Instruction-Bk20.PDF");
    }

    @AfterEach
    void tearDown() {
        booklet1 = booklet2 = booklet3 = booklet4 = booklet5 = null;
    }


        @Test
        void constructorTests() {
            //testing lower boundaries for fileName and numberOfPages at constructor level
            assertEquals("InstructionBook -19", booklet1.getFileName());   // value accepted - max 20 chars
            assertEquals(1, booklet1.getNumberOfPages());                  // 0 not valid, defaults to 1

            //testing on boundaries for fileName and numberOfPages at constructor level
            assertEquals("InstructionBook - 20", booklet2.getFileName());   // value accepted - max 20 chars
            assertEquals(1, booklet2.getNumberOfPages());                   // 0 not valid, defaults to 1

            //testing upper boundaries for fileName and numberOfPages at constructor level
            //booklet = new InstructionBooklet(80, "Booklet name 21 chars");
            assertEquals("InstructionBook - 21", booklet3.getFileName());   // value truncated - max 20 chars
            assertEquals(80, booklet3.getNumberOfPages());                  // value accepted - max 80

            //testing upper boundaries numberOfPages at constructor level
            assertEquals(1, booklet5.getNumberOfPages());  // 81 not valid, defaults to 1
        }


        @Test
        void fileNameGetAndSetWorkingCorrectly() {
            //testing 19 chars - update performed
            assertEquals("InstructionBook -19", booklet1.getFileName());
            booklet1.setFileName("InstructionBook--19");
            assertEquals("InstructionBook--19", booklet1.getFileName());

            //testing 20 chars - update performed
            assertEquals("InstructionBook - 20", booklet2.getFileName());
            booklet2.setFileName("InstructionBookNo.20");
            assertEquals("InstructionBookNo.20", booklet2.getFileName());

            //tests 21 chars - update should be ignored
            assertEquals("InstructionBook - 21", booklet3.getFileName());
            booklet3.setFileName("InstructionBook - 21.");
            assertEquals("InstructionBook - 21", booklet3.getFileName());
        }

        @Test
        void pageNumbersGetAndSetWorkingCorrectly() {
            //testing setting to zero - update should be ignored
            assertEquals(1, booklet2.getNumberOfPages());
            booklet2.setNumberOfPages(0);
            assertEquals(1, booklet2.getNumberOfPages());

            //testing setting to 1 - update done
            assertEquals(80, booklet3.getNumberOfPages());
            booklet3.setNumberOfPages(1);
            assertEquals(1, booklet3.getNumberOfPages());

            //testing setting to 2 - update done
            assertEquals(1, booklet2.getNumberOfPages());
            booklet2.setNumberOfPages(2);
            assertEquals(2, booklet2.getNumberOfPages());

            //testing setting to 79 - update done
            assertEquals(2, booklet2.getNumberOfPages());
            booklet2.setNumberOfPages(79);
            assertEquals(79, booklet2.getNumberOfPages());

            //testing setting to 80 - update done
            assertEquals(79, booklet2.getNumberOfPages());
            booklet2.setNumberOfPages(80);
            assertEquals(80, booklet2.getNumberOfPages());

            //testing setting to 81 - update should be ignored
            assertEquals(80, booklet2.getNumberOfPages());
            booklet2.setNumberOfPages(81);
            assertEquals(80, booklet2.getNumberOfPages());
        }


        @Test
        void validatingTheEqualsMethod() {
            InstructionBooklet booklet = new InstructionBooklet(80, "InstructionBook - 21.");
            assertNotSame(booklet3, booklet);  //checking they are not the same identity i.e. the same memory location
            assertEquals(booklet3, booklet);  //checking they have the same object state i.e. variable contents.
        }

    @Nested
    class ToString {

        @Test
        void toStringContainsAllFieldsInObject() {
            //checking an Instruction Booklet contains number of pages and a file name
            assertTrue(booklet2.toString().contains("1"));
            assertTrue(booklet2.toString().contains("InstructionBook - 20"));

            //checking an Instruction Booklet contains number of pages and a file name
            assertTrue(booklet4.toString().contains("79"));
            assertTrue(booklet4.toString().contains("InstructionBk19.pdf"));
        }

        @Test
        void toStringAddsPDFToFileNameIfNeeded() {
            //checking tha pdf is added as a file extension when no pdf extension is there.
            assertTrue(booklet2.toString().contains("InstructionBook - 20.pdf"));
            assertTrue(booklet4.toString().contains("InstructionBk19.pdf"));
            assertFalse(booklet4.toString().contains("InstructionBk19.pdf.pdf"));
        }

        @Test
        void toStringAddsPageToTheString() {
            //"pages" is added to the number of pages when it is not equal 1, "page" otherwise.
            assertTrue(booklet2.toString().contains("1 page"));
            assertTrue(booklet4.toString().contains("79 pages"));
        }
    }
}
