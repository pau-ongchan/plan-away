package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlanTest {
    private Plan p1;
    private Plan p2;

    @BeforeEach
    void runBefore() {
        p1 = new Plan(1, "Stanley", "Picnic with friends");
    }

    @Test
    void testConstructor(){
        assertEquals(1, p1.getDay());
        assertEquals("Stanley", p1.getLocation());
        assertEquals("Picnic with friends", p1.getDescription());
        assertFalse(p1.getHasVisited());
    }

    @Test
    void testSetDay(){
        p1.setDay(2);
        assertEquals(2, p1.getDay());
    }

    @Test
    void testSetLocation(){
        p1.setLocation("San Francisco");
        assertEquals("San Francisco", p1.getLocation());
    }

    @Test
    void testSetDescription(){
        p1.setDescription("meeting");
        assertEquals("meeting", p1.getDescription());
    }

    @Test
    void testVisited(){
        p1.visited();
        assertTrue(p1.getHasVisited());
    }

    @Test
    void testRevisit(){
        p1.revisit();
        assertFalse(p1.getHasVisited());
    }

}
