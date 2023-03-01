import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Challenge2And3 {
    private  ArrayList<Bot> bots;
    private  HashMap<String,Bot> botMap;

    public Challenge2And3()
    {
    }

    public static void main(String[] args) {
        Challenge2And3 task = new Challenge2And3();
        task.operateChallenge2();
        System.out.println("\n");
        task.operateChallenge3();
    }

    private void operateChallenge2() {
        setBots();
        simulateTrading(2,0.2);
        sortBots();
    }

    private void operateChallenge3() {
        setBots();
        simulateTrading(5000,1);
        sortBots();
    }

    /**
     * Initialisation of all trading bots
     */
    private void setBots() {
        bots = new ArrayList<>();
        botMap = new HashMap<>();
        Bot template = null;
        ArrayList<String> data = FileReader.readFile("challenge_2_and_3.txt");
        String partnerAString = "";
        for(String line: data) {
            String[] words = line.trim().split(" ");
            if(line.isEmpty()) { //Add bot template to collection of bots
                bots.add(template);
                partnerAString = "";
            }
            else if(line.startsWith("Bot")) { //Make a new bot template, and add its name
                template = new Bot();
                template.setName(line.substring(4,line.length()-1)); //removes the colon
            }
            else if(line.startsWith("initial securities")) {
                String[] numbers = line.split(":")[1].split(",");
                for(String number: numbers){
                    template.addSecurity(Long.parseLong(number.trim()));
                }
            }
            else if(line.startsWith("Trading rule")) {
                String divisor = words[8].split(",")[0];
                template.setDivisor(Integer.parseInt(divisor.trim()));
                partnerAString += words[12].substring(0,words[12].length() - 1).trim();
            }
            else{ //i.e. begins with otherwise
                String partnerBString = words[4].substring(0,words[4].length()-1).trim();

                template.setTradePartners(partnerAString, partnerBString);
            }
        }
        bots.add(template); //adds the final bot
        for(Bot bot: bots){
            botMap.put(bot.getName(), bot);
        }

        for(Bot bot: bots) {
            Bot partnerA = botMap.get(bot.getTradePartners()[0]);
            Bot partnerB = botMap.get(bot.getTradePartners()[1]);
            bot.setTradePartners(partnerA, partnerB);
        }
    }

    private long getSupermodulo() {
        long supermodulo = 1;
        for(Bot bot: bots) {
            supermodulo *= bot.getDivisor();
        }
        return supermodulo;
    }

    private void simulateTrading(int timePeriod, double cost)
    {
        for(int i = 0; i < timePeriod; i++){
            for(Bot bot: bots){
                bot.tradeSecurities(cost, getSupermodulo());
            }
        }
    }

    private void sortBots(){
         bots.stream().sorted(Comparator.comparingLong(Bot::getNumberOfInspections).reversed())
                      .forEach(x -> System.out.print(x.getName() + " " + x.getNumberOfInspections() + "  "));
    }
}


