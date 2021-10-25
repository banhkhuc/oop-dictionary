import java.util.HashMap;

public class Node {
    private Word data;
    private boolean isWord;
    private HashMap<Character, Node> children = new HashMap<>();

    HashMap<Character, Node> getChildren() {
        return children;
    }

    void setData(Word data) { this.data = data; }

    Word getData() { return this.data; }

    void setIsWord(boolean isWord) { this.isWord = isWord; }

    Boolean getIsWord() { return this.isWord; }
}
