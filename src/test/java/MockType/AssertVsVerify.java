package MockType;
import MockExamples.Car;
import org.easymock.EasyMockExtension;
import org.easymock.MockType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(EasyMockExtension.class)
public class AssertVsVerify {

    @Test
    public void onlyAssert() {
        // Chcemy przetestować metodę getEngineTemperature.
        Car mock = mock(MockType.NICE, Car.class);
        expect(mock.getEngineTemperature()).andReturn(30.0);
        expect(mock.getEngineCapacity()).andReturn(1.4);
        replay(mock);

        assertEquals(1.4, mock.getEngineCapacity());
        // assercja przeszła pomyślnie, nie ma błędu
        // testowaliśmy inną metodę, assert nie zwraca uwagi na to, że
        // getEngineTemperature nie zostało wywołane
    }

    @Disabled("Expectation failure on verify: Car.getEngineTemperature()")
    @Test
    public void withVerify() {
        // Chcemy przetestować metodę getEngineTemperature.
        Car mock = mock(MockType.NICE, Car.class);
        expect(mock.getEngineTemperature()).andReturn(30.0);
        expect(mock.getEngineCapacity()).andReturn(1.4);
        replay(mock);

        assertEquals(1.4, mock.getEngineCapacity());

        // Brak wywołania metody getEngineTemperature spowoduje bład,
        // ponieważ verify sprawdza czy wszystkie oczekiwane metody zostały wywołane

        verify(mock);

    }
}



