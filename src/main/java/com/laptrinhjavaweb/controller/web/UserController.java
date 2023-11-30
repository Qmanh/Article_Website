package com.laptrinhjavaweb.controller.web;

import com.laptrinhjavaweb.dto.CommentDTO;
import com.laptrinhjavaweb.dto.MyUser;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;

@Controller(value = "userControllerOfWeb")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private MessageUtils messageUtils;

    @RequestMapping(value = "/dang-ky", method = RequestMethod.POST)
    public ModelAndView registerPage(UserDTO userDTO) {
        ModelAndView mav = new ModelAndView("register");
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        String confirmPassword = userDTO.getConfirmPassword();
        String message="";
        String alert="";

        if(!password.equals(confirmPassword)){
            message="Mật khẩu nhập lại không đúng";
            alert="danger";

        } else if (userService.existsByUserName(username)) {
            message="Tên người dùng đã tồn tại";
            alert="danger";
        }else{
            message="Chúc mừng bạn đã đăng ký thành công";
            alert="success";
            userService.save(userDTO);
        }
        mav.addObject("message", message);
        mav.addObject("alert", alert);

        return mav;
    }

    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ModelAndView ForgotPassword(UserDTO userDTO) {
        ModelAndView mav = new ModelAndView("forgotPassword");
        String message="";
        String alert="";
        UserDTO user = userService.findByUserName(userDTO.getUsername());
        if(user!= null ){
            userService.resetPassword(user);
            message="Vui lòng kiểm tra Email";
            alert="success";
        }else{
            message= "Email không tồn tại";
            alert="danger";
        }
        mav.addObject("message", message);
        mav.addObject("alert", alert);

        return mav;
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public ModelAndView ChangePassword(@RequestParam("inputOldPassword")String oldPassword,
                                       @RequestParam("inputNewPassword")String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser user = (MyUser) authentication.getPrincipal();
        ModelAndView mav = new ModelAndView("changePassword");
        String message="";
        String alert="";
        String username = user.getUsername();
        UserDTO userDTO = userService.findByUserName(username);
        if(userDTO.getPassword().equals(oldPassword)){
            message= "Mật khẩu cũ không đúng";
            alert="danger";
        }else{
            userService.changePassword(userDTO,newPassword);
            message="Thay đổi mật khẩu thành công";
            alert="success";
        }

        mav.addObject("message", message);
        mav.addObject("alert", alert);

        return mav;
    }

}
