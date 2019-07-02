package com.magda.projekt.controller;

import com.magda.projekt.dao.userDao;
import com.magda.projekt.dao.wantDao;
import com.magda.projekt.upload.repository.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class WantToReadController {

    @Autowired
    private wantDao dao;

    @Autowired
    private userDao daoUser;

    @Autowired
    private DBFileRepository daoBook;

    @RequestMapping(value = "/deleteFromWant", method = RequestMethod.GET)
    public String profilePage(@RequestParam("id") int id) {

        dao.deleteById(id);
        return "redirect:wantRead";
    }



    @RequestMapping(value = "/wantRead", method = RequestMethod.GET)
    public String profilePage(Principal principal, Model m, Model n) {

        int loggedUserId = daoUser.findByLogin(principal.getName()).getUserid();
        m.addAttribute("wantlist", dao.findByUser(loggedUserId));
        n.addAttribute("readBookList", daoBook.findAll());
        return "wantRead";
    }


}
