package lecture;

import lecture.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class InquiryAdversiseViewHandler {


    @Autowired
    private InquiryAdversiseRepository inquiryAdversiseRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCourseRegistered_then_CREATE_1 (@Payload CourseRegistered courseRegistered) {
        try {
            System.out.println("*********************whenCourseRegistered_then_CREATE_1");
            if (!courseRegistered.validate()) return;

            // view 객체 생성
            InquiryAdversise inquiryAdversise = new InquiryAdversise();
            // view 객체에 이벤트의 Value 를 set 함
            inquiryAdversise.setCouseId(courseRegistered.getId());
            inquiryAdversise.setName(courseRegistered.getName());
            inquiryAdversise.setStatus("READY");
            inquiryAdversise.setTeacher(courseRegistered.getTeacher());
            // view 레파지 토리에 save
            inquiryAdversiseRepository.save(inquiryAdversise);
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenAdRegistered_then_UPDATE_1(@Payload AdRegistered adRegistered) {
        try {
            if (!adRegistered.validate()) return;
                // view 객체 조회
            List<InquiryAdversise> inquiryAdversiseList = inquiryAdversiseRepository.findByCourseId(adRegistered.getCourseId());
            for(InquiryAdversise inquiryAdversise : inquiryAdversiseList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                inquiryAdversise.setAdId(adRegistered.getId());
                inquiryAdversise.setStatus(adRegistered.getStatus());
                // view 레파지 토리에 save
                inquiryAdversiseRepository.save(inquiryAdversise);
            }
            System.out.println("*********************whenAdRegistered_then_UPDATE_1");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenAdCanceled_then_UPDATE_2(@Payload AdCanceled adCanceled) {
        try {
            if (!adCanceled.validate()) return;
                // view 객체 조회
            List<InquiryAdversise> inquiryAdversiseList = inquiryAdversiseRepository.findByCourseId(adCanceled.getCourseId());
            for(InquiryAdversise inquiryAdversise : inquiryAdversiseList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                inquiryAdversise.setStatus(adCanceled.getStatus());
                // view 레파지 토리에 save
                inquiryAdversiseRepository.save(inquiryAdversise);
            }
            List<InquiryAdversise> inquiryAdversiseList2 = inquiryAdversiseRepository.findByAdId(adCanceled.getId());
            for(InquiryAdversise inquiryAdversise : inquiryAdversiseList2){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                inquiryAdversise.setStatus(adCanceled.getStatus());
                // view 레파지 토리에 save
                inquiryAdversiseRepository.save(inquiryAdversise);
            }
            System.out.println("*********************whenAdCanceled_then_UPDATE_2");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCourseDeleted_then_DELETE_1(@Payload CourseDeleted courseDeleted) {
        try {
            if (!courseDeleted.validate()) return;
            System.out.println("*********************whenCourseDeleted_then_DELETE_1");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}