package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PetResponseBodyPojo {

    private PetCategoryPojo petCategoryPojo;
    private String name;
    //private List<String> photoUrls;
    //private List<PetStoreTagPojo> petStoreTagPojos;
    private String status;

    public PetResponseBodyPojo() {
    }

    public PetResponseBodyPojo(PetCategoryPojo petCategoryPojo, String name, String status) {
        this.petCategoryPojo = petCategoryPojo;
        this.name = name;
        //this.photoUrls = photoUrls;
        //this.petStoreTagPojos = petStoreTagPojos;
        this.status = status;
    }

    public PetCategoryPojo getCategory() {
        return petCategoryPojo;
    }

    public void setCategory(PetCategoryPojo petCategoryPojo) {
        this.petCategoryPojo = petCategoryPojo;
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
                "category=" + petCategoryPojo +
                ", name='" + name + '\'' +
//               ", photoUrls=" + photoUrls +
//                ", tags=" + petStoreTagPojos +
                ", status='" + status + '\'' +
                '}';
    }
}
