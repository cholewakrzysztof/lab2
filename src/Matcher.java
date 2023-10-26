import java.util.List;
import java.util.Objects;

public class Matcher {
    public static List<Guest> matchGuests(List<Guest> guests){
        for (Guest guest : guests) {
            for (Guest possibleMatch : guests) {
                if (Objects.equals(guest.getID(), possibleMatch.getID()))
                    continue;
                if (guest.assignedGuestsIDs.contains(possibleMatch.getID()))
                    continue;
                possibleMatch.assignmentNumber++;
                guest.assignedGuests.add(possibleMatch);
                guest.assignedGuestsIDs.add(possibleMatch.getID());
                guest.sortAssignedGuests();
            }
        }
        return guests;
    }
}
