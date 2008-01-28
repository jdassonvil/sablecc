/* This file is part of SableCC ( http://sablecc.org ).
 *
 * See the NOTICE file distributed with this work for copyright information.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sablecc.sablecc.alphabet;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.sablecc.sablecc.exception.InternalException;

/**
 * An alphabet is a set of symbols. Two symbols of an alphabet may not contain
 * intersecting intervals.
 */

public class Alphabet {

    /**
     * The sorted set of symbols of this alphabet. Includes the complement
     * symbol, if present.
     */
    private SortedSet<Symbol> symbols;

    /**
     * The complement symbol of this alphabet. Is <code>null</code> when the
     * alphabet does not contain a complement symbol.
     */
    private Symbol complementSymbol;

    /**
     * A mapping from each interval contained in a symbol of this alphabet to
     * its symbol. The implicit intervals of the complement symbol are not
     * mapped.
     */
    private SortedMap<Interval, Symbol> intervalToSymbolMap;

    /**
     * The cached string representation of this alphabet. It is
     * <code>null</code> when not yet computed.
     */
    private String toString;

    /**
     * Constructs an alphabet with the provided collection of symbols.
     */
    public Alphabet(
            Collection<Symbol> symbols) {

        if (symbols == null) {
            throw new InternalException("symbols may not be null");
        }

        init(symbols);
    }

    /**
     * Constructs an alphabet with the provided symbol.
     */
    public Alphabet(
            Symbol symbol) {

        if (symbol == null) {
            throw new InternalException("symbol may not be null");
        }

        Collection<Symbol> symbols = new LinkedList<Symbol>();
        symbols.add(symbol);

        init(symbols);
    }

    /**
     * Constructs an alphabet with the provided interval.
     */
    public Alphabet(
            Interval interval) {

        if (interval == null) {
            throw new InternalException("interval may not be null");
        }

        Collection<Symbol> symbols = new LinkedList<Symbol>();
        symbols.add(new Symbol(interval));

        init(symbols);
    }

    /**
     * Constructs an alphabet with the provided bound.
     */
    public Alphabet(
            Bound bound) {

        if (bound == null) {
            throw new InternalException("bound may not be null");
        }

        Collection<Symbol> symbols = new LinkedList<Symbol>();
        symbols.add(new Symbol(bound));

        init(symbols);
    }

    /**
     * Constructs an empty alphabet.
     */
    public Alphabet() {

        Collection<Symbol> symbols = new LinkedList<Symbol>();

        init(symbols);
    }

    /**
     * Initializes this alphabet using the provided collection of symbols. This
     * method must be called by all constructors. It fills the
     * <code>symbols</code> and <code>intervalToSymbolMap</code> instance
     * variables and detects intersecting intervals.
     */
    private void init(
            Collection<Symbol> symbols) {

        this.symbols = Collections.unmodifiableSortedSet(new TreeSet<Symbol>(
                symbols));

        // compute interval map
        TreeMap<Interval, Symbol> intervalMap = new TreeMap<Interval, Symbol>();

        for (Symbol symbol : symbols) {
            if (symbol == null) {
                throw new InternalException("symbol may not be null");
            }

            if (symbol.isComplement()) {

                if (this.complementSymbol != null) {
                    throw new InternalException(
                            "alphabet may not contain multiple complement symbols");
                }

                this.complementSymbol = symbol;
                continue;
            }

            for (Interval interval : symbol.getIntervals()) {
                if (intervalMap.put(interval, symbol) != null) {
                    throw new InternalException(
                            "distinct symbols may not have equal intervals");
                }
            }
        }

        this.intervalToSymbolMap = Collections
                .unmodifiableSortedMap(intervalMap);

        // check for intersecting intervals
        Interval previous = null;
        for (Interval current : this.intervalToSymbolMap.keySet()) {
            if (previous != null && previous.intersects(current)) {
                throw new InternalException(
                        "distinct symbols may not have intersecting intervals");
            }

            previous = current;
        }
    }

    /**
     * Returns the set of symbols of this alphabet.
     */
    public SortedSet<Symbol> getSymbols() {

        return this.symbols;
    }

    /**
     * Returns the complement symbol of this alphabet.
     */
    public Symbol getComplementSymbol() {

        if (this.complementSymbol == null) {
            throw new InternalException(
                    "this alphabet does not contain a complement symbol");
        }

        return this.complementSymbol;
    }

    /**
     * Returns a mapping from each interval contained in a symbol of this
     * alphabet to its symbol. The implicit intervals of the complement symbol
     * are not mapped.the interval to symbol map of this alphabet.
     */
    public SortedMap<Interval, Symbol> getIntervalToSymbolMap() {

        return this.intervalToSymbolMap;
    }

    /**
     * Returns the string representation of this alphabet.
     */
    @Override
    public String toString() {

        if (this.toString == null) {
            StringBuilder sb = new StringBuilder();

            sb.append("Alphabet:{ ");

            boolean first = true;
            for (Symbol symbol : this.symbols) {
                if (first) {
                    first = false;
                }
                else {
                    sb.append(", ");
                }

                sb.append(symbol);
            }

            sb.append(" }");

            this.toString = sb.toString();
        }

        return this.toString;
    }

    /**
     * Returns true when this alphabet contains a complement symbol.
     */
    public boolean containsComplementSymbol() {

        return this.complementSymbol != null;
    }

    /**
     * Merges this alphabet with the provided one. Merging two alphabets
     * <code>A</code> and <code>B</code> consists of creating a new alphabet
     * <code>C</code> containing a minimal number of symbols with the
     * following property: For every symbol <code>x</code> element of
     * <code>(A union B)</code> there exists a corresponding subset
     * <code>S</code> of <code>C</code> such that:
     * <code>merge(S) == x</code>.
     */
    public AlphabetMergeResult mergeWith(
            Alphabet alphabet) {

        if (alphabet == null) {
            throw new InternalException("alphabet may not be null");
        }

        // no need to really merge, when merging with self
        if (alphabet == this) {
            return new AlphabetMergeResult(this);
        }

        /*
         * In theoretical terms, an alphabet is a set of symbols.
         * 
         * Merging two alphabets A and B consists of creating a new alphabet C
         * containing a minimal number of symbols, with the following property:
         * 
         * For every symbol x element of (A union B), there exists a
         * corresponding subset S of C, such that: merge(S) == x.
         * 
         * As a direct consequence, every new symbol w element of C is related
         * to a pair (x,y) where x is element of (A union {null}) and y is
         * element of (B union {null}).
         * 
         * Our algorithm proceeds by finding these pairs to identify the symbols
         * of the new alphabet.
         * 
         */

        // First, we compute a map of (symbol pair,interval set)
        Map<SymbolPair, SortedSet<Interval>> symbolPairToIntervalSetMap = computeSymbolPairToIntervalSetMap(
                this, alphabet);

        // list of new symbols
        Collection<Symbol> newSymbols = new LinkedList<Symbol>();

        // SortedMaps to map old symbols to sets of new symbols
        SortedMap<Symbol, SortedSet<Symbol>> alphabet1SymbolMap = new TreeMap<Symbol, SortedSet<Symbol>>();
        SortedMap<Symbol, SortedSet<Symbol>> alphabet2SymbolMap = new TreeMap<Symbol, SortedSet<Symbol>>();

        // if either alphabets contains a complement symbol, the new alphabet
        // will contain one.
        Symbol complementSymbol;

        if (containsComplementSymbol() || alphabet.containsComplementSymbol()) {
            complementSymbol = new Symbol();

            newSymbols.add(complementSymbol);

            if (containsComplementSymbol()) {

                SortedSet<Symbol> collection = new TreeSet<Symbol>();
                collection.add(complementSymbol);
                alphabet1SymbolMap.put(this.complementSymbol, collection);
            }

            if (alphabet.containsComplementSymbol()) {

                SortedSet<Symbol> collection = new TreeSet<Symbol>();
                collection.add(complementSymbol);
                alphabet2SymbolMap.put(alphabet.complementSymbol, collection);
            }
        }
        else {
            complementSymbol = null;
        }

        for (Map.Entry<SymbolPair, SortedSet<Interval>> entry : symbolPairToIntervalSetMap
                .entrySet()) {

            Symbol oldSymbol1 = entry.getKey().getSymbol1();
            Symbol oldSymbol2 = entry.getKey().getSymbol2();

            // if no old (non-complement) symbol matches, don't create a symbol
            if (oldSymbol1 == null && oldSymbol2 == null) {
                continue;
            }

            // we can make a new symbol that relates to the pair
            Symbol newSymbol = new Symbol(entry.getValue());

            newSymbols.add(newSymbol);

            // we add the associations in the (old symbol, set of new symbols)
            // maps

            if (oldSymbol1 != null) {
                SortedSet<Symbol> collection = alphabet1SymbolMap
                        .get(oldSymbol1);

                if (collection == null) {
                    collection = new TreeSet<Symbol>();
                    alphabet1SymbolMap.put(oldSymbol1, collection);
                }

                collection.add(newSymbol);
            }
            else if (containsComplementSymbol()) {
                SortedSet<Symbol> collection = alphabet1SymbolMap
                        .get(this.complementSymbol);
                collection.add(newSymbol);
            }

            if (oldSymbol2 != null) {
                SortedSet<Symbol> collection = alphabet2SymbolMap
                        .get(oldSymbol2);

                if (collection == null) {
                    collection = new TreeSet<Symbol>();
                    alphabet2SymbolMap.put(oldSymbol2, collection);
                }

                collection.add(newSymbol);
            }
            else if (alphabet.containsComplementSymbol()) {
                SortedSet<Symbol> collection = alphabet2SymbolMap
                        .get(alphabet.complementSymbol);
                collection.add(newSymbol);
            }
        }

        return new AlphabetMergeResult(new Alphabet(newSymbols), this,
                alphabet1SymbolMap, alphabet, alphabet2SymbolMap);
    }

    /**
     * Computes a <code>Map</code> that maps each symbol pair
     * <code>(x,y)</code> to a set of shared intervals, where <code>x</code>
     * is a symbol of <code>alphabet1</code> or <code>null</code>, and
     * <code>y</code> is a symbol of <code>alphabet2</code> or
     * <code>null</code>.
     * <p>
     * This methods does not explicitly take into account complement symbols,
     * but a <code>null</code> symbol, in a symbol pair, represents complement
     * symbol.
     * <p>
     * The particular property of this implementation is that it does so in
     * linear time by only mapping pairs that have a non-empty shared interval
     * set. The intuitive algorithm would have analyzed all possible pairs,
     * leading to quadratic running time.
     */
    private static Map<SymbolPair, SortedSet<Interval>> computeSymbolPairToIntervalSetMap(
            Alphabet alphabet1,
            Alphabet alphabet2) {

        Map<SymbolPair, SortedSet<Interval>> symbolPairToIntervalSetMap = new LinkedHashMap<SymbolPair, SortedSet<Interval>>();

        /*
         * We find all intervals of new symbols by analyzing the space starting
         * from the smallest lower bound of an interval to the highest upper
         * bound.
         */

        // currently analyzed sorted map entries
        Map.Entry<Interval, Symbol> entry1 = null;
        Map.Entry<Interval, Symbol> entry2 = null;

        // iterators
        Iterator<Map.Entry<Interval, Symbol>> i1 = alphabet1.intervalToSymbolMap
                .entrySet().iterator();
        Iterator<Map.Entry<Interval, Symbol>> i2 = alphabet2.intervalToSymbolMap
                .entrySet().iterator();

        Bound lastUpperBound = null;

        while (entry1 != null || entry2 != null || i1.hasNext() || i2.hasNext()) {
            // if possible, make sure that entry1 and entry2 are filled
            if (entry1 == null && i1.hasNext()) {
                entry1 = i1.next();
            }

            if (entry2 == null && i2.hasNext()) {
                entry2 = i2.next();
            }

            // Compute the lower bound of the new interval
            Bound lowerBound;

            if (lastUpperBound == null) {
                // On the first iteration we need to apply a special treatment

                // We pick the smallest lower bound
                if (entry1 == null) {
                    lowerBound = entry2.getKey().getLowerBound();
                }
                else if (entry2 == null) {
                    lowerBound = entry1.getKey().getLowerBound();
                }
                else {
                    lowerBound = Bound.min(entry1.getKey().getLowerBound(),
                            entry2.getKey().getLowerBound());
                }
            }
            else {
                lowerBound = lastUpperBound.getSuccessor();
            }

            // compute the upper bound of the new interval
            Bound upperBound;

            {
                Bound upperBoundCandidate1 = null;
                Bound upperBoundCandidate2 = null;

                if (entry1 != null) {
                    if (lowerBound.compareTo(entry1.getKey().getLowerBound()) < 0) {
                        upperBoundCandidate1 = entry1.getKey().getLowerBound()
                                .getPredecessor();
                    }
                    else {
                        upperBoundCandidate1 = entry1.getKey().getUpperBound();
                    }
                }

                if (entry2 != null) {
                    if (lowerBound.compareTo(entry2.getKey().getLowerBound()) < 0) {
                        upperBoundCandidate2 = entry2.getKey().getLowerBound()
                                .getPredecessor();
                    }
                    else {
                        upperBoundCandidate2 = entry2.getKey().getUpperBound();
                    }
                }

                if (upperBoundCandidate1 == null) {
                    upperBound = upperBoundCandidate2;
                }
                else if (upperBoundCandidate2 == null) {
                    upperBound = upperBoundCandidate1;
                }
                else {
                    upperBound = Bound.min(upperBoundCandidate1,
                            upperBoundCandidate2);
                }
            }

            // create new interval, and related symbol pair
            Interval newInterval = new Interval(lowerBound, upperBound);

            Symbol symbol1;
            if (entry1 != null && newInterval.intersects(entry1.getKey())) {
                symbol1 = entry1.getValue();
            }
            else {
                symbol1 = null;
            }

            Symbol symbol2;
            if (entry2 != null && newInterval.intersects(entry2.getKey())) {
                symbol2 = entry2.getValue();
            }
            else {
                symbol2 = null;
            }

            SymbolPair symbolPair = new SymbolPair(symbol1, symbol2);

            // add interval in (symbol pair,interval set) map
            SortedSet<Interval> intervalSet = symbolPairToIntervalSetMap
                    .get(symbolPair);
            if (intervalSet == null) {
                intervalSet = new TreeSet<Interval>();
                symbolPairToIntervalSetMap.put(symbolPair, intervalSet);
            }

            intervalSet.add(newInterval);

            // save last upper bound
            lastUpperBound = upperBound;

            // update current entries
            if (entry1 != null
                    && lastUpperBound
                            .compareTo(entry1.getKey().getUpperBound()) >= 0) {
                entry1 = null;
            }
            if (entry2 != null
                    && lastUpperBound
                            .compareTo(entry2.getKey().getUpperBound()) >= 0) {
                entry2 = null;
            }
        }

        return symbolPairToIntervalSetMap;
    }
}
