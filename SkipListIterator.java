import java.util.Iterator;

public class SkipListIterator<K extends Comparable<K>, V> implements Iterator<K> {

        private Node<K, V> node;

        public SkipListIterator(Node<K, V> node) {
            while (node.getDown() != null) node = node.getDown();

            while (node.getPrevious() != null) node = node.getPrevious();

            if (node.getNext() != null)  node = node.getNext();

            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return this.node != null;
        }

        @Override
        public K next() {
            K result = node.getKey();
            node = node.getNext();
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }