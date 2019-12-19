package app.scheduled;

import java.nio.channels.InterruptedByTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class Deadline {

	Logger logger = LoggerFactory.getLogger(Deadline.class);

	@Async
	@Scheduled(fixedRate = 3600000)
	public void scheduledDeadlineTaskCheck() throws InterruptedByTimeoutException {

	}

}
