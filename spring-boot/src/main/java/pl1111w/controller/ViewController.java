package pl1111w.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/3/28 14:26
 */
@Controller
public class ViewController {

    @GetMapping("view")
    public String view(Model model) {
        model.addAttribute("msg", "message");
        model.addAttribute("link", "https://www.baidu.com/");
        return "success";
    }
}
