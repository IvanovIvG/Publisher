package ru.ivanov.Publisher.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import ru.ivanov.Publisher.stagesEnums.FourStateStage;
import ru.ivanov.Publisher.stagesEnums.ThreeStateStage;
import ru.ivanov.Publisher.stagesEnums.TwoStateStage;

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
    private Journal journal;

    @Column(name = "name")
    private String name;

    @Column(name = "topic")
    private String topic;

    @Column(name = "pages")
    private int pages;

    @Column(name = "first_page")
    private int firstPage;

    @Column(name = "last_page")
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

