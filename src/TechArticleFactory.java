public class TechArticleFactory implements ArticleFactory {

    @Override
    public Article createFromString(String article) {
        String[] fields = article.split(";");
        if (fields.length != 4) {
            throw new ArticleManagementException("not 4 fields");
        }
        for(int i = 0; i < fields.length; i++){
            fields[i] = fields[i].trim();
        }
        try {
            return new TechArticle(fields[1], Integer.parseInt(fields[0]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3]));
        }
        catch (Exception e) {
            throw new ArticleManagementException(e.getMessage(), e.getCause());
        }
    }
}
