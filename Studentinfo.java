package capstone.core;

public class Studentinfo {
    private int Regnumber;
    private String name;
    private double cgpa;  // ✅ changed to double

    // Constructor
    public Studentinfo(int Regnumber, String name, double cgpa) {
        this.Regnumber = Regnumber;
        this.name = name;
        this.cgpa = cgpa;
    }

    // Encapsulation (Getters & Setters)
    public int getRegnumber() { 
        return Regnumber; 
    }
    public void setRegnumber(int Regnumber) { 
        this.Regnumber = Regnumber; 
    }

    public String getName() { 
        return name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }

    public double getCgpa() { 
        return cgpa; 
    }
    public void setCgpa(double cgpa) { 
        if (cgpa < 0) {   // ✅ prevent negative CGPA
            this.cgpa = 0.0;
        } else {
            this.cgpa = cgpa;
        }
    }

    @Override
    public String toString() {
        return Regnumber + " : " + name + " (CGPA: " + cgpa + ")";
    }
}
