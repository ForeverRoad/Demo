package com.util;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

public class WXBizMsgCryptUtil {
	
	/**
	 * 
	 * @Description: 获取加密解密的解析类对象
	 * @param @param token
	 * @param @param encodingAesKey
	 * @param @param appId
	 * @param @throws AesException   
	 * @return WXBizMsgCrypt  
	 * @throws AesException
	 * @author zcf
	 * @date 2015-10-9
	 */
	public WXBizMsgCrypt getPc(String token,String encodingAesKey,String appId) throws AesException{
		WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
		
		return pc;
	}
}
