package test;

import org.junit.jupiter.api.extension.*;
import java.util.*;

public class TestResultsLogger implements TestWatcher, AfterAllCallback {
    private List<String> successfulTests = new ArrayList<>();
    private List<String> failedTests = new ArrayList<>();
    private List<String> abortedTests = new ArrayList<>();
    private List<String> disabledTests = new ArrayList<>();

    @Override
    public void testSuccessful(ExtensionContext context) {
        successfulTests.add(context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        failedTests.add(context.getDisplayName());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        abortedTests.add(context.getDisplayName());
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        disabledTests.add(context.getDisplayName());
    }

    @Override
    public void afterAll(ExtensionContext context) {
        System.out.println("\nTest Summary:");
        System.out.println("Total Tests: " + getTotalTests());
        printNonEmptyList("Successful Tests", successfulTests);
        printNonEmptyList("Failed Tests", failedTests);
        printNonEmptyList("Aborted Tests", abortedTests);
        printNonEmptyList("Disabled Tests", disabledTests);
    }

    private int getTotalTests() {
        return successfulTests.size() + failedTests.size() + abortedTests.size() + disabledTests.size();
    }

    private void printNonEmptyList(String label, List<String> list) {
        if (!list.isEmpty()) {
            System.out.println(label + ": " + list);
        }
    }
}
