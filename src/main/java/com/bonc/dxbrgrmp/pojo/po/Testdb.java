package com.bonc.dxbrgrmp.pojo.po;

import org.springframework.stereotype.Component;

/**
 * @Auther: lgf
 * @Date: 2019/12/23
 * @Description: com.bonc.dxbrgrmp.pojo.po
 * @version: 1.0
 */
@Component
public class Testdb {
    private int testId;
    private float testFloat;
    private double testDouble;
    private int testBigInt;
    private String name;
    private String name3;

    @Override
    public String toString() {
        return "Testdb{" +
                "testId=" + testId +
                ", testFloat=" + testFloat +
                ", testDouble=" + testDouble +
                ", testBigInt=" + testBigInt +
                ", name='" + name + '\'' +
                ", name3='" + name3 + '\'' +
                '}';
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public float getTestFloat() {
        return testFloat;
    }

    public void setTestFloat(float testFloat) {
        this.testFloat = testFloat;
    }

    public double getTestDouble() {
        return testDouble;
    }

    public void setTestDouble(double testDouble) {
        this.testDouble = testDouble;
    }

    public int getTestBigInt() {
        return testBigInt;
    }

    public void setTestBigInt(int testBigInt) {
        this.testBigInt = testBigInt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }
}
