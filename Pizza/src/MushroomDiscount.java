class MushroomDiscount extends Coupon {
    @Override
    double applyDiscount(double originalPrice) {
        return originalPrice * 0.85; // 15% off
    }
}