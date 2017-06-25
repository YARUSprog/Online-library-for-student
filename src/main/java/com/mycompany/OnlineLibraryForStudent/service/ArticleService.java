
package com.mycompany.OnlineLibraryForStudent.service;

import com.mycompany.OnlineLibraryForStudent.model.Article;
import com.mycompany.OnlineLibraryForStudent.model.dto.ArticleDto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author YARUS
 */
public interface ArticleService {
    Article addArticle(Article article);
    Article addArticle(ArticleDto articleDto);
    void delete(long id);
    Article editArticle(Article article);
    Article editArticle(ArticleDto articleDto);
    List<Article> getAll();
    
    Article findById(long id);
    List<Article> findByTitle(String title);
    List<Article> findByDate(Date date);
    
    public List<Article> findByFilters(String searchText, String searchType, String searchConf, 
                                       String searchSubject, Integer searchYear, int groupId);
    
    public Long getCountByFilters(String searchText, String searchType, String searchConf,
            String searchSubject, Integer searchYear);
    public int getCountGroupArticles(int countArticles);
    
    public int getStartGroupPagination(Integer groupId, int countGroup);
    
    public int getEndGroupPagination(Integer groupId, int countGroup);
    
    public int validateGroupId(Integer groupId, int countGroup);
}
