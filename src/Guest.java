import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Guest {
    Map<Attribute,Float> ownAttribute;
    Map<Attribute,Float> searchAttribute;
    Integer assignmentNumber;
    List<Guest> assignedGuests;
    public Guest(Map<Attribute,Float> ownAttribute, Map<Attribute,Float> searchAttribute){
        this.ownAttribute = ownAttribute;
        this.searchAttribute = searchAttribute;
        assignedGuests = new LinkedList<>();
        assignmentNumber = 0;
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
}
