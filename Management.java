import java.util.*;
import java.util.Scanner;

public class Management{
    private ArrayList<Item> equipment = new ArrayList<>();
    private HashMap<Integer, Item> map = new HashMap<>();
    private Vector<String> categories = new Vector<>();
    private Queue<String> borrowQueue = new LinkedList<>();
    private Stack<String> maintenance = new Stack<String>();
    private HashMap<Integer, Stack<String>> maintenanceMap = new HashMap<>();

    private Scanner sc = new Scanner(System.in);
    private int choice;
    private int idCounter = 1;

    public Management(){
        categories.add("Cardio Machines");
        categories.add("Strength Training");
        categories.add("Resistance Machines");
        categories.add("Flexibility & Mobility");
        categories.add("Accessories");
        categories.add("Benches & Racks");

    }

    public void addItem(){
        System.out.print("Enter equipment name: ");
        String name = sc.nextLine();

        System.out.println("Choose category: " + categories );
        String category = sc.nextLine();

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter condition: ");
        String condition = sc.nextLine();

        Item it = new Item(idCounter, name, category, qty, condition);
        equipment.add(it);
        map.put(idCounter, it);
        idCounter++;
    }

    public void borrowItem(){
        System.out.print("Enter your Name: ");
        String name = sc. nextLine();

        System.out.print("Enter equipment ID to borrow: ");
        int id = sc.nextInt();
        Item eq = map.get(id);

        if(eq != null && eq.getQuantity() > 0){
            borrowQueue.add(name + " Borrowed " + eq.getName());
            eq.setQuantity(eq.getQuantity() - 1);
            System.out.println("Borrow successful!");
        }
        else{
            System.out.println("Invalid ID Equipment");
        }
    }

    public void returnEquipment(){
        Item eq = map.get(idCounter);
        if (borrowQueue.isEmpty()) {
            System.out.println("No borrow requests in queue.");
        } else {
            String lastBorrow = borrowQueue.poll();
            eq.setQuantity(eq.getQuantity() + 1);
            System.out.println("Processed return for: " + lastBorrow);
        }

    }

    private void maintenanceLog() {
        System.out.println("1. Add Maintenance Record");
        System.out.println("2. View Maintenance History");
        int opt = sc.nextInt();
        sc.nextLine();

        if (opt == 1) {
            System.out.print("Enter maintenance note: ");
            String note = sc.nextLine();
            maintenance.push(note);
            System.out.println("Maintenance log added.");
        } else if (opt == 2) {
            if (maintenance.isEmpty()) {
                System.out.println("No maintenance records found.");
            } else {
                System.out.println("\n--- Maintenance History ---");
                for (String note : maintenance) {
                    System.out.println(note);
                }
            }
        }
    }

    private void searchItem(){
        System.out.print("Enter equipment ID to search: ");
        int id = sc.nextInt();
        sc.nextLine();

        Item eq = map.get(id);
        if(eq != null){
            System.out.println("Equipment Found: " + eq.getName() + " (#) " + eq.getQuantity());
        }
        else{
            System.out.println("Equipment not found");
        }
    }


    public void displayMenu(){
        do{
            System.out.println("\n===== GYM EQUIPMENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Equipment");
            System.out.println("2. View All Equipment");
            System.out.println("3. Borrow Equipment");
            System.out.println("4. Return Equipment");
            System.out.println("5. Maintenance Log");
            System.out.println("6. Search Equipment by ID");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1 : addItem(); break;
                case 2 : showEquipment(); break;
                case 3 : borrowItem(); break;
                case 4 : returnEquipment(); break;
                case 5 : maintenanceLog(); break;
                case 6 : searchItem(); break;
            }

        } while (choice != 7);
    }
    public void showEquipment(){
        if(equipment.isEmpty()){
            System.out.println("No Equipment Found!");
        }
        else {
            System.out.println("\n \t\t\t\t\t\t\t--- EQUIPMENT LIST ---");
            for(Item i : equipment){
                System.out.println(
                        "ID: " + i.getId() + " | " +
                                " EQUIPMENT NAME: " + i.getName() + " | " +
                                " CATEGORY: " + i.getCategory() + " | " +
                                " QUANTITY: " + i.getQuantity() + " | " +
                                " CONDITION: " + i.getCondition()
                );
            }
        }
    }
    }

