package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.converter.RoleConverter;
import com.laptrinhjavaweb.dto.RoleDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.service.IRoleService;
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
import java.util.Map;
import java.util.Set;

@Controller(value = "roleControllerOfAdmin")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserService userService;
    @Autowired
    private MessageUtils messageUtils;
    @Autowired
    private RoleConverter roleConverter;

    @RequestMapping(value="/quan-tri/tai-khoan/role",method = RequestMethod.GET)
    public ModelAndView showListRole(@RequestParam("page")int page,
                                     @RequestParam("limit")int limit, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/role/list");
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setPage(page);
        roleDTO.setLimit(limit);
        Pageable pageable = new PageRequest(page-1,limit);

        roleDTO.setListResult(roleService.findAll(pageable));
        roleDTO.setTotalItem(roleService.getTotalItem());
        roleDTO.setTotalPage((int) Math.ceil((double)roleDTO.getTotalItem() / roleDTO.getLimit()));

        if(request.getParameter("message")!= null){

            Map<String,String> message = messageUtils.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        mav.addObject("model", roleDTO);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/tai-khoan/role/chinh-sua", method = RequestMethod.GET)
    public ModelAndView editNew(@RequestParam(value = "id",required = false)Long id,
                                HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/role/edit");
        RoleDTO roleDTO = new RoleDTO();
        if(id != null){
            roleDTO =roleConverter.toDTO(roleService.findById(id));
        }
        if(request.getParameter("message")!= null){
            Map<String,String> message = messageUtils.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        mav.addObject("model",roleDTO);
        return mav;
    }


}
