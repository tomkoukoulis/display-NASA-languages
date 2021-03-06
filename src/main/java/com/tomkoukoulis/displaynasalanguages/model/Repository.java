package com.tomkoukoulis.displaynasalanguages.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {

    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String name;
    
    public Repository() {
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Repository)) return false;

        Repository repo = (Repository) obj;
        boolean isEqual = false;
        
        if(this.name.equalsIgnoreCase(repo.getName()) && this.id == repo.getId()){
            isEqual = true;
        }
        
        return isEqual;
    }
}
