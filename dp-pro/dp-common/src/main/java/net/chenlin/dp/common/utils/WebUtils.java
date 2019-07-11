package net.chenlin.dp.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.StringUtils;
import net.chenlin.dp.common.xss.XssHttpServletRequestWrapper;
import org.apache.shiro.SecurityUtils;

import net.chenlin.dp.common.entity.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * web工具类
 * @author zcl<yczclcn@163.com>
 */
public class WebUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebUtils.class);

	/**
	 * 获取IP地址
	 *
	 * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = null, unknown = "unknown", seperator = ",";
		int maxLength = 15;
		try {
			ip = request.getHeader("x-forwarded-for");
			if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (StringUtils.isEmpty(ip) || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		} catch (Exception e) {
			LOGGER.error("IPUtils ERROR ", e);
		}

		// 使用代理，则获取第一个IP地址
		if (StringUtils.isEmpty(ip) && ip.length() > maxLength) {
			int idx = ip.indexOf(seperator);
			if (idx > 0) {
				ip = ip.substring(0, idx);
			}
		}

		return ip;
	}

	/**
	 * 获取ip地址
	 * @return
	 */
	public static String getIpAddr() {
		HttpServletRequest request = getRequest();
		return getIpAddr(request);
	}

	/**
	 * 获取httpServletRequest
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return new XssHttpServletRequestWrapper(request);
	}

	/**
	 * 获取httpServletResponse
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}
	
	/**
	 * 是否为ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request){
		String header = "x-requested-with", httpRequest = "XMLHttpRequest";
		//如果是ajax请求响应头会有，x-requested-with
		if (request.getHeader(header) != null
				&& request.getHeader(header)
				.equalsIgnoreCase(httpRequest)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 页面输出
	 * @param response
	 * @param o
	 */
	public static void write(HttpServletResponse response,Object o){
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println(o.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将用户信息封装到map中
	 * @return
	 */
	public static Query getCurrUserMap() {
		Object userObj = SecurityUtils.getSubject().getPrincipal();
		if(CommonUtils.isNullOrEmpty(userObj)) {
			Query error = new Query();
			error.put("username", "获取用户信息失败");
			return error;
		}
		String username = JsonUtils.beanToJson(userObj);
		Map<String, Object> currUser = JsonUtils.jsonToMap(username);
		Query query = new Query(currUser);
		return query;
	}

}
