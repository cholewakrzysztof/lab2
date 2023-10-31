# lab2
Repository for second laboratory project
# APPLICATION LAUNCH

Main [-f file_path]/[-c]/[-o int] [-s int] [-g int] \n
Options:\n
  For file mode
  -f file mode with file source path
  For console mode
  -c console mode (no more parameters)
  For parameters mode
  -o max number of Guest own attributes
  -s max number of Guest search attributes
  -o number of guests

Application has 3 mode of running
FILE MODE:
  Read list of guest from file and match them automatically, as a result you get preformatted list of final matches with score of each guest in top 5 matches
  You can use file from repository as source "plik.txt" with sample 5 guests
  Sample run: Main -f D:\JAVA\lab2\src\plik.txt
CONSOLE MODE:
  Run application console menu where you can pick;
    - Launch matching (if you didn't set options there will be default {max Guest own attributes: 2, max Guest search attributes: 4, number of guests: 20})
    - Set options where you can chose each of 3 parameters for simulation
    - Quit infinite loop 
  Sample run: Main -c
PARAMETERS MODE:
  Read arguments or set default as {max Guest own attributes: 2, max Guest search attributes: 4, number of guests: 20}, as a result you get preformatted list of final matches with score of each guest in top 5 matches 
  Sample run: Main -o 3 -s 5 -g 5
