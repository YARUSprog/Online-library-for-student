
package com.mycompany.OnlineLibraryForStudent.controller;

import com.mycompany.OnlineLibraryForStudent.service.ArticleService;
import com.mycompany.OnlineLibraryForStudent.service.ConferenceService;
import com.mycompany.OnlineLibraryForStudent.service.SubjectService;
import com.mycompany.OnlineLibraryForStudent.service.UserRolesService;
import com.mycompany.OnlineLibraryForStudent.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author YARUS
 */
@Controller
public class MainController {
    
    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    
    @Autowired
    ArticleService articleService;
    
    @Autowired
    ConferenceService conferenceService;
    
    @Autowired
    SubjectService subjectService;
    
    @Autowired
    UserRolesService userRolesService;
    
    @Autowired
    UsersService usersService;
        
    
    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String getHome(
                          @RequestParam(value = "groupId", required = false) Integer groupId, 
                          @RequestParam(value = "searchText", required = false)  String searchText,  
                          @RequestParam(value = "searchType", required = false)  String searchType,  
                          @RequestParam(value = "searchConf", required = false)  String searchConf, 
                          @RequestParam(value = "searchSubject", required = false)  String searchSubject, 
                          @RequestParam(value = "searchYear", required = false) Integer searchYear,
                          Model model
    )
    {   
        Long countArticles = articleService.getCountByFilters(searchText, searchType, searchConf, searchSubject, searchYear);
        int countGroup = articleService.getCountGroupArticles(countArticles.intValue());        
        groupId = articleService.validateGroupId(groupId, countGroup);
        model.addAttribute("currentGroup", groupId);
        model.addAttribute("countGroups", countGroup);        
        model.addAttribute("startGroup", articleService.getStartGroupPagination(groupId, countGroup));
        model.addAttribute("endGroup", articleService.getEndGroupPagination(groupId, countGroup));        
        model.addAttribute("allArticles", articleService.findByFilters(searchText, searchType, 
                                                                       searchConf, searchSubject, 
                                                                       searchYear, groupId));
        model.addAttribute("allConferences", conferenceService.getAll());
        model.addAttribute("allSubjects", subjectService.getAll());
        
        model.addAttribute("searchText", searchText);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchConf", searchConf);
        model.addAttribute("searchSubject", searchSubject);
        model.addAttribute("searchYear", searchYear);        
        return "index";
    }
        
    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public String getArticle(Model model, @RequestParam(value = "article_id", required = false) Long article_id){        
        model.addAttribute("article", articleService.findById(article_id));     
        return "/article";
    }
    
    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String getContacts(Model model){
        
             
        return "/contacts";
    }
    
    
    
}
