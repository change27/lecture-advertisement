package lecture;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="InquiryAdversise_table")
public class InquiryAdversise {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long courseId;
        private Long adId;
        private String name;
        private String teacher;
        private String status;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getCourseId() {
            return courseId;
        }

        public void setCouseId(Long courseId) {
            this.courseId = courseId;
        }
        public Long getAdId() {
            return adId;
        }

        public void setAdId(Long adId) {
            this.adId = adId;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

}
