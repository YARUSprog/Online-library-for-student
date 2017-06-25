
package com.mycompany.OnlineLibraryForStudent.model.dao;

import com.mycompany.OnlineLibraryForStudent.model.Article;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author YARUS
 */
public interface ArticleRepository extends CrudRepository<Article, Long>{
    List<Article> findByTitle(String title);
    List<Article> findByDate(Date date);
}
