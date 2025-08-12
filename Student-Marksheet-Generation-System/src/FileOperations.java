import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class FileOperations {
    static void markSheetGeneration(Student student) {
        try {
//            BufferedWriter bufferedWriter;
//            if(student.result.equalsIgnoreCase("Fail") ){
//                Connection connection=DatabaseConnection.getConnection();
//                Statement statement=connection.createStatement();
//                ResultSet resultSet=statement.executeQuery("select Marksheet from remedial_"+student.semester+" where EnrollmentNo ="+student.enrollmentNo);
//                if (resultSet.next()){
//                    bufferedWriter = new BufferedWriter(new FileWriter(student.enrollmentNo + ".txt",true));
//                }else {
//                    bufferedWriter = new BufferedWriter(new FileWriter(student.enrollmentNo + ".txt"));
//                }
//            }else {
//                bufferedWriter = new BufferedWriter(new FileWriter(student.enrollmentNo + ".txt"));
//            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(student.enrollmentNo + ".txt"));
            bufferedWriter.write("                                                 Lok Jagruti Kendra University\n");
            bufferedWriter.write("                                                    Semester Performance Report \n");
            bufferedWriter.write("                                            L J Institute of Engineering & Technology\n\n");
            bufferedWriter.write("Specialization : Computer Engineering");
            bufferedWriter.write("                  Semester : " + student.semester+"\n");
            bufferedWriter.write("Enrollment No : " + student.enrollmentNo + "\n");
            if(student.semester.equalsIgnoreCase("Semester_II") ||(student.semester.equalsIgnoreCase("semester_i") && student.result.equalsIgnoreCase("Fail"))){
                student.name=DatabaseConnection.getName(student.enrollmentNo);
                bufferedWriter.write("Name : " + student.name + "\n");
            }else {
                bufferedWriter.write("Name : " + student.name + "\n");
            }bufferedWriter.write("_______________________________________________________________________________________________________________________________\n");
            bufferedWriter.write("Code       |                    Course Title                        | Credit | Theory Grade | Practical Grade | Overall Grade |\n");
            bufferedWriter.write("-----------|--------------------------------------------------------|--------|--------------|-----------------|---------------|\n");
            for (int i = 0; i < student.marks.length; i++) {
                bufferedWriter.write(student.marks[i].subjectCode + "");
                int count=0;
                int code=student.marks[i].subjectCode;
                while (code>0){
                    count++;
                    code/=10;
                }
                for (int j=0;j<11-count;j++){
                    bufferedWriter.write(" ");
                }bufferedWriter.write("|");
                bufferedWriter.write(student.marks[i].subjectName + " ");
                for (int j = 0; j < 55 - student.marks[i].subjectName.length(); j++) {
                    bufferedWriter.write(" ");
                }
                bufferedWriter.write("|   " + student.marks[i].subjectCredit + "    |");
                bufferedWriter.write("    " );
                if( student.marks[i].gradeOfTheory!=null){
                    bufferedWriter.write( student.marks[i].gradeOfTheory);
                }
                for (int j = 0; j < 10 - student.marks[i].gradeOfTheory.length(); j++) {
                    bufferedWriter.write(" ");
                }
                bufferedWriter.write("|     " );
                if( student.marks[i].gradeOfPractical!=null){
                    bufferedWriter.write( student.marks[i].gradeOfPractical);
                }
                for (int j = 0; j < 12 - student.marks[i].gradeOfPractical.length(); j++) {
                    bufferedWriter.write(" ");
                }
                bufferedWriter.write("|     "+student.marks[i].overAllGrade);
                for (int j = 0; j < 10 - student.marks[i].overAllGrade.length(); j++) {
                    bufferedWriter.write(" ");
                }
                bufferedWriter.write("|");
                bufferedWriter.newLine();
            }
            bufferedWriter.write("_______________________________________________________________________________________________________________________________\n");
            bufferedWriter.write("_______________________________________________________________________________________________________________________________\n");
            bufferedWriter.write("                                Semester Performance Index\n");
            bufferedWriter.write("Total Credit        " +student.totalCredit+"\n");
            bufferedWriter.write("Grade Point Earned  "+student.gradePointEarned+"\n");
            bufferedWriter.write("SPI                 "+student.spi+"\n");
            bufferedWriter.write("_______________________________________________________________________________________________________________________________\n");
            bufferedWriter.write("                                Progressive Performance Index\n");
            if(student.semester.equalsIgnoreCase("Semester_I")){
                bufferedWriter.write("Total Credit        " +student.totalCredit+"\n");
                bufferedWriter.write("Grade Point Earned  "+student.gradePointEarned+"\n");
                bufferedWriter.write("SPI                 "+student.spi+"\n");
            }else {
                String fetch=DatabaseConnection.fetch(student.enrollmentNo);
                String[] split =fetch.split(" ");
                if(split[0].equalsIgnoreCase("")){

                }else {
                    double spi=Double.parseDouble(split[0]);
                    double totalCredit=Double.parseDouble(split[1]);
                    double gradePointEarned=Double.parseDouble(split[2]);
                    double spiForSemII=(student.spi+spi)/2;
                    String result =split[3];
                    if(result.equalsIgnoreCase("Pass")){
                        spiForSemII= Math.round(spiForSemII*100)/100.0;
                        if(result.equalsIgnoreCase("Pass")){
                            bufferedWriter.write("Total Credit        " +(student.totalCredit+totalCredit)+"\n");
                            bufferedWriter.write("Grade Point Earned  "+(student.gradePointEarned+gradePointEarned)+"\n");
                            bufferedWriter.write("SPI                 "+spiForSemII+"\n");
                        }
                    }
                }
            }
            bufferedWriter.write("_______________________________________________________________________________________________________________________________\n");
            bufferedWriter.write("Result : "+student.result);
            bufferedWriter.flush();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    static void fetch(){
        System.out.println("Enter enrollmentNo");
        long enrollmentNo=Student.validLongInput();
        System.out.println("Enter semester");
        int sem=Student.validIntInput();
        String semester;
        if(sem==1){
            semester="Semester_I";
        }
        else {
            semester="Semester_II";
        }
        if(DatabaseConnection.search(enrollmentNo,sem)) {
            String query = "select MarkSheet from " + semester + " where EnrollmentNo = " + enrollmentNo;
            try {
                Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                FileWriter fileWriter = new FileWriter(enrollmentNo + ".txt");
                while (resultSet.next()) {
                    Reader reader = resultSet.getCharacterStream(1);
                    int i = reader.read();
                    while (i != -1) {
                        fileWriter.write((char) i);
                        i = reader.read();
                    }

                    fileWriter.close();
                    printMarkSheet(enrollmentNo);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else {
            System.out.println("Not exists");
        }
    }
    static void printMarkSheet(long enrollmentNo){
        try {

            BufferedReader bufferedReader=new BufferedReader(new FileReader(enrollmentNo+".txt"));
            int i=bufferedReader.read();
            while (i!=-1){
                System.out.print((char) i);
                i=bufferedReader.read();
            }
            System.out.println("\n");
            bufferedReader.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}