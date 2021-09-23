import static org.junit.Assert.*;

import org.junit.Test;

public class NodeTest {

    @Test
    public void nodeTest() {
        Node b = new Node();
        Node a = new Node("hello", 1);
        assertNotNull(a.getTerm());
        assertEquals(a.getReferences().length, 26);
        a.setWords(0);
        assertEquals(a.getWords(), 0);
        a.setPrefixes(10);
        assertEquals(a.getPrefixes(), 10);
        a.setReferences(null);
        assertNull(a.getReferences());
        a.setTerm(null);
        assertNull(a.getTerm());
        
    }
//    @Test (expected = IllegalArgumentException.class)
//    public void exceptionTest() {
//        new Node(null, 0);
//    }
//    @Test (expected = IllegalArgumentException.class)
//    public void exceptionTest2() {
//        new Node("hi", -1);
//    }

}
