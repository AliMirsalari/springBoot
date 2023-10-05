package ir.maktab.springboot.model.student;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonStudent implements Student {
    private String name;
    private String phoneNumber;
}
