public class DoublyLinkedList {
    class Node{
        Student student;
        Node previous;
        Node next;
        Node(Student student){
            this.student=student;
            previous=null;
            next=null;
        }
    }
    Node head=null;
    public  void addLast(Student student){
        Node node=new Node(student);
        if(head==null){
            System.out.println(node.student.enrollmentNo);
            head=node;
        }
        else {
            DoublyLinkedList.Node temporary=head;
            System.out.println(node.student.enrollmentNo);
            while (temporary.next!=null){
                temporary=temporary.next;
            }
            temporary.next=node;
        }
    }
    /*public void delete(long enrollmentNo){
        if(head==null){
            System.out.println("No student available");
            return;
        }

        if(head.student.enrollmentNo==enrollmentNo){
            if(head.next==null){
                head=null;
            }
            else {
                head=head.next;
                head.previous.next=null;
                head.previous=null;
            }
        }
        else {
            Node temp=head;
            while (temp!=null && temp.student.enrollmentNo!=enrollmentNo ) {
                temp=temp.next;
            }

            if(temp==null){
                System.out.println("Student not found");
            }
            else {
                temp.previous.next=temp.next;
                if(temp.next!=null){
                    temp.next.previous=temp.previous;
                }
                else {
                    temp.previous=null;
                }
            }
        }
    }*/
    public  void display(){
        if(head==null){
            System.out.println("List is empty");
        }
        else {
            Node temp=head;
            while (temp!=null){
                //Student student1= temp.student;
                System.out.println("enrollment No : "+temp.student.enrollmentNo);
                temp=temp.next;
            }
        }
    }
}