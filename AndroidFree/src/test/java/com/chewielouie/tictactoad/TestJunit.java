import static org.junit.Assert.*;

import org.junit.Test;

public class TestJunit {

    @Test
    public void testFail() {
        fail("failure!");
    }
}
