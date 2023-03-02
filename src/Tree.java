import java.util.Objects;

public class Tree {
    private Node rootNode;

    public Tree() {
        rootNode = null;
    }

    public void insertNode(Student student) {
        Node newNode = new Node();
        newNode.setValue(student);

        if (rootNode == null) {
            rootNode = newNode;
        }
        else {

            Node currentNode = rootNode;
            Node parentNode;
            while (true)
            {
                parentNode = currentNode;

                if(Objects.equals(student.getId(),currentNode.getValue().getId())) { // элемент существует
                    return;
                } else  if (student.getId() < currentNode.getValue().getId()) {   // <-
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null){
                        parentNode.setLeftChild(newNode);
                        return;
                    }
                } else { // ->
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        parentNode.setRightChild(newNode);
                        return;
                    }
                }
            }
        }
    }


    public Student findNodeByID(int ID) {
        long startTime = System.nanoTime();
        long endTime;
        Node currentNode = rootNode;
        while (currentNode.getValue().getId() != ID) {

            if (ID < currentNode.getValue().getId()) {
                currentNode = currentNode.getLeftChild();
            } else {
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null) {
                return null;
            }
        }
        endTime = System.nanoTime();
        System.out.println("Time of execution: " + (endTime - startTime) + " Nano Seconds");
        return currentNode.getValue();
    }

}