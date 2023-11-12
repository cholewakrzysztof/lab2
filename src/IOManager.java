import org.apache.commons.cli.CommandLine;

import java.util.*;

public class IOManager {
    private final Random random = new Random();
    public Settings settings;

    public IOManager(String[] input) throws Exception {
        MyInputParser myInputParser = new MyInputParser(input);

        this.settings = new Settings(myInputParser);
    }

    public static String[] getGuestStringArray(List<Guest> guests){
        String[] result = new String[guests.size()];
        for (int i=0; i<guests.size();i++){
            result[i] = guests.get(i).printGuestStats()+guests.get(i).printAssignedGuests(true) ;
        }
        return result;
    }
    public Integer getRandomOWNAttributes(){
        return random.nextInt(1,settings.getMaxOwnAttributes());
    }
    public Integer getRandomSEARCHAttributes(){
        return random.nextInt(1,settings.getMaxSearchAttributes());
    }
    public Settings getSettings(){ return settings;}
}
