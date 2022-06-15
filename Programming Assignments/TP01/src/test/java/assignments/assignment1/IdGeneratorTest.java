package assignments.assignment1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class IdGeneratorTest {
    @BeforeAll
    public static void setUp() {
        IdGenerator.buildMapCharToValue();
    }

    @Test
    public void testGenerateIdOne() {
        assertEquals("ID Anggota: SSI21170803DE", IdGenerator.generateId("SSI", "2021", "17/08/2003"));
    }

    @Test
    public void testGenerateIdTwo() {
        assertEquals("Input tidak valid!", IdGenerator.generateId("SIK", "217", "09/09/1999"));
    }

    @Test
    public void testGenerateIdThree() {
        assertEquals("Input tidak valid!", IdGenerator.generateId("SIK", "2017", "09-091999"));
    }

    @Test
    public void testGenerateIdFour() {
        assertEquals("Input tidak valid!", IdGenerator.generateId("Tekkom", "2017", "09/09/1999"));
    }

    @Test
    public void testCheckValidityExpectTrueOne() {
        assertEquals(true, IdGenerator.checkValidity("SSI21170803DE"));
    }

    @Test
    public void testCheckValidityExpectTrueTwo() {
        assertEquals(true, IdGenerator.checkValidity("DIK18010195MC"));
    }

    @Test
    public void testCheckValidityExpectTrueThree() {
        assertEquals(true, IdGenerator.checkValidity("MTI19021298JV"));
    }

    @Test
    public void testCheckValidityExpectFalseOne() {
        assertEquals(false, IdGenerator.checkValidity("914532"));
    }

    @Test
    public void testCheckValidityExpectFalseTwo() {
        assertEquals(false, IdGenerator.checkValidity("MAS190389089A"));
    }

    @Test
    public void testCheckValidityExpectFalseThree() {
        assertEquals(false, IdGenerator.checkValidity("SKI190212989A"));
    }
}
