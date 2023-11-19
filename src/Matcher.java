import java.util.List;

public class Matcher {
    public static void matchGuests(List<Guest> guests,Settings settings) throws InterruptedException {
        boolean delay = settings.getDelay();
        boolean withScore = settings.PrintScore();
        for(Guest guest : guests){
            guests.stream()
                    .filter(g->g.hashCode()!=guest.hashCode())
                    .forEach(possibleMatch->{
                        guest.countScore(possibleMatch);
                        guest.getQueue().add(possibleMatch);
                        if(guest.getQueue().size()>5)
                            guest.getQueue().poll();
                    });
            if(delay){
                Thread.sleep(1000);
                System.out.println();
                System.out.println(guest.printAssignedGuests(withScore));
            }
            while (!guest.getQueue().isEmpty())
                guest.assignedGuests.add((Guest)guest.getQueue().poll());
        }
    }
}
