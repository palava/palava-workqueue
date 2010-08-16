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

/**
 * Static constant utility class for workqueue config key names.
 *
 * @since 1.0
 * @author Willi Schoenborn
 */
public final class WorkQueueConfig {

    public static final String PREFIX = "workQueue.";
    
    /**
     * 0 means no batch processing.
     */
    public static final String BATCH_SIZE = PREFIX + "batchSize";
    
    /**
     * 0 means no delay.
     */
    public static final String DELAY = PREFIX + "delay";
    
    public static final String DELAY_UNIT = PREFIX + "delayUnit";
    
    private WorkQueueConfig() {
        
    }
    
}
