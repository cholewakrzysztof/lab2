import java.util.ArrayList;
import java.util.Random;

public class Attribute {
    private static final ArrayList<String> attributes = new ArrayList<>();
    private static final Random RANDOM = new Random();
    public static String randomAttribute() {
        return attributes.get(RANDOM.nextInt(attributes.size()));
    }

    public static String fromString(String source){
        if(!attributes.contains(source))
            attributes.add(source);
        return source;
    }
}
