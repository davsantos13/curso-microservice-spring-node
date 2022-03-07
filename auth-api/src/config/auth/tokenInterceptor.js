import jsonwebtoken from "jsonwebtoken";
import { promisify } from "util";
import * as secrets from "../constants/secrets.js";
import * as statusHttp from "../constants/httpStatus.js";
import AuthException from "./AuthException.js";

const empty = " ";

export default async (req, res, next) => {
  try {
    let { authorization } = req.headers;

    if (!authorization) {
      throw new AuthException(
        statusHttp.UNAUTHORIZED,
        "Token was not informed!"
      );
    }

    let token = authorization;

    if (token.includes(empty)) {
      token = token.split(empty)[1];
    } else {
      token = authorization;
    }

    const decoded = await promisify(jsonwebtoken.verify)(
      token,
      secrets.API_SECRET
    );

    req.authUser = decoded.authUser;

    return next();
  } catch (err) {
    const status = err.status ? err.status : statusHttp.INTERNAL_SERVER_ERROR;
    return res.status(status).json({
      status,
      message: err.message,
    });
  }
};
