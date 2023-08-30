package com.F0rchy.Entropy.controllers;

import com.F0rchy.Entropy.models.Articles;
import com.F0rchy.Entropy.repo.ArticlesRepository;
import com.F0rchy.Entropy.models.News;
import com.F0rchy.Entropy.repo.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private ArticlesRepository articlesRepository;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<News> news = newsRepository.findAllByOrderByIdDesc();
        Iterable<Articles> articles = articlesRepository.findAllByOrderByIdDesc();

        model.addAttribute("title", "Главная страница | Entropy");
        model.addAttribute("news", news);
        model.addAttribute("articles", articles);

        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "О нас | Entropy");
        return "about";
    }

    @GetMapping("/finding")
    public String finding(@ModelAttribute ("text") String text, Model model) {
        if (!text.isEmpty()) {
            Iterable<News> news = newsRepository.findByTitleContainingIgnoreCase(text);
            Iterable<Articles> articles = articlesRepository.findByTitleContainingIgnoreCase(text);

            model.addAttribute("news", news);
            model.addAttribute("articles", articles);
        }

        model.addAttribute("title", "Поиск по сайту | Entropy");

        return "finding";
    }

    @PostMapping("/finding")
    public String finding(@RequestParam String text, Model model, RedirectAttributes ra) {
        ra.addFlashAttribute("text", text);

        return "redirect:/finding";
    }
}
