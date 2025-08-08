package node

type Node struct {
	Key   int
	Value int
	Next  *Node
	Prev  *Node
}

func NewNode(key int, val int) *Node {
	return &Node{
		Key:   key,
		Value: val,
	}
}
