package com.coinbase.utils;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;

import com.coinbase.utils.CircularQueue;

/**
 * Unit test for Utils package.
 */
public class UtilsTest {

    @Test
    public void testCirucalSizeKeepTo5() {
       CircularQueue<Integer> queue = new CircularQueue<Integer>(5);

       queue.add(1);
       queue.add(2);
       queue.add(3);
       queue.add(4);
       queue.add(5);
       queue.add(6);

       assertTrue( queue.size() == 5 );
    }
}
