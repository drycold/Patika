public class Student {
    
    String name;
    String stuNo;
    String classes;
    Course course1;
    Course course2;
    Course course3;
    double average;
    boolean isPass;


    Student(String name,String stuNo,String classes,Course course1,Course course2,Course course3){
        this.name=name;
        this.stuNo=stuNo;
        this.classes=classes;
        this.course1=course1;
        this.course2=course2;
        this.course3=course3;
        this.average=0.0;
        this.isPass=false;

    }

    void  isPass(){
        this.average= (this.course1.note*0.8 + this.course1.verbalNote*0.2 + this.course2.note*0.8 + this.course2.verbalNote*0.2 + this.course3.note*0.8 + this.course3.verbalNote*0.2) / 3.0;

        if(this.average>55){
            System.out.println("\nSınıfı geçtiniz");
            this.isPass=true;
        }
        else{
            System.out.println("\nKALDINIZ");
            this.isPass=false;
        }
        printNote();


    }

    void addBulkExamNote(int note1,int note2,int note3){
        if(note1>=0 &&  note1<=100){
            course1.note=note1;

        }
        if(note2>=0 && note2<=100){
            course2.note=note2;

        }
        if(note3>=0 && note3<=100){
            course3.note=note3;

        }
    }

    void addBulkVerbalNote(int verbalNote1, int verbalNote2, int verbalNote3) {
        if (verbalNote1 >= 0 && verbalNote1 <= 100) {
            course1.verbalNote = verbalNote1;
        }
        if (verbalNote2 >= 0 && verbalNote2 <= 100) {
            course2.verbalNote = verbalNote2;
        }
        if (verbalNote3 >= 0 && verbalNote3 <= 100) {
            course3.verbalNote = verbalNote3;
        }
    }

    void printNote(){
        System.out.println(this.course1.name + " Sınav Notu : " + this.course1.note + " , Sözlü Notu : " + this.course1.verbalNote);
        System.out.println(this.course2.name + " Sınav Notu : " + this.course2.note + " , Sözlü Notu : " + this.course2.verbalNote);
        System.out.println(this.course3.name + " Sınav Notu : " + this.course3.note + " , Sözlü Notu : " + this.course3.verbalNote);
        System.out.println("Ortalamanız : " + this.average);
    }

}