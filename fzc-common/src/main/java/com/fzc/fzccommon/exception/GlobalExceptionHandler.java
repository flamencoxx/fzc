package com.fzc.fzccommon.exception;

/**
 * 全局异常处理
 *
 * @author flamencoxx
 * @date 2020/2/27
 */
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ResponseBody
//    @ExceptionHandler(value = ApiException.class)
//    public CommonResult handle(ApiException e) {
//        if (e.getErrorCode() != null) {
//            return CommonResult.failed(e.getErrorCode());
//        }
//        return CommonResult.failed(e.getMessage());
//    }
//
//    @ResponseBody
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public CommonResult handleValidException(MethodArgumentNotValidException e) {
//        BindingResult bindingResult = e.getBindingResult();
//        String message = null;
//        if (bindingResult.hasErrors()) {
//            FieldError fieldError = bindingResult.getFieldError();
//            if (fieldError != null) {
//                message = fieldError.getField()+fieldError.getDefaultMessage();
//            }
//        }
//        return CommonResult.validateFailed(message);
//    }
//
//    @ResponseBody
//    @ExceptionHandler(value = BindException.class)
//    public CommonResult handleValidException(BindException e) {
//        BindingResult bindingResult = e.getBindingResult();
//        String message = null;
//        if (bindingResult.hasErrors()) {
//            FieldError fieldError = bindingResult.getFieldError();
//            if (fieldError != null) {
//                message = fieldError.getField()+fieldError.getDefaultMessage();
//            }
//        }
//        return CommonResult.validateFailed(message);
//    }
//}
