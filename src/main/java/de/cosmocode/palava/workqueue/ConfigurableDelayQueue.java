/**
 * Copyright 2010 CosmoCode GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.cosmocode.palava.workqueue;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterators;
import com.google.inject.Inject;

/**
 * Configurable {@link DelayQueue} which supports a predefined delay.
 *
 * @since 1.0
 * @author Willi Schoenborn
 * @param <E> generic element type
 */
public final class ConfigurableDelayQueue<E> implements Queue<E> {

    private final Function<DelayedElement<E>, E> extractor = new Function<DelayedElement<E>, E>() {
        
        @Override
        public E apply(DelayedElement<E> from) {
            return from.element();
        }

        @Override
        public String toString() {
            return ConfigurableDelayQueue.this + ".extractor";
        }
        
    };
    
    private final Function<E, DelayedElement<E>> transformer = new Function<E, DelayedElement<E>>() {

        @Override
        public DelayedElement<E> apply(E from) {
            return new DelayedElement<E>(from, delayInMillis);
        }

        @Override
        public String toString() {
            return ConfigurableDelayQueue.this + ".transformer";
        }
        
    };

    private final Queue<DelayedElement<E>> queue = new DelayQueue<DelayedElement<E>>();
    
    private final long delayInMillis;
    
    @Inject
    public ConfigurableDelayQueue(long delay, TimeUnit delayUnit) {
        this.delayInMillis = Preconditions.checkNotNull(delayUnit, "DelayUnit").toMillis(delay);
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean add(E e) {
        return queue.add(transformer.apply(e));
    }

    @Override
    public Iterator<E> iterator() {
        return Iterators.transform(queue.iterator(), extractor);
    }

    @Override
    public E remove() {
        return queue.remove().element();
    }

    @Override
    public boolean offer(E e) {
        return queue.offer(transformer.apply(e));
    }

    @Override
    public Object[] toArray() {
        return Collections2.transform(queue, extractor).toArray();
    }

    @Override
    public E poll() {
        final DelayedElement<E> head = queue.poll();
        return head == null ? null : head.element();
    }

    @Override
    public E element() {
        return queue.element().element();
    }

    @Override
    public E peek() {
        final DelayedElement<E> head = queue.peek();
        return head == null ? null : head.element();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return Collections2.transform(queue, extractor).toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        return Collections2.transform(queue, extractor).remove(o);
    }

    @Override
    public boolean contains(Object o) {
        return Collections2.transform(queue, extractor).contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return Collections2.transform(queue, extractor).containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return queue.addAll(Collections2.transform(c, transformer));
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return Collections2.transform(queue, extractor).removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return Collections2.transform(queue, extractor).retainAll(c);
    }

    @Override
    public void clear() {
        queue.clear();
    }
    
}
