package restaurant

// Item represents a single menu item in a restaurant.
type Item struct {
	ID          int
	Name        string
	Price       float64
	IsAvailable bool
}
