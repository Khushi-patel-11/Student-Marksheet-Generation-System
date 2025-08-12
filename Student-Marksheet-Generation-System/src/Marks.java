public class Marks {
    int subjectCode;
    String subjectName;
    int subjectCredit;
    double gradePointOfTheory;
    double gradePointOfPractical;
    double overAllGradePoint;
    String overAllGrade="";
    String gradeOfTheory="";
    String gradeOfPractical="";

    public Marks(int subjectCode,int subjectCredit){
        this.subjectCode=subjectCode;
        this.subjectCredit=subjectCredit;
    }
    public void setSubjectName(int subjectCode) {
        if(subjectCode==117011191){
            this.subjectName="Mathematics - I";
        }
        else if(subjectCode==117012191){
            this.subjectName="Computer Programing using Java-I";
        }
        else if(subjectCode==17013191){
            this.subjectName="Software Engineering";
        }
        else if(subjectCode==117011192){
            this.subjectName="Physics";
        }
        else if(subjectCode==17012192){
            this.subjectName="IOT Workshop - Laboratory";
        }
        else if(subjectCode==17012193){
            this.subjectName="Computer Workshop - Laboratory";
        }else if(subjectCode==17018191){
            this.subjectName="Environmental Science";
        }else if(subjectCode==11701392){
            this.subjectName="Data Structure using Java";
        }else if(subjectCode==11701391){
            this.subjectName="Database Management System";
        }else if(subjectCode==117012292){
            this.subjectName="Computer Programing using Java-II";
        }else if(subjectCode==1711291){
            this.subjectName="Mathematics-II";
        }else if(subjectCode==117012291){
            this.subjectName="Fundamental of Electronics and Electrical Engineering";
        }
    }
    public void setGradePoint(double marks,String isThOrPr){
        String grade;
        double gradePoint;
        if(marks>=95){
            gradePoint=10;
            grade="O+++";
        }
        else if(marks >=90){
            gradePoint=9.5;
            grade="O++";
        }
        else if(marks>=85){
            gradePoint=9;
            grade="O+";
        } else if (marks>=80) {
            gradePoint=8.5;
            grade="O";
        }
        else if(marks>=75){
            gradePoint=8.0;
            grade="A++";
        }
        else if(marks>=70){
            gradePoint=7.5;
            grade="A+";
        }
        else if(marks>=65){
            gradePoint=7.0;
            grade="A";
        }else if(marks>=60){
            gradePoint=6.5;
            grade="B++";
        }else if(marks>=55){
            gradePoint=6.0;
            grade="B+";
        }else if(marks>=50){
            gradePoint=5.5;
            grade="B";
        }else if(marks>=45){
            gradePoint=5.0 ;
            grade="C";
        }else if(marks>=40){
            gradePoint=4.5 ;
            grade="D";
        }else if(marks>=35){
            gradePoint=4.0 ;
            grade="D";
        }else {
            gradePoint=0;
            grade="FAIL";
        }
        if(isThOrPr.equalsIgnoreCase("Theory")){
            this.gradePointOfTheory=gradePoint;
            this.gradeOfTheory=grade;
        }else if(isThOrPr.equalsIgnoreCase("Practical")) {
            this.gradePointOfPractical=gradePoint;
            this.gradeOfPractical=grade;
        }
    }
    void setGrade(double gradePoint){

        if(gradePoint>=10){
            overAllGrade="O+++";
        }else if(gradePoint>=9.5){
            overAllGrade="O++";
        }else if(gradePoint>=9.0){
            overAllGrade="O+";
        }else if(gradePoint>=8.5){
            overAllGrade="O";
        }else if(gradePoint>=8){
            overAllGrade="A++";
        }else if(gradePoint>=7.5){
            overAllGrade="A+";
        }else if(gradePoint>=7){
            overAllGrade="A";
        }else if(gradePoint>=6.5){
            overAllGrade="B++";
        }else if(gradePoint>=6){
            overAllGrade="B+";
        }else if(gradePoint>=5.5){
            overAllGrade="B";
        }else if(gradePoint>=5){
            overAllGrade="C";
        }else if(gradePoint>=4.5){
            overAllGrade="D";
        }else if(gradePoint>=4){
            overAllGrade="E";
        }else {
            overAllGrade="FAIL";
        }
    }
}