package shape;

// ShapeFactory is responsible for creating instances of Shape
public class ShapeFactory {
    public Shape getShape(String input) {
        switch (input) {
            case "CIRCLE":
                return new Circle();
            case "SQUARE":
                return new Square();
            default:
                return null;
        }
    }
}
