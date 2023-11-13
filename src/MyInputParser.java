import org.apache.commons.cli.*;
public class MyInputParser {
    public CommandLine cmd;
    public MyInputParser(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("f","FileMode",true,"Open in file mode");
        options.addOption("c","ConsoleMode",false,"Open in console mode");
        options.addOption("s","SearchSet",true,"Set max number of search attributes");
        options.addOption("o","OwnSet",true,"Set max number of own attributes");
        options.addOption("g","GuestNumber",true,"Set number of guests");
        options.addOption("d","DelayOn",true,"Set delay ON");
        options.addOption("sc","Score",false,"Set showing score ON");

        CommandLineParser parser = new DefaultParser();
        cmd = parser.parse(options, args);
    }
}
