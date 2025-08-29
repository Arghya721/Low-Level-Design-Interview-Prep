package restaurant

// Restaurant represents a restaurant with a menu and open/close state.
type Restaurant struct {
	ID     int
	Name   string
	Menu   []Item
	IsOpen bool
}

func NewRestaurant(id int, name string, menu []Item, isOpen bool) *Restaurant {
	return &Restaurant{ID: id, Name: name, Menu: menu, IsOpen: isOpen}
}

func (r *Restaurant) GetItem(itemID int) *Item {
	for i := range r.Menu {
		if r.Menu[i].ID == itemID {
			return &r.Menu[i]
		}
	}
	return nil
}

func (r *Restaurant) CloseItem(itemID int) {
	for i := range r.Menu {
		if r.Menu[i].ID == itemID {
			r.Menu[i].IsAvailable = false
			return
		}
	}
}
