public class Main {
    public static void main(String[] args) {
        DoublyLinkedListCustom list = new DoublyLinkedListCustom();

        // Füge einige Elemente hinzu
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(2); // Duplikat
        list.addLast(4);

        System.out.println("Original List:");
        list.printList();

        // Teste die Methoden
        list.removeDuplicates();
        System.out.println("Liste nach Entfernen von Duplikaten:");
        list.printList();

        list.reverseList();
        System.out.println("Liste nach Umkehren:");
        list.printList();

        DoublyLinkedListCustom copiedList = list.copyList();
        System.out.println("Kopierte Liste:");
        copiedList.printList();

        list.clear();
        System.out.println("Liste nach dem Löschen:");
        list.printList();
    }
}
