package com.company;

public class Cat {
    private double age;
    private double mass;
    private double xSound;
    private double sleep;
    private double brand;

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getxSound() {
        return xSound;
    }

    public void setxSound(double xSound) {
        this.xSound = xSound;
    }

    public double getSleep() {
        return sleep;
    }

    public void setSleep(double sleep) {
        this.sleep = sleep;
    }

    public double getBrand() {
        return brand;
    }

    public void setBrand(double brand) {
        this.brand = brand;
    }

    public void setAll(double age, double mass, double xSound, double sleep, double brand) {
        setAge(age);
        setBrand(brand);
        setxSound(xSound);
        setMass(mass);
        setSleep(sleep);
    }
}
