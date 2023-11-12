import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        IOManager ioManager = new IOManager(args);
        List<Guest> guests = new LinkedList<>();

        switch (ioManager.getSettings().getAppMode()){
            case FILE -> {
                File file = new File(ioManager.getSettings().getFileSource());
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()){
                    guests.add(GuestFactory.readGuest(scanner.next()));
                }

                Matcher.matchGuests(guests,ioManager.getSettings().getDelay());

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
                    System.out.println("On/Off delay................d");
                    System.out.println("Quit........................q");

                    switch (scanner.next()){
                        case "l" ->{
                            guests.clear();
                            Guest.resetID();
                            for (int i= 0; i< ioManager.getSettings().getNumberOfGuests(); i++)
                                guests.add(GuestFactory.createGuest(ioManager.getRandomOWNAttributes(), ioManager.getRandomSEARCHAttributes()));

                            Matcher.matchGuests(guests,ioManager.getSettings().getDelay());

                            for (String text:IOManager.getGuestStringArray(guests))
                                System.out.print(text+"\n");
                        }
                        case "d" -> {
                            System.out.println("Type 1 for set delay ON");
                            System.out.println("Type 0 for set delay OFF");
                            ioManager.getSettings().setDelay(scanner.nextInt() > 0);
                        }
                        case "s" -> {
                            System.out.println("Please chose number of max own guest attributes");
                            ioManager.getSettings().setMaxOwnAttributes(scanner.nextInt());

                            System.out.println("Please chose number of max search guest attributes");
                            ioManager.getSettings().setMaxSearchAttributes(scanner.nextInt());

                            System.out.println("Please chose number of guests");
                            ioManager.getSettings().setNumberOfGuests(scanner.nextInt());

                            System.out.println("App options: ");
                            System.out.println("Max guest own attributes   : "+ioManager.getSettings().getMaxOwnAttributes());
                            System.out.println("Max guest search attributes: "+ioManager.getSettings().getMaxSearchAttributes());
                            System.out.println("Number of guests           : "+ioManager.getSettings().getNumberOfGuests());

                        }
                        case "q" -> {System.out.println("Good bye!"); go = false;}
                        default -> System.out.println("Option doesn't exist");
                    }

                }

            }
            case PARAMETERS -> {
                for (int i= 0; i< ioManager.getSettings().getNumberOfGuests(); i++)
                    guests.add(GuestFactory.createGuest(ioManager.getRandomOWNAttributes(), ioManager.getRandomSEARCHAttributes()));

                Matcher.matchGuests(guests, ioManager.getSettings().getDelay());

                for (String text:IOManager.getGuestStringArray(guests))
                    System.out.print(text+"\n");
            }
            default -> throw new Exception("Unknown error");
        }


    }
}