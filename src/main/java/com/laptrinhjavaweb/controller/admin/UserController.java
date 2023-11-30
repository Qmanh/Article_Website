package com.laptrinhjavaweb.controller.admin;


import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.service.ICommentService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller(value = "userControllerOfAdmin")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private MessageUtils messageUtils;

    @RequestMapping(value="/quan-tri/tai-khoan/danh-sach",method = RequestMethod.GET)
    public ModelAndView showListUser(@RequestParam("page")int page,
                                     @RequestParam("limit")int limit, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/user/list");
        UserDTO userDTO = new UserDTO();
        userDTO.setPage(page);
        userDTO.setLimit(limit);
        Pageable pageable = new PageRequest(page-1,limit);
        List<UserDTO> dtos = new ArrayList<>();
        List<UserDTO> list = userService.findAll(pageable);

        for(UserDTO userDTO1: list) {
            Set<RoleEntity> roles = userService.getUserRoles(userDTO1.getId());
            if (roles != null) {
                for (RoleEntity role : roles) {
                    String roleName = role.getName();
                    userDTO1.setRoleName(roleName);
                    dtos.add(userDTO1);
                }
            } else {
                System.out.println("failed!");
            }
        }

        userDTO.setListResult(dtos);
        userDTO.setTotalItem(userService.getTotalItem());
        userDTO.setTotalPage((int) Math.ceil((double)userDTO.getTotalItem() / userDTO.getLimit()));
        if(request!= null){
            getMessage(request,mav);
        }
        mav.addObject("model", userDTO);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/tai-khoan/chinh-sua", method = RequestMethod.GET)
    public ModelAndView editNew(@RequestParam(value = "id",required = false)Long id,
                                HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/edit");
        UserDTO userDTO =new UserDTO();
        if(id != null) {
            userDTO = userService.findById(id);
            Set<RoleEntity> roles = userService.getUserRoles(userDTO.getId());
            if (roles != null) {
                for (RoleEntity role : roles) {
                    String roleName = role.getName();
                    userDTO.setRoleName(roleName);
                }
            }
        }
        if(request != null){
            getMessage(request,mav);
        }
        mav.addObject("model",userDTO);
        return mav;
    }
    public void getMessage(HttpServletRequest request,ModelAndView mav){
        if(request.getParameter("message")!= null){
            Map<String,String> message = messageUtils.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
    }
}
