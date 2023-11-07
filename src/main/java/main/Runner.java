package main;

import main.utils.BSTreeLogging;
import main.utils.WordTracker;

import java.io.File;
import java.io.IOException;

/**
 * The Runner class is the main class of the program.
 */
public class Runner {

    /**
     * The main runner method of the program.
     *
     * @param args the arguments passed in from the command line
     *             Proper arguments: <input.txt> -pf/-pl/-po [-f <output.txt>]
     */
    public static void main(String[] args) {

        //build arguments
        String arguments = getArguments(args);

        if (args.length == 0) {
            System.out.println("No arguments were given.");
            System.out.println("Proper arguments: <input.txt> -pf/-pl/-po [-f <output.txt>]");
            return;
        }

        if (args.length < 2) {
            System.out.println("Invalid argument count, please follow the proper format: <input.txt> -pf/-pl/-po [-f <output.txt>]");
            return;
        }

        String inputFile = args[0];
        if (!new File(inputFile).exists()) {
            System.out.println("The input file does not exist.");
            System.out.println("Proper arguments: <input.txt> -pf/-pl/-po [-f <output.txt>]");
            return;
        }

        String argument = getValueForFlagLabel("p", arguments);
        if (argument == null) {
            System.out.println("Proper arguments: <input.txt> -pf/-pl/-po [-f <output.txt>]");
            return;
        }

        // get the output file if it exists
        String outputFile = args.length == 4 && args[2].equalsIgnoreCase("-f") ? args[3] : null;
        if (outputFile == null && arguments.contains("-f")) {
            String outputFileFlag = getValueForFlagLabel("f", arguments);
            if (outputFileFlag == null) {
                System.out.println("The -f flag is present, but no file name was given.");
                System.out.println("Proper arguments: <input.txt> -pf/-pl/-po [-f <output.txt>]");
                return;
            }
            outputFile = outputFileFlag;
        }

        if (args.length == 4 && !arguments.contains("-f")) {
            System.out.println("Output file arguments formatted incorrectly.");
            System.out.println("Proper arguments: <input.txt> -pf/-pl/-po [-f <output.txt>]");
            return;
        }


        switch (argument) {
            case "f":
                System.out.println("Printing words with files...");
                printResults(argument, inputFile, outputFile);
                break;
            case "l":
                System.out.println("Printing words with lines...");
                printResults(argument, inputFile, outputFile);
                break;
            case "o":
                System.out.println("Printing words with line numbers...");
                printResults(argument, inputFile, outputFile);
                break;
            default:
                System.out.println("Invalid argument for the -p flag: " + argument);
                System.out.println("Proper arguments: <input.txt> -pf/-pl/-po [-f <output.txt>]");
                break;
        }

    }

    /**
     * Prints the results of the tree to the console or to a file.
     *
     * @param argument   the argument passed in from the command line
     * @param inputFile  the input file to read from
     * @param outputFile the output file to write to
     */
    private static void printResults(String argument, String inputFile, String outputFile) {
        System.out.println("Processing file: " + inputFile);

        long startTime = System.currentTimeMillis();
        WordTracker wordTracker = new WordTracker();
        try {
            wordTracker.processFile(inputFile);
            wordTracker.saveTreeToFile();
        } catch (IOException e) {
            System.out.println("File not found: " + inputFile);
            return;
        }
        BSTreeLogging bsTreeLogging;
        if (outputFile != null) {
            bsTreeLogging = new BSTreeLogging(wordTracker.getWordTree(), outputFile);
        } else {
            bsTreeLogging = new BSTreeLogging(wordTracker.getWordTree());
        }

        switch (argument) {
            case "f":
                bsTreeLogging.printWordsWithFiles();
                break;
            case "l":
                bsTreeLogging.printWordsWithLines();
                break;
            case "o":
                bsTreeLogging.printWordsWithLineNumbers();
                break;
        }

        //save the file
        bsTreeLogging.close();
        long endTime = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (endTime - startTime) + "ms");
    }

    /**
     * Gets the arguments from the command line.
     *
     * @param args the arguments passed in from the command line
     * @return the arguments as a string
     */
    private static String getArguments(String[] args) {
        StringBuilder allArgsBuilder = new StringBuilder();

        for (String arg : args)
            allArgsBuilder.append(arg + " ");

        String arguments = allArgsBuilder.toString();
        return arguments;
    }

    /**
     * Gets the value for a flag label.
     *
     * @param label the label to get the value for
     * @param input the input string
     * @return the value for the flag label
     */
    private static String getValueForFlagLabel(String label, String input) {

        // case insensitive setups
        String lowerLabel = label.toLowerCase();
        String lowerInput = input.toLowerCase();

        String data;

        if (!lowerInput.contains("-" + lowerLabel)) {
            return null;
        } else if (lowerInput.contains("-" + lowerLabel + " ")) {
            return null;
        }
        int indexOfFlag = lowerInput.indexOf("-" + lowerLabel);
        int indexOfEndOfData = lowerInput.indexOf(' ', indexOfFlag);

        data = input.substring(indexOfFlag + 2, indexOfEndOfData);

        if ((indexOfEndOfData + 5) <= (input.length() - 1) && input.startsWith(" .txt", indexOfEndOfData)) {
            data = data + ".txt";
        }

        return data;
    }


}
