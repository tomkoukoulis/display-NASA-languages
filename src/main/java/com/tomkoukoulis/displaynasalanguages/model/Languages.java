package com.tomkoukoulis.displaynasalanguages.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashMap;

/**
 *
 * @author tomkoukoulis
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Languages {

    private final HashMap<String, String> languages = new HashMap<>();

    public Languages() {
    }

    @JsonAnyGetter
    public HashMap<String, String> get() {
        return languages;
    }

    @JsonAnySetter
    public void set(String key, String value) {
        languages.put(key, value);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Languages)) 
            return false;

        Languages languages = (Languages) obj;
        boolean isEqual = false;
        
        if(this.languages.keySet().equals(languages.get().keySet())){
            isEqual = true;
        }
        
        return isEqual;
    }
}
