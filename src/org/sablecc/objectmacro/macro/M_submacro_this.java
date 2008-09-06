/* This file was generated by SableCC's ObjectMacro. */

package org.sablecc.objectmacro.macro;

import java.util.LinkedList;
import java.util.List;

public class M_submacro_this
        extends Macro {

    // ---- constructor ----

    M_submacro_this(
            Macro parent) {

        this.parent = parent;
    }

    // ---- parent ----

    private final Macro parent;

    @Override
    Macro get_parent() {

        return this.parent;
    }

    // ---- expands ----

    private final List<Macro> e_expand_19 = new LinkedList<Macro>();

    // ---- macro creators ----

    public M_submacro_comma new_submacro_comma() {

        M_submacro_comma result = new M_submacro_comma(this);
        this.e_expand_19.add(result);
        return result;
    }

    // ---- appendTo ----

    @Override
    public void appendTo(
            StringBuilder sb) {

        sb.append("this");

        if (this.e_expand_19.size() == 0) {
        }
        else {
            boolean first = true;
            for (Macro macro : this.e_expand_19) {
                if (first) {
                    first = false;
                }
                else {
                }
                macro.appendTo(sb);
            }
        }
    }

}