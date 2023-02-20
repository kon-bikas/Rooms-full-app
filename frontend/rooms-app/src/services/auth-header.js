import UserService from "./user-service";

export function authHeader() {
      const user = UserService.getUser();

      if (user && user.accessToken) {
            return { 'Authorization': `Bearer ${user?.accessToken}` };
      } else {
            return {};
      }

}

export function authBodyHeader() {
      const user = UserService.getUser;

      if (user && user.accessToken) {
            return { 
                  'Content-Type': 'application/json',
                  'Authorization': `Bearer ${user?.accessToken}` 
            };
      } else {
            return {};
      }
}

const AuthHeaderService = {
      authHeader,
      authBodyHeader
};

export default AuthHeaderService;