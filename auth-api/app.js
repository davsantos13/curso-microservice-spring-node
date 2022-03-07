import express from "express";
import * as db from "./src/config/db/initialData.js";
import userRouter from "./src/modules/user/route/userRoutes.js";

const app = express();
const env = process.env;
const PORT = env.PORT || 8080;

db.createInitialData();

app.use(express.json());

app.use(userRouter);

app.get("/api/status", (req, res) => {
  return res.status(200).json({
    service: "AUTH_API",
    status: "UP",
    httpStatus: 200,
  });
});

app.listen(PORT, () => {
  console.info(`Server started successfully at port ${PORT}`);
});
