Assignment Three: Binary Search Trees and Serialization. (Group Six)
Program: WordTracker
About:
 WordTracker is a program that processes a text files and stores every word, its line number, and what file it came from inside a binary search tree.
 WordTracker presents this data to the user in the form of a report, the type of which is specified by the user.
 The user can choose to have this report stored in a file of their choosing, rather than printed in the command line shell.

Steps to Run the Program:

1. Download the WordTracker Program, and the required files, and save to a file directory of your choosing.

2. Open a command-line shell.

3. To run the program, navigate to the directory where the JAR file is located or use the absolute or relative
   path to the JAR file when running the commands outlined below.

4. Begin the command with java -jar, followed by the path to the JAR file.

5. After the JAR file path, specify the text file you would like to process, then the report you would like to generate with -pf, -pl, or -po.
   optionally you can specify the filepath to an output file using the -f flag. This allows you to save the generated report.

        * The '-pf' flag is used to print in alphabetic order all words along with the corresponding list of files in which
        the words occur.

        * The '-pl' flag is used to print in alphabetic order all words along with the corresponding list of files and
        numbers of the lines in which the word occurs.

        * The '-po' flag is used to print in alphabetic order all words along with the corresponding list of files and
        numbers of the lines in which the word occurs.

        * The `-f` flag is used to specify the file path of where you would like the output to go.
        The file path can be provided with or without quotes. You can either place the file in the same
        directory as the JAR file and use the file name followed by `.txt`, or use the absolute or relative path
        to the text file. If the file you specified does not yet exist, the program will create it for you,
        just make sure you have permission to write to that directory.

Example Inputs:

java -jar c:/users/janel/wordtracker.jar c:/users/janel/documents/text.txt -pf
java -jar c:/wordtracker.jar c:/users/janel/documents/text.txt -po -f report.txt
java -jar c:/wordtracker.jar c:/users/janel/documents/text.txt -po -freport.txt

```
java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f <output.txt>]
```
 


        