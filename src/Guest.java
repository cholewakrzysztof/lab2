import java.util.*;

public class Guest{
    Map<Attribute,Float> ownAttribute;
    Map<Attribute,Float> searchAttribute;
    Integer assignmentNumber;
    List<Guest> assignedGuests;
    List<Integer> assignedGuestsIDs;
    private static final float B = 0.0f;
    private static Integer static_ID = 0;
    private final Integer ID;
    public Guest(Map<Attribute,Float> ownAttribute, Map<Attribute,Float> searchAttribute){
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
        for (Attribute atr: ownAttribute.keySet()) {
            result.append(atr.toString()).append(":").append(ownAttribute.get(atr).toString()).append(",");
        }
        result.append("/");
        for (Attribute atr: searchAttribute.keySet()) {
            result.append(atr.toString()).append(":").append(searchAttribute.get(atr).toString()).append(",");
        }
        return result.toString();
    }

    public Float countScore(Guest otherGuest){
        float score = 0f;
        for (Attribute atr: this.ownAttribute.keySet()) {
            if (otherGuest.searchAttribute.containsKey(atr)){
                score+= ownAttribute.get(atr)*otherGuest.searchAttribute.get(atr);
            }
        }
        for (Attribute atr: this.searchAttribute.keySet()){
            if(otherGuest.ownAttribute.containsKey(atr)){
                score+= searchAttribute.get(atr)*otherGuest.ownAttribute.get(atr);
            }
        }
        score -= otherGuest.assignmentNumber*B;

        return score;
    }

    public void sortAssignedGuests(){
        for(int i=0; i< assignedGuests.size()-1; i++){
            for(int j=0; j< assignedGuests.size()-1; j++){
                Float g1Score = this.countScore(assignedGuests.get(j));
                Float g2Score = this.countScore(assignedGuests.get(j+1));
                if(g1Score>g2Score){
                    Guest tmp = new Guest(assignedGuests.get(j+1));
                    assignedGuests.set(j+1,new Guest(assignedGuests.get(j)));
                    assignedGuests.set(j,tmp);

                    Integer tmp2 = assignedGuestsIDs.get(j+1);
                    assignedGuestsIDs.set(j+1,assignedGuests.get(j).ID);
                    assignedGuestsIDs.set(j,tmp2);
                }
            }
        }
        if(assignedGuests.size()>5){
            assignedGuests.get(5).assignmentNumber--;
            assignedGuests = assignedGuests.subList(1,6);
            assignedGuestsIDs = assignedGuestsIDs.subList(1,6);
        }
    }

    public String printAssignedGuests(Boolean withScore){
        StringBuilder result = new StringBuilder("\nFor guest number: " + this.ID + " = {");
        for (Guest g:assignedGuests) {
            result.append(g.ID);
            if(withScore)
                result.append(" ").append(this.countScore(g));
            result.append(",");
        }
        result = new StringBuilder(result.substring(0, result.length() - 1) + "}\n");
        return result.toString();
    }

    public String printGuestStats(){
        StringBuilder result = new StringBuilder("Guest number: " + this.ID + "\n   1. Is: ");
        for(Attribute key: ownAttribute.keySet()){
            result.append(key.toString()).append(" ").append(ownAttribute.get(key)).append(",");
        }
        result.append("\n   2. Search for: ");
        for(Attribute key: searchAttribute.keySet()){
            result.append(key.toString()).append(" ").append(searchAttribute.get(key)).append(",");
        }
        return result.toString();
    }

    public Integer getID(){ return this.ID; }
}
