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

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;

/**
 * Internal {@link Delayed} implementation.
 *
 * @since 1.0
 * @author Willi Schoenborn
 * @param <E> generic element type
 */
final class DelayedElement<E> implements ResetableDelayed {

    private final E element;
    private long endOfDelay;
    
    private final long delayInMillis;
    
    public DelayedElement(E element, long delayInMillis) {
        this.element = Preconditions.checkNotNull(element, "Element");
        this.endOfDelay = System.currentTimeMillis() + delayInMillis;
        this.delayInMillis = delayInMillis;
    }
    
    @Override
    public int compareTo(Delayed that) {
        return Longs.compare(getDelay(TimeUnit.MILLISECONDS), that.getDelay(TimeUnit.MILLISECONDS));
    }
    
    @Override
    public void reset() {
        this.endOfDelay = System.currentTimeMillis() + delayInMillis;
    }
    
    /**
     * Returns the element associated with this delayed element.
     * 
     * @return the associated element
     */
    public E element() {
        return element;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(endOfDelay - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

}
