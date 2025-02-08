package Spring.demo.file;

import Spring.demo.folder.Folder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public File createFile(String title, String content, Folder folder) {
        if(title.isBlank() && title.isEmpty()){
            throw new IllegalArgumentException("Title may not be blank or empty!");
        }
        if(content.isEmpty() && content.isBlank()){
            throw new IllegalArgumentException("Content may not be blank or empty!");
        }


        File file = new File(title, content, folder);

        return fileRepository.save(file);
    }
    public List<File> gettextFiles(){
        return fileRepository.findAll();
    }

    public File viewFileId(UUID id) {
        File file = fileRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("File not found: "+ id));

        return file;
    }

    public void deleteFile(UUID id) throws FileNotFoundException {
        File file = fileRepository.findById(id)
                .orElseThrow(()-> new FileNotFoundException("File not found: " + id));

        fileRepository.deleteById(id);
    }
}