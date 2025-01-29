package ru.ivanov.Publisher.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import ru.ivanov.Publisher.models.stagesEnums.FourStateStage;
import ru.ivanov.Publisher.models.stagesEnums.ThreeStateStage;
import ru.ivanov.Publisher.models.stagesEnums.TwoStateStage;

/**
 * @author Ivan Ivanov
 **/
@Entity
@Table(name = "article")
@NoArgsConstructor
@Data
public class Article {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="journal", referencedColumnName = "id")
    @NotEmpty
    private Journal journal;

    @Column(name = "name")
    @NotEmpty(message = "Name must not be empty")
    @Size(min = 1, max = 100, message = "Name must be from 1 to 100 length")
    private String name;

    @Column(name = "topic")
    @NotEmpty(message = "Topic must not be empty")
    @Size(min = 1, max = 100, message = "Topic must be from 1 to 100 length")
    private String topic;

    @Column(name = "pages")
    @Min(value = 0, message = "Number of pages must be positive")
    private int pages = 0;

    @Column(name = "first_page")
    @Min(value = 0, message = "Number of first page must be positive")
    private int firstPage = 0;

    @Column(name = "last_page")
    @Min(value = 0, message = "Number of last page must be positive")
    private int lastPage = 0;

    @Column(name = "chief_editor_stage")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private FourStateStage chiefEditor = FourStateStage.Not_Ready_And_In_Stock;

    @Column(name = "science_editor")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private ThreeStateStage scienceEditor = ThreeStateStage.Not_Ready;

    @Column(name = "author_coordination")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private ThreeStateStage authorCoordination = ThreeStateStage.Not_Ready;

    @Column(name = "corrector")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private ThreeStateStage corrector = ThreeStateStage.Not_Ready;

    @Column(name = "page_proofs")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private TwoStateStage pageProofs = TwoStateStage.Not_Ready;

    @Column(name = "dummy")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private TwoStateStage dummy = TwoStateStage.Not_Ready;
}
