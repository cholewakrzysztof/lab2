import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class IOManager {
    private Integer maxOwnAttributes;
    private Integer maxSearchAttributes;
    private Integer numberOfGuests;
    private String fileSource;
    private AppMode appMode;
    private final Random random = new Random();

    public IOManager(String[] input) throws Exception {

        List<String> args = new LinkedList<>(Arrays.stream(input).toList());

        if(args.contains("-f")){
            if(input.length>2){
                throw new WrongParametersException("If you open app in file mode add just source of file");
            }
            this.fileSource = args.get(args.indexOf("-f")+1);
            this.appMode = AppMode.FILE;
        } else if (args.contains("-c")) {
            if(input.length%2>1){
                throw new WrongParametersException("If you open app in console mode don't add more parameters");
            }
            this.maxSearchAttributes = 4;
            this.maxOwnAttributes = 2;
            this.numberOfGuests = 20;
            this.appMode = AppMode.CONSOLE;
        }else{
            if(input.length%2!=0){
                throw new WrongParametersException("Number of parameters is incorrect");
            }
            if(args.contains("-o")){
                this.maxOwnAttributes = Integer.parseInt(args.get(args.indexOf("-o")+1));
                if(this.maxOwnAttributes<=0)
                    throw new WrongParametersException("Number of max own attributes must be higher than 0");
            }else this.maxOwnAttributes = 2;

            if(args.contains("-s")){
                this.maxSearchAttributes = Integer.parseInt(args.get(args.indexOf("-s")+1));
                if(this.maxSearchAttributes<=0)
                    throw new WrongParametersException("Number of max search attributes must be higher than 0");
            }else this.maxSearchAttributes = 4;

            if(args.contains("-g")) {
                this.numberOfGuests = Integer.parseInt(args.get(args.indexOf("-g") + 1));
                if (this.numberOfGuests <= 0)
                    throw new WrongParametersException("Number of guest must be higher than 0");
            }else this.numberOfGuests = 20;

            this.appMode = AppMode.PARAMETERS;
        }

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

    public void setMaxOwnAttributes(Integer value){
        maxOwnAttributes = value;
    }
    public void setMaxSearchAttributes(Integer value){
        maxSearchAttributes = value;
    }

    public void setNumberOfGuests(Integer value){
        numberOfGuests = value;
    }
    public Integer getMaxOwnAttributes(){return maxOwnAttributes;}
    public Integer getMaxSearchAttributes(){return maxSearchAttributes;}
    public String getFileSource() { return fileSource; }

    public AppMode getAppMode(){return appMode; }
}
