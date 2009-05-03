/* This file was generated by SableCC's ObjectMacro. */

package org.sablecc.sablecc.errormessages;

public class MSyntaxError {

    private final String pLine;

    private final String pChar;

    private final String pTokenType;

    private final String pTokenText;

    private final String pMessage;

    private final MSyntaxError mSyntaxError = this;

    public MSyntaxError(
            String pLine,
            String pChar,
            String pTokenType,
            String pTokenText,
            String pMessage) {

        this.pLine = pLine;
        this.pChar = pChar;
        this.pTokenType = pTokenType;
        this.pTokenText = pTokenText;
        this.pMessage = pMessage;
    }

    String pLine() {

        return this.pLine;
    }

    String pChar() {

        return this.pChar;
    }

    String pTokenType() {

        return this.pTokenType;
    }

    String pTokenText() {

        return this.pTokenText;
    }

    String pMessage() {

        return this.pMessage;
    }

    private String rLine() {

        return this.mSyntaxError.pLine();
    }

    private String rChar() {

        return this.mSyntaxError.pChar();
    }

    private String rTokenType() {

        return this.mSyntaxError.pTokenType();
    }

    private String rTokenText() {

        return this.mSyntaxError.pTokenText();
    }

    private String rMessage() {

        return this.mSyntaxError.pMessage();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("*** SYNTAX ERROR ***");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("Line: ");
        sb.append(rLine());
        sb.append(System.getProperty("line.separator"));
        sb.append("Char: ");
        sb.append(rChar());
        sb.append(System.getProperty("line.separator"));
        sb.append("Syntax error on unexpected ");
        sb.append(rTokenType());
        sb.append(" token \"");
        sb.append(rTokenText());
        sb.append("\":");
        sb.append(System.getProperty("line.separator"));
        sb.append(rMessage());
        sb.append(".");
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }

}
