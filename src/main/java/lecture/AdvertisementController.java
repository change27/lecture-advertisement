package lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


 @RestController
 public class AdvertisementController {
    @Autowired
    AdvertisementRepository advertisementRepository;

    // @PostMapping(value = "/advertisement")
    // public Advertisement registerAdvertisement(@RequestBody Map<String, String> param) {

    //     Advertisement advertisement = new Advertisement();
    //     advertisement.setCourseId(Long.parseLong(param.get("courseId")));
    //     advertisement.setStatus(param.get("status"));
        
    //     advertisement = advertisementRepository.save(advertisement);
    //     System.out.println("*****************registerAdvertisement");
    //     return advertisement;
    // }

  /*   @PatchMapping(value = "/advertisement/{id}")
    public Advertisement modifyAdvertisement(@RequestBody Map<String, String> param, @PathVariable String id) {

        Optional<Advertisement> opt = advertisementRepository.findById(Long.parseLong(id));
        Advertisement advertisement = null;

        if (opt.isPresent()) {
            advertisement = opt.get();

            if (param.get("name") != null)
            advertisement.setName(param.get("name"));
            if (param.get("teacher") != null)
            advertisement.setTeacher(param.get("teacher"));
            if (param.get("fee") != null)
            advertisement.setFee(Long.parseLong(param.get("fee")));
            if (param.get("textBook") != null)
            advertisement.setTextBook(param.get("textBook"));

            advertisement = advertisementRepository.save(advertisement);
        }

        return advertisement;
    }

    @PutMapping(value = "/advertisement/{id}")
    public Advertisement modifyAdvertisementPut(@RequestBody Map<String, String> param, @PathVariable String id) {
        return this.modifyAdvertisement(param, id);
    }

    @GetMapping(value = "/advertisement/{id}")
    public Advertisement inquiryAdvertisementById(@PathVariable String id) {

        Optional<Advertisement> opt = advertisementRepository.findById(Long.parseLong(id));
        Advertisement advertisement = null;

        if (opt.isPresent())
        advertisement = opt.get();

        return advertisement;
    }
*/
    @GetMapping(value = "/advertisement")
    public Iterable<Advertisement> inquiryAdvertisement() {

        Iterable<Advertisement> iter = advertisementRepository.findAll();
        System.out.println("*****************inquiryAdvertisement");
        return iter;
    }

    @DeleteMapping(value = "/advertisements/{id}")
    public boolean deleteAdvertisement(@PathVariable String id) {
        boolean result = false;

        advertisementRepository.deleteById(Long.parseLong(id));
        result = true;
        System.out.println("*****************deleteAdvertisement");
        return result;
    }
 
 }
