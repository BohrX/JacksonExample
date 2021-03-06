package cc.bohrx.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;


import java.io.IOException;
import java.util.*;

public class ParamMapDes  extends JsonDeserializer<Map<String, Object>> {

    private static Set<String> specialFields = Collections.singleton("serverParamList");

    @Override
    public Map<String, Object> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if(JsonToken.START_OBJECT == p.getCurrentToken()){
            Map<String, Object> map = new HashMap<>();
            // enter map body/object value
            for (next(p); p.getCurrentToken() != JsonToken.END_OBJECT;next(p)) {
                String propName = p.currentName();
                // Skip field name:
                next(p);
                Object val;
                if(specialFields.contains(propName)){
                    val = deserializeSpecialFields(p,ctxt);
                    map.put(propName,val);
                }else {
                    val = p.readValueAs(Object.class);
                    map.put(propName,val);
                }
            }
            System.out.println("--------------------------------------");
            return map;
        }
        return p.getCodec().readValue(p, new TypeReference<Map<String, Object>>() {});
    }

    private JsonToken next(JsonParser p) throws IOException {
        JsonToken token = p.nextToken();
        System.out.println(token+": "+p.currentName()+"="+p.getValueAsString());
        return token;
    }

    public List<Map<String, Object>> deserializeSpecialFields(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        switch (p.getCurrentToken()){
            case VALUE_STRING:
                return JacksonDeserialHelper.Str2MapArr(p,ctxt);
            case START_OBJECT:
                return JacksonDeserialHelper.singleObj2MapArr(p);
        }

        return p.getCodec().readValue(p, new TypeReference<List<Map<String, Object>>>() { });
    }

}
