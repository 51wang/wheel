package tuxedo.wheel.utility.assembler;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

public class SetAssemblerTest {
    @Test
    public void test() {
        Set<String> actual = new SetAssembler<String>().add("a").add("b").add("c").assemble();
        Set<String> expected = new HashSet<String>();
        expected.add("a");
        expected.add("b");
        expected.add("c");
        Assert.assertEquals(actual, expected);
    }
}
