public class Category extends DBEntity {
    private String code;

    public Category(String name, String description, String code) {
        super(name, description);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
