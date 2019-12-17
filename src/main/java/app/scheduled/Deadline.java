package app.scheduled;

import java.nio.channels.InterruptedByTimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

import app.service.TaskService;

@EnableAsync
public class Deadline {

	@Autowired
	private TaskService taskService;
	
	@Async
	@Scheduled(fixedRate = 3600000)
	public void scheduleDeadlineTaskCheck() throws InterruptedByTimeoutException {
		
	}

}
