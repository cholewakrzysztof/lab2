import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Guest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;
    Map<String,Float> ownAttribute;
    Map<String,Float> searchAttribute;
    Integer assignmentNumber;
    List<Guest> assignedGuests;
    List<Integer> assignedGuestsIDs;
    private static final float B = 0.0f;
    private static Integer static_ID = 0;
    private final Integer ID;
    public Guest(Map<String,Float> ownAttribute, Map<String,Float> searchAttribute){
        this.ownAttribute = ownAttribute;
        this.searchAttribute = searchAttribute;
        assignedGuests = new LinkedList<>();
        assignedGuestsIDs = new LinkedList<>();
        assignmentNumber = 0;
        this.ID = static_ID;
        static_ID++;
    }
    public Guest(Guest g){
        this.ownAttribute = g.ownAttribute;
        this.searchAttribute = g.searchAttribute;
        assignedGuests = g.assignedGuests;
        assignmentNumber = g.assignmentNumber;
        assignedGuestsIDs = g.assignedGuestsIDs;
        this.ID = g.ID;
    }
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("("+serialVersionUID+")");
        for (String atr: ownAttribute.keySet()) {
            result.append(atr.toString()).append(":").append(ownAttribute.get(atr).toString()).append(",");
        }
        result.append("/");
        for (String atr: searchAttribute.keySet()) {
            result.append(atr.toString()).append(":").append(searchAttribute.get(atr).toString()).append(",");
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
        score -= otherGuest.assignmentNumber*B;

        return score;
    }

    public void sortAssignedGuests(boolean delay) throws InterruptedException {
        for(int i=0; i< assignedGuests.size()-1; i++){
            for(int j=0; j< assignedGuests.size()-i-1; j++){
                Float g1Score = this.countScore(assignedGuests.get(j));
                Float g2Score = this.countScore(assignedGuests.get(j+1));
                if(g1Score>g2Score){
                    if(delay){
                        Thread.sleep(200);
                        System.out.println(this.printAssignedGuests(true));
                    }
                    swapGuests(j);
                }
            }
        }
        updateAssigment();
    }
    private void swapGuests(int j){
        Guest tmp = new Guest(assignedGuests.get(j+1));
        assignedGuests.set(j+1,new Guest(assignedGuests.get(j)));
        assignedGuests.set(j,tmp);

        Integer tmp2 = assignedGuestsIDs.get(j+1);
        assignedGuestsIDs.set(j+1,assignedGuests.get(j).ID);
        assignedGuestsIDs.set(j,tmp2);
    }
    private void updateAssigment(){
        if(assignedGuests.size()>5){
            assignedGuests.get(5).assignmentNumber--;
            assignedGuests = assignedGuests.subList(1,6);
            assignedGuestsIDs = assignedGuestsIDs.subList(1,6);
        }
    }

    public String printAssignedGuests(Boolean withScore){
        StringBuilder result = new StringBuilder("For guest number: " + this.ID + " = {");
        for (Guest g:assignedGuests) {
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
            result.append(key.toString()).append(" ").append(ownAttribute.get(key)).append(",");
        }
        result.append("\n   2. Search for: ");
        for(String key: searchAttribute.keySet()){
            result.append(key.toString()).append(" ").append(searchAttribute.get(key)).append(",");
        }
        return result+"\n";
    }

    public Integer getID(){ return this.ID; }
    public static void resetID(){static_ID = 0;}
}
