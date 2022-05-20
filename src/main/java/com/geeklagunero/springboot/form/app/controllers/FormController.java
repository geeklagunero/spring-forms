package com.geeklagunero.springboot.form.app.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import com.geeklagunero.springboot.form.app.models.domain.Usuario;

@Controller
public class FormController {
	
	@GetMapping("/form")
	public String form(Model model) {

		Usuario usuario = new Usuario();
		
		model.addAttribute("titulo", "Formulario Usuarios");
		model.addAttribute("usuario", usuario);
		return "form";
	}
	
	/*
	 * @PostMapping("/form") public String procesar(Model model, @RequestParam
	 * String username, @RequestParam String password, @RequestParam String email) {
	 * 
	 * Usuario usuario = new Usuario(); usuario.setUsername(username);
	 * usuario.setPassword(password); usuario.setEmail(email);
	 * 
	 * 
	 * model.addAttribute("usuario", usuario); model.addAttribute("titul",
	 * "Resultado del formulario");
	 * 
	 * return "resultado"; }
	 */
	
	
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model) {
		
		model.addAttribute("titul", "Resultado del formulario");
		
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), "El campo ".concat(err.getField().concat(" ").concat(err.getDefaultMessage())));
			});
			model.addAttribute("error", errores);
			
			return "form";
		}
		
		model.addAttribute("usuario", usuario);
		
		
		return "resultado";
	}
		
}
