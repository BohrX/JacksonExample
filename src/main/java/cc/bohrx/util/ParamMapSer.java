package cc.bohrx.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.*;

public class ParamMapSer  extends JsonSerializer<Map<String, Object>> {

    private static Set<String> specialFields = Collections.singleton("serverParamList");

    @Override
    public void serialize(Map<String, Object> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        Map<String, Object> copy = new HashMap<>();
        copy.putAll(value);
        for (String field : specialFields){
            Object v = copy.get(field);
            if(v != null){
                copy.put(field, JsonUtil.Obj2Str(v));
            }
        }
        gen.writeObject(copy);
    }
}
