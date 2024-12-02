package com.nju.mystore.util;

import com.nju.mystore.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**

 * 你可以通过这个类的方法来获得当前用户的信息。
*/
@Component
public class SecurityUtil {

    @Autowired
    HttpServletRequest httpServletRequest;

    public User getCurrentUser(){
        return (User)httpServletRequest.getSession().getAttribute("currentUser");
    }

    public void setCurrentUser(User user){
        httpServletRequest.getSession().setAttribute("currentUser", user);
    }

}
