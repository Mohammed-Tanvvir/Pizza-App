class PepperoniDiscount extends Coupon {
    @Override
    double applyDiscount(double originalPrice) {
        return originalPrice * 0.9; // 10% off
    }
}