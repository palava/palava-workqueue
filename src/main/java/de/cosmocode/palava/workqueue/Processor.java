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

import java.util.List;

import de.cosmocode.collections.Procedure;

/**
 * A {@link Processor} defines the actual process of handling multiple
 * elements of a {@link WorkQueue}.
 *
 * @since 1.0
 * @author Willi Schoenborn
 * @param <E> generic element type
 */
public interface Processor<E> extends Procedure<List<E>> {

}
