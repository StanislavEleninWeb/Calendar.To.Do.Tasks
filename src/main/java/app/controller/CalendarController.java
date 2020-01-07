package app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("calendar")
public class CalendarController {

	@GetMapping
	public String index(Model model) {

		DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM/yyyy");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate currentDate = LocalDate.now();
		LocalDate startDate = currentDate.minusMonths(2);
		LocalDate endDate = currentDate.plusMonths(3);

		List<LocalDate> months = new ArrayList<LocalDate>();

		for (LocalDate month = startDate; month.isBefore(endDate); month = month.plusMonths(1)) {
			System.err.println("\n " + month.format(monthFormatter));

			months.add(month);

			LocalDate firstDayOfMonth = month.with(TemporalAdjusters.firstDayOfMonth());
			LocalDate lastDayOfMonth = month.with(TemporalAdjusters.lastDayOfMonth());

			for (LocalDate date = firstDayOfMonth; date.isBefore(lastDayOfMonth.plusDays(1)); date = date.plusDays(1)) {
				System.out.println("\n " + date.format(dateFormatter));

			}

		}

		return "calendar/index";
	}

}
