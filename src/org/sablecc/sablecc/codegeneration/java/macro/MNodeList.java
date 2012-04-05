/* This file was generated by SableCC's ObjectMacro. */

package org.sablecc.sablecc.codegeneration.java.macro;

import java.util.*;

public class MNodeList {

    private final List<Object> eDefaultPackage_SpecifiedPackage = new LinkedList<Object>();

    public MNodeList() {

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
        sb.append("import java.util.*;");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("public class NodeList<TNode extends Node>extends Node");
        sb.append(System.getProperty("line.separator"));
        sb.append("        implements Iterable<TNode> {");
        sb.append(System.getProperty("line.separator"));
        sb.append("	");
        sb.append(System.getProperty("line.separator"));
        sb.append("	int lowerBound;");
        sb.append(System.getProperty("line.separator"));
        sb.append("	int upperBound;");
        sb.append(System.getProperty("line.separator"));
        sb.append("	");
        sb.append(System.getProperty("line.separator"));
        sb.append("	int size = 0;");
        sb.append(System.getProperty("line.separator"));
        sb.append("	");
        sb.append(System.getProperty("line.separator"));
        sb.append("	private final int INF = -1;");
        sb.append(System.getProperty("line.separator"));
        sb.append("	");
        sb.append(System.getProperty("line.separator"));
        sb.append("	Entry<TNode> header = new Entry<TNode>(null,null,null);");
        sb.append(System.getProperty("line.separator"));
        sb.append("	");
        sb.append(System.getProperty("line.separator"));
        sb.append("	NodeList(){");
        sb.append(System.getProperty("line.separator"));
        sb.append("		this.header.next = this.header.previous = this.header;");
        sb.append(System.getProperty("line.separator"));
        sb.append("	}");
        sb.append(System.getProperty("line.separator"));
        sb.append("	");
        sb.append(System.getProperty("line.separator"));
        sb.append("	NodeList(int lowerBound){");
        sb.append(System.getProperty("line.separator"));
        sb.append("	    this.header.next = this.header.previous = this.header;");
        sb.append(System.getProperty("line.separator"));
        sb.append("		this.lowerBound = lowerBound;");
        sb.append(System.getProperty("line.separator"));
        sb.append("		this.upperBound = INF;");
        sb.append(System.getProperty("line.separator"));
        sb.append("	}");
        sb.append(System.getProperty("line.separator"));
        sb.append("	");
        sb.append(System.getProperty("line.separator"));
        sb.append("	NodeList(int lowerBound,int upperBound){");
        sb.append(System.getProperty("line.separator"));
        sb.append("	    this.header.next = this.header.previous = this.header;");
        sb.append(System.getProperty("line.separator"));
        sb.append("	    this.lowerBound = lowerBound;");
        sb.append(System.getProperty("line.separator"));
        sb.append("		this.upperBound = upperBound;");
        sb.append(System.getProperty("line.separator"));
        sb.append("	}");
        sb.append(System.getProperty("line.separator"));
        sb.append("	");
        sb.append(System.getProperty("line.separator"));
        sb.append("	void add(TNode element){");
        sb.append(System.getProperty("line.separator"));
        sb.append("		 Entry<TNode> newEntry = new Entry<TNode>(element, this.header, this.header.previous);");
        sb.append(System.getProperty("line.separator"));
        sb.append("		 newEntry.previous.next = newEntry;");
        sb.append(System.getProperty("line.separator"));
        sb.append("		 newEntry.next.previous = newEntry;");
        sb.append(System.getProperty("line.separator"));
        sb.append("		 size++;");
        sb.append(System.getProperty("line.separator"));
        sb.append("		 ");
        sb.append(System.getProperty("line.separator"));
        sb.append("		 //TODO Control list bounds");
        sb.append(System.getProperty("line.separator"));
        sb.append("	}");
        sb.append(System.getProperty("line.separator"));
        sb.append("	");
        sb.append(System.getProperty("line.separator"));
        sb.append("	void addAll(NodeList<TNode> elements){");
        sb.append(System.getProperty("line.separator"));
        sb.append("		this.header.previous.next = elements.header.next;");
        sb.append(System.getProperty("line.separator"));
        sb.append("		elements.header.next.previous = this.header.previous;");
        sb.append(System.getProperty("line.separator"));
        sb.append("		this.header.previous = elements.header.previous;");
        sb.append(System.getProperty("line.separator"));
        sb.append("		elements.header.previous.next = this.header;");
        sb.append(System.getProperty("line.separator"));
        sb.append("		this.size += elements.size;");
        sb.append(System.getProperty("line.separator"));
        sb.append("		");
        sb.append(System.getProperty("line.separator"));
        sb.append("		//TODO Control list bounds");
        sb.append(System.getProperty("line.separator"));
        sb.append("	}");
        sb.append(System.getProperty("line.separator"));
        sb.append("	");
        sb.append(System.getProperty("line.separator"));
        sb.append("	public int size(){");
        sb.append(System.getProperty("line.separator"));
        sb.append("	");
        sb.append(System.getProperty("line.separator"));
        sb.append("		return size;");
        sb.append(System.getProperty("line.separator"));
        sb.append("	}");
        sb.append(System.getProperty("line.separator"));
        sb.append("	");
        sb.append(System.getProperty("line.separator"));
        sb.append("	private static class Entry<E>{");
        sb.append(System.getProperty("line.separator"));
        sb.append("	");
        sb.append(System.getProperty("line.separator"));
        sb.append("	E element;");
        sb.append(System.getProperty("line.separator"));
        sb.append("	Entry<E> next;");
        sb.append(System.getProperty("line.separator"));
        sb.append("	Entry<E> previous;");
        sb.append(System.getProperty("line.separator"));
        sb.append("	");
        sb.append(System.getProperty("line.separator"));
        sb.append("		Entry(E element, Entry<E> next,Entry<E> previous) {");
        sb.append(System.getProperty("line.separator"));
        sb.append("			this.element = element;");
        sb.append(System.getProperty("line.separator"));
        sb.append("			this.next = next;");
        sb.append(System.getProperty("line.separator"));
        sb.append("			this.previous = previous;");
        sb.append(System.getProperty("line.separator"));
        sb.append("		");
        sb.append(System.getProperty("line.separator"));
        sb.append("		}");
        sb.append(System.getProperty("line.separator"));
        sb.append("	}");
        sb.append(System.getProperty("line.separator"));
        sb.append("	");
        sb.append(System.getProperty("line.separator"));
        sb.append(" @Override");
        sb.append(System.getProperty("line.separator"));
        sb.append("  public String getText() {");
        sb.append(System.getProperty("line.separator"));
        sb.append("  	return \"\";");
        sb.append(System.getProperty("line.separator"));
        sb.append("  }");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("  @Override");
        sb.append(System.getProperty("line.separator"));
        sb.append("  public int getLine() {");
        sb.append(System.getProperty("line.separator"));
        sb.append("    return 0;");
        sb.append(System.getProperty("line.separator"));
        sb.append("  }");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("  @Override");
        sb.append(System.getProperty("line.separator"));
        sb.append("  public int getPos() {");
        sb.append(System.getProperty("line.separator"));
        sb.append("    return 0;");
        sb.append(System.getProperty("line.separator"));
        sb.append("  }");
        sb.append(System.getProperty("line.separator"));
        sb.append("  ");
        sb.append(System.getProperty("line.separator"));
        sb.append("  @Override");
        sb.append(System.getProperty("line.separator"));
        sb.append("  public Type getType() {");
        sb.append(System.getProperty("line.separator"));
        sb.append("    return Type.TAnonymous;");
        sb.append(System.getProperty("line.separator"));
        sb.append("  }");
        sb.append(System.getProperty("line.separator"));
        sb.append("  ");
        sb.append(System.getProperty("line.separator"));
        sb.append("  @Override");
        sb.append(System.getProperty("line.separator"));
        sb.append("  public void applyOnChildren(Walker walker) {");
        sb.append(System.getProperty("line.separator"));
        sb.append("        for (TNode node : this) {");
        sb.append(System.getProperty("line.separator"));
        sb.append("            node.apply(walker);");
        sb.append(System.getProperty("line.separator"));
        sb.append("        }");
        sb.append(System.getProperty("line.separator"));
        sb.append("  }");
        sb.append(System.getProperty("line.separator"));
        sb.append("  ");
        sb.append(System.getProperty("line.separator"));
        sb.append("  @Override");
        sb.append(System.getProperty("line.separator"));
        sb.append("  public void apply(Walker walker){");
        sb.append(System.getProperty("line.separator"));
        sb.append("    this.applyOnChildren(walker);");
        sb.append(System.getProperty("line.separator"));
        sb.append("  }");
        sb.append(System.getProperty("line.separator"));
        sb.append("  ");
        sb.append(System.getProperty("line.separator"));
        sb.append("  @Override");
        sb.append(System.getProperty("line.separator"));
        sb.append("  public Iterator<TNode> iterator(){");
        sb.append(System.getProperty("line.separator"));
        sb.append("  	return new NodeListItr();");
        sb.append(System.getProperty("line.separator"));
        sb.append("  }");
        sb.append(System.getProperty("line.separator"));
        sb.append("  ");
        sb.append(System.getProperty("line.separator"));
        sb.append("  private class NodeListItr");
        sb.append(System.getProperty("line.separator"));
        sb.append("            implements Iterator<TNode> {");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("    private int cursor = 0;");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("    private Entry<TNode> current;");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("    NodeListItr() {");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("         this.current = NodeList.this.header;");
        sb.append(System.getProperty("line.separator"));
        sb.append("    }");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("    @Override");
        sb.append(System.getProperty("line.separator"));
        sb.append("    public boolean hasNext() {");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("        return this.cursor != size();");
        sb.append(System.getProperty("line.separator"));
        sb.append("    }");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("    @Override");
        sb.append(System.getProperty("line.separator"));
        sb.append("    public TNode next() {");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("        this.cursor++;");
        sb.append(System.getProperty("line.separator"));
        sb.append("        current = this.current.next;");
        sb.append(System.getProperty("line.separator"));
        sb.append("        return this.current.next.element;");
        sb.append(System.getProperty("line.separator"));
        sb.append("    }");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("    @Override");
        sb.append(System.getProperty("line.separator"));
        sb.append("    public void remove() {");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("    }");
        sb.append(System.getProperty("line.separator"));
        sb.append("  }");
        sb.append(System.getProperty("line.separator"));
        sb.append("  ");
        sb.append(System.getProperty("line.separator"));
        sb.append("}");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }

}
