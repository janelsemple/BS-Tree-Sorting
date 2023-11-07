package main.exceptions;

/**
 * This class represents a RuntimeException that is thrown by a tree.
 * It extends RunTimeException, and inherits all its methods and properties.
 */
public class TreeException extends RuntimeException {
    public TreeException(String message) {
        super(message);
    }
}
