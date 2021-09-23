import static org.junit.Assert.*;

import org.junit.Test;

public class AutocompleteTest {
    String root = "/autograder/submission/";
    //String root = "/Users/coreyparker/OneDrive - PennO365/School/Spring 2021/Classes/CIT594/HW5/";
    @Test
    public void test() {
        Autocomplete a = new Autocomplete();
        a.buildTrie(root + "pokemon.txt", 5);
        assertEquals(a.numberSuggestions(),5);
        assertEquals(a.countPrefixes(""), 729);
        assertEquals(a.countPrefixes(null), 0);
        assertNull(a.getSubTrie(null));
        assertNull(a.getSubTrie("~"));
        assertNull(a.getSubTrie("0"));
        assertNotNull(a.getSubTrie("a"));
        assertNotNull(a.getSubTrie("al"));
        a.addWord("~", 0);
        a.addWord("hi", -1);
        assertNotNull(a.getRoot());
        assertNotNull(a.getSuggestions("h").size());
        assertEquals(a.getSuggestions(null).size(),0);
        assertEquals(a.getSuggestions("?").size(),0);
    }
    //    @Test (expected = FileNotFoundException.class)
    //    public void exceptionTest() {
    //        Autocomplete a = new Autocomplete();
    //        a.buildTrie("hi", 0);
    //    }

}
