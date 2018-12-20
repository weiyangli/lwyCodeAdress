package ssm.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Person {
    private Long Id;
    @Excel(name = "姓名",orderNum = "0" )
    private String name;
    @Excel(name = "性别", orderNum = "1")
    private int sex;
    @Excel(name = "年龄", orderNum = "2")
    private int age;

    public Person(Long id, String name, int sex, int age) {
        Id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public Person() {

    }
    public int compare(Person o1, Person o2) {
        return o1.getAge() > o2.getAge() ? 1 : o1.getAge() == o2.getAge() ? 0 : -1;
    }
}
