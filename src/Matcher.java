import java.util.List;
import java.util.Objects;

public class Matcher {
    public static void matchGuests(List<Guest> guests,boolean delay) throws InterruptedException {
        for (Guest guest : guests) {
            for (Guest possibleMatch : guests) {
                if (Objects.equals(guest.getID(), possibleMatch.getID()))
                    continue;
                if (guest.assignedGuestsIDs.contains(possibleMatch.getID()))
                    continue;
                possibleMatch.assignmentNumber++;
                guest.assignedGuests.add(possibleMatch);
                guest.assignedGuestsIDs.add(possibleMatch.getID());
                guest.sortAssignedGuests(delay);
                if(delay){
                    Thread.sleep(1000);
                    System.out.println();
                    System.out.println(guest.printAssignedGuests(true));
                }
            }
        }
    }
}
