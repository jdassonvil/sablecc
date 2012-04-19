/* This file was generated by SableCC's ObjectMacro. */

package org.sablecc.sablecc.codegeneration.java.macro;

public class MSeparatedDeclaration {

    private final String pLeftListType;

    private final String pRightListType;

    private final MSeparatedDeclaration mSeparatedDeclaration = this;

    MSeparatedDeclaration(
            String pLeftListType,
            String pRightListType) {

        if (pLeftListType == null) {
            throw new NullPointerException();
        }
        this.pLeftListType = pLeftListType;
        if (pRightListType == null) {
            throw new NullPointerException();
        }
        this.pRightListType = pRightListType;
    }

    String pLeftListType() {

        return this.pLeftListType;
    }

    String pRightListType() {

        return this.pRightListType;
    }

    private String rLeftListType() {

        return this.mSeparatedDeclaration.pLeftListType();
    }

    private String rRightListType() {

        return this.mSeparatedDeclaration.pRightListType();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("SeparatedNodeList<N");
        sb.append(rLeftListType());
        sb.append(",N");
        sb.append(rRightListType());
        sb.append(">");
        return sb.toString();
    }

}
