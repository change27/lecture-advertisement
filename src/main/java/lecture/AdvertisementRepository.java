package lecture;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="advertisements", path="advertisements")
public interface AdvertisementRepository extends PagingAndSortingRepository<Advertisement, Long>{


}
