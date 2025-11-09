package com.support.desk.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TicketCommentDto {
	
	@Size(min=1, max=1000)
     private String content;
	
	private LocalDateTime createdAt;
}
