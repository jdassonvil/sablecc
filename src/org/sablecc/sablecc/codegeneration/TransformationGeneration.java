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

package org.sablecc.sablecc.codegeneration;

import static org.sablecc.util.CamelCase.*;

import java.math.*;
import java.util.*;

import org.sablecc.exception.*;
import org.sablecc.sablecc.codegeneration.java.macro.*;
import org.sablecc.sablecc.core.*;
import org.sablecc.sablecc.core.interfaces.*;
import org.sablecc.sablecc.grammar.Element.ProductionElement;
import org.sablecc.sablecc.grammar.Element.TokenElement;
import org.sablecc.sablecc.grammar.interfaces.*;
import org.sablecc.sablecc.grammar.transformation.*;
import org.sablecc.sablecc.oldlrautomaton.*;

public class TransformationGeneration
        implements ITransformationVisitor {

    private final Stack<Object> macroStack = new Stack<Object>();

    private final MReduceDecision mReduceDecision;

    private final OldAlternative reducedAlternative;

    private Map<String, BigInteger> nameToVarNameMap = new HashMap<String, BigInteger>();

    private final Map<IReferencable, String> alternativeToCamelFullName;

    TransformationGeneration(
            OldAlternative reducedAlternative,
            MReduceDecision reduceDecision,
            Map<IReferencable, String> alternativeToCamelFullName) {

        this.reducedAlternative = reducedAlternative;
        this.mReduceDecision = reduceDecision;
        this.macroStack.push(reduceDecision);
        this.alternativeToCamelFullName = alternativeToCamelFullName;
    }

    private String getNextVarId(
            String base) {

        BigInteger varCount = this.nameToVarNameMap.get(base);

        if (varCount == null) {
            this.nameToVarNameMap.put(base, BigInteger.ONE);
            return to_camelCase(base);
        }
        else {
            varCount.add(BigInteger.ONE);
            this.nameToVarNameMap.put(base, varCount);
            return to_camelCase(base + varCount);
        }
    }

    @Override
    public void visitNewElement(
            SAlternativeTransformationElement.NewElement node) {

        String alt_CamelCase = this.alternativeToCamelFullName.get(node
                .getAlternative());

        String elementName = getNextVarId(alt_CamelCase);

        MNewTreeClass newMacro = this.mReduceDecision.newNewTreeClass(
                alt_CamelCase, elementName);
        this.macroStack.push(newMacro);

        for (SAlternativeTransformationElement e : node.getElements()) {
            e.apply(this);
        }

        this.mReduceDecision.newAddNToForest(elementName);
        this.macroStack.pop();
    }

    @Override
    public void visitReferenceElement(
            SAlternativeTransformationElement.ReferenceElement node) {

        IElement reference = node.getReference();
        Object currentMacro = this.macroStack.peek();

        if (reference instanceof TokenElement) {

            OldElement matchedElement = this.reducedAlternative
                    .getElement((TokenElement) reference);

            if (currentMacro instanceof MNewTreeClass) {
                ((MNewTreeClass) currentMacro).newNormalParameter(
                        matchedElement.getTypeName(), matchedElement.getName(),
                        "0");
            }
            else if (currentMacro instanceof MReduceDecision) {
                ((MReduceDecision) currentMacro).newAddLToForest(matchedElement
                        .getName());
            }
            else {
                throw new InternalException("Unhandled "
                        + currentMacro.getClass());
            }

        }
        else if (reference instanceof ProductionElement) {
            OldElement matchedElement = this.reducedAlternative
                    .getElement((ProductionElement) reference);

            if (currentMacro instanceof MNewTreeClass) {
                ((MNewTreeClass) currentMacro).newNormalParameter(
                        matchedElement.getTypeName(), matchedElement.getName(),
                        "0");
            }
            else if (currentMacro instanceof MReduceDecision) {
                ((MReduceDecision) currentMacro).newAddLToForest(matchedElement
                        .getName());
            }
            else {
                throw new InternalException("Unhandled "
                        + currentMacro.getClass());
            }
        }
        else if (reference instanceof SProductionTransformationElement) {

            if (reference instanceof SProductionTransformationElement.NormalElement) {
                SProductionTransformationElement.NormalElement normalElement = (SProductionTransformationElement.NormalElement) reference;

                if (normalElement.getCoreReference() instanceof Tree.TreeProduction) {
                    String prodCamelCaseType = ((Tree.TreeProduction) normalElement
                            .getCoreReference()).getName_CamelCase();

                    if (currentMacro instanceof MNewTreeClass) {
                        String elementName = to_camelCase(normalElement
                                .getProductionTransformation().getProduction()
                                .getName());
                        ((MNewTreeClass) currentMacro).newNormalParameter(
                                prodCamelCaseType, elementName,
                                normalElement.getIndex() + "");
                    }
                    else if (currentMacro instanceof MReduceDecision) {
                        String elementName = to_camelCase(normalElement
                                .getName());
                        ((MReduceDecision) currentMacro)
                                .newAddLToForest(elementName);
                    }
                    else {
                        throw new InternalException("Unhandled "
                                + currentMacro.getClass());
                    }
                }

            }
            else if (reference instanceof SProductionTransformationElement.SeparatedElement) {

            }
            else if (reference instanceof SProductionTransformationElement.AlternatedElement) {

            }
            else {
                // unhandled case
            }

        }

    }

    @Override
    public void visitListElement(
            SAlternativeTransformationElement.ListElement node) {

        for (SAlternativeTransformationListElement e : node.getElements()) {
            e.apply(this);
        }

    }

    @Override
    public void visitNullElement(
            SAlternativeTransformationElement.NullElement node) {

        Object currentMacro = this.macroStack.peek();

        if (currentMacro instanceof MReduceDecision) {

            ((MReduceDecision) currentMacro).newAddNullToForest();

        }
        else if (currentMacro instanceof MNewTreeClass) {
            ((MNewTreeClass) currentMacro).newNullParameter();
        }
        else {
            throw new InternalException("Unhandled " + currentMacro.getClass());
        }

    }

    @Override
    public void visitReferenceListElement(
            SAlternativeTransformationListElement.ReferenceElement node) {

        // TODO Auto-generated method stub

    }

    @Override
    public void visitNewListElement(
            SAlternativeTransformationListElement.NewElement node) {

        for (SAlternativeTransformationElement e : node.getElements()) {
            e.apply(this);
        }

    }

    @Override
    public void visitNormalListListElement(
            SAlternativeTransformationListElement.NormalListElement node) {

        // TODO Auto-generated method stub

    }

    @Override
    public void visitLeftListListElement(
            SAlternativeTransformationListElement.LeftListElement node) {

        // TODO Auto-generated method stub

    }

    @Override
    public void visitRightListListElement(
            SAlternativeTransformationListElement.RightListElement node) {

        // TODO Auto-generated method stub

    }

}
