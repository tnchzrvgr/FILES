import java.util.*;

public class GymManager {
    private ArrayList<Equipment> equipmentList = new ArrayList<>();
    private Queue<String> borrowQueue = new LinkedList<>();
    private Stack<String> maintenanceStack = new Stack<>();
    private HashMap<Integer, Equipment> equipmentMap = new HashMap<>();
    private Vector<String> categories = new Vector<>();
    private int idCounter = 1;
    private Scanner sc = new Scanner(System.in);

    public GymManager() {
        categories.add("Cardio");
        categories.add("Strength");
        categories.add("Flexibility");
        categories.add("Free Weights");
    }

    public void displayMenu() {
        int choice;
        do {
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

            switch (choice) {
                case 1: addEquipment(); break;
                case 2: viewEquipment(); break;
                case 3: borrowEquipment(); break;
                case 4: returnEquipment(); break;
                case 5: maintenanceLog(); break;
                case 6: searchEquipment(); break;
                case 7: System.out.println("Exiting system..."); break;
                default: System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 7);
    }

    private void addEquipment() {
        System.out.print("Enter equipment name: ");
        String name = sc.nextLine();

        System.out.println("Choose category: " + categories);
        String category = sc.nextLine();

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter condition: ");
        String condition = sc.nextLine();

        Equipment eq = new Equipment(idCounter, name, category, qty, condition);
        equipmentList.add(eq);
        equipmentMap.put(idCounter, eq);

        System.out.println("✅ Equipment added successfully with ID: " + idCounter);
        idCounter++;
    }

    private void viewEquipment() {
        if (equipmentList.isEmpty()) {
            System.out.println("No equipment found.");
        } else {
            System.out.println("\n--- EQUIPMENT LIST ---");
            for (Equipment e : equipmentList) {
                System.out.println(e);
            }
        }
    }

    private void borrowEquipment() {
        System.out.print("Enter your name: ");
        String user = sc.nextLine();

        System.out.print("Enter equipment ID to borrow: ");
        int id = sc.nextInt();
        sc.nextLine();

        Equipment eq = equipmentMap.get(id);
        if (eq != null && eq.getQuantity() > 0) {
            borrowQueue.add(user + " borrowed " + eq.getName());
            eq.setQuantity(eq.getQuantity() - 1);
            System.out.println(user + " borrowed " + eq.getName() + ".");
        } else {
            System.out.println("Equipment not available or invalid ID.");
        }
    }

    private void returnEquipment() {
        if (borrowQueue.isEmpty()) {
            System.out.println("No borrow requests in queue.");
        } else {
            String lastBorrow = borrowQueue.poll();
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
            maintenanceStack.push(note);
            System.out.println("Maintenance log added.");
        } else if (opt == 2) {
            if (maintenanceStack.isEmpty()) {
                System.out.println("No maintenance records found.");
            } else {
                System.out.println("\n--- Maintenance History ---");
                for (String note : maintenanceStack) {
                    System.out.println(note);
                }
            }
        }
    }

    private void searchEquipment() {
        System.out.print("Enter equipment ID to search: ");
        int id = sc.nextInt();
        sc.nextLine();

        Equipment eq = equipmentMap.get(id);
        if (eq != null) {
            System.out.println("Equipment found: " + eq);
        } else {
            System.out.println("Equipment not found.");
        }
    }

}
