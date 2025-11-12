package com.support.desk.service;

import java.util.List;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.support.desk.dto.TicketCommentDto;
import com.support.desk.dto.TicketDetailsUpdatedDto;
import com.support.desk.dto.TicketDto;
import com.support.desk.model.Ticket;
import com.support.desk.model.TicketComment;
import com.support.desk.model.TicketPriority;
import com.support.desk.model.TicketStatus;
import com.support.desk.model.User;
import com.support.desk.repository.TicketCommentRepository;
import com.support.desk.repository.TicketRepository;
import com.support.desk.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TicketService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private TicketCommentRepository ticketCommentRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	// create ticket
	
	@Transactional
	public TicketDto createTicket(TicketDto ticketDto,Long userId) {
		User customer = userRepository.findById(userId).get();
		Ticket ticket = new Ticket();
		ticket.setTicketId(ThreadLocalRandom.current().nextLong(100000, 999999));
		ticket.setTitle(ticketDto.getTitle());
		ticket.setDescription(ticketDto.getDescription());
		ticket.setStatus(TicketStatus.OPEN);
		ticket.setPriority(TicketPriority.HIGH);
		ticket.setCustomer(customer);
		ticket.setCreationTime(LocalDateTime.now());
		ticket.setResolutionTime(LocalDateTime.now().plusHours(48));
		Ticket savedTicket = ticketRepository.save(ticket);
		return modelMapper.map(savedTicket, TicketDto.class);
	}
	
	
	@Transactional
	public TicketDto assignTicket(Long ticketId, Long agentId) {
		Ticket ticket = ticketRepository.findByTicketId(ticketId);
		User agent = userRepository.findById(agentId).get();
		ticket.setAssignedAgent(agent);
		Ticket updatedTicket = ticketRepository.save(ticket);
		return modelMapper.map(updatedTicket, TicketDto.class);
	}
	
	
	@Transactional
	public String updateTicket(TicketDetailsUpdatedDto ticketDetailsUpdatedDto) {
		Ticket ticket = ticketRepository.findByTicketId(ticketDetailsUpdatedDto.getTicketId());
	     if(!ticket.getStatus().equals(TicketStatus.RESOLVED)) {
	    	 if(ticketDetailsUpdatedDto.getStatus()!=ticket.getStatus() && ticketDetailsUpdatedDto.getStatus()!=null) {
	    		 ticket.setStatus(ticketDetailsUpdatedDto.getStatus());
	    		 if(ticket.getStatus().equals(TicketStatus.RESOLVED))
	    		 {ticket.setResolutionTime(LocalDateTime.now());}
	    		 
	    	 }
	     }
	     // if content is not empty in new updated dto then add comment
	     if(!ticketDetailsUpdatedDto.getContent().isEmpty())
	     {
	    	 TicketComment ticketComment = new TicketComment();
	    	 ticketComment.setTicket(ticket);
	    	 ticketComment.setUser(ticket.getCustomer());
	    	 ticketComment.setContent(ticketDetailsUpdatedDto.getContent());
	    	 ticketComment.setCreatedAt(LocalDateTime.now());
	    	 ticket.getComments().add(ticketComment);
	     }
         if(ticketDetailsUpdatedDto.getPriority()!=null && ticketDetailsUpdatedDto.getPriority()!=ticket.getPriority()) {
	    	 ticket.setPriority(ticketDetailsUpdatedDto.getPriority());
	     }
		ticketRepository.save(ticket);
		return "Ticket updated successfully";
		}
	
	
	//why transactional here? , each jpa method is transactional by default for read operations 
	//but in service class we call multiple jpa methods so to maintain consistency we use transactional,
	   // get tickets associated with user/customemr
	  @Transactional  
	  public List<TicketDto> getTicketsByUserId(Long userId){
		   User customer = userRepository.findById(userId).get();
		   List<Ticket> tickets = ticketRepository.findByCustomer(customer);
		   List<TicketDto> ticketDtos = tickets.stream()
				   .map(ticket -> modelMapper.map(ticket, TicketDto.class))
				   .toList();
		   return ticketDtos;
	   }
	   
	   
	   //get ticket assigned to agent
	   
	   @Transactional
	   public List<TicketDto> getTicketsByAgentId(Long agentId){
		   User agent = userRepository.findById(agentId).get();
		   List<Ticket> tickets = ticketRepository.findByAssignedAgent(agent);
		   List<TicketDto> ticketDtos = tickets.stream()
				   .map(ticket -> modelMapper.map(ticket, TicketDto.class))
				   .toList();
		   return ticketDtos;
	   }
	   
	   
	   // get tickets by status
	   
	   @Transactional
	   public List<TicketDto> getTicketsByStatus(TicketStatus status){
		   List<Ticket> tickets = ticketRepository.findByStatus(status);
		   List<TicketDto> ticketDtos = tickets.stream()
				   .map(ticket -> modelMapper.map(ticket, TicketDto.class))
				   .toList();
		   return ticketDtos;
	   }
	   
	   // get tickets by department
	   @Transactional
	   public List<TicketDto> getTicketsByDepartment(String department){
		   List<Ticket> tickets = ticketRepository.findByDepartment(department);
		   List<TicketDto> ticketDtos = tickets.stream()
				   .map(ticket -> modelMapper.map(ticket, TicketDto.class))
				   .toList();
		   return ticketDtos;
	   } 
	   
	   
	   // get Total active tickets
	   @Transactional
	   public Long getTotalActiveTicketCount() {
		   Integer size = ticketRepository.findByStatus(TicketStatus.OPEN).size();
		   return size.longValue();
	   }
	   
	   //get comments for a ticket
	   
	   @Transactional
	   public List<TicketCommentDto> getCommentsByTicketId(Long ticketId){
		   Ticket ticket = ticketRepository.findByTicketId(ticketId);
		   List<TicketComment> comments = ticketCommentRepository.findByTicketOrderByCreatedAtAsc(ticket);
		   List<TicketCommentDto> commentDtos = comments.stream()
				   .map(comment -> modelMapper.map(comment, TicketCommentDto.class))
				   .toList();
		   return commentDtos;
	   }
}
