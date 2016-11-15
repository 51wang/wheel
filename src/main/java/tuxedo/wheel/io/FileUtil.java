package tuxedo.wheel.io;

import java.io.File;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

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

    public static void deleteRecursively(File path) {
        if (path.exists()) {
            if (path.isDirectory()) {
                for (File child : path.listFiles()) {
                    deleteRecursively(child);
                }
            }
            path.delete();
        }
    }
}
