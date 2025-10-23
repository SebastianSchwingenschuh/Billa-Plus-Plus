import java.util.List;
import java.util.Map;

public class ArticleRepository {
    private Map<Article, Integer> articles;

    public boolean addArticle(Article article) {
        return false;
    }

    public Article getArticleByBarcode(int i) {
        return null;
    }
    
    public List<Article> getArticlesWithQuantityBelow(int quantity) {
        return null;
    }

    public List<FoodArticle> getFoodWithoutAllergens(AllergenType[] allergenTypes) {
        return null;
    }

    public List<Article> getSortedArticles() {
        return null;
    }

    public TechArticle getTechArticleWithLongestWarranty() {
        return null;
    }
}
