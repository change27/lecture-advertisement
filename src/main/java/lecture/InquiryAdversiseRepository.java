package lecture;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InquiryAdversiseRepository extends CrudRepository<InquiryAdversise, Long> {

    List<InquiryAdversise> findByCourseId(Long courseId);
    List<InquiryAdversise> findByAdId(Long adId);

}