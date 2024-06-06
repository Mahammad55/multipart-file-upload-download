package az.edu.orient.fileuploaddownload.repository;

import az.edu.orient.fileuploaddownload.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Long> {
}
