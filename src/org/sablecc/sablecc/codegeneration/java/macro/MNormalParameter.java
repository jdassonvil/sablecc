/* This file was generated by SableCC's ObjectMacro. */

package org.sablecc.sablecc.codegeneration.java.macro;

public class MNormalParameter {

    private final String pElementType;

    private final String pElementName;

    private final String pIndex;

    private final MNormalParameter mNormalParameter = this;

    MNormalParameter(
            String pElementType,
            String pElementName,
            String pIndex) {

        if (pElementType == null) {
            throw new NullPointerException();
        }
        this.pElementType = pElementType;
        if (pElementName == null) {
            throw new NullPointerException();
        }
        this.pElementName = pElementName;
        if (pIndex == null) {
            throw new NullPointerException();
        }
        this.pIndex = pIndex;
    }

    String pElementType() {

        return this.pElementType;
    }

    String pElementName() {

        return this.pElementName;
    }

    String pIndex() {

        return this.pIndex;
    }

    private String rElementType() {

        return this.mNormalParameter.pElementType();
    }

    private String rElementName() {

        return this.mNormalParameter.pElementName();
    }

    private String rIndex() {

        return this.mNormalParameter.pIndex();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("(N");
        sb.append(rElementType());
        sb.append(") l");
        sb.append(rElementName());
        sb.append(".getNodes.get(");
        sb.append(rIndex());
        sb.append(")");
        return sb.toString();
    }

}
