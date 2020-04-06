package org.longj.gun.commons.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 *
 *
 * @date 2020/04/05 22:02
 * @author LongJ
 */
public class FileUtil {

    /**
     * 对路径进行格式化
     *
     * @param path 待格式化的路径（以/开头）
     * @return /path/to - 标准的格式化路径
     */
    public static String formatPathToStandard(String path) {
        Preconditions.checkNotNull(path);
        path = path.trim();
        Preconditions.checkArgument(!StringUtils.isBlank(path),"不合法的路径["+path+"]");
        Preconditions.checkArgument(path.startsWith(File.separator),"不合法的路径["+path+"]");

        StringBuilder builder = new StringBuilder();
        for (String partPath : path.split(File.separator, -1)) {
            if ( StringUtils.isBlank(partPath) ) {
                continue;
            }
            builder.append(File.separator).append(partPath);
        }

        Preconditions.checkArgument(!StringUtils.isBlank(builder),"不合法的路径["+path+"]");
        return builder.toString();
    }
}
