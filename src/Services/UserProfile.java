package Services;
import domain.Image;

public class UserProfile {
    private UserData userData;

    public UserProfile() {
        this.userData = new UserData("testuser", "testemail@example.com");
    }

    public UserData showProfile() {
        System.out.println("Profile displayed.");
        return userData;
    }

    public boolean editProfile(UserData newData) {
        System.out.println("Profile edited.\nNew Username: " + newData.getUsername());
        this.userData = newData;
        return true;
    }

    public boolean changeEmail(String newEmail) {
        System.out.println("Email changed: " + newEmail);
        userData.setEmail(newEmail);
        return true;
    }

    public String uploadProfilePic(Image image) {
        System.out.println("Profile picture uploaded.\nFile name: " + image.getFileName());
        return "Profile Picture Path";
    }

    public static class UserData {
        private String username;
        private String email;

        public UserData(String username, String email) {
            this.username = username;
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}