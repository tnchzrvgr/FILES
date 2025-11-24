    import java.util.Scanner;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.Vector;
    import java.util.Stack;
    import java.util.InputMismatchException;


    class BorrowRecord {
        int equipmentId;
        String borrower;

        BorrowRecord(int equipmentId, String borrower) {
            this.equipmentId = equipmentId;
            this.borrower = borrower;
        }
    }
    public class Management {

        private ArrayList<Equipment> equipmentList = new ArrayList<>();
        private HashMap<Integer, Equipment> map = new HashMap<>();
        private Vector<String> categories = new Vector<>();
        private ArrayList<BorrowRecord> borrowList = new ArrayList<>();
        private ArrayList<BorrowRecord> borrowLog = new ArrayList<>();
        private Stack<String> maintenance = new Stack<>();

        private Scanner sc = new Scanner(System.in);
        private int choice;
        private int idCounter = 1;

        public Management() {
            categories.add("[1] Cardio");
            categories.add("[2] Strength");
            categories.add("[3] Flexibility");
        }

        public void displayErrorMessage(String msg) {
            System.out.println(msg);
        }

        public void addItem() {
            System.out.print("Enter equipment name: ");
            String name = sc.nextLine();

            System.out.print("Choose category " + categories + ": ");
            int category = sc.nextInt();
            if(category == 1){
                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter condition [1]OLD [2]NEW: ");
                int condition = sc.nextInt();
                if(condition == 2){
                    Equipment item = new Item(idCounter, name, "Cardio", qty, "NEW");

                    equipmentList.add(item);
                    map.put(idCounter, item);
                    System.out.println(" Equipment added successfully with ID: " + idCounter);
                    idCounter++;
                }else if (condition == 1){
                    Equipment item = new Item(idCounter, name, "Cardio", qty, "OLD");

                    equipmentList.add(item);
                    map.put(idCounter, item);
                    System.out.println(" Equipment added successfully with ID: " + idCounter);
                    idCounter++;
                }

            } else if (category == 2) {
                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter condition [1]OLD [2]NEW: ");
                int condition = sc.nextInt();
                if(condition == 2){
                    Equipment item = new Item(idCounter, name, "Strength", qty, "NEW");

                    equipmentList.add(item);
                    map.put(idCounter, item);
                    System.out.println(" Equipment added successfully with ID: " + idCounter);
                    idCounter++;
                }else if (condition == 1){
                    Equipment item = new Item(idCounter, name, "Strength", qty, "OLD");

                    equipmentList.add(item);
                    map.put(idCounter, item);
                    System.out.println(" Equipment added successfully with ID: " + idCounter);
                    idCounter++;
                }
            } else if (category == 3) {
                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter condition [1]OLD [2]NEW: ");
                int condition = sc.nextInt();

                if(condition == 2){
                    Equipment item = new Item(idCounter, name, "Flexibility", qty, "NEW");

                    equipmentList.add(item);
                    map.put(idCounter, item);
                    System.out.println(" Equipment added successfully with ID: " + idCounter);
                    idCounter++;
                }else if (condition == 1){
                    Equipment item = new Item(idCounter, name, "Flexibility", qty, "OLD");

                    equipmentList.add(item);
                    map.put(idCounter, item);
                    System.out.println(" Equipment added successfully with ID: " + idCounter);
                    idCounter++;
                }
                else {
                    displayErrorMessage("Enter valid Condition");
                }
            }else{
                displayErrorMessage("Invalid Category!");
            }

        }

        public void updateItem(){
            System.out.print("Enter the Item ID you want to update: ");
            int id = sc.nextInt();
            Equipment eq = map.get(id);

            if (eq == null) {
                displayErrorMessage("Invalid ID.");
                return;
            }
            sc.nextLine();
            System.out.print("Enter new Name: ");
            String name = sc.nextLine();

            System.out.print("Enter new Category " + categories + ": ");
            String category = sc.nextLine();

            System.out.print("Enter new Quantity: ");
            int qty = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter new Condition (OLD or NEW): ");
            String condition = sc.nextLine();

            eq.setName(name);
            eq.setCategory(category);
            eq.setQuantity(qty);
            eq.setCondition(condition);
            System.out.println("ID " + id + " Updated Successfully");
        }

        public void deleteItem(){
            System.out.print("Enter the ID of the Equipment you want to remove: ");
            int id = sc.nextInt();
            Equipment eq = map.get(id);

            if (eq == null) {
                displayErrorMessage("Invalid ID.");
                return;
            }

            equipmentList.remove(id - 1);
            System.out.println("Item ID " + id + " has been removed.");

        }

        public void borrowItem() {
            System.out.print("Enter your name: ");
            String borrower = sc.nextLine();

            System.out.print("Enter equipment ID to borrow: ");
            int id = sc.nextInt();
            sc.nextLine();

            Equipment eq = map.get(id);

            if (eq == null) {
                displayErrorMessage("Invalid ID.");
                return;
            }

            if (eq.getQuantity() <= 0) {
                displayErrorMessage("Item is out of stock.");
                return;
            }

            borrowList.add(new BorrowRecord(id, borrower));
            borrowLog.add(new BorrowRecord(id, borrower));

            eq.setQuantity(eq.getQuantity() - 1);

            System.out.println("Borrow successful!");
        }


        public void returnEquipment() {
            if (borrowList.isEmpty()) {
                displayErrorMessage("No borrow records to process.");
                return;
            }

            System.out.println("\n \t\t\t\t\t\t--- Borrowed Items ---");
            for (int i = 0; i < borrowList.size(); i++) {
                BorrowRecord r = borrowList.get(i);
                Equipment eq = map.get(r.equipmentId);
                System.out.println((i + 1) + ". " + r.borrower + " borrowed " + eq.getName() + " (ID: " + r.equipmentId + ")");
            }

            System.out.print("Select which borrowed item to return (1-" + borrowList.size() + "): ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice < 1 || choice > borrowList.size()) {
                displayErrorMessage("Invalid selection.");
                return;
            }

            BorrowRecord record = borrowList.remove(choice - 1);
            Equipment eq = map.get(record.equipmentId);

            eq.setQuantity(eq.getQuantity() + 1);

            System.out.println("Successfully returned: " + eq.getName());
            System.out.println("Returned by: " + record.borrower);
        }

        public void borrowHistoryLog() {
            if (borrowLog.isEmpty()) {
                displayErrorMessage("No borrow history available.");
                return;
            }

            System.out.println("\n \t\t\t\t\t\t--- BORROW HISTORY LOG ---");
            for (int i = 0; i < borrowLog.size(); i++) {
                BorrowRecord record = borrowLog.get(i);
                Equipment eq = map.get(record.equipmentId);

                System.out.println((i + 1) + ". "
                        + record.borrower + " borrowed "
                        + eq.getName() + " (ID: " + record.equipmentId + ")");
            }
        }



        private void maintenanceLog() {
            System.out.println("\n \t\t\t\t\t\t--- MAINTENANCE MENU ---");
            System.out.println("[1] Add Maintenance Record");
            System.out.println("[2] View Maintenance History");
            int opt = sc.nextInt();
            sc.nextLine();

            if (opt == 1) {
                System.out.print("Enter maintenance note: ");
                String note = sc.nextLine();
                maintenance.push(note);
                System.out.println("Maintenance note added.");
            } else if (opt == 2) {
                if (maintenance.isEmpty()) {
                    displayErrorMessage("No maintenance records.");
                } else {
                    System.out.println("\n \t\t\t\t\t\t--- Maintenance Records ---");
                    for (String note : maintenance) {
                        System.out.println(note);
                    }
                }
            }
        }

        private void searchItem() {
            System.out.print("Enter equipment ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            Equipment eq = map.get(id);
            if (eq != null) {
                eq.displayInfo();
            } else {
                eq.displayErrorMessage("Equipment not found.");
            }
        }

        public void filterItem(){
            System.out.print("Enter the Category fo filter: ");
            String cat = sc.nextLine();

            for (Equipment e : equipmentList){
                if (e.getCategory().equalsIgnoreCase(cat)){
                    e.displayInfo();
                }
            }
        }

        public void showEquipment() {
            if (equipmentList.isEmpty()) {
                System.out.println("No equipment available.");
                return;
            }
            System.out.println("\n \t\t\t\t\t\t--- EQUIPMENT LIST ---");
            for (Equipment e : equipmentList) {
                e.displayInfo();
            }
        }

        public void displayMenu() {
            do {
               try {
                   System.out.println("\n \t\t\t\t\t\t===== GYM EQUIPMENT MANAGEMENT SYSTEM =====");
                   System.out.println("[1] Add Equipment");
                   System.out.println("[2] Remove Equipment");
                   System.out.println("[3] Update Equipment");
                   System.out.println("[4] View All Equipment");
                   System.out.println("[5] Borrow Equipment");
                   System.out.println("[6] Return Equipment");
                   System.out.println("[7] Show Borrow history log");
                   System.out.println("[8] Maintenance Log");
                   System.out.println("[9] Search Equipment by ID");
                   System.out.println("[10] Filter by Category");
                   System.out.println("[11] Exit");
                   System.out.print("Enter choice: ");
                   choice = sc.nextInt();
                   sc.nextLine();

                   switch (choice) {
                       case 1:
                           addItem();
                           break;
                       case 2:
                           deleteItem();
                           break;
                       case 3:
                           updateItem();
                           break;
                       case 4:
                           showEquipment();
                           break;
                       case 5:
                           borrowItem();
                           break;
                       case 6:
                           returnEquipment();
                           break;
                       case 7:
                           borrowHistoryLog();
                           break;
                       case 8:
                           maintenanceLog();
                           break;
                       case 9:
                           searchItem();
                           break;
                       case 10:
                           filterItem();
                           break;
                       case 11:
                           System.out.println("Exiting system...");
                           break;
                       default:
                           displayErrorMessage("Invalid choice. Try again.");
                           break;
                   }
               }catch (InputMismatchException e)
               {
                   displayErrorMessage("Invalid choice. Try again.");
                   sc.next();
               }
            } while (choice != 11);
        }
    }
