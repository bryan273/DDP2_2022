package assignments.assignment1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsdosTestGenerateId {
    @BeforeAll
    public static void setUp() {
        IdGenerator.buildMapCharToValue();
    }

    @Test
    public void testGenerateIdOne() {
        assertEquals("ID Anggota: MIK18100802E0",
                IdGenerator.generateId("MIK", "2018", "10/08/2002"));
    }

    @Test
    public void testGenerateIdTwo() {
        assertEquals("ID Anggota: DIK16090499PV",
                IdGenerator.generateId("DIK", "2016", "09/04/1999"));
    }

    @Test
    public void testGenerateIdThree() {
        assertEquals("ID Anggota: MTI1221118994",
                IdGenerator.generateId("MTI", "2012", "21/11/1989"));
    }

    @Test
    public void testGenerateIdFour() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("DIK", "2002", "1/11/1990"));
    }

    @Test
    public void testGenerateIdFive() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("SIK", "2019", "10/8/1997"));
    }

    @Test
    public void testGenerateIdSix() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("SSI", "2007", "14/12/990"));
    }

    @Test
    public void testGenerateIdSeven() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("dik", "2018", "14/12/2000"));
    }

    @Test
    public void testGenerateIdEight() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("SSI", "1980", "14/12/2000"));
    }

    @Test
    public void testGenerateIdNine() {
        assertEquals("ID Anggota: SSI041312783Y",
                IdGenerator.generateId("SSI", "2004", "13/12/1978"));
    }

    @Test
    public void testGenerateIdTen() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("DIK", "2009", "02//201/2004"));
    }

    @Test
    public void testGenerateIdEleven() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("SSI", "205", "13/12/1978"));
    }

    @Test
    public void testGenerateIdTwelve() {
        assertEquals("ID Anggota: MTI002104014D",
                IdGenerator.generateId("MTI", "2000", "21/04/2001"));
    }

    @Test
    public void testGenerateIdThirteen() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("SSI", "1987", "20/02/1964"));
    }

    @Test
    public void testGenerateIdFourteen() {
        assertEquals("ID Anggota: MIK181911004H",
                IdGenerator.generateId("MIK", "2018", "19/11/2000"));
    }

    @Test
    public void testGenerateIdFifteen() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("SSI", "2006", "04/013/201"));
    }

    @Test
    public void testGenerateIdSixteen() {
        assertEquals("ID Anggota: SIK010501901C",
                IdGenerator.generateId("SIK", "2001", "05/01/1990"));
    }

    @Test
    public void testGenerateIdSeventeen() {
        assertEquals("ID Anggota: MIK00290200HZ",
                IdGenerator.generateId("MIK", "2000", "29/02/2000"));
    }

    @Test
    public void testGenerateIdEighteen() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("SIK", "21", "31/01/2003"));
    }

    @Test
    public void testGenerateIdNineteen() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("MTI", "2005", "14/1/02001"));
    }

    @Test
    public void testGenerateIdTwenty() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("mti", "2021", "12/12/2012"));
    }

    @Test
    public void testGenerateIdTwentyOne() {
        assertEquals("ID Anggota: DIK21010100U8",
                IdGenerator.generateId("DIK", "2021", "01/01/2000"));
    }

    @Test
    public void testGenerateIdTwentyTwo() {
        assertEquals("ID Anggota: SSI00201280W7",
                IdGenerator.generateId("SSI", "2000", "20/12/1980"));
    }

    @Test
    public void testGenerateIdTwentyThree() {
        assertEquals("ID Anggota: SIK21300105X0",
                IdGenerator.generateId("SIK", "2021", "30/01/2005"));
    }

    @Test
    public void testGenerateIdTwentyFour() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("MIK", "02", "01/11/1990"));
    }

    @Test
    public void testGenerateIdTwentyFive() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("MTI", "2032", "10/08/1997"));
    }

    @Test
    public void testGenerateIdTwentySix() {
        assertEquals("Input tidak valid!",
                IdGenerator.generateId("SSI", "2007", "1/01/2000"));
    }
}
