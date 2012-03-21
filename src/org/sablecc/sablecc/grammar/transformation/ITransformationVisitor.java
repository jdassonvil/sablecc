
package org.sablecc.sablecc.grammar.transformation;

public interface ITransformationVisitor {

    void visitNewElement(
            SAlternativeTransformationElement.NewElement node);

    void visitReferenceElement(
            SAlternativeTransformationElement.ReferenceElement node);

    void visitListElement(
            SAlternativeTransformationElement.ListElement node);

    void visitNullElement(
            SAlternativeTransformationElement.NullElement node);

    void visitReferenceListElement(
            SAlternativeTransformationListElement.ReferenceElement node);

    void visitNewListElement(
            SAlternativeTransformationListElement.NewElement node);

    void visitNormalListListElement(
            SAlternativeTransformationListElement.NormalListElement node);

    void visitLeftListListElement(
            SAlternativeTransformationListElement.LeftListElement node);

    void visitRightListListElement(
            SAlternativeTransformationListElement.RightListElement node);

}
