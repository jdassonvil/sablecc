/* This file was generated by SableCC's ObjectMacro. */

package org.sablecc.objectmacro.codegeneration.java.macro;

import java.util.LinkedList;
import java.util.List;

public class MTextInsert {

    private final String pName;

    private final MTextInsert mTextInsert = this;

    private final List<Object> eString_ParamInsert_TextInsert_TextInsertAncestor = new LinkedList<Object>();

    public MTextInsert(
            String pName) {

        this.pName = pName;
    }

    public MString newString(
            String pString) {

        MString lString = new MString(pString);
        this.eString_ParamInsert_TextInsert_TextInsertAncestor.add(lString);
        return lString;
    }

    public MParamInsert newParamInsert(
            String pName) {

        MParamInsert lParamInsert = new MParamInsert(pName);
        this.eString_ParamInsert_TextInsert_TextInsertAncestor
                .add(lParamInsert);
        return lParamInsert;
    }

    public MTextInsert newTextInsert(
            String pName) {

        MTextInsert lTextInsert = new MTextInsert(pName);
        this.eString_ParamInsert_TextInsert_TextInsertAncestor.add(lTextInsert);
        return lTextInsert;
    }

    public MTextInsertAncestor newTextInsertAncestor(
            String pName) {

        MTextInsertAncestor lTextInsertAncestor = new MTextInsertAncestor(pName);
        this.eString_ParamInsert_TextInsert_TextInsertAncestor
                .add(lTextInsertAncestor);
        return lTextInsertAncestor;
    }

    String pName() {

        return this.pName;
    }

    private String rName() {

        return this.mTextInsert.pName();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("new M");
        sb.append(rName());
        sb.append("(");
        {
            boolean first = true;
            for (Object oString_ParamInsert_TextInsert_TextInsertAncestor : this.eString_ParamInsert_TextInsert_TextInsertAncestor) {
                if (first) {
                    first = false;
                }
                else {
                    sb.append(", ");
                }
                sb.append(oString_ParamInsert_TextInsert_TextInsertAncestor
                        .toString());
            }
        }
        sb.append(").toString()");
        return sb.toString();
    }

}
