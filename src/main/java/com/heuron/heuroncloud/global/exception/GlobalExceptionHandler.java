package com.heuron.heuroncloud.global.exception;

import com.heuron.heuroncloud.api.common.dto.HttpResponseBody;
import com.heuron.heuroncloud.domain.common.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.NonUniqueResultException;
import org.hibernate.query.sqm.UnknownPathException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * [404] NoResourceFoundException
     */
    @ExceptionHandler(NoResourceFoundException.class)
    protected ResponseEntity<Object> handleNoResourceFoundException(
        NoResourceFoundException e,
        HttpServletRequest request
    ) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String exception = "[" + NoResourceFoundException.class.getName() + "] ";
        String url = request.getRequestURL().toString();
        String message = e.getMessage();

        log.error(exception + url);
        log.error(exception + message);

        return HttpResponseBody.builder()
            .code(httpStatus)
            .message(exception + message)
            .response(exception + url)
            .buildAndMapToResponseEntity();
    }

    /**
     * [405] HttpRequestMethodNotSupportedException
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<Object> handleRequestMethodNotSupportedException(
        HttpRequestMethodNotSupportedException e,
        HttpServletRequest request
    ) {
        HttpStatus httpStatus = HttpStatus.METHOD_NOT_ALLOWED;
        String exception = "[" + HttpRequestMethodNotSupportedException.class.getName() + "] ";
        String url = request.getRequestURL().toString();
        String message = e.getMessage();

        log.error(exception + url);
        log.error(exception + message);

        return HttpResponseBody.builder()
            .code(httpStatus)
            .message(exception + message)
            .response(exception + url)
            .buildAndMapToResponseEntity();
    }

    /**
     * MethodArgumentTypeMismatchException
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleArgumentTypeMismatchException(
        MethodArgumentTypeMismatchException e,
        HttpServletRequest request
    ) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String exception = "[" + MethodArgumentTypeMismatchException.class.getName() + "] ";
        String url = request.getRequestURL().toString();
        String message = e.getMessage();

        log.error(exception + url);
        log.error(exception + message);

        return HttpResponseBody.builder()
            .code(httpStatus)
            .message(exception + message)
            .response(exception + url)
            .buildAndMapToResponseEntity();
    }

    /**
     * Method Argument Not Valid Exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleArgumentNotValidException(
        MethodArgumentNotValidException e,
        HttpServletRequest request
    ) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String exception = "[" + MethodArgumentNotValidException.class.getName() + "] ";
        String url = request.getRequestURL().toString();
        String message = e.getMessage();

        log.error(exception + url);
        log.error(exception + message);

        return HttpResponseBody.builder()
            .code(httpStatus)
            .message(exception + message)
            .response(exception + url)
            .buildAndMapToResponseEntity();
    }

    /**
     * Http Message Not Readable Exception
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleMessageNotReadableException(
        HttpMessageNotReadableException e,
        HttpServletRequest request
    ) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String exception = "[" + HttpMessageNotReadableException.class.getName() + "] ";
        String url = request.getRequestURL().toString();
        String message = e.getMessage();

        log.error(exception + url);
        log.error(exception + message);

        return HttpResponseBody.builder()
            .code(httpStatus)
            .message(exception + message)
            .response(exception + url)
            .buildAndMapToResponseEntity();
    }

    /**
     * HandlerMethodValidationException
     */
    @ExceptionHandler(HandlerMethodValidationException.class)
    protected ResponseEntity<Object> handleMethodValidationException(
        HandlerMethodValidationException e,
        HttpServletRequest request
    ) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String exception = "[" + HandlerMethodValidationException.class.getName() + "] ";
        String url = request.getRequestURL().toString();
        String message = e.getMessage();

        log.error(exception + url);
        log.error(exception + message);

        return HttpResponseBody.builder()
            .code(httpStatus)
            .message(exception + message)
            .response(exception + url)
            .buildAndMapToResponseEntity();
    }

    /**
     * MissingServletRequestParameterException
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<Object> handleMissingServletRequestParameterException(
        MissingServletRequestParameterException e,
        HttpServletRequest request
    ) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String exception = "[" + MissingServletRequestParameterException.class.getName() + "] ";
        String url = request.getRequestURL().toString();
        String message = e.getMessage();

        log.error(exception + url);
        log.error(exception + message);

        return HttpResponseBody.builder()
            .code(httpStatus)
            .message(exception + message)
            .response(exception + url)
            .buildAndMapToResponseEntity();
    }

    /**
     * Business Exception
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(
        BusinessException e,
        HttpServletRequest request
    ) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String exception = "[" + BusinessException.class.getName() + "] ";
        String url = request.getRequestURL().toString();
        String message = e.getMessage();

        log.error(exception + url);
        log.error(exception + message);

        return HttpResponseBody.builder()
            .code(httpStatus)
            .message(exception + message)
            .response(exception + url)
            .buildAndMapToResponseEntity();
    }

    /**
     * Unknown Path Exception UnknownPathException
     */
    @ExceptionHandler(UnknownPathException.class)
    protected ResponseEntity<Object> handleUnknownPathException(UnknownPathException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String exception = "[" + UnknownPathException.class.getName() + "] ";
        String url = request.getRequestURL().toString();
        String message = e.getMessage();

        log.error(exception + url);
        log.error(exception + message);

        return HttpResponseBody.builder()
            .code(httpStatus)
            .message(exception + message)
            .response(exception + url)
            .buildAndMapToResponseEntity();
    }

    /**
     * Non Unique Result Exception
     * 의도치 않은 서버의 이상 동작으로 데이터가 2개 등록된 것을 호출할 때 발생
     */
    @ExceptionHandler(NonUniqueResultException.class)
    protected ResponseEntity<Object> handleNonUniqueResultException(NonUniqueResultException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String exception = "[" + NonUniqueResultException.class.getName() + "] ";
        String url = request.getRequestURL().toString();
        String message = e.getMessage();

        log.error(exception + url);
        log.error(exception + message);

        return HttpResponseBody.builder()
            .code(httpStatus)
            .message(exception + message)
            .response(exception + url)
            .buildAndMapToResponseEntity();
    }

    /**
     * Internal Exception
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleBusinessException(Exception e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String exception = "[" + Exception.class.getName() + "] ";
        String url = request.getRequestURL().toString();
        String message = e.getMessage();

        log.error(exception + url);
        log.error(exception + message);
        e.printStackTrace();

        return HttpResponseBody.builder()
            .code(httpStatus)
            .message(exception + message)
            .response(exception + url)
            .buildAndMapToResponseEntity();
    }


}
