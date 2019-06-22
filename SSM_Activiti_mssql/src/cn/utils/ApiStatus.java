package cn.utils;

/**
* 项目名称：Spring_activiti        
* 类名称：ApiStatus    
* 类描述：
* 创建人：Jie.xiaojun
* 创建时间：2018年7月5日 下午12:09:04
 */
public class ApiStatus {
	
	/**
	 * 调用成功
	 * http调用成功，则为success
	 * Success=0 表征http请求成功并且业务操作成功
	 * 如果业务操作失败，Status状态非0
	 */
	public static int success = 0;
	/**
	 * 表示http请求方式错误，请求方式应该为POST请求
	 */
	public static int post = -1;
	/**
	 * 表示时间戳错误，时间戳与接口接收时间差值不能大于60秒
	 */
	public static int timeError = -2;
	/**
	 * 校验数据为空
	 */
	public static int signNull = -3;
	/**
	 * 身份校验错误
	 */
	public static int signError = -4;
	/**
	 * 必填参数为空
	 */
	public static int paramNull = -5;
	/**
	 * 参数长度超出范围
	 */
	public static int paramLengthOut = -6;
	/**
	 * 参数类型错误
	 */
	public static int paramTypeError = -7;
	/**
	 * 操作失败
	 */
	public static int operateError = -8;
	/**
	 * 返回数据为空
	 */
	public static int dataNull = -9;
	/**
	 * 返回数据
	 */
	public static int ReturnData = -10;
	/**
	 * 登录错误
	 */
	public static int loginError = -401;

}
