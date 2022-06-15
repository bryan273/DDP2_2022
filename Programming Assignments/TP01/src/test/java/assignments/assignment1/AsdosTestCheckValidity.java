package assignments.assignment1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsdosTestCheckValidity {
    @BeforeAll
    public static void setUp() {
        IdGenerator.buildMapCharToValue();
    }

    @Test
    public void testCheckValidityOne() {
        assertEquals(false, IdGenerator.checkValidity("SSI211411028Y"));
    }

    @Test
    public void testCheckValidityTwo() {
        assertEquals(true, IdGenerator.checkValidity("DIK22110599AS"));
    }

    @Test
    public void testCheckValidityThree() {
        assertEquals(true, IdGenerator.checkValidity("MTI19040700EA"));
    }

    @Test
    public void testCheckValidityFour() {
        assertEquals(false, IdGenerator.checkValidity("MTI19040700US"));
    }

    @Test
    public void testCheckValidityFive() {
        assertEquals(false, IdGenerator.checkValidity("MTI19040700ea"));
    }

    @Test
    public void testCheckValiditySix() {
        assertEquals(true, IdGenerator.checkValidity("MIK21180203WX"));
    }

    @Test
    public void testCheckValiditySeven() {
        assertEquals(false, IdGenerator.checkValidity("mik21180203wx"));
    }

    @Test
    public void testCheckValidityEight() {
        assertEquals(false, IdGenerator.checkValidity("SSI21170803DF"));
    }

    @Test
    public void testCheckValidityNine() {
        assertEquals(true, IdGenerator.checkValidity("SIK20291101TW"));
    }

    @Test
    public void testCheckValidityTen() {
        assertEquals(false, IdGenerator.checkValidity("SIK20291101Tw"));
    }

    @Test
    public void testCheckValidityEleven() {
        assertEquals(true, IdGenerator.checkValidity("MTI002911013H"));
    }

    @Test
    public void testCheckValidityTwelve() {
        assertEquals(false, IdGenerator.checkValidity("MTI202211011TW"));
    }

    @Test
    public void testCheckValidityThirteen() {
        assertEquals(false, IdGenerator.checkValidity("DIK00291100BX"));
    }

    @Test
    public void testCheckValidityFourteen() {
        assertEquals(false, IdGenerator.checkValidity("Mti00211101BH"));
    }

    @Test
    public void testCheckValidityFifteen() {
        assertEquals(true, IdGenerator.checkValidity("SSI20150402JI"));
    }

    @Test
    public void testCheckValiditySixteen() {
        assertEquals(false, IdGenerator.checkValidity("SSI20150402JIK"));
    }

    @Test
    public void testCheckValiditySeventeen() {
        assertEquals(false, IdGenerator.checkValidity("SsI20150402JI"));
    }

    @Test
    public void testCheckValidityEighteen() {
        assertEquals(false, IdGenerator.checkValidity("SSI20150402AI"));
    }

    @Test
    public void testCheckValidityNineteen() {
        assertEquals(false, IdGenerator.checkValidity("SSI20150402JC"));
    }

    @Test
    public void testCheckValidityTwenty() {
        assertEquals(true, IdGenerator.checkValidity("DIK1017081021"));
    }

    @Test
    public void testCheckValidityTwentyOne() {
        assertEquals(true, IdGenerator.checkValidity("MIK042810024D"));
    }

    @Test
    public void testCheckValidityTwentyTwo() {
        assertEquals(false, IdGenerator.checkValidity("SIK19210818VS"));
    }

    @Test
    public void testCheckValidityTwentyThree() {
        assertEquals(true, IdGenerator.checkValidity("MIK110205195H"));
    }

    @Test
    public void testCheckValidityTwentyFour() {
        assertEquals(false, IdGenerator.checkValidity("MIK05260520E1"));
    }

    @Test
    public void testCheckValidityTwentyFive() {
        assertEquals(true, IdGenerator.checkValidity("SSI1304091775"));
    }

    @Test
    public void testCheckValidityTwentySix() {
        assertEquals(false, IdGenerator.checkValidity("DIK03170506IT"));
    }

    @Test
    public void testCheckValidityTwentySeven() {
        assertEquals(true, IdGenerator.checkValidity("SSI00071002ZA"));
    }

    @Test
    public void testCheckValidityTwentyEight() {
        assertEquals(false, IdGenerator.checkValidity("DIK10230609CF"));
    }

    @Test
    public void testCheckValidityTwentyNine() {
        assertEquals(true, IdGenerator.checkValidity("DIK100205197B"));
    }

    @Test
    public void testCheckValidityThirty() {
        assertEquals(false, IdGenerator.checkValidity("MtI002911013H"));
    }

    @Test
    public void testCheckValidityThirtyOne() {
        assertEquals(true, IdGenerator.checkValidity("DIK001919009H"));
    }

    @Test
    public void testCheckValidityThirtyTwo() {
        assertEquals(false, IdGenerator.checkValidity("dik182803017x"));
    }

    @Test
    public void testCheckValidityThirtyThree() {
        assertEquals(true, IdGenerator.checkValidity("SSI09301101B3"));
    }

    @Test
    public void testCheckValidityThirtyFour() {
        assertEquals(false, IdGenerator.checkValidity("SIK20291101WA"));
    }

    @Test
    public void testCheckValidityThirtyFive() {
        assertEquals(false, IdGenerator.checkValidity("SSI17220413IMT"));
    }

    @Test
    public void testCheckValidityThirtySix() {
        assertEquals(false, IdGenerator.checkValidity("SSI000101013b"));
    }

    @Test
    public void testCheckValidityThirtySeven() {
        assertEquals(false, IdGenerator.checkValidity("SSI18141214S85"));
    }

    @Test
    public void testCheckValidityThirtyEight() {
        assertEquals(false, IdGenerator.checkValidity(""));
    }
}
