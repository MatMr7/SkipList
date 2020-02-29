
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;


public class SkipList<K extends Comparable<K> , V> implements Iterable<K> {
	
	public static final double Probabilidade_Inicial = 0.5;
	public static final Random NumberGenerator = new Random();
	
	private Node<K,V> head;
	private double probabilidade ;
	private int size;
	
	public SkipList() {
		this(Probabilidade_Inicial);
	}

	 
	 public SkipList(double probabilidade) {
		 this.head = new Node<K,V>(null,null,0);
		 this.probabilidade = probabilidade;
		 this.size =0;
	 }
	 
	 public void add(K key , V value) {
		 this.checkKeyValidity(key);
		 Node<K,V> node = findNode(key);
		 if(node.getKey()!= null && node.getKey().compareTo(key) == 0 ) {
			 node.setValue(value);
			 return;
		 }
		 Node<K,V> newNode = new Node<K,V>(key,value,node.getLevel());
		 this.horizontalInsert(node, newNode);
		 
		 int currentLevel = node.getLevel();
		 int headLevel = head.getLevel();
		 //construir novo nivel
		 while(this.isBuildLevel()) {
			 if(currentLevel >= headLevel) {
				 Node<K,V> newHead = new Node<K,V>(null,null,headLevel +1);
				 this.verticalLink(newHead, head);
				 this.head = newHead;
				 headLevel = this.head.getLevel();
			 }
			 //coloca o novo node no nivel criado
			 while(node.getUp() == null) {
				 node = node.getPrevious();
			 }
			 node = node.getUp(); 
			 Node<K,V> tmp = new Node<K,V>(key,value,node.getLevel());
			 this.horizontalInsert(node, tmp);
			 this.verticalLink(tmp, newNode);
			 newNode= tmp;
			 currentLevel += 1;
		 }
		 size += 1;
	 }
	 
	 public void remove(K key) {
		 this.checkKeyValidity(key);
		 Node<K,V> node = this.findNode(key);
		 if(node.getKey().compareTo(key) != 0 || node == null)
			 throw new NoSuchElementException("A chave não existe");
		 while (node.getDown() != null) {
			 node = node.getDown();
		 }
		  Node<K, V> prev = null;
	      Node<K, V> next = null;
	      for (; node != null; node = node.getUp()) {
	    	  prev = node.getPrevious();
	          next = node.getNext();
	          if (prev != null)
	              prev.setNext(next);
	          if (next != null)
	              next.setPrevious(prev);
	        }
	      while (head.getNext() == null && head.getDown() != null) {
	    	  head = head.getDown();
	          head.setUp(null);
	      }
	      size-=1;
	 }
	 
	 public int size() {
	        return size;
	 }
	 
	 private Node<K,V>findNode(K key){
		 
		 K nodeKey = null;
		 Node<K, V> next = null;
		 Node<K, V> down = null;
		 Node<K, V> node = head;
		 
		 while(true) {
		 next = node.getNext();
		 //Procura o node mais proximo, menor ou igual, com o valor requisitado
		 while (next != null && lessThanOrEqual(next.getKey(),key)) {
			 node = next;
			 next = node.getNext();
		 }
		 nodeKey = node.getKey();
		 if(nodeKey != null && nodeKey.compareTo(key) == 0) {
			 break;
		 }
		 //descendo um nivel e continuando a pesquisa
		 down = node.getDown();
		 if(down != null) {
			 node = down;
		 }else {
			 break;
		 }
		 
	 }
		 return node;
	 }
	 
	 
	 
	  private boolean lessThanOrEqual(K a, K b) {
	        return a.compareTo(b) <= 0;
	    }
	  
	  public V get(K key) {
		  this.checkKeyValidity(key);
		  Node<K,V> node = findNode(key);
		  if(node.getKey().compareTo(key) == 0){
			  return node.getValue();
		  }
		  return null;
	  }
	  
	  
	  public boolean contains(K key) {
		  return get(key) != null;
	  }
	  protected boolean isBuildLevel() {
	        return NumberGenerator.nextDouble() < probabilidade;
	    }
	  
	  private void checkKeyValidity(K key) {
		  if(key == null) {
			  throw new IllegalArgumentException("A chave não deve ser nula");
		  }
	  }

	  private void verticalLink(Node<K, V> x, Node<K, V> y) {
	        x.setDown(y);
	        y.setUp(x);
	    }
	  private void horizontalInsert(Node<K,V> x, Node<K,V> y) {
		  y.setPrevious(x);
		  y.setNext(x.getNext());
		  if(x.getNext() != null) x.getNext().setPrevious(y);
		  x.setNext(y);
	  }
	  
	  

	
	public boolean empty() {
	        return size == 0;
	}


	@Override
	public Iterator<K> iterator() {
		return new SkipListIterator<K, V>(head);
	}






	
	

}
