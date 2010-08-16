package de.cosmocode.palava.workqueue;

import java.util.Collection;
import java.util.Queue;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingQueue;

/**
 * Decorator to add {@link Set} semantic to {@link Queue}s.
 *
 * @since 1.0
 * @author Willi Schoenborn
 * @param <E> generic element type
 */
final class SetQueue<E> extends ForwardingQueue<E> {

    private final Queue<E> queue;
    
    public SetQueue(Queue<E> queue) {
        this.queue = Preconditions.checkNotNull(queue, "Queue");
    }

    @Override
    protected Queue<E> delegate() {
        return queue;
    }

    @Override
    public boolean offer(E element) {
        remove(element);
        return super.offer(element);
    }

    @Override
    public boolean add(E element) {
        remove(element);
        return super.add(element);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        removeAll(collection);
        return super.addAll(collection);
    }
    
}
