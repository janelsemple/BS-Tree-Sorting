package main.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents an object to be stored in the node of a word tree. A WordNode contains a word, the line numbers where it occurs, and the filenames it occurs in.
 * WordNode implements Comparable and Serializable.
 */
public class WordNode implements Comparable<WordNode>, Serializable {

    /**
     *  The serialVersion UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * The word
     */
    private String word;

    /**
     * A map to store the line numbers and file names
     */
    private Map<String, List<Integer>> fileLinesMap;

    /**
     * A constructor for a WordNode
     *
     * @param word The word to be stored
     */
    public WordNode(String word) {
        this.word = word;
        this.fileLinesMap = new HashMap<>();
    }

    /**
     * Adds a file name and line number to the fileLinesMap
     *
     * @param filename The name of the file where the word is found
     * @param lineNumber The line number where the word is found
     */
    public void addFileAndLineNumber(String filename, int lineNumber) {
        fileLinesMap.computeIfAbsent(filename, k -> new ArrayList<>()).add(lineNumber);
    }

    /**
     * returns the word
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * returns the map containing the file names and line numbers.
     *
     * @return the map containing the file names and line numbers
     */
    public Map<String, List<Integer>> getFileLinesMap() {
        return fileLinesMap;
    }

    /**
     * Overridden method inherited from the Comparable interface.
     * Compares two WordNodes by word.
     *
     * @param other the object to be compared.
     * @return a negative integer, zero, or a positive integer as the specified String is greater than, equal to, or less than this String, ignoring case considerations
     */
    @Override
    public int compareTo(WordNode other) {
        return this.word.compareToIgnoreCase(other.word);
    }
}