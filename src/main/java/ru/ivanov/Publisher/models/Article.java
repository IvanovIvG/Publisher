package ru.ivanov.Publisher.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import ru.ivanov.Publisher.models.stagesEnums.FourStateStage;
import ru.ivanov.Publisher.models.stagesEnums.ThreeStateStage;
import ru.ivanov.Publisher.models.stagesEnums.TwoStateStage;
import ru.ivanov.Publisher.models.validationGroups.OnCreate;
import ru.ivanov.Publisher.models.validationGroups.OnUpdate;

/**
 * @author Ivan Ivanov
 **/
@Entity
@Table(name = "article")
@Data
@NoArgsConstructor
public class Article {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="journal", referencedColumnName = "id")
    @NotNull
    @Valid
    private Journal journal;

    @Column(name = "name")
    @Size(min = 1, max = 100, message = "Name must be from 1 to 100 length")
    @NotBlank(message = "Name must not be empty", groups = OnCreate.class)
    private String name;

    @Column(name = "topic")
    @Size(min = 1, max = 100, message = "Topic must be from 1 to 100 length")
    @NotBlank(message = "Topic must not be empty", groups = OnCreate.class)
    private String topic;

    @Column(name = "pages")
    @Min(value = 0, message = "Number of pages must be positive")
    private int pages;

    @Column(name = "first_page")
    @Min(value = 0, message = "Number of first page must be positive")
    private int firstPage;

    @Column(name = "last_page")
    @Min(value = 0, message = "Number of last page must be positive")
    private int lastPage;

    @Column(name = "chief_editor_stage")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private FourStateStage chiefEditor;

    @Column(name = "science_editor")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private ThreeStateStage scienceEditor;

    @Column(name = "author_coordination")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private ThreeStateStage authorCoordination;

    @Column(name = "corrector")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private ThreeStateStage corrector;

    @Column(name = "page_proofs")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private TwoStateStage pageProofs;

    @Column(name = "dummy")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private TwoStateStage dummy;
}

