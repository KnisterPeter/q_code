package s2;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Test class for Q-Code Port to Java.
 * 
 * @author andeng
 */
public class QTest extends AbstractTest {

    /** Define some test strings. */
    private static final Map<Integer, String> TEST_STRINGS = new TreeMap<Integer, String>();

    static {
        TEST_STRINGS
                .put(Integer.valueOf(1),
                        "{\"content\":{\"url\":\"Quitte/Quittenprodukte/Gelb~CT?673.html\",\"node\":[\"title\",\"#content\"],\"nav\":\"c31\"}}");
        TEST_STRINGS
                .put(Integer.valueOf(2),
                        "{\"to\":\"s2.controller.ExtMyAccountController#showLogin\",\"layer\":{\"name\":\"login\",\"params\":{\"explicit\":true}}}");
        TEST_STRINGS
                .put(Integer.valueOf(3),
                        "{\"content\":{\"url\":\"/order/detail/743808971-240238.html\",\"node\":[\"title\",\"#content\"]}}");
    }

    public static final void main(final String[] args) {
        System.out.println("************** Test started at  '" + new Date() + "' **************");

        final Map<Integer, String> encoded = testEncode();
        final Map<Integer, String> decoded = testDecode(encoded);
        // test the result
        try {
            assert TEST_STRINGS.size() == decoded.size() : "Number of decoded string is not equals number of test string: "
                    + decoded.size() + "!=" + TEST_STRINGS.size();
            for (final Map.Entry<Integer, String> decodedString : decoded.entrySet()) {
                assert TEST_STRINGS.get(decodedString.getKey()).equals(decodedString.getValue()) : "Decoded string not equals test string: \n"
                        + TEST_STRINGS.get(decodedString.getKey()) + "\n" + decodedString.getValue();
            }
        } catch (AssertionError ae) {
            printAssertionError(ae);
        }
        System.out.println("************** Test finished at '" + new Date() + "' **************");
    }

    private static Map<Integer, String> testEncode() {
        final Map<Integer, String> encoded = new TreeMap<Integer, String>();
        for (final Map.Entry<Integer, String> testString : TEST_STRINGS.entrySet()) {
            encoded.put(testString.getKey(), Q.encode(testString.getValue()));
        }
        return encoded;
    }

    private static Map<Integer, String> testDecode(final Map<Integer, String> encoded) {
        final Map<Integer, String> decoded = new TreeMap<Integer, String>();
        for (final Map.Entry<Integer, String> encodedString : encoded.entrySet()) {
            decoded.put(encodedString.getKey(), Q.decode(encodedString.getValue()));
        }
        return decoded;
    }

    private static void printAssertionError(AssertionError ae) {
        final StackTraceElement[] stackTraceElements = ae.getStackTrace();
        final StackTraceElement stackTraceElement = stackTraceElements[0];
        System.err.println("AssertionError");
        System.err.println("   class=   " + stackTraceElement.getClassName());
        System.err.println("   method=  " + stackTraceElement.getMethodName());
        System.err.println("   line=    " + stackTraceElement.getLineNumber());
        System.err.println("   message= " + ae.getMessage());
    }
}
