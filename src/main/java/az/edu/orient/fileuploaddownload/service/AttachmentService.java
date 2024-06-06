package az.edu.orient.fileuploaddownload.service;

import az.edu.orient.fileuploaddownload.dto.AttachmentResponse;
import az.edu.orient.fileuploaddownload.entity.Attachment;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    AttachmentResponse saveAttachment(MultipartFile file, HttpServletRequest request);

    Attachment getAttachment(Long fileId);
}
