package com.jfcorp.picpaysimplificado.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jfcorp.picpaysimplificado.domain.transaction.Transaction;
import com.jfcorp.picpaysimplificado.domain.user.User;
import com.jfcorp.picpaysimplificado.dtos.TransactionDTO;
import com.jfcorp.picpaysimplificado.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private NotificationService notificationService;
	
	public Transaction createTransaction(TransactionDTO transaction) throws Exception {
		User sender = this.userService.findUserById(transaction.senderId());
		User receiver = this.userService.findUserById(transaction.receiverId());
		
		userService.validateTransaction(sender, transaction.value());
		
		boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
		if(!isAuthorized) {
			throw new Exception("Transação não autorizada.");
		}
		
		Transaction newTransaction = new Transaction();
		newTransaction.setBalance(transaction.value());
		newTransaction.setSender(sender);
		newTransaction.setReceiver(receiver);
		newTransaction.setTimestamp(LocalDateTime.now());
		
		sender.setBalance(sender.getBalance().subtract(transaction.value()));
		receiver.setBalance(receiver.getBalance().add(transaction.value()));
		
		this.repository.save(newTransaction);
		this.userService.saveUser(sender);
		this.userService.saveUser(receiver);
		
		this.notificationService.sendNotification(sender, "Transação realizada com sucesso.");
		this.notificationService.sendNotification(receiver, "Transação recebida com sucesso.");
		
		return newTransaction;
	}
	
	public boolean authorizeTransaction(User sender, BigDecimal value) {
		//return true;
		
		ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
				
		if(authorizationResponse.getStatusCode() == HttpStatus.OK) {			
			//String message = (String) authorizationResponse.getBody().get("message");
			boolean message = (boolean) authorizationResponse.getBody().get("message");
			//return "Autorizado".equalsIgnoreCase(message);
			return message;
		} else {
			return false;
		}
	}
}
