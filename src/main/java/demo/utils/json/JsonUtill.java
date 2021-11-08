package demo.utils.json;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

public class JsonUtill {
    public static String getJSONValue(JsonObject obj, String propName)
    {
        String output = null;
        if (obj.has(propName)) {
            Object obj2 = null;
            try {
                obj2 = obj.get(propName);
            } catch (JsonIOException e) {
                e.printStackTrace();
            }
            if (obj2 != null)
                output = obj2.toString();
        }

        return output;
    }

}
