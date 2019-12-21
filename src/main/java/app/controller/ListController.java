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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entity.List;
import app.service.ListService;

@Controller
@RequestMapping("/list")
public class ListController {

	@Autowired
	private ListService listService;

	@GetMapping
	public String index(Model model) {

		model.addAttribute("lists", listService.findAll());

		return "list/index";
	}

	@GetMapping("/add")
	public String add(@ModelAttribute("list") List list) {
		return "list/add";
	}

	@PostMapping("/add")
	public String add(@Valid @ModelAttribute("list") List list, BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return "list/add";

		listService.save(list);

		return "redirect:/list";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") long id) {

		model.addAttribute("list", listService.findById(id));

		return "list/add";
	}

	@PutMapping("/add")
	public String update(@Valid @ModelAttribute("list") List list, BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return "list/add";

		listService.save(list);

		return "redirect:/list";
	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable("id") long id) {

		listService.deleteById(id);

		return "redirect:/list";
	}

}
