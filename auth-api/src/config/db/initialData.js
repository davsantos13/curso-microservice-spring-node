import bcrypt from "bcrypt";
import User from "../../modules/user/model/User.js";

export async function createInitialData() {
  try {
    await User.sync({ force: true });

    /* let password = "123456";

    bcrypt.genSalt(10, (err, salt) => {
      bcrypt.hash(password, salt, (err, hash) => {
        if (err) throw err;

        password = hash;
      });
    }); */

    let password = await bcrypt.hash("123456", 10);

    await User.create({
      name: "User Test",
      email: "teste@gmail.com",
      password: password,
    });
    
    await User.create({
      name: "User Test2",
      email: "teste2@gmail.com",
      password: password,
    });
  } catch (err) {
    console.error(err);
  }
}
