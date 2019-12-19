package app.scheduled;

import java.nio.channels.InterruptedByTimeoutException;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

@EnableAsync
public class Deadline {
	
	@Async
	@Scheduled(fixedRate = 3600000)
	public void scheduleDeadlineTaskCheck() throws InterruptedByTimeoutException {
		
	}

}
