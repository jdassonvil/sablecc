/* This file was generated by SableCC's ObjectMacro. */

package org.sablecc.sablecc.codegeneration.java.macro;

import java.util.*;

public class MAlternative {

    private final String pName;

    private final MAlternative mAlternative = this;

    private final List<Object> eDefaultPackage_SpecifiedPackage = new LinkedList<Object>();

    private final List<Object> ePublic = new LinkedList<Object>();

    private final List<Object> eAlternativeNamedParent_AlternativeNodeParent = new LinkedList<Object>();

    private final List<Object> eNormalElementDeclaration_ListElementDeclaration_EndElementDeclaration = new LinkedList<Object>();

    private final List<Object> eNormalChildApply_EndChildApply = new LinkedList<Object>();

    private final List<Object> eNormalConstructorParameter_ListConstructorParameter_EndConstructorParameter = new LinkedList<Object>();

    private final List<Object> ePublicElementAccessor = new LinkedList<Object>();

    private final List<Object> eNormalElementAccessor_EndElementAccessor = new LinkedList<Object>();

    private final List<Object> eAltNormalApply = new LinkedList<Object>();

    private final List<Object> eAltAnonymousApply = new LinkedList<Object>();

    private final List<Object> eNormalContructorInitialization = new LinkedList<Object>();

    private final List<Object> eEndContructorInitialization = new LinkedList<Object>();

    private final List<Object> eNamedAltType = new LinkedList<Object>();

    private final List<Object> eAnonymousAltType = new LinkedList<Object>();

    private final List<Object> eListElementAccessor = new LinkedList<Object>();

    public MAlternative(
            String pName) {

        if (pName == null) {
            throw new NullPointerException();
        }
        this.pName = pName;
    }

    public MAltNormalApply newAltNormalApply() {

        MAltNormalApply lAltNormalApply = new MAltNormalApply(this.mAlternative);
        this.eAltNormalApply.add(lAltNormalApply);
        return lAltNormalApply;
    }

    public MAltAnonymousApply newAltAnonymousApply() {

        MAltAnonymousApply lAltAnonymousApply = new MAltAnonymousApply();
        this.eAltAnonymousApply.add(lAltAnonymousApply);
        return lAltAnonymousApply;
    }

    public MNormalContructorInitialization newNormalContructorInitialization(
            String pElementName) {

        MNormalContructorInitialization lNormalContructorInitialization = new MNormalContructorInitialization(
                pElementName);
        this.eNormalContructorInitialization
                .add(lNormalContructorInitialization);
        return lNormalContructorInitialization;
    }

    public MEndContructorInitialization newEndContructorInitialization() {

        MEndContructorInitialization lEndContructorInitialization = new MEndContructorInitialization();
        this.eEndContructorInitialization.add(lEndContructorInitialization);
        return lEndContructorInitialization;
    }

    public MNamedAltType newNamedAltType() {

        MNamedAltType lNamedAltType = new MNamedAltType(this.mAlternative);
        this.eNamedAltType.add(lNamedAltType);
        return lNamedAltType;
    }

    public MAnonymousAltType newAnonymousAltType() {

        MAnonymousAltType lAnonymousAltType = new MAnonymousAltType();
        this.eAnonymousAltType.add(lAnonymousAltType);
        return lAnonymousAltType;
    }

    public MListElementAccessor newListElementAccessor(
            String pListType,
            String pElementName) {

        MListElementAccessor lListElementAccessor = new MListElementAccessor(
                pListType, pElementName);
        this.eListElementAccessor.add(lListElementAccessor);
        return lListElementAccessor;
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

    public MPublic newPublic() {

        MPublic lPublic = new MPublic();
        this.ePublic.add(lPublic);
        return lPublic;
    }

    public MAlternativeNamedParent newAlternativeNamedParent(
            String pParent) {

        MAlternativeNamedParent lAlternativeNamedParent = new MAlternativeNamedParent(
                pParent);
        this.eAlternativeNamedParent_AlternativeNodeParent
                .add(lAlternativeNamedParent);
        return lAlternativeNamedParent;
    }

    public MAlternativeNodeParent newAlternativeNodeParent() {

        MAlternativeNodeParent lAlternativeNodeParent = new MAlternativeNodeParent();
        this.eAlternativeNamedParent_AlternativeNodeParent
                .add(lAlternativeNodeParent);
        return lAlternativeNodeParent;
    }

    public MNormalElementDeclaration newNormalElementDeclaration(
            String pElementType,
            String pElementName) {

        MNormalElementDeclaration lNormalElementDeclaration = new MNormalElementDeclaration(
                pElementType, pElementName);
        this.eNormalElementDeclaration_ListElementDeclaration_EndElementDeclaration
                .add(lNormalElementDeclaration);
        return lNormalElementDeclaration;
    }

    public MListElementDeclaration newListElementDeclaration(
            String pListType,
            String pElementName) {

        MListElementDeclaration lListElementDeclaration = new MListElementDeclaration(
                pListType, pElementName);
        this.eNormalElementDeclaration_ListElementDeclaration_EndElementDeclaration
                .add(lListElementDeclaration);
        return lListElementDeclaration;
    }

    public MEndElementDeclaration newEndElementDeclaration() {

        MEndElementDeclaration lEndElementDeclaration = new MEndElementDeclaration();
        this.eNormalElementDeclaration_ListElementDeclaration_EndElementDeclaration
                .add(lEndElementDeclaration);
        return lEndElementDeclaration;
    }

    public MNormalChildApply newNormalChildApply(
            String pElementName) {

        MNormalChildApply lNormalChildApply = new MNormalChildApply(
                pElementName);
        this.eNormalChildApply_EndChildApply.add(lNormalChildApply);
        return lNormalChildApply;
    }

    public MEndChildApply newEndChildApply() {

        MEndChildApply lEndChildApply = new MEndChildApply();
        this.eNormalChildApply_EndChildApply.add(lEndChildApply);
        return lEndChildApply;
    }

    public MNormalConstructorParameter newNormalConstructorParameter(
            String pElementType,
            String pElementName) {

        MNormalConstructorParameter lNormalConstructorParameter = new MNormalConstructorParameter(
                pElementType, pElementName);
        this.eNormalConstructorParameter_ListConstructorParameter_EndConstructorParameter
                .add(lNormalConstructorParameter);
        return lNormalConstructorParameter;
    }

    public MListConstructorParameter newListConstructorParameter(
            String pListType,
            String pElementName) {

        MListConstructorParameter lListConstructorParameter = new MListConstructorParameter(
                pListType, pElementName);
        this.eNormalConstructorParameter_ListConstructorParameter_EndConstructorParameter
                .add(lListConstructorParameter);
        return lListConstructorParameter;
    }

    public MEndConstructorParameter newEndConstructorParameter() {

        MEndConstructorParameter lEndConstructorParameter = new MEndConstructorParameter();
        this.eNormalConstructorParameter_ListConstructorParameter_EndConstructorParameter
                .add(lEndConstructorParameter);
        return lEndConstructorParameter;
    }

    public MPublicElementAccessor newPublicElementAccessor(
            String pElementName) {

        MPublicElementAccessor lPublicElementAccessor = new MPublicElementAccessor(
                pElementName);
        this.ePublicElementAccessor.add(lPublicElementAccessor);
        return lPublicElementAccessor;
    }

    public MNormalElementAccessor newNormalElementAccessor(
            String pElementType,
            String pElementName) {

        MNormalElementAccessor lNormalElementAccessor = new MNormalElementAccessor(
                pElementType, pElementName);
        this.eNormalElementAccessor_EndElementAccessor
                .add(lNormalElementAccessor);
        return lNormalElementAccessor;
    }

    public MEndElementAccessor newEndElementAccessor() {

        MEndElementAccessor lEndElementAccessor = new MEndElementAccessor();
        this.eNormalElementAccessor_EndElementAccessor.add(lEndElementAccessor);
        return lEndElementAccessor;
    }

    String pName() {

        return this.pName;
    }

    private String rName() {

        return this.mAlternative.pName();
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
        for (Object oPublic : this.ePublic) {
            sb.append(oPublic.toString());
        }
        sb.append("class N");
        sb.append(rName());
        sb.append(System.getProperty("line.separator"));
        sb.append("    extends ");
        for (Object oAlternativeNamedParent_AlternativeNodeParent : this.eAlternativeNamedParent_AlternativeNodeParent) {
            sb.append(oAlternativeNamedParent_AlternativeNodeParent.toString());
        }
        sb.append(" {");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("  private final int line;");
        sb.append(System.getProperty("line.separator"));
        sb.append("  private final int pos;");
        sb.append(System.getProperty("line.separator"));
        for (Object oNormalElementDeclaration_ListElementDeclaration_EndElementDeclaration : this.eNormalElementDeclaration_ListElementDeclaration_EndElementDeclaration) {
            sb.append(oNormalElementDeclaration_ListElementDeclaration_EndElementDeclaration
                    .toString());
        }
        sb.append(System.getProperty("line.separator"));
        sb.append("  @Override");
        sb.append(System.getProperty("line.separator"));
        sb.append("  public int getLine() {");
        sb.append(System.getProperty("line.separator"));
        sb.append("    return this.line;");
        sb.append(System.getProperty("line.separator"));
        sb.append("  }");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("  @Override");
        sb.append(System.getProperty("line.separator"));
        sb.append("  public int getPos() {");
        sb.append(System.getProperty("line.separator"));
        sb.append("    return this.pos;");
        sb.append(System.getProperty("line.separator"));
        sb.append("  }");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("  @Override");
        sb.append(System.getProperty("line.separator"));
        sb.append("  public String getText() {");
        sb.append(System.getProperty("line.separator"));
        sb.append("    return null;");
        sb.append(System.getProperty("line.separator"));
        sb.append("  }");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("  @Override");
        sb.append(System.getProperty("line.separator"));
        sb.append("  public void apply(Walker walker) {");
        sb.append(System.getProperty("line.separator"));
        for (Object oAltNormalApply : this.eAltNormalApply) {
            sb.append(oAltNormalApply.toString());
        }
        for (Object oAltAnonymousApply : this.eAltAnonymousApply) {
            sb.append(oAltAnonymousApply.toString());
        }
        sb.append("  }");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("  @Override");
        sb.append(System.getProperty("line.separator"));
        sb.append("  public void applyOnChildren(Walker walker) {");
        sb.append(System.getProperty("line.separator"));
        for (Object oNormalChildApply_EndChildApply : this.eNormalChildApply_EndChildApply) {
            sb.append(oNormalChildApply_EndChildApply.toString());
        }
        sb.append("  }");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("  N");
        sb.append(rName());
        sb.append("(int line, int pos");
        if (this.eNormalConstructorParameter_ListConstructorParameter_EndConstructorParameter
                .size() > 0) {
            sb.append(", ");
        }
        {
            boolean first = true;
            for (Object oNormalConstructorParameter_ListConstructorParameter_EndConstructorParameter : this.eNormalConstructorParameter_ListConstructorParameter_EndConstructorParameter) {
                if (first) {
                    first = false;
                }
                else {
                    sb.append(", ");
                }
                sb.append(oNormalConstructorParameter_ListConstructorParameter_EndConstructorParameter
                        .toString());
            }
        }
        sb.append(") {");
        sb.append(System.getProperty("line.separator"));
        sb.append("    this.line = line;");
        sb.append(System.getProperty("line.separator"));
        sb.append("    this.pos = pos;");
        sb.append(System.getProperty("line.separator"));
        for (Object oNormalContructorInitialization : this.eNormalContructorInitialization) {
            sb.append(oNormalContructorInitialization.toString());
        }
        for (Object oEndContructorInitialization : this.eEndContructorInitialization) {
            sb.append(oEndContructorInitialization.toString());
        }
        sb.append("  }");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("  @Override");
        sb.append(System.getProperty("line.separator"));
        sb.append("  public Type getType() {");
        sb.append(System.getProperty("line.separator"));
        for (Object oNamedAltType : this.eNamedAltType) {
            sb.append(oNamedAltType.toString());
        }
        for (Object oAnonymousAltType : this.eAnonymousAltType) {
            sb.append(oAnonymousAltType.toString());
        }
        sb.append("  }");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        if (this.ePublicElementAccessor.size() > 0) {
            sb.append(System.getProperty("line.separator"));
        }
        {
            boolean first = true;
            for (Object oPublicElementAccessor : this.ePublicElementAccessor) {
                if (first) {
                    first = false;
                }
                else {
                    sb.append(System.getProperty("line.separator"));
                }
                sb.append(oPublicElementAccessor.toString());
            }
        }
        if (this.eNormalElementAccessor_EndElementAccessor.size() > 0) {
            sb.append(System.getProperty("line.separator"));
        }
        {
            boolean first = true;
            for (Object oNormalElementAccessor_EndElementAccessor : this.eNormalElementAccessor_EndElementAccessor) {
                if (first) {
                    first = false;
                }
                else {
                    sb.append(System.getProperty("line.separator"));
                }
                sb.append(oNormalElementAccessor_EndElementAccessor.toString());
            }
        }
        for (Object oListElementAccessor : this.eListElementAccessor) {
            sb.append(oListElementAccessor.toString());
        }
        sb.append("}");
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }

}
