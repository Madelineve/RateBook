package com.magda.projekt.controller;

import com.magda.projekt.dao.ratesDao;
import com.magda.projekt.dao.userDao;
import com.magda.projekt.entity.Rates;
import com.magda.projekt.upload.repository.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class RatesController {


    @Autowired
    private ratesDao dao;

    @Autowired
    private userDao daoUser;

    @Autowired
    private DBFileRepository daoBook;

    @RequestMapping(value = "/rates", method = RequestMethod.GET)
    public String profilePage(Principal principal, Model m, Model n) {

        int loggedUserId = daoUser.findByLogin(principal.getName()).getUserid();
        m.addAttribute("ratelist", dao.findByUser(loggedUserId));
        n.addAttribute("readBookList", daoBook.findAll());
        return "rates";
    }


    @RequestMapping(value = "/deleteFromRead", method = RequestMethod.GET)
    public String profilePage(@RequestParam("id") int id) {


        dao.deleteById(id);
        return "redirect:/rates";
    }

    @RequestMapping(value = "/editRate", method = RequestMethod.POST)
    public String editPage(@RequestParam("id") Integer id, @ModelAttribute(value = "bookEdit") Rates book) {

        Rates rate = dao.findById(id).get();

        rate.setRate(book.getRate());

        dao.save(rate);
        return "redirect:/rates";
    }

       /* @RequestMapping(value = "/editRate", method = RequestMethod.POST)
    public String editPage(@RequestParam("id") int id, Model m, @ModelAttribute(value = "bookEdit") Rates book, Principal principal) {


        dao.setRateInfoById(book.getRate(),id);
        return "redirect:/rates";
    }
*/


}
