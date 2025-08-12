public class MarkSheetGenerationSystem {
    public static void main(String [] args)  {
        try {
            DatabaseConnection.getConnection();
        }
        catch (Exception e){
            System.out.println("Connection failed");
            System.exit(0);
        }
        System.out.println("Welcome Student MarksSheet Generation System");
        DoublyLinkedList doublyLinkedList=new DoublyLinkedList();
        Queue queue;
        while (true){
            System.out.println("Press 1 for add student");
            System.out.println("Press 2 for display student");
            System.out.println("Press 3 for view marks sheet ");
            System.out.println("Press 4 for delete student ");
            System.out.println("Press 5 for view top 10 student in semester 1");
            System.out.println("Press 6 for view top 10 student in semester 2");
            System.out.println("Press 7 for exit");
            System.out.println("Enter choice ");
            int choice=Student.validIntInput();
            switch (choice){
                case 1:
                    Student student=new Student();
                    student.takeInput();
                    doublyLinkedList.addLast(student);
                    break;
                case 2:
                    doublyLinkedList.display();
                    break;
                case 3:
                    FileOperations.fetch();
                    break;
                case 4:
                    DatabaseConnection.deleteStudent();
                    break;
                case 5:
                    queue=new Queue();
                    DatabaseConnection.getEnrollNameSpi(queue,"Semester_i");
                    queue.display();
                    break;
                case 6:
                    queue=new Queue();
                    DatabaseConnection.getEnrollNameSpi(queue,"Semester_ii");
                    queue.display();
                    break;
                case 7:
                    System.out.println("Thank you");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter valid choice");
            }
        }
    }
}