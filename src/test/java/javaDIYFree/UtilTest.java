package javaDIYFree;

import org.junit.Test;

/**
 * @author Hearts
 * @date 2019/4/18
 * @desc
 */

public class UtilTest {

    @Test
    public void lineSeparatorPropertyTest(){
        System.out.print("--");
        System.out.println(System.getProperty("line.separator"));
        System.out.print("--");
    }
}
