import javax.persistence.*;
import java.util.List;

@Entity(name = "LINKER")
public class Linker{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int linkId;



}

