package com.sohu.edm.controller.common;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sohu.edm.service.EmsUserService;
import com.sohu.edm.tools.Constants;
import com.sohu.edm.util.ViewTarget;

@Controller
@RequestMapping("/simulogin")
public class SimuLoginController {
	@Autowired
	EmsUserService	emsUserService;
	@RequestMapping(value="")
	public String login(
			HttpServletRequest request,
			HttpServletResponse response){
		request.setAttribute("login", false);
		return ViewTarget.SIMU_LOGIN;
	}
	
	@RequestMapping(value="login")
	public String sumuLogin(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "username", required = false) String username){
		if(0==emsUserService.getUserNumByUserName(username)){
			request.setAttribute("message", "无此用户！");
			return "/simulogin";
		}
		String cookie = SimuLoginController.getCookie(username);
		SimuLoginController.getCommon(cookie);
		/*URL url;
		try {
            String temp = new String();
			url = new	URL(Constants.EMS_LOGIN_URL);
		    HttpURLConnection httpURLConn;
			httpURLConn= (HttpURLConnection)url.openConnection();
	        httpURLConn.setDoOutput(true);
	        httpURLConn.setRequestMethod("POST");
	        httpURLConn.setDoOutput(true); 
	        httpURLConn.setDoInput(true); 
	        httpURLConn.getOutputStream().write(("user_id="+username).getBytes());
	        httpURLConn.getOutputStream().close();
	        httpURLConn.connect(); 
	        InputStream in =httpURLConn.getInputStream();
            BufferedReader bd = new BufferedReader(new InputStreamReader(in));
            while((temp=bd.readLine())!=null)
            {
                System.out.println(temp+"1");
            }      
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		request.setAttribute("message", "登录成功！请访问："+Constants.EMS_SYS_ADDR);
		request.setAttribute("login", true);
		request.setAttribute("redirectURL", Constants.EMS_SYS_ADDR);
		request.setAttribute("url", Constants.EMS_LOGIN_URL);
		return ViewTarget.SIMU_LOGIN;
		
	}
	
	public static String getCookie(String username) {
		String cookie = "";
		try {
			URL url = new URL(Constants.EMS_LOGIN_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.getOutputStream().write(("username=" + username).getBytes());
			connection.getOutputStream().close();
			connection.connect();
			String key = null;
			String cookieVal = null;
			for (int i = 1; (key = connection.getHeaderFieldKey(i)) != null; i++) {
				if (key.equalsIgnoreCase("Set-Cookie")) {
					cookieVal = connection.getHeaderField(i);
					cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));
					cookie = cookie + cookieVal + ";";
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cookie;
	}
	public static void getCommon(String cookie) {
		try {
			URL url = new URL(Constants.EMS_SYS_ADDR);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Cookie", cookie);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.getOutputStream().close();

			connection.connect();
/*
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "GB2312"));*/
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
