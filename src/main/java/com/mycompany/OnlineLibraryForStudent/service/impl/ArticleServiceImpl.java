package com.mycompany.OnlineLibraryForStudent.service.impl;

import com.mycompany.OnlineLibraryForStudent.model.Article;
import com.mycompany.OnlineLibraryForStudent.model.dao.ArticleRepository;
import com.mycompany.OnlineLibraryForStudent.model.dto.ArticleDto;
import com.mycompany.OnlineLibraryForStudent.service.ArticleService;
import com.mycompany.OnlineLibraryForStudent.service.ConferenceService;
import com.mycompany.OnlineLibraryForStudent.service.SubjectService;
import com.mycompany.OnlineLibraryForStudent.service.UsersService;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YARUS
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private static final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);
    
    @javax.persistence.PersistenceContext
    private EntityManager em;

    public static final int GROUP_ARTICLES_SIZE = 10;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UsersService userService;

    @Autowired
    ConferenceService conferenceService;

    @Autowired
    SubjectService subjectService;
    
    @Override
    public Article addArticle(Article article) {
        return articleRepository.save(article);
    }
    
    @Override
    public Article addArticle(ArticleDto articleDto) {
        Article article = dtoToModel(articleDto);
        return articleRepository.save(article);
    }

    @Override
    public void delete(long id) {
        articleRepository.delete(id);
    }

    @Override
    public Article editArticle(Article article) {
        return articleRepository.save(article);
    }
    
    @Override
    public Article editArticle(ArticleDto articleDto) {
        Article article = dtoToModel(articleDto);
        return articleRepository.save(article);
    }

    @Override
    public List<Article> getAll() {
        return (List<Article>) articleRepository.findAll();
    }

    @Override
    public Article findById(long id) {
        return articleRepository.findOne(id);
    }
    
    @Override
    public List<Article> findByTitle(String title) {
        return articleRepository.findByTitle(title);
    }

    @Override
    public List<Article> findByDate(Date date) {
        return articleRepository.findByDate(date);
    }

    @Override
    public int validateGroupId(Integer groupId, int countGroup){
        if (groupId == null || groupId <= 0)
            groupId = 1;
        else if (groupId > countGroup)
            groupId = countGroup; 
        return groupId;
    }
    
    @Override
    public int getStartGroupPagination(Integer groupId, int countGroup){
        groupId = validateGroupId(groupId, countGroup);
        int startGroup;
        if(groupId <= 2 ) 
            startGroup = 1;        
        else if(groupId <= countGroup-2)
            startGroup = groupId - 2;                    
        else if(countGroup < 5){
            startGroup = 1; 
        } else           
            startGroup = countGroup-4;   
        return startGroup;
    }
    
    @Override
    public int getEndGroupPagination(Integer groupId, int countGroup){
        groupId = validateGroupId(groupId, countGroup);
        int endGroup;
        if(countGroup < 5) 
            endGroup = countGroup; 
        else if(groupId <= 2 ) 
            endGroup = 5;        
        else if(groupId <= countGroup-2)
            endGroup = groupId + 2;          
        else 
            endGroup = countGroup;   
        return endGroup;
    }
    
    public int findGroupArticles(int groupId, int countArticles) {
        if (groupId <= 0) {
            return 0;
        }
        if (countArticles <= 0) {
            return 0;
        }
        int countGroupPage = getCountGroupArticles(countArticles);
        int sizeIncompleteGroup = countArticles % GROUP_ARTICLES_SIZE;
        if (groupId > countGroupPage) {
            return 0;
        }
        //if groupId - it's last group
        if (sizeIncompleteGroup > 0 && countGroupPage == groupId) {
            return (groupId - 1) * GROUP_ARTICLES_SIZE + sizeIncompleteGroup;
        } else {
            return  groupId * GROUP_ARTICLES_SIZE;
        }        
    }
    
    @Override
    public int getCountGroupArticles(int countArticles) {        
        int countGroupPage = countArticles / GROUP_ARTICLES_SIZE;
        //if last group incomplete
        if (countArticles % GROUP_ARTICLES_SIZE > 0) {
            countGroupPage++;
        }
        log.info("getCountGroupArticles");
        return countGroupPage;
    }

    @Override
    public Long getCountByFilters(String searchText, String searchType, String searchConf,
            String searchSubject, Integer searchYear) {
        String stringQuery = "SELECT COUNT(a.id) FROM Article a ";
        boolean isOtherOptions = false;
        if (searchType != null && searchType.equals("Author") && searchText != null && !searchText.trim().isEmpty()) {            
            stringQuery += "join a.usersCollection u "
                    + "where (LOWER(u.firstName) LIKE LOWER(CONCAT('%',:searchText, '%')) OR "
                    + "LOWER(u.middleName) LIKE LOWER(CONCAT('%',:searchText, '%')) OR "
                    + "LOWER(u.lastName) LIKE LOWER(CONCAT('%',:searchText, '%'))) ";  
            isOtherOptions = true;
        } else if (searchType != null && searchType.equals("Title") && searchText != null && !searchText.trim().isEmpty()) {
            stringQuery += "where LOWER(a.title) LIKE LOWER(CONCAT('%',:searchText, '%')) ";
            isOtherOptions = true;            
        }
        if(searchConf != null && !searchConf.trim().isEmpty()){
            if(isOtherOptions == true)
                stringQuery += " AND a.conferenceId.name = :searchConf ";
            else{
                stringQuery += " where a.conferenceId.name = :searchConf ";
                isOtherOptions = true;
            }
        }     
        if(searchSubject != null && !searchSubject.trim().isEmpty()){
            if(isOtherOptions == true)
                stringQuery += " AND a.subjectId.name = :searchSubject ";
            else {
                stringQuery += " where a.subjectId.name = :searchSubject ";
                isOtherOptions = true;
            }            
        }   
        
        if(searchYear != null){
            if(isOtherOptions == true)
                stringQuery += " AND YEAR(a.date) = :searchYear ";
            else {
                stringQuery += " where YEAR(a.date) = :searchYear ";
                isOtherOptions = true;
            }            
        }  
        
        TypedQuery query = em.createQuery(stringQuery, Long.class);               
        if (searchType != null && (searchType.equals("Author") || searchType.equals("Title")) && searchText != null && !searchText.trim().isEmpty())
            query.setParameter("searchText", searchText);
        if(searchConf != null && !searchConf.trim().isEmpty())
            query.setParameter("searchConf", searchConf);
        if(searchSubject != null && !searchSubject.trim().isEmpty())
            query.setParameter("searchSubject", searchSubject);
        if(searchYear != null)
            query.setParameter("searchYear", searchYear);
        log.info("getCountByFilters");
        return (Long)query.getSingleResult();
    }
    
    @Override
    public List<Article> findByFilters(String searchText, String searchType, String searchConf,
            String searchSubject, Integer searchYear, int groupId) {
        Long count = this.getCountByFilters(searchText, searchType, searchConf, searchSubject, searchYear);
        log.info("In findByFilters. Count: " + count);
        String stringQuery = "SELECT a FROM Article a ";
        boolean isOtherOptions = false;
        if (searchType != null && searchType.equals("Author") && searchText != null && !searchText.trim().isEmpty()) {            
            stringQuery += "join fetch a.usersCollection u "
                    + "where (LOWER(u.firstName) LIKE LOWER(CONCAT('%',:searchText, '%')) OR "
                    + "LOWER(u.middleName) LIKE LOWER(CONCAT('%',:searchText, '%')) OR "
                    + "LOWER(u.lastName) LIKE LOWER(CONCAT('%',:searchText, '%'))) ";  
            isOtherOptions = true;
        } else if (searchType != null && searchType.equals("Title") && searchText != null && !searchText.trim().isEmpty()) {
            stringQuery += "where LOWER(a.title) LIKE LOWER(CONCAT('%',:searchText, '%')) ";
            isOtherOptions = true;            
        }
        if(searchConf != null && !searchConf.trim().isEmpty()){
            if(isOtherOptions == true)
                stringQuery += " AND a.conferenceId.name = :searchConf ";
            else{
                stringQuery += " where a.conferenceId.name = :searchConf ";
                isOtherOptions = true;
            }
        }     
        if(searchSubject != null && !searchSubject.trim().isEmpty()){
            if(isOtherOptions == true)
                stringQuery += " AND a.subjectId.name = :searchSubject ";
            else {
                stringQuery += " where a.subjectId.name = :searchSubject ";
                isOtherOptions = true;
            }            
        }   
        
        if(searchYear != null){
            if(isOtherOptions == true)
                stringQuery += " AND YEAR(a.date) = :searchYear ";
            else {
                stringQuery += " where YEAR(a.date) = :searchYear ";
                isOtherOptions = true;
            }            
        }  
        
        TypedQuery query = em.createQuery(stringQuery, Article.class);               
        if (searchType != null && (searchType.equals("Author") || searchType.equals("Title")) && searchText != null && !searchText.trim().isEmpty())
            query.setParameter("searchText", searchText);
        if(searchConf != null && !searchConf.trim().isEmpty())
            query.setParameter("searchConf", searchConf);
        if(searchSubject != null && !searchSubject.trim().isEmpty())
            query.setParameter("searchSubject", searchSubject);
        if(searchYear != null)
            query.setParameter("searchYear", searchYear);
        
        if (groupId <= 0) {
            groupId = 1;
        }
        query.setFirstResult(groupId * GROUP_ARTICLES_SIZE - GROUP_ARTICLES_SIZE);
        query.setMaxResults(GROUP_ARTICLES_SIZE);
        
        return query.getResultList();
    }

    private Article dtoToModel(ArticleDto articleDto){
        Article article;
        if(articleDto.getId() != null && articleDto.getId() > 0){
            article = findById(articleDto.getId());
        } else
            article = new Article(); 
        
        article.setId(articleDto.getId());
        article.setText(articleDto.getText());
        article.setTitle(articleDto.getTitle());
        article.setConferenceId(conferenceService.findById(articleDto.getConferenceId()));
        article.setSubjectId(subjectService.findById(articleDto.getSubjectId()));
        article.setDate(articleDto.getDate());        
        return article;
    }
}
