package com.afei.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.afei.model.SessionUser;
import com.afei.util.Constants;

@Controller
@RequestMapping("/aboutController")
public class AboutController {

	@RequestMapping("/showAbout")
	public String showBorrows(Model model,HttpSession session) {
		SessionUser sessionUser= (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		model.addAttribute("sessionUser", sessionUser);
		return "pages/about";
	}
}
