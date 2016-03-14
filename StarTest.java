

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class StarTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StarTest
{
    private Star star;
    public StarTest()
    {
        star = new Star(30,20,0,0,35,null,new Universe());
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
    public void removeLife(){
        int initial = star.getLife();
        star.addLife(100);
        if(star.getLife() < initial);
    }
}
