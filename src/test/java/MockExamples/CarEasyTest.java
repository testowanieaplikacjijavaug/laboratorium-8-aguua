package MockExamples;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;


public class CarEasyTest {
    private Car myFerrari = EasyMock.createMock(Car.class);

    @Test
    public void test_instance_car(){
        assertTrue(myFerrari instanceof Car);
    }

    @Test
    public void test_default_behavior_needsFuel(){
        assertFalse(myFerrari.needsFuel(), "New test double should return False as boolean");
    }

    @Test
    public void test_default_behavior_temperature(){
        assertEquals(0.0, myFerrari.getEngineTemperature(), "New test double should return 0.0");
    }
    
    @Test
    public void test_stubbing_mock(){
        EasyMock.expect(myFerrari.needsFuel()).andReturn(true);
        EasyMock.replay(myFerrari);
        assertTrue(myFerrari.needsFuel());

    }

    @Test
    public void test_exception(){
        EasyMock.expect(myFerrari.needsFuel()).andThrow(new RuntimeException());
        EasyMock.replay(myFerrari);
        assertThrows(RuntimeException.class, () -> {
            myFerrari.needsFuel();
        });
    }
    @Test   //1
    public void test_default_behavior_capacity(){
        assertEquals(0.0, myFerrari.getEngineCapacity(), "New test double should return 0.0");
    }

    @Test  //2
    public void test_recorded_temperature_value(){
        EasyMock.expect(myFerrari.getEngineTemperature()).andReturn(50.5);
        EasyMock.replay(myFerrari);
        assertEquals(50.5, myFerrari.getEngineTemperature());
    }
    @Test  //3
    public void test_recorded_capacity_value(){
        EasyMock.expect(myFerrari.getEngineCapacity()).andReturn(1.8);
        EasyMock.replay(myFerrari);
        assertEquals(1.8, myFerrari.getEngineCapacity());
    }

    @Disabled
    @Test  //4
    public void test_destination(){
        final AtomicReference passedLocation = null;
        final AtomicBoolean driveToCalled = new AtomicBoolean();
        doAnswer( invocation -> {
                    passedLocation.set(EasyMock.getCurrentArguments()[0]);
                    driveToCalled.set(true);
                    return null;
                }).when(myFerrari).driveTo(anyString());
        EasyMock.replay(myFerrari);

        myFerrari.driveTo("London");
        assertEquals("Longvdfdon", passedLocation);
        assertEquals(true, driveToCalled.get());
        EasyMock.verify();

    }
    @Test  //5
    public void test5(){}


}
