package com.bushemi.controller;

import com.bushemi.filter.LoginFilter;
import com.bushemi.model.*;
import com.bushemi.model.entity.HobbyDto;
import com.bushemi.model.entity.PersonDto;
import com.bushemi.model.entity.PlaceDto;
import com.bushemi.model.entity.PostDto;
import com.bushemi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * Created by igor on 10.10.17.
 * useless comment
 */
@Controller
public class MyPageController {
    @Autowired
    private EntityFindingByIdService entityFindingByIdService;
    @Autowired
    private PostService postService;
    @Autowired
    private PersonService personService;
    @Autowired
    private InterestService interestService;
    @Autowired
    private MessageService messageService;
    Long id;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String getLogin(Model model, HttpServletRequest request) {

        try {
            id = getIdFromSession(request);
        }catch(NumberFormatException nfe){return "LoginPage";}

        PersonInfo myself = getMyPersonInfoAndHideMyId(id);
        request.getSession().setAttribute("personInfo", myself);

        getMyPlaces(request);
        getMyPosts(request);
        getMyFriends(request);
        getMyMessages(request);
        getMyHobbies(request);

        getAllPersons(request);
        getAllPosts(request);
        return "MyPage";
    }



    @ResponseBody
    @RequestMapping(value = "/myPlaces", method = RequestMethod.GET)
    public String getMyPlaces(HttpServletRequest request) {
        try {
            id = getIdFromSession(request);
        }catch(NumberFormatException nfe){return "LoginPage";}
        request.getSession().setAttribute("placesInfo",
                entityFindingByIdService.getPlacesDtoByPersonId(id).stream().map(PlaceInfo::new).collect(Collectors.toList()));
        request.getSession().setAttribute("listOfPlaces",
                interestService.getAllPlaces().stream().map(PlaceInfo::new).collect(Collectors.toList()));
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/myHobbies", method = RequestMethod.GET)
    public String getMyHobbies(HttpServletRequest request) {
        try {
            id = getIdFromSession(request);
        }catch(NumberFormatException nfe){return "LoginPage";}
        request.getSession().setAttribute("hobbiesInfo",
                entityFindingByIdService.getHobbiesDtoByPersonId(id).stream().map(HobbyInfo::new).collect(Collectors.toList()));
        request.getSession().setAttribute("listOfHobbies",
                interestService.getAllHobbies().stream().map(HobbyInfo::new).collect(Collectors.toList()));
        return "";
    }
    @ResponseBody
    @RequestMapping(value = "/myFriends", method = RequestMethod.GET)
    public String getMyFriends(HttpServletRequest request) {
        try {
            id = getIdFromSession(request);
        }catch(NumberFormatException nfe){return "LoginPage";}
        request.getSession().setAttribute("friendshipsInfo",
                entityFindingByIdService.getFriendshipsDtoByPersonId(id).stream().map(FriendshipInfo::new).collect(Collectors.toList()));
        getAllPersons(request);
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/myPosts", method = RequestMethod.GET)
    public String getMyPosts(HttpServletRequest request) {
        try {
            id = getIdFromSession(request);
        }catch(NumberFormatException nfe){return "LoginPage";}
        request.getSession().setAttribute("postsInfo",
                entityFindingByIdService.getPostsDtoByOwnerId(id).stream().map((e) -> new PostInfo(e, postService.countLikesForPost(e))).collect(Collectors.toList()));
        getAllPosts(request);
        return "OK";
    }

    @ResponseBody
    @RequestMapping(value = "/myMessages", method = RequestMethod.GET)
    public String getMyMessages(HttpServletRequest request) {

        try {
            id = getIdFromSession(request);
        }catch(NumberFormatException nfe){return "LoginPage";}
        request.getSession().setAttribute("messagesInfo",
                entityFindingByIdService.getMessagesDtoByPersonId(id).stream().map(MessageInfo::new).collect(Collectors.toList()));
        getAllPersons(request);
        return "";
    }


    @ResponseBody
    @RequestMapping(value = "/newMessage", method = RequestMethod.POST)
    public String makeMessage(@RequestBody MessageInfo messageInfo, HttpServletRequest request) {
        try {
            id = getIdFromSession(request);
        }catch(NumberFormatException nfe){return "LoginPage";}
        PersonDto personFrom = personService.findPersonById(id);
        PersonDto personTo = personService.findPersonByNickname(messageInfo.getNicknamePersonTo());
        messageService.createMessage(personFrom, personTo, messageInfo.getContent());
        return "OK";
    }

    @ResponseBody
    @RequestMapping(value = "/newPost", method = RequestMethod.POST)
    public String makePost(@RequestBody PostInfo postInfo, HttpServletRequest request) {
        try {
            id = getIdFromSession(request);
        }catch(NumberFormatException nfe){return "LoginPage";}
        PersonDto owner = personService.findPersonById(id);
        postService.createNewPost(owner,postInfo.getTitle(),postInfo.getContent());
        return "OK";
    }
    @ResponseBody
    @RequestMapping(value = "/newHobby", method = RequestMethod.POST)
    public String makeHobby(@RequestBody HobbyInfo hobbyInfo, HttpServletRequest request) {
        try {
            id = getIdFromSession(request);
        }catch(NumberFormatException nfe){return "LoginPage";}
        PersonDto person = personService.findPersonById(id);
        HobbyDto hobby;
        if (hobbyInfo.getId() > 0) {hobby = interestService.getHobbyById(hobbyInfo.getId());}
        else{
            hobby = new HobbyDto();
            hobby.setDescription(hobbyInfo.getDescription());
            hobby.setTitle(hobbyInfo.getTitle());
        }
        interestService.addPersonHobby(person, hobby);
        return "OK";
    }
    @ResponseBody
    @RequestMapping(value = "/newPlace", method = RequestMethod.POST)
    public String makePlace(@RequestBody PlaceInfo placeInfo, HttpServletRequest request) {
        try {
            id = getIdFromSession(request);
        }catch(NumberFormatException nfe){return "LoginPage";}
        PersonDto person = personService.findPersonById(id);
        PlaceDto place;
        if (placeInfo.getId() > 0) {
            place = interestService.getPlaceById(placeInfo.getId());
        }else {
            place = new PlaceDto();
            place.setTitle(placeInfo.getTitle());
            place.setDescription(placeInfo.getDescription());
            place.setLatitude(placeInfo.getLatitude());
            place.setLongitude(placeInfo.getLongitude());
        }
        interestService.addPersonPlace(person, place);
        return "OK";
    }
    @ResponseBody
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public String makeLike(@RequestBody PostInfo postInfo, HttpServletRequest request) {
        try {
            id = getIdFromSession(request);
        }catch(NumberFormatException nfe){return "LoginPage";}
        PersonDto person = personService.findPersonById(id);
        PostDto post = entityFindingByIdService.getPostsDtoById(postInfo.getId());
        postService.createLike(person, post);
        return "OK";
    }

    @ResponseBody
    @RequestMapping(value = "/newFriend", method = RequestMethod.POST)
    public String newFriend(@RequestBody PersonInfo personInfo, HttpServletRequest request) {
        try {
            id = getIdFromSession(request);
        }catch(NumberFormatException nfe){return "LoginPage";}
        PersonDto person = personService.findPersonById(id);
        PersonDto personDtoById = entityFindingByIdService.getPersonDtoById(personInfo.getId());
        personService.addFriendShip(person, personDtoById);
        return "OK";
    }
    @ResponseBody
    @RequestMapping(value = "/endFriendship", method = RequestMethod.POST)
    public String unFriendship(@RequestBody PersonInfo personInfo, HttpServletRequest request) {
        try {
            id = getIdFromSession(request);
        }catch(NumberFormatException nfe){return "LoginPage";}
        PersonDto person = personService.findPersonById(id);
        PersonDto friend = personService.findPersonByNickname(personInfo.getNickname());
        personService.removeFriendship(person, friend);
        return "OK";
    }
    @ResponseBody
    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exit(HttpServletRequest request) {
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute(LoginFilter.LOGIN_ATTR);
        return "OK";
    }



    private void getAllPosts(HttpServletRequest request) {
        request.getSession().setAttribute("listAllPosts",
                postService.getAllPosts().stream().map((e) -> new PostInfo(e, postService.countLikesForPost(e))).collect(Collectors.toList()));
    }

    private long getIdFromSession(HttpServletRequest request) {
        long id;
        String myId = String.valueOf(request.getSession().getAttribute("id"));
        id = Long.parseLong(myId);
        return id;
    }

    private PersonInfo getMyPersonInfoAndHideMyId(long id) {
        PersonInfo personInfo = new PersonInfo(entityFindingByIdService.getPersonDtoById(id));
        personInfo.setId(0l);
        return personInfo;
    }
    private void getAllPersons(HttpServletRequest request){
        request.getSession().setAttribute("listOfPersons",
                personService.findAllPersons().stream().map(PersonInfo::new).collect(Collectors.toList()));
    }
}
