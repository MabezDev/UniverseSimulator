

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlanetTest
{
    private Planet planet;
    
    public PlanetTest()
    {
        planet = new Planet(0,0,0,0,25,null,new Universe());
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testResize(){
        int before = planet.getDiameter();
        planet.addToDiameter(10);
        assertTrue(planet.getDiameter() > before);
    }
}
