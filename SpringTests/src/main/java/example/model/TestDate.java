package example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author dshaplov
 */
@Data
@NoArgsConstructor
public class TestDate {
    LocalDate date = LocalDate.now();

    String name = "SDFSDF";
}
