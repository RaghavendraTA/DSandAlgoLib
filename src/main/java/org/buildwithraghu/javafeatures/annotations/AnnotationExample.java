package org.buildwithraghu.javafeatures.annotations;

@JsonSerializable
public class AnnotationExample {

    @JsonElement
    private String firstName;

    @JsonElement
    private String lastName;

    @JsonElement(key = "personAge")
    private String age;

    private String address;

    @Init
    private void initNames() {
        this.firstName = this.firstName.substring(0, 1).toUpperCase()
                + this.firstName.substring(1);
        this.lastName = this.lastName.substring(0, 1).toUpperCase()
                + this.lastName.substring(1);
    }

    AnnotationExample(String fname, String lname, String age) {
        this.firstName = fname;
        this.lastName = lname;
        this.age = age;
    }
}
