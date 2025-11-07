package com.example.springkadaitodo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springkadaitodo.entity.ToDo;
import com.example.springkadaitodo.service.ToDoSevice;

@Controller
public class ToDoController {
	private final ToDoSevice todoService;
	
	public ToDoController(ToDoSevice todoService) {
		this.todoService = todoService;
	}
	
	@GetMapping("/todo")
	public String ToDo(Model model) {
		List<ToDo> todos = todoService.getAllToDo();
		
		model.addAttribute("todos", todos);
		
		return "todoView";
	}
}
