class ExtraCheeseDiscount extends Coupon {
    @Override
    double applyDiscount(double originalPrice) {
        return originalPrice * 0.75; // 25% off
    }


}