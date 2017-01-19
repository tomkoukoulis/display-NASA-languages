package com.tomkoukoulis.displaynasalanguages.controllers;

import java.util.ArrayList;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class Restful {

    private RestTemplate restTemplate;

    protected RestTemplate getRestTemplate() {
        if (restTemplate != null) {
            return restTemplate;
        } else {
            ArrayList<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
            messageConverters.add(new MappingJackson2HttpMessageConverter());
            restTemplate = new RestTemplate();
            restTemplate.setMessageConverters(messageConverters);

            return restTemplate;
        }
    }

    protected <T> T get(String url, Class<T> aClass) throws RestClientException {
        return aClass.cast(getRestTemplate().getForObject(url, aClass));
    }
}
