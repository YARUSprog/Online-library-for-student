
package com.mycompany.OnlineLibraryForStudent.controller.rest;

import com.mycompany.OnlineLibraryForStudent.model.Article;
import com.mycompany.OnlineLibraryForStudent.model.dto.ArticleDto;
import com.mycompany.OnlineLibraryForStudent.service.ArticleService;
import com.mycompany.OnlineLibraryForStudent.service.ConferenceService;
import com.mycompany.OnlineLibraryForStudent.service.SubjectService;
import com.mycompany.OnlineLibraryForStudent.service.UserRolesService;
import com.mycompany.OnlineLibraryForStudent.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author YARUS
 */
@RestController
public class ArticleRestController {
    
    private static final Logger log = LoggerFactory.getLogger(ArticleRestController.class);
    
    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private  ConferenceService conferenceService;
    
    @Autowired
    private  SubjectService subjectService;
    
    @Autowired
    private  UserRolesService userRolesService;
    
    @Autowired
    private  UsersService usersService;
    
    @RequestMapping(value = "/articles", method = RequestMethod.POST)    
    public void create(ArticleDto productParamDto) {       
        Article article = articleService.addArticle(productParamDto);
        log.info("Article is created!");  
    }

    @RequestMapping(value = "/articles", method = RequestMethod.PUT)    
    public void update( ArticleDto productParamDto) {       
        Article article = articleService.editArticle(productParamDto);
        log.info("Article is updated!");  
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.DELETE)    
    public void delete(@PathVariable Long id) {
        articleService.delete(id);   
        log.info("Article is deleted!");        
    }
             
    @RequestMapping(value = "/articles", method = RequestMethod.DELETE)    
    public void delete2(@RequestParam(value = "id", required = false) Long id) {
        articleService.delete(id);   
        log.info("Article is deleted!");     
    }
            
}
