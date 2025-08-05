package jumper

type Jumper interface {
	GetStartPosition() int
	GetEndPosition() int
}

type SnakeJumper struct {
	startPosition int
	endPosition   int
}

func (s *SnakeJumper) GetStartPosition() int {
	return s.startPosition
}

func (s *SnakeJumper) GetEndPosition() int {
	return s.endPosition
}

type LadderJumper struct {
	startPosition int
	endPosition   int
}

func (l *LadderJumper) GetStartPosition() int {
	return l.startPosition
}

func (l *LadderJumper) GetEndPosition() int {
	return l.endPosition
}

type JumperFactory struct{}

func NewSnakeJumper(startPos int, endPos int) *SnakeJumper {
	return &SnakeJumper{
		startPosition: startPos,
		endPosition:   endPos,
	}
}

func NewLadderJumper(startPos int, endPos int) *LadderJumper {
	return &LadderJumper{
		startPosition: startPos,
		endPosition:   endPos,
	}
}

func (j *JumperFactory) NewJumperFactory(jumper string, startPos int, endPos int) Jumper {
	switch jumper {
	case "snake":
		return NewSnakeJumper(startPos, endPos)
	case "ladder":
		return NewLadderJumper(startPos, endPos)
	default:
		return nil
	}
}
