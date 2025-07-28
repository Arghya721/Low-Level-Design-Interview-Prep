import shape.*;

public class Main {
    public static void main(String[] args) {
        // Create a ShapeFactory instance
        ShapeFactory shapeFactoryObj = new ShapeFactory();
        // Get a Circle object and call its draw method
        Shape circle = shapeFactoryObj.getShape("CIRCLE");
        circle.draw();
        // Get a Square object and call its draw method
        Shape square = shapeFactoryObj.getShape("SQUARE");
        square.draw();
    }
}
