package Spring.demo.folder;


import Spring.demo.file.File;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Folder {

    @Id
    private UUID id;

    private String name;

    @OneToMany (mappedBy = "folder")
    @JsonManagedReference
    private List<File> mappedFiles;

    public Folder(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
