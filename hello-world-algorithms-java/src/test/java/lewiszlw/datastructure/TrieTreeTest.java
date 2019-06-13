package lewiszlw.datastructure;

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-01
 */
public class TrieTreeTest {

    @Test
    public void test() {
        TrieTree trieTree = new TrieTree();

        trieTree.insert("abc");
        trieTree.insert("adegace");
        trieTree.insert("adfelgo");

        Assert.assertTrue("assert1", trieTree.countPrefix("a") == 3);
        Assert.assertTrue("assert2", trieTree.countPrefix("ad") == 2);

        trieTree.delete("adegace");
        Assert.assertTrue("assert3", trieTree.countPrefix("a") == 2);
        Assert.assertTrue("assert4", trieTree.countPrefix("ad") == 1);
        Assert.assertTrue("assert5", trieTree.countPrefix("adf") == 1);

        trieTree.delete("abc");
        trieTree.delete("adfelgo");
        Assert.assertTrue("assert6", trieTree.countPrefix("a") == 0);
    }
}
