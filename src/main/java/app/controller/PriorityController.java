package app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entity.Priority;
import app.service.PriorityService;

@Controller
@RequestMapping("priority")
public class PriorityController {

	@Autowired
	private PriorityService priorityService;

	@GetMapping
	public String index(Model model) {

		model.addAttribute("priorities", priorityService.findAll());

		return "priority/index";
	}

	@GetMapping("/add")
	public String add(@ModelAttribute("priority") Priority priority) {
		return "priority/add";
	}

	@PostMapping("/add")
	public String add(@Valid @ModelAttribute("priority") Priority priority, BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return "priority/add";

		priorityService.save(priority);

		return "redirect:/priority";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") Long id) {

		model.addAttribute("priority", priorityService.findById(id));

		return "priority/add";
	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable("id") Long id) {

		priorityService.deleteById(id);

		return "redirect:/priority";
	}

}
