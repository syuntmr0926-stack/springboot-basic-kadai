package com.example.springkadaiform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;

import jakarta.validation.Valid;

@Controller
public class ContactFormController {
	
	// 入力フォームの表示
    @GetMapping("/form")
    public String showForm(Model model) {
        if (!model.containsAttribute("contactForm")) {
            model.addAttribute("contactForm", new ContactForm());
        }
        return "contactFormView"; // 
    }

    // フォーム送信→確認画面 or バリデーションエラー時は入力画面
    @PostMapping("/contact/confirm")
    public String confirm(
        @Valid ContactForm contactForm,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes,
        Model model ) {
    	
        if (bindingResult.hasErrors()) {
        	 redirectAttributes.addFlashAttribute("contactForm", contactForm);

             // エラー情報を保持（特別なキー名が必要）
             redirectAttributes.addFlashAttribute(
                 "org.springframework.validation.BindingResult.contactForm",
                 bindingResult
             );

             // ★ リダイレクトしてもエラーを表示できる
             return "redirect:/form";
        }
        // OKなら入力内容を確認画面（confirmView.html）へ
        model.addAttribute("contactForm", contactForm);
        return "confirmView";
    }
}