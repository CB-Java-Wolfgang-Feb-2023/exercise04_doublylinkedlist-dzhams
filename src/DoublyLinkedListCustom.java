public class DoublyLinkedListCustom implements MyListInterface {
    private Node head;
    private Node tail;
    private int size;

    @Override
    public void addFirst(int item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(int item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    @Override
    public void addAtIndex(int index, int item) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Ungültiger Index");
        }
        if (index == 0) {
            addFirst(item);
        } else if (index == size) {
            addLast(item);
        } else {
            Node newNode = new Node(item);
            Node current = getNodeAtIndex(index);
            Node previous = current.getPrevious();
            newNode.setNext(current);
            newNode.setPrevious(previous);
            current.setPrevious(newNode);
            previous.setNext(newNode);
            size++;
        }
    }

    @Override
    public void addElementAtRandomIndex(int value) {
        if (isEmpty()) {
            // Wenn die Liste leer ist, füge das Element an den Anfang hinzu
            addFirst(value);
        } else {
            // Erzeuge einen zufälligen Index zwischen 0 und size (einschließlich size)
            int randomIndex = (int) (Math.random() * (size + 1));

            if (randomIndex == 0) {
                // Wenn der Index 0 ist, füge das Element an den Anfang hinzu
                addFirst(value);
            } else if (randomIndex == size) {
                // Wenn der Index size ist, füge das Element am Ende hinzu
                addLast(value);
            } else {
                // Füge das Element an einem zufälligen Index in der Mitte der Liste hinzu
                addAtIndex(randomIndex, value);
            }
        }
    }

    @Override
    public Node removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node removedNode = head;
        head = head.getNext();
        if (head == null) {
            tail = null;
        } else {
            head.setPrevious(null);
        }
        size--;
        return removedNode;
    }

    @Override
    public Node removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node removedNode = tail;
        tail = tail.getPrevious();
        if (tail == null) {
            head = null;
        } else {
            tail.setNext(null);
        }
        size--;
        return removedNode;
    }

    @Override
    public Node removeAtIndex(int index) {
        if (isEmpty() || index < 0 || index >= size) {
            return null;
        }
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node current = getNodeAtIndex(index);
            Node previous = current.getPrevious();
            Node next = current.getNext();
            previous.setNext(next);
            next.setPrevious(previous);
            size--;
            return current;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.getValue() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    @Override
    public void printListBackwards() {
        Node current = tail;
        while (current != null) {
            System.out.print(current.getValue() + " ");
            current = current.getPrevious();
        }
        System.out.println();
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Ungültiger Index");
        }
        Node node = getNodeAtIndex(index);
        return node.getValue();
    }
    @Override
    public void removeDuplicates() {
        if (isEmpty()) {
            return; // Die Liste ist leer, es gibt keine Duplikate
        }

        Node current = head;
        while (current != null) {
            Node runner = current.getNext();
            while (runner != null) {
                if (current.getValue() == runner.getValue()) {
                    // Ein Duplikat gefunden, löschen
                    Node nextRunner = runner.getNext();
                    Node previousRunner = runner.getPrevious();

                    if (previousRunner != null) {
                        previousRunner.setNext(nextRunner);
                    } else {
                        head = nextRunner;
                    }

                    if (nextRunner != null) {
                        nextRunner.setPrevious(previousRunner);
                    } else {
                        tail = previousRunner;
                    }

                    size--;
                }
                runner = runner.getNext();
            }
            current = current.getNext();
        }
    }

    @Override
    public void reverseList() {
        if (isEmpty() || size == 1) {
            return; // Die Liste ist leer oder hat nur ein Element, es gibt keine Änderung
        }

        // Starte am Ende der Liste
        Node current = tail;
        Node temp; // Temporäre Variable zum Umdrehen der Zeiger

        while (current != null) {
            // Tausche die next- und previous-Zeiger des aktuellen Knotens
            temp = current.getNext();
            current.setNext(current.getPrevious());
            current.setPrevious(temp);

            if (current.getPrevious() == null) {
                // Aktuelles Element war zuvor das letzte Element, daher ist es jetzt der neue Kopf
                head = current;
            }

            current = current.getNext(); // Gehe zum vorherigen Element in der umgedrehten Reihenfolge
        }
    }

    @Override
    public DoublyLinkedListCustom copyList() {
        DoublyLinkedListCustom copy = new DoublyLinkedListCustom();
        Node current = head;

        while (current != null) {
            copy.addLast(current.getValue());
            current = current.getNext();
        }

        return copy;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean insertAfter(int key, int data) {
        Node current = head;

        while (current != null) {
            if (current.getValue() == key) {
                Node newNode = new Node(data);
                Node nextNode = current.getNext();

                newNode.setNext(nextNode);
                newNode.setPrevious(current);

                current.setNext(newNode);

                if (nextNode != null) {
                    nextNode.setPrevious(newNode);
                } else {
                    // Wenn der aktuelle Knoten zuvor das letzte Element der Liste war
                    tail = newNode;
                }

                size++;
                return true; // Das Einfügen war erfolgreich
            }

            current = current.getNext();
        }

        return false; // Der Schlüssel wurde nicht gefunden, Einfügen nicht möglich
    }



    private Node getNodeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Ungültiger Index");
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    @Override
    public Node deleteKey(int key) {
        Node current = head;
        while (current != null) {
            if (current.getValue() == key) {
                Node previous = current.getPrevious();
                Node next = current.getNext();

                if (previous != null) {
                    previous.setNext(next);
                } else {
                    // Wenn das zu löschende Element das erste Element der Liste ist
                    head = next;
                }

                if (next != null) {
                    next.setPrevious(previous);
                } else {
                    // Wenn das zu löschende Element das letzte Element der Liste ist
                    tail = previous;
                }

                size--;
                return current;
            }
            current = current.getNext();
        }

        return null; // Das Element wurde nicht gefunden
    }

}
