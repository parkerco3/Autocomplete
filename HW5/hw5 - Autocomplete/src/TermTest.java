import static org.junit.Assert.*;
import java.util.*;
import java.util.Collections;

import org.junit.Test;

public class TermTest {

    @Test
    public void constructorTest() {
        Term a = new Term("hello", 1);
        assertEquals(a.getTerm(), "hello");
        assertEquals(a.getWeight(), 1);
        a.setWeight(0);
        assertEquals(a.getWeight(), 0);
        a.setTerm("hi");
        assertEquals(a.getTerm(), "hi");
        assertNotNull(a.toString());
        
    }
    @Test 
    public void compareToTest() {
        Term a = new Term("hello", 1);
        Term b = new Term("help", 1);
        assertEquals(a.compareTo(b), -1);
        Term c = new Term("happy", 1);
        assertEquals(a.compareTo(c), 1);
        Term d = new Term("hell", 1);
        assertEquals(a.compareTo(d), 1);
        Term e = new Term("hello", 1);
        assertEquals(a.compareTo(e), 0);
        Term f = new Term("helloo", 1);
        assertEquals(a.compareTo(f), -1);
    }
    @Test
    public void comparatorTest() {
        ArrayList<Term> toSort = new ArrayList<>();
        Term a = new Term("hello", 1);
        Term b = new Term("help", 2);
        toSort.add(a);
        toSort.add(b);
        Collections.sort(toSort, ITerm.byReverseWeightOrder());
        assertEquals(toSort.get(0).getWeight(), 2);
    }
    @Test
    public void comparatorTest2() {
        ArrayList<Term> toSort = new ArrayList<>();
        Term a = new Term("hello", 2);
        Term b = new Term("help", 1);
        toSort.add(a);
        toSort.add(b);
        Collections.sort(toSort, ITerm.byReverseWeightOrder());
        assertEquals(toSort.get(0).getWeight(), 2);
    }
    @Test
    public void comparatorTest1() {
        ArrayList<Term> toSort = new ArrayList<>();
        Term a = new Term("hello", 1);
        Term b = new Term("help", 1);
        toSort.add(a);
        toSort.add(b);
        Collections.sort(toSort, ITerm.byReverseWeightOrder());
        assertEquals(toSort.get(0).getTerm(), "hello");
    }
    @Test
    public void prefixTest() {
        ArrayList<Term> toSort = new ArrayList<>();
        Term a = new Term("hello", 1);
        Term b = new Term("help", 1);
        toSort.add(a);
        toSort.add(b);
        Collections.sort(toSort, ITerm.byPrefixOrder(3));
        assertEquals(toSort.get(0).getTerm(), "hello");
    }
    @Test
    public void prefixTest1() {
        ArrayList<Term> toSort = new ArrayList<>();
        Term a = new Term("hello", 1);
        Term b = new Term("help", 1);
        toSort.add(a);
        toSort.add(b);
        Collections.sort(toSort, ITerm.byPrefixOrder(4));
        assertEquals(toSort.get(0).getTerm(), "hello");
    }
    @Test
    public void prefixTest2() {
        ArrayList<Term> toSort = new ArrayList<>();
        Term a = new Term("help", 1);
        Term b = new Term("hello", 1);
        toSort.add(a);
        toSort.add(b);
        Collections.sort(toSort, ITerm.byPrefixOrder(4));
        assertEquals(toSort.get(0).getTerm(), "hello");
    }
    @Test
    public void prefixTest3() {
        ArrayList<Term> toSort = new ArrayList<>();
        Term a = new Term("help", 1);
        Term b = new Term("hello", 1);
        toSort.add(a);
        toSort.add(b);
        Collections.sort(toSort, ITerm.byPrefixOrder(5));
        assertEquals(toSort.get(0).getTerm(), "hello");
    }
//    @Test (expected = IllegalArgumentException.class)
//    public void prefixTest3() {
//        ArrayList<Term> toSort = new ArrayList<>();
//        Term a = new Term("help", 1);
//        Term b = new Term("hello", 1);
//        toSort.add(a);
//        toSort.add(b);
//        Collections.sort(toSort, ITerm.byPrefixOrder(-1));
//    }
//    @Test (expected = IllegalArgumentException.class)
//    public void exceptionTest() {
//        new Term(null, 0);
//    }
//    @Test (expected = IllegalArgumentException.class)
//    public void exceptionTest2() {
//        new Term("hi", -1);
//    }

}
