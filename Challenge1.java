import java.util.ArrayList;

/**
 * Write a description of class Challenge1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Challenge1
{
    public static void main(String[] args) {
        ArrayList<String> data = FileReader.readFile("challenge_1.txt");
        System.out.println(data);
    }
}