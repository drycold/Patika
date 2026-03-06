public class Main {
    
    public static void main(String[] args) {
        
        Course mat = new Course("Matematik", "MAT101", "MAT");
        Course fizik = new Course("Fizik", "FZK101", "FZK");
        Course kimya = new Course("Kimya", "KMY101", "KMY");

        Teacher t1 = new Teacher("Ali Can", "MAT", 123456789);
        Teacher t2 = new Teacher("Veli Can", "FZK", 987654321);
        Teacher t3 = new Teacher("Ayşe Can", "KMY", 456789123);

        mat.addTeacher(t1);
        fizik.addTeacher(t2);
        kimya.addTeacher(t3);

        Student s1 = new Student("Ahmet", "12345", "4", mat, fizik, kimya);
        s1.addBulkExamNote(50, 60, 70);
        s1.addBulkVerbalNote(80, 90, 100);
        s1.isPass();

        Student s2 = new Student("Mehmet", "67890", "4", mat, fizik, kimya);
        s2.addBulkExamNote(30, 40, 50);
        s2.addBulkVerbalNote(60, 70, 80);
        s2.isPass();

        Student s3 = new Student("Ayşe", "54321", "4", mat, fizik, kimya);
        s3.addBulkExamNote(80, 90, 100);
        s3.addBulkVerbalNote(90, 95, 100);
        s3.isPass();

    }
}
