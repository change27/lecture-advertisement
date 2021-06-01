package lecture;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import lecture.external.Payment;
import lecture.external.PaymentService;

import java.util.List;
import java.util.Date;

@Entity
@Table(name="Advertisement_table")
public class Advertisement {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long courseId;
    private String status;

    @PostPersist
    public void onPostPersist() throws Exception{
        Payment payment = new Payment();
        // mappings goes here
        payment.setAdId(this.getId());
        payment.setCourseId(this.getCourseId());;
        payment.setStatus("PAYMENT_COMPLETED");
        
        System.out.println("*****************onPostPersist");
        if (AdvertisementApplication.applicationContext.getBean(PaymentService.class).pay(payment)) {
            AdRegistered adRegistered = new AdRegistered();
            BeanUtils.copyProperties(this, adRegistered);
            adRegistered.publishAfterCommit();
        }else {
            throw new RollbackException("Failed during payment");
        }


        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

 //       lecture.external.Payment payment = new lecture.external.Payment();
 //       // mappings goes here
 //       Application.applicationContext.getBean(lecture.external.PaymentService.class)
 //           .pay(payment);


    }

    @PreRemove
    public void onPreRemove(){
        AdCanceled adCanceled = new AdCanceled();
        BeanUtils.copyProperties(this, adCanceled);
        adCanceled.publishAfterCommit();

        System.out.println("*****************onPreRemove");
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
