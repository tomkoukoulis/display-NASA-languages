package com.tomkoukoulis.displaynasalanguages.controllers;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class Restful {
        
    protected RestTemplate getRestTemplate() {

        return null;
    }

    protected <T> T get(String url, Class<T> aClass) throws RestClientException {
        return null;
    }
}
