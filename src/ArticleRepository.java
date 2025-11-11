import java.awt.color.CMMException;
import java.io.File;
import java.io.StringReader;
import java.util.*;

public class ArticleRepository {
    private Map<Integer, Article> articles = new HashMap<>();

    public boolean addArticle(Article article) {
        if (articles.containsKey(article.getBarcode()) || article.getQuantity() == 0 || article == null) {
            return false;
        }
        articles.put(article.getBarcode(), article);
        return true;
    }

    public Article getArticleByBarcode(int barcode) {
        return articles.get(barcode);
    }

    public List<Article> getArticlesWithQuantityBelow(int quantity) {
        List<Article> articlesWithQuantityBelow = new ArrayList<Article>();
        for (Article article : articles.values()) {
            if (article.getQuantity() < quantity) {
                articlesWithQuantityBelow.add(article);
            }
        }
        articlesWithQuantityBelow.sort(Comparator.comparing(Article::getQuantity));
        return articlesWithQuantityBelow;
    }

    public List<FoodArticle> getFoodWithoutAllergens(AllergenType[] allergenTypes) {
        List<FoodArticle> foodWithoutAllergens = new ArrayList<>();
        for (Article article : articles.values()) {
            if (article instanceof FoodArticle) {
                if (!((FoodArticle) article).containsAnyAllergen(allergenTypes))
                    foodWithoutAllergens.add((FoodArticle) article);
            }
        }
        return foodWithoutAllergens;
    }

    public List<Article> getSortedArticles() {  //sorts by names
        List<Article> sortedArticles = new ArrayList<>(articles.values());
        Collections.sort(sortedArticles);
        return sortedArticles;
    }

    public TechArticle getTechArticleWithLongestWarranty() {
        TechArticle actMax = new TechArticle("TechArticleWithLongestWarranty Dummy", 26353522, 15, 0);
        for (Article article : articles.values()) {
            if (article instanceof TechArticle) {
                if (actMax == null || ((TechArticle) article).getWarrantyMonths() > actMax.getWarrantyMonths())
                    actMax = (TechArticle) article;
            }
        }
        return actMax;
    }

    public void addArticlesFromFile(String s, ArticleFactory articleFactory) {
        try (Scanner sc = new Scanner(new File(s))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Article article = articleFactory.createFromString(line);
                articles.put(article.getBarcode(), article);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
