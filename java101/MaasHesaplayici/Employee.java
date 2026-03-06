public class Employee {

    String name;
    int salary;
    int workHours;
    int hireYear;

    Employee(String name, int salary, int workHours, int hireYear) {
        this.name = name;
        this.salary = salary;
        this.workHours = workHours;
        this.hireYear = hireYear;
    }

    double tax() {
        if (this.salary < 1000) {
            return 0;
        } else {
            return this.salary * 0.03;
        }
    }

    double bonus() {
        if (this.workHours > 40) {
            return (this.workHours - 40) * 30;
        } else {
            return 0;
        }
    }

    double raiseSalary() {
        int yearsOfService = 2021 - this.hireYear;
        if (yearsOfService < 10) {
            return this.salary * 0.05;
        } else if (yearsOfService >= 10 && yearsOfService < 20) {
            return this.salary * 0.1;
        } else {
            return this.salary * 0.15;
        }
    }

    void toString(Employee employee) {
        System.out.println("Adı: " + employee.name);
        System.out.println("Maaşı: " + employee.salary);
        System.out.println("Çalışma Saati: " + employee.workHours);
        System.out.println("Başlangıç Yılı: " + employee.hireYear);
        System.out.println("Vergi: " + tax());
        System.out.println("Bonus: " + bonus());
        System.out.println("Maaş Artışı: " + raiseSalary());
        System.out.println("Vergi ve Bonuslar ile birlikte maaş: " + (employee.salary - tax() + bonus()));
        System.out.println("Toplam Maaş: " + (employee.salary - tax() + bonus() + raiseSalary()));
    }
}
