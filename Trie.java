import java.util.ArrayList;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Trie {
    private Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(Word word) {
        Node curr = root;
        for(char x: word.wordTarget.toCharArray()) {
            curr = curr.getChildren().computeIfAbsent(x, k -> new Node());
        }
        curr.setData(word);
        curr.setIsWord(true);
    }

    public String search (String word) {
        Node curr = getNode(word);
        if (curr == null) return "khong tim thay";
        if (curr.getIsWord()) return curr.getData().wordExplain;
        return "khong tim thay";
    }


    private void searchWithPrefix(Node curr, ArrayList<String> result) {
        if(curr.getIsWord()) {
            result.add(curr.getData().wordTarget);
        }
        SortedSet<Character> set = new TreeSet<>(curr.getChildren().keySet());
        for(char x: set) {
            searchWithPrefix(curr.getChildren().get(x), result);
        }
    }
    public ArrayList<String> startWithPrefix(String prefix) {
        ArrayList<String> result = new ArrayList<>();
        Node curr = getNode(prefix);
        if(curr == null) {
            return result;
        }
        searchWithPrefix(curr, result);
        return result;
    }

    private Node getNode(String word) {
        Node curr = root;
        for(char x: word.toCharArray()) {
            Node node = curr.getChildren().get(x);
            if(node == null) return null;
            curr = node;
        }
        return curr;
    }

    public void delete(String deleteWord) {
        delete(root, deleteWord.toLowerCase(), 0);
    }

    private boolean delete(Node current, String word, int index) {
        if (index == word.length()) {
            if (!current.getIsWord()) {
                return false;
            }
            current.setIsWord(false);
            return current.getChildren().isEmpty();
        }
        char ch = word.charAt(index);
        Node node = current.getChildren().get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.getIsWord();

        if (shouldDeleteCurrentNode) {
            current.getChildren().remove(ch);
            return current.getChildren().isEmpty();
        }
        return false;
    }

}
