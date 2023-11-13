import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Guest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;
    private final Map<String,Float> ownAttribute;
    private final Map<String,Float> searchAttribute;
    List<Guest> assignedGuests;
    private final PriorityQueue<Guest> guestPriorityQueue;
    Float tmpScore = 0f;
    private static Integer static_ID = 0;
    private final Integer ID;
    public Guest(Map<String,Float> ownAttribute, Map<String,Float> searchAttribute){
        this.ownAttribute = ownAttribute;
        this.searchAttribute = searchAttribute;
        assignedGuests = new LinkedList<>();
        this.guestPriorityQueue = new PriorityQueue<>(6,(guest1, guest2) -> Float.compare(guest2.getTmpScore(), guest1.getTmpScore()));
        this.ID = static_ID;
        static_ID++;
    }
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("("+serialVersionUID+")");
        for (String atr: ownAttribute.keySet()) {
            result.append(atr).append(":").append(ownAttribute.get(atr).toString()).append(",");
        }
        result.append("/");
        for (String atr: searchAttribute.keySet()) {
            result.append(atr).append(":").append(searchAttribute.get(atr).toString()).append(",");
        }
        return result.toString();
    }

    public Float countScore(Guest otherGuest){
        float score = 0f;
        for (String atr: this.ownAttribute.keySet()) {
            if (otherGuest.searchAttribute.containsKey(atr)){
                score+= ownAttribute.get(atr)*otherGuest.searchAttribute.get(atr);
            }
        }
        for (String atr: this.searchAttribute.keySet()){
            if(otherGuest.ownAttribute.containsKey(atr)){
                score+= searchAttribute.get(atr)*otherGuest.ownAttribute.get(atr);
            }
        }
        otherGuest.tmpScore = score;
        return score;
    }

    public String printAssignedGuests(Boolean withScore){
        StringBuilder result = new StringBuilder("For guest number: " + this.ID + " = {");
        for(Guest g : assignedGuests){
            result.append(g.ID);
            if(withScore)
                result.append(" ").append(this.countScore(g));
            result.append(",");
        }

        result = new StringBuilder(result.substring(0, result.length() - 1) + "}");
        return result.toString();
    }

    public String printGuestStats(){
        StringBuilder result = new StringBuilder("Guest number: " + this.ID + "\n   1. Is: ");
        for(String key: ownAttribute.keySet()){
            result.append(key).append(" ").append(ownAttribute.get(key)).append(",");
        }
        result.append("\n   2. Search for: ");
        for(String key: searchAttribute.keySet()){
            result.append(key).append(" ").append(searchAttribute.get(key)).append(",");
        }
        return result+"\n";
    }
    public static void resetID(){static_ID = 0;}
    public Float getTmpScore(){return tmpScore;}
    public PriorityQueue getQueue(){return guestPriorityQueue;}
}
