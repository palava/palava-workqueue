package de.cosmocode.palava.workqueue;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * Test {@link Module}.
 *
 * @since 1.0
 * @author Willi Schoenborn
 */
public final class ApplicationTestModule implements Module {
    
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationTestModule.class);

    @Override
    public void configure(Binder binder) {

    }
    
    @Provides
    public Queue<String> provideBackingQueue() {
        return new SetQueue<String>(new ConcurrentLinkedQueue<String>());
    }
    
    @Provides
    @Singleton
    public Processor<String> provideProcessor() {
        return new Processor<String>() {
            
            @Override
            public void apply(List<String> input) {
                LOG.info("Processing {}", input);
            }
            
        };
    }
    
    @Provides
    @Singleton
    public WorkQueue<String> provideWorkQueue(Queue<String> queue, Processor<String> processor) {
        return new BatchWorkQueue<String>(queue, processor, 1);
    }

}
