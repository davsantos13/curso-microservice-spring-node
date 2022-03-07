import bcrypt from "bcrypt";
import jsonwebtoken from "jsonwebtoken";

import userRepository from "../repository/userRepository.js";
import * as statusHTTP from "../../../config/constants/httpStatus.js";
import UserExceptions from "../exception/UserExceptions.js";
import * as secrets from "../../../config/constants/secrets.js";

class UserService {
  async findByEmail(req) {
    try {
      const { email } = req.params;
      const { authUser } = req;
      this.checkRequest(email);

      let user = await userRepository.findByEmail(email);

      this.checkUserNotFound(user);
      this.checkAuthenticateUser(user, authUser);
      return {
        status: statusHTTP.SUCCESS,
        user: {
          id: user.id,
          name: user.name,
          email: user.email,
        },
      };
    } catch (err) {
      return {
        status: err.status ? err.status : statusHTTP.INTERNAL_SERVER_ERROR,
        message: err.message,
      };
    }
  }

  checkAuthenticateUser(user, authUser) {
    if (!authUser || user.id !== authUser.id) {
      throw new UserExceptions(
        statusHTTP.FORBIDDEN,
        "You can not see this user data"
      );
    }
  }

  async getAccessToken(req) {
    try {
      const { email, password } = req.body;

      this.checkAccessToken(email, password);

      let user = await userRepository.findByEmail(email);

      this.checkUserNotFound(user);
      await this.checkPassword(password, user.password);

      const authUser = {
        id: user.id,
        name: user.name,
        email: user.email,
      };

      const token = jsonwebtoken.sign({ authUser }, secrets.API_SECRET, {
        expiresIn: "1d",
      });

      return {
        status: statusHTTP.SUCCESS,
        token: token,
      };
    } catch (err) {
      return {
        status: err.status ? err.status : statusHTTP.INTERNAL_SERVER_ERROR,
        message: err.message,
      };
    }
  }

  async checkPassword(password, hashPassword) {
    if (!(await bcrypt.compare(password, hashPassword))) {
      throw new UserExceptions(
        statusHTTP.UNAUTHORIZED,
        "Password doesn't match"
      );
    }
  }

  checkAccessToken(email, password) {
    if (!email || !password) {
      throw new UserExceptions(
        statusHTTP.UNAUTHORIZED,
        "Email and Password must be informed!"
      );
    }
  }

  checkRequest(email) {
    if (!email) {
      throw new UserExceptions(
        statusHTTP.BAD_REQUEST,
        "User email was not informed!"
      );
    }
  }

  checkUserNotFound(user) {
    if (!user) {
      throw new UserExceptions(statusHTTP.NOT_FOUND, "User not found!");
    }
  }
}

export default new UserService();
