public class Item extends Equipment {

    public Item(int id, String name, String category, int quantity, String condition) {
        super(id, name, category, quantity, condition);
    }

    @Override
    public void displayInfo() {
        System.out.println("\n \t\t\t\t\t\t--- EQUIPMENT LIST ---");
        System.out.println(
                "ID: " + getId() +
                        " | Name: " + getName() +
                        " | Category: " + getCategory() +
                        " | Qty: " + getQuantity() +
                        " | Condition: " + getCondition()
        );
    }
}
