import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PizzaChooserApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pizza Chooser");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);

            JPanel panel = new JPanel();
            frame.add(panel);
            placeComponents(panel);

            frame.setVisible(true);
        });
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(new BorderLayout());

        JLabel labelPizza = new JLabel("Choose your pizza:");
        panel.add(labelPizza, BorderLayout.NORTH);

        JComboBox<String> pizzaComboBox = new JComboBox<>(new String[]{"Pepperoni", "Mushroom", "Extra Cheese"});
        panel.add(pizzaComboBox, BorderLayout.CENTER);

        JLabel labelQuantity = new JLabel("Choose quantity:");
        panel.add(labelQuantity, BorderLayout.WEST);

        Integer[] quantityOptions = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        JComboBox<Integer> quantityComboBox = new JComboBox<>(quantityOptions);
        panel.add(quantityComboBox, BorderLayout.EAST);

        JButton orderButton = new JButton("Place Order");
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPizza = (String) pizzaComboBox.getSelectedItem();
                int quantity = (int) quantityComboBox.getSelectedItem();
                double originalPrice = getPrice(selectedPizza);
                double discountedPrice = applyCouponDiscount(selectedPizza, originalPrice);
                double totalPrice = discountedPrice * quantity;

                JOptionPane.showMessageDialog(panel, "You ordered " + quantity + " " + selectedPizza + " pizza(s). The total price is $" + totalPrice);
            }
        });
        panel.add(orderButton, BorderLayout.SOUTH);
    }

    private static double getPrice(String pizza) {
        switch (pizza) {
            case "Pepperoni":
                return PizzaPrices.PEPPERONI_PRICE;
            case "Mushroom":
                return PizzaPrices.MUSHROOM_PRICE;
            case "Extra Cheese":
                return PizzaPrices.EXTRA_CHEESE_PRICE;
            default:
                return 0.0;
        }
    }

    private static double applyCouponDiscount(String pizza, double originalPrice) {
        Coupon coupon = getCoupon(pizza);
        if (coupon != null) {
            return coupon.applyDiscount(originalPrice);
        }
        return originalPrice;
    }

    private static Coupon getCoupon(String pizza) {
        switch (pizza) {
            case "Pepperoni":
                return new PepperoniDiscount();
            case "Mushroom":
                return new MushroomDiscount();
            case "Extra Cheese":
                return new ExtraCheeseDiscount();
            default:
                return null;
        }
    }
}