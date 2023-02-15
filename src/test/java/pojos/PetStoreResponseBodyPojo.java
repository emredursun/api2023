package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PetStoreResponseBodyPojo {

    private PetStoreCategoryPojo petStoreCategoryPojo;
    private String name;
    //private List<String> photoUrls;
    //private List<PetStoreTagPojo> petStoreTagPojos;
    private String status;

    public PetStoreResponseBodyPojo() {
    }

    public PetStoreResponseBodyPojo(PetStoreCategoryPojo petStoreCategoryPojo, String name, String status) {
        this.petStoreCategoryPojo = petStoreCategoryPojo;
        this.name = name;
        //this.photoUrls = photoUrls;
        //this.petStoreTagPojos = petStoreTagPojos;
        this.status = status;
    }

    public PetStoreCategoryPojo getCategory() {
        return petStoreCategoryPojo;
    }

    public void setCategory(PetStoreCategoryPojo petStoreCategoryPojo) {
        this.petStoreCategoryPojo = petStoreCategoryPojo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<String> getPhotoUrls() {
//        return photoUrls;
//    }
//
//    public void setPhotoUrls(List<String> photoUrls) {
//        this.photoUrls = photoUrls;
//    }

//    public List<PetStoreTagPojo> getTags() {
//        return petStoreTagPojos;
//    }
//
//    public void setTags(List<PetStoreTagPojo> petStoreTagPojos) {
//        this.petStoreTagPojos = petStoreTagPojos;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PetStoreResponseBodyPojo{" +
                "category=" + petStoreCategoryPojo +
                ", name='" + name + '\'' +
//               ", photoUrls=" + photoUrls +
//                ", tags=" + petStoreTagPojos +
                ", status='" + status + '\'' +
                '}';
    }
}
