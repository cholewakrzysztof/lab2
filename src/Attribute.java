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
        return switch (source) {
            case "DEVELOPER" -> DEVELOPER;
            case "INVESTOR" -> INVESTOR;
            case "SALES" -> SALES;
            case "MARKETING" -> MARKETING;
            case "PROJECT_MANAGER" -> PROJECT_MANAGER;
            case "ARCHITECT" -> ARCHITECT;
            case "INTERN" -> INTERN;
            default -> UNDECIDED;
        };
    }
}
