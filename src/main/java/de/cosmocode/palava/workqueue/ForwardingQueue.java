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

import com.google.common.collect.ForwardingObject;

abstract class ForwardingQueue<E> extends ForwardingObject implements Queue<E> {

    @Override
    protected abstract Queue<E> delegate();

    @Override
    public boolean add(E e) {
        return delegate().add(e);
    }

    @Override
    public int size() {
        return delegate().size();
    }

    @Override
    public boolean isEmpty() {
        return delegate().isEmpty();
    }

    @Override
    public boolean offer(E e) {
        return delegate().offer(e);
    }

    @Override
    public boolean contains(Object o) {
        return delegate().contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return delegate().iterator();
    }

    @Override
    public E remove() {
        return delegate().remove();
    }

    @Override
    public Object[] toArray() {
        return delegate().toArray();
    }

    @Override
    public E poll() {
        return delegate().poll();
    }

    @Override
    public E element() {
        return delegate().element();
    }

    @Override
    public E peek() {
        return delegate().peek();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return delegate().toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        return delegate().remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return delegate().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return delegate().addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return delegate().removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return delegate().retainAll(c);
    }

    @Override
    public void clear() {
        delegate().clear();
    }

}
