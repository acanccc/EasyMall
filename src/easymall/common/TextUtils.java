package easymall.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * @author	passerbyYSQ
 * @date	2020-11-17 8:36:08
 */
public class TextUtils {
	/**
	 * 判断邮箱是否符合格式
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){  
        if (StringUtils.isEmpty(email)){
        	return false; 
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"; 
        Pattern pattern = Pattern.compile(regEx1);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
