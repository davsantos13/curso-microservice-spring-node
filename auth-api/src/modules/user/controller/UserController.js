import userService from "../service/userService.js";

class UserController {
  async getAccessToken(req, res) {
    let token = await userService.getAccessToken(req);

    return res.status(token.status).json(token);
  }


  async findByEmail(req, res) {
    let response = await userService.findByEmail(req);

    return res.status(response.status).json(response);
  }
}

export default new UserController();
