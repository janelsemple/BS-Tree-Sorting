package main.utils;

import main.datastructures.BSTree;
import main.datastructures.BSTreeNode;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BSTreeLogging {


    private final BSTree<WordNode> tree;
    private File outputFile;
    private PrintWriter writer;

    /**
     * Constructor for BSTreeLogging when there is no output file.
     *
     * @param tree the tree to log
     */
    public BSTreeLogging(BSTree<WordNode> tree) {
        this.tree = tree;
    }

    /**
     * Constructor for BSTreeLogging when there is an output file.
     *
     * @param tree       the tree to log
     * @param outputFile the output file to write to
     */
    public BSTreeLogging(BSTree<WordNode> tree, String outputFile) {
        this.tree = tree;
        this.outputFile = new File(outputFile);
        try {
            writer = new PrintWriter(outputFile);
        } catch (Exception e) {
            System.out.println("Error creating output file: " + outputFile);
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * In-order traversal to loop through the tree.
     *
     * @param node  the current node being visited
     * @param nodes the list to store the nodes in in-order
     */
    private void inOrderTraversal(BSTreeNode<WordNode> node, List<WordNode> nodes) {
        if (node != null) {
            inOrderTraversal(node.getLeft(), nodes);
            nodes.add(node.getElement());
            inOrderTraversal(node.getRight(), nodes);
        }
    }

    /**
     * Prints words in alphabetic order along with the corresponding list of files.
     */
    public void printWordsWithFiles() {
        List<WordNode> nodes = new ArrayList<>();
        inOrderTraversal(tree.getRoot(), nodes);
        for (WordNode node : nodes) {
            write(node.getWord() + ": ");
            for (String file : node.getFileLinesMap().keySet()) {
                write(file + " ");
            }
            write("\n");
        }
    }

    /**
     * Prints words in alphabetic order along with the corresponding list of files and line numbers.
     */
    public void printWordsWithLines() {
        List<WordNode> nodes = new ArrayList<>();
        inOrderTraversal(tree.getRoot(), nodes);
        for (WordNode node : nodes) {
            write(node.getWord() + ": ");
            for (Map.Entry<String, List<Integer>> entry : node.getFileLinesMap().entrySet()) {
                String filename = entry.getKey();
                List<Integer> lineNumbers = entry.getValue();
                write(filename + " (Lines: ");
                for (int lineNumber : lineNumbers) {
                    write(lineNumber + " ");
                }
                write(") ");
            }
            write("\n");
        }
    }

    /**
     * Prints words in alphabetic order along with the corresponding list of files and line numbers.
     */
    public void printWordsWithLineNumbers() {
        List<WordNode> nodes = new ArrayList<>();
        inOrderTraversal(tree.getRoot(), nodes);
        for (WordNode node : nodes) {
            write(node.getWord() + ": ");
            for (Map.Entry<String, List<Integer>> entry : node.getFileLinesMap().entrySet()) {
                String filename = entry.getKey();
                List<Integer> lineNumbers = entry.getValue();
                write(filename + " (Line Numbers: ");
                for (int lineNumber : lineNumbers) {
                    write(lineNumber + " ");
                }
                write(") ");
            }
            write("\n");
        }
    }

    /**
     * Write to either the output file or the console.
     *
     * @param log the log to write
     */
    private void write(String log) {
        if (writer != null) {
            writer.print(log);
            return;
        }
        System.out.print(log);
    }

    /**
     * Close the output file.
     */
    public void close() {
        if (writer != null) {
            writer.close();
            System.out.println("Output file written to: " + outputFile.getAbsolutePath());
        }
    }
}
