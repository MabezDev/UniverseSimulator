

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class Space_ObjectTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Space_ObjectTest
{
    private Space_Object testObject;
    
    public Space_ObjectTest()
    {
        testObject = new Space_Object(24,32,2,-4,25,null,new Universe());
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
    public void getSpeed()
    {
        if(testObject.getXSpeed()==2);
        if(testObject.getYSpeed()==-4);
    }
    
    @Test
    public void getSize(){
        if(testObject.getDiameter() > 0);
    }
}

