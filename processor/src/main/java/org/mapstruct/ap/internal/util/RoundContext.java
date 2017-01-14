/**
 *  Copyright 2012-2017 Gunnar Morling (http://www.gunnarmorling.de/)
 *  and/or other contributors as indicated by the @authors tag. See the
 *  copyright.txt file in the distribution for a full listing of all
 *  contributors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.mapstruct.ap.internal.util;

import java.util.HashSet;
import java.util.Set;

import javax.lang.model.type.TypeMirror;

import org.mapstruct.ap.spi.AstModifyingAnnotationProcessor;

/**
 * Keeps contextual data in the scope of one annotation processing round.
 *
 * @author Gunnar Morling
 */
public class RoundContext {

    private final AnnotationProcessorContext annotationProcessorContext;
    private final Set<TypeMirror> clearedTypes;

    public RoundContext(AnnotationProcessorContext annotationProcessorContext) {
        this.annotationProcessorContext = annotationProcessorContext;
        this.clearedTypes = new HashSet<TypeMirror>();
    }

    public AnnotationProcessorContext getAnnotationProcessorContext() {
        return annotationProcessorContext;
    }

    /**
     * Marks the given type as being ready for further processing.
     */
    public void addClearedType(TypeMirror type) {
        clearedTypes.add( type );
    }

    /**
     * Whether the given type has been found to be ready for further processing or not. This is the case if the type's
     * hierarchy is complete (no super-types need to be generated by other procesors) an no processors have signaled the
     * intention to amend the given type.
     *
     * @see AstModifyingAnnotationProcessor
     */
    public boolean isCleared(TypeMirror type) {
        return clearedTypes.contains( type );
    }
}
