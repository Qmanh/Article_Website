package com.laptrinhjavaweb.api.web;

import com.laptrinhjavaweb.dto.CommentDTO;
import com.laptrinhjavaweb.dto.MyUser;
import com.laptrinhjavaweb.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "commentAPIOfWeb")
public class CommentAPI {
    @Autowired
    private ICommentService commentService;

    @PostMapping(value = "/api/comment" ,consumes = {"application/json"})
    public ResponseEntity<Object> createComment(@RequestBody CommentDTO commentDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof MyUser) {
            MyUser user = (MyUser) authentication.getPrincipal();
            CommentDTO savedComment = commentService.save(commentDTO, user);
            return ResponseEntity.ok(savedComment);
        } else {
            // Handle the case when the user is not authenticated
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @DeleteMapping(value = "/api/comment")
    public void deleteComment(@RequestBody Long [] ids){
        commentService.delete(ids);
    }
}
