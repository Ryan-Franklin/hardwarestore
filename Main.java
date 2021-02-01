import java.awt.event.*;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

/**
 * This is the main class of the Hardware Store database manager. It provides a
 * console for a user to use the 5 main commands.
 *
 *
 *
 * @author Tory Brewer & Ryan Franklin
 *
 */
public class Main extends JFrame{
    private JFrame frame;
    private JComboBox options;
    private JLabel label1;
    private JTextArea welcome;

    private final HardwareStore hardwareStore;
    private static final Scanner CONSOLE_INPUT = new Scanner(System.in);

    private static int width = 1352;
    private static int height = 760;
    JPanel panel1 = new JPanel();

    public static void main(String[] args) throws Exception {
        Main mainApp = new Main();
        mainApp.run();
    }

    private void run() {
        frame = new JFrame("Hardware Store Database");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                    saveDatabase();
                    System.exit(0);
            }
        });

        frame.setBounds(100, 100, width, height);

        panel1.setLayout(new GridLayout(0,2,10,10));
        label1 = new JLabel("Select a Command:", JLabel.RIGHT);
        panel1.add(label1);

        String[] optionString = {
                "SELECT AN OPTION...",
                "1. Show existing items in database by ID#",
                "2. Add new item to database",
                "3. Delete item from database",
                "4. Search for item by name",
                "5. Show users in database",
                "6. Add new user to the database",
                "7. Update user info by ID#",
                "8. Complete a transaction",
                "9. Show completed transaction(s)",
                "10. Save and Exit"};

        options = new JComboBox(optionString);

        options.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if(event.getStateChange() == ItemEvent.SELECTED){
                    Object source = event.getSource();
                    if (source instanceof JComboBox){
                        JComboBox cb= (JComboBox)source;
                        Object selectedItem = cb.getSelectedItem();
                        if("1. Show existing items in database by ID#".equals(selectedItem)) {
                            showAllItems();
                        }else if ("2. Add new item to database".equals(selectedItem)){
                            addItemQuantity();
                        }else if("3. Delete item from database".equals(selectedItem)){
                            removeItemQuantity();
                        }else if("4. Search for item by name".equals(selectedItem)){
                            searchForItem();
                        }else if("5. Show users in database".equals(selectedItem)){
                                showUsers();
                        }else if("6. Add new user to the database".equals(selectedItem)){
                                addNewUser();
                        }else if("7. Update user info by ID#".equals(selectedItem)){
                                updateUserData();
                        }else if("8. Complete a transaction".equals(selectedItem)){
                                completeTransaction();
                        }else if("9. Show completed transaction(s)".equals(selectedItem)){
                                    showcompleted();
                        }else if ("10. Save and Exit".equals(selectedItem)){
                            saveDatabase();
                            frame.dispose();
                        }
                    }
                }
            }
        });
        panel1.add(options);

        for (int i = 1; i <= 16; i++) {
            panel1.add(new JLabel());
        }

        frame.add(panel1, BorderLayout.EAST);

        JPanel panel2 = new JPanel(new GridLayout(0,1));

        welcome = new JTextArea("Welcome to Hardware Store Managment System\n!TOP RIGHT FOR OPTIONS!");

        panel2.setForeground(Color.BLACK);
        panel2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        welcome.setEditable(false);
        welcome.setColumns(105);
        welcome.setRows(15);
        panel2.add(welcome);
        frame.add(panel2, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    /**
     * Default constructor. Initializes a new object of type HardwareStore
     *
     * @throws IOException
     */
    public Main() throws IOException {
        hardwareStore = new HardwareStore();
    }

    /**
     * This method shows all items in the inventory.
     *
     */
    public void showAllItems(){
        panel1.removeAll();
        panel1.add(label1,BorderLayout.NORTH);
        panel1.add(options,BorderLayout.SOUTH);
        for (int i = 1; i <= 16; i++) {
            panel1.add(new JLabel());
        }
        repaint();
        printAll(getGraphics());
        welcome.setText(hardwareStore.getAllItemsFormatted());
    }

   /**
     * This method will add items quantity with given number. If the item does
     * not exist, it will call another method to add it.
     *
     */
    public void addItemQuantity() {
        panel1.removeAll();
        panel1.add(label1);
        panel1.add(options);

        JLabel typeLabel = new JLabel("Package Type", JLabel.RIGHT);
        panel1.add(typeLabel);

        JComboBox typeComboBox = new JComboBox();
        typeComboBox.addItem("Small Hardware");
        typeComboBox.addItem("Appliance");
        panel1.add(typeComboBox);

        JLabel categoryLabel = new JLabel("Category", JLabel.RIGHT);
        panel1.add(categoryLabel);

        JComboBox categoryComboBox = new JComboBox();
        categoryComboBox.addItem("Door&Window");
        categoryComboBox.addItem("Cabinet&Furniture");
        categoryComboBox.addItem("Fasteners");
        categoryComboBox.addItem("Structural");
        categoryComboBox.addItem("Other");
        panel1.add(categoryComboBox);
        JLabel applianceType = new JLabel("Appliance Type", JLabel.RIGHT);

        JComboBox AtypeComboBox = new JComboBox();
        AtypeComboBox.addItem("Refrigerators");
        AtypeComboBox.addItem("Washer&Dryer");
        AtypeComboBox.addItem("Range&Ovens");
        AtypeComboBox.addItem("Small Appliances");
        JLabel itemNameLabel = new JLabel("Item Name",JLabel.RIGHT);
        panel1.add(itemNameLabel);
        JTextField itemNameText = new JTextField("");
        panel1.add(itemNameText);

        JLabel brandLabel = new JLabel("Appliance Brand", JLabel.RIGHT);
        JTextField brandTextField = new JTextField("");

        JLabel itemIdLabel = new JLabel("Item ID", JLabel.RIGHT);
        panel1.add(itemIdLabel);
        JTextField itemIdTextField = new JTextField("");
        panel1.add(itemIdTextField);

        JLabel itemQuantity = new JLabel("Item Quantity", JLabel.RIGHT);
        panel1.add(itemQuantity);
        JTextField itemQuantityText = new JTextField("");
        panel1.add(itemQuantityText);

        JLabel itemPrice = new JLabel("Item Price", JLabel.RIGHT);
        panel1.add(itemPrice);
        JTextField itemPriceText = new JTextField("");
        panel1.add(itemPriceText);
        panel1.add(new JLabel());
        JButton confirmButton = new JButton("Confirm");
        panel1.add(confirmButton);
        for (int i = 1; i <= 15; i++) {
            panel1.add(new JLabel());
        }

        typeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    if (e.getItem().equals("Small Hardware")) {
                        panel1.remove(4);
                        panel1.add(categoryLabel, 4);
                        panel1.remove(5);
                        panel1.add(categoryComboBox, 5);
                        panel1.remove(14);
                        panel1.add(confirmButton, 14);
                        panel1.remove(15);
                        panel1.remove(16);
                        panel1.updateUI();
                    } else if (e.getItem().equals("Appliance")) {
                        panel1.remove(4);
                        panel1.add(brandLabel, 4);
                        panel1.remove(5);
                        panel1.add(brandTextField, 5);
                        panel1.remove(14);
                        panel1.add(applianceType,14);
                        panel1.remove(15);
                        panel1.add(AtypeComboBox,15);
                        panel1.remove(16);
                        panel1.add(confirmButton,16);
                        panel1.updateUI();
                    }
                }
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                String id = itemIdTextField.getText();
                String name = itemNameText.getText();

                String numQty = itemQuantityText.getText();
                int quantity = 0;

                String fltPrc = itemPriceText.getText();
                float price = 0;

                try {
                    quantity = Integer.parseInt(numQty);
                    price = Float.parseFloat(fltPrc);
                } catch (NumberFormatException n) {
                    welcome.setText("!!Error: data improperly entered!!\n");
                    welcome.setText("FIX: Enter integers for quantity; Enter floats for price");
                    System.out.println("Caught");
                }

                String category = categoryComboBox.getSelectedItem().toString();
                String brand = brandTextField.getText();

                String type = AtypeComboBox.getSelectedItem().toString();

                String itemType = typeComboBox.getSelectedItem().toString();

                if (itemType.equals("Small Hardware")) {
                    hardwareStore.addNewSmallHardwareItem(id, name, quantity, price, category);
                } else {
                    hardwareStore.addNewAppliance(id, name, quantity, price, type, brand);
                }

                itemIdTextField.setText("");
                itemNameText.setText("");
                itemQuantityText.setText("");
                itemPriceText.setText("");
                brandTextField.setText("");

            }
        });
        panel1.updateUI();
    }

    /**
     * This method will remove the item with given ID.
     * If the item does not exist, it will show an appropriate message.
     */
    public void removeItemQuantity(){
        panel1.removeAll();
        panel1.add(label1);
        panel1.add(options);

        JLabel itemIdLabel = new JLabel("Item ID", JLabel.RIGHT);
        panel1.add(itemIdLabel);
        JTextField itemIdTextField = new JTextField("");
        panel1.add(itemIdTextField);
        panel1.add(new JLabel());
        JButton confirmButton = new JButton("Confirm");
        panel1.add(confirmButton);

        for (int i = 1; i <= 16; i++) {
            panel1.add(new JLabel());
        }
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemID = itemIdTextField.getText();

                int itemIndex = hardwareStore.findItemIndex(itemID);
                hardwareStore.removeItem(itemIndex);
            }
        });
        panel1.updateUI();
    }

    /**
     * This method can search item by a given name (part of name.
     * Case-insensitive.) Will display all items with the given name.
     */
    public void searchForItem(){
        panel1.removeAll();
        panel1.add(label1);
        panel1.add(options);

        JLabel nameLabel = new JLabel("Name", JLabel.RIGHT);
        panel1.add(nameLabel);

        JTextField nameText = new JTextField("");
        panel1.add(nameText);
        panel1.add(new JLabel());
        JButton confirmButton = new JButton("Confirm");
        panel1.add(confirmButton);
        for (int i = 1; i <= 20; i++) {
            panel1.add(new JLabel());
        }
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String name = nameText.getText();

                String output = hardwareStore.getMatchingItemsByName(name);
                if (output == null) {
                    welcome.setText("Item not found with: " + name + ".");
                } else {
                    welcome.setText(output);
                }
            }
        });
        panel1.updateUI();
    }

    /**
     * This method shows all users in the system.
     */
    public void showUsers(){

        panel1.removeAll();
        panel1.add(label1,BorderLayout.NORTH);
        panel1.add(options,BorderLayout.SOUTH);
        for (int i = 1; i <= 16; i++) {
            panel1.add(new JLabel());
        }
        repaint();
        printAll(getGraphics());
        welcome.setText(hardwareStore.getAllUsersFormatted());
    }

/**
 * This method will add a user (customer or employee) to the system.
 *
 *
 * */
    public void addNewUser(){
        panel1.removeAll();
        panel1.add(label1);
        panel1.add(options);

        JLabel userTypeLabel = new JLabel("User Type", JLabel.RIGHT);
        panel1.add(userTypeLabel);

        JComboBox userTypeCombo = new JComboBox();
        userTypeCombo.addItem("Customer");
        userTypeCombo.addItem("Employee");
        panel1.add(userTypeCombo);

        JLabel firstNameLabel = new JLabel("First Name", JLabel.RIGHT);
        panel1.add(firstNameLabel);
        JTextField firstNameText = new JTextField("");
        panel1.add(firstNameText);
        JLabel lastNameLabel = new JLabel("Last Name", JLabel.RIGHT);
        panel1.add(lastNameLabel);
        JTextField lastNameText = new JTextField("");
        panel1.add(lastNameText);
        JLabel phoneNumberLabel = new JLabel("Phone Number", JLabel.RIGHT);
        panel1.add(phoneNumberLabel);
        JTextField phoneNumberText = new JTextField("");
        panel1.add(phoneNumberText);
        JLabel addressLabel = new JLabel("Address", JLabel.RIGHT);
        panel1.add(addressLabel);
        JTextField addressText = new JTextField("");
        panel1.add(addressText);

        JLabel salaryLabel = new JLabel("Salary", JLabel.RIGHT);
        JTextField salaryText = new JTextField("");

        JLabel SSNlabel = new JLabel("SSN (9- digit int)", JLabel.RIGHT);
        JTextField SSNText = new JTextField("");

        panel1.add(new JLabel());//12
        JButton confirmbutton = new JButton("Confirm");
        panel1.add(confirmbutton);//13
        for (int i = 1; i <= 15; i++) {
            panel1.add(new JLabel());
        }

        userTypeCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    if (e.getItem().equals("Customer")) {
                        panel1.remove(8);
                        panel1.add(phoneNumberLabel, 8);
                        panel1.remove(9);
                        panel1.add(phoneNumberText, 9);
                        panel1.remove(10);
                        panel1.add(addressLabel,10);
                        panel1.remove(11);
                        panel1.add(addressText,11);
                        panel1.updateUI();
                    } else if (e.getItem().equals("Employee")) {
                        panel1.remove(8);
                        panel1.add(salaryLabel, 8);
                        panel1.remove(9);
                        panel1.add(salaryText, 9);
                        panel1.remove(10);
                        panel1.add(SSNlabel,10);
                        panel1.remove(11);
                        panel1.add(SSNText,11);
                        panel1.updateUI();
                    }
                }
            }
        });
        confirmbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String firstName = firstNameText.getText();

                String lastName = lastNameText.getText();

                String phoneNum = phoneNumberText.getText();
                String addy = addressText.getText();

                String intSSN = SSNText.getText();
                int SSN = 0;

                String fltSly = salaryText.getText();
                float salary = 0;

                try {
                    SSN = Integer.parseInt(intSSN);
                    salary = Float.parseFloat(fltSly);
                } catch (NumberFormatException n) {
                    welcome.setText("!!Error: data improperly entered!! \n");
                    welcome.setText("FIX: Enter integers for quantity; Enter floats for price");
                    System.out.println("Caught");
                }

                String userType = userTypeCombo.getSelectedItem().toString();
                if (userType.equals("Customer")) {

                    hardwareStore.addCustomer(firstName, lastName, phoneNum, addy);
                    // String[] dataInv = {id, name, numQty, fltPrc, category, "N/A"};
                } else {
                    hardwareStore.addEmployee(firstName, lastName, SSN, salary);
                    //String[] dataInv = {id, name, numQty, fltPrc, "N/A", brand};
                }
                firstNameText.setText("");
                lastNameText.setText("");
                phoneNumberText.setText("");
                SSNText.setText("");
                addressText.setText("");
                welcome.setText("New user has benn added");
                }
            }
        );
        panel1.updateUI();
    }

    /**
     * This method will update user data (customer or employee).
     *
     */
    public void updateUserData() {

        panel1.removeAll();
        panel1.add(label1);
        panel1.add(options);

        JLabel userIdLabel = new JLabel("User ID", JLabel.RIGHT);
        panel1.add(userIdLabel);
        JTextField userIdTextField = new JTextField("");
        panel1.add(userIdTextField);   // Added to index 3

        JButton search = new JButton("Search");
        panel1.add(new JLabel());
        panel1.add(search);

        for (int i = 1; i <= 16; i++) {
            panel1.add(new JLabel());
        }

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //System.out.println("You have made it into the ActionListener");
                String intID = userIdTextField.getText();
                int ID = 0;

                try {
                    ID = Integer.parseInt(intID);
                } catch (NumberFormatException n) {
                    welcome.setText("!!Error: data improperly entered!! \n");
                    welcome.setText("FIX: Enter integers for quantity; Enter floats for price");
                    System.out.println("Caught");
                }

                User editUser = hardwareStore.findUser(ID);

                if (editUser.isEmployee) {
                    panel1.remove(6);
                    JLabel userFNLabel = new JLabel("First name", JLabel.RIGHT);
                    panel1.add(userFNLabel, 6);
                    panel1.remove(7);
                    JTextField userFNTextField = new JTextField("");
                    panel1.add(userFNTextField, 7);

                    panel1.remove(8);
                    JLabel userLNLabel = new JLabel("Last name", JLabel.RIGHT);
                    panel1.add(userLNLabel,8);
                    panel1.remove(9);
                    JTextField userLNTextField = new JTextField("");
                    panel1.add(userLNTextField,9);

                    panel1.remove(10);
                    JLabel userSSNLabel = new JLabel("SSN", JLabel.RIGHT);
                    panel1.add(userSSNLabel,10);
                    panel1.remove(11);
                    JTextField userSSNTextField = new JTextField("");
                    panel1.add(userSSNTextField,11);

                    panel1.remove(12);
                    JLabel userSalaryLabel = new JLabel("Salary", JLabel.RIGHT);
                    panel1.add(userSalaryLabel,12);
                    panel1.remove(13);
                    JTextField userSalaryTextField = new JTextField("");
                    panel1.add(userSalaryTextField,13);

                    panel1.remove(14);
                    panel1.add(new JLabel());
                    JButton confirmButton = new JButton("Confirm");
                    panel1.add(confirmButton);
                    for (int i = 1; i <= 10; i++) {
                        panel1.add(new JLabel());
                    }

                    panel1.updateUI();

                    confirmButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            String firstName = userFNTextField.getText();
                            String lastName = userLNTextField.getText();

                            String stringSSN = userSSNTextField.getText();
                            int ssn = 0;

                            String fltSalary = userSalaryTextField.getText();
                            float salary = 0;

                            String stringID = userIdTextField.getText();
                            int ID = 0;
                            try {
                                ssn = Integer.parseInt(stringSSN);
                                salary = Float.parseFloat(fltSalary);
                                ID = Integer.parseInt(stringID);
                            }
                            catch (NumberFormatException n) {
                                welcome.setText("!!Error: data improperly entered!! \n");
                                welcome.setText("FIX: Enter integers for quantity; Enter floats for price");
                                System.out.println("Caught");
                            }
                            hardwareStore.editEmployeeInformation(ID, firstName, lastName, ssn, salary);
                        }
                    });
                    panel1.updateUI();
                } else {
                    panel1.remove(6);
                    JLabel userFNLabel = new JLabel("First name", JLabel.RIGHT);
                    panel1.add(userFNLabel, 6);
                    panel1.remove(7);
                    JTextField userFNTextField = new JTextField("");
                    panel1.add(userFNTextField, 7);

                    panel1.remove(8);
                    JLabel userLNLabel = new JLabel("Last name", JLabel.RIGHT);
                    panel1.add(userLNLabel,8);
                    panel1.remove(9);
                    JTextField userLNTextField = new JTextField("");
                    panel1.add(userLNTextField,9);

                    panel1.remove(10);
                    JLabel phoneLabel = new JLabel("phone #", JLabel.RIGHT);
                    panel1.add(phoneLabel,10);
                    panel1.remove(11);
                    JTextField phoneTextField = new JTextField("");
                    panel1.add(phoneTextField,11);

                    panel1.remove(12);
                    JLabel addyLabel = new JLabel("address", JLabel.RIGHT);
                    panel1.add(addyLabel,12);
                    panel1.remove(13);
                    JTextField addyTextField = new JTextField("");
                    panel1.add(addyTextField,13);

                    panel1.remove(14);
                    panel1.add(new JLabel());
                    JButton confirmButton = new JButton("Confirm");
                    panel1.add(confirmButton);
                    for (int i = 1; i <= 10; i++) {
                        panel1.add(new JLabel());
                    }
                    panel1.updateUI();

                    confirmButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            String firstName = userFNTextField.getText();
                            String lastName = userLNTextField.getText();

                            String phoneNum = phoneTextField.getText();
                            String addy = addyTextField.getText();

                            String stringID = userIdTextField.getText();
                            int ID = 0;
                            try {
                                ID = Integer.parseInt(stringID);

                            } catch (NumberFormatException n) {
                                welcome.setText("!!Error: data improperly entered!! \n");
                                welcome.setText("FIX: Enter integers for quantity; Enter floats for price");
                                System.out.println("Caught");
                            }

                            hardwareStore.editCustomerInformation(ID, firstName, lastName, phoneNum, addy);
                            welcome.setText("User Info has been updated");
                        }
                    });
                    panel1.updateUI();
                }
            }
        });
        panel1.updateUI();
    }

    /**
     * This method will lead user to complete a transaction.
     */

    public void completeTransaction(){
        panel1.removeAll();
        panel1.add(label1);
        panel1.add(options);

        JLabel customerIDLabel  = new JLabel("Customer ID(int)", JLabel.RIGHT);
        panel1.add(customerIDLabel);
        JTextField customerIDText = new JTextField("");
        panel1.add(customerIDText);
        JLabel EmployeeLabel = new JLabel(" Employee ID (int)",JLabel.RIGHT);
        panel1.add(EmployeeLabel);
        JTextField EmployeeText = new JTextField("");
        panel1.add(EmployeeText);
        JLabel ItemIDLabel = new JLabel("Item ID (string)",JLabel.RIGHT);
        panel1.add(ItemIDLabel);
        JTextField ItemIDName = new JTextField("");
        panel1.add(ItemIDName);
        JLabel quantityLabel = new JLabel("Quantity(int)",JLabel.RIGHT);
        panel1.add(quantityLabel);
        JTextField quantityText = new JTextField("");
        panel1.add((quantityText));
        panel1.add(new JLabel());
        JButton confirmbutton = new JButton("Confirm");
        panel1.add(confirmbutton);
        for (int i = 1; i <= 15; i++) {
            panel1.add(new JLabel());
        }
        panel1.updateUI();

        confirmbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String itemID = ItemIDName.getText();
                Integer customerID = Integer.parseInt(customerIDText.getText());
                Integer employeeID = Integer.parseInt(EmployeeText.getText());
                Integer quantity = Integer.parseInt(quantityText.getText());

                int itemIndex;

                    itemIndex = hardwareStore.findItemIndex(itemID);
                    if (itemIndex == -1) {
                        welcome.setText("Item not found. Will return to main menu.");
                        return;
                    } else {
                        Item tempItem = hardwareStore.findItem(itemID);
                        try {
                            if (quantity <= 0) {
                                welcome.setText("Invalid input: must be greater than 0.");
                            } else if (quantity > tempItem.getQuantity()) {
                                welcome.setText("Invalid input: Number too big. Transaction cannot progress.");
                            }
                        } catch (Exception E) {
                            welcome.setText("Amount of items sold input invalid: not an integer");
                        }
                    }

                    try {
                        if (hardwareStore.findUserIndex(employeeID) == -1) {
                            welcome.setText("User not found.");
                        } else if (!hardwareStore.findUser(employeeID).isEmployee) {
                            welcome.setText("This user is not an employee.");
                        }
                    }
                    catch (Exception E) {
                        welcome.setText("Illegal input: Must input an integer.");
                    }

                    try {
                        if (hardwareStore.findUserIndex(customerID) == -1) {
                            welcome.setText("User not found.");
                        } else if (hardwareStore.findUser(customerID).isEmployee) {
                            welcome.setText("This user is not a customer.");
                        }
                    }
                    catch (Exception E) {
                        welcome.setText("Illegal input: Must input an integer.");
                    }
                hardwareStore.progressTransaction(itemID, quantity, customerID, employeeID, itemIndex);
                welcome.setText("Transaction complete.");
            }
        });
    panel1.updateUI();
    }

    /**
     * Shows all completed transactions in the database
     */
    public void showcompleted(){ welcome.setText(hardwareStore.getAllTransactionsFormatted()); }

    /**
     * These method is called to save the database before exit the system.
     * @throws IOException
     */
    public void saveDatabase(){
        try { hardwareStore.writeDatabase(); }
        catch (IOException e) { e.printStackTrace(); }
    }
}