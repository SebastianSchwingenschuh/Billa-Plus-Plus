public class FoodArticleFactory implements ArticleFactory {

    @Override
    public Article createFromString(String article) {
        String[] fields = article.split(";");
        for(int i = 0; i < fields.length; i++){
            fields[i] = fields[i].trim();
        }
        if (fields.length != 4) {
            throw new ArticleManagementException("not 4 fields");
        }
        try {
            AllergenType[] allergenTypes = new AllergenType[fields[3].split(",").length];
            for (int i = 0; i < fields[3].split(",").length; i++) {
                allergenTypes[i] = AllergenType.valueOf(fields[3].split(",")[i]);
            }
            return new FoodArticle(fields[1], Integer.parseInt(fields[0]), Integer.parseInt(fields[2]), allergenTypes);
        }
        catch (java.lang.NumberFormatException e) {
            throw new ArticleManagementException("Problems while parsing food article");
        }
    }
}
