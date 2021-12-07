package tuxedo.wheel.utility.assembler;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ListAssemblerTest {
    @Test
    public void test() {
        List<String> actual = new ListAssembler<String>().add("a").add("b").add("c").assemble();
        List<String> expected = new ArrayList<String>();
        expected.add("a");
        expected.add("b");
        expected.add("c");
        Assert.assertEquals(actual, expected);
    }
}
