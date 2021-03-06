package cc.bohrx.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public abstract class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    static String Obj2Str(Object value) throws JsonProcessingException {
        return mapper.writeValueAsString(value);
    }
}
