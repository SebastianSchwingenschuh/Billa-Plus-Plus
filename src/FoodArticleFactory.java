public class FoodArticleFactory implements ArticleFactory {

    @Override
    public Article createFromString(String article) {
        String[] fields = article.split(";");
        if (fields.length != 5) {
            throw new ArticleManagementException("not 5 fields");
        }
        AllergenType[] allergenTypes = new AllergenType[fields[3].split(",").length];
        for (int i = 0; i < fields[3].split(",").length; i++) {
            allergenTypes[i] = AllergenType.valueOf(fields[3].split(",")[i]);
        }
        return new FoodArticle(fields[1], Integer.parseInt(fields[0]), Integer.parseInt(fields[2]), allergenTypes);
    }
}
