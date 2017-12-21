
package cn.newtouch.step.file.util;

import java.util.UUID;

/**
 * 
* @Title: KeyGenerator.java
* @Package cn.newtouch.yangyang.common.utils
* @Description: 业务主键生成工具类
* @author Mumu
* @date 2014年12月10日 上午10:16:24
* @version V1.0
 */
public class KeyGenerator {
	
	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",  
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",  
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",  
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
            "W", "X", "Y", "Z" };
	
	private KeyGenerator() {
		super();
	}

	/**
	 * 自动生成主键值，uuid 作为主键
	 * @return
	 */
	public static String getUuid(){
		String keyId = UUID.randomUUID().toString().replaceAll("-", "");
		return keyId;
	}

	/**
	 * 获取8位uuid<p>
	 * <b>警告：此方法不保证绝对唯一，使用时，应该校验唯一性</b>
	 * <p>
	 * 经单for循环 10000000 次测试，没有出现重复
	 * @author haijiang.mo
	 * @since 2016年7月5日
	 * @return
	 */
	public static String getUUID8Characters() {  
	    StringBuffer shortBuffer = new StringBuffer();  
	    String uuid = UUID.randomUUID().toString().replace("-", "");  
	    for (int i = 0; i < 8; i++) {  
	        String str = uuid.substring(i * 4, i * 4 + 4);  
	        int x = Integer.parseInt(str, 16);  
	        shortBuffer.append(chars[x % 0x3E]);  
	    }  
	    return shortBuffer.toString();  
	  
	}  
	
}
