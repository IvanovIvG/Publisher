package ru.ivanov.Publisher.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    private FourStateStage chiefEditor = FourStateStage.NotReadyAndOrdered;

    @Column(name = "science_editor")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private ThreeStateStage scienceEditor = ThreeStateStage.NotReady;

    @Column(name = "author_coordination")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private ThreeStateStage authorCoordination = ThreeStateStage.NotReady;

    @Column(name = "corrector")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private ThreeStateStage corrector = ThreeStateStage.NotReady;

    @Column(name = "page_proofs")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private TwoStateStage pageProofs = TwoStateStage.NotReady;

    @Column(name = "dummy")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private TwoStateStage dummy = TwoStateStage.NotReady;

    public Article() {
    }

    public Article(Journal journal, String name, String topic) {
        this.journal = journal;
        this.name = name;
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "Name must not be empty") @Size(min = 1, max = 100, message = "Name must be from 1 to 100 length") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name must not be empty") @Size(min = 1, max = 100, message = "Name must be from 1 to 100 length") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "Topic must not be empty") @Size(min = 1, max = 100, message = "Topic must be from 1 to 100 length") String getTopic() {
        return topic;
    }

    public void setTopic(@NotEmpty(message = "Topic must not be empty") @Size(min = 1, max = 100, message = "Topic must be from 1 to 100 length") String topic) {
        this.topic = topic;
    }

    @Min(value = 0, message = "Number of pages must be positive")
    public int getPages() {
        return pages;
    }

    public void setPages(@Min(value = 0, message = "Number of pages must be positive") int pages) {
        this.pages = pages;
    }

    @Min(value = 0, message = "Number of first page must be positive")
    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(@Min(value = 0, message = "Number of first page must be positive") int firstPage) {
        this.firstPage = firstPage;
    }

    @Min(value = 0, message = "Number of last page must be positive")
    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(@Min(value = 0, message = "Number of last page must be positive") int lastPage) {
        this.lastPage = lastPage;
    }

    public FourStateStage getChiefEditor() {
        return chiefEditor;
    }

    public void setChiefEditor(FourStateStage chiefEditor) {
        this.chiefEditor = chiefEditor;
    }

    public ThreeStateStage getScienceEditor() {
        return scienceEditor;
    }

    public void setScienceEditor(ThreeStateStage scienceEditor) {
        this.scienceEditor = scienceEditor;
    }

    public ThreeStateStage getAuthorCoordination() {
        return authorCoordination;
    }

    public void setAuthorCoordination(ThreeStateStage authorCoordination) {
        this.authorCoordination = authorCoordination;
    }

    public ThreeStateStage getCorrector() {
        return corrector;
    }

    public void setCorrector(ThreeStateStage corrector) {
        this.corrector = corrector;
    }

    public TwoStateStage getPageProofs() {
        return pageProofs;
    }

    public void setPageProofs(TwoStateStage pageProofs) {
        this.pageProofs = pageProofs;
    }

    public TwoStateStage getDummy() {
        return dummy;
    }

    public void setDummy(TwoStateStage dummy) {
        this.dummy = dummy;
    }

    public @NotEmpty Journal getJournal() {
        return journal;
    }

    public void setJournal(@NotEmpty Journal journal) {
        this.journal = journal;
    }
}
