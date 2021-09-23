import java.io.*;
import java.util.*;

public class Autocomplete implements IAutocomplete {
    private int numSuggestions;
    private Node root;



    public Autocomplete() {
        root = new Node("",0);
    }

    @Override
    public void addWord(String word, long weight) {
        boolean isOk = true;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) < 'a' || word.charAt(i) > 'z') {
                isOk = false;
            }
        }
        if (weight < 0) {
            isOk = false;
        }
        if (isOk) {
            root.setPrefixes(root.getPrefixes() + 1);
            Node tempRoot = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                Node[] refs = tempRoot.getReferences();
                if (refs[index] == null) {
                    refs[index] = new Node();
                }
                refs[index].setPrefixes(refs[index].getPrefixes() + 1);
                if (i == word.length() - 1) {
                    refs[index].setWords(1);
                    refs[index].setTerm(new Term(word, weight));
                  //  tempRoot.setReferences(refs);
                  //  break;
                }
                tempRoot.setReferences(refs);
                tempRoot = refs[index];
            }
          //  root = tempRoot;
        }

    }

    @Override
    public Node buildTrie(String filename, int k) {
        numSuggestions = k;
        root.setPrefixes(1);
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            try {
                int length = Integer.parseInt(br.readLine());
                for (int i = 0; i < length; i++) {
                    String currentLine = br.readLine();
                    String[] line = currentLine.split("\t");
                    String word = line[1].toLowerCase();
                    String[] line1 = line[0].split(" ");
                    long weight = Long.parseLong(line1[line1.length - 1]);
                    addWord(word, weight);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return root;
    }

    @Override
    public int numberSuggestions() {
        return numSuggestions;
    }
    
    public Node getRoot() {
        return root;
    }

    @Override
    public Node getSubTrie(String prefix) {
        Node tempRoot = root;
        Node finalNode = null;
        boolean isOk = true;
        if (prefix == "") {
            return tempRoot; //potential change
        }
        if (prefix == null) {
            return null;
        }
        for (int i = 0; i < prefix.length(); i++) {
            if (prefix.charAt(i) < 'a' || prefix.charAt(i) > 'z') {
                isOk = false;
            }
        }
        if (isOk) {
            for (int i = 0; i < prefix.length(); i++) {
                if (tempRoot == null) {
                    return finalNode;
                }
                Node[] refs = tempRoot.getReferences();
                if (refs == null) {
                    return finalNode;
                }
                Node currentNode = refs[prefix.charAt(i) - 'a'];
                if (i == prefix.length() - 1) {
                    finalNode = currentNode;
                }
                tempRoot = currentNode;
            }
        }
        return finalNode;
    }

    @Override
    public int countPrefixes(String prefix) {
        if (prefix == null) {
            return 0;
        }
        Node currentNode = getSubTrie(prefix);
        if (currentNode == null) {
            return 0;
        }
        return currentNode.getPrefixes();
    }

    private void helper(List<ITerm> list, Node node, String prefix) {
        if (node == null) {
            int x = 0;
        } else if (node.getWords() == 1) {
            if (!list.contains(node.getTerm())) {
                list.add(node.getTerm());
            }
            for (int i = 0; i < 26; i++) {
                Node newNode = node.getReferences()[i];
                helper(list, newNode, prefix);
            }
        } else {
            for (int i = 0; i < 26; i++) {
                Node newNode = node.getReferences()[i];
                helper(list, newNode, prefix);
            }
        }
    }


    @Override
    public List<ITerm> getSuggestions(String prefix) {
        List<ITerm> toReturn = new ArrayList<ITerm>();
        List<ITerm> toReturn1;
        if (countPrefixes(prefix) == 0) {
            toReturn1 = toReturn;
            return toReturn;
        } 
        Node subTrie = getSubTrie(prefix);
        if (subTrie != null) {
            for (int i = 0; i < 26; i++) {
                helper(toReturn, subTrie, prefix);
            }
        }
        toReturn1 = toReturn; //change
        return toReturn1;
    }

}
