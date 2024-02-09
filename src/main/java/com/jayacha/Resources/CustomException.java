package com.jayacha.Resources;

import com.jayacha.Model.User;

@SuppressWarnings("serial")
public class CustomException extends Exception{
	
		private User user = null;
		private String message;
		
		public CustomException() {
			
		}
		
		public CustomException(String messageString) {
			this.setMessage(messageString);
		}
		
		public CustomException(User user , String messageString) {
			this.setUser(user);
			this.setMessage(messageString);
		}
		
		public User getUser() {
			return user;
		}
		
		public void setUser(User user) {
			this.user = user;
		}
		
		public String getMessage() {
			return message;
		}
		
		public void setMessage(String message) {
			this.message = message;
		}
		
}
