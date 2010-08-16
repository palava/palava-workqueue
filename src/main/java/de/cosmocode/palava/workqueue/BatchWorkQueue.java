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

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Batch based {@link WorkQueue}.
 *
 * @since 1.0
 * @author Willi Schoenborn
 * @param <E> generic element type
 */
final class BatchWorkQueue<E extends Serializable> extends ForwardingQueue<E> implements WorkQueue<E> {

    private final Queue<E> queue;
    private final Processor<E> processor;
    
    private final int batchSize;

    @Inject
    public BatchWorkQueue(BlockingQueue<E> queue, Processor<E> processor, 
        @Named(WorkQueueConfig.BATCH_SIZE) int batchSize) {
        
        this.queue = Preconditions.checkNotNull(queue, "Queue");
        this.processor = Preconditions.checkNotNull(processor, "Processor");
        this.batchSize = batchSize;
    }
    
    @Override
    protected Queue<E> delegate() {
        return queue;
    }
    
    @Override
    public void run() {
        
        final List<E> all = Lists.newArrayList();
        
        while (true) {
            final E e = queue.poll();
            if (e == null) break;
            all.add(e);
        }
        
        switch (batchSize) {
            case 0: {
                processor.apply(all);
                break;
            }
            case 1: {
                for (E e : all) {
                    processor.apply(Collections.singletonList(e));
                }
                break;
            }
            default: {
                for (List<E> partition : Lists.partition(all, batchSize)) {
                    processor.apply(partition);
                }
                break;
            }
        }
    }
    
}
