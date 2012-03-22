/* This file was generated by SableCC's ObjectMacro. */

package org.sablecc.sablecc.codegeneration.java.macro;

import java.util.*;

public class MNewList {

    private final String pListName;

    private final String pListType;

    private final String pLowerBound;

    private final MNewList mNewList = this;

    private final List<Object> eNormalParameter_NewParameter = new LinkedList<Object>();

    private final List<Object> eAddElement_AddList = new LinkedList<Object>();

    MNewList(
            String pListName,
            String pListType,
            String pLowerBound) {

        if (pListName == null) {
            throw new NullPointerException();
        }
        this.pListName = pListName;
        if (pListType == null) {
            throw new NullPointerException();
        }
        this.pListType = pListType;
        if (pLowerBound == null) {
            throw new NullPointerException();
        }
        this.pLowerBound = pLowerBound;
    }

    public MNormalParameter newNormalParameter(
            String pElementType,
            String pElementName,
            String pIndex) {

        MNormalParameter lNormalParameter = new MNormalParameter(pElementType,
                pElementName, pIndex);
        this.eNormalParameter_NewParameter.add(lNormalParameter);
        return lNormalParameter;
    }

    public MNewParameter newNewParameter(
            String pElementName) {

        MNewParameter lNewParameter = new MNewParameter(pElementName);
        this.eNormalParameter_NewParameter.add(lNewParameter);
        return lNewParameter;
    }

    public MAddElement newAddElement(
            String pListName,
            String pElementName) {

        MAddElement lAddElement = new MAddElement(pListName, pElementName);
        this.eAddElement_AddList.add(lAddElement);
        return lAddElement;
    }

    public MAddList newAddList(
            String pListName,
            String pElementName) {

        MAddList lAddList = new MAddList(pListName, pElementName);
        this.eAddElement_AddList.add(lAddList);
        return lAddList;
    }

    String pListName() {

        return this.pListName;
    }

    String pListType() {

        return this.pListType;
    }

    String pLowerBound() {

        return this.pLowerBound;
    }

    private String rListType() {

        return this.mNewList.pListType();
    }

    private String rListName() {

        return this.mNewList.pListName();
    }

    private String rLowerBound() {

        return this.mNewList.pLowerBound();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("      NodeList<");
        sb.append(rListType());
        sb.append("> ");
        sb.append(rListName());
        sb.append(" = new NodeList<");
        sb.append(rListType());
        sb.append(">(");
        sb.append(rLowerBound());
        sb.append(",");
        for (Object oNormalParameter_NewParameter : this.eNormalParameter_NewParameter) {
            sb.append(oNormalParameter_NewParameter.toString());
        }
        sb.append(");");
        sb.append(System.getProperty("line.separator"));
        for (Object oAddElement_AddList : this.eAddElement_AddList) {
            sb.append(oAddElement_AddList.toString());
        }
        return sb.toString();
    }

}
