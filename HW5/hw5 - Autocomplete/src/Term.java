
public class Term implements ITerm {

    private String query;
    private long weight;


    public Term(String query, long weight) {
        if (query == null || weight < 0) {
            throw new IllegalArgumentException();
        } else {
            this.query = query;
            this.weight = weight;
        }
    }

    @Override
    public int compareTo(ITerm that) {
        int minLength = 0;
        if (this.query.length() <= ((Term) that).getTerm().length()) {
            minLength = this.query.length();
        } else {
            minLength = ((Term) that).getTerm().length(); 
        }
        for (int i = 0; i < minLength; i++) {
            if (this.query.charAt(i) < ((Term) that).getTerm().charAt(i)) {
                return -1;
            } 
            if (this.query.charAt(i) > ((Term) that).getTerm().charAt(i)) {
                return 1;
            } 
        }
        if (this.query.length() == ((Term) that).getTerm().length()) {
            return 0;
        } else if (this.query.length() < ((Term) that).getTerm().length()) {
            return -1;
        } else {
            return 1;  
        }
    }
    @Override
    public String toString() {
        String convert = weight + "\t" + query;
        return convert;
    }

    public String getTerm() {
        return query;
    }
    public void setTerm(String query) {
        this.query = query;
        //return this.query;
    }

    public long getWeight() {
        return weight;
    }
    public void setWeight(long weight) {
        this.weight = weight;
        //return this.weight;
    }
    

}
