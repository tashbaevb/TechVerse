package com.example.makersprojectbackend.controller;

import com.example.makersprojectbackend.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/ws/comment")
    public void handleComment(CommentDto commentDto) {
        messagingTemplate.convertAndSend("/text/comments", commentDto);
    }
}
