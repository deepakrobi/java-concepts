package LRU;

import java.util.HashMap;

    class DoubleNode {
        int key;
        int value;
        DoubleNode pre;
        DoubleNode next;

        public DoubleNode(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }

    class LeastRecentlyUsedCache {
        private DoubleNode head, tail;
        int capacity, count;
        private HashMap<Integer, DoubleNode> map;

        public LeastRecentlyUsedCache(int capacity) {
            this.capacity = capacity;
            count = 0;
            head = new DoubleNode(0, 0);
            tail = new DoubleNode(0, 0);
            head.next = tail;
            head.pre = null;
            tail.next = null;
            tail.pre = head;
            map= new HashMap<>();
        }

        public void deleteNode(DoubleNode node) {
            node.next.pre = node.pre;
            node.pre.next = node.next;
        }

        public void addToHead(DoubleNode node) {
            node.next = head.next;
            node.pre = head;
            node.next.pre = node;
            head.next = node;
        }

        public int get(int key) {
            DoubleNode cachedNode = map.get(key);
            if (cachedNode == null) {
                System.out.println("Did not get any value for the key: " + key);
                return -1;
            }
            markNodeMostRecentlyUsed(cachedNode);
            System.out.println("found  value " + cachedNode.value + " for key " + key);
            return cachedNode.value;
        }

        public void set(int key, int value) {
            System.out.println("Going to set the (key, value) : (" + key + ", " + value + ")");

            DoubleNode cachedDoubleNode = map.get(key);
            if (cachedDoubleNode == null) {
                DoubleNode newNodesToBeCached = new DoubleNode(key, value);
                if (count < capacity) {
                    count++;
                } else {
                    // LRU needs to be evicted form cache.
                    System.out.println("Evicting key " + tail.pre.key);
                    map.remove(tail.pre.key);
                    deleteNode(tail.pre);
                }
                map.put(key, newNodesToBeCached);
                addToHead(newNodesToBeCached);
            } else {
                cachedDoubleNode.value = value;
                markNodeMostRecentlyUsed(cachedDoubleNode);
            }
        }

        private void markNodeMostRecentlyUsed(DoubleNode node){
            deleteNode(node);
            addToHead(node);
        }
    }

public class ImplementLRU {
        public static void main (String [] args) {
            LeastRecentlyUsedCache leastRecentlyUsedCache = new LeastRecentlyUsedCache(2);
            leastRecentlyUsedCache.set(1, 10);
            leastRecentlyUsedCache.set(2,20);
            leastRecentlyUsedCache.get(1);
            leastRecentlyUsedCache.set(3,30);
            leastRecentlyUsedCache.get(1);
            leastRecentlyUsedCache.get(2);
        }
    }
