package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.CommentDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.ICommentService;
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

@Controller(value = "commentControllerOfAdmin")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @RequestMapping(value="/quan-tri/binh-luan/danh-sach",method = RequestMethod.GET)
    public ModelAndView showListComment(@RequestParam("page")int page,
                                     @RequestParam("limit")int limit){
        ModelAndView mav = new ModelAndView("admin/comment/list");
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setPage(page);
        commentDTO.setLimit(limit);
        Pageable pageable = new PageRequest(page-1,limit);

        commentDTO.setListResult(commentService.findAll(pageable));
        commentDTO.setTotalItem(commentService.getTotalItem());
        commentDTO.setTotalPage((int) Math.ceil((double)commentDTO.getTotalItem() / commentDTO.getLimit()));

        mav.addObject("model", commentDTO);
        return mav;
    }
}
