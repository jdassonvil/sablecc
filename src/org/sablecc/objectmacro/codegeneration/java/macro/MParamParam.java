/* This file was generated by SableCC's ObjectMacro. */

package org.sablecc.objectmacro.codegeneration.java.macro;

public class MParamParam {

    private final String pName;

    private final MParamParam mParamParam = this;

    public MParamParam(
            String pName) {

        this.pName = pName;
    }

    String pName() {

        return this.pName;
    }

    private String rName() {

        return this.mParamParam.pName();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("String p");
        sb.append(rName());
        return sb.toString();
    }

}
