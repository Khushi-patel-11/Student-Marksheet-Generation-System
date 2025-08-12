public class Queue {
    int front;
    int rear;
    Student_Information[] arr =new Student_Information[10];
    Queue(){
        this.front=-1;
        this.rear=-1;
    }
    void enqueue(Student_Information studentInformation){
        if(rear==(arr.length-1)){
            System.out.println("Overflow");
            return;
        }
        if(front==-1){
            front=0;
        }
        rear++;
        arr[rear]=studentInformation;
    }
    void display(){
        if(front==-1){
            System.out.println("Queue is empty");
        }else {
            for(int i=front;i<=rear;i++){
                System.out.println("Rank is :"+(i+1));
                Student_Information studentInformation=arr[i];
                System.out.println("EnrollmentNo is : "+studentInformation.enrollmentNo);
                System.out.println("Name is         : "+studentInformation.name);
                System.out.println("SPI is          : "+studentInformation.spi);
            }
        }
    }
}
class Student_Information{
    long enrollmentNo;
    String name;
    double spi;
    Student_Information(long enrollmentNo,String name,double spi){
        this.enrollmentNo=enrollmentNo;
        this.name=name;
        this.spi=spi;
    }
}
