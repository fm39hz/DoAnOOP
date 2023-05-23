package team4;

// import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import team4.KitchenManager.Object.Dishes;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    Dishes _test = new Dishes();
    // /**
    //  * Rigorous Test :-)
    //  */
    // @Test
    // public void shouldAnswerWithTrue()
    // {
    //     assertTrue( true );
    // }
    @Test
    public void DishesTest()
    {
        _test.setCost(250);
        assertEquals(_test.getCost(),250);
    }
}
