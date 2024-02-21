package production.model;

import java.math.BigDecimal;

public class Grade extends NamedEntity {
    private Integer gradeOfArticle;
    private String gradeText;

    public Grade(String name, Long id, Integer gradeOfArticle, String gradeText) {
        super(name, id);
        this.gradeOfArticle = gradeOfArticle;
        this.gradeText = gradeText;
    }

    public Integer getGradeOfArticle() {
        return gradeOfArticle;
    }

    public void setGradeOfArticle(Integer gradeOfArticle) {
        this.gradeOfArticle = gradeOfArticle;
    }

    public String getGradeText() {
        return gradeText;
    }

    public void setGradeText(String gradeText) {
        this.gradeText = gradeText;
    }
}
