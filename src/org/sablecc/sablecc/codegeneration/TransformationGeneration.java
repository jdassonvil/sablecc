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
import org.sablecc.sablecc.grammar.*;
import org.sablecc.sablecc.grammar.Element.ProductionElement;
import org.sablecc.sablecc.grammar.Element.TokenElement;
import org.sablecc.sablecc.grammar.interfaces.*;
import org.sablecc.sablecc.grammar.transformation.*;
import org.sablecc.sablecc.oldlrautomaton.*;
import org.sablecc.util.*;
import org.sablecc.util.Type.SimpleType.HomogeneousType;

public class TransformationGeneration
        implements ITransformationVisitor {

    private final Stack<Object> macroStack = new Stack<Object>();

    private final Stack<Pair<String, String>> listStack = new Stack<Pair<String, String>>();

    private final OldAlternative reducedAlternative;

    private final Grammar grammar;

    private Map<String, BigInteger> nameToVarNameMap = new HashMap<String, BigInteger>();

    private final Map<IReferencable, String> alternativeToCamelFullName;

    TransformationGeneration(
            Grammar grammar,
            OldAlternative reducedAlternative,
            MReduceDecision reduceDecision,
            Map<IReferencable, String> alternativeToCamelFullName) {

        this.grammar = grammar;
        this.reducedAlternative = reducedAlternative;
        this.macroStack.push(reduceDecision);
        this.alternativeToCamelFullName = alternativeToCamelFullName;
    }

    private String getNextVarId(
            String base) {

        BigInteger varCount = this.nameToVarNameMap.get(base);

        if (varCount == null) {
            if (base == "") {
                this.nameToVarNameMap.put(base, BigInteger.ONE);
                return to_camelCase("$" + BigInteger.ONE);
            }
            else {
                this.nameToVarNameMap.put(base, BigInteger.ZERO);
                return to_camelCase(base);
            }

        }
        else {
            varCount.add(BigInteger.ONE);
            this.nameToVarNameMap.put(base, varCount);
            return to_camelCase(base + "$" + varCount);
        }
    }

    @Override
    public void visitNewElement(
            SAlternativeTransformationElement.NewElement node) {

        String alt_CamelCase = this.alternativeToCamelFullName.get(node
                .getAlternative());

        String elementName = getNextVarId(alt_CamelCase);

        Object currentMacro = this.macroStack.peek();

        MNewTreeClass newMacro;

        if (currentMacro instanceof MReduceDecision) {
            newMacro = ((MReduceDecision) currentMacro).newNewTreeClass(
                    alt_CamelCase, elementName);
        }
        else if (currentMacro instanceof MNewTreeClass) {
            newMacro = ((MNewTreeClass) currentMacro).newNewTreeClass(
                    alt_CamelCase, elementName);
        }
        else {
            throw new InternalException("Unhandle " + currentMacro.getClass());
        }

        this.macroStack.push(newMacro);

        for (SAlternativeTransformationElement e : node.getElements()) {
            e.apply(this);
        }

        this.macroStack.pop();

        if (currentMacro instanceof MReduceDecision) {
            ((MReduceDecision) currentMacro).newAddNToForest(elementName);
        }
        else if (currentMacro instanceof MNewTreeClass) {
            newMacro.newNewParameter(elementName);
        }
        else {
            throw new InternalException("Unhandle " + currentMacro.getClass());
        }

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
                        to_CamelCase(matchedElement.getTypeName()),
                        matchedElement.getName(), "0");
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
                        to_CamelCase(matchedElement.getTypeName()),
                        matchedElement.getName(), "0");
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
                throw new InternalException("Unhandled "
                        + currentMacro.getClass());
            }

        }

    }

    private String computeListType(
            SAlternativeTransformationElement.ListElement node) {

        if (node.getType() instanceof HomogeneousType) {

            HomogeneousType type = (HomogeneousType) node.getType();

            INameDeclaration nameDeclaration = this.grammar
                    .getGlobalReference(type.getName());

            if (nameDeclaration instanceof LexerExpression.NamedExpression) {
                LexerExpression.NamedExpression namedToken = (LexerExpression.NamedExpression) nameDeclaration;
                return namedToken.getName_CamelCase();
            }
            else if (nameDeclaration instanceof LexerExpression.InlineExpression) {
                LexerExpression.InlineExpression inlineToken = (LexerExpression.InlineExpression) nameDeclaration;
                return inlineToken.getInternalName_CamelCase();
            }
            else if (nameDeclaration instanceof Parser.ParserProduction) {
                return to_CamelCase(((Parser.ParserProduction) nameDeclaration)
                        .getName());
            }
            else {
                throw new InternalException("Unhandle "
                        + nameDeclaration.getClass());
            }
        }
        else {
            throw new InternalException("Not implemented yet");
        }
    }

    @Override
    public void visitListElement(
            SAlternativeTransformationElement.ListElement node) {

        Object currentMacro = this.macroStack.peek();

        String listTypeName = computeListType(node);
        String listName = getNextVarId("");

        MNewList newList;
        if (currentMacro instanceof MReduceDecision) {
            newList = ((MReduceDecision) currentMacro).newNewList(listName,
                    listTypeName);
        }
        else if (currentMacro instanceof MNewTreeClass) {
            newList = ((MNewTreeClass) currentMacro).newNewList(listName,
                    listTypeName);
        }
        else {
            throw new InternalException("Unhandle " + currentMacro.getClass());
        }

        this.macroStack.push(newList);
        this.listStack.push(new Pair<String, String>(listTypeName, listName));

        for (SAlternativeTransformationListElement e : node.getElements()) {
            e.apply(this);
        }

        this.listStack.pop();
        newList = (MNewList) this.macroStack.pop();

        newList.newStringParameter(node.getType().getCardinality()
                .getLowerBound().getValue()
                + "");

        if (!node.getType().getCardinality().upperBoundIsInfinite()) {
            newList.newStringParameter(node.getType().getCardinality()
                    .getUpperBound().getValue()
                    + "");
        }

        if (currentMacro instanceof MReduceDecision) {
            ((MReduceDecision) currentMacro).newAddNToForest(listName);
        }
        else if (currentMacro instanceof MNewTreeClass) {
            ((MNewTreeClass) currentMacro).newNewParameter(listName);
        }
        else {
            throw new InternalException("Unhandled " + currentMacro.getClass());
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

        MNewList list = (MNewList) this.macroStack.peek();

        if (node.getReference() instanceof Element) {
            String elementName = this.reducedAlternative.getElement(
                    (Element) node.getReference()).getName();
            String elementType = to_CamelCase(this.reducedAlternative
                    .getElement((Element) node.getReference()).getTypeName());
            list.newAddPopElement(this.listStack.peek().getRight(),
                    elementName, elementType, "0");
        }
        else if (node.getReference() instanceof SProductionTransformationElement) {

            SProductionTransformationElement reference = (SProductionTransformationElement) node
                    .getReference();

            if (reference instanceof SProductionTransformationElement.NormalElement) {
                SProductionTransformationElement.NormalElement normalElement = (SProductionTransformationElement.NormalElement) reference;

                if (normalElement.getCoreReference() instanceof Tree.TreeProduction) {
                    String prodCamelCaseType = ((Tree.TreeProduction) normalElement
                            .getCoreReference()).getName_CamelCase();

                    String elementName = to_camelCase(normalElement
                            .getProductionTransformation().getProduction()
                            .getName());
                    list.newAddPopElement(this.listStack.peek().getRight(),
                            elementName, prodCamelCaseType,
                            normalElement.getIndex() + "");

                }
                else if (normalElement.getCoreReference() instanceof LexerExpression.NamedExpression) {
                    String tokenCamelCaseType = ((LexerExpression.NamedExpression) normalElement
                            .getCoreReference()).getName_CamelCase();
                    String elementName = to_camelCase(normalElement
                            .getProductionTransformation().getProduction()
                            .getName());

                    list.newAddPopElement(this.listStack.peek().getRight(),
                            elementName, tokenCamelCaseType,
                            normalElement.getIndex() + "");
                }
                else if (normalElement.getCoreReference() instanceof LexerExpression.InlineExpression) {
                    String tokenCamelCaseType = ((LexerExpression.InlineExpression) normalElement
                            .getCoreReference()).getInternalName_CamelCase();
                    String elementName = to_camelCase(normalElement
                            .getProductionTransformation().getProduction()
                            .getName());

                    list.newAddPopElement(this.listStack.peek().getRight(),
                            elementName, tokenCamelCaseType,
                            normalElement.getIndex() + "");
                }
                else {
                    throw new InternalException("unhandle case");
                }

            }
            else {
                throw new InternalException("Unhandled " + reference.getClass());
            }

        }
        else {
            throw new InternalException("Unhandled " + node.getClass());
        }

    }

    @Override
    public void visitNewListElement(
            SAlternativeTransformationListElement.NewElement node) {

        String alt_CamelCase = this.alternativeToCamelFullName.get(node
                .getAlternative());

        String elementName = getNextVarId(alt_CamelCase);

        MNewList list = (MNewList) this.macroStack.peek();
        MNewTreeClass newMacro = list.newNewTreeClass(alt_CamelCase,
                elementName);

        this.macroStack.push(newMacro);

        for (SAlternativeTransformationElement e : node.getElements()) {
            e.apply(this);
        }

        this.macroStack.pop();

        list.newAddNewElement(this.listStack.peek().getRight(), elementName);

    }

    @Override
    public void visitNormalListListElement(
            SAlternativeTransformationListElement.NormalListElement node) {

        MNewList list = (MNewList) this.macroStack.peek();
        String elementType = this.listStack.peek().getLeft();

        if (node.getReference() instanceof Element) {
            String elementName = to_camelCase(this.reducedAlternative
                    .getElement((Element) node.getReference()).getName());
            list.newAddPopList(this.listStack.peek().getRight(), elementName,
                    elementType, "0");
        }
        else if (node.getReference() instanceof SProductionTransformationElement) {

            SProductionTransformationElement reference = (SProductionTransformationElement) node
                    .getReference();

            if (reference instanceof SProductionTransformationElement.NormalElement) {
                SProductionTransformationElement.NormalElement normalElement = (SProductionTransformationElement.NormalElement) reference;

                if (normalElement.getCoreReference() instanceof Tree.TreeProduction) {

                    String elementName = to_camelCase(normalElement
                            .getProductionTransformation().getProduction()
                            .getName());
                    list.newAddPopList(this.listStack.peek().getRight(),
                            elementName, elementType, normalElement.getIndex()
                                    + "");

                }
                else if (normalElement.getCoreReference() instanceof LexerExpression.NamedExpression) {
                    String elementName = to_camelCase(normalElement
                            .getProductionTransformation().getProduction()
                            .getName());

                    list.newAddPopList(this.listStack.peek().getRight(),
                            elementName, elementType, normalElement.getIndex()
                                    + "");
                }
                else if (normalElement.getCoreReference() instanceof LexerExpression.InlineExpression) {
                    String elementName = to_camelCase(normalElement
                            .getProductionTransformation().getProduction()
                            .getName());

                    list.newAddPopList(this.listStack.peek().getRight(),
                            elementName, elementType, normalElement.getIndex()
                                    + "");
                }
                else {
                    throw new InternalException("unhandle case");
                }

            }
            else if (reference instanceof SProductionTransformationElement.SeparatedElement) {

            }
            else if (reference instanceof SProductionTransformationElement.AlternatedElement) {

            }
            else {
                throw new InternalException("Unhandled " + reference.getClass());
            }
        }

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
