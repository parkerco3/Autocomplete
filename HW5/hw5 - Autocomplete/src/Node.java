
/**
 * ==== Attributes ====
 * - words: number of words
 * - term: the ITerm object
 * - prefixes: number of prefixes 
 * - references: Array of references to next/children Nodes
 * 
 * ==== Constructor ====
 * Node(String word, long weight)
 * 
 * @author Corey A. Parker
 */
public class Node {
    private Term term;
    private int words;
    private int prefixes;
    protected Node[] references;

    public Node() {
        term = null; 
        words = 0;
        prefixes = 0;
        references = new Node[26];
        for (int i = 0; i < 26; i++) {
            references[i] = null;
        }
    }

    public Node(String query, long weight) {
        if (query == null || weight < 0) {
            throw new IllegalArgumentException();
        } else {
            term = new Term(query, weight);
            words = 0;
            prefixes = 0;
            references = new Node[26];
            for (int i = 0; i < 26; i++) {
                references[i] = null;
            }
        }
    }

    public Term getTerm() {
        return term;
    }
    public void setTerm(Term term) {
        this.term = term;
       // return this.term;
    }
    public int getWords() {
        return words;
    }
    public void setWords(int words) {
        this.words = words;
        //return this.words;
    }
    public int getPrefixes() {
        return prefixes;
    }
    public void setPrefixes(int prefixes) {
        this.prefixes = prefixes;
      //  return this.prefixes;
    }
    public Node[] getReferences() {
        Node[] toReturn = references;
        return toReturn;
    }
    public void setReferences(Node[] references) {
        this.references = references;
        //return this.references;
    }

}
