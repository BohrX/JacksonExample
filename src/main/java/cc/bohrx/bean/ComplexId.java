package cc.bohrx.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplexId {

    private static final String HYPHEN = ".";

    private String region;

    private String id;

    public ComplexId() {
    }

    @JsonCreator
    public ComplexId(String value) throws IllegalArgumentException {
        int pos = value.indexOf(HYPHEN);
        if(pos == -1){
            throw new IllegalArgumentException("value is illegal!");
        }
        region = value.substring(0, pos);
        id = value.substring(pos+1);
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonValue
    public String getValue(){
        return toString();
    }

    @Override
    public String toString() {
        return  region + HYPHEN + id ;
    }
}
