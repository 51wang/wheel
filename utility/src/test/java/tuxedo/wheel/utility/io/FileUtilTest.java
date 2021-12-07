package tuxedo.wheel.utility.io;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class FileUtilTest {
    @Test
    public void testToZip() {
        File root = new File("target/").getAbsoluteFile();
        File zip = new File(root, "test.zip");
        zip.delete();
        Assert.assertFalse(zip.exists());
        FileUtil.toZip(new File(root, "classes"), zip);
        Assert.assertTrue(zip.exists());
        zip.delete();
    }

    @Test
    public void testDeleteRecursively() throws IOException {
        File root = new File("target/xyz/").getAbsoluteFile();
        root.mkdir();
        File dir1 = new File(root, "a");
        dir1.mkdir();
        new File(dir1, "x").createNewFile();
        File dir2 = new File(dir1, "b");
        dir2.mkdir();
        File dir3 = new File(dir1, "c");
        dir3.mkdir();
        new File(dir3, "y").createNewFile();
        new File(dir3, "z").createNewFile();
        Assert.assertTrue(root.exists());
        Assert.assertTrue(FileUtil.deleteRecursively(root));
        Assert.assertFalse(root.exists());
    }
}
