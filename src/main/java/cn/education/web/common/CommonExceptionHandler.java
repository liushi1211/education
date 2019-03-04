package cn.education.web.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class CommonExceptionHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(CommonExceptionHandler.class);


    @ExceptionHandler(value = EdException.class)
    @ResponseBody
    public JsonResponse<String> handleException1(EdException rpmException) {
        return new JsonResponse<>(rpmException.getErrorCode(),
                rpmException.getDesc(), null);
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResponse<String> handleException2(Exception exception) {
        LOGGER.error("system error", exception);
        return new JsonResponse<>(ErrorCode.FAIL,null);
    }
}
