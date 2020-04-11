package org.longj.gun.commons.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Properties;

/**
 * properties文件操作
 *
 * @author LongJ
 * @date  2020/04/11 13:30
 */
public class PropertiesUtil {

    /**
     * 获取jar包内部的配置文件
     *
     * @param filePath jar包内部的配置文件的路径
     * @return Properties
     */
    public static Properties getPropertiesInJar(String filePath){
        Preconditions.checkArgument( StringUtils.isNotBlank( filePath ) );
        Properties properties = new Properties();
        try ( InputStream in = PropertiesUtil.class.getResourceAsStream(filePath) ) {
            properties.load(in);
            return properties;
        } catch ( IOException e ) {
            e.printStackTrace();
            throw new RuntimeException( "加载配置文件["+filePath+"]异常" );
        }
    }

    /**
     * 获取jar包外部的配置文件
     *
     * @param filePath jar包外部的配置文件路径
     * @return Properties
     */
    public static Properties getPropertiesOutJar(String filePath){
        Preconditions.checkArgument( StringUtils.isNotBlank( filePath ) );
        Properties properties = new Properties();
        try ( InputStream in = new FileInputStream(filePath) ) {
            properties.load(in);
            return properties;
        } catch ( IOException e ) {
            e.printStackTrace();
            throw new RuntimeException( "加载配置文件["+filePath+"]异常" );
        }
    }



}
