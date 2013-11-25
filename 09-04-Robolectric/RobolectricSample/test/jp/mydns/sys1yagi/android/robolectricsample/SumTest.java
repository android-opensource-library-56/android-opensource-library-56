/**
 * 
 */
package jp.mydns.sys1yagi.android.robolectricsample;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class SumTest {

    @Before
    public void before() {

    }

    @After
    public void after() {

    }

    @Test
    public void sumTest() {
        Sum sum = new Sum();
        assertThat(sum.sum(10, 4), is(14));
    }

}
