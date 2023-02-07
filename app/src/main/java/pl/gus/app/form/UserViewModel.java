package pl.gus.app.form;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.Objects;

import pl.gus.app.BR;

public class UserViewModel extends BaseObservable {
    private String firstName;
    private String lastName;

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

    @Override
    public String toString() {
        return "UserViewModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
