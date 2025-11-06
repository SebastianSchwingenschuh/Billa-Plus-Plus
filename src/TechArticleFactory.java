public class TechArticleFactory implements ArticleFactory {

    @Override
    public Article createFromString(String article) {
        String[] fields = article.split(";");
        if (fields.length != 5) {
            throw new ArticleManagementException("not 5 fields");
        }
        return new TechArticle(fields[1], Integer.parseInt(fields[0]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3]));
    }
}
