package com.cmcc.common.exception.user;

/**
 * 用户不存在异常类
 * 
 * @author terry
 */
public class UserNotExistsException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserNotExistsException()
    {
        super("user.not.exists", null);
    }
}
