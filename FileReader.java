import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

/**
 * Write a description of class FileReader here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FileReader
{
    public static ArrayList<String> readFile(String fileToRead) {
        ArrayList<String> fileData = new ArrayList<>();
        try{
            File file = new File(fileToRead);
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNextLine()) {
                fileData.add(fileReader.nextLine());
            }
            fileReader.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        return fileData;
    }
}
