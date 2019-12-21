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

import app.entity.Role;
import app.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping
	public String index(Model model) {

		model.addAttribute("roles", roleService.findAll());

		return "role/index";
	}

	@GetMapping("/add")
	public String add(@ModelAttribute("role") Role role) {
		return "role/add";
	}

	@PostMapping("/add")
	public String add(@Valid @ModelAttribute("role") Role role, BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return "role/add";

		roleService.save(role);

		return "redirect:/role";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") Long id) {
		model.addAttribute("role", roleService.findById(id));

		return "role/add";
	}

	@PutMapping("/add")
	public String update(@Valid @ModelAttribute("role") Role role, BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return "role/add";

		return "redirect:/role";
	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable("id") Long id) {

		roleService.deleteById(id);

		return "redirect:/role";
	}

}
