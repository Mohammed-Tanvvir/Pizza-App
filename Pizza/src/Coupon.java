import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Abstract class representing the model for coupons
abstract class Coupon {
    abstract double applyDiscount(double originalPrice);
}