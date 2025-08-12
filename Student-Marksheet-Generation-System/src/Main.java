/*import java.util.Scanner;

class DLL{
    class Node{
        Studetss studetss;
        Node next;
        Node(Studetss studentss){
            this.studetss=studentss;
            next=null;
        }
    }
    Node head=null;
    void addFirst(Studetss studetss){
        Node n=new Node(studetss);
        if(head==null){
            head=n;
        }else {
            n.next=head;
            head=n;
        }
    }
    void display(){
        if(head==null){
            System.out.println("empty");
        }else {
            Node temp=head;
            while (temp!=null){
                Studetss ss=temp.studetss;
                System.out.println(ss.en);
                System.out.println(ss.name);
                temp=temp.next;
            }
        }
    }
}
class Main{
    public static void main(String[] args) {
        Studetss studetss=new Studetss();
        DLL dll=new DLL();
        Scanner sc=new Scanner(System.in);
        while (true) {
            System.out.println("1 add student");
            System.out.println("2 display");
            System.out.println("3 exit");
            int ch=sc.nextInt();
            switch (ch) {
                case 1 : studetss.addStudent(); dll.addFirst(studetss); break;
                case 2 :dll.display(); break;
                case 3 : System.exit(0); break;
            }
            //break;
        }
    }
}
class Studetss{
    long en;
    String name;
    Studetss (long en,String name){
        this.en=en;
        this.name=name;
    }
    Studetss(){}
    void addStudent(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter en");
        long en=sc.nextLong();
        sc.nextLine();
        System.out.println("Enter name");
        String name=sc.nextLine();
        this.en=en;
        this.name=name;

    }
}*/
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

class DLL {
    class Node {
        Students student;
        Node next;
        Node(Students student) {
            this.student = student;
            this.next = null;
        }
    }

    Node head = null;

    void addFirst(Students student) {
        Node n = new Node(student);
        if (head == null) {
            head = n;
        } else {
            n.next = head;
            head = n;
        }
    }

    void display() {
        if (head == null) {
            System.out.println("empty");
        } else {
            Node temp = head;
            while (temp != null) {
                Students s = temp.student;
                System.out.println("Enrollment Number: " + s.en);
                System.out.println("Name: " + s.name);
                temp = temp.next;
            }
        }
    }
}

class Students {
    long en;
    String name;

    Students(long en, String name) {
        this.en = en;
        this.name = name;
    }

    Students() {}

    void addStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter enrollment number:");
        this.en = sc.nextLong();
        sc.nextLine();  // Consume newline
        System.out.println("Enter name:");
        this.name = sc.nextLine();
    }
}

class Main {
    //public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        /*DLL dll = new DLL();

        while (true) {
            System.out.println("1. Add student");
            System.out.println("2. Display");
            System.out.println("3. Exit");
            int ch = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (ch) {
                case 1:
                    Students student = new Students();  // Create a new student instance
                    student.addStudent();
                    dll.addFirst(student);
                    break;
                case 2:
                    dll.display();
                    break;
                case 3:
                    sc.close(); // Close the scanner
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }*/
        //Collections collections= (Collections) DatabaseConnection.getConnection();
    static void updateStudent(Student student) throws  Exception{
        Connection connection=DatabaseConnection.getConnection();
        if(connection!=null){
            System.out.println("yes");
        }
//        Student student=new Student();
//        student.semester="semester_i";
//        student.spi=78;
//        student.totalCredit=78;
//        student.gradePointEarned=56;
//        student.result="Fail";
//        student.enrollmentNo=1;
        FileReader fileReader=new FileReader(student.enrollmentNo+".txt");
        //FileReader fileReader=
       String query= "UPDATE "   +student.semester+
               " SET spi=?, totalCredit=?, GradePointEarned=?, Result=?, Marksheet=? " +
               "WHERE EnrollmentNo=?";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setDouble(1,student.spi);
        statement.setDouble(2,student.totalCredit);
        statement.setDouble(3,student.gradePointEarned);
        statement.setString(4,student.result);
        statement.setClob(5,fileReader);
        statement.setLong(6,student.enrollmentNo);
        int n=statement.executeUpdate();
        if(n>0){
            System.out.println("eds");
        }else {
            System.out.println("wesfdcv");
        }
        //Collections collections1=DatabaseConnection.getConnection();
    }
}
