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
        return "contactFormView";
    }

    // フォーム送信 → 確認画面へ（バリデーションあり）
    @PostMapping("/confirm")
    public String confirm(
            @Valid ContactForm contactForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("contactForm", contactForm);

            // ★ バリデーションエラーを Flash Attribute に保存するための特別なキー
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.contactForm",
                    bindingResult
            );

            return "redirect:/form";
        }

        // 正常時は PRG パターンで確認画面へ
        redirectAttributes.addFlashAttribute("contactForm", contactForm);
        return "redirect:/confirm";
    }

    // 確認画面の表示（GET で確認画面を表示する）
    @GetMapping("/confirm")
    public String showConfirm(Model model) {

        // 直接アクセスした場合はフォームへ
        if (!model.containsAttribute("contactForm")) {
            return "redirect:/form";
        }

        return "confirmView";
    }
}
