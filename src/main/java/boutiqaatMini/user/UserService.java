package boutiqaatMini.user;

import java.util.Optional;

public interface UserService {

    Optional<UserModel> CreateUser(UserModel userModel);

}
