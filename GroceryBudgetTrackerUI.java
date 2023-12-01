import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class GroceryBudgetTrackerUI {
    private JFrame frame;
    private JLabel label;
    private JLabel totalLabel;
    private ArrayList<GroceryItem> groceryList;

    public static void main(String[] args) {
        GroceryBudgetTrackerUI window = new GroceryBudgetTrackerUI();
        window.initialize();
    }

    public void initialize() {
        groceryList = new ArrayList<>();

        frame = new JFrame("Grocery Budget Tracker");
        frame.setBounds(100, 100, 400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        label = new JLabel("Welcome to Grocery Budget Tracker!");
        label.setBounds(10, 20, 300, 25);
        panel.add(label);

        JButton addButton = new JButton("Add Item");
        addButton.setBounds(10, 60, 120, 25);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });
        panel.add(addButton);

        JButton deleteButton = new JButton("Delete Item");
        deleteButton.setBounds(140, 60, 120, 25);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteItem();
            }
        });
        panel.add(deleteButton);

        JButton calculateButton = new JButton("Calculate Total");
        calculateButton.setBounds(270, 60, 120, 25);
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTotal();
            }
        });
        panel.add(calculateButton);

        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setBounds(10, 100, 150, 25);
        panel.add(totalLabel);
    }

    private void addItem() {
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();

        Object[] fields = {"Item Name:", nameField, "Item Price:", priceField};

        int result = javax.swing.JOptionPane.showConfirmDialog(null, fields, "Add Item", javax.swing.JOptionPane.OK_CANCEL_OPTION);

        if (result == javax.swing.JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());

            GroceryItem newItem = new GroceryItem(name, price);
            groceryList.add(newItem);

            System.out.println(name + " added to the list.");
        }
    }

    private void deleteItem() {
        JTextField nameField = new JTextField();

        Object[] fields = {"Item Name:", nameField};

        int result = javax.swing.JOptionPane.showConfirmDialog(null, fields, "Delete Item", javax.swing.JOptionPane.OK_CANCEL_OPTION);

        if (result == javax.swing.JOptionPane.OK_OPTION) {
            String nameToDelete = nameField.getText();

            for (GroceryItem item : groceryList) {
                if (item.name.equalsIgnoreCase(nameToDelete)) {
                    groceryList.remove(item);
                    System.out.println(item.name + " removed from the list.");
                    return;
                }
            }

            System.out.println("Item not found in the list.");
        }
    }

    private void calculateTotal() {
        double total = 0;
        for (int i = 0; i < groceryList.size(); i++) {
            total += groceryList.get(i).price;
        }
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }

    private static class GroceryItem {
        String name;
        double price;

        public GroceryItem(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }
}

