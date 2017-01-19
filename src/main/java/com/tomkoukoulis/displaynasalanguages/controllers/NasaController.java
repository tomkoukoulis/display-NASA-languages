package com.tomkoukoulis.displaynasalanguages.controllers;

import com.tomkoukoulis.displaynasalanguages.model.Repository;
import com.tomkoukoulis.displaynasalanguages.model.Languages;
import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;


@Controller
public class NasaController extends Restful {

    public static final String GITHUB_LIST_NASA_REPOSITORIES_URL = "https://api.github.com/orgs/NASA/repos";
    public static final String GITHUB_LIST_REPOSITORY_LANGUAGES_1 = "https://api.github.com/repos/NASA/";
    public static final String GITHUB_LIST_REPOSITORY_LANGUAGES_2 = "/languages";
    
    public static String getNasaRepositoryLanguagesUrl(String name) {
        return GITHUB_LIST_REPOSITORY_LANGUAGES_1 + name + GITHUB_LIST_REPOSITORY_LANGUAGES_2;
    }

    @RequestMapping(value = "/languages", method = RequestMethod.GET)
    public String viewLanguages(ModelMap model) {

        return "index";
    }

    protected HashMap<String, Long> getLanguages() throws RestClientException, NumberFormatException {
        
        return null;
    }

    protected Repository[] getRepositories() throws RestClientException {
        
        return null;
    }

    protected void collateLanguages(HashMap<String, String> languages, HashMap<String, Long> collatedLanguages) throws NumberFormatException {

    }
}
