/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomkoukoulis.displaynasalanguages.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tomkoukoulis
 */
public class LanguagesTest {

    public LanguagesTest() {
    }

    @Test
    public void testEquals() {
        Languages instance1 = new Languages();
        instance1.set("Java", "1000");
        Languages instance2 = new Languages();
        instance2.set("Java", "1000");

        assertEquals(instance1, instance2);
    }

    @Test
    public void testNotEquals() {
        Languages instance1 = new Languages();
        instance1.set("Java", "1000");
        Languages instance2 = new Languages();
        instance1.set("Scala", "2000");

        assertNotEquals(instance1, instance2);
    }
}
