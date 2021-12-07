package tuxedo.wheel.utility.io;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import java.io.File;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtil {
    public static void toZip(File dir, File destFile) {
        Project project = new Project();
        FileSet fileSet = new FileSet();
        fileSet.setProject(project);
        fileSet.setDir(dir);
        Zip zip = new Zip();
        zip.setProject(project);
        zip.setDestFile(destFile);
        zip.addFileset(fileSet);
        zip.execute();
    }

    public static boolean deleteRecursively(File path) {
        if (!path.exists()) {
            return true;
        }

        if (path.isDirectory()) {
            for (File child : path.listFiles()) {
                if (!deleteRecursively(child)) {
                    return false;
                }
            }
        }
        return path.delete();
    }
}
