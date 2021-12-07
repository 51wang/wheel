package tuxedo.wheel.utility.assembler;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class MapAssemblerTest {
    @Test
    public void test() {
        Map<String, String> actual =
                new MapAssembler<String, String>().put("a", "1").put("b", "2").put("c", "3").assemble();
        Map<String, String> expected = new HashMap<String, String>();
        expected.put("a", "1");
        expected.put("b", "2");
        expected.put("c", "3");
        Assert.assertEquals(actual, expected);
    }
}
