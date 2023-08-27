package com.F0rchy.Entropy.controllers;

import com.F0rchy.Entropy.models.News;
import com.F0rchy.Entropy.repo.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<News> news = newsRepository.findAllByOrderByIdDesc();
        model.addAttribute("title", "Главная страница | Entropy");
        model.addAttribute("news", news);
        return "home";
    }

    @GetMapping("/articles")
    public String articles(Model model) {
        model.addAttribute("title", "Игровые статьи | Entropy");
        return "articles";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "О нас | Entropy");
        return "about";
    }
}
