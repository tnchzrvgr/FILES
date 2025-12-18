public class Item extends Equipment {

    private Management management;

    public Item(int id, String name, String category, int quantity, String condition) {
        super(id, name, category, quantity, condition);
    }

    @Override
    public void displayInfo() {
        System.out.println("┌───────────────────────────────────────────────────────────────────┐");
        System.out.println(
                "  ID: " + getId() +
                        " | Name: " + getName() +
                        " | Category: " + getCategory() +
                        " | Qty: " + getQuantity() +
                        " | Condition: " + getCondition()
        );
        System.out.println("└───────────────────────────────────────────────────────────────────┘");
    }
    public void displayErrorMessage(String msg){
        management.displayErrorMessage(msg);
    }
}

