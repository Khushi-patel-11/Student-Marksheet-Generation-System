import java.io.*;
import java.sql.*;
//import java.util.Queue;
interface DatabaseConnection {
    String URL = "jdbc:mysql://localhost:3306/new";
    String USER = "root";
    String PASSWORD = "vraj";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    static void insert(Student student)  {
        try {
            Connection connection = getConnection();
            try {
                connection.setAutoCommit(false);
                FileReader fileReader = new FileReader(student.enrollmentNo + ".txt");
                String query ;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select spi from "+student.semester+" where result ='fail' " + " and EnrollmentNo=" + student.enrollmentNo);
                if (resultSet.next()) {
                    DatabaseConnection.updateStudent(student);
                    return;
                }
                System.out.println("Insert on simple");
                query = "insert into " + student.semester + " values (" + student.enrollmentNo + ", '" + student.name + "'," + student.spi + "," + student.totalCredit + " ," + student.gradePointEarned + ", '" + student.result + "',?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setClob(1, fileReader);
                int n = preparedStatement.executeUpdate();
                System.out.println((n > 0) ? "Insertion Successfully " : "Insertion not successfully");
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    static void updateStudent(Student student){
        try {
            Connection connection=getConnection();
            FileReader fileReader=new FileReader(student.enrollmentNo+".txt");
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
            System.out.println((n > 0) ? "Update Successfully " : "Update not successfully");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    static String fetch(long enrollmentNo){
        try {
            Connection connection=getConnection();
            try {
                String s="";
                String query="select Spi,TotalCredit,GradePointEarned,Result from Semester_I where EnrollmentNo="+enrollmentNo;
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery(query);
                while (resultSet.next()){
                    System.out.println("In result set");
                    double spi=resultSet.getDouble(1);
                    double totalCredit=resultSet.getDouble(2);
                    double gradePointEarned=resultSet.getDouble(3);
                    String result=resultSet.getString(4);
                    s=spi+" "+totalCredit+" "+gradePointEarned+" "+result;
                }
                System.out.println("s is "+s);
                return s;
            }catch (Exception e){
                System.out.println(e);
            }
        }catch (Exception e){
            System.out.println(e);
        }return "";
    }
    static boolean search(long enrollmentNo,int sem){
        try {
            Connection connection=getConnection();
            try {
                String s="Semester_";
                if(sem==1){
                    s=s+"I";
                }else {
                    s=s+"II";
                }
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("select EnrollmentNo from "+ s +" where EnrollmentNo="+enrollmentNo);
                return resultSet.next();

            }catch (Exception e){
                System.out.println(e);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return true;
    }
    static void deleteStudent(){
        System.out.println("Enter enrollmentNo for delete");
        long enrollmentNo=Student.validLongInput();
        System.out.println("Enter semester");
        int sem=Student.validIntInput();
        String string;
        while (true) {
            if (sem == 1) {
                string = "semester_i";
                break;
            } else if(sem == 2) {
                string = "semester_ii";
                break;
            }else {
                System.out.println("Enter valid semester (1 or 2)");
            }
        }
        try {

        Statement statement= getConnection().createStatement();
        int n=statement.executeUpdate("Delete from "+string+" where EnrollmentNo= "+enrollmentNo);
            System.out.println((n>0) ? "Delete " : "Not delete");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    static String getName(long enrollmentNo){
        try {
            Connection connection=DatabaseConnection.getConnection();
            String query="{call getName(?,?)}";
            CallableStatement callableStatement=connection.prepareCall(query);
            callableStatement.setLong(1, enrollmentNo);
            callableStatement.executeQuery();
            return callableStatement.getString(2);
        }catch (Exception e){
            System.out.println(e);
        }
        return "";
    }
    static String getResult(long enrollmentNo){
        try {
            Connection connection=DatabaseConnection.getConnection();
            String query="{call getResult(?)}";
            CallableStatement callableStatement=connection.prepareCall(query);
            callableStatement.setLong(1, enrollmentNo);
            ResultSet resultSet=callableStatement.executeQuery();
            String result="";
            while (resultSet.next()) {
                result = resultSet.getString(1);
            }
            return result;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    static void getEnrollNameSpi(Queue queue,String semester){
        try {
            Connection connection=getConnection();
            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select enrollmentNo, name, spi from "+semester+" where result='Pass' order by spi desc limit 10 ");
            while (resultSet.next()){
               Student_Information studentInformation=new Student_Information(resultSet.getLong(1),resultSet.getString(2),resultSet.getDouble(3));
               queue.enqueue(studentInformation);
            }

        }  catch (Exception e) {
            System.out.println(e);
        }
    }
}

