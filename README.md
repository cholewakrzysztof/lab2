# lab2
Repository for second laboratory project
# APPLICATION LAUNCH

Main [-f file_path]/[-c]/[-o int] [-s int] [-g int]  
Options:  
  For file mode  
  &emsp;-f file mode with file source path  
  For console mode  
  &emsp;-c console mode (no more parameters)  
  For parameters mode  
  &emsp;-o max number of Guest own attributes  
  &emsp;-s max number of Guest search attributes  
  &emsp;-o number of guests  

# Application has 3 mode of running  
FILE MODE:  
  &emsp;Read list of guest from file and match them automatically, as a result you get preformatted list of final matches with score of each guest in top 5 matches
  &emsp;You can use file from repository as source "plik.txt" with sample 5 guests  
  &emsp;Sample run: Main -f D:\JAVA\lab2\src\plik.txt  
CONSOLE MODE:  
  &emsp;Run application console menu where you can pick;  
    &emsp;- Launch matching (if you didn't set options there will be default {max Guest own attributes: 2, max Guest search attributes: 4, number of guests: 20})  
    &emsp;- Set options where you can chose each of 3 parameters for simulation  
   &emsp; - Quit infinite loop  
 &emsp; Sample run: Main -c  
PARAMETERS MODE:  
 &emsp; Read arguments or set default as {max Guest own attributes: 2, max Guest search attributes: 4, number of guests: 20}, as a result you get preformatted list of final matches with score of each guest in top 5 matches  
 &emsp; Sample run: Main -o 3 -s 5 -g 5  
