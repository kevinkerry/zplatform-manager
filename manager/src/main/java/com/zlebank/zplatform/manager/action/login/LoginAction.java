package com.zlebank.zplatform.manager.action.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.object.UserModel;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;
import com.zlebank.zplatform.manager.util.CookieUtils;
import com.zlebank.zplatform.manager.util.MD5Util;

public class LoginAction extends BaseAction
        implements
            ServletRequestAware,
            ServletResponseAware,
            SessionAware {

    private static final long serialVersionUID = 453655250496268464L;
    private ServiceContainer serviceContainer;
    private UserModel user;
    private List<?> funlist;
    private String loginName;
    private String randcode;
    private CookieUtils cookieUtils = new CookieUtils();
    private HttpServletResponse response;
    private HttpServletRequest request;
    private Map<String, Object> session;
    private int pwdFlag;// 密码有效期过期标示 1-过期 0-未过期
    private int pwdDay;// 密码到期时间，5天时开始提示
    public String loginFailedCookie() {
        if (isNull(getCookie(request))) {
            Cookie cookie = new Cookie("user.cookie", "1");
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
        } else {
            int count = Integer.valueOf(cookieUtils.getCookie(request));
            Cookie[] cookies4 = request.getCookies();
            for (Cookie cookie : cookies4) {
                if (cookie.getName().equals("user.cookie")) {
                    cookie.setValue(count + 1 + "");
                    response.addCookie(cookie);
                }
            }
        }
        return null;
    }

    private boolean checkCookie() {
        if (!isNull(getCookie(request))) {
            int count = Integer.valueOf(cookieUtils.getCookie(request));
            if (count > 5) {
                Map<String, Object> variable = new HashMap<String, Object>();
                variable.put("ret", "err");
                variable.put("info", "您已经连续失败登录5次，一个小时内无法继续登录！");
                json_encode(variable);
                return true;
            }
        }
        return false;
    }

    /**
     * 验证用户登录信息
     * 
     * @return
     */

    public String validateUser() {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (checkCookie()) {
            return null;
        }

        String rand = "";
        if(session.get("rand")==null){
            rand = "";
        }else{
            rand = session.get("rand").toString();
        }
        if(!randcode.equals(rand)){
            returnMap.put("ret", "err_rand");
            returnMap.put("info", "验证码错误！");
            json_encode(returnMap);
            return null;
        }

        String passwordMark = "w5y1j5z1s1l1z6z0y8z1m1l0c5r5y3z4";
        passwordMark = passwordMark + user.getPwd();
        Map<String, Object> variable = new HashMap<String, Object>();
        variable.put("loginName", user.getLoginName());
        variable.put("status", "00");
        variable.put("pwd", MD5Util.MD5(passwordMark));
        List<UserModel> loginUser = serviceContainer.getUserService()
                .findByProperty(variable);
        boolean loginFlag = false;
        if (!isNull(loginUser)) {
            if (loginUser.size() == 1) {
                if (loginUser.get(0).getLoginName().equals(user.getLoginName())
                        && loginUser.get(0).getPwd()
                                .equals(MD5Util.MD5(passwordMark))) {

                    session.put("LOGIN_USER", loginUser.get(0));
                    returnMap.put("ret", "success");
                } else {
                    loginFlag = true;
                }
            } else {
                loginFlag = true;
            }
        }
        if (loginFlag) {
            returnMap.put("ret", "err_user");
            returnMap.put("info", "用户名或密码错误！");
        } else {
            @SuppressWarnings("unused")
            UserModel currentUser = loginUser.get(0);
        }
        json_encode(returnMap);
        return null;
    }

    // 操作栏
    public String querymenu() throws Exception {
        loginName = getCurrentUser().getUserName();
        if (getCurrentUser().getLoginName().equals("admin")) {
            funlist = serviceContainer.getFunctionService().findFunction();
        } else {
            funlist = serviceContainer.getFunctionService().findLoginFuntion(
                    getCurrentUser());
        }
        session.put("Authority", funlist);
        checkPWDDate();
        pwdDay = calcExpirationDay();
        return SUCCESS;
    }
    /**
     * 检查密码有效期
     */
    private void checkPWDDate() {
        // pwdFlag=0;
        // Timestamp pwdTime = getCurrentUser().getPwdValid();
        // if(isNull(pwdTime)){
        // pwdFlag=1;
        // }else{
        // long time = pwdTime.getTime();//密码有效期
        // long currentTime = new Date().getTime();//当前时间
        // if(currentTime>time){
        // pwdFlag = 1;
        // }
        // }
    }
    /**
     * 计算时间间隔
     * 
     * @return
     */
    private int calcExpirationDay() {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date());
        Calendar cal2 = Calendar.getInstance();
        if (isNull(getCurrentUser().getPwdValid())) {
            cal2.setTime(new Date());
        } else {
            cal2.setTime(new Date(getCurrentUser().getPwdValid().getTime()));
        }
        long l = cal2.getTimeInMillis() - cal1.getTimeInMillis();
        int days = new Long(l / (1000 * 60 * 60 * 24)).intValue();
        return days;
    }

    /**
     * 用户登出
     * 
     * @return
     */
    public String logout() {
        ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> session = actionContext.getSession();

        // UserModel currentUser = getCurrentUser();
        // /退出日志记录
        /*
         * OperLogModel operLog = new OperLogModel();
         * operLog.setUserId(currentUser.getUserId());
         * operLog.setUserName(currentUser.getUserName());
         * operLog.setFunctId(0L); operLog.setIp(getIpAddr(request));
         * operLog.setHostName(request.getRemoteHost());
         * operLog.setDeptId(currentUser.getDeptId());
         * operLog.setOpContent(currentUser.getUserName()+"退出作业系统");
         * operLog.setLogId(1L); Timestamp opDate = new Timestamp(new
         * Date().getTime()); operLog.setOpDate(opDate);
         * serviceContainer.getOperLogService().save(operLog);
         */
        if (session.containsKey("LOGIN_USER")) {
            session.remove("LOGIN_USER");
            session.remove("Authority");
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user.cookie")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "logout";
    }
    private ByteArrayInputStream inputStream;
    public String validateCode() throws Exception {
        // 在内存中创建图象
        int width = 65, height = 36;
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 取随机产生的认证码(4位数字)
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 13 * i + 6, 26);
        }
        // 将认证码存入SESSION
        ActionContext context = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) context
                .get(ServletActionContext.HTTP_REQUEST);
        request.getSession().setAttribute("rand", sRand);
        // ActionContext.getContext().getSession().put("rand",sRand);
        // 图象生效
        g.dispose();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
        ImageIO.write(image, "JPEG", imageOut);
        imageOut.close();
        ByteArrayInputStream input = new ByteArrayInputStream(
                output.toByteArray());
        this.setInputStream(input);
        return "code";
    }
    /*
     * 给定范围获得随机颜色
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        // 如果是多级代理，那么取第一个ip为客户ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        return ip;
    }
    // 得到cookie
    public String getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (CookieUtils.USER_COOKIE.equals(cookie.getName())) {
                    String value = cookie.getValue();
                    if (StringUtils.isNotBlank(value)) {
                        String[] split = value.split(",");
                        // String clientIp = split[0];
                        String count = split[0];
                        return count;
                    }
                }
            }
        }
        return null;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public ServiceContainer getServiceContainer() {
        return serviceContainer;
    }

    public void setServiceContainer(ServiceContainer serviceContainer) {
        this.serviceContainer = serviceContainer;
    }

    public List<?> getFunlist() {
        return funlist;
    }

    public void setFunlist(List<?> funlist) {
        this.funlist = funlist;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public int getPwdFlag() {
        return pwdFlag;
    }

    public void setPwdFlag(int pwdFlag) {
        this.pwdFlag = pwdFlag;
    }

    public int getPwdDay() {
        return pwdDay;
    }

    public void setPwdDay(int pwdDay) {
        this.pwdDay = pwdDay;
    }

    public String getRandcode() {
        return randcode;
    }

    public void setRandcode(String randcode) {
        this.randcode = randcode;
    }

}
