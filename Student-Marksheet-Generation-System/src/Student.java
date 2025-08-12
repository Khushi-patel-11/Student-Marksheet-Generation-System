import java.util.InputMismatchException;
import  java.util.Scanner;
public class Student  implements DatabaseConnection{
    long enrollmentNo;
    String name;
    String semester;
    Marks[] marks;
    int totalCredit;
    double gradePointEarned;
    double spi;
    String result;

    public void takeInput() {
        System.out.println("Enter enrollmentNo of student");
        this.enrollmentNo=Student.validLongInput();
        while (true){
            System.out.println("Enter choice ");
            System.out.println("Press 1 for semester 1  ");
            System.out.println("Press 2 for semester 2  ");
            int sem=validIntInput();
            if(DatabaseConnection.search(this.enrollmentNo,sem)){
                if(DatabaseConnection.getResult(enrollmentNo). equalsIgnoreCase("Pass")){
                    System.out.println("Already exists");
                    return;
                }
                else {
                    System.out.println("Enter marks for Remedial");
                }
            }
            if(sem==1){
                takeInputForSemI();
                break;
            }else if(sem==2){
                takeInputForSemII();
                break;
            }else {
                System.out.println("Enter valid choice");
            }
        }


        for (Marks mark : this.marks) {
            this.totalCredit += mark.subjectCredit;
            if (mark.subjectCredit == 4) {
                double d = mark.gradePointOfTheory * 0.75 * mark.subjectCredit;
                double f = mark.gradePointOfPractical * 0.25 * mark.subjectCredit;
                this.gradePointEarned = this.gradePointEarned + d + f;
                double e = mark.gradePointOfTheory * 0.75;
                double c = mark.gradePointOfPractical * 0.25;
                mark.overAllGradePoint = mark.overAllGradePoint + e + c;
                if (mark.gradePointOfTheory < 4 || mark.gradePointOfPractical < 4) {
                    mark.overAllGrade = "FAIL";
                } else {
                    mark.setGrade(mark.overAllGradePoint);
                }
            } else if (mark.subjectCredit == 6) {
                if (mark.subjectCode != 117011191) {
                    double d = mark.gradePointOfTheory * 0.50 * mark.subjectCredit;
                    double f = mark.gradePointOfPractical * 0.50 * mark.subjectCredit;
                    this.gradePointEarned = this.gradePointEarned + d + f;
                    double e = mark.gradePointOfTheory * 0.50;
                    double c = mark.gradePointOfPractical * 0.50;
                    mark.overAllGradePoint = mark.overAllGradePoint + e + c;
                    mark.setGrade(mark.overAllGradePoint);
                    if (mark.gradePointOfTheory < 4 || mark.gradePointOfPractical < 4) {
                        mark.overAllGrade = "FAIL";
                    } else {
                        mark.setGrade(mark.overAllGradePoint);
                    }
                } else {
                    this.gradePointEarned = this.gradePointEarned + mark.gradePointOfTheory * mark.subjectCredit;
                    mark.overAllGradePoint = mark.gradePointOfTheory;
                    mark.setGrade(mark.overAllGradePoint);
                }
            } else if (mark.subjectCredit == 2) {
                this.gradePointEarned = this.gradePointEarned + mark.gradePointOfPractical * mark.subjectCredit;
                mark.overAllGradePoint = mark.gradePointOfPractical;
                mark.setGrade(mark.overAllGradePoint);
            } else if (mark.subjectCredit == 0) {
                if (mark.gradePointOfTheory >= 4) {
                    mark.overAllGrade = "PASS";
                } else {
                    mark.overAllGrade = "FAIL";
                }
            } else if (mark.subjectCredit == 5) {
                this.gradePointEarned = this.gradePointEarned + mark.gradePointOfTheory * mark.subjectCredit;
                mark.overAllGradePoint = mark.gradePointOfTheory;
                mark.setGrade(mark.overAllGradePoint);
            }
            if (mark.overAllGrade.equalsIgnoreCase("FAIL")) {
                this.result = "FAIL";
            }
        }
        if(this.result==null){
            this.result="PASS";
        }
        this.spi=this.gradePointEarned/this.totalCredit;
        this.spi=Math.round(this.spi*100)/100.0;
        FileOperations.markSheetGeneration(this);
        DatabaseConnection.insert(this);
    }
    void takeInputForSemI(){
        Scanner scanner=new Scanner(System.in);
        if(!DatabaseConnection.search(this.enrollmentNo,1)) {
            System.out.println("Enter name : ");
            this.name = scanner.nextLine();
        }
        this.semester="Semester_I";
        this.marks=new Marks[7];
        System.out.println("Enter marks for Mathematics-I Theory");
        double marksOfMaths =validDoubleInput();
        System.out.println("Enter marks for Computer Programing using Java-I Theory  ");
        double marksOfJavaTh =validDoubleInput();
        System.out.println("Enter marks for Computer Programing using Java-I Practical  ");
        double marksOfJavaPr =validDoubleInput();
        System.out.println("Enter marks for Software Engineering of Theory");
        double marksOfSETh=validDoubleInput();
        System.out.println("Enter marks for Software Engineering of Practical");
        double marksOfSEPr=validDoubleInput();
        System.out.println("Enter marks for Physics of Theory");
        double marksOfPhyTh=validDoubleInput();
        System.out.println("Enter marks for Physics of Practical");
        double marksOfPhyPr=validDoubleInput();
        System.out.println("Enter marks for IOT Workshop - Laboratory of Theory");
        double marksOfIOT=validDoubleInput();
        System.out.println("Enter marks for Computer Workshop - Laboratory of Practical");
        double marksOfCWS=validDoubleInput();
        System.out.println("Enter marks for Environmental Science of Theory");
        double marksOfES=validDoubleInput();

        this.marks[0]=new Marks(117011191,6);
        this.marks[0].setGradePoint(marksOfMaths,"Theory");
        this.marks[0].setSubjectName(117011191);
        this.marks[1]=new Marks(117012191,6);
        this.marks[1].setGradePoint(marksOfJavaTh,"Theory");
        this.marks[1].setGradePoint(marksOfJavaPr,"Practical");
        this.marks[1].setSubjectName(117012191);
        this.marks[2]=new Marks(17013191,4);
        this.marks[2].setGradePoint(marksOfSETh,"Theory");
        this.marks[2].setGradePoint(marksOfSEPr,"Practical");
        this.marks[2].setSubjectName(17013191);
        this.marks[3]=new Marks(117011192,4);
        this.marks[3].setGradePoint(marksOfPhyTh,"Theory");
        this.marks[3].setGradePoint(marksOfPhyPr,"Practical");
        this.marks[3].setSubjectName(117011192);
        this.marks[4]=new Marks(17012192,2);
        this.marks[4].setGradePoint(marksOfIOT,"Practical");
        this.marks[4].setSubjectName(17012192);
        this.marks[5]=new Marks(17012193,2);
        this.marks[5].setGradePoint(marksOfCWS,"Practical");
        this.marks[5].setSubjectName(17012193);
        this.marks[6]=new Marks(17018191,0);
        this.marks[6].setGradePoint(marksOfES,"Theory");
        this.marks[6].setSubjectName(17018191);

    }
    void takeInputForSemII(){

        this.semester="Semester_II";
        this.marks=new Marks[5];
        System.out.println("Enter marks for Data Structure using Java Theory");
        double marksOfDSTh= validDoubleInput();
        System.out.println("Enter marks for Data Structure using Java Practical");
        double marksOfDSPr= validDoubleInput();
        System.out.println("Enter marks for Database Management System  Theory");
        double marksOfDBMSTh= validDoubleInput();
        System.out.println("Enter marks for Database Management System  Practical");
        double marksOfDBMSPr= validDoubleInput();
        System.out.println("Enter marks for Computer Programing using Java-II Theory");
        double marksOfJavaIITh= validDoubleInput();
        System.out.println("Enter marks for Computer Programing using Java-II Practical");
        double marksOfJavaIIPr= validDoubleInput();
        System.out.println("Enter marks for Mathematics-II Theory");
        double marksOfMathsIITh= validDoubleInput();
        System.out.println("Enter marks for Fundamental of Electronics and Electrical Engineering Theory");
        double marksOfFeeTh= validDoubleInput();
        System.out.println("Enter marks for Fundamental of Electronics and Electrical Engineering Practical");
        double marksOfFeePr= validDoubleInput();

        this.marks[0]=new Marks(11701392,6);
        this.marks[0].setGradePoint(marksOfDSTh,"Theory");
        this.marks[0].setGradePoint(marksOfDSPr,"Practical");
        this.marks[0].setSubjectName(11701392);
        this.marks[1]=new Marks(11701391,6);
        this.marks[1].setGradePoint(marksOfDBMSTh,"Theory");
        this.marks[1].setGradePoint(marksOfDBMSPr,"Practical");
        this.marks[1].setSubjectName(11701391);
        this.marks[2]=new Marks(117012292,6);
        this.marks[2].setGradePoint(marksOfJavaIITh,"Theory");
        this.marks[2].setGradePoint(marksOfJavaIIPr,"Practical");
        this.marks[2].setSubjectName(117012292);
        this.marks[3]=new Marks(1711291,5);
        this.marks[3].setGradePoint(marksOfMathsIITh,"Theory");
        this.marks[3].setSubjectName(1711291);
        this.marks[4]=new Marks(117012291,4);
        this.marks[4].setGradePoint(marksOfFeeTh,"Theory");
        this.marks[4].setGradePoint(marksOfFeePr,"Practical");
        this.marks[4].setSubjectName(117012291);
    }
    static int validIntInput(){
        Scanner scanner=new Scanner(System.in);
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input integer. Please enter a valid integer.\n ");
                scanner.nextLine();
            }
        }
    }
    static double validDoubleInput(){
        Scanner scanner=new Scanner(System.in);
        while (true) {
            try {
                double input=scanner.nextDouble();
                if(validMarks(input)){
                    return input;
                }else {
                    System.out.println("Please Enter the marks between 0 to 100");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid.\n ");
                scanner.nextLine();
            }
        }
    }
    static long validLongInput(){
        Scanner scanner=new Scanner(System.in);
        while (true) {
            try {
                long input=scanner.nextLong();
                if(validEnrollmentNo(input)){
                    return input;
                }else {
                    System.out.println("Enter enrollmentNo in 14 digit");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid.\n ");
                scanner.next();
            }
        }
    }
    static boolean validEnrollmentNo(long enrollmentNo){
        int count=0;
        while (enrollmentNo>0){
            count++;
            enrollmentNo /=10;
        }
        return count == 14;
    }
    static boolean validMarks(double marks){
        return marks >= 0 && marks <= 100;
    }
}



