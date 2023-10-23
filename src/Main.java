import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {


        List<Guest> guests = new LinkedList<>();
        Random random = new Random();

        for (int i= 0; i<2; i++){
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

    }
}