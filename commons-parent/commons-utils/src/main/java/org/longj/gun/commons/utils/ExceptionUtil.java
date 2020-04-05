package org.longj.gun.commons.utils;

/**
 * @Author: LongJ
 * @Date: 2020/04/05 22:34
 * @Description:
 */
public class ExceptionUtil {

    /**
     * 将CheckedException转换为UncheckedException
     *
     * @param e 异常
     * @return RuntimeException
     */
    private static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else {
            return new RuntimeException(e.getMessage());
        }
    }
}
