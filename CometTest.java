

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CometTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CometTest
{
    private CometOne comet1;
    private CometTwo comet2;
    public CometTest()
    {
        Universe test = new Universe();
        comet1 = new CometOne(0,0,0,0,25,null,test);
        comet2 = new CometTwo(0,0,0,0,25,null,test);
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
    public void typeTest(){
        comet1.getType().equals("SEVEN");
        comet2.getType().equals("SIX");
    }
}



