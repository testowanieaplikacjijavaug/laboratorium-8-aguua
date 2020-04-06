package MockType;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.easymock.EasyMockExtension;
import org.easymock.MockType;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(EasyMockExtension.class)
public class EasyMockNice {


    ArrayList<Integer> mockList = mock(MockType.NICE, ArrayList.class);


    @Test
    public void noRecordedCallsReturnsFalse(){
        boolean a = mockList.add(1);
        assertFalse(a);
    }

    @Test
    public void noRecordedCallsReturnsNull(){
        assertTrue(mockList.get(0) == null);
    }

    @Test
    public void testRecodedCalls() {
        expect(mockList.add(1)).andReturn(true);
        expect(mockList.get(0)).andReturn(10);
        expect(mockList.size()).andReturn(5);

        replay(mockList);

        //dowolna kolejność sprawdzania nauczonych zachowań
        assertEquals(5, mockList.size());
        assertTrue(mockList.get(0) == 10);
        assertTrue(mockList.add(1));
        verify(mockList);
    }
}
