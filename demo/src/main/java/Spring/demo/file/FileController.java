package Spring.demo.file;

import Spring.demo.exception.FolderNotFoundException;
import Spring.demo.folder.Folder;
import Spring.demo.folder.FolderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final FolderService folderService;

    @PostMapping("/create-file")
    public ResponseEntity<?> createFile(@RequestBody CreateFileDTO createFile){
        try{
            Folder folder = folderService.getFolder(createFile.getFolderId());
            File file = fileService.createFile(createFile.getTitle(), createFile.getContent(), folder);
            return new ResponseEntity<>(file, HttpStatus.CREATED);
        }catch (FolderNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class CreateFileDTO{
        private String title;
        private String content;
        private UUID folderId;
    }


    @GetMapping("View-file/{id}")
    public ResponseEntity<?> viewFileId(@PathVariable("id") UUID id){
        try{
            File file = fileService.viewFileId(id);
            return ResponseEntity.ok(file);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("an error occured " + e.getMessage());
        }
    }

    @DeleteMapping("/delete-file")
    public ResponseEntity<?> deleteFile(@RequestParam UUID id){
        try {
            fileService.deleteFile(UUID.fromString(String.valueOf(id)));
            return ResponseEntity.ok("File deleted: " + id);
        }catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occured");
        }
    }
}
