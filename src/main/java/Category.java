public class Category extends DBEntity {
    private String code;

    public Category(String code, String name, String description) {
        super(name, description);
        this.code = code;
    }

    public Category(Category category) {
        super(category.getName(), category.getCode());
        this.code = category.getCode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String toString(){
        return code + " " + super.getName() + " " + super.getDescription();
    }
}
