package cafeboard.storage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonStorage {

    static final ObjectMapper mapper = new ObjectMapper();

    public static String serialize(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        }

        catch (Exception e) {
            System.err.println("JSON serialization failed for object: " + obj + " (" + e.getMessage() + ")");
            return null;
        }
    }

    public static <T> T deserialize(String json, TypeReference<T> typeRef) {
        try {
            return mapper.readValue(json, typeRef);
        }

        catch (Exception e) {
            System.err.println("JSON deserialization failed for json: " + json + " (" + e.getMessage() + ")");
            return null;
        }
    }
}

