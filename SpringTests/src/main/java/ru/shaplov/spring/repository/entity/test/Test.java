package ru.shaplov.spring.repository.entity.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "test")
public class Test {
    public Test(String name, String description, String info) {
        this.name = name;
        this.description = description;
        this.info = info;
    }

    @Id
    @GeneratedValue(generator = "test_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "test_seq", sequenceName = "test_id_seq", allocationSize = 50)
//    @GenericGenerator(
//            name = "test_seq",
//            strategy = "sequence",
//            parameters = {
//                    @Parameter(name = "sequence_name", value = "test_id_seq"),
//                    @Parameter(name = "initial_value", value = "1"),
//                    @Parameter(name = "increment_size", value = "50"),
//                    @Parameter(name = "optimizer", value = "pooled-lo")
//            }
//    )
    private Long id;

    private String name;
    private String description;
    private String info;

    @Transient
    private List<TestAttr> attrs;
}
