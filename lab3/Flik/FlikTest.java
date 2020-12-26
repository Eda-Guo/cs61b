import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FlikTest {
    @Test
    public void testIsSameNumber(){
        Integer a1 = 127;
        Integer b1 = 127;
        Integer a2 = 128;
        Integer b2 = 128;
        boolean exp = true;
        assertTrue("fail: 127 should be equal to 127", Flik.isSameNumber(a1, b1)== exp);
        assertTrue("fail: 128 should be equal to 128", Flik.isSameNumber(a2, b2)== exp);
    }
}
