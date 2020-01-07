package app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import app.entity.User;
import app.service.EmailService;

@Aspect
@Component
@Order(1)
public class UserAOP {

	@Autowired
	private EmailService emailService;

	@Pointcut("execution(* app.service.UserServiceImpl.register(..))")
	public void afterUserRegister() {
	}

	@After("afterUserRegister() && args(user)")
	public void afterUserRegisterAdvice(JoinPoint joinPoint, User user) {

		String to = user.getUsername();
		String subject = "Successfull user registration!";
		String message;

		message = "Success registering!";
		message += "/n Username: " + user.getUsername();
		message += "/n Password: " + user.getPasswordConfirm();
		message += "/n Activation Code: " + user.getActivationCode();

		emailService.sendMessage(to, subject, message);
	}

}
