package lewiszlw.spi.impl;

import lewiszlw.spi.ISayName;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-19
 */
public class SayChineseName implements ISayName {
    @Override
    public void sayName() {
        System.out.println("吴彦祖");
    }
}
