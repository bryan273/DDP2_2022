package assignments.assignment1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsdosTestInputValidity {
    @BeforeAll
    public static void setUp() {
        IdGenerator.buildMapCharToValue();
    }

    @Test
    public void testInputValidOne() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("DIK", "2020", "19/1/2001"));
    }

    @Test
    public void testInputValidTwo() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("MIK", "2022", "24/12/1928"));
    }

    @Test
    public void testInputValidThree() {
        assertEquals("ID Anggota: MTI001111717N",
                IdGenerator.generateId("MTI", "2000", "11/11/1971"));
    }

    @Test
    public void testInputValidFour() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("", "2000", "04/03/1975"));
    }

    @Test
    public void testInputValidFive() {
        assertEquals("ID Anggota: MIK21191099NU",
                IdGenerator.generateId("MIK", "2021", "19/10/1999"));
    }

    @Test
    public void testInputValidSix() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("MTI", "2000", "29-06-1983"));
    }

    @Test
    public void testInputValidSeven() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("SIK", "2015", "19/9/1919"));
    }

    @Test
    public void testInputValidEight() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("MTI", "2010", "2/10/1989"));
    }

    @Test
    public void testInputValidNine() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("DIK", "2001", "19/12/001/"));
    }

    @Test
    public void testInputValidTen() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("MTI", "2003", "/11/11/002"));
    }

    @Test
    public void testInputValidEleven() {
        assertEquals("ID Anggota: SSI19120201GE",
                IdGenerator.generateId("SSI", "2019", "12/02/2001"));
    }

    @Test
    public void testInputValidTwelve() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("SSI", "2020", "5/Jan-2011"));
    }

    @Test
    public void testInputValidThirteen() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("SlK", "0000", "10/02/2000"));
    }

    @Test
    public void testInputValidFourteen() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("DIK", "2006", "9/9/1999"));
    }

    @Test
    public void testInputValidFifteen() {
        assertEquals("ID Anggota: MIK03041294RT",
                IdGenerator.generateId("MIK", "2003", "04/12/1994"));
    }

    @Test
    public void testInputValidSixteen() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("SII", "2021", "04/12/2001"));
    }

    @Test
    public void testInputValidSeventeen() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("DIK", "2121", "04/09/2004"));
    }

    @Test
    public void testInputValidEighteen() {
        assertEquals("ID Anggota: DIK2109100432",
                IdGenerator.generateId("DIK", "2021", "09/10/2004"));
    }

    @Test
    public void testInputValidNineteen() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("DIK", "2006", "0a/9/i00z"));
    }

    @Test
    public void testInputValidTwenty() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("ssi", "2121", "04/09/2004"));
    }

    @Test
    public void testInputValidTwentyOne() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("DIK", "21", "19/01/2001"));
    }

    @Test
    public void testInputValidTwentyTwo() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("STEI", "2021", "20-04/2002"));
    }

    @Test
    public void testInputValidTwentyThree() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("SSI", "2021", "17-9/2002"));
    }

    @Test
    public void testInputValidTwentyFour() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("", "2021", ""));
    }

    @Test
    public void testInputValidTwentyFive() {
        assertEquals("ID Anggota: DIK002001995A",
                IdGenerator.generateId("DIK", "2000", "20/01/1999"));
    }
}
