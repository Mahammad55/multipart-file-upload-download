package az.edu.orient.fileuploaddownload.service.impl;

import az.edu.orient.fileuploaddownload.dto.AttachmentResponse;
import az.edu.orient.fileuploaddownload.entity.Attachment;
import az.edu.orient.fileuploaddownload.repository.AttachmentRepository;
import az.edu.orient.fileuploaddownload.service.AttachmentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;

    @Override
    public AttachmentResponse saveAttachment(MultipartFile file, HttpServletRequest request) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (fileName.contains(".."))
                throw new RuntimeException("File name contains invalid path request " + fileName);

            Attachment attachment = Attachment.builder().fileName(fileName).fileType(file.getContentType()).data(file.getBytes()).build();
            Attachment savedAttachment = attachmentRepository.save(attachment);
            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path(request.getServletPath().substring(0, request.getServletPath().lastIndexOf('/'))).path("/download/")
                    .path(savedAttachment.getId().toString()).toUriString();
            return new AttachmentResponse(savedAttachment.getFileName(), downloadUrl, file.getContentType(), file.getSize());

        } catch (Exception exception) {
            throw new RuntimeException("Couldn't save the file " + fileName);
        }
    }

    @Override
    public Attachment getAttachment(Long fileId) {
        return attachmentRepository.findById(fileId).orElseThrow(() -> new RuntimeException("File not found with id " + fileId));
    }
}
