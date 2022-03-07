import { Router } from "express";
import userController from "../controller/userController.js";
import tokenInterceptor from "../../../config/auth/tokenInterceptor.js";

const userRouter = new Router();

userRouter.post("/api/user/auth", userController.getAccessToken);

userRouter.use(tokenInterceptor);
userRouter.get("/api/user/email/:email", userController.findByEmail);

export default userRouter;
