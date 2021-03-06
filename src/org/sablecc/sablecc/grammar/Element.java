/* This file is part of SableCC ( http://sablecc.org ).
 *
 * See the NOTICE file distributed with this work for copyright information.
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

package org.sablecc.sablecc.grammar;

import org.sablecc.exception.*;
import org.sablecc.sablecc.grammar.interfaces.*;
import org.sablecc.sablecc.syntax3.node.*;

public abstract class Element
        implements IElement {

    public abstract String getTypeName();

    @Override
    public abstract Element clone();

    public static class TokenElement
            extends Element {

        private String typeName;

        public TokenElement(
                PUnit type) {

            if (type instanceof ANameUnit) {
                this.typeName = ((ANameUnit) type).getIdentifier().getText();
            }
            else if (type instanceof AStringUnit) {
                this.typeName = ((AStringUnit) type).getString().getText();
            }
            else if (type instanceof ACharacterUnit) {
                PCharacter character = ((ACharacterUnit) type).getCharacter();

                if (character instanceof ACharCharacter) {
                    this.typeName = ((ACharCharacter) character).getChar()
                            .getText();
                }
                else if (character instanceof ADecCharacter) {
                    this.typeName = ((ADecCharacter) character).getDecChar()
                            .getText();
                }
                else if (character instanceof AHexCharacter) {
                    this.typeName = ((AHexCharacter) character).getHexChar()
                            .getText();
                }
            }
            else if (type instanceof AStartUnit) {
                this.typeName = ((AStartUnit) type).getStartKeyword().getText();
            }
            else if (type instanceof AEndUnit) {
                this.typeName = ((AEndUnit) type).getEndKeyword().getText();
            }

        }

        public TokenElement(
                String typeName) {

            if (typeName == null) {
                throw new InternalException("typeName shouldn't be null");
            }
            this.typeName = typeName;
        }

        @Override
        public String getTypeName() {

            return this.typeName;
        }

        @Override
        public Element clone() {

            return new TokenElement(this.typeName);
        }
    }

    public static class ProductionElement
            extends Element {

        private String typeName;

        private Production reference;

        public ProductionElement(
                String typeName,
                Production reference) {

            if (typeName == null) {
                throw new InternalException("typeName shouldn't be null");
            }

            if (reference == null) {
                throw new InternalException("reference shouldn't be null");
            }

            this.typeName = typeName;
            this.reference = reference;
        }

        public ProductionElement(
                Production reference) {

            if (reference == null) {
                throw new InternalException("reference shouldn't be null");
            }

            this.typeName = reference.getName();
            this.reference = reference;
        }

        @Override
        public String getTypeName() {

            return this.typeName;
        }

        public Production getReference() {

            return this.reference;
        }

        @Override
        public Element clone() {

            return new ProductionElement(this.typeName, this.reference);
        }
    }

}
