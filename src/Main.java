import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        IOManager ioManager = new IOManager(args);

        List<Guest> guests = new LinkedList<>();
        for (int i= 0; i< ioManager.getNumberOfGuests(); i++){
            guests.add(GuestFactory.createGuest(ioManager.getRandomOWNAttributes(), ioManager.getRandomSEARCHAttributes()));
        }

        Matcher.matchGuests(guests);

        for (String text:IOManager.getGuestStringArray(guests)) {
            System.out.print(text);
        }
    }
}