package lrucache

import (
	"lru-cache/node"
)

type LRUCache struct {
	head     *node.Node
	tail     *node.Node
	capacity int
	mp       map[int]*node.Node
}

func Constructor(capacity int) LRUCache {
	newHead := node.NewNode(0, 0)
	newTail := node.NewNode(0, 0)
	newHead.Next = newTail
	newTail.Prev = newHead

	return LRUCache{
		capacity: capacity,
		mp:       make(map[int]*node.Node),
		head:     newHead,
		tail:     newTail,
	}
}

func (cache *LRUCache) addNode(node *node.Node) {
	nex := cache.head.Next
	cache.head.Next = node
	node.Prev = cache.head
	nex.Prev = node
	node.Next = nex
}

func (cache *LRUCache) deleteNode(node *node.Node) {
	prev := node.Prev
	nex := node.Next
	prev.Next = nex
	nex.Prev = prev
}

func (cache *LRUCache) Get(key int) int {
	if node, ok := cache.mp[key]; ok {
		value := node.Value
		cache.deleteNode(node)
		cache.addNode(node)
		return value
	}
	return -1
}

func (cache *LRUCache) Put(key int, value int) {
	if node, ok := cache.mp[key]; ok {
		node.Value = value
		cache.deleteNode(node)
		cache.addNode(node)
		return
	}

	if len(cache.mp) == cache.capacity {
		temp := cache.tail.Prev
		cache.deleteNode(temp)
		delete(cache.mp, temp.Key)
	}

	newNode := node.NewNode(key, value)
	cache.addNode(newNode)
	cache.mp[key] = newNode
}
