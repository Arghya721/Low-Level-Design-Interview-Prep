package player

import "tictactoe/shape"

type Player struct {
	Name  string
	Shape shape.Shape
}

func NewPlayer(name string, shape shape.Shape) *Player {
	return &Player{
		Name:  name,
		Shape: shape,
	}
}

func (p *Player) GetName() string {
	return p.Name
}

func (p *Player) GetShape() shape.Shape {
	return p.Shape
}
