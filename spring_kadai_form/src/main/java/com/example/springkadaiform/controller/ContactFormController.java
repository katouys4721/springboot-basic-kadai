package com.example.springkadaiform.controller;

import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {
	@GetMapping("/form")
	public String openForm(Model model) {
		if(!model.containsAttribute("contactForm")) {
//			ビューにフォームクラスのインスタンスを渡す
			model.addAttribute("contactForm", new ContactForm());
		}
		
		return "contactFormView";
	}
	
	@PostMapping("/confirm")
	public String confirmForm(RedirectAttributes redirectAttributes,
			@Validated ContactForm form, BindingResult result) {
		
//		バリデーションエラーがあったら終了
		if(result.hasErrors()) {
//			フォームクラスをビューに受け渡す
			redirectAttributes.addFlashAttribute("contactForm",form);
//			バリデーション結果を受け渡す
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX
					+ Conventions.getVariableName(form), result);
			
			return "contactFormView";
		}
		return "confirmView";
	}
}
