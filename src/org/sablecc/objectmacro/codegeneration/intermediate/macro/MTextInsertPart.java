/* This file was generated by SableCC's ObjectMacro. */

package org.sablecc.objectmacro.codegeneration.intermediate.macro;

import java.util.LinkedList;
import java.util.List;

public class MTextInsertPart {

    private final List<Object> eTextInsert = new LinkedList<Object>();

    public MTextInsertPart() {

    }

    public MTextInsert newTextInsert(
            String pName,
            String pIndent) {

        MTextInsert lTextInsert = new MTextInsert(pName, pIndent);
        this.eTextInsert.add(lTextInsert);
        return lTextInsert;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (Object oTextInsert : this.eTextInsert) {
            sb.append(oTextInsert.toString());
        }
        return sb.toString();
    }

}
