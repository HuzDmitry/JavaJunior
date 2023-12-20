package DZHibernate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses", schema="schooldb")
public class Course {
    @Id
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="duration")
    private Integer duration;

}
