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
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *
 * @since 
 * @author Willi Schoenborn
 * @param <E>
 */
public class ConcurrentLinkedBlockingQueue<E> extends ConcurrentLinkedQueue<E> implements BlockingQueue<E> {

    private static final long serialVersionUID = 3762477413193896836L;

    @Override
    public void put(E e) throws InterruptedException {
        add(e);
    }

    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        return offer(e);
    }

    @Override
    public E take() throws InterruptedException {
        // TODO wait if necessary
        return poll();
    }

    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        // TODO wait if necessary
        return poll();
    }

    @Override
    public int remainingCapacity() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int drainTo(Collection<? super E> c) {
        return drainTo(c, Integer.MAX_VALUE);
    }

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        for (int i = 0; i < maxElements; i++) {
            final E e = poll();
            if (e == null) return i;
            c.add(e);
        }
        return maxElements;
    }
    
}
