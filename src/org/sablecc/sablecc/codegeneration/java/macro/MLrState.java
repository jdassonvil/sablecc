/* This file was generated by SableCC's ObjectMacro. */

package org.sablecc.sablecc.codegeneration.java.macro;

import java.util.*;

public class MLrState {

    private final List<Object> eDefaultPackage_SpecifiedPackage = new LinkedList<Object>();

    public MLrState() {

    }

    public MDefaultPackage newDefaultPackage(
            String pLanguageName) {

        MDefaultPackage lDefaultPackage = new MDefaultPackage(pLanguageName);
        this.eDefaultPackage_SpecifiedPackage.add(lDefaultPackage);
        return lDefaultPackage;
    }

    public MSpecifiedPackage newSpecifiedPackage(
            String pLanguageName,
            String pPackage) {

        MSpecifiedPackage lSpecifiedPackage = new MSpecifiedPackage(
                pLanguageName, pPackage);
        this.eDefaultPackage_SpecifiedPackage.add(lSpecifiedPackage);
        return lSpecifiedPackage;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(new MHeader().toString());
        if (this.eDefaultPackage_SpecifiedPackage.size() > 0) {
            sb.append(System.getProperty("line.separator"));
        }
        for (Object oDefaultPackage_SpecifiedPackage : this.eDefaultPackage_SpecifiedPackage) {
            sb.append(oDefaultPackage_SpecifiedPackage.toString());
        }
        sb.append(System.getProperty("line.separator"));
        sb.append("abstract class LRState {");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("  abstract Node apply(Parser parser)");
        sb.append(System.getProperty("line.separator"));
        sb.append("      throws ParserException, LexerException, IOException;");
        sb.append(System.getProperty("line.separator"));
        sb.append("  ");
        sb.append(System.getProperty("line.separator"));
        sb.append("  abstract LRState getTokenTarget(InternalType type)");
        sb.append(System.getProperty("line.separator"));
        sb.append("      throws ParserException, LexerException, IOException;");
        sb.append(System.getProperty("line.separator"));
        sb.append("  ");
        sb.append(System.getProperty("line.separator"));
        sb.append("  abstract LRState getProductionTarget(CSTProductionType type)");
        sb.append(System.getProperty("line.separator"));
        sb.append("      throws ParserException, LexerException, IOException;");
        sb.append(System.getProperty("line.separator"));
        sb.append("}");
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }

}