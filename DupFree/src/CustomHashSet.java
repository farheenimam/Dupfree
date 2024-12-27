public class CustomHashSet {
    private class Node{
        String value;
        Node next;

        public Node(String value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node[] table;
    private int size;

    public CustomHashSet(int capacity) {
        table = new Node[capacity];
        size = 0;
    }

    // Hash function to compute the index
    private int hash(String value) {
        return Math.abs(value.hashCode() % table.length);
    }


    // Add an element to the set
    public void add(String value) {
        int index = hash(value);
        Node newNode = new Node(value);
        Node current = table[index];

        if (current == null) {
            table[index] = newNode;
        } else {
            // Check for duplicates and add to the linked list if not present
            while (current != null) {
                if (current.value.equals(value)) {
                    return;  // Element already exists
                }
                current = current.next;
            }
            // Add to the beginning of the linked list at this index
            newNode.next = table[index];
            table[index] = newNode;
        }
        size++;
    }

    // Check if an element is in the set
    public boolean contains(String value) {
        value = value.toLowerCase();
        int index = hash(value);
        Node current = table[index];

        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Get the size of the set
    public int size() {
        return size;
    }

    // Get the union of two CustomHashSets
    public CustomHashSet union(CustomHashSet other) {
        CustomHashSet result = new CustomHashSet(this.table.length + other.table.length);

        // Add all elements from this set
        for (int i = 0; i < table.length; i++) {
            Node current = table[i];
            while (current != null) {
                result.add(current.value);
                current = current.next;
            }
        }

        // Add all elements from the other set
        for (int i = 0; i < other.table.length; i++) {
            Node current = other.table[i];
            while (current != null) {
                result.add(current.value);
                current = current.next;
            }
        }
        return result;
    }

    // Get the intersection of two CustomHashSets
    public CustomHashSet intersection(CustomHashSet other) {
        CustomHashSet result = new CustomHashSet(this.table.length);

        // Add elements that are common to both sets
        for (int i = 0; i < table.length; i++) {
            Node current = table[i];
            while (current != null) {
                if (other.contains(current.value)) {
                    result.add(current.value);
                }
                current = current.next;
            }
        }
        return result;
    }
}