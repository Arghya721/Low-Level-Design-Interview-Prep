package player

type Player struct {
	name            string
	currentPosition int
}

func NewPlayer(name string) *Player {
	return &Player{
		name:            name,
		currentPosition: 0,
	}
}

func (p *Player) GetName() string {
	return p.name
}

func (p *Player) GetCurrentPosition() int {
	return p.currentPosition
}

func (p *Player) SetCurrentPosition(pos int) {
	p.currentPosition = pos
}
