package ru.job4j;

/**
 * FoodReproducer class.
 *
 * @author Denis
 * @since 15.02.2017
 */
public class FoodReproducer extends FoodDecorator {
    /**
     * Food.
     */
    private Food food;

    /**
     * Can reproduce.
     */
    private boolean canReproduce;

    /**
     * Default constructor.
     *
     * @param food         food.
     * @param canReproduce canReproduce.
     */
    public FoodReproducer(Food food, boolean canReproduce) {
        this.food = food;
        this.canReproduce = canReproduce;
    }

    /**
     * Get reproduce flag.
     *
     * @return canReproduce.
     */
    public boolean isCanReproduce() {
        return canReproduce;
    }

    /**
     * Set reproduce flag.
     *
     * @param canReproduce reproduce flag.
     */
    public void setCanReproduce(boolean canReproduce) {
        this.canReproduce = canReproduce;
    }

    /**
     * Get freshness of food.
     *
     * @param currentDate current time.
     * @return freshness of food.
     */
    @Override
    public double getFreshness(double currentDate) {
        double result = food.getFreshness(currentDate);
        if (this.canReproduce && result > 1.0) {
            food.setExpireDate(food.getCreateDate() + (food.getExpireDate() - food.getCreateDate()) * 2);
            result = food.getFreshness(currentDate);
            this.canReproduce = false;
        }
        System.out.println(result);
        return result;
    }

    /**
     * Get food name.
     *
     * @return name.
     */
    @Override
    public String getName() {
        return food.getName();
    }

    /**
     * Get date of creation.
     *
     * @return date of creation.
     */
    @Override
    public long getCreateDate() {
        return food.getCreateDate();
    }

    /**
     * Get date of expire.
     *
     * @return expires date.
     */
    @Override
    public long getExpireDate() {
        return food.getExpireDate();
    }

    /**
     * Get price without discount..
     *
     * @return price.
     */
    @Override
    public double getPrice() {
        return food.getPrice();
    }

    /**
     * Get price with discount.
     *
     * @return price with discount.
     */
    @Override
    public double getPriceWithDiscount() {
        return food.getPriceWithDiscount();
    }

    /**
     * Get discount percent.
     *
     * @return discount.
     */
    @Override
    public double getDiscount() {
        return food.getDiscount();
    }

    /**
     * Set name of food.
     *
     * @param name name.
     */
    @Override
    public void setName(String name) {
        food.setName(name);
    }

    /**
     * Set food price.
     *
     * @param price price.
     */
    @Override
    public void setPrice(double price) {
        food.setPrice(price);
    }

    /**
     * Set create date.
     *
     * @param createDate createDate.
     */
    @Override
    public void setCreateDate(long createDate) {
        food.setCreateDate(createDate);
    }

    /**
     * Set expire date.
     *
     * @param expireDate expireDate.
     */
    @Override
    public void setExpireDate(long expireDate) {
        food.setExpireDate(expireDate);
    }

    /**
     * Det discount percent.
     *
     * @param discount discount.
     */
    @Override
    public void setDiscount(double discount) {
        food.setDiscount(discount);
    }
}
