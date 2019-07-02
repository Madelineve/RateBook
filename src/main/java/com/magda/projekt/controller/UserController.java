package com.magda.projekt.controller;

import com.magda.projekt.dao.friendsDao;
import com.magda.projekt.dao.userDao;
import com.magda.projekt.entity.User;
import com.magda.projekt.upload.repository.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private userDao dao;

    @Autowired
    private friendsDao daoFiends;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {

        return "login";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model m) {

        m.addAttribute("user", new User());

        return "register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPagePOST(@ModelAttribute(value = "user") User user) {


        user.setPassword(passwordEncoder.encode(user.getPassword()));

        dao.save(user);

        return "redirect:/login";
    }


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profilePage(Model m, Principal principal) {

        m.addAttribute("user", dao.findByLogin(principal.getName()));

        return "profile";
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String usersPage(Model m, Principal principal) {


        int loggedUserId = dao.findByLogin(principal.getName()).getUserid();

        m.addAttribute("inviteList", daoFiends.findByUser2(loggedUserId));
        m.addAttribute("friendsList3", daoFiends.findByUser1(loggedUserId));

        m.addAttribute("userlist", dao.findAll());

        return "users";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String profilePage(Principal principal, @RequestParam("id") int id) {

        int loggedUserId = dao.findByLogin(principal.getName()).getUserid();

        dao.deleteById(id);
        if (id == loggedUserId)
            return "redirect:/logout";

        return "delete";
    }


    @RequestMapping(value = "/deleteInv", method = RequestMethod.GET)
    public String profilePage1(@RequestParam("id") int id) {

        daoFiends.deleteById(id);

        return "redirect:users";
    }


}
