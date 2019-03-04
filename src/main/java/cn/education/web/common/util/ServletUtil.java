package cn.education.web.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Mark on 2017/8/1.
 * copy from jeesite
 */
public class ServletUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletUtil.class);

    // -- 常用数值定义 --//
    public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

    public static final String BROWSER_TYPE_IE = "IE";
    public static final String BROWSER_TYPE_FIREFOX = "FF";
    public static final String BROWSER_TYPE_SAFARI = "SF";

    /**
     * 静态文件后缀
     */
    private static String[] staticFiles = {".css", ".js", ".png", ".jpg", ".gif", ".jpeg", ".bmp", ".ico", ".swf", ".psd", ".htc", ".htm", ".html", ".crx", ".xpi", ".exe", ".ipa", ".apk"};

    /**
     * 设置客户端缓存过期时间 的Header.
     */
    public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
        // Http 1.0 header, set a fix expires date.
        response.setDateHeader(HttpHeaders.EXPIRES, System.currentTimeMillis() + expiresSeconds * 1000);
        // Http 1.1 header, set a time after now.
        response.setHeader(HttpHeaders.CACHE_CONTROL, "private, max-age=" + expiresSeconds);
    }

    /**
     * 设置禁止客户端缓存的Header.
     */
    public static void setNoCacheHeader(HttpServletResponse response) {
        // Http 1.0 header
        response.setDateHeader(HttpHeaders.EXPIRES, 1L);
        response.addHeader(HttpHeaders.PRAGMA, "no-cache");
        // Http 1.1 header
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, max-age=0");
    }

    /**
     * 设置LastModified Header.
     */
    public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
        response.setDateHeader(HttpHeaders.LAST_MODIFIED, lastModifiedDate);
    }

    /**
     * 设置Etag Header.
     */
    public static void setEtag(HttpServletResponse response, String etag) {
        response.setHeader(HttpHeaders.ETAG, etag);
    }

    /**
     * 根据浏览器If-Modified-Since Header, 计算文件是否已被修改.
     * <p>
     * 如果无修改, checkIfModify返回false ,设置304 not modify status.
     *
     * @param lastModified 内容的最后修改时间.
     */
    public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response,
                                               long lastModified) {
        long ifModifiedSince = request.getDateHeader(HttpHeaders.IF_MODIFIED_SINCE);
        if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            return false;
        }
        return true;
    }

    /**
     * 组合Parameters生成Query String的Parameter部分,并在paramter name上加上prefix.
     */
    public static String encodeParameterStringWithPrefix(Map<String, Object> params, String prefix) {
        StringBuilder queryStringBuilder = new StringBuilder();

        String pre = prefix;
        if (pre == null) {
            pre = "";
        }
        Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            queryStringBuilder.append(pre).append(entry.getKey()).append("=").append(entry.getValue());
            if (it.hasNext()) {
                queryStringBuilder.append("&");
            }
        }
        return queryStringBuilder.toString();
    }

    /**
     * 获取当前请求对象
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断访问URI是否是静态文件请求
     *
     * @throws Exception
     */
    public static boolean isStaticFile(String uri) {
        if (staticFiles == null || staticFiles.length == 0) {
            try {
                throw new Exception("检测到“app.properties”中没有配置“web.staticFile”属性。配置示例：\n#静态文件后缀\n"
                        + "web.staticFile=.css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,.psd,.htc,.crx,.xpi,.exe,.ipa,.apk");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.endsWithAny(uri, staticFiles)) {
            return true;
        }
        return false;
    }

    /**
     * 判断客户端浏览器类型
     *
     * @param request
     * @return
     */
    public static String getBrowser(HttpServletRequest request) {
        String userAgent = request.getHeader("USER-AGENT");
        if (userAgent != null) {
            userAgent = userAgent.toLowerCase();
            if (userAgent.indexOf("firefox") != -1) {
                return BROWSER_TYPE_FIREFOX;
            }
            if (userAgent.indexOf("msie") != -1) {
                return BROWSER_TYPE_IE;
            }
            if (userAgent.indexOf("safari") != -1) {
                return BROWSER_TYPE_SAFARI;
            }
        }
        return "";
    }

    public static void setExportCSVHeader(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        String utf = "UTF-8";
        response.setContentType("application/ms-txt.numberformat:@");
        response.setCharacterEncoding(utf);
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition",
            "attachment; filename=" + URLEncoder.encode(fileName, utf));
    }

    public static void setExportHeader(HttpServletRequest request, HttpServletResponse response,
                                       String fileName) {
        String filenamedisplay = null;
        try {
            // 解决下载文件文件名中文不显示的问题, 解决火狐浏览器下载文件名未正常显示的BUG
            filenamedisplay = URLEncoder.encode(fileName, "UTF-8");
            if (BROWSER_TYPE_FIREFOX.equals(getBrowser(request))) {
                filenamedisplay = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            filenamedisplay = fileName;
            LOGGER.error("setExportHeader occurred an exception,fileName is {}: ", fileName, e);
        }

        response.addHeader("Content-Disposition",
            "attachment; filename=" + filenamedisplay + ".xlsx");
        response.addHeader("Content-Type", "application/binary");
    }

}
