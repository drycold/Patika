public class Course {
    
    Teacher courseTeacher;
    String name;
    String code;
    String prefix;
    int note;
    int verbalNote;

    Course(String name, String code, String prefix) {
        this.name = name;
        this.code = code;
        this.prefix = prefix;
        this.note = 0;
        this.verbalNote = 0;
    }

    public void addTeacher(Teacher teacher) {
        if (teacher.branch.equals(this.prefix)) {
            this.courseTeacher = teacher;
        } else {
            System.out.println("Öğretmen ve Ders Bölümü Uyuşmuyor.");
        }
    }

    public void printTeacher() {
        if (this.courseTeacher != null) {
            System.out.println(this.name + " dersinin öğretmeni : " + this.courseTeacher.name);
        } else {
            System.out.println(this.name + " dersine öğretmen atanmamıştır.");
        }
    }
}
