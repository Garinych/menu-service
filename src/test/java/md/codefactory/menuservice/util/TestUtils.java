package md.codefactory.menuservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

    private final static ObjectMapper objectM = new ObjectMapper().findAndRegisterModules();

    public static byte[] objectAsBytes(final Object obj) {
        try {
            return objectM.writeValueAsString(obj).getBytes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
