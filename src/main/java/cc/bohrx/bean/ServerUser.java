package cc.bohrx.bean;

import cc.bohrx.util.ParamMapDes;
import cc.bohrx.util.ParamMapSer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerUser {

    private ComplexId id;

    private String name;

    @JsonDeserialize(using = ParamMapDes.class)
    @JsonSerialize(using = ParamMapSer.class)
    private Map<String,Object> parameter;

    public ComplexId getId() {
        return id;
    }

    public void setId(ComplexId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getParameter() {
        return parameter;
    }

    public void setParameter(Map<String, Object> parameter) {
        this.parameter = parameter;
    }
}
