import java.util.Comparator;

/**
 * @author ericfouh
 */
public interface ITerm extends Comparable<ITerm> {

    /**
     * Compares the two terms in descending order by weight.
     * 
     * @return comparator Object
     */
    public static Comparator<ITerm> byReverseWeightOrder() {
        Comparator<ITerm> comp = new Comparator<ITerm>() {
            public int compare(ITerm term1, ITerm that) {
                if (((Term) term1).getWeight() < ((Term) that).getWeight()) {
                    return 1;
                }
                if (((Term) term1).getWeight() > ((Term) that).getWeight()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        return comp;
    }


    /**
     * Compares the two terms in lexicographic order but using only the first r
     * characters of each query.
     * 
     * @param r
     * @return comparator Object
     */
    public static Comparator<ITerm> byPrefixOrder(int r) {
        if (r < 0) {
            throw new IllegalArgumentException();
        }
        Comparator<ITerm> comp = new Comparator<ITerm>() {
            public int compare(ITerm term1, ITerm that) {
                int smallerLength = 0;
                int max = r;
                if (((Term) term1).getTerm().length() > ((Term) that).getTerm().length()) {
                    smallerLength = ((Term) that).getTerm().length();
                }
                if (((Term) term1).getTerm().length() <= ((Term) that).getTerm().length()) {
                    smallerLength = ((Term) term1).getTerm().length();
                }
                if (r > smallerLength) {
                    max = smallerLength; 
                }
                for (int i = 0; i < max; i++) {
                    if (((Term) term1).getTerm().charAt(i) < ((Term) that).getTerm().charAt(i)) {
                        return -1;
                    } 
                    if (((Term) term1).getTerm().charAt(i) > ((Term) that).getTerm().charAt(i)) {
                        return 1;
                    }
                }
                return 0;
            }
        };
        return comp;
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(ITerm that);


    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString();

}
