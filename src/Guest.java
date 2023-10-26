import java.util.*;

public class Guest{
    Map<Attribute,Float> ownAttribute;
    Map<Attribute,Float> searchAttribute;
    Integer assignmentNumber;
    List<Guest> assignedGuests;
    List<Integer> assignedGuestsIDs;
    private Float B = 0.0f;
    private static Integer static_ID = 0;
    private Integer ID;
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
        String result = "";
        for (Attribute atr: ownAttribute.keySet()) {
            result+=atr.toString()+":"+ ownAttribute.get(atr).toString()+",";
        }
        result+= "/";
        for (Attribute atr: searchAttribute.keySet()) {
            result+=atr.toString()+":"+ searchAttribute.get(atr).toString()+",";
        }
        return result;
    }

    public Float countScore(Guest otherGuest){
        Float score = 0f;
        for (Attribute atr: this.ownAttribute.keySet()) {
            if (otherGuest.searchAttribute.keySet().contains(atr)){
                score+= ownAttribute.get(atr)*otherGuest.searchAttribute.get(atr);
            }
        }
        for (Attribute atr: this.searchAttribute.keySet()){
            if(otherGuest.ownAttribute.keySet().contains(atr)){
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
        String result = "\nFor guest number: "+this.ID+" = {";
        for (Guest g:assignedGuests) {
            result += g.ID;
            if(withScore)
                result+= " "+this.countScore(g);
            result+= ",";
        }
        result = result.substring(0,result.length()-1)+"}\n";
        return result;
    }

    public String printGuestStats(){
        String result = "Guest number: "+this.ID+"\n   1. Is: ";
        for(Attribute key: ownAttribute.keySet()){
            result += key.toString()+" "+ownAttribute.get(key)+",";
        }
        result += "\n   2. Search for: ";
        for(Attribute key: searchAttribute.keySet()){
            result += key.toString()+" "+searchAttribute.get(key)+",";
        }
        return result;
    }

    public Integer getID(){ return this.ID; }
}
