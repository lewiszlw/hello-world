package lewiszlw.algorithms.search;

/**
 * Desc: 顺序查找
 *
 * @author zhanglinwei02
 * @date 2019-06-13
 */
public class SequenceSearch {

    public int search(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
