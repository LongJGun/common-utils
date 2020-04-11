package org.longj.gun.commons.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则相关工具类
 *
 * @date 2020/04/05 22:01
 * @author LongJ
 */
public class RegexUtil {

    /**
     * 将通配符匹配串转换成正则表达式匹配串
     *
     * @param toBeEscapedKeyword 待转换的字符串
     * @return 返回转换后的正则表达式字符串
     */
    public static String escapeSpecialWordToRegExp(String toBeEscapedKeyword) {
        if ( StringUtils.isNotBlank(toBeEscapedKeyword) ) {
            String[] fbsArr = { "\\", "$", "(", ")", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
            for (String key : fbsArr) {
                if (toBeEscapedKeyword.contains(key)) {
                    toBeEscapedKeyword = toBeEscapedKeyword.replace(key, "\\" + key);
                }
            }
            toBeEscapedKeyword = toBeEscapedKeyword.replace("*", ".*");
        }
        toBeEscapedKeyword = "^" + toBeEscapedKeyword + "$";
        return toBeEscapedKeyword;
    }

    /**
     * 根据正则表达式匹配字符串列表
     *
     * @param toBeMatchedList 字符串列表
     * @param regExp 正则表达式
     * @return 返回匹配到的字符串新列表
     */
    public static List<String> matchStringList(final Collection<String> toBeMatchedList, final String regExp){
        Pattern compile = Pattern.compile(regExp);
        return matchStringList(toBeMatchedList,compile);
    }

    /**
     * 根据正则表达式匹配字符串列表
     *
     * @param toBeMatchedList 字符串列表
     * @param compile java.util.regex.Pattern
     * @return 返回匹配到的字符串新列表
     */
    public static List<String> matchStringList(final Collection<String> toBeMatchedList, final Pattern compile){
        List<String> matchList = new ArrayList<>();
        for ( String toBeMatched : toBeMatchedList ){
            if ( isMatched(compile,toBeMatched) ){
                matchList.add(toBeMatched);
            }
        }
        return matchList;
    }

    /**
     * 根据正则表达式匹配字符串
     *
     * @param compile java.util.regex.Pattern
     * @param toBeMatched 待匹配的字符串
     * @return true - 匹配成功<br/>
     *         false - 匹配失败
     */
    public static boolean isMatched(final Pattern compile, final String toBeMatched){
        Matcher matcher = compile.matcher(toBeMatched);
        if ( matcher.find() ){
            return true;
        }
        return false;
    }

    /**
     * 根据正则表达式匹配字符串
     *
     * @param regExp 正则表达式
     * @param toBeMatched 待匹配的字符串
     * @return true - 匹配成功<br/>
     *         false - 匹配失败
     */
    public static boolean isMatched(final String regExp, final String toBeMatched){
        Pattern compile = Pattern.compile(regExp);
        return isMatched(compile,toBeMatched);
    }
}
