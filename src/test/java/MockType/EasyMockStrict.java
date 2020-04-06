package MockType;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.easymock.EasyMockExtension;
import org.easymock.MockType;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(EasyMockExtension.class)
public class EasyMockStrict {

    ArrayList<Integer> mockList = mock(MockType.STRICT, ArrayList.class);


    @Test
    public void  testRecodedCalls() {
        expect(mockList.add(10)).andReturn(true);
        expect(mockList.add(20)).andReturn(true);
        expect(mockList.size()).andReturn(2);
        expect(mockList.get(0)).andReturn(10);
        replay(mockList);

        mockList.add(10);
        mockList.add(20);
        assertEquals(mockList.size(), 2);
        assertTrue(mockList.get(0) == 10);

        verify(mockList);
    }

    @Test(expected = AssertionError.class)
    public void  testRecodedCallsWrongOrder() {
        expect(mockList.add(10)).andReturn(true);
        expect(mockList.size()).andReturn(2);
        replay(mockList);

        // nieodpowienia kolejnosc uzycia zadeklarownych zachowań
        // Unexpected method call ArrayList.size(): ArrayList.add(10 (int)): expected: 1, actual: 0
        assertEquals(mockList.size(), 2);
        assertTrue(mockList.get(0) == 10);

    }

}
