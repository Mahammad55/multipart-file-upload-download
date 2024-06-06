package az.edu.orient.fileuploaddownload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentResponse {
    private String fileName;

    private String downloadUrl;

    private String fileType;

    private Long fileSize;
}
