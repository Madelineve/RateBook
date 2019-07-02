package com.magda.projekt.controller;

import com.magda.projekt.dao.ratesDao;
import com.magda.projekt.dao.userDao;
import com.magda.projekt.dao.wantDao;
import com.magda.projekt.entity.Rates;
import com.magda.projekt.entity.WantToRead;
import com.magda.projekt.upload.repository.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class BookController {

    @Autowired
    public BookController(){}


    @RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
    public String profilePage(Principal principal) {

        return "uploadView";
    }

    @Autowired
    private DBFileRepository dao1;
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String booksPage(Model m) {

        m.addAttribute("booklist", dao1.findAll());

        return "books";
    }


    @RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
    public String profilePage(@RequestParam("id") int id) {


        dao1.deleteById(id);

        return "redirect:/books";
    }

    @Autowired
    private ratesDao daoRate;

    @Autowired
    private userDao daoUser;

    @RequestMapping(value = "/addRate", method = RequestMethod.POST)
    public String bookPagePOST(@ModelAttribute(value = "bookNew") Rates rate, Principal principal, @ModelAttribute("id") Integer idBook, @ModelAttribute("rate") float value) {


        Integer loggedUserId = daoUser.findByLogin(principal.getName()).getUserid();
        rate.setUser(loggedUserId);
        rate.setBook(idBook);
        rate.setRate(value);
        daoRate.save(rate);

        return "redirect:books";
    }

    @RequestMapping(value = "/addRate", method = RequestMethod.GET)
    public String bookPage(Model m) {

        m.addAttribute("bookNew", new Rates());

        return "books";
    }


    @Autowired
    private wantDao daoWant;

    @RequestMapping(value = "/addWant", method = RequestMethod.POST)
    public String wantPagePOST(@ModelAttribute(value = "bookNew1") WantToRead book, Principal principal, @ModelAttribute("id") Integer idBook) {


        Integer loggedUserId = daoUser.findByLogin(principal.getName()).getUserid();
        book.setUser(loggedUserId);
        book.setBook(idBook);
        daoWant.save(book);

        return "redirect:books";
    }

    @RequestMapping(value = "/addWant", method = RequestMethod.GET)
    public String wantPage(Model m) {

        m.addAttribute("bookNew1", new WantToRead());

        return "books";
    }
}
