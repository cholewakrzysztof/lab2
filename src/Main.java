import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        IOManager ioManager = new IOManager(args);
        List<Guest> guests = new LinkedList<>();

        switch (ioManager.getAppMode()){
            case FILE -> {
                File file = new File(ioManager.getFileSource());
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()){
                    guests.add(GuestFactory.readGuest(scanner.next()));
                }

                Matcher.matchGuests(guests);

                for (String text:IOManager.getGuestStringArray(guests))
                    System.out.print(text);
            }
            case CONSOLE ->{
                Scanner scanner = new Scanner(System.in);
                boolean go = true;
                while (go){
                    System.out.println("=============================");
                    System.out.println("Chose option:");
                    System.out.println("Set data for app............s");
                    System.out.println("Launch app..................l");
                    System.out.println("Quit........................q");

                    switch (scanner.next()){
                        case "l" ->{
                            for (int i= 0; i< ioManager.getNumberOfGuests(); i++)
                                guests.add(GuestFactory.createGuest(ioManager.getRandomOWNAttributes(), ioManager.getRandomSEARCHAttributes()));

                            Matcher.matchGuests(guests);

                            for (String text:IOManager.getGuestStringArray(guests))
                                System.out.print(text);
                        }
                        case "s" -> {
                            System.out.println("Please chose number of max own guest attributes");
                            ioManager.setMaxOwnAttributes(scanner.nextInt());

                            System.out.println("Please chose number of max search guest attributes");
                            ioManager.setMaxSearchAttributes(scanner.nextInt());

                            System.out.println("Please chose number of guests");
                            ioManager.setNumberOfGuests(scanner.nextInt());

                            System.out.println("App options: ");
                            System.out.println("Max guest own attributes   : "+ioManager.getMaxOwnAttributes());
                            System.out.println("Max guest search attributes: "+ioManager.getMaxSearchAttributes());
                            System.out.println("Number of guests           : "+ioManager.getNumberOfGuests());

                        }
                        case "q" -> {System.out.println("Good bye!"); go = false;}
                        default -> System.out.println("Option doesn't exist");
                    }

                }

            }
            case PARAMETERS -> {
                for (int i= 0; i< ioManager.getNumberOfGuests(); i++)
                    guests.add(GuestFactory.createGuest(ioManager.getRandomOWNAttributes(), ioManager.getRandomSEARCHAttributes()));

                Matcher.matchGuests(guests);

                for (String text:IOManager.getGuestStringArray(guests))
                    System.out.print(text);
            }
            default -> throw new Exception("Unknown error");
        }


    }
}