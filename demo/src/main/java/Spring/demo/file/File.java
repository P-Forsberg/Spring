package Spring.demo.file;

import Spring.demo.folder.Folder;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @Id
    private UUID id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    @JsonBackReference
    private Folder folder;




    public File(String title, String content, Folder folder) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.folder = folder;
    }

}
