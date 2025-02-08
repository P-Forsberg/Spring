package Spring.demo.folder;

import Spring.demo.exception.FolderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;

    public Folder createFolder(String name) {
        if(name == null){
            throw new IllegalArgumentException("This may no be null");
        }
        if(name.isEmpty()){
            throw new IllegalArgumentException("This name may not be empty");
        }
        if(name.isBlank()){
            throw new IllegalArgumentException("This may not be blank!");
        }
        if(name.length() < 3){
            throw new IllegalArgumentException("Folder name must be more than three characters.");
        }

        Folder folder = new Folder(name);
        return folderRepository.save(folder);
    }

    public Folder getFolder(UUID id) throws FolderNotFoundException {
        Folder folder = folderRepository.findById(id)
                .orElseThrow(()-> new FolderNotFoundException("Folder not found" + id));
        return folder;
    }
}
