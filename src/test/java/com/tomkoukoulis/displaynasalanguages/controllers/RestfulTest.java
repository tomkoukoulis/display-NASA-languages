package com.tomkoukoulis.displaynasalanguages.controllers;

import com.tomkoukoulis.displaynasalanguages.model.Languages;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author tomkoukoulis
 */
public class RestfulTest {

    public RestfulTest() {
    }

    /**
     * Test of getRestTemplate method, of class Restful.
     */
    @Test
    public void testGetRestTemplate() {
        Restful instance = new Restful();

        RestTemplate result = instance.getRestTemplate();

        assertNotNull(result);
    }

    /**
     * Test of get method, of class Restful.
     */
    @Test
    public void testGet() {
        Restful rest = new Restful();
        RestTemplate restTemplate = rest.getRestTemplate();
        MockRestServiceServer server = MockRestServiceServer.bindTo(restTemplate).build();

        server.expect(once(), requestTo("/test")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("{ \"Java\" : \"1000\", \"C++\" : \"1500\"}", MediaType.APPLICATION_JSON));

        Languages expResult = new Languages();
        expResult.set("Java", "1000");
        expResult.set("C++", "1500");
        
        Languages result = rest.get("/test", Languages.class);
             
        server.verify(); // Verify all expectations met
        assertTrue(expResult.equals(result));
    }
    
    /**
     * Test of get method, of class Restful.
     */
    @Test(expected = RestClientException.class)
    public void testGetThrowsRestClientException() {
        Restful rest = new Restful();
        RestTemplate restTemplate = rest.getRestTemplate();
        MockRestServiceServer server = MockRestServiceServer.bindTo(restTemplate).build();

        server.expect(once(), requestTo("/test")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("Gibberish", MediaType.TEXT_HTML));
        
        Languages result = rest.get("/test", Languages.class);
             
        server.verify(); // Verify all expectations met
        fail("This test should have thrown a RestClientException");
    }
}
