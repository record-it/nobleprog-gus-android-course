package pl.gus.app.form;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import java.util.Objects;

import pl.gus.app.BR;
import pl.gus.app.sqlite.User;

public class UserViewModel extends BaseObservable {
    private String firstName;
    private String lastName;

    private String phone;

    private String imageUri;

    private boolean active;

    private String birth;

    public UserViewModel() {
    }

    public UserViewModel(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(!Objects.equals(this.firstName, firstName)){
            this.firstName = firstName;
            notifyPropertyChanged(BR.firstName);
        }
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(!Objects.equals(this.lastName, lastName)){
            this.lastName = lastName;
            notifyPropertyChanged(BR.lastName);
        }
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (!Objects.equals(this.phone, phone)) {
            this.phone = phone;
            notifyPropertyChanged(BR.phone);
        }
    }

    @Bindable
    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        if (!Objects.equals(imageUri, this.imageUri)) {
            this.imageUri = imageUri;
            notifyPropertyChanged(BR.imageUri);
        }
    }

    @Bindable
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        if (this.active != active) {
            this.active = active;
            notifyPropertyChanged(BR.active);
        }
    }

    @Bindable
    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        if (!Objects.equals(this.birth, birth)) {
            this.birth = birth;
            notifyPropertyChanged(BR.birth);
        }
    }

    @Override
    public String toString() {
        return "UserViewModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", imageUri=" + imageUri +
                ", active=" + active +
                ", birth=" + birth +
                '}';
    }

    public User toEntity(){
        User entity = new User();
        entity.setBirth(this.birth);
        entity.setFirstName(this.firstName);
        entity.setPhone(this.phone);
        entity.setImageUri(this.imageUri);
        entity.setLastName(this.lastName);
        return entity;
    }
}
