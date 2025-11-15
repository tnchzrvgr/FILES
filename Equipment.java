public class Equipment {
    private int id;
    private String name;
    private String category;
    private int quantity;
    private String condition;

    public Equipment(int id, String name, String category, int quantity, String condition) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.condition = condition;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getCondition() { return condition; }
}
