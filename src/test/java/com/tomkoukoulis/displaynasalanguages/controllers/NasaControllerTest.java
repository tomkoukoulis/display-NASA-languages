package com.tomkoukoulis.displaynasalanguages.controllers;

import com.tomkoukoulis.displaynasalanguages.model.Repository;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 *
 * @author tomkoukoulis
 */
public class NasaControllerTest {

    public NasaControllerTest() {
    }

    /**
     * Test of getLanguages method, of class NasaController.
     */
    @Test
    public void testGetLanguages() {
        NasaController instance = new NasaController();

        RestTemplate restTemplate = instance.getRestTemplate();
        MockRestServiceServer server = MockRestServiceServer.bindTo(restTemplate).build();
        server.expect(once(), requestTo(NasaController.GITHUB_LIST_NASA_REPOSITORIES_URL))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess(
                    "["
                    + "{ \"id\" : \"0\", \"name\" : \"DAVETools\"}, "
                    + "{ \"id\" : \"1\", \"name\" : \"Tools\"}"
                    + "]", MediaType.APPLICATION_JSON));

        server.expect(once(), requestTo(NasaController.getNasaRepositoryLanguagesUrl("DAVETools")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess("{ \"Java\" : \"1000\", \"Scala\" : \"1500\"}", MediaType.APPLICATION_JSON));

        server.expect(once(), requestTo(NasaController.getNasaRepositoryLanguagesUrl("Tools")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess("{ \"Java\" : \"2000\", \"C++\" : \"2500\"}", MediaType.APPLICATION_JSON));

        HashMap<String, Long> expectedResult = new HashMap<>();
        expectedResult.put("Java", 3000L);
        expectedResult.put("C++", 2500L);
        expectedResult.put("Scala", 1500L);

        HashMap<String, Long> result = instance.getLanguages();

        assertEquals(expectedResult, result);
    }

    /**
     * Test of getLanguages method, of class NasaController.
     */
    @Test
    public void testGetRepositories() {
        NasaController instance = new NasaController();

        RestTemplate restTemplate = instance.getRestTemplate();
        MockRestServiceServer server = MockRestServiceServer.bindTo(restTemplate).build();
        server.expect(once(), requestTo(NasaController.GITHUB_LIST_NASA_REPOSITORIES_URL))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess(
                  "["
                    + "{ \"id\" : \"0\", \"name\" : \"DAVETools\"}, "
                    + "{ \"id\" : \"1\", \"name\" : \"Tools\"}"
                + "]", MediaType.APPLICATION_JSON));

        Repository[] expectedResult = new Repository[2];
        expectedResult[0] = new Repository();
        expectedResult[0].setId(0);
        expectedResult[0].setName("DAVETools");
        expectedResult[1] = new Repository();
        expectedResult[1].setId(1);
        expectedResult[1].setName("Tools");

        Repository[] result = instance.getRepositories();

        assertArrayEquals(expectedResult, result);
    }

    /**
     * Test of collateLanguages method, of class NasaController.
     */
    @Test
    public void testCollateLanguages() {
        HashMap<String, String> languages = new HashMap<>();
        languages.put("Java", "1000");
        languages.put("Scala", "1500");

        HashMap<String, Long> collatedLanguages = new HashMap<>();
        collatedLanguages.put("Java", 2000L);
        collatedLanguages.put("C++", 2500L);

        HashMap<String, Long> expectedResult = new HashMap<>();
        expectedResult.put("Java", 3000L);
        expectedResult.put("C++", 2500L);
        expectedResult.put("Scala", 1500L);

        NasaController instance = new NasaController();

        instance.collateLanguages(languages, collatedLanguages);

        assertTrue(expectedResult.equals(collatedLanguages));
    }

    /**
     * Test of collateLanguages method, of class NasaController.
     */
    @Test(expected = NumberFormatException.class)
    public void testCollateLanguagesThrowsNumberFormatException() {
        HashMap<String, String> languages = new HashMap<>();
        languages.put("Java", "NaN");
        languages.put("Scala", "1500");

        HashMap<String, Long> collatedLanguages = new HashMap<>();
        collatedLanguages.put("Java", 2000L);
        collatedLanguages.put("C++", 2500L);

        NasaController instance = new NasaController();

        instance.collateLanguages(languages, collatedLanguages);

        fail("This test should have thrown a NumberFormatException");
    }
}
