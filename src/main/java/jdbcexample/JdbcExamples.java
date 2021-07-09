package jdbcexample;

import jdbcexample.manager.UserManager;
import model.Users;

import java.util.List;

public class JdbcExamples {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
//        Users users = Users.builder()
//                .name("poxos")
//                .surName("poxosyan")
//                .email("poxos")
//                .password("poxos")
//                .build();
//        userManager.addUsers(users);
//        List<Users> users = userManager.getUsers();
//        for (Users user : users) {
//            System.out.println(user);
//        }
//
//        userManager.deleteUsers(1);
//
//        users = userManager.getUsers();
//        for (Users user : users) {
//            System.out.println(user);
//        }
        Users usersBYId = userManager.getUsersBYId(2);
        System.out.println(usersBYId);
        usersBYId.setName("valod");
        usersBYId.setSurName("yan");
        userManager.updateUsers(usersBYId);
        System.out.println(usersBYId);


    }
}
