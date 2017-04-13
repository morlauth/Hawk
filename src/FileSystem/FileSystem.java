package FileSystem;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

public class FileSystem {

    String appData;
    String userRoot;

    public FileSystem(String ad, String ur) {
        appData = ad;
        userRoot = ur;
    }

    /*
    * Takes a path string and a content string
    * Writes content to the path
    */
    public void writeSync(String path, String content) throws IOException {
        File file = new File(path);
        BufferedWriter out = new BufferedWriter(new FileWriter(file));

        out.write(content);
        out.close();
    }

    /*
    * Takes a path and returns the content in a string
    */
    public String readFileSync(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    /*
    * Takes a string which specifies the type of path we want
    * Returns a predetermined path
    */
    public String getPath(String name) {
        String out = null;
        if (name.equals("userData")) {
            out = appData;
        } else if (name.equals("rootData")) {
            out = userRoot;
        }

        return out;
    }

}