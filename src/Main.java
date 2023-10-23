import java.util.*;

public class Main {
    public static void main(String[] args) {


        List<Guest> guests = new LinkedList<>();
        Random random = new Random();

        for (int i= 0; i<40; i++){
            guests.add(GuestFactory.createGuest(random.nextInt(1,3),random.nextInt(1,5)));
        }

        String tmp = "";
        for (Guest guest: guests) {
            System.out.println(guest.toString());
            tmp+= guest.toString()+"\n";
        }
        System.out.println("Zmiana na string");
        for (String source : tmp.split("\n")) {
            Guest g = GuestFactory.readGuest(source);
            System.out.println(g.toString());
        }

        System.out.println(guests.get(0).countScore(guests.get(1)));
        System.out.println(guests.get(1).countScore(guests.get(0)));

        for (Guest guest : guests) {
            for (Guest possibleMatch : guests) {
                if (Objects.equals(guest.ID, possibleMatch.ID))
                    continue;
                if (guest.assignedGuestsIDs.contains(possibleMatch.ID))
                    continue;
                possibleMatch.assignmentNumber++;
                guest.assignedGuests.add(possibleMatch);
                guest.assignedGuestsIDs.add(possibleMatch.ID);
                guest.sortAssignedGuests();
            }
        }

        for (Guest guest:guests) {
            System.out.println(guest.printAssignedGuests());
        }
    }
}