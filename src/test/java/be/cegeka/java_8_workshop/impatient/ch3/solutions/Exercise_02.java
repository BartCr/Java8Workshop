package be.cegeka.java_8_workshop.impatient.ch3.solutions;

import org.junit.Test;
import org.mockito.InOrder;

import java.util.concurrent.locks.Lock;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

public class Exercise_02 {

    @Test
    public void solution() {
        Lock lock = mock(Lock.class);
        Iterable iterable = mock(Iterable.class);

        withLock(lock, iterable::iterator);

        InOrder inOrder = inOrder(lock, iterable);

        inOrder.verify(lock).lock();
        inOrder.verify(iterable).iterator();
        inOrder.verify(lock).unlock();
    }

    public static void withLock(Lock lock, Runnable runnable) {
        lock.lock();
        try {
            runnable.run();
        } finally {
            lock.unlock();
        }
    }
}
