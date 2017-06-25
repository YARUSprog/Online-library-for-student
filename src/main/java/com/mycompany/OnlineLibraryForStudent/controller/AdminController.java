
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
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    
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
    
    @RequestMapping(value = "/admin_page", method = RequestMethod.GET)
    public String getAdminPage(@RequestParam(value = "content_id", required = false) String content_id, 
                               @RequestParam(value = "command", required = false) String command,
                               @RequestParam(value = "article_id", required = false) Long article_id,
                                Model model){
        if("admin_article-list".equals(content_id))
            model.addAttribute("articles", articleService.getAll());
        if("delete_article".equals(command))
            articleService.delete(article_id);
        if("load_article".equals(command)){
            model.addAttribute("article", articleService.findById(article_id));
            model.addAttribute("conferences", conferenceService.getAll());
            model.addAttribute("subjects", subjectService.getAll());
            
        }
        model.addAttribute("content_id", content_id); 
        
        
        return "/admin_page";
    }
    
    @RequestMapping(value = "/admin_page", method = RequestMethod.POST)
    public String createArticle(@RequestParam(value = "content_id", required = false) String content_id,                                 
                                Model model){
        model.addAttribute("content_id", content_id); 
             
        return "/admin_page";
    }
    
    
}
