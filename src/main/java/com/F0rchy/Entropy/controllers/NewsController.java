package com.F0rchy.Entropy.controllers;

import com.F0rchy.Entropy.models.News;
import com.F0rchy.Entropy.repo.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/news")
    public String news(Model model) {
        Iterable<News> news = newsRepository.findAll();
        model.addAttribute("news", news);
        model.addAttribute("title", "Новости сайта");
        return "news";
    }

    @GetMapping("/news/add")
    public String newsAdd(Model model) {
        return "news-add";
    }

    @PostMapping("/news/add")
    public String newsPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, @RequestParam String author, @RequestParam String date, Model model) {
        News news = new News(title, anons, full_text, author, date);
        newsRepository.save(news);
        return "redirect:/news";
    }
}
