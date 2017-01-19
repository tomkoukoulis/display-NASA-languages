/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
}
