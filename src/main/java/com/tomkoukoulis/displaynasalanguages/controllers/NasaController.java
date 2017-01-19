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

        try {
            model.addAttribute("languages", getLanguages());
        } catch (RestClientException rce) {
            model.addAttribute("my-error-message", "There was a problem communicating with GitHub.");
        } catch (NumberFormatException nfe) {
            model.addAttribute("my-error-message", "The data received from GitHub had an error.");
        }

        return "index";
    }

    protected HashMap<String, Long> getLanguages() throws RestClientException, NumberFormatException {
        Repository[] nasaRepos = getRepositories();

        HashMap<String, Long> collatedLanguages = new HashMap<>();

        for (int i = 0; i < 2; i++) { //TODO: Replace 2 with nasaRepos.length
            String languagesUrl = getNasaRepositoryLanguagesUrl(nasaRepos[i].getName());

            Languages repoLanguages = get(languagesUrl, Languages.class);

            HashMap languages = repoLanguages.get();

            collateLanguages(languages, collatedLanguages);
        }

        return collatedLanguages;
    }

    protected Repository[] getRepositories() throws RestClientException {
        return get(GITHUB_LIST_NASA_REPOSITORIES_URL, Repository[].class);
    }

    protected void collateLanguages(HashMap<String, String> languages, HashMap<String, Long> collatedLanguages) throws NumberFormatException {
        for (HashMap.Entry<String, String> entry : languages.entrySet()) {
            String language = entry.getKey();
            String noOfLines = entry.getValue();

            if (collatedLanguages.get(language) == null) {
                collatedLanguages.put(language, Long.parseLong(noOfLines));
            } else {
                collatedLanguages.put(language, collatedLanguages.get(language) + Long.parseLong(noOfLines));
            }
        }
    }
}
