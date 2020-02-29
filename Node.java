
public class Node<K extends Comparable<K>, V> {
	
	private K key;
	private V value;
	private int level;
	
	private Node<K,V> up;
	private Node<K,V> down;
	private Node<K,V> next;
	private Node<K,V> previous;
	
	public Node(K key,V value,int level) {
		this.key = key;
		this.value = value;
		this.level =  level;
		
	}
	
	public void setKey(K key) {
		this.key = key;
	}
	
	public K getKey() {
		return this.key;
	}
	
	public void setValue(V value) {
		this.value = value;
	}
	
	public V getValue() {
		return this.value;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void setUp(Node<K,V> up) {
		this.up = up;
	}
	
	public Node<K,V> getUp() {
		return this.up;
	}
	
	public void setDown(Node<K,V> down) {
		this.down = down;
	}
	
	public Node<K,V> getDown(){
		return this.down;
	}
	
	public void setPrevious(Node<K,V> previous) {
		this.previous = previous;
	}
	
	public Node<K,V> getPrevious(){
		return this.previous;
	}
	
	public void setNext(Node<K,V> next) {
		this.next = next;
	}
	public Node<K,V> getNext(){
		return this.next;
	}
	
	
	
}
