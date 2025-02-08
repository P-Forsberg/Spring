package Spring.demo.folder;

import Spring.demo.exception.FolderNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("folder")
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;

    @PostMapping("/create")
    public ResponseEntity<?> createFolder(@RequestBody CreateFolderDTO dto){
        try {
            Folder folder = folderService.createFolder(dto.name);
            return new ResponseEntity<>(folder, HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFolder(@PathVariable UUID id) {
        try {
            Folder folder = folderService.getFolder(id);
            return new ResponseEntity<>(folder, HttpStatus.OK);
        }catch (FolderNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class CreateFolderDTO{
        private String name;
    }
}
