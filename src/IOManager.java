import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class IOManager {
    private Integer maxOwnAttributes;
    private Integer maxSearchAttributes;
    private Integer numberOfGuests;
    private Random random = new Random();

    public IOManager(String[] input) throws Exception {
        if(input.length%2!=0){
            WrongParametersException e = new WrongParametersException("Number of parameters is incorrect");
            throw e;
        }
        List args = new LinkedList<String>(Arrays.stream(input).toList());
        this.maxOwnAttributes = Integer.parseInt(args.get(args.indexOf("-o")+1).toString());
        if(this.maxOwnAttributes<=0)
            throw new WrongParametersException("Number of max own attributes must be higher than 0");
        this.maxSearchAttributes = Integer.parseInt(args.get(args.indexOf("-s")+1).toString());
        if(this.maxSearchAttributes<=0)
            throw new WrongParametersException("Number of max search attributes must be higher than 0");
        this.numberOfGuests = Integer.parseInt(args.get(args.indexOf("-g")+1).toString());
        if(this.numberOfGuests<=0)
            throw new WrongParametersException("Number of guest must be higher than 0");
    }
    public static String[] getGuestStringArray(List<Guest> guests){
        String[] result = new String[guests.size()];
        for (int i=0; i<guests.size();i++){
            result[i] = guests.get(i).printGuestStats()+guests.get(i).printAssignedGuests(true) ;
        }
        return result;
    }
    public Integer getNumberOfGuests(){
        return numberOfGuests;
    }
    public Integer getRandomOWNAttributes(){
        return random.nextInt(1,maxOwnAttributes);
    }
    public Integer getRandomSEARCHAttributes(){
        return random.nextInt(1,maxSearchAttributes);
    }
}
