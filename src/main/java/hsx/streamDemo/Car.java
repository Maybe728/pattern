package hsx.streamDemo;

public class Car {
    String color;
    String width;
    String height;
    double price;

    public Car(String color, String width, String height, double price) {
        this.color = color;
        this.width = width;
        this.height = height;
        this.price = price;
    }

    public Car() {

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        final Car car = (Car) obj;
        if (this == car) {
            return true;
        } else {
            return (this.color.equals(car.color) && this.width.equals(car.width) && this.height.equals(car.height) && this.price == car.price);
        }
    }
    @Override
    public int hashCode() {
        int hashno = 7;
        hashno = 13 * hashno + (color == null ? 0 : color.hashCode());
        return hashno;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", price=" + price +
                '}';
    }
}
