import java.util.ArrayList;
import java.util.Collections;

/**
 * Write a description of class Challenge1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Challenge1
{
    private static ArrayList<String> botMemoryUsages;

    public static void main(String[] args) {
        sortBotUsages();
        System.out.println(generateAnswerAsString(5));
    }

    /**
     * main calculations goes here
     */
    private static void sortBotUsages() {
        int currentBotUsage = 0;
        botMemoryUsages = new ArrayList<>();
        ArrayList<String> data = FileReader.readFile("challenge_1.txt");
        //read numbers from lines
        for(String line: data) {
            if(line.isBlank()) {
                botMemoryUsages.add(Integer.toString(currentBotUsage));
                currentBotUsage = 0;
            }
            else {
                currentBotUsage += Integer.parseInt(line);
            }
        }

        Collections.sort(botMemoryUsages);
        Collections.reverse(botMemoryUsages);
    }

    /**
     *
     * @param topNumber the top n amount of CPU Usages wanted
     * @return answer as a string
     */
    private static String generateAnswerAsString(int topNumber) {
        String answer = "";
        for(int i = 0; i < topNumber; i++) {
            answer += botMemoryUsages.get(i);
            if( i != topNumber - 1){
                answer += ", "; //ensures that the will not be a final comma at the end
            }
        }

        return answer;
    }
}