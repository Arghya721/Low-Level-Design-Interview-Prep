package dice

import (
	"math/rand"
	"time"
)

type Dice struct {
	sides uint
}

func NewDice(sides uint) *Dice {
	return &Dice{
		sides: sides,
	}
}

func (d *Dice) Roll() uint {
	r := rand.New(rand.NewSource(time.Now().UnixNano()))
	return uint(r.Intn(int(d.sides))) + 1
}
