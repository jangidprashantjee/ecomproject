package com.support.desk.dto;

import java.time.LocalDateTime;

import com.support.desk.model.TicketPriority;
import com.support.desk.model.TicketStatus;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TicketDetailsUpdatedDto {
	
    private Long ticketId;
    @Size(max = 1000)
    private String content;
    private LocalDateTime createdAt;
    private TicketPriority priority;
    private TicketStatus status;
	
}
