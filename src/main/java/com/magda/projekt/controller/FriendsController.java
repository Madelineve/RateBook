package com.magda.projekt.controller;


import com.magda.projekt.dao.friendsDao;
import com.magda.projekt.dao.userDao;
import com.magda.projekt.entity.Friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class FriendsController {

    @Autowired
    private friendsDao dao;

    @Autowired
    private userDao daoUser;

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public String addFriend(@RequestParam("id") int id, Principal principal) {

        Friends rel = new Friends();
        Integer loggedUserId = daoUser.findByLogin(principal.getName()).getUserid();
        rel.setUser1(loggedUserId);
        rel.setUser2(id);
        rel.setState(1);
        dao.save(rel);

        return "redirect:users";
    }

    @RequestMapping(value = "/accept", method = RequestMethod.GET)
    public String acceptFriend(@RequestParam("id") Integer id, Principal principal) {


        Integer loggedUserId = daoUser.findByLogin(principal.getName()).getUserid();

        List<Friends> list= dao.findByUser1(id);
        List<Friends> list1 = dao.findByUser2(loggedUserId);

        for(Friends e: list1) {
            for (Friends q : list) {
                if (e.getUser1().equals(q.getUser1()) && e.getUser2().equals(q.getUser2())) {

                    Friends rel = e;
                    rel.setState(2);
                    dao.save(rel);

                }
            }
        }


        return "redirect:users";
    }


}
