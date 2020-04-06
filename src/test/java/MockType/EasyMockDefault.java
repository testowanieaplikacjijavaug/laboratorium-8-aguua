package MockType;
import org.easymock.EasyMockExtension;
import org.easymock.MockType;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(EasyMockExtension.class)
public class EasyMockDefault {


    ArrayList<Integer> mockList = mock(MockType.DEFAULT, ArrayList.class);

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
