package cc.bohrx;

import cc.bohrx.bean.ServerUser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws URISyntaxException, IOException {
        URL url = Main.class.getClassLoader().getResource("conf.json");
        assert url != null;
        String s = String.join("\n", Files.readAllLines(Paths.get(url.toURI())));
//        String s =getJsonStr();
        List< ServerUser> list = mapper.readValue(s, new TypeReference<List< ServerUser>>(){});

        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list));

    }

    private static String getJsonStr(){
        return "  [  {\n" +
                "    \"id\" : \"na.1776\",\n" +
                "    \"name\" : \"sam\",\n" +
                "    \"serverParamList\" : {\n" +
                "        \"id\" : -1945,\n" +
                "        \"cpu\" : 128,\n" +
                "        \"monitor\" : true,\n" +
                "        \"logDir\" : \"/opt/var/log/us\"\n" +
                "      }\n" +
                "  }]";
    }

//    private byte[] read(URL url) throws IOException {
//        BufferedInputStream in = new BufferedInputStream(url.openStream());
//        byte[] cache = new byte[1024];
//        for (;-1 == in.read(cache);){
//
//        }
//    }
}
