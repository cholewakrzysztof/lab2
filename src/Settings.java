public class Settings {
    private Integer maxOwnAttributes;
    private Integer maxSearchAttributes;
    private Integer numberOfGuests;
    private String fileSource;
    private AppMode appMode;
    private boolean delay;
    private boolean printScore;

    public Settings(MyInputParser myInputParser) throws WrongParametersException {
        if(myInputParser.cmd.hasOption("-f")){
            this.fileSource = myInputParser.cmd.getOptionValue("-f");
            this.appMode = AppMode.FILE;
        } else if (myInputParser.cmd.hasOption("-c")) {
            this.maxSearchAttributes = 4;
            this.maxOwnAttributes = 2;
            this.numberOfGuests = 20;
            this.appMode = AppMode.CONSOLE;
        }else{
            if(myInputParser.cmd.hasOption("-o")){
                this.maxOwnAttributes = Integer.parseInt(myInputParser.cmd.getOptionValue("-o"));
                if(this.maxOwnAttributes<=0)
                    throw new WrongParametersException("Number of max own attributes must be higher than 0");
            }else this.maxOwnAttributes = 2;

            if(myInputParser.cmd.hasOption("-s")){
                this.maxSearchAttributes = Integer.parseInt(myInputParser.cmd.getOptionValue("-s"));
                if(this.maxSearchAttributes<=0)
                    throw new WrongParametersException("Number of max search attributes must be higher than 0");
            }else this.maxSearchAttributes = 4;

            if(myInputParser.cmd.hasOption("-g")) {
                this.numberOfGuests = Integer.parseInt(myInputParser.cmd.getOptionValue("-g"));
                if (this.numberOfGuests <= 0)
                    throw new WrongParametersException("Number of guest must be higher than 0");
            }else this.numberOfGuests = 20;

            this.appMode = AppMode.PARAMETERS;
        }
        delay = myInputParser.cmd.hasOption("-d");
        printScore = myInputParser.cmd.hasOption("-sc");
    }

    public Integer getNumberOfGuests(){
        return numberOfGuests;
    }
    public Integer getMaxOwnAttributes(){return maxOwnAttributes;}
    public Integer getMaxSearchAttributes(){return maxSearchAttributes;}
    public String getFileSource() { return fileSource; }
    public AppMode getAppMode(){return appMode; }
    public boolean getDelay(){return delay;}
    public void setDelay(boolean delay){this.delay = delay;}
    public void setMaxOwnAttributes(Integer value){
        maxOwnAttributes = value;
    }
    public void setMaxSearchAttributes(Integer value){
        maxSearchAttributes = value;
    }
    public void setNumberOfGuests(Integer value){
        numberOfGuests = value;
    }

    public boolean PrintScore(){return printScore;}
    public void setPrintScore(boolean printScore){this.printScore = printScore;}
}
