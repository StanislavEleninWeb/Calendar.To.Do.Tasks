package app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import app.service.EmailService;

@Aspect
@Component
@Order(1)
public class UserAOP {

	@Autowired
	private EmailService emailService;

	@Pointcut("execution(* app.repository.UserRepository.save(..))")
	public void afterUserRegister() {
	}

	@After("afterUserRegister()")
	public void afterUserRegisterAdvice(JoinPoint joinPoint) {

		String to;
		String subject = "Successfull user registration!";
		String message;

		Object[] args = joinPoint.getArgs();

		System.out.println(args);

		message = "=========================>>>>>>>>> Method Signature \n";
		message += joinPoint.getSignature().toString() + "\n";
		message += "=========================>>>>>>>>> User credentials \n";

		//emailService.sendMessage(to, subject, message);
	}

}
