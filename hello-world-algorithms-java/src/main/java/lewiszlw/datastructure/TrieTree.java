package lewiszlw.datastructure;


import java.util.*;

/**
 * Desc: 字典树/前缀树/单词查找树
 * 核心思想是空间换时间，利用字符串的公共前缀来减少无谓的字符串比较以达到提高查询效率的目的。
 *
 * @author zhanglinwei02
 * @date 2019-04-01
 */
public class TrieTree {

    private final TrieNode root = new TrieNode();

    /**
     * 增加一条字符串
     */
    public void insert(String word) {
        if (word == null) {
            throw new NullPointerException("insert word is null");
        }
        insertChild(root, word);
    }

    private void insertChild(TrieNode node, String word) {
        if (word.length() == 0) {
            return;
        }
        char letter = word.charAt(0);
        TrieNode child = node.child(letter);
        if (child == null) {
            child = new TrieNode(letter, 1, 0);
            node.addChild(child);
        } else {
            child.count++;
        }
        if (word.length() == 1) {
            child.end++;
            return;
        }
        String subWord = word.substring(1);
        insertChild(child, subWord);
    }

    /**
     * 删除字符串
     */
    public void delete(String word) {
        if (word == null) {
            throw new NullPointerException("delete word is null");
        }
        ArrayList<Map<TrieNode, TrieNode>> nodeList = new ArrayList<>();
        if (isWord(root, word, nodeList)) {
            for (int i = 0; i < nodeList.size(); i++) {
                Iterator<Map.Entry<TrieNode, TrieNode>> iterator = nodeList.get(i).entrySet().iterator();
                Map.Entry<TrieNode, TrieNode> trieNodeEntry = iterator.next();
                TrieNode parent = trieNodeEntry.getKey();
                TrieNode child = trieNodeEntry.getValue();
                child.count--;
                if (i == nodeList.size() - 1) {
                    child.end--;
                }
                if (child.count <= 0) {
                    parent.deleteChild(child);
                }
            }
        } else {
            throw new NoSuchElementException("delete word can not be found");
        }
    }

    private boolean isWord(TrieNode node, String word, ArrayList<Map<TrieNode, TrieNode>> nodeList) {
        if (word.length() == 0) {
            return false;
        }
        char letter = word.charAt(0);
        TrieNode child = node.child(letter);
        if (child == null) {
            return false;
        }
        Map<TrieNode, TrieNode> map = new HashMap<>();
        map.put(node, child);
        nodeList.add(map);
        if (word.length() == 1 && child.end > 0) {
            return true;
        }
        String subWord = word.substring(1);
        return isWord(child, subWord, nodeList);
    }

    /**
     * 统计前缀出现频次
     */
    public int countPrefix(String prefix) {
        if (prefix == null) {
            throw new NullPointerException("count prefix is null");
        }
        TrieNode trieNode = searchPrefix(root, prefix);
        return trieNode == null? 0: trieNode.count;
    }

    private TrieNode searchPrefix(TrieNode node, String prefix) {
        if (prefix.length() == 0) {
            return null;
        }
        char letter = prefix.charAt(0);
        TrieNode child = node.child(letter);
        if (child == null) {
            return null;
        }
        if (prefix.length() == 1) {
            return child;
        }
        return searchPrefix(child, prefix.substring(1));
    }


    class TrieNode {
        /**孩子节点*/
        List<TrieNode> children;
        /**字母*/
        char letter;
        /**经过此节点路径数量*/
        int count;
        /**作为最后节点次数*/
        int end;

        TrieNode() {
            this('#', 0, 0);
        }

        TrieNode(char letter, int count, int end) {
            this.children = new ArrayList<>();
            this.letter = letter;
            this.count = count;
            this.end = end;
        }

        public TrieNode child(char letter) {
            for (TrieNode trieNode : this.children) {
                if (trieNode.letter == letter) {
                    return trieNode;
                }
            }
            return null;
        }

        public void addChild(TrieNode child) {
            this.children.add(child);
        }

        public void deleteChild(TrieNode child) {
            this.children.remove(child);
        }
    }
}
