import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GuestFactory implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;
    public static Guest createGuest(int ownAttributeNumber, int searchAttributeNumber){
        Map<Attribute,Float> ownAttribute = new HashMap<>();
        Map<Attribute,Float> searchAttribute = new HashMap<>();

        Random random = new Random();
        for (int i =0; i<ownAttributeNumber; i++){
            ownAttribute.put(Attribute.randomAttribute(), random.nextFloat());
        }
        for (int i =0; i<searchAttributeNumber; i++){
            searchAttribute.put(Attribute.randomAttribute(), random.nextFloat());
        }

        return new Guest(ownAttribute,searchAttribute);
    }
    public static Guest readGuest(String source) throws Exception {
        Map<Attribute,Float> ownAttribute = new HashMap<>();
        Map<Attribute,Float> searchAttribute = new HashMap<>();

        String version = source.substring(source.indexOf("(")+1,source.indexOf(")"));
        if(serialVersionUID!=Integer.parseInt(version)){
            throw new Exception("Serialization error");
        }

        String ownAttributeString = source.substring (0,source.indexOf('/'));
        String searchAttributeString = source.substring(source.indexOf('/')+1);

        for(String attribute : ownAttributeString.split(",")){
            String[] atr = attribute.split(":");
            ownAttribute.put(Attribute.fromString(atr[0]),Float.parseFloat(atr[1]));
        }
        for(String attribute : searchAttributeString.split(",")){
            String[] atr = attribute.split(":");
            searchAttribute.put(Attribute.fromString(atr[0]),Float.parseFloat(atr[1]));
        }

        return new Guest(ownAttribute,searchAttribute);
    }
}
