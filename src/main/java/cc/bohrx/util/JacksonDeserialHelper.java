package cc.bohrx.util;

import cc.bohrx.exc.CustomJsonMismatchedInputException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class JacksonDeserialHelper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<Map<String, Object>> Str2MapArr(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TypeFactory typeFactory = ctxt.getTypeFactory();
        return cast(mapper.readValue(p.getValueAsString(),Object.class),p,
                typeFactory.constructArrayType(typeFactory.constructMapType(Map.class,String.class, String.class)));
    }

    public static List<Map<String, Object>> singleObj2MapArr(JsonParser p) throws IOException, JsonProcessingException {
        List<Map<String, Object>> list= new ArrayList<>();
        list.add(p.getCodec().readValue(p,new TypeReference<Map<String, Object>>() { }));
        return list;
    }

    // 场景：1. Map<String, ?> 2. List<?>
    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> cast(Object noStrObj, JsonParser p, JavaType targetType)throws JsonProcessingException {
        List<Map<String, Object>> res= new ArrayList<>();
        if(noStrObj instanceof Map){
            res.add((Map<String, Object>)noStrObj); // key must be String
        }else if(noStrObj instanceof List){
            for (Iterator<?> it = ((List<?>) noStrObj).iterator(); !it.hasNext();) {
                Object e = it.next();
                if(e == null){
                    it.remove();
                }else if (!(e instanceof Map)) {
                    throw CustomJsonMismatchedInputException.from(p, targetType,
                            "val is illegal! the string value must be parsed to list of Map<String, Object>" +
                                    ", but there is a "+e.getClass()+" element!");
                }
            }
            res.addAll((List<Map<String, Object>>) noStrObj); // key of map must be String
        }else {
            throw CustomJsonMismatchedInputException.from(p, targetType,
                    "val is illegal! the string value must be parsed to List<Map<String, Object>>!");
        }
        return res;
    }
}
