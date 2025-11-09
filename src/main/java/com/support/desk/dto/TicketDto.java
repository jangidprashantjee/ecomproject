package com.support.desk.dto;

import com.support.desk.model.User;

import lombok.Data;

import java.util.List;
import java.util.ArrayList;
import com.support.desk.model.TicketComment;

import com.support.desk.model.TicketStatus;


@Data
public class TicketDto {

	    private Long ticketId;
	    private String title;
	    private String description;
	    private String creationTime;
	    private String resolutionTime;
	    private TicketStatus status;
	    private User assignedAgent;
	    private List<TicketComment>comments=new ArrayList<>();
}
