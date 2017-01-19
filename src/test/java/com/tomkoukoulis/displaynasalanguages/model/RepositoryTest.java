package com.tomkoukoulis.displaynasalanguages.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class RepositoryTest {
    
    public RepositoryTest() {
    }
    
    @Test
    public void testEquals() {
        Repository instance1 = new Repository();
        instance1.setId(0);
        instance1.setName("DAVETools");
        Repository instance2 = new Repository();
        instance2.setId(0);
        instance2.setName("DAVETools");
        
        assertEquals(instance1, instance2);
    }
    
    @Test
    public void testNotEquals() {
        Repository instance1 = new Repository();
        instance1.setId(0);
        instance1.setName("DAVETools");
        Repository instance2 = new Repository();
        instance2.setId(0);
        instance2.setName("Tools");
        
        assertNotEquals(instance1, instance2);
    }
}
