package main.utils;

import main.datastructures.BSTree;
import main.datastructures.BSTreeNode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * This class represents a WordTracker that takes in words from a text file and adds them a BST with WordNodes.
 * The WordTracker class can also store the BST in a binary file so that when it is instantiated again, it will re-populate the BST so that new words may be added.
 */
public class WordTracker {

    /**
     * The name of the binary file which will contain a BST of WordNodes
     */
    private static final String REPOSITORY_FILE = "repository.ser";  // specify the path to the file

    /**
     * The BST that will hold WordNodes
     */
    private BSTree<WordNode> wordTree;

    /**
     * No argument constructor for WordTracker
     */
    public WordTracker() {
        wordTree = readTreeFromFile();
        if (wordTree == null) {
            wordTree = new BSTree<>();
        }
    }

    /**
     * Returns a BST populated with WordNode objects read from a binary file.
     *
     * @return a BST populated with WordNode objects
     */
    private BSTree<WordNode> readTreeFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(REPOSITORY_FILE))) {
            return (BSTree<WordNode>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    /**
     * Saves the BST to a binary file.
     */
    public void saveTreeToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(REPOSITORY_FILE))) {
            oos.writeObject(wordTree);
        } catch (IOException ex) {
            System.err.println("Failed to save tree to file: " + ex.getMessage());
        }
    }

    /**
     * Gets the BST containing the WordNode objects.
     * @return a BST containing WordNode objects
     */
    public BSTree<WordNode> getWordTree() {
        return wordTree;
    }

    /**
     * Processes words from the provided text file and places them in the BST.
     * @param filename
     * @throws IOException
     */
    public void processFile(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        WordNode wordNode;
        for (int i = 0; i < lines.size(); i++) {
            String[] words = lines.get(i).split("\\s+");
            for (String word : words) {
                word = word.replaceAll("[^a-zA-Z0-9'-]", "");
                if (word.isEmpty())
                    continue;
                WordNode newWordNode = new WordNode(word);

                BSTreeNode<WordNode> node = wordTree.search(newWordNode);
                if (node != null) {
                    wordNode = node.getElement();
                    wordNode.addFileAndLineNumber(filename, i + 1);
                } else {
                    newWordNode.addFileAndLineNumber(filename, i + 1);
                    wordTree.add(newWordNode);
                }
            }
        }

    }
}
