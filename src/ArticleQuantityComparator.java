import java.util.Comparator;

public class ArticleQuantityComparator implements Comparator<Article> {
    public int compare(Article article1, Article article2) {
        return Integer.compare(article1.getQuantity(), article2.getQuantity());
    }
}
