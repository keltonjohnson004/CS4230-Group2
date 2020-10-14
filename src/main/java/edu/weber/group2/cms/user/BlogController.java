package edu.weber.group2.cms.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(("blog"))
public class BlogController {
	@RequestMapping("blog")
    public String getBlogPage(Model model) {
    	return "blog/blog";
    }
}
