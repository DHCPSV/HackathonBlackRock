import java.util.ArrayList;
import java.util.List;

public class Bot {
    public static final long SUPERMODULO = 720; //Product of all divisors
    private String name;
    private List<Long> securities;
    private int divisor;
    private String[] partnerBotsAsString;
    private Bot[] partnerBots; //store the references to the two bots that this bot can trade to.
    private long numberOfInspections;

    /**
     * Create a bot
     */
    public Bot(){
        partnerBots = new Bot[2];
        partnerBotsAsString = new String[2];
        securities = new ArrayList<>();
        numberOfInspections = 0;
    }

    /**
     * Just for testing purposes
     */
    public String getBotDetails() {
        String str = "Name = " + name + "\n";
        str += "Security values = ";
        for(long number: securities) {
            str += " " + number ;
        }
        str += "\n Divisor = " + divisor;
        str += "\n Partners = " + partnerBots[0].getName() + " " + partnerBots[1].getName();
        return str;
    }

    /**
     * Define the name of the current bot
     * @param name the name of the bot
     */
    public void setName(String name) { this.name = name; }

    /**
     * @return this returns the name of the current bot
     */
    public String getName() { return name; }

    public void setDivisor(int divisor) {
        this.divisor = divisor;
    }

    /**
     * Define which two bot the current bot are trading with
     * @param partnerA This is the bot that our current bot trades to if the divisibility requirement is met.
     * @param partnerB This is the bot that our current bot trades to if the divisibility requirement is not met.
     */
    public void setTradePartners(Bot partnerA, Bot partnerB){
        partnerBots[0] = partnerA;
        partnerBots[1] = partnerB;
    }

    /**
     * Defines the names of which two bots the current bot are trading with
     * @param partnerA This is the bot that our current bot trades to if the divisibility requirement is met.
     * @param partnerB This is the bot that our current bot trades to if the divisibility requirement is not met.
     */
    public void setTradePartners(String partnerA, String partnerB) {
        partnerBotsAsString[0] = partnerA;
        partnerBotsAsString[1] = partnerB;
    }

    /**
     * @return this returns the name of the current bots trading partners
     */
    public String[] getTradePartners(){ return partnerBotsAsString;}

    /**
     * @param security the security to be added to the list of securities
     */
    public void addSecurity(Long security){
        securities.add(security);
    }

    /**
     * @param security the security to be removed from the list of securities.
     */
    public void removeSecurity(Long security){
        securities.remove(security);
    }
    /**
     * @return this returns the list of the securities of the current bot.
     */
    public List<Long> getSecurities(){
        return securities;
    }

    /**
     * @return The divisibility rule that this bot follows.
     */
    public int getDivisor(){
        return divisor;
    }

    /**
     * This will go through all the securities of the current bot
     * if it is divisible by the divisor of this bot, ill will be added to the securities list of the first partner bot
     * if it is not divisible by the divisor, it will be added to the securities list of the second bot.
     * the securities list of this bot will be empty.
     */
    public void tradeSecurities(double cost){
        securities.stream().filter(x -> Math.ceil(x*x * cost) % divisor == 0)
                  .forEach(x -> partnerBots[0].addSecurity((long) Math.ceil((x*x * cost) % SUPERMODULO)));
        securities.stream().filter(x -> Math.ceil(x*x * cost) % divisor != 0)
                  .forEach(x -> partnerBots[1].addSecurity((long) Math.ceil((x*x * cost) % SUPERMODULO)));

        updateNumberOfInspection(securities.size());
        securities.clear();
    }

    private void updateNumberOfInspection(long increment) {
        numberOfInspections += increment;
    }

    public long getNumberOfInspections(){
        return numberOfInspections;
    }
}
