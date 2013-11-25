package jp.mydns.sys1yagi.android.mockitosample;

import static org.mockito.Mockito.*;

import java.util.Iterator;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import android.test.AndroidTestCase;

public class MockTest extends AndroidTestCase {
    private final static String TAG = MockTest.class.getSimpleName();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.setProperty("dexmaker.dexcache", getContext().getCacheDir()
                .getPath());
        MockitoAnnotations.initMocks(this);
    }

    public void listProcessor(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
    }

    public void listIterateProcessor(List<String> list) {
        for (String str : list) {
            assertEquals("test", str);
        }
    }

    @SuppressWarnings("unchecked")
    public void testMock() {
        List<String> list = mock(List.class);
        when(list.size()).thenReturn(1);
        when(list.get(0)).thenReturn("test");

        listProcessor(list);

        verify(list).get(0);
    }

    @SuppressWarnings("unchecked")
    public void testIteratorMock() {
        List<String> list = mock(List.class);
        Iterator<String> itr = mock(Iterator.class);

        when(itr.hasNext()).thenReturn(true).thenReturn(true).thenReturn(false);

        when(itr.next()).thenReturn("test");

        when(list.iterator()).thenReturn(itr);

        listIterateProcessor(list);
        verify(list).iterator();
        verify(itr, times(3)).hasNext();
        verify(itr, times(2)).next();
    }

    static class Something {
        private List<String> list;

        public void setList(List<String> list) {
            this.list = list;
        }

        public int getSize() {
            return list.size();
        }
    }

    @Mock
    List<String> list;

    @InjectMocks
    Something mSomething;

    public void testSomething() {
        mSomething = new Something();
        MockitoAnnotations.initMocks(this);

        when(list.size()).thenReturn(10);

        assertEquals(10, mSomething.getSize());
    }

    public static class Status {
        public boolean isReady() {
            return false;
        }
    }

    public int getMode(Status status) {
        if (status.isReady()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void testReady() {
        Status status = mock(Status.class);
        when(status.isReady()).thenReturn(false);
        assertEquals(0, getMode(status));
    }

    public void testNotReady() {
        Status status = mock(Status.class);
        when(status.isReady()).thenReturn(true);
        assertEquals(1, getMode(status));
    }

}
