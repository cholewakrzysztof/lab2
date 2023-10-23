import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Attribute {
    DEVELOPER, INVESTOR, SALES, MARKETING, PROJECT_MANAGER, ARCHITECT, INTERN, UNDECIDED;

    private static final List<Attribute> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    public static Attribute randomAttribute() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public static Attribute fromString(String source){
        switch (source){
            case "DEVELOPER": return DEVELOPER;
            case "INVESTOR": return INVESTOR;
            case "SALES": return SALES;
            case "MARKETING": return MARKETING;
            case "PROJECT_MANAGER": return PROJECT_MANAGER;
            case "ARCHITECT": return ARCHITECT;
            case "INTERN" : return INTERN;
            default : return UNDECIDED;
        }
    }
}
