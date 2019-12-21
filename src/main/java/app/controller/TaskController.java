package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entity.Task;

@Controller
@RequestMapping("/task")
public class TaskController {

	@GetMapping
	public String index(Model model) {
		return "task/index";
	}

	@GetMapping("/add")
	public String add(@ModelAttribute("task") Task task) {
		return "task/add";
	}

}
